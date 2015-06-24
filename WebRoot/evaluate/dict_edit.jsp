<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
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
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
		</style>
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		$(document).ready(function(){
			var msg = $("#msg").val(); 
			if(msg!="保存成功"){
				if(msg!=""){
					alert(msg);
					if(msg.indexOf("评价角度")>0){
						$("#name").focus();
					}else if(msg.indexOf("排列顺序")>0){
						$("#sortingOrder").focus();
					}
				}
			}else{
				alert(msg);
				shut();
			}
		});
		
		//关闭窗口
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		
		
		//检查表单
		function checkForm(){
			
			if($("#name").val()==""){
				alert("评价角度不能为空！");
				$("#name").focus();
				return false;
			}
			if($("#sortingOrder").val()==""){
				alert("排列顺序不能为空！");
				$("#sortingOrder").focus();
				return false;
			}
			
			if(!$("#sortingOrder").val().match(/^[0-9]*$/)){
				alert("排列顺序只能输入数字！");
				$("#sortingOrder").focus();
				return false;
			}
			return true;
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
	<li><a href="#">定量角度评价管理</a></li>	
	<li class="fin">信息编辑</li>
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

<div class="mb10">

<input type="hidden" value="<s:property value='#request.msg'/>" id="msg">

<s:form action="updateEvaluationItem" name="TFlowEvaluationItem" namespace="/evaluate">
	<input type="hidden" value="<s:property value='tFlowEvaluationItemVO.id'/>" name="id">

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="reset" value="取 消" onclick="clearStar();"/> &nbsp;
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd"  style="width:20%">评价流程：</td>
			<td colspan="3">
				<input type="hidden" value="<s:property value='#request.flowName'/>" name="flowName">
				<s:property value='#request.flowName'/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">评价角度：</td>
			<td >
				<input type="text" name="tFlowEvaluationItemVO.name" value="<s:property value='tFlowEvaluationItemVO.name'/>" maxlength="20" id="name"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">排列顺序：</td>
			<td >
				<input type="text" name="tFlowEvaluationItemVO.sortingOrder" value="<s:property value='tFlowEvaluationItemVO.sortingOrder'/>" maxlength="3" id="sortingOrder"/>
			</td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="20" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="reset" value="取 消" onclick="clearStar();"/>&nbsp;
		</td>
	</tr>
	
</table>
</s:form>

</div>
<!--Table End--></div>
</body>
</html>
