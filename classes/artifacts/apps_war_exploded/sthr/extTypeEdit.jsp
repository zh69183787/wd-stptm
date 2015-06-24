<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.UserInfo" %>
<%@ page import="com.wonders.stpt.CrossIpLogin" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%
Properties properties = new Properties();
String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
properties.load(new FileInputStream(path));
String filterButton=properties.getProperty("filterButton");
String loginName = "";
if("on".equals(filterButton)){
	CrossIpLogin crossIpLogin = new CrossIpLogin();
	UserInfo userInfo = new UserInfo();
	crossIpLogin.setUserInfo(request,userInfo);
	loginName = userInfo.getLoginName();
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>人事扩展信息类别编辑</title>
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
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		$(document).ready(function(){
			var status = $("#typeStatus").val();
			if(status!=null&&status!=""){
				alert(status);
				$("#typeName").focus();				
			}
			status = $("#orderStatus").val();
			if(status!=null&&status!=""){
				alert(status);
				$("#sortingOrder").focus();				
			}
		});
		
		function checkType(){
			if($("#typeName").val()==""){
				alert("请输入类别名称");
				$("#typeName").focus();
				return false;
			}
			$("#typeName").val($("#typeName").val().replace(/(^\s*)|(\s*$)/g,''));
			return true;
		}	
		
		function checkSortingOrder(){
			if($("#sortingOrder").val()==""){
				alert("请输入排列顺序");
				$("#sortingOrder").focus();
				return false;
			}
			
			$("#sortingOrder").val($("#sortingOrder").val().replace(/(^\s*)|(\s*$)/g,''));
			if(!$("#sortingOrder").val().match(/^[0-9]*$/)){
				alert("请输入正整数");
				$("#sortingOrder").focus();
				return false;
			}
			return true;
		}
		
		function checkForm(){
			if(checkType() && checkSortingOrder()){
				return true;
			}else{
				return false;
			}
		}
		
		function clearData(){
			$("#sortingOrder").val("");
			$("#typeName").val("");
		}
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
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
	<li><a href="#">工会人事数据库</a></li>
	<li><a href="#">扩展信息类别</a></li>
	<li class="fin">编辑</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li class="selected"><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<!--Ctrl End--> <!--Tabs_2-->
<!-- 
<div class="tabs_2">
<ul>
	<li class="selected"><a href="#"><span>公会人事数据库</span></a></li>
	
	<li><a href="#"><span>待定名称</span></a></li>
	<li><a href="#"><span>待定名称</span></a></li>
	<li><a href="#"><span>待定名称</span></a></li>
	 
</ul>
</div>
-->
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">
<input type="hidden" value="<s:property value='#request.typeStatus'/>" id="typeStatus" />
<input type="hidden" value="<s:property value='#request.orderStatus'/>" id="orderStatus" />

<s:form action="editHrEt" name="HrEt" namespace="/sthr" id="form">
<input type="hidden" name="updatePerson" value="<%=loginName %>"/>
<input type="hidden" name="hretId" value="<s:property value='#request.hret.hretId'/>"/>
<input type="hidden" name="updatePerson" value="<s:property value='#request.hret.updatePerson'/>"/>
<input type="hidden" name="removed" value="<s:property value='#request.hret.removed'/>"/>

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="submit"" value="确 认" " onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut();"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td></td>
			<td></td>
			<td class="t_r lableTd">类别名称</td>
			<td><input type="text" id="typeName" name="typeName" class="input_xlarge" value="<s:property value='#request.hret.typeName'/>" maxlength="50"/></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td class="t_r lableTd">排列顺序</td>
			<td><input type="text" id="sortingOrder" name="sortingOrder" class="input_large" value="<s:property value='#request.hret.sortingOrder'/>" maxlength="3"/></td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="submit" value="确 认" id="submit" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut();"/>
		</td>
	</tr>
</table>
</s:form>
<table width="100%" class="table_1 odd">
	<tbody>
	
	</tbody>
	<!-- 
	<tr class="tfoot">
		<td colspan="5" class="t_r">&nbsp;</td>
	</tr>
	 -->
</table>
</div>
<!--Table End--></div>
</body>
</html>
