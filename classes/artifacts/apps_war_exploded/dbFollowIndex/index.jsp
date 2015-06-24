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
		<ul id="dbFollow">
			<li>督办跟踪</li>
			<li>开发人：孙佳伟</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/dbBusiness/findDbBusinessByPage.action" target="_blank">[督办业务列表]</a>&nbsp;<%=basePath%>/dbBusiness/findDbBusinessByPage.action
			</li>
			<li>&nbsp;</li>
			<li>
				<a href="<%=basePath%>/dbBusiness/findDbBusinessByPage.action?deptCount=yes" target="_blank">[督办统计]</a>&nbsp;<%=basePath%>/dbBusiness/findDbBusinessByPage.action?deptCount=yes
			</li>
			<li>&nbsp;</li>
			<li>
				<a href="<%=basePath%>/dbFollow/findDbFollowByPage.action" target="_blank">[督办跟踪列表]</a>&nbsp;<%=basePath%>/dbFollow/findDbFollowByPage.action
			</li>
			<li>&nbsp;</li>
			<li>
				<a href="<%=basePath%>/dbFollow/toFollowAdd.action?dbId=8a81ac9035d4939c0135f695a7fc1093&followType=2" target="_blank">[新增督办跟踪]</a>&nbsp;<%=basePath%>/dbFollow/toFollowAdd.action?dbId=8a81ac9035d4939c0135f695a7fc1093&followType=2
				<br>链接中dbId是督办流程表的主键，下方提供了几条督办流程的主键，以供测试。
				<br>链接中followType是督办跟踪的类型，“1”是简单跟踪，“2”是按计划跟踪
			</li>
			<li>&nbsp;</li>
			<li>
				<a href="http://10.1.48.19:8088/oaFlowBegin.jsp?returnUrl=http://10.1.48.19:8088/asset/getOldDept.jsp?type=3" target="_blank">[部门督办跟踪列表]</a>&nbsp;http://10.1.48.19:8088/oaFlowBegin.jsp?returnUrl=http://10.1.48.19:8088/asset/getOldDept.jsp?type=3
				<br>链接中oldDeptId是老平台部门的id，下方的图片可供参考。
				<br>另外因为部门是取的老平台的部门，所以和当前新平台的登录人是什么部门的并没有关系，所以全程可用李名敏（登录名：G00100000161，密码：888888）进行测试。
				<br>提示可能存在一个登录上问题：因cookie过期而跳转到登陆页面重新进行登录后，再自行跳转的原地址可能存在错误（有缺失）而未能正确打开链接，请重新打开链接。（正式环境没有此问题）
			</li>
			<li>&nbsp;</li>
			<li><b>督办流程：</b></li>
			<li>3月12日运营生产周例会上对近期路网渗漏水情况的整改要求：8a81ac9035d4939c013605cbbea4128a</li>
			<li>俞光耀总裁关于12月31日6号线民生路控制中心UPS失电事故的批示：8a81ac9334b4673a0134bfffc0280034</li>
			<li>0518关于能耗监测管理系统遗留问题协调会相关内容的督办：8a81ac9037611d9001376e873c39024e</li>
			<li>落实2012“夏令热线”活动，提升今夏轨交服务质量：8a81ac90386af0b7013888af089e0958</li>
			<li>俞光耀总裁关于总结完善领导干部夜间带班深入现场制度的批示：8a81ac933a437333013a689da3521576</li>
			<li>&nbsp;</li>
			<li><img src="image/dept.png"></li>
		</ul>
		 
	</div>
</body>
</html>