<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>供应商详细</title>
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


</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">供应商详细</li>
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
<s:form action="saveAdd" name="supplier" namespace="/supplier" method="post">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody>
			<tr>
				<td class="t_r lableTd" style="width:15%;white-space:nowrap;">厂商名称</td>
				<td colspan="4" >
					<s:property value="supplier.name"/>
				</td>
			</tr>
			<tr>
				<td width="10%;" class="t_r lableTd" style="width:15%;white-space: nowrap;">法人代表</td>
				<td width="45%;" style="width:35%"><s:property value="supplier.legalPerson"/></td>
				<td width="10%;" class="t_r lableTd" style="width:15%;white-space: nowrap;">法人代表电话</td>
				<td width="35%;" style="width:35%"><s:property value="supplier.legalPersonTel"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">联系人</td>
				<td><s:property value="supplier.contactPerson"/></td>
				<td class="t_r lableTd">联系人电话</td>
				<td><s:property value="supplier.contactTel"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd" colspan="">厂商类型</td>
				<td >
					<s:if test="supplier.type==0">生产厂商</s:if>
					<s:elseif test="supplier.type==1">供应商</s:elseif>
				</td>
				<td class="t_r lableTd">邮政编码</td>
				<td><s:property value="supplier.zip"/></td>
			</tr>
			<tr>
			<td class="t_r lableTd" >联系地址</td>
				<td colspan="3"><s:property value="supplier.address"/></td>
				
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="4">
					<s:property value="supplier.memo"/>
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> 
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
