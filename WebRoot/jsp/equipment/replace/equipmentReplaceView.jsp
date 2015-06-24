<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>设备零件更换详细</title>
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


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	
	$("input[name=replaceDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});	
	
});
</script>


<!-- 该段script最后要删除-->
<script type="text/javascript">
//点击右上角的确认将所有输入框赋值为第一个数据框的值 
function setValue(){
	$("input").each(function(){
		$(this).val($("input:eq(3)").val());
	});
}


</script>



<script type="text/javascript">
$(function(){
	$("input[name=name]").parent().text();
});



//表单检验
function checkForm(){

	var value ;
	value = $("input[name=name]").val(); 
	if(value==""){
		alert("请输入厂商名称");
		$("input[name=name]").focus();
		return false;
	}
	$("#formBody tr td input").each(function(){
		var text = $(this).val();  
		$(this).val(
			text.replace(/(^\s*)|(\s*$)/g,'')
		);
	});
	var textarea = $("#formBody tr td textarea").val();
	$("#formBody tr td textarea").val(textarea.replace(/(^\s*)|(\s*$)/g,''));
	
	return true;
}






</script>






</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">设备零件更换详细</li>
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
<s:form action="saveAdd" name="replace" namespace="/replace" method="post">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td width="15%;" class="t_r lableTd">部件名称</td>
				<td width="35%;">
					<s:property value="equipmentPartsReplace.partsName"/>
				</td>
				<td width="15%;" class="t_r lableTd">数量</td>
				<td width="35%;">
					<s:property value="equipmentPartsReplace.partsAmount"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">规格型号</td>
				<td>
					<s:property value="equipmentPartsReplace.productModel"/>
				</td>
				<td class="t_r lableTd">生产厂家</td>
				<td >
					<s:property value="equipmentPartsReplace.manufacturer"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">单价</td>
				<td><s:property value="equipmentPartsReplace.price"/></td>
				<td class="t_r lableTd">费用</td>
				<td><s:property value="equipmentPartsReplace.cost"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">消耗率</td>
				<td><s:property value="equipmentPartsReplace.consumeRate"/></td>
				<td class="t_r lableTd">更换日期</td>
				<td><s:property value="equipmentPartsReplace.replaceDate"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注</td>
				<td colspan="3">
					<s:property value="equipmentPartsReplace.notes"/>
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/>
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
