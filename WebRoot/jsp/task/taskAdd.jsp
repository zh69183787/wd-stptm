<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String, String> cookieMap = new HashMap<String, String>();
Cookie[] cookies = request.getCookies();
String token=null;
if(cookies !=null){
	for(int i=0;i<cookies.length;i++){
		Cookie cookie = cookies[i];
		cookieMap.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
		if("token".equals(cookie.getName())){
			try{
				token = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
String appName="stptm";
String secret="124a77748fcb48a7a0863f30970a2a04";
if(token==null){
	token=appName+secret;
}
%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资产盘点任务新增</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<link rel="stylesheet" href="../css/jquery.autocomplete.css" type="text/css"/>
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

<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	
	$("td[id=must]").each(function(){
		$(this).attr("style","color:red");
	});
	
	$("input[name=starttime]").datepicker({
		inline: true,
		changeYear:true,
		minDate:new Date(),
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("input[name=endtime]").datepicker("option","minDate",selectedDate);
		}
	});	
	$("input[name=endtime]").datepicker({
		inline: true,
		changeYear:true,
		minDate:new Date(),
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect:function(selectedDate){
			$("input[name=starttime]").datepicker("option","maxDate",selectedDate);
		}
	});	
	
});
</script>



<script type="text/javascript">
$(function(){
	$("input[name=name]").parent().text();
});



//表单检验
function checkForm(){

	var value ;
	//任务名称
	value = $("input[name=taskname]").val();
	if(isValueEmpty(value)){
		alert("请输入任务名称!");
		$("input[name=taskname]").focus();
		return false;
	}

	value = $("#checkpersonlist").val();
	if(isValueEmpty(value)){
		alert("请输入指定盘点人！");
		$("#checkpersonlist").select().focus();
		return false;
	}	
	value = $("#hideCheckpersonlist").val();
	if(isValueEmpty(value)){
		alert("该用户不存在，请重新输入！");
		$("#checkpersonlist").select().focus();
		return false;	
	}
	
	
	//任务开始时间
	value = $("input[name=starttime]").val();
	if(isValueEmpty(value)){
		alert("请选择任务开始时间!");
		$("input[name=starttime]").focus();
		return false;
	} 
	
	//任务结束时间
	value = $("input[name=endtime]").val();
	if(isValueEmpty(value)){
		alert("请选择任务结束时间!");
		$("input[name=endtime]").focus();
		return false;
	}
	
	return true;
}


//检查数据是否为空
function isValueEmpty(value){
	if(value==null || value=="" || value=="undifined"){
		return true;	//空	
	}
	return false;	//非空
}

//正整数校验
function isValueNumber(value){
	var reg = /^\d*$/;
	return reg.test(value);
}

//价格校验
function isValuePrice(value){
	var reg = /^\d*\.?\d+$/;
	return reg.test(value);
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
   	$("#showAdd").submit();
} 

//当指定盘点人的值改变的时候，设置指定盘点的id为空
function setCheckpersonList(){
	$("#hideCheckpersonList").val("");
}

</script>
<style>
	.ui-autocomplete {
		max-height: 150px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
	}
	/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
	* html .ui-autocomplete {
		height: 150px;
	}
	</style>
	
<!--自动提示功能  -->
<script>
$(function() {
	var saveStatus = false;
	$("#checkpersonlist" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><userName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</userName></dataParams>";
			$.ajax({
				url: "findDeptUserName.action",
				dataType: "json",
				data: {
					"token" : "<%=token%>",	
					"method" : "getMatchedDeptUsers",				
					"appName" : "<%=appName%>",	
					"secret" : "<%=secret%>",	
					"dataType" : "json",	
					"dataParams" : dataParams,									
					"random" : Math.random()
				},
				success: function( data ) {
					//alert(data.code);
					response( $.map( data.params, function( item,index ) {
						return {
							label: item.userName+"("+item.deptName+")",
							value: item.userId
						}
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			saveStatus = false;
			$("#hideCheckpersonlist").val("");
		},
		select: function( event, ui ) {
			$("#hideCheckpersonlist").val(ui.item.value);
			$("#checkpersonlist").val(ui.item.label.split("(")[0]);
			saveStatus = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				$( "#hideCheckpersonlist" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#hideCheckpersonList").val("");
			}
		}
	});
	
});

//清除搜索条件
function clearSearch(){
	$("input[name=taskname]").val("");
	$("input[name=userName]").val("");
	$("input[name=starttime]").val("");
	$("input[name=endtime]").val("");
}
</script>




</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">资产盘点任务新增</li>
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
<s:form action="saveAdd" name="task" namespace="/task" method="post">
<input type="hidden" name="taskmemoFilter" value="<s:property value="#request.filterCondition"/>">
<input type="hidden" name="taskmemo" value="<s:property value="#request.searchCondition"/>">

<!--<input type="hidden" name="checkpersonlist" value="<s:property value="#request.userId"/>">-->
<input type="hidden" name="taskuser" value="<s:property value="#request.userId"/>">

<input type="hidden" name="appName" value="<%=appName%>" />
<input type="hidden" name="token" value="<%=token%>" />
<input type="hidden" name="method" value="getMatchedDeptUsers" />
<input type="hidden" name="secret" value="<%=secret%>" />
<input type="hidden" name="dataType" value="xml" />
<input type="hidden" name="dataParams" value="<%=token%>" />

	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="submit"" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重置" onclick="clearSearch();"/> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd">任务名称</td>
				<td id="must" colspan="3">
					<input type="text" colspan="3" name="taskname" class="input_xxlarge" />&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">指定盘点人</td>
				<td id="must" colspan="3">
					<input type="hidden" id="hideCheckpersonlist" name="checkpersonlist"/>
					<input type="text"  id="checkpersonlist" name="userName" class="input_xxlarge" />&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td width="10%;" class="t_r lableTd">任务开始时间</td>
				<td width="40%;"  colspan="3" id="must"><input type="text" name="starttime" class="input_small date" readonly="readonly"/>&nbsp;&nbsp;*</td>
			</tr>
			<tr>
				<td width="10%;" width="10%;"class="t_r lableTd">任务结束时间</td>
				<td width="40%;" colspan="3" id="must" colspan="1"><input type="text" name="endtime" class="input_small date" readonly="readonly"/>&nbsp;&nbsp;*</td>
			</tr>
			<tr>
				<td width="10%;" class="t_r lableTd">任务范围</td>
				<td width="40%;" colspan="3">
					<textarea style="width: 480px;" rows="5" cols="6" readonly="readonly" disabled="disabled" name=""><s:property value="#request.searchCondition"/></textarea>
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="4" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="重置" onclick="clearSearch();" />&nbsp;
			</td>
		</tr>
	</table>
</s:form>

	<s:form action="showAdd.action" name="task" namespace="/task" id="showAdd">
		<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
		<input type="hidden" name="searchCondition" value="<s:property value="#request.searchCondition"/>" />
		<input type="hidden" name="filterCondition" value="<s:property value="#request.filterCondition"/>" />
	</s:form>
	<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="14">资产信息列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td>序号</td>
					<td>资产编号</td>
					<td>资产名称</td>
					<td>规格</td>
					<td>型号</td>
					<td>原值(元)</td>
					<td>净值(元)</td>
					<td>出厂价(元)</td>
					<td>决算价(元)</td>
					<td>当前设备状态</td>
					<td>所属线路</td>
					<td></td>
				</tr>
				
				<s:iterator value="#request.page.result" id="assetInfo" status="st">
					<tr class="t_c">
						<td><s:property value="#request.page.start+#st.index"/></td>
						<td><s:property value="#assetInfo.assetId"/></td>
						<td><s:property value="#assetInfo.assetName"/></td>
						<td><s:property value="#assetInfo.specification"/></td>
						<td><s:property value="#assetInfo.model"/></td>
						<td><s:property value="#assetInfo.yuanzhi"/></td>
						<td><s:property value="#assetInfo.jingzhi"/></td>
						<td><s:property value="#assetInfo.leaveFactoryPrice"/></td>
						<td><s:property value="#assetInfo.finalAccountPrice"/></td>
						<td>
							<s:iterator value="#request.equipmentStatusList" id="equipmentStatus" >
								<s:if test="#equipmentStatus.id==#assetInfo.equipmentState">
									<s:property value="#equipmentStatus.name"/>									
								</s:if>
							</s:iterator>
						</td>
						<td>
							<s:iterator value="#request.lineList" id="line" >
								<s:if test="#line.id==#assetInfo.routeNum">
									<s:property value="#line.name"/>									
								</s:if>
							</s:iterator>
						</td>
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
		
</div>
<!--Table End--></div>
</body>
</html>