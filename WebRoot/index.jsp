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
		<ul id="accidentCase">
			<li>运营事件案例库</li>
			<li>开发人：孙佳伟</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/accidentCase/findMetroAccidentCaseByPage.action" target="_blank">[管理]</a>&nbsp;<%=basePath%>/accidentCase/findMetroAccidentCaseByPage.action
			</li>
			<li>
				<a href="<%=basePath%>/accidentCase/findMetroAccidentCaseByPage2.action" target="_blank">[查看]</a>&nbsp;<%=basePath%>/accidentCase/findMetroAccidentCaseByPage2.action
			</li>
			<li>
				<a href="<%=basePath%>/accidentCase/findMetroAccidentCaseByUpdatePerson.action" target="_blank">[录入]</a>&nbsp;<%=basePath%>/accidentCase/findMetroAccidentCaseByUpdatePerson.action
			</li>
			<li>&nbsp;</li>
		</ul>
		<ul id="sthr">
			<li>公会人事</li>
			<li>开发人：孙佳伟</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/sthr/findHrBInfoByPage.action" target="_blank">[基础信息管理]</a>&nbsp;<%=basePath%>/sthr/findHrBInfoByPage.action
			</li>
			<li>
				<a href="<%=basePath%>/sthr/findHrEtByPage.action" target="_blank">[扩展字段管理]</a>&nbsp;<%=basePath%>/sthr/findHrEtByPage.action
			</li>
			<li>&nbsp;</li>
		</ul>
		<ul id="constructionNotice">
			<li>施工组织公告</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/construction/findTConstructionNotice.action" target="_blank">[管理]</a>&nbsp;&nbsp;<%=basePath%>/construction/findTConstructionNotice.action
			</li>
			<li>
				<a href="<%=basePath%>/construction/findTConstructionNoticeOnly.action" target="_blank">[查看]</a>&nbsp;&nbsp;<%=basePath%>/construction/findTConstructionNoticeOnly.action
			</li>
			<li>&nbsp;</li>
		</ul>
		<ul id="evaluation">
			<li>办公行文评价</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/evaluate/showEvaluationItem.action" target="_blank">[定量评价管理]</a>&nbsp;&nbsp;<%=basePath%>/evaluate/showEvaluationItem.action
			</li>
			<li>
				<a href="<%=basePath%>/evaluate/showFlowEvaluations.action" target="_blank">[评价管理]</a>&nbsp;&nbsp;<%=basePath%>/evaluate/showFlowEvaluations.action
			</li>
			<li>&nbsp;</li>
		</ul>
		
		
		
		
		
		
		
		<ul id="assrt">
			<li>资产清册维护</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/asset/showInventory.action" target="_blank">[资产清册维护]</a>&nbsp;&nbsp;<%=basePath%>/asset/showInventory.action
			</li>
			<li><a href="<%=basePath%>/asset/getAssetInventoryList.action" target="_blank">[资产清册]</a>&nbsp;&nbsp;<%=basePath%>/asset/getAssetInventoryList.action</li>
		</ul>
		<ul id="assrt">
			<li>资产动态管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/asset/showDynamicManagement.action" target="_blank">[资产动态管理]</a>&nbsp;&nbsp;<%=basePath%>/asset/showDynamicManagement.action
			</li>
		</ul>
		<ul id="supplier">
			<li>供应商管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/supplier/showList.action" target="_blank">[供应商管理]</a>&nbsp;&nbsp;<%=basePath%>/supplier/showList.action
			</li>
		</ul>
		<ul id="supplier">
			<li>资产盘点任务管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/task/showListManage.action" target="_blank">[资产盘点任务管理]</a>&nbsp;&nbsp;<%=basePath%>/task/showListManage.action
			</li>
			<li>
				<a href="<%=basePath%>/task/showListCommon.action" target="_blank">[资产盘点任务上传]</a>&nbsp;&nbsp;<%=basePath%>/task/showListCommon.action
			</li>
		</ul>
		<ul id="supplier">
			<li>设备管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/equipment/showList.action" target="_blank">[设备管理]</a>&nbsp;&nbsp;<%=basePath%>/equipment/showList.action
			</li>
		</ul>
		
		
		<ul id="supplier">
			<li>设备零件更换管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/replace/showList.action" target="_blank">[设备零件更换管理]</a>&nbsp;&nbsp;<%=basePath%>/replace/showList.action
			</li>
		</ul>
		
		<ul id="supplier">
			<li>设备检修管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/service/showList.action" target="_blank">[设备检修管理]</a>&nbsp;&nbsp;<%=basePath%>/service/showList.action
			</li>
		</ul>
		
		<ul id="supplier">
			<li>参数检修管理</li>
			<li>开发人：姚成龙</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/paramCheck/showEquipmentList.action" target="_blank">[参数检修管理]</a>&nbsp;&nbsp;<%=basePath%>/paramCheck/showEquipmentList.action
			</li>
		</ul>
		
		<ul id="hiddenDangersCorrect">
			<li>&nbsp;</li>
			<li>隐患排查</li>
			<li>开发人：孙佳伟</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?operateType=2" target="_blank">[隐患管理]</a>&nbsp;<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?operateType=2
			</li>
			<li>
				<a href="<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?operateType=1" target="_blank">[隐患录入]</a>&nbsp;<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?operateType=1
			</li>
			<li>
				<a href="<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?importance=0" target="_blank">[隐患查询]</a>&nbsp;<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?importance=0
			</li>
			<li>
				<a href="<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?operateType=3&checkState=1" target="_blank">[隐患审核]</a>&nbsp;<%=basePath%>/hiddenDangersCorrect/findHiddenDangersCorrectByPage.action?operateType=3&checkState=1
			</li>
			<li>&nbsp;</li>
		</ul>
		
		<ul id="group">
			<li>团委</li>
			<li>开发人：单伟峰</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/member/findGroupMemberByPage.action" target="_blank">[团委]</a>&nbsp;&nbsp;<%=basePath%>/member/findGroupMemberByPage.action
			</li>
			
		</ul>
		 
		 
		 <ul id="group">
			<li>运营整改</li>
			<li>开发人：单伟峰</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/RectificationWork/findRectificationWorkByPage.action?operateType=1" target="_blank">[录入]</a>&nbsp;&nbsp;<%=basePath%>/RectificationWork/findRectificationWorkByPage.action?operateType=1
			</li>
			<li>
			    <a href="<%=basePath%>/RectificationWork/findRectificationWorkByPage.action?operateType=2" target="_blank">[管理]</a>&nbsp;&nbsp;<%=basePath%>/RectificationWork/findRectificationWorkByPage.action?operateType=2
			</li>
			<li>
			    <a href="<%=basePath%>/RectificationWork/findRectificationWorkByPage.action?operateType=3" target="_blank">[查询]</a>&nbsp;&nbsp;<%=basePath%>/RectificationWork/findRectificationWorkByPage.action?operateType=3
			</li>
			<li>&nbsp;</li>
			<li>&nbsp;</li>
		</ul>
		
		<ul id="group">
			<li>项目计划</li>
			<li>开发人：孙佳伟</li>
			<li>版本号：</li>
			<li>发布时间：</li>
			<li>列表页面：</li>
			<li>
				<a href="<%=basePath%>/projectPlan/findProjectPlanByPage.action" target="_blank">[项目计划]</a>&nbsp;&nbsp;<%=basePath%>/projectPlan/findProjectPlanByPage.action
			</li>
			
		</ul>		
	</div>
</body>
</html>