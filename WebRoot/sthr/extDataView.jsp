<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>人事扩展信息数据项查看</title>
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
		
		function clearData(){
		
			$("#itemName").attr("value","");
			$("#sortingOrder").attr("value","");
		}
		
		
		$(document).ready(function(){
			var status;
			status = $("#itemNameStatus").val();
			if(status!=null && status!=""){
				alert(status);
				//$("#itemName").attr("value","");
				$("#itemName").focus();
			}
			status = $("#sortingOrderStatus").val();
			if(status!=null && status!=""){
				alert(status);
				//$("#sortingOrder").attr("value","");
				$("#sortingOrder").focus();
			}
			status = $("#outOfDB").val();
			if(status!=null && status!=""){
				alert(status);
				clearData();
			}			
					
			if($("#inputType").val().substring(0,3)=='下拉框'){			  
			  var optionInputType = $("#inputType").val().substring(14);	
			  $("#dd").html("下拉框");			  		  
			  $("#sss").after("<tr><td class=\"t_r lableTd\">下拉框数据项</td><td>"+optionInputType+"</td></tr>");
			}
			if($("#inputType").val().substring(0,3)=='多选框'){			  
			  var optionInputType = $("#inputType").val().substring(4);	
			  $("#dd").html("多选框");			  		  
			  $("#sss").after("<tr><td class=\"t_r lableTd\">多选框数据项</td><td>"+optionInputType+"</td></tr>");
			}
		});
		
		function checkForm(){
			
			var value ;
			value = $("#itemName").val();
			if(value==""){
				alert("请填写数据项名称");
				$("#itemName").focus();
				return false;
			}
			value = $("#sortingOrder").val();
			if(value==""){
				alert("请填写排列顺序");
				$("#sortingOrder").focus();
				return false;
			}
			if(!$("#sortingOrder").val().match(/^[0-9]*$/)){
				alert("请填写数字");
				//$("#sortingOrder").val("");
				$("#sortingOrder").focus();
				return false;
			}
			return true;
		}
		
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
   	<li><a href="#">数据项</a></li>
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
-->
</div>
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">


<input type="hidden" name="" value="<s:property value='#request.itemNameStatus'/>" id="itemNameStatus">
<input type="hidden" name="" value="<s:property value='#request.sortingOrderStatus'/>" id="sortingOrderStatus">
<input type="hidden" name="" value="<s:property value='#request.outOfDB'/>" id="outOfDB">
<input type="hidden" id="inputType" value="<s:property value="#request.hrEtD.inputType"/>">

<input type="hidden" name="hretId" value="<s:property value='#request.hretId'/>" />

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp;
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd">类别名称</td>
			<td>
				<s:property value="#request.hrEtD.typeName"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">数据项名称</td>
			<td>
				<s:property value="#request.hrEtD.itemName"/>
			</td>
		</tr>
		<tr id="sss">
			<td class="t_r lableTd">录入方式</td>
			<td id="dd">
                <s:property value="#request.hrEtD.inputType"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">排列顺序</td>
			<td>
				<s:property value="#request.hrEtD.sortingOrder"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">是否有效</td>
			<td>
				<s:if test="#request.hrEtD.isLShow==0">
					否
				</s:if>
				<s:else>
					是
				</s:else>
			</td>
		</tr>
		<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/>
		</td>
	</tr>
	</tbody>
	<tr class="tfoot">
		
	</tr>
</table>

</div>
<!--Table End--></div>
</body>
</html>
