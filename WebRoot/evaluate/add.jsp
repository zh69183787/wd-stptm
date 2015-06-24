<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>添加</title>
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
<script type="text/javascript"><!--
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		//关闭窗口
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		//绑定mouseenter,mouseout事件
		$(document).ready(function(){
			$("img[id^=star]").each(function(){
				$(this).bind("mouseenter",function(){starOn($(this));});
			});
			$("img[id^=star]").each(function(){
				$(this).bind("mouseout",function(){starOff($(this));});
			});
		});
		
		//点击，评分
		function starClick(obj){
			var $starNow = $(obj);
			starOn($starNow);
			$starNow.unbind("mouseout").unbind("mouseenter");
			$starNow.siblings().unbind("mouseout").unbind("mouseenter");
			
			$starNow.siblings("span").text(($starNow.prevUntil("input").length+1)+"分");
			$starNow.prevAll("input").val($starNow.prevUntil("input").length+1);
		}
		
		//评分
		function starOn($starNow){
			$starNow.attr("src","../images/starOn.jpg");
			
			$starNow.prevAll("img").each(function(index,element){
				$(element).attr("src","../images/starOn.jpg");
			}); 
			$starNow.nextAll("img").each(function(index,element){
				$(element).attr("src","../images/starOff.jpg")
			});
		}
		//评分
		function starOff($starNow){
			$starNow.attr("src","../images/starOff.jpg");
			$starNow.prevAll("img").each(function(index,element){
				$(element).attr("src","../images/starOff.jpg");
			}); 
			$starNow.nextAll("img").each(function(index,element){
				$(element).attr("src","../images/starOff.jpg")
			});
		}
		
		//清除星级评分
		function clearStar(){
			$("img[id^=star]").each(function(){
				$(this).attr("src","../images/starOff.jpg");			
			});
			$("img[id^=star]").each(function(){
				$(this).bind("mouseenter",function(){starOn($(this));});
			});
			$("img[id^=star]").each(function(){
				$(this).bind("mouseout",function(){starOff($(this));});
			});
			
			$("span[id^=showScore]").each(function(){
				$(this).text("0分");
				$(this).prevUntil("intput").val(0);
			});
		}
		
		//检查表单
		function checkForm(){
			var names = "";
			var scores = "";
			var comments = "";
			var tempValue ;
			
			var flowIdValue = $("#flowId").val(); 
		
			if(flowIdValue==null && flowIdValue=="-1"){
				alert("请选择需要评价的流程！");
				return false;
			}
			///*
			//评价部门
			if($("#evaluateDept").val()==""){
				$("#evaluateDept").focus();
				alert("评价部门不能为空！");
				return false;
			}
			
			//被评部门
			if($("#beEvaluatedDept").val()==""){
				$("#beEvaluatedDept").focus();
				alert("被评部门不能为空！");
				return false;
			}
			
			//详细评价
			if($("#evaluationDetail").val()==""){
				$("#evaluationDetail").focus();
				alert("详细评价不能为空！");
				return false;
			}else{
				if($("#evaluationDetail").val().length>1000){
					alert("详细评价不能超过1000个字！");
					//$("#evaluationDetail").val($("#evaluationDetail").val().substr(0,999));
					$("#evaluationDetail").focus();
					return false
				}
			}
			
			//名称
			$("span[id^=name]").each(function(){
				tempValue = $(this).text();
				tempValue = tempValue.substr(0,tempValue.length-1).replace(/^——$/g,"--");
				names += tempValue+"——";
			});
			//分数
			$("input[id^=hideScore]").each(function(){
				tempValue = $(this).val();
				if(tempValue!=""){
					tempValue = tempValue.replace(/^——$/g,"--");
					scores += tempValue+"——";
				}else{
					scores += "0——";
				}
			});	
			
			//评论
			$("input[id^=comment]").each(function(){
				tempValue = $(this).val();
				tempValue = tempValue.replace(/^——$/g,"--")
				if(tempValue!=""){
					comments += tempValue+"——";
				}else{
					comments += "|——";
				}
			});
			
			$("#submitNames").val(names);
			$("#submitScores").val(scores);
			$("#submitComments").val(comments);
			return true;
		}
		
		//保存流程的选择值		
		$(function(){
			var flowId = $("#hideFlowId").val();
		/****************************/
			$("#"+flowId+"flow").attr("selected",true);
		});
		
		//根据flowId查询
		function showFlowInfo(obj){
			var flowId = $(obj).children("option:selected").val();
			$.ajax({
				 url: 	"findFlowById.action",
				data:	{"flowId":flowId},
				type:	"post",
			   error:	function(){alert("系统错误，请稍后再试！");},
			 success:	function(obj){
			 				var html = ""
			 				if(obj!=null && obj.length>0){
			 					for(var i=0; i<obj.length; i++){
				 					html += "<tr>"+	
												"<td colspan='1' class='t_r lableTd'>"+
													"<span id='name"+i+"'>"+obj[i]+"：</span>"+
												"</td>"+
												"<td colspan='1' class='t_r lableTd' id='score"+i+"' style='width:inherit;'>"+
													"<input type='hidden' name='' id='hideScore"+i+"' value='0'>"+
													"<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>"+
													"<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>"+
													"<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>"+
													"<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>"+
													"<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>"+
													"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
													"<span id='showScore"+i+"' style='float:left;'>0分</span>"+
												"</td>"+
												"<td colspan='1'>"+
													"<input type='text' size='55' id='comment"+i+"' value='' maxlength='100'/>"+
												"</td>"+
												"<td></td>"+
											"</tr>";
								} 
			 				}
			 				$("#evaluateDetial").nextAll().remove().parent().html()
			 				var originalHtml = $("#evaluateDetial").parent().html();
			 				$("#evaluateDetial").parent().append(html);
			 				
			 				$("img[id^=star]").each(function(){
								$(this).bind("mouseenter",function(){starOn($(this));});
							});
							$("img[id^=star]").each(function(){
								$(this).bind("mouseout",function(){starOff($(this));});
							});
			 			}
			});
		}
		
--></script>

</head>

<body>

<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">办公行文评价</a></li>	
	<li class="fin">信息添加</li>
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

<input type="hidden" value="<s:property value='#request.flowId'/>" id="hideFlowId">


<s:form action="saveFlowEvaluaton" name="TFlowEvaluation" namespace="/evaluate">

	<input type="hidden" name="names" id="submitNames">
	<input type="hidden" name="scores" id="submitScores">
	<input type="hidden" name="comments" id="submitComments">

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="button" value="取 消" onclick="clearStar();"/> &nbsp;
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd" width="20%">评价流程：</td>
			<td colspan="3" >
				<select name="tFlowEvaluationVO.flowId" class="input_large" onchange="showFlowInfo(this);" id="flowId">
					<option value="-1" id="-1flow">---请选择---</option>
					<s:iterator value="#request.flowMap" id="flowMap">
						<option id="<s:property value='#flowMap.key'/>flow" value="<s:property value='#flowMap.key'/>"><s:property value='#flowMap.value'/></option>
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">评价部门：</td>
			<td colspan="3">
				<input type="text" name="tFlowEvaluationVO.evaluateDept" value="" maxlength="20" id="evaluateDept"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">被评部门：</td>
			<td colspan="3">
				<input type="text" name="tFlowEvaluationVO.beEvaluatedDept" value="" maxlength="20" id="beEvaluatedDept"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">定性评价：</td>
			<td colspan="3">
				<input type="radio" name="tFlowEvaluationVO.goodMediumBad" checked="checked" value="好评"/>好评
				<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="中评"/>中评
				<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="差评"/>差评
			</td>
		</tr>
		<tr id="evaluateDetial">
			<td class="t_r lableTd">评价详细：</td>
			<td colspan="3">
				<textarea rows="5" name="tFlowEvaluationVO.evaluationDetail" id="evaluationDetail"></textarea>
			</td>
		</tr>
		
		<!--
		<tr>
			<td colspan="1" class="t_r lableTd">定量评价：</td>
			<td colspan="3" class="t_r lableTd"> 
				<span id="name<s:property value="#st.index"/>" style="float:left">aaa</span>
				<s:iterator value="#request.evaluationArray" status="st">
						<span id="name<s:property value="#st.index"/>" style="float:left"><s:property/>：</span>
						<input type="hidden" name="" id="hideScore<s:property value="#st.index"/>" value="0">
						<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff1" onclick="starClick(this);">
						<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff2" onclick="starClick(this);">
						<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff3" onclick="starClick(this);">
						<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff4" onclick="starClick(this);">
						<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff5" onclick="starClick(this);">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="showScore<s:property value="#st.index"/>" style="float:left;"></span>
						<input type="text" size="55" id="comment<s:property value="#st.index"/>" value="" maxlength="100" style="float:left"/>
						<br/><br/>
				</s:iterator>
			</td>
		</tr>	
		-->		
		
		<s:iterator value="#request.evaluationList" status="st">
		<tr>	
			<td colspan="1" class="t_r lableTd">
				<span id="name<s:property value="#st.index"/>"><s:property/>：</span>
			</td>
			<td colspan="1" class="t_r lableTd" id="score<s:property value="#st.index"/>" style="width:inherit;">
				<input type="hidden" name="" id="hideScore<s:property value="#st.index"/>" value="0">
				<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff1" onclick="starClick(this);">
				<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff2" onclick="starClick(this);">
				<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff3" onclick="starClick(this);">
				<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff4" onclick="starClick(this);">
				<img alt="" src="../images/starOff.jpg" style="float: left" id="starOff5" onclick="starClick(this);">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span id="showScore<s:property value="#st.index"/>" style="float:left;">0分</span>
			</td>
			<td colspan="1">
				<input type="text" size="55" id="comment<s:property value="#st.index"/>" value="" maxlength="100"/>
			</td>
			<td></td>
		</tr>
		</s:iterator>
		
		
	</tbody>
	<tr class="tfoot">
		<td colspan="20" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="button" value="取 消" onclick="clearStar();"/>&nbsp;
		</td>
	</tr>
</table>
</s:form>

</div>
<!--Table End--></div>
</body>
</html>
