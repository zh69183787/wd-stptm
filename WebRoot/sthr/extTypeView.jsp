<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>人事扩展信息类别查看</title>
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
	<li class="fin">详细</li>
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

<s:form action="addHrEt" name="HrEt" namespace="/sthr" id="form">
<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<!-- <input type="submit"" value="确 认" " onclick="return checkForm();"/>&nbsp; --> 
			<input type="button" value="关 闭" onclick="shut();"/> &nbsp; 
			<!-- <input type="reset" value="取 消" onclick="clearData();"/> &nbsp; -->
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd">类别名称</td>
			<td>
				<!-- <input type="text" id="typeName" name="typeName" class="input_large" value="<s:property value='#request.typeName'/>" /> -->
				<s:property value='#request.hret.typeName'/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">排列顺序</td>
			<td>
				<!-- <input type="text" id="sortingOrder" name="sortingOrder" class="input_large" value="<s:property value='#request.sortingOrder'/>"/> -->
				<s:property value="#request.hret.sortingOrder"/>
			</td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<!-- <input type="submit""" value="确 认" id="submit" onclick="return checkForm();"/>&nbsp; --> 
			<input type="button" value="关闭" onclick="shut();"/> 
			<!-- <input type="reset" value="取 消" onclick="clearData();"/>&nbsp; -->
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
