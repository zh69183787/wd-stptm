<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<title>资产选择</title>
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
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<% String basePath = request.getContextPath();  %>
<script type="text/javascript">
$(document).ready(function () {
	//
	var $tbInfo = $(".filter .query input:text");
    $tbInfo.each(function () {
         $(this).focus(function () {
             $(this).attr("placeholder", "");
         });
    });
	var $tblAlterRow = $(".table_1 tbody tr:even");
	if ($tblAlterRow.length > 0)
		$tblAlterRow.css("background","#fafafa");
});

$(function(){
	$("input[name=useTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=stopuseTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
});

</script>
 
 

 
 
 
<script type="text/javascript">


function goPage(pageNo,type){
	//type=0,直接跳转到制定页
	if(type=="0"){
  		var pageCount = $("#totalPageCount").val();
  		var number = $("#number").val();
  		if(!number.match(/^[0-9]*$/)){
  			alert("请输入数字");
  			$("#number").val("");
  			$("#number").focus();
  			return ;
  		}
  		if(parseInt(number)>parseInt(pageCount)){
  			$("#number").val(pageCount);
  			$("#pageNo").val(pageCount);
  		}else{
  			$("#pageNo").val(number);
  		}
  	}
	//type=1,跳转到上一页	       
    if(type=="1"){
    	$("#pageNo").val(parseInt($("#number").val())-1);
    }
	//type=2,跳转到下一页	       
    if(type=="2"){
   		$("#pageNo").val(parseInt($("#number").val())+1);
    }
     //type=3,跳转到最后一页,或第一页
    if(type=="3"){
    	$("#pageNo").val(pageNo);
    }
   	$("#form").submit();
} 


//清除搜索条件
function clearSearch(){
	//$("#search tr:lt(3) td input").val("");
	$("input:visible:lt(4)").each(function(){
		$(this).val("");
	});
	
	$("option:selected").each(function(){
		$(this).attr("selected",false);
	});
}


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
			url : '<%=basePath%>/asset/findTypeByCodeInfoId.action?random='+Math.random(),
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
			url : '<%=basePath%>/asset/findStationByLine.action?random='+Math.random(),
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


$(function(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide")
		$(".filter").css("display","none");
	else
		$(".filter").css("display","block");

});

//控制显示或隐藏查询条件
function showOrHideControl(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide"){
		$(".filter").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
		$(".filter").slideUp();
		$("input[name=showOrHide]").val("hide");
	}
}

</script>
 
 
 
 
</head>

<body>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起" ></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">设备管理</a></li>
				<li class="fin">资产选择</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="javascript:void(0);" onclick="showOrHideControl();">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8">
<input type="hidden" value="<s:property value="#request.type2"/>" id="hidden_type2">
<input type="hidden" value="<s:property value="#request.type3"/>" id="hidden_type3">			
			<s:form action="showAssetSearchPage" name="equipment" namespace="/equipment" method="post" id="form" >
			<input type="hidden" name="showOrHide" value="<s:property value='#request.showOrHide'/>"/>
			<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td class="t_r">大类</td>
						<td>
							<select name="type1" onchange="clearOPtions(0);chooseType(2);" class="input_large">
								<option value="-1">---请选择---</option>
									<s:iterator value="#request.type1List" id="type1">
										<s:if test="#request.type1==#type1.id">
											<option value="<s:property value="#type1.id"/>" selected="selected"><s:property value="#type1.name" /></option>
										</s:if>
										<s:else>
											<option value="<s:property value="#type1.id"/>"><s:property value="#type1.name"/></option>
										</s:else>
									</s:iterator>
							</select>
						</td>
						<td class="t_r">中类</td>
						<td>
							<select name="type2" onchange="clearOPtions(1);chooseType(3);" class="input_large">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.type2List" id="type2">
									<s:if test="#request.type2==#type2.id">
										<option value="<s:property value="#type2.id"/>" selected="selected"><s:property value="#type2.name" /></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#type2.id"/>"><s:property value="#type2.name"/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<td class="t_r">小类</td>
						<td>
							<select name="type3" class="input_large">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.type3List" id="type3">
									<s:if test="#request.type3==#type3.id">
										<option value="<s:property value="#type3.id"/>" selected="selected"><s:property value="#type3.name" /></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#type3.id"/>"><s:property value="#type3.name"/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="t_r">所属线路</td>
						<td>
							<select name="routeNum" class="input_large" onchange="clearStations();showStations(this);">
								<option value='-1'>---请选择---</option>
								<s:iterator value="#request.lineList" id="list">
									<s:if test="#request.routeNum==#list.id">
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>" selected="selected"><s:property value='#list.name'/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>"><s:property value='#list.name'/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<td class="t_r">所属车站</td>
						<td>
							<select name="stationNum" class="input_large">
								<option value='-1'>---请选择---</option>
								<s:iterator value="#request.stationList" id="list">
									<s:if test="#request.stationNum==#list.id">
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>" selected="selected"><s:property value='#list.name'/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>"><s:property value='#list.name'/></option>
									</s:else>
								</s:iterator>
								
							</select>
						</td>
						<td class="t_r">权属单位</td>
						<td>
							<select name="ownerDuty">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.unitList" id="unit">
									<s:if test="#request.ownerDuty==#unit.id">
										<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>" selected="selected"><s:property value="#unit.name"/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>"><s:property value="#unit.name"/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="t_r lableTd">开始使用日期</td>
						<td>
							<input type="text" id="" name="useTime" class="input_large date" readonly="readonly" value="<s:property value='#request.useTime'/>"/>
						</td>
						<td class="t_r lableTd">停止使用日期</td>
						<td>
							<input type="text" id="" name="stopuseTime" class="input_large date" readonly="readonly" value="<s:property value='#request.stopuseTime'/>"/>
						</td>
						<td class="t_r">使用单位</td>
						<td>
							<select name="userDuty">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.unitList" id="unit">
									<s:if test="#request.userDuty==#unit.id">
										<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>" selected="selected"><s:property value="#unit.name"/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#unit.id"/>" id="<s:property value="#unit.code"/>"><s:property value="#unit.name"/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="t_r">权属责任人</td>
						<td>
							<input type="text" name="owner" id="textfield" class="input_large" value="<s:property value='#request.owner'/>"/>
						</td>
						<td class="t_r">使用责任人</td>
						<td>
							<input type="text" name="usePerson" id="textfield" class="input_large" value="<s:property value='#request.usePerson'/>"/>
						</td>
						<td class="t_r">维护部门</td>
						<td>
							<select name="maintainDep">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.maintainDeptList" id="dept">
									<s:if test="#request.maintainDep==#dept.id">
										<option value="<s:property value="#dept.id"/>" id="<s:property value="#dept.code"/>" selected="selected"><s:property value="#dept.name"/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#dept.id"/>" id="<s:property value="#dept.code"/>"><s:property value="#dept.name"/></option>
									</s:else>
								</s:iterator>
								
							</select>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="4" class="t_c">
							<input type="submit" value="查 询" />&nbsp;&nbsp; 
							<input type="button" value="重 置" onclick="clearSearch();"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			<!-- 
			<div class="fn">
				<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();">
			</div>
			 -->
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="14">&nbsp;&nbsp;资产信息列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td style="white-space: nowrap;">序号</td>
					<td style="white-space: nowrap;">资产编号</td>
					<td style="white-space: nowrap;">资产名称</td>
					<td style="white-space: nowrap;">规格</td>
					<td style="white-space: nowrap;">型号</td>
					<td style="white-space: nowrap;">原值(元)</td>
					<td style="white-space: nowrap;">净值(元)</td>
					<td style="white-space: nowrap;">出厂价(元)</td>
					<td style="white-space: nowrap;">决算价(元)</td>
					<td style="white-space: nowrap;">当前设备状态</td>
					<td style="white-space: nowrap;">所属线路</td>
					<td>操作</td>
				</tr>
				
				<s:iterator value="#request.page.result" id="assetInfo" status="st">
					<tr class="t_c">
						<td class="t_c"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_c"><s:property value="#assetInfo.assetId"/></td>
						<td class="t_c"><s:property value="#assetInfo.assetName"/></td>
						<td class="t_c"><s:property value="#assetInfo.specification"/></td>
						<td class="t_c"><s:property value="#assetInfo.model"/></td>
						<td class="t_c"><s:property value="#assetInfo.yuanzhi"/></td>
						<td class="t_c"><s:property value="#assetInfo.jingzhi"/></td>
						<td class="t_c"><s:property value="#assetInfo.leaveFactoryPrice"/></td>
						<td class="t_c"><s:property value="#assetInfo.finalAccountPrice"/></td>
						<td class="t_c">
							<s:iterator value="#request.equipmentStatusList" id="equipmentStatus" >
								<s:if test="#equipmentStatus.id==#assetInfo.equipmentState">
									<s:property value="#equipmentStatus.name"/>									
								</s:if>
							</s:iterator>
						</td>
						<td class="t_c">
							<s:iterator value="#request.lineList" id="line" >
								<s:if test="#line.id==#assetInfo.routeNum">
									<s:property value="#line.name"/>									
								</s:if>
							</s:iterator>
						</td>
						<td class="t_c">
							<a href="showAdd.action?id=<s:property value="#assetInfo.id"/>" style="float:inline;" target="_blank">添加设备</a>
						</td>
						<!--
						<td>
							<a href="javascript:void(0);" style="float:inline;" onclick="showEditPage('<s:property value="#assetInfo.id"/>')">编辑</a>
						</td>
						<td>
							<a href="#" style="float:inline;" onclick="deleteData('<s:property value="#assetInfo.id"/>');">删除</a>
						</td>
						-->
					</tr>
				</s:iterator>
				
				
				
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="14"><div class="clearfix">
			      <s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>
			      <s:else>
			      	<span class="fl">
				      	<s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
					    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
					      	<s:property value="#request.page.totalSize"/>条
					    </s:if>
					    <s:else>
					    	<s:property value="#request.page.start+#request.page.pageSize-1"/>条
						</s:else>
				    </span>
			        <ul class="fr clearfix pager">
			          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
			          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
			            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
			            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
		           	  </li>
		           	  
		               <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
		               	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		               </s:if>
		               <s:else>
		                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
		               </s:else>
		                
		              <li>
		              	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
		              		<a href="javascript:void(0)">下一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
		              	</s:else>
		              </li>
		              
		              <li>
		              	<s:if test="#request.page.currentPageNo==1">
		              		<a href="javascript:void(0)">上一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
		              	</s:else>
		              </li>
		              
		              <s:if test="#request.page.currentPageNo==1">
		              	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
		              </s:if>
		              <s:else>
		              	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
		              </s:else>
		         </ul>
		       </s:else>
		       </div></td>
		     </tr>
		</table>
		</div>
		<!--Table End-->
	</div>
</body>
</html>
