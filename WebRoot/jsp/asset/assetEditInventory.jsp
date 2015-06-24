<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资产编辑</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<!--<script src="../js/show.js"></script>-->
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");
			
			//loadShow();			
		});
$(function(){
	$("input[name=makeTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});	
	$("input[name=useTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("input[name=stopuseTime]").datepicker("option","minDate",selectedDate);
		}
	});
	$("input[name=stopuseTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("input[name=useTime]").datepicker("option","maxDate",selectedDate);
		}
	});
	
	$("input[name=shelfLife]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});			
	$("input[name=handoverTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});		
	
	$("input[name=purchaseTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});		
	
	$("input[name=discardTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});	
	
	$("input[name=provideTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});	
	
});		
</script>



<script type="text/javascript">

$(function(){
	$("td[id=must]").each(function(){
		$(this).attr("style","color:red;white-space:nowrap;");
	});

	var zlh = $("input[name=assetId]").val().substr(4,1);
	
	$("select[name=zlh] option").each(function(){
		if($(this).val()==zlh){
			$(this).attr("selected",true);
		}
	});
	
	
});


</script>



<script type="text/javascript">

//查询类别
function chooseType(type){
	var id ;
	var appendElement;
	if(type==2){			//大类查中类
		if($("select[name=type1]").val()=='-1'){
			alert("请先选择大类！");
			return ;
		}
		id = $("select[name=type1]").val();
		$appendElement = $("select[name=type2]");
	}else if(type==3){		//中类查小类
		if($("select[name=type2]").val()=='-1'){
			alert("请先选择中类！");
			return ;
		}
		id = $("select[name=type2]").val();
		$appendElement = $("select[name=type3]");
	}
	$.ajax({
			type : 'post',
			url : 'findTypeByCodeInfoId.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				//var addOptions ;
				var addOptions = "<option value='-1'>---请选择---</option>";
				if(object!=null && object.length>1){
					for(var i=0; i<object.length; i++){
						addOptions += "<option value='"+object[i].id+"' id='"+object[i].code+"'>"+object[i].name+"</option>";
					}
				}
				$appendElement.html(addOptions);
			}	
		});
}

//清楚中类、小类
function clearOPtions(num){
	$("select[name^=type]").each(function(index){
		if(index > num)
			$(this).children(":first").attr("selected",true);
	});
}

//清除车站
function clearStations(){
	$("select[name=stationNum]").html("<option value='-1'>---请选择---</option>");
}

//查询车站
function showStations(obj){
	$.ajax({
			type : 'post',
			url : 'findStationByLine.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:$("select[name=routeNum]").val()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				var html ;
				for(var i=0; i<object.length; i++){
					html += "<option value='"+object[i].id+"' id='"+object[i].code+"'>"+object[i].name+"</option>"
				}
				html += "&nbsp;*";
				$("select[name=stationNum]").append(html);
			}	
		});
}



//检查表单
function checkForm(){
	var value ;

	
	//资产编号
	value = $("input[name=assetId]").val();
	if(!checkValueEmpty(value)){
		alert("资产编号不能为空！");
		$("input[name=assetId]").focus();
		return false;
	}else{
		if(!checkValueNumber(value)){
			alert("资产编号由20位的数字组成！");
			$("input[name=assetId]").focus();
			return false;
		}else{
			if(value.length!=20){
				alert("资产编号由20位的数字组成，请检查资产编号是否填写正确！");
				$("input[name=assetId]").focus();
				return false;
			}
		}
	}
	
	//资产名称
	value = $("input[name=assetName]").val();
	if(!checkValueEmpty(value)){
		alert("资产名称不能为空！");
		$("input[name=assetName]").focus();
		return false;
	}
	
	//生产商
	value = $("#showManufacturer").val();
	if(!checkValueEmpty(value)){
		alert("请填写生产商！");
		$("#showManufacturer").focus().select();
		return false;
	}
	value = $("#hideManufacturer").val();
	if(!checkValueEmpty(value)){
		alert("该生产商不存在");
		$("#showManufacturer").focus().select();
		return false;
	} 
	
	//供应商
	value = $("#showVendor").val();
	if(!checkValueEmpty(value)){
		alert("请填写供应商！");
		$("#showVendor").focus().select();
		return false;
	}
	value = $("#hideVendor").val();
	if(!checkValueEmpty(value)){
		alert("该供应商不存在");
		$("#showVendor").focus().select();
		return false;
	} 
	
	//大类
	value = $("select[name=type1]").val();
	if(value=='-1'){
		alert("请选择大类！");
		$("select[name=type1]").focus();
		return false;
	} 
	//中类
	value = $("select[name=type2]").val();
	if(value=='-1'){
		alert("请选择中类！");
		$("select[name=type2]").focus();
		return false;
	} 
	/*
	//小类
	value = $("select[name=type3]").val();
	if(value=='-1'){
		alert("请选择小类！");
		$("select[name=type3]").focus();
		return false;
	} */
	
	//出厂日期
	value = $("input[name=makeTime]").val();
	if(!checkValueEmpty(value)){
		alert("请选择出厂日期！");
		$("input[name=makeTime]").focus();
		return false;
	} 
	
	//预期使用寿命
	value = $("input[name=useLife]").val();
	if(!checkValueEmpty(value)){
		alert("预期使用寿命不能为空！");
		$("input[name=useLife]").focus().select();
		return false;
	}else{
		if(!checkValueNumber(value)){
			alert("请输入正整数！");
			$("input[name=useLife]").focus();
			return false;
		}
	} 
	
	//安装地点
	value = $("input[name=buildAddr]").val();
	if(!checkValueEmpty(value)){
		alert("请填写安装地点！");
		$("input[name=buildAddr]").focus().select();
		return false;
	}
	
	//所属线路
	value = $("select[name=routeNum]").val();
	if(value=='-1'){
		alert("请选择线路！");
		$("select[name=routeNum]").focus();
		return false;
	} 
	//所属车站
	value = $("select[name=stationNum]").val();
	if(value=='-1'){
		alert("请选择车站！");
		$("select[name=stationNum]").focus();
		return false;
	} 
	
	//使用责任人
	value = $("#showUsePerson").val();
	if(!checkValueEmpty(value)){
		alert("请填写使用责任人！");
		$("#showUsePerson").val("").focus();
		return false;
	}
	
	value = $("#hideUsePerson").val();
	if(!checkValueEmpty(value)){
		alert("该用户不存在，请重新输入！");
		$("#showUsePerson").val("").focus();
		return false;
	}
	
	//权属责任人
	value = $("#showOwner").val();
	if(!checkValueEmpty(value)){
		alert("请填写权属责任人！");
		$("#showOwner").val("").focus();
		return false;
	}
	
	value = $("#hideOwner").val();
	if(!checkValueEmpty(value)){
		alert("该用户不存在，请重新输入！");
		$("#showOwner").val("").focus();
		return false;
	}
	
	//权属单位
	value = $("select[name=ownerDuty]").val();
	if(value=='-1'){
		alert("请选择权属单位！");
		$("select[name=ownerDuty]").focus();
		return false;
	} 
	
	//使用单位
	value = $("select[name=userDuty]").val();	
	if(value=='-1'){
		alert("请选择使用单位！");
		$("select[name=userDuty]").focus();
		return false;
	} 
	
	//维护部门
	value = $("select[name=maintainDep]").val();
	if(value=='-1'){
		alert("请选择维护部门！");
		$("select[name=maintainDep]").focus();
		return false;
	} 
	
	//出厂价--非必填，必须是为正的数字
	value = $("input[name=leaveFactoryPrice]").val();
	if(checkValueEmpty(value)){
		if(!checkPrice(value)){
			alert("请填写正确的数字！");
			$("input[name=leaveFactoryPrice]").focus().select();
			return false;		
		}
	} 
	//合同价--非必填，必须是为正的数字
	value = $("input[name=compactPrice]").val();
	if(checkValueEmpty(value)){
		if(!checkPrice(value)){
			alert("请填写正确的数字！");
			$("input[name=compactPrice]").focus().select();
			return false;		
		}
	} 
	
	//原值--非必填，必须是为正的数字
	value = $("input[name=yuanzhi]").val();
	if(!checkValueEmpty(value)){
		alert("请填写原值！");
		$("input[name=yuanzhi]").focus();
		return false;
	}else{
		if(!checkPrice(value)){
			alert("请填写正确的数字！");
			$("input[name=yuanzhi]").focus().select();
			return false;		
		}
	} 
	
	//折旧方法
	value = $("select[name=depreciationWay]").val();	
	if(value=='-1'){
		alert("请选择折旧方法！");
		$("select[name=depreciationWay]").focus();
		return false;
	} 
	
	//当前使用状态
	value = $("select[name=useState]").val();	
	if(value=='-1'){
		alert("请选择当前使用状态！");
		$("select[name=useState]").focus();
		return false;
	} 
	
	//当前设备状态
	value = $("select[name=equipmentState]").val();	
	if(value=='-1'){
		alert("请选择当前设备状态！");
		$("select[name=equipmentState]").focus();
		return false;
	} 
	
	//铭牌张贴部位
	value = $("select[name=mpztbw]").val();	
	if(value=='-1'){
		alert("请选择铭牌张贴部位！");
		$("select[name=mpztbw]").focus();
		return false;
	} 
	
	//残值率
	value = $("input[name=czl]").val();
	if(!checkValueEmpty(value)){
		alert("请填写残值率！");
		$("input[name=czl]").focus();
		return false;
	}else{
		if(!checkPrice(value)){
			alert("请填写正确的数字！");
			$("input[name=czl]").focus().select();
			return false;		
		}else{
			if(parseInt(value)>100){
				alert("残值率不得高于100");
				$("input[name=czl]").focus().select();
				return false;
			}
		}
	} 
	
	//检查资产编号是否符合规则
	value = $("input[name=assetId]").val();		//资产编号
	var zcqsh =	$("select[name=ownerDuty]").children("option:selected").attr("id");		//资产权属号2位 
	var syqsh = $("select[name=userDuty]").children("option:selected").attr("id");		//使用权属号2位
	var zongleihao = 	$("select[name=zlh]").val();	//总类号1位
	var whbmh = $("select[name=maintainDep]").children("option:selected").attr("id");		//维护部门号2位
	var xlwlh = $("select[name=routeNum]").children("option:selected").attr("id");		//线路网络号2位(线路)
	
	var zch ;
	if($("select[name=stationNum]").children("option:selected")!='undefined'){
		zch = $("select[name=stationNum]").children("option:selected").attr("id").substr(2,4);		//车站号2位
	}	
	
	var dlh = $("select[name=type1]").children("option:selected").attr("id");		//大类号2位
	var zlh = $("select[name=type2]").children("option:selected").attr("id").substr(2,4);		//中类号2位
	var xlh;
	if($("select[name=type3]").children("option:selected").attr("id").substr(4,6)!='undefined'){
		xlh = $("select[name=type3]").children("option:selected").attr("id").substr(4,6);			//小类号2位type3
	}else{
		xlh == '00';
	}
	//var sxh	=			//顺序号3位
	

	if(value.substr(0,2)!=zcqsh){
		alert("资产编号第1位和第2位数字与权属单位编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(2,2)!=syqsh){
		alert("资产编号第3位和第4位数字与使用单位编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(4,1)!=zongleihao){
		alert("资产编号第5位数字与总类号编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(5,2)!=whbmh){
		alert("资产编号第6位和第7位数字与维护部门编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(7,2)!=xlwlh){
		alert("资产编号第8位和第9位数字与所属线路编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(9,2)!=zch){
		alert("资产编号第10位和第11位数字与所属车站编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(11,2)!=dlh){
		alert("资产编号第12位和第13位数字与大类编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(13,2)!=zlh){
		alert("资产编号第14位和第15位数字与中类编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	if(value.substr(15,2)!=xlh){
		alert("资产编号第16位和第17位数字与小类编号不同，请检查资产编号是否填写正确！");
		$("input[name=assetId]").focus();
		return false;
	}
	return true;

}

//检查数据是否为空
function checkValueEmpty(value){
	if(value==null || value=="" || value=="undifined"){
		return false;	//空	
	}
	return true;	//非空
}

//检查数据是否符合长度
function checkValueLength(value,min,max){



}

//正整数校验
function checkValueNumber(value){
	var reg = /^\d*$/;
	return reg.test(value);
}

//价格校验
function checkPrice(value){
	var reg = /^\d*\.?\d+$/;
	return reg.test(value);
}

//清除数据
function clearData(){
	$("tbody input:gt(0):lt(30)").val("");
	$("tbody select").each(function(){
		$(this).children("option:first").attr("selected",true);
	});	
	$("textarea").val("");

}

</script>






</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">资产编辑</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<div class="mb10">
<s:form action="saveEdit" name="assetInfo" namespace="/asset" method="post">
<input name="id" value="<s:property value="assetInfo.id"/>" type="hidden">
<input name="xh" value="<s:property value="assetInfo.xh"/>" type="hidden" >
	<table width="100%" class="table_1">
		<thead>
			<th colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重 置" /> &nbsp;
			</th>
		</thead>
		<tbody>
			<!-- 未找到,数据空中没有字段 -->
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">资产基本信息</td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">资产编号</td>
				<td id="must"><input type="text" name="assetId" class="input_large" value="<s:property value="assetInfo.assetId"/>" readonly="readonly"/>&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">资产名称</td>
				<td id="must"><input type="text" name="assetName" class="input_large" value="<s:property value="assetInfo.assetName"/>"/>&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">生产商</td>
				<td id="must">
					<select name="manufacturer">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.manufacturerList" id="manufacturer">
							<s:if test="assetInfo.manufacturer==#manufacturer.id">
								<option value="<s:property value="#manufacturer.id"/>" style="width:200px;" selected="selected"><s:property value="#manufacturer.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#manufacturer.id"/>" style="width:200px;"><s:property value="#manufacturer.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">规格</td>
				<td><input type="text" id="" name="specification" class="input_large" value="<s:property value="assetInfo.specification"/>"/></td>
				<td class="t_r lableTd">型号</td>
				<td><input type="text" id="" name="model" class="input_large" value="<s:property value="assetInfo.model"/>"/></td>
				<td class="t_r lableTd">供应商</td>
				<td id="must">
					<select name="vendor">
						<s:iterator value="#request.supplierList" id="supplier">
							<s:if test="assetInfo.vendor==#manufacturer.id">
								<option value="<s:property value="#supplier.id"/>" style="width:200px;" selected="selected"><s:property value="#supplier.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#supplier.id"/>" style="width:200px;"><s:property value="#supplier.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">大类</td>
				<td id="must">
					<select name="type1" class="input_large" onchange="clearOPtions(0);chooseType(2);">
						<option value='-1'>---请选择---</option>
						<s:iterator value="#request.type1List" id="type1">
							<s:if test="assetInfo.type1==#type1.id">
								<option value="<s:property value="#type1.id"/>" id="<s:property value="#type1.code"/>" selected="selected"><s:property value='#type1.name'/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#type1.id"/>" id="<s:property value="#type1.code"/>" ><s:property value='#type1.name'/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
				<td class="t_r lableTd">中类</td>
				<td id="must">
					<select name="type2" class="input_large" onchange="chooseType(3);clearOPtions(1);" >
						<option value='-1'>---请选择---</option>
						<s:iterator value="#request.type2List" id="type2">
							<s:if test="assetInfo.type2==#type2.id">
								<option value="<s:property value="#type2.id"/>" id="<s:property value="#type2.code"/>" selected="selected"><s:property value='#type2.name'/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#type2.id"/>" id="<s:property value="#type2.code"/>" ><s:property value='#type2.name'/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
				<td class="t_r lableTd">小类</td>
				<td id="must">
					<select name="type3" class="input_large">
						<option value='-1'>---请选择---</option>
						<s:iterator value="#request.type3List" id="type3">
							<s:if test="assetInfo.type3==#type3.id">
								<option value="<s:property value="#type3.id"/>" id="<s:property value="#type3.code"/>" selected="selected"><s:property value='#type3.name'/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#type3.id"/>" id="<s:property value="#type3.code"/>" ><s:property value='#type3.name'/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">供应日期</td>
				<td><input type="text" id="" name="provideTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.provideTime"/>"/></td>
				<td class="t_r lableTd">出厂日期</td>
				<td id="must"><input type="text" id="" name="makeTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.makeTime"/>"/>&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">预期使用寿命(月)</td>
				<td id="must"><input type="text" id="" name="useLife" class="input_large" value="<s:property value="assetInfo.useLife"/>"/>&nbsp;&nbsp;*</td>
			</tr>
			<tr>
				<td class="t_r lableTd">总类号</td>
				<td colspan="5">
					<select name="zlh" id="zlh">
						<option value="1">资产(固定资产)</option>
						<option value="2">物资</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd"> 安装地点 </td>
				<td colspan="5" id="must"><input type="text" name="buildAddr" class="input_large" style="width:500px;" value="<s:property value="assetInfo.buildAddr"/>"/>&nbsp;&nbsp;*</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注 </td>
				<td colspan="5">
					<textarea rows="3" cols="4" name="memo" style="width: 500px;"><s:property value="assetInfo.memo"/></textarea>					
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">设备清单     </td>
				<td colspan="5"><input type="text" id="" name="shebeiqingdan" class="input_large" value="<s:property value="assetInfo.shebeiqingdan"/>"/></td>
			</tr>
			<tr><td colspan="6" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">资产所属信息</td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">所属线路</td>
				<td id="must">
					<select name="routeNum" class="input_large" onchange="clearStations();showStations(this);">
						<option value='-1'>---请选择---</option>
						<s:iterator value="#request.lineList" id="line">
							<s:if test="assetInfo.routeNum==#line.id">
								<option value="<s:property value="#line.id"/>" style="width:200px;" selected="selected"><s:property value="#line.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#line.id"/>" style="width:200px;"><s:property value="#line.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
				<td class="t_r lableTd">所属车站</td>
				<td id="must">
					<select name="stationNum" class="input_large">
						<option value='-1'>---请选择---</option>
						<s:iterator value="#request.stationList" id="station">
							<s:if test="assetInfo.stationNum==#station.id">
								<option value="<s:property value="#station.id"/>" style="width:200px;" selected="selected"><s:property value="#station.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#station.id"/>" style="width:200px;"><s:property value="#station.name"/></option>
							</s:else>
						</s:iterator>
					</select>
				</td>
				<td class="t_r lableTd">权属单位</td>
				<td id="must">
					<select name="ownerDuty">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.unitList" id="unit">
							<s:if test="assetInfo.ownerDuty==#unit.id">
								<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>" selected="selected"><s:property value="#unit.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>"><s:property value="#unit.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">使用责任人</td>
				<td id="must"><input type="text" id="" name="usePerson" class="input_large" value="<s:property value="assetInfo.usePerson"/>"/>&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">权属责任人</td>
				<td id="must"><input type="text" id="" name="owner" class="input_large" value="<s:property value="assetInfo.owner"/>"/>&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">使用单位</td>
				<td id="must">
					<select name="userDuty">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.unitList" id="unit">
							<s:if test="assetInfo.userDuty==#unit.id">
								<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>" selected="selected"><s:property value="#unit.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>"><s:property value="#unit.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">开始使用日期</td>
				<td><input type="text" id="" name="useTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.useTime"/>"/></td>
				<td class="t_r lableTd">停止使用日期</td>
				<td><input type="text" id="" name="stopuseTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.stopuseTime"/>"/></td>
				<td class="t_r lableTd">维护部门</td>
				<td id="must">
					<select name="maintainDep">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.maintainDeptList" id="dept">
							<s:if test="assetInfo.maintainDep==#dept.id">
								<option value="<s:property value="#dept.id"/>" id="<s:property value="#dept.code"/>" selected="selected"><s:property value="#dept.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#dept.id"/>" id="<s:property value="#dept.code"/>"><s:property value="#dept.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">保修期至</td>
				<td><input type="text" id="" name="shelfLife" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.shelfLife"/>"/></td>
				<td class="t_r lableTd">移交时间</td>
				<td><input type="text" id="" name="handoverTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.handoverTime"/>"/></td>
				<td class="t_r lableTd">购买时间</td>
				<td><input type="text" id="" name="purchaseTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.purchaseTime"/>"/></td>
			</tr>
			<tr><td colspan="6" style="background-color: #FFFFFF;height: 40px;"></td></tr>
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;">资产价值信息</td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">报废时间</td>
				<td><input type="text" id="" name="discardTime" class="input_large date" readonly="readonly" value="<s:property value="assetInfo.discardTime"/>"/></td>
				<td class="t_r lableTd">出厂价</td>
				<td><input type="text" id="" name="leaveFactoryPrice" class="input_large" value="<s:property value="assetInfo.leaveFactoryPrive"/>"/></td>
				<td class="t_r lableTd">合同价</td>
				<td><input type="text" id="" name="compactPrice" class="input_large" value="<s:property value="assetInfo.compactPrice"/>"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">原值(元)</td>
				<td id="must"><input type="text" id="" name="yuanzhi" class="input_large" value="<s:property value="assetInfo.yuanzhi"/>"/>&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">决算价(元)</td>
				<td><input type="text" id="" name="finalAccountPrice" class="input_large" value="<s:property value="assetInfo.finalAccountPrice"/>"/></td>
				<td class="t_r lableTd">本期折旧(元)</td>
				<td><input type="text" id="" name="benqizhijiu" class="input_large" value="<s:property value="assetInfo.benqizhijiu"/>"/></td>
			</tr>
			
			<tr>
				<td class="t_r lableTd">累计折旧(元)</td>
				<td><input type="text" id="" name="leijizhejiu" class="input_large" value="<s:property value="assetInfo.leijizhejiu"/>"/></td>
				<td class="t_r lableTd">折旧方法</td>
				<td id="must">
					<select name="depreciationWay">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.depreciationList" id="depreciation">
							<s:if test="assetInfo.depreciationWay==#depreciation.id">
								<option value="<s:property value="#depreciation.id"/>" selected="selected"><s:property value="#depreciation.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#depreciation.id"/>"><s:property value="#depreciation.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
				<td class="t_r lableTd">净值(元)</td>
				<td><input type="text" id="" name="jingzhi" class="input_large" value="<s:property value="assetInfo.jingzhi"/>"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">当前使用状态</td>
				<td id="must">
					<select name="useState">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.useStatusList" id="useStatus">
							<s:if test="assetInfo.useState==#useStatus.id">
								<option value="<s:property value="#useStatus.id"/>" selected="selected"><s:property value="#useStatus.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#useStatus.id"/>"><s:property value="#useStatus.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
				<td class="t_r lableTd">当前设备状态</td>
				<td id="must">
					<select name="equipmentState">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.equipmentStatusList" id="equipmentStatusList">
							<s:if test="assetInfo.equipmentState==#equipmentStatusList.id">
								<option value="<s:property value="#equipmentStatusList.id"/>" selected="selected"><s:property value="#equipmentStatusList.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#equipmentStatusList.id"/>"><s:property value="#equipmentStatusList.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
				<td class="t_r lableTd">铭牌张贴部位 </td>
				<td id="must">
					<select name="mpztbw">
						<option value="-1">---请选择---</option>
						<s:iterator value="#request.namePlatePosition" id="namePlatePosition">
							<s:if test="assetInfo.mpztbw==#namePlatePosition.id">
								<option value="<s:property value="#namePlatePosition.id"/>" selected="selected"><s:property value="#namePlatePosition.name"/></option>
							</s:if>
							<s:else>
								<option value="<s:property value="#namePlatePosition.id"/>"><s:property value="#namePlatePosition.name"/></option>
							</s:else>
						</s:iterator>
					</select>
					&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">残值率</td>
				<td colspan="5" style="white-space: nowrap;"><input type="text" id="" name="czl" class="input_large" value="<s:property value="assetInfo.czl"/>"/>%&nbsp;&nbsp;<font color="red" style="float:right;">*</font></td>
			</tr>
			 
			
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重 置" />&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
