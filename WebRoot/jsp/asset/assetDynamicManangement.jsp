<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<title>资产动态管理</title>
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
function crossDomainShowOrHide(){
	//$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
	$("#iframe").attr("src","http://10.1.40.201:8088/portal/portal.jsp?random=Math.random()");
}  
</script>
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
</script>
 
 
<script type="text/javascript">
//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		window.location.href = "showDynamicManagement.action";
		clearInterval(refresh);
	}
}

//跳转到新增页面
function showAddPage(){
	sonWindow = window.open('showAdd.action');
}

function showEditPage(id){
	sonWindow = window.open('showHistoryEdit.action?paramId='+id+'&saveType=1');
}
</script> 
 
 
 
<script type="text/javascript">
//删除
function deleteData(id){
	
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'delete.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("form").submit();
			}
		});
	}
}

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


//清楚搜索条件
function clearSearch(){
	$("#search").find("select").each(function(){
		$(this).children("option:first").attr("selected",true);
	});
	$("#search").find("input:lt(2)").val("");
}


$(function(){

	var status = $("input[name=showOrHide]").val();
	if(status=="hide")
		$(".filter").css("display","none");
	else
		$(".filter").css("display","block");

	
});

//控制显示或隐藏查询条件
function showOrHideControl(obj){
	var status = $("input[name=showOrHide]").val();
	var $li = $(obj).parent();
	if(status=="hide"){
		$li.addClass("selected");
		$(".filter").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
		$li.removeClass("selected");	
		$(".filter").slideUp();
		$("input[name=showOrHide]").val("hide");
	}
}

$(function(){
	 $("img").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 );
});

</script>
 
<!-- 搜索条件js -->
<script type="text/javascript">

//清楚中类、小类
function clearOPtions(num){
	if(num=="0"){	//清除中类、小类
		$("select[name^=type]:gt(0)").each(function(index){
			$optionFirst = $(this).children(":first").attr("selected",true);
			$(this).children().remove();
			$(this).append($optionFirst);
		});
	}else if(num=="1"){		//清除小类
		$select = $("select[name^=type]:eq(2)");
		$select.children(":first").attr("selected",true);
		$optionFirst = $select.children(":first");
		$select.children().remove();
		$select.append($optionFirst);
	}
}


//查询类别
function chooseType(type){
	var id ;
	var appendElement;
	if(type==2){			//大类查中类
		if($("select[name=type1]").val()=='-1'){
			//alert("请先选择大类！");
			return ;
		}
		id = $("select[name=type1]").val();
		$appendElement = $("select[name=type2]");
	}else if(type==3){		//中类查小类
		if($("select[name=type2]").val()=='-1'){
			//alert("请先选择中类！");
			return ;
		}
		id = $("select[name=type2]").val();
		$appendElement = $("select[name=type3]");
	}
	$.ajax({
			type : 'post',
			url : "<%=basePath%>/asset/findTypeByCodeInfoId.action?random="+Math.random(),
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

//清除车站
function clearStations(){
	$("select[name=stationNum]").html("<option value='-1'>---请选择---</option>");
}

//查询车站
function showStations(obj){
	$.ajax({
			type : 'post',
			url : "<%=basePath%>/asset/findStationByLine.action?random="+Math.random(),
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


function setPageNo(no){
	$("#pageNo").val(no);
}

$(function(){
	$("input[name=operateTime]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
});

//导出EXCEL
function exportExcel(){ 
//   var type1 = #("input[name=type1]").val();
	 var type1 = $("#type1").val();
     var type2 = $("#type2").val();
   	 var type3 = $("#type3").val();
     var assetId = $("#assetId").val();
     var routeNum = $("#routeNum").val();
     var stationNum = $("#stationNum").val();
	 var ownerDuty = $("#ownerDuty").val();
     var operateTime = $("#operateTime").val();
    
     var param ="?assetId="+encodeURI(encodeURI(assetId))+"&routeNum="+encodeURI(encodeURI(routeNum))+"&stationNum="+encodeURI(encodeURI(stationNum))+
     "&ownerDuty="+encodeURI(encodeURI(ownerDuty))+"&operateTime="+encodeURI(encodeURI(operateTime))+"&type1="+encodeURI(encodeURI(type1))+
	 "&type2="+encodeURI(encodeURI(type2))+"&type3="+encodeURI(encodeURI(type3));
  //  alert(param);   // encodeURI(encodeURI("要编码的值"))
     window.location.href="exportExcel!DynamicManagementExportExcel.action"+param;
   }
</script>
 
 
</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>	
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">资产管理</a></li>
				<li class="fin">资产动态管理</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl(this);">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8">
			<s:form action="showDynamicManagement" name="assetInfo" namespace="/asset" method="post" id="form">
			<input type="hidden" name="showOrHide" value="<s:property value='#request.showOrHide'/>"/>
			<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
					<tr>
						<td class="t_r" style="white-space: nowrap;">大类</td>
						<td><!--
							<select name="type1" id="type1" onchange="clearOPtions(0);chooseType(2);setMaxCountEmpty();" class="input_large">
							-->
							<select name="type1" id="type1" onchange="clearOPtions(0);chooseType(2);" class="input_large">
								<option value="-1">---请选择---</option>
									<s:iterator value="#request.type1List" id="type1">
										<s:if test="#request.assetInfo.type1==#type1.id">
											<option value="<s:property value="#type1.id"/>" selected="selected"><s:property value="#type1.name"/></option>	
										</s:if>
										<s:else>
											<option value="<s:property value="#type1.id"/>"><s:property value="#type1.name"/></option>
										</s:else>
									</s:iterator>
							</select>
						</td>
						<td class="t_r" style="white-space: nowrap;">中类</td>
						<td><!--
							<select name="type2" id="type2" onchange="clearOPtions(1);chooseType(3);setMaxCountEmpty();" class="input_large">
							-->
							<select name="type2" id="type2" onchange="clearOPtions(1);chooseType(3);" class="input_large">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.type2List" id="type2">
									<s:if test="#request.assetInfo.type2==#type2.id">
										<option value="<s:property value="#type2.id"/>" selected="selected"><s:property value="#type2.name"/></option>	
									</s:if>
									<s:else>
										<option value="<s:property value="#type2.id"/>"><s:property value="#type2.name"/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<td class="t_r" style="white-space: nowrap;">小类</td>
						<td><!--
							<select name="type3" id="type3" class="input_large" onchange="setMaxCountEmpty();">
							-->
							<select name="type3" id="type3" class="input_large">
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.type3List" id="type3">
									<s:if test="#request.assetInfo.type3==#type3.id">
										<option value="<s:property value="#type3.id"/>" selected="selected"><s:property value="#type3.name"/></option>	
									</s:if>
									<s:else>
										<option value="<s:property value="#type3.id"/>"><s:property value="#type3.name"/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
					</tr>
					<tr>
						<td class="t_r" style="white-space: nowrap;">所属线路</td>
						<td><!--
							<select name="routeNum" id="routeNum" class="input_large" onchange="clearStations();showStations(this);setMaxCountEmpty();">
							-->
							<select name="routeNum" id="routeNum" class="input_large" onchange="clearStations();showStations(this);">
								<option value='-1'>---请选择---</option>
								<s:iterator value="#request.lineList" id="list">
									<s:if test="#request.assetInfo.routeNum==#list.id">
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>" selected="selected"><s:property value='#list.name'/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>"><s:property value='#list.name'/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<td class="t_r" style="white-space: nowrap;">所属车站</td>
						<td><!--
							<select name="stationNum" id="stationNum" class="input_large" onchange="setMaxCountEmpty();">
							-->
							<select name="stationNum" id="stationNum" class="input_large" >
								<option value='-1'>---请选择---</option>
								<s:iterator value="#request.stationList" id="list">
									<s:if test="#request.assetInfo.stationNum==#list.id">
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>" selected="selected"><s:property value='#list.name'/></option>
									</s:if>
									<s:else>
										<option value="<s:property value="#list.id"/>" id="<s:property value="#list.code"/>"><s:property value='#list.name'/></option>
									</s:else>
								</s:iterator>
							</select>
						</td>
						<td class="t_r" style="white-space: nowrap;">权属单位</td>
						<td><!--
							<select name="ownerDuty" id="ownerDuty" onchange="setMaxCountEmpty();">
							-->
							<select name="ownerDuty" id="ownerDuty" >
								<option value="-1">---请选择---</option>
								<s:iterator value="#request.unitList" id="unit">
									<s:if test="#request.assetInfo.ownerDuty==#unit.id">
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
						<td class="t_r" style="white-space: nowrap;">资产编号</td>
						<td>
							<input class="input_large" name="assetId"  id="assetId" value="<s:property value="#request.assetInfo.assetId"/>"/>
						</td>
						<td class="t_r" style="white-space: nowrap;">添加时间</td>
						<td>
							<input class="input_large" name="operateTime" id="operateTime" value="<s:property value="#request.assetInfo.operateTime"/>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td></td>
						<td colspan="4" class="t_c">
							<input type="submit" value="查 询" onclick="setPageNo(0)"/>&nbsp;&nbsp; 
							<input type="button" value="重 置" onclick="clearSearch();"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			<div class="fn">
				<input type="button"" name="button3" id="button3" value="导出Excel" class="new" onclick="exportExcel();" style="float: right;">
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="14">资产列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td class="t_c">序号</td>
					<td class="t_c">资产编号</td>
					<td class="t_c">资产名称</td>
					<td class="t_c">大类</td>
					<td class="t_c">中类</td>
					<td class="t_c">小类</td>
					<td class="t_c">线路</td>
					<td class="t_c">车站</td>
					<td class="t_c">添加时间</td>
					<td class="t_c">是否入册</td>
					<td class="t_c" colspan="3">操作</td>
				</tr>
				
				<s:iterator value="#request.page.result" id="assetInfo" status="st">
					<tr>
						<td class="t_c"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_c"><s:property value="#assetInfo.assetId"/></td>
						<td class="t_c"><s:property value="#assetInfo.assetName"/></td>
						<td class="t_c"><s:property value="#request.type1NameList[#st.index]"/></td>
						<td class="t_c"><s:property value="#request.type2NameList[#st.index]"/></td>
						<td class="t_c"><s:property value="#request.type3NameList[#st.index]"/></td>
						<td class="t_c"><s:property value="#request.lineNameList[#st.index]"/></td>
						<td class="t_c"><s:property value="#request.stationNameList[#st.index]"/></td>
						<td class="t_c"><s:property value="#assetInfo.operateTime.substring(0,10)"/></td>
						<td class="t_c">
							<s:if test="#assetInfo.registry==1">
								已入册
							</s:if>
							<s:else>
								待审核
							</s:else>
						</td>
						<td class="t_c">
							<a href="showAssetInfoHistory.action?paramId=<s:property value="#assetInfo.assetId"/>" style="float:inline;" target="_blank">查看</a>
						</td>
						<td class="t_c">
							<a href="javascript:void(0);" style="float:inline;" onclick="showEditPage('<s:property value="#assetInfo.assetId"/>')">编辑</a>
						</td>
					</tr>
				</s:iterator>
				
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="14"><div class="clearfix">
			      <s:if test="#request.page.totalSize==0"><span>无入册数据</span></s:if>
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
