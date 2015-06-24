<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>设备参数检查新增</title>
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
	
	$("input[name=checkDate]").datepicker({
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
	<li class="fin">设备参数检查新增</li>
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

<s:if test="#request.errorInfo==null">
	<s:form action="saveAdd" name="paramCheck" namespace="/paramCheck" method="post">
		<input type="hidden" value="<s:property value="#request.equipmentId"/>" name="equipmentId">	<!-- 设备编号 -->
		<table width="100%" class="table_1">
			<thead>
				<th colspan="6" class="t_r">
					<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
					<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
					<input type="reset" value="取 消" /> &nbsp;
				</th>
			</thead>
			<tbody id="formBody">
				<tr>
					<td class="t_r lableTd">检查日期</td>
					<td >
						<input type="text" id="" name="checkDate" class="input_large date" readonly="readonly"/>
					</td>
				</tr>
				<s:iterator value="#request.paramList" id="param">
				<input type="hidden" name="paramId" value="<s:property value="#param.id"/>">
					<tr>
						<td class="t_r lableTd"><s:property value="#param.parameterName"/></td>
						<td>
							<input name="paramValue" class="input_large">
						</td>
					</tr>
				</s:iterator>
				<tr>
					<td class="t_r lableTd">检查人员</td>
					<td >
						<input type="text" id="" name="checkPerson" class="input_large" />
					</td>
				</tr>
			</tbody>
			<tr class="tfoot">
				<td colspan="6" class="t_r">
					<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
					<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
					<input type="reset"value="取 消" />&nbsp;
				</td>
			</tr>
		</table>
	</s:form>
</s:if>
<s:else>
	<table width="100%" class="table_1">
		<thead>
			<th colspan="2" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_l" colspan="2"><s:property value="#request.errorInfo"/></td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="2" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> 
			</td>
		</tr>
	</table>
</s:else>


</div>
<!--Table End--></div>
</body>
</html>
