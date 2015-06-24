<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>编辑</title>
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
<style type="text/css">
	.must{
		color:red;
	}
</style>


<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");		
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
	
	value = $("input[name=legalPersonTel]").val();		//法人代表电话
	var reg = /^[\d/8-]{0,20}$/;
	if(!reg.test(value)){
		alert("电话只能输入数字,\"*\" 和 \"-\"");
		$("input[name=legalPersonTel]").focus();
		return false;
	}
	
	value = $("input[name=contactTel]").val();		//联系人电话
	var reg = /^[\d/8-]{0,20}$/;
	if(!reg.test(value)){
		alert("电话只能输入数字,\"*\" 和 \"-\"");
		$("input[name=contactTel]").focus();
		return false;
	}
	
	
}

//重置
function clearInput(){
	$("tbody input:lt(7)").val("");
	$("textarea").val("");
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
	<li class="fin">供应商编辑</li>
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
<s:form action="saveEdit" name="supplier" namespace="/supplier" method="post">
<input type="hidden" value="<s:property value="supplier.id"/>" name="id"/>


	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="重 置" onclick="clearInput();"/> &nbsp;
			</th>
		</thead>
		<tbody>
			<tr>
				<td class="t_r lableTd" style="white-space: nowrap;">厂商名称</td>
				<td colspan="3" style="color: red;">
					<input type="text" id="" name="name" class="input_large" style="width: 440px;" value="<s:property value="supplier.name"/>" maxlength="30"/>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td width="10%;" class="t_r lableTd" style="white-space: nowrap;">法人代表</td>
				<td width="45%;"><input type="text" id="" name="legalPerson" class="input_large" value="<s:property value="supplier.legalPerson"/>" maxlength="10"/></td>
				<td width="10%;" class="t_r lableTd" style="white-space: nowrap;">法人代表电话</td>
				<td width="35%;"><input type="text" id="" name="legalPersonTel" class="input_large" value="<s:property value="supplier.legalPersonTel"/>" maxlength="20"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd" style="white-space: nowrap;">联系人</td>
				<td><input type="text" id="" name="contactPerson" class="input_large" value="<s:property value="supplier.contactPerson"/>" maxlength="10"/></td>
				<td class="t_r lableTd" style="white-space: nowrap;">联系人电话</td>
				<td><input type="text" id="" name="contactTel" class="input_large" value="<s:property value="supplier.contactTel"/>" maxlength="20"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd" style="white-space: nowrap;">联系地址</td>
				<td><input type="text" id="" name="address" value="<s:property value="supplier.address"/>" maxlength="30" style="width: 440px;"/></td>
				<td class="t_r lableTd" style="white-space: nowrap;">邮政编码</td>
				<td><input type="text" id="" name="zip" class="input_large" value="<s:property value="supplier.zip"/>" maxlength="6"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd" colspan="">厂商类型</td>
				<td colspan="5">
					<select name="type" size="1" class="input_large">
						<s:if test="supplier.type==0">
							<option value="1">供应商</option>
							<option value="0" selected="selected">生产厂商</option>
						</s:if>
						<s:elseif test="supplier.type==1">
							<option value="1" selected="selected">供应商</option>
							<option value="0">生产厂商</option>						
						</s:elseif>
					</select>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注信息 </td>
				<td colspan="3">
					<textarea rows="5" cols="5" name="memo" ><s:property value="supplier.memo"/></textarea>					
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();" /> &nbsp; 
				<input type="button" value="重 置" onclick="clearInput();"/>&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
