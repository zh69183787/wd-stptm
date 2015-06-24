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
        <ul id="supplier">
			<li>我的通知</li>
			<li>开发人：汤川</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/notice/showList.action" target="_blank">[我的通知]</a>&nbsp;&nbsp;<%=basePath%>/notice/showList.action
			</li>
		</ul>
        
         <ul id="supplier">
			<li>超时提醒</li>
			<li>开发人：汤川</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/overtime/showReceiveList.action" target="_blank">[超时提醒]</a>&nbsp;&nbsp;<%=basePath%>/overtime/showReceiveList
			</li>
		</ul>
        
         <ul id="supplier">
			<li>我的催办</li>
			<li>开发人：汤川</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/myurge/showMyUrge.action" target="_blank">[我的催办]</a>&nbsp;&nbsp;<%=basePath%>/myurge/showMyUrge.action
			</li>
		</ul>
		
		<ul id="supplier">
			<li>集团通讯录</li>
			<li>开发人：刘纪东</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/address/showGroupList.action" target="_blank">[集团通讯录]</a>&nbsp;&nbsp;<%=basePath%>/address/showGroupList.action
			</li>
		</ul>
		
		<ul id="supplier">
			<li>个人通讯录</li>
			<li>开发人：刘恒</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/address/showPersonAddressList.action" target="_blank">[个人通讯录]</a>&nbsp;&nbsp;<%=basePath%>/address/showPersonAddressList.action
			</li>
		</ul>
	
         <ul id="supplier">
			<li>节假日管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/holConfig/showList.action" target="_blank">[节假日管理]</a>&nbsp;&nbsp;<%=basePath%>/holConfig/showList.action
			</li>
		</ul>
		 
		  <ul id="supplier">
			<li>公休管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/holHolidays/showList.action" target="_blank">[公休管理]</a>&nbsp;&nbsp;<%=basePath%>/holHolidays/showList.action
			</li>
		</ul>
		
		 <ul id="supplier">
			<li>公休申请</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/holHolidaysApply/showHolHolidaysApplyAdd.action" target="_blank">[公休申请]</a>&nbsp;&nbsp;<%=basePath%>/holHolidaysApply/showHolHolidaysApplyAdd.action
			</li>
		</ul>
		 
	</div>
</body>
</html>