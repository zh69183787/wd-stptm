<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>设备参数检查详细</title>
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
<style type="text/css">
	.must{
		color:red;
	}
</style>



</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">设备参数检查详细</li>
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
<s:form action="saveAdd" name="paramCheck" namespace="/paramCheck" method="post">
<input type="hidden" value="<s:property value="#request.equipmentId"/>" name="equipmentId">	<!-- 设备编号 -->

	<table width="100%" class="table_1">
		<thead>
			<th colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody id="formBody">
			<s:if test="#request.map!=null&&#request.map.size()>0">
				<s:iterator value="#request.map" id="map" status="mapst">
					<s:iterator value="#map.value" id="valueList" status="st">
						<s:if test="#st.index%#request.paramsList.size==0">
							<tr>
								<td class="t_r lableTd" width="20%;">检查日期</td>
								<td width="80%;"><s:property value="#valueList.checkDate"/></td>
							</tr>		
						</s:if>
						<tr>
							<td class="t_r lableTd"><s:property value="#request.paramsList[#st.index].parameterName"/></td>
							<td><s:property value="#valueList.paramterValue"/></td>
						</tr>
					</s:iterator>
					<s:iterator value="#map.value" id="valueList" status="st">
						<s:if test="#st.index%#request.paramsList.size==0">
							<tr>
								<td class="t_r lableTd">检查人员</td>
								<td><s:property value="#valueList.checkPerson"/></td>
							</tr>		
						</s:if>
					</s:iterator>
					<s:if test="#mapst.index!=(#request.map.size-1)">
						<tr><td colspan="5">&nbsp;</td></tr>
					</s:if>
				</s:iterator>
			</s:if>
			<s:else>
				<tr>
					<td class="t_l">无历史检查记录</td>
				</tr>
			</s:else>
			
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
