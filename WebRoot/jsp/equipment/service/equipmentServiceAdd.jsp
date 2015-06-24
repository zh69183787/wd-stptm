<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>设备检修新增</title>
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
	
	$("input[name=serviceDate]").datepicker({
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
	//检修程度
	value = $("select[name=serviceLevel]").val();
	if(value=="-1"){
		alert("请选择检修程度！");
		return false;
	}
	
	//检修日期
	value = $("input[name=serviceDate]").val();
	if(isEmpty(value)){
		alert("请选择检修日期！");
		$("input[name=serviceDate]").focus();
		return false;
	}
	//检修费用
	value = $("input[name=serviceCost]").val();
	if(isEmpty(value)){
		alert("请输入检修费用！");
		$("input[name=serviceCost]").focus();
		return false;
	}else{
		if(!isPositiveInteger(value)){
			alert("请输入正整数！");
			$("input[name=serviceCost]").focus();
			return false;
		}
	}
	//实施单位
	value = $("input[name=implementUnit]").val();
	if(isEmpty(value)){
		alert("请输入实施单位！");
		$("input[name=implementUnit]").focus();
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



//判断是否为空
function isEmpty(value){	
	if(value==null) return true;
	return trimAround(value)=="";
}

function isPositiveInteger(value){
	if(value=="0") return true;
	var reg = /^[1-9]{1}\d*/;
	return reg.test(value);
}

//删除左右两端的空格
function trimAround(str){
	if(str==null) return null;
	return str.replace(/(^\s*)(\s*$)/g,"");
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
	<li class="fin">设备检修新增</li>
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
<s:form action="saveAdd" name="service" namespace="/service" method="post">
<input type="hidden" value="<s:property value="#request.equipmentId"/>" name="equipmentId">	<!-- 设备编号 -->

	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset" value="取 消" /> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd">检修程度</td>
				<td class="must">
					<select name="serviceLevel" class="input_large">
						<option value="-1">---请选择---</option>
						<option value='1'>重大故障临修</option>
						<option value='2'>大修</option>
						<option value='3'>中修</option>
					</select>&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd">检修日期</td>
				<td class="must">
					<input type="text" id="" name="serviceDate" class="input_large date" />&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">检修费用(元)</td>
				<td class="must"><input type="text" name="serviceCost" class="input_large" />&nbsp;&nbsp;*</td>
				<td class="t_r lableTd">实施单位</td>
				<td class="must"><input type="text" name="implementUnit" class="input_large" />&nbsp;&nbsp;*</td>
			</tr>
			<tr>
				<td class="t_r lableTd">检修内容</td>
				<td colspan="3">
					<textarea rows="3" cols="4" name="serviceContent"></textarea>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注</td>
				<td colspan="3">
					<textarea rows="3" cols="3" name="notes"></textarea>
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="4" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="reset"value="取 消" />&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
