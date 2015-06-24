<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.UserInfo" %>
<%@ page import="com.wonders.stpt.CrossIpLogin" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
Map<String, String> cookieMap = new HashMap<String, String>();
Cookie[] cookies = request.getCookies();
String token=null;
String editperson=null;
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

//取当前登录人
	for (Cookie cookie:cookies) {
		if("userName".equals(cookie.getName())){
			editperson = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			break;
		}
	}
%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>数据修改</title>
<link rel="stylesheet" href="../css/formalize.css" />

<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script src="<%=basePath %>rectificationwork/js/common.js"></script>
		<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
		};
		
		$(function () {
		  var linesArray = new Array();
		  
		  var linesAll = $("#linesAll").val();
		  linesArray = linesAll.split(",");
		  $("[id=lines]").each(
        	  function(index){
        	    for(var i=0;i<linesArray.length;i++){
        	      if($(this).val()==linesArray[i]){
        	        	$(this).attr("checked","checked");
        	        	break;
        	      }
        	    }
        	  }
        	);
        	
          var h_levels = $("#h_levels");
          var levels=$("#levels");		  
		  for(var i=1;i<levels.children("option").length;i++){
		    if(levels.children("option:eq("+i+")").val()==h_levels.val())
		    levels.children("option:eq("+i+")").attr("selected",true);
		  }	
		  
		  var h_workState = $("#h_workState");
          var workState=$("#workState");		  
		  for(var i=1;i<workState.children("option").length;i++){
		    if(workState.children("option:eq("+i+")").val()==h_workState.val())
		    workState.children("option:eq("+i+")").attr("selected",true);
		  }
		  var h_leadDept = $("#h_leadDept");
          var leadDept=$("#leadDept");		  
		  for(var i=1;i<leadDept.children("option").length;i++){
		    if(leadDept.children("option:eq("+i+")").val()==h_leadDept.val())
		    leadDept.children("option:eq("+i+")").attr("selected",true);
		  }
		  //$("#dangersState").val($("#dangersState").val().replaceAll("<br>","\n"));
		 // $("#correctMethod").val($("#correctMethod").val().replaceAll("<br>","\n"));
		  $("#remark").val($("#remark").val().replaceAll("<br>","\n"));
		 
		  
		  var workDept_hidden = $("#workDept_hidden").val();
		  $("[id=workDept_checkbox]").each(function(){
		  	if(workDept_hidden.indexOf($(this).val().split("#")[0])>-1){
		  		$(this).attr("checked",true);
		  	}
		  });
		  
		  
		});
		
		
		
		//自动提示模糊搜索，责任单位
		$(function(){
		var saveStatus = false;
		$("#workDept_add" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><deptName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</deptName><typeId>0</typeId></dataParams>";
			$.ajax({
				url: "findOwnerWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"token" : "<%=token%>",
					"method" : "getMatchedDepts",				
					"dataType" : "json",
					"dataParams":dataParams
				},
				success: function( data ) {
					response( $.map( data.params, function( item,index ) {
						return {
							label: item.description,
							value: item.id
						};
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			$("#workDeptId_add").val("");
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#workDeptId_add").val(ui.item.value);
			$("#workDept_add").val(ui.item.label);
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
				$( "#workDeptId_add" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#workDeptId_add").val("");
			}
		}
		});
		});

		//自动提示模糊搜索，督办部门
		$(function(){
		var saveStatus = false;
		$("input[name=supervisionDept]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><deptName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</deptName><typeId>0</typeId></dataParams>";
			$.ajax({
				url: "findOwnerWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"token" : "<%=token%>",
					"method" : "getMatchedDepts",				
					"dataType" : "json",
					"dataParams":dataParams
				},
				success: function( data ) {
					response( $.map( data.params, function( item,index ) {
						return {
							label: item.description,
							value: item.id
						};
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			$( "#supervisionDeptId" ).val("");
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#supervisionDeptId").val(ui.item.value);
			$("#supervisionDept").val(ui.item.label);
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
				$( "#supervisionDeptId" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#supervisionDeptId").val("");
			}
		}
		});
		});
		
		
        </script></head>

<body>
<input type="hidden" id="h_dangersClass" name="" value="<s:property value='hiddenDangersCorrectVO.dangersClass'/>">
<input type="hidden" id="h_workState" name="h_workState" value="<s:property value='avo.workState'/>">
<input type="hidden" id="h_leadDept" name="h_leadDept" value="<s:property  value='avo.leadDept'/>" />

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营安全评价风险点整改工作</a></li>  
                	<li class="fin">数据修改</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        <s:form action="updateRectificationWorkById" name="RectificationWork" namespace="/RectificationWork">
        <input type="hidden" name="id" value="<s:property value='avo.id'/>">
        <input type="hidden" id="linesAll" name="lines" value="<s:property value='avo.lines'/>">
        <input type="hidden" name="editPerson" value="<%=editperson %>"/>
        <input type="hidden" id="h_levels" name="h_levels" value="<s:property value='avo.levels'/>"/>
        <input type="hidden" id="gonghao" name="gonghao" value="<s:property value='avo.gonghao'/>"/>
        <input type="hidden" id="dept_id" name="dept_id" value="<s:property value='avo.dept_id'/>"/>
        
       	  <table style="width:100%"  class="table_1">
                              <thead>
            <th  class="t_r" colspan="4">
            <input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	     
       	      </th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">线路/处所</td>
                                <td colspan="3">
                                <input type="checkbox" id="lines" name="line" value='全路网' onclick="clickAll(this);">全路网
							    <input type="checkbox" id="lines" name="line" value='1号线'>1号线
							    <input type="checkbox" id="lines" name="line" value='2号线'>2号线
							    <input type="checkbox" id="lines" name="line" value='3号线'>3号线
							    <input type="checkbox" id="lines" name="line" value='4号线'>4号线
							    <input type="checkbox" id="lines" name="line" value='5号线'>5号线
							    <input type="checkbox" id="lines" name="line" value='6号线'>6号线
							    <input type="checkbox" id="lines" name="line" value='7号线'>7号线
							    <input type="checkbox" id="lines" name="line" value='8号线'>8号线
							    <input type="checkbox" id="lines" name="line" value='9号线'>9号线
							    <input type="checkbox" id="lines" name="line" value='10号线'>10号线
							    <input type="checkbox" id="lines" name="line" value='11号线'>11号线
							    <input type="checkbox" id="lines" name="line" value='12号线'>12号线
							    <input type="checkbox" id="lines" name="line" value='13号线'>13号线
							    <input type="checkbox" id="lines" name="line"  value='14号线'>14号线
							    <input type="checkbox" id="lines" name="line"  value='15号线'>15号线
							    <input type="checkbox" id="lines" name="line"  value='16号线'>16号线
							    <input type="checkbox" id="lines" name="line"  value='17号线'>17号线
							    <input type="checkbox" id="lines" name="line"  value='18号线'>18号线
							    <input type="checkbox" id="lines" name="line"  value='19号线'>19号线
							    <input type="checkbox" id="lines" name="line"  value='20号线'>20号线
                                &nbsp;&nbsp;<span style="color:red;display:inline">*</span></td>
                           </tr>
                            
                           
                           		<tr>
                           			<td class="t_r lableTd" style="width:15%;">风险编号</td>
	                                <td style="width:35%;"><input type="text" id="dangerNumber" value="<s:property value='avo.dangerNumber' />" name="dangerNumber" maxlength="20" class="input_large"/>
	                                </td>
	                                <td style="width:15%;" class="t_r lableTd">子系统</td>
	                                <td style="width:35%;"><input type="text" id="childSYS" name="childSYS" value="<s:property value='avo.childSYS' />" class="input_large"/>
	                                </td>
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">风险点</td>
	                                <td><input type="text" id="riskPoint" value="<s:property value='avo.riskPoint' />" name="riskPoint" maxlength="20" class="input_large"/>
	                                </td>
	                                <td class="t_r lableTd">等级</td>
	                                <td>
	                                <select id="levels"name="levels"  class="input_large">
                                	<option value="">---请选择---</option>
                                	<option value="严重的">严重的</option>
                                	<option value="需重视的">需重视的</option>
                                	</select>
                               	 	</td>
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">责任单位/部门</td>
	                                <td><input type="text" id="deptWork" value="<s:property value='avo.deptWork'/> " name="deptWork" maxlength="20" class="input_large"/>
	                                </td>
	                                <td class="t_r lableTd">分管领导</td>
	                                <td><input type="text" id="leaderShip" value="<s:property value='avo.leaderShip'/>" name="leaderShip" class="input_large"/>
	                                </td>
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">消项情况</td>
	                                <td>
	                                <select id="workState" name="workState" class="input_large">
                                	<option value="">---请选择---</option>
                                	<option value="已完成">已完成</option>
                                	<option value="整改中">整改中</option>
                                	</select>
	                                </td>
	                                <td class="t_r lableTd">牵头部门</td>
	                                <td>
	                                <select id="leadDept" name="leadDept" class="input_xlarge">
                                	<option value="">---请选择---</option>
                                	<option value="上海地铁第一运营有限公司">上海地铁第一运营有限公司</option>
                                	<option value="上海地铁第二运营有限公司">上海地铁第二运营有限公司</option>
                                	<option value="上海地铁第三运营有限公司">上海地铁第三运营有限公司</option>
                                	<option value="上海地铁第四运营有限公司">上海地铁第四运营有限公司</option>
                                	<option value="上海轨道交通运营管理中心">上海轨道交通运营管理中心</option>
                                	<option value="磁浮公司">磁浮公司</option>
                                	<option value="维保公司">维保公司</option>
                                	</select></td>
                           		</tr>
                           		
                           		<tr>
                           			<td class="t_r lableTd">填报人</td>
	                                <td>
	                                	<input type="text" id="userName" readonly="readonly" name="userName" value="<s:property value='avo.userName'/>" maxlength="20" class="input_large"/>
	                                </td>
	                                <td class="t_r lableTd">填报部门</td>
	                                <td>
	                                	<input type="text" id="deptName" readonly="readonly" name="deptName" value="<s:property value='avo.deptName'/>" maxlength="20" class="input_large"/>
                                	</td>
                                	
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">填报日期</td>
	                                <td colspan="3">
	                                	<input type="text" id="inputDate" name="inputDate" readonly="readonly" maxlength="20" class="input_large" value="<s:property value='avo.inputDate'/>"/>
	                                </td>
                           		</tr>
                              <tr>
                                <td class="t_r lableTd">专家建议</td>
                                <td colspan="3"><textarea id="expertAdvice" name="expertAdvice" rows="5" cols="10" style="overflow:auto;  border-width:1;text-align: left;"><s:property value="avo.expertAdvice"/></textarea>&nbsp;&nbsp;<span style="color:red;display:inline">*</span>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">现象说明</td>
                                <td colspan="3"><textarea id="phenomenon" name="phenomenon" cols="60" rows="5" style="overflow:auto;  border-width:1;text-align: left;" maxlength="600"><s:property value="avo.phenomenon"/></textarea>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">整改措施</td>
                                <td colspan="3">
                                <textarea id="rectificationMethod" name="rectificationMethod" cols="60" rows="5" style="overflow:auto;  border-width:1;text-align: left;" maxlength="601"><s:property value="avo.rectificationMethod"/></textarea>
                                </td>
                              </tr>
                               <tr>
                                <td class="t_r lableTd">节点目标</td>
                                <td colspan="3">
                                <textarea id="targetNode" name="targetNode" cols="60" rows="5" style="overflow:auto;  border-width:1;text-align: left;" maxlength="600"><s:property value="avo.targetNode" /></textarea>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">进展情况</td>
                                <td colspan="3">
                                <textarea id="cwip" name="cwip" cols="60" rows="5" style="overflow:auto;  border-width:1;text-align: left;" maxlength="600"><s:property value="avo.cwip" /></textarea>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注</td>
                                <td colspan="3">
                                <textarea id="remark" name="remark" cols="60" rows="5" style="overflow:auto;  border-width:1;text-align: left;" maxlength="600"><s:property value="avo.remark" /></textarea>
                                </td>
                              </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>

</td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
