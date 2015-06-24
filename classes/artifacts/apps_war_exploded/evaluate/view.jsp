<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>查看</title>
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
		
		
		$(document).ready(function(){
		
			var starOff = "<img alt='' src='../images/starOff.jpg' style='float: left' id='starOff1' >";
			var starOn = "<img alt='' src='../images/starOn.jpg' style='float: left' id='starOff1' >";
		
			$("input[id^=getScore]").each(function(index,elem){
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
				$(this).parent("td").append(score+"分");
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
	<li class="fin">信息查看</li>
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

<s:form action="saveEvaluation" name="OfficialBusinessEvaluation" namespace="/evaluate">

	<input type="hidden" name="names" id="submitNames">
	<input type="hidden" name="scores" id="submitScores">
	<input type="hidden" name="comments" id="submitComments">

<table width="100%" class="table_1">
	<thead>
		<th colspan="5" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd" style="width:20%">涉及流程：</td>
			<td colspan="3">
				<s:property value='#request.flowEvaluation.flowName'/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">评价部门：</td>
			<td colspan="3" >
				<s:property value='#request.flowEvaluation.evaluateDept'/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">被评部门：</td>
			<td colspan="3" >
				<s:property value='#request.flowEvaluation.evaluateDept'/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">定性评价：</td>
			<td colspan="3" >
				<s:property value='#request.flowEvaluation.goodMediumBad' />
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">评价详细：</td>
			<td colspan="3">
				<textarea rows="5" readonly="readonly"><s:property value="#request.flowEvaluation.evaluationDetail"/></textarea>
			</td>
		</tr>
		
		
		<s:iterator value="#request.names" status="st">
			<tr>
				<td colspan="1" class="t_r lableTd">
					<span id="name<s:property value="#st.index"/>"><s:property/>：</span>
				</td>
				<td colspan="1" class="t_r lableTd" id="score<s:property value="#st.index"/>" style="width:inherit;">
					<input type="hidden" name="" id="getScore<s:property value="#st.index"/>" value="<s:property value='#request.scores[#st.index]'/>" />
					<input type="hidden" name="" id="hideScore<s:property value="#st.index"/>" value="0" />
				</td>
				<td colspan="2">
					<s:property value='#request.comments[#st.index]'/>
				</td>
			</tr>
		</s:iterator>	
	
		
	</tbody>
	<tr class="tfoot">
		<td colspan="5" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> 
		</td>
	</tr>
</table>
</s:form>

</div>
<!--Table End--></div>
</body>
</html>

