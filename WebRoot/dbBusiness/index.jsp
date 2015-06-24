<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="cw" uri="/widget-tags"%>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>app.shmetro.com</title>
<cw:base></cw:base>

<%
String basePath = request.getContextPath();
 %>
</head>
<body>
	<div>				
		<ul id="dbBusiness">
			<li>督办业务</li>
			<li>开发人：孟杰</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/dbBusiness/findDbBusinessByPage.action" target="_blank">[督办业务列表]</a>&nbsp;<%=basePath%>/dbBusiness/findDbBusinessByPage.action
			</li>
		</ul>
		 
	</div>
</body>
</html>