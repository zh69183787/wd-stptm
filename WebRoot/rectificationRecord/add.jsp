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
String inputdept=null;
String inputperson=null;
String inputdeptId = null;
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
	//取填报部门
	for (Cookie cookie:cookies) {
		if("deptName".equals(cookie.getName())){
			inputdept = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			break;
		}
	}
	//取填报人
	for (Cookie cookie:cookies) {
		if("userName".equals(cookie.getName())){
			inputperson = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			break;
		}
	}
	
	//取填报部门id
	for (Cookie cookie:cookies) {
		if("deptId".equals(cookie.getName())){
			inputdeptId = cookie.getValue();
			break;
		}
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
<title>数据录入</title>
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
		<script src="js/common.js"></script>
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
						}
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
				$("#workDeptId_add").val("");
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
						}
					}));
				}
			});
		},
		minLength: 1,
		search: function() {
			$("#supervisionDeptId").val("");
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
		
		
		
		function cancel(){
		
			$("#supervisionDeptId_hidden").val("");
			$("#supervisionDept_hidden").val("");
			$("#workDept_hidden").val("");
			$("#workDeptId_hidden").val("");
			//$("#workDept").parent().find("p").remove();
		}
		
		
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营安全隐患问题整改推进表</a></li>  
                	<li class="fin">数据录入</li>
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
        <s:form action="add" name="rectificationRecord" namespace="/rectificationRecord">
        <input type="hidden" id="wid" name="wid" value='<s:property value='#request.wid'/>'>
        	
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;<input type="reset" value="重 置" onclick="cancel();"/>&nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd" style="width:10%;">整改措施</td>
                                <td colspan="3" style="width:89%;">
                                <textarea id="rectificationMethod" name="rectificationMethod" cols="60" rows="5" style="overflow:auto;  border-width:1" maxlength="600"></textarea>
                                </td>
                              </tr>
                               <tr>
                                <td class="t_r lableTd">节点目标</td>
                                <td colspan="3">
                                <textarea id="targetNode" name="targetNode" cols="60" rows="5" style="overflow:auto;  border-width:1" maxlength="600"></textarea>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">进展情况</td>
                                <td colspan="3">
                                <textarea id="cwip" name="cwip" cols="60" rows="5" style="overflow:auto;  border-width:1" maxlength="600"></textarea>
                                </td>
                              </tr>
                              </tbody>
                              <tr class="tfoot">
                              <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;<input type="reset" value="重 置" onclick="cancel();"/>&nbsp;
<input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
