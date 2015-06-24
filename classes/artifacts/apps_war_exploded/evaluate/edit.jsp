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
		
		//关闭窗口
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
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
		
		$(document).ready(function(){
			var starOff = "<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>";
			var starOn = "<img alt='' src='../images/starOn.jpg' style='float: left' id='starOff1' onclick='starClick(this);'>";
			$("input[id^=hideScore]").each(function(index,elem){
				var score = $(this).val();
				if(0!=parseInt(score)){
					for(var i=1 ;i<=5; i++){
						if(i<=parseInt(score)){
							$(this).parent("td").append(starOn);
						}else{
							$(this).parent("td").append(starOff);
						}
					}
				}else{
					for(var i=1 ;i<=5; i++){
						$(this).parent("td").append(starOff);
					}
				}
				$(this).parent("td").append("<span id='showScore<s:property value='#st.index'/>' >"+score+"分</span>");
			});
		});
</script>

</head>

<body>

<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">办公行文评价</a></li>	
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

<s:form action="updateFlowEvaluation" name="TFlowEvaluation" namespace="/evaluate">

	<input type="hidden" name="names" id="submitNames">
	<input type="hidden" name="scores" id="submitScores">
	<input type="hidden" name="comments" id="submitComments">
	<input type="hidden" name="tFlowEvaluationVO.id" value="<s:property value='#request.flowEvaluation.id'/>">


<table width="100%" class="table_1">
	<thead>
		<th colspan="5" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
			<input type="button"" value="取 消" onclick="clearStar();"/> &nbsp;
		</th>
	</thead>
	<tbody>
		<tr>
			<td colspan="1" class="t_r lableTd" width="20%">评价流程：</td>
			<td colspan="3" >
				<s:property value='#request.flowEvaluation.flowName'/>				
			</td>
			
		</tr>
		<tr>
			<td colspan="1" class="t_r lableTd">评价部门：</td>
			<td colspan="3" >
				<input type="text" name="tFlowEvaluationVO.evaluateDept" value="<s:property value='#request.flowEvaluation.evaluateDept'/>" maxlength="20"/>
			</td>
		</tr>
		<tr>
			<td colspan="1" class="t_r lableTd">被评部门：</td>
			<td colspan="3" >
				<input type="text" name="tFlowEvaluationVO.beEvaluatedDept" value="<s:property value='#request.flowEvaluation.evaluateDept'/>" maxlength="20"/>
			</td>
		</tr>
		<tr>
			<td colspan="1" class="t_r lableTd">定性评价：</td>
			<td colspan="3" >
				<s:if test="#request.flowEvaluation.goodMediumBad=='好评'">
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="好评" checked="checked" />好评
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="中评" />中评
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="差评" />差评
				</s:if>
				<s:elseif test="#request.flowEvaluation.goodMediumBad=='中评'">
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="好评" />好评
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="中评" checked="checked" />中评
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="差评" />差评
				</s:elseif>
				<s:else>
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="好评" />好评
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="中评" />中评
					<input type="radio" name="tFlowEvaluationVO.goodMediumBad" value="差评" checked="checked" />差评
				</s:else>
			</td>
		</tr>
		<tr>
			<td colspan="1" class="t_r lableTd">评价详细：</td>
			<td colspan="3">
				<textarea rows="5" name="tFlowEvaluationVO.evaluationDetail"><s:property value="#request.flowEvaluation.evaluationDetail"/></textarea>
			</td>
		</tr>
		
		<s:if test="#request.names!=null">
			<s:iterator value="#request.names" status="st">
				<tr>
					<td colspan="1" class="t_r lableTd">
						<span id="name<s:property value="#st.index"/>"><s:property/>：</span>
					</td>
					<td colspan="1" class="t_r lableTd" id="score<s:property value="#st.index"/>" style="width:inherit;">
						<input type="hidden" name="" id="hideScore<s:property value="#st.index"/>" value="<s:property value='#request.scores[#st.index]'/>" >
					</td>
					<td colspan="2">
						<input name="" size="55" value="<s:property value='#request.comments[#st.index]'/>" id="comment<s:property value="#st.index"/>">
					</td>
				</tr>
			</s:iterator>
			
		</s:if>
		<s:else>
			<s:iterator value="#request.evaluationList" status="st">
			<tr>	
				<td colspan="1" class="t_r lableTd">
					<span id="name<s:property value="#st.index"/>"><s:property/>：</span>
				</td>
				<td colspan="1" class="t_r lableTd" id="score<s:property value="#st.index"/>" style="width:inherit;">
					<input type="hidden" name="" id="hideScore<s:property value="#st.index"/>" value="0">
				</td>
				<td colspan="1">
					<input type="text" size="55" id="comment<s:property value="#st.index"/>" value="" maxlength="100"/>
				</td>
				<td></td>
			</tr>
			</s:iterator>	
		</s:else>
	
		
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
