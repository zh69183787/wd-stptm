<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%String basePath = request.getContextPath(); %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>公休申请</title>
<link rel="stylesheet" href="<%=basePath%>/css/formalize.css" />
<link rel="stylesheet" href="<%=basePath%>/css/page.css" />
<link rel="stylesheet" href="<%=basePath%>/css/imgs.css" />
<link rel="stylesheet" href="<%=basePath%>/css/reset.css" />
<link rel="stylesheet" href="<%=basePath%>/js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="<%=basePath%>/js/html5.js"></script>
<script src="<%=basePath%>/js/jquery-1.7.1.js"></script>
<script src="<%=basePath%>/js/jquery.formalize.js"></script>
<script src="<%=basePath%>/js/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=basePath%>/js/datepicker/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
#cover
{
    position:absolute;
    right:0px;
    bottom:0px;
    width:100%;
    height:100%;
    display:none;
    z-index:99;
}
#tb_window
{
    width:450px;
    height:220px;
    border:2px #6699CC solid;
    z-index:2;
    background:#FFFFFF;
    margin:200px auto;
}
</style>
<script type="text/javascript">
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
}  
</script>
<script type="text/javascript">
$(document).ready(function () {
            var $tbInfo = $(".filter .query input:text");
            $tbInfo.each(function () {
                $(this).focus(function () {
                    $(this).attr("placeholder", "");
                });
            });
			
			var $tblAlterRow = $(".table_1 tbody tr:even");
			if ($tblAlterRow.length > 0)
				$tblAlterRow.css("background","#fafafa");			
        });  
   
    
</script>
 
 
<script type="text/javascript">
function cancelDate(target){
	if($(target).parent().parent().find('div').length==1){
		alert('公休天数至少一天，无法删除！');
		return;
	}
	$(target).parent().remove();
	//setAllDay();
}
var dpIndex=0;





var dp = '<div class="clearfix"><input onchange="setAllDay();" type="text" id="holDay'+dpIndex+'" name="holDay" class="dp input_small" class="input" maxlength="20" readonly="readonly" style="float:left;"/><img src="<%=basePath%>/annualLeave/images/delete.gif" style="float:left;" onclick="cancelDate(this)"></div>';
$(function(){
	$("#startDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$( "#endDate" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#endDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
		}
	});


	var days = $('#days').val();
	
	
	
});

function setAllDay(){
	var index =0;
	$('#holDaysArea').find('input').each(function(){
		if($(this).val()!=null && $(this).val()!=''){
			index++; 
		}
	});
	$('#allDays').html(index+'天');
}

function clearLeader(){
	$('#leaderName').val('');
	$('#leaderId').val('');
}
function showPickLeader(){
	$('#cover').show();
}
function hidePickLeader(){
	$("#cover").hide();
}
function setLeader(){
	var leaderId = $("input[type='radio']:checked").val();
	var leaderName = $("input[type='radio']:checked").parent().text();
	$('#leaderId').val(leaderId);
	$('#leaderName').val(leaderName);
	$('#cover').hide();
}
function check(holState){
	$('#submitBt').attr('disabled',true);
	var holDays=[];
	var leaderName = $('#leaderName').val();
	if(leaderName==null || ''==leaderName){
		alert('请选择审批领导！');
		$('#submitBt').attr('disabled',false);
		return false;
	}
	
	if($('#startDate').val()==''){
		alert('请选择公休开始日期！');
		$('#submitBt').attr('disabled',false);
		return false;
	}
	if($('#endDate').val()==''){
		alert('请选择公休结束日期！');
		$('#submitBt').attr('disabled',false);
		return false;
	}
	var index =0;
	$('#daysArea').find('input[type="hidden"]').each(function(){
		if($(this).val()!=null && $(this).val()!=''){
			$('#form').append('<input name="holDays" type="hidden" value="'+$(this).val()+'">');
			holDays.push($(this).val());
			index++; 
		}
	});
	if(holDays.length>0){
		holDyas = holDays.sort();
		$('#holDaysArray').val(holDyas);
		for(var i=0;i<holDays.length;i++){
			if (holDays[i]==holDays[i+1]){
				alert("申请休假日期重复："+holDays[i]);
				$('#submitBt').attr('disabled',false);
				return false;
			}
		}
	}
	
	if(index==0){
		alert('请选择公休日期！');
		$('#submitBt').attr('disabled',false);
		return false;
	}
	if(index>$('#allDaysLeft').val()){
		alert('申请天数不能超过当前可用天数！');
		$('#submitBt').attr('disabled',false);
		return false;
	}
	if($('#holComment').html().length>100){
		alert('申请说明只限100字');
		$('#holComment').focus();
		$('#submitBt').attr('disabled',false);
		return false;
	}
	/*
	if($('#mobile').val()!=null && $('#mobile').val()!=''){
		var status = checkMobileNumber($('#mobile'));	
		if(!status){
			return false;
		}
	}*/
	
	$('#holState').val(holState);
	//$('#submitBt').attr('disabled',false);
	$('#form').submit();
	//return true;
}

function setHoldays(){
	var start = $('#startDate').val();
	var end = $('#endDate').val();
	
	if(start==''){
		alert('请选择公休开始日期！');
		$('#stratDate').focus();
		return;
	}
	if(end==''){
		alert('请选择公休结束日期！');
		$('#endDate').focus();
		return;
	}
	$('#clearBt').show();
	$('#startDate').prop('disabled',true);
	$('#endDate').prop('disabled',true);
	$.ajax({
			type : 'post',
			url : "<%=basePath%>/holConfig/findHolConfigDays.action?random="+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				startDate:$('#startDate').val(),
				endDate:$('#endDate').val()
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				if(object!=null && object.length>0){
					var html = '';
					for(var i=0;i<object.length;i++){
						var d = '<input type="hidden" value="'+object[i]+'">';
						if(i!=object.length-1){
							html+=object[i]+',';
							$('#daysArea').append(d);
						}else{
							html+=object[i];
							$('#daysArea').append(d);
						}
						if((i+1)%10==0){
							html+='<br/>';
						}
					}
					$('#daysArea').append(html);
					$('#daysArea').append('<br/><input type="button" value="清除" id="clearBt" onclick="clearHoldays();">');
					$('#allDays').html(object.length);
				}else{
                    $('#startDate').prop('disabled',false);
                    $('#endDate').prop('disabled',false);
                    $('#setBt').prop('disabled',false);
                }
			}
		});
		$('#setBt').prop('disabled',true);
}

function clearHoldays(){
	$('#daysArea').html('');
	$('#allDays').html('0天');
	$('#startDate').val('');
	$('#endDate').val('');
	$('#startDate').attr('disabled',false);
	$('#endDate').attr('disabled',false);
	$('#setBt').attr('disabled',false);
	$( "#startDate" ).datepicker( "destroy" );
	$( "#endDate" ).datepicker( "destroy" );
	$("#startDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$( "#endDate" ).datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#endDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
		}
	});
}

function checkMobile(str) {
    var re = /^1\d{10}$/
    if (re.test(str)) {
        return true;
    } else {
        return false;
    }
}

function checkMobileNumber(target){
	var status = true;
	var reg = /^[\d\,]*/;
	var text = $(target).val();
	
	if(!reg.test(text)){
		alert('手机号码只能输入数字，手机号码之间请用逗号分隔！');
		$(target).focus();
		status = false;
	}
	if(!status){
		return status; 
	}

	if(text.indexOf(',')!=-1){
		var numberArray = text.split(',');
		for(var i=0;i<numberArray.length;i++){
			if(!checkMobile(numberArray[i])){
				alert('手机号码不正确，请重新输入！');
				$(target).focus();
				status =  false;
				break;
			}
		}
	}
	return status;
}
function noticeTo(){

    window.idField=document.getElementById('noticeIdList');
    window.nameField=document.getElementById('noticeNameList');
    //alert(window.idField);
    var left=(window.screen.width-400)/2;
    var top=(window.screen.height-500)/2;
    var root ='0';
    window.open('<%=basePath%>/holHolidaysApply/choosePerson.action?root=' + root ,'',
                    'height=500,width=450,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,left=' + left + ',top=' + top);

}
function clears()
{
    document.all("noticeIdList").value="";
    document.all("noticeNameList").value="";
    $("#sjhm").attr('value','');
}
</script> 
 
 
 
 
</head>

<body>
<input type="hidden" value="<s:property value="#request.days"/>" id="days">
<input type="hidden" value="<s:property value="#request.allDaysLeft"/>" id="allDaysLeft">
<div id="cover" style="left: 0px; top: 0px;">
	<div id="tb_window" style="margin-left:30%;">
		<div style="background:#6699CC; text-align:right; height:28px; padding:5px;">
		    <label id="title" style="float:left; font-size:15px; color:blue;">选择审核领导</label>
		    <input type="button" onclick="hidePickLeader();" value="关闭" style=" border-style:none;" />
		</div>
		<div style="padding-left:20px;padding-top: 6px;">
			<s:iterator id="user" value="#request.leaders" status="st">
				<s:if test="#st.index==0">
					<span><input name="leader" type="radio" value="<s:property value="#user.id"/>" checked="checked"><s:property value="#user.name"/><br/></span>
				</s:if>
				<s:else>
					<span><input name="leader" type="radio" value="<s:property value="#user.id"/>"><s:property value="#user.name"/><br/></span>
				</s:else>
			</s:iterator>
			<br/>
			<input type="button" value="确认" onclick="setLeader();">
		</div>
	</div>
</div>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img src="<%=basePath%>/images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
			<div class="posi fl">
				<ul>
					<li><a href="#">公休事务</a></li>
					<li class="fin">公休申请</li>
				</ul>
			</div>
			<div class="fr lit_nav">
				<ul>
					<li><a class="print" href="#">打印</a></li>
					<li><a class="storage" href="#">网络硬盘</a></li>
					<li><a class="rss" href="#">订阅</a></li>
					<li><a class="chat" href="#">聊天</a></li>
					<li><a class="query" href="javascript:void(0);" onclick="showOrHideControl(this);">查询</a></li>
				</ul>
			</div>
		</div>
		<div class="tabs_2 nwarp">
        	<ul class="nwarp">
				<li class="selected"><a href="#"><span>公休申请</span></a></li>
				<li><a href="findHolHolidayApplyByPage.action"><span>公休查询</span></a></li>
				<s:if test="#request.showStatus==true">
					<li><a href="findHolHolidayApplyByPageForDept.action"><span>部门公休</span></a></li>
	           		<li><a href="findHolHolidayApplyByPageForApprove.action?holState=55"><span>公休审批</span></a></li>
				</s:if>
            </ul>
        </div>
		
		<div class="filter">
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<s:form action="saveHolidaysApply" name="saveHolidaysApply" namespace="/holHolidaysApply" id="form">
        		<input type="hidden" name="holState" id="holState" value="58">
        		<input type="hidden" name="holDaysArray" id="holDaysArray" value="58">
	       	  <table width="100%"  class="table_1">
	          	
	         	<tbody>
	         		<s:if test="#request.ayylying=='yes'">
	         			<tr>
		                	<td class="t_r lableTd red" style="color: red;">请注意</td>
		                    <td colspan="5" style="width:85%;color: red;">
		                    	您已有初始保存的或正在申请中的公休申请，不能再次申请！		                    	  
		                    </td>
		                </tr>
	         		</s:if>
	         		<tr>
	                	<td class="t_r lableTd" ></td>
	                    <td colspan="5" style="width:85%;">
	                    	您本年度公休天数为：
	                    	<s:if test="#request.thisYearHolHoliday==null">0</s:if>
	                    	<s:else><s:property value="#request.thisYearHolHoliday.holDays"/></s:else>天， 
	                    	   当前可用公休天数为：<s:property value="#request.allDaysLeft"/> 天。  
	                    	<s:if test="#request.lastYearCount!=null">其中去年剩余天数：<s:property value="#request.lastYearCount"/> 天。</s:if>
	                    	<s:if test="#request.yearBeforeLastYearCount!=null">其中前年剩余天数：<s:property value="#request.yearBeforeLastYearCount"/> 天。</s:if>
	                    	  
	                    </td>
	                </tr>
	            	<tr>
	                	<td class="t_r lableTd" style="width:15%;">领导审批</td>
	                    <td colspan="5" style="width:85%;">
	                    	<s:if test="#request.leaders!=null && #request.leaders.size>0">
	                    		<input id="leaderId" name="leaderId" type="hidden" value="<s:property value='#request.leaders.get(0).id'/>">
	                    		<input id="leaderName" name="leaderName" type="text" id="leader" name="hdate" class="input_small" value="<s:property value='#request.leaders.get(0).name'/>" readonly="readonly"/>&nbsp;&nbsp;<input type="button" onclick="showPickLeader();" class="input_small" value="选择"/>&nbsp;&nbsp;<!-- <input type="button" class="input_small" value="清空" onclick="clearLeader();"/> -->
	                    	</s:if>
	                    </td>
	                </tr>
	                <tr>
	                	<td class="t_r lableTd" style="width:15%;">
	                	<!-- <img alt="添加" src="<%=basePath %>/annualLeave/images/fuhao.jpg" style="float: right;padding-top: 2px;" onclick="addDp();">-->公休范围</td>
	                    <td colspan="5" style="width:85%;" id="holDaysArea">
	                    	<input type="text" name="startDate" id="startDate" class="input_small" readonly="readonly"/>-<input type="text" name="endDate" id="endDate" class="input_small" readonly="readonly"/>
	                    	<input type="button" value="确认" id="setBt" onclick="setHoldays();">
	                    </td>
	                </tr>
	                 <tr>
	                	<td class="t_r lableTd" style="width:15%;">公休日期</td>
	                    <td colspan="5" style="width:85%;word-break:break-all;" id="daysArea"></td>
	                </tr> 
	                <tr>
	                	<td class="t_r lableTd" style="width:15%;">公休天数</td>
	                    <td colspan="5" style="width:85%;" id="allDays">0天</td>
	                </tr> 
	                 <tr>
	                	<td class="t_r lableTd" style="width:15%;">申请说明<br/>(限100字)</td>
	                    <td colspan="5" style="width:85%;">
	                    	<textarea rows="3" cols="3" id="holComment" name="holComment" style="width: 400px;" maxlength="100"></textarea>
	                    </td>
	                </tr>
                    <tr>
                        <td class="t_r lableTd" style="width:15%;">通知人员</td>
                        <td colspan="5" style="width:85%;">
                            <input type="hidden" name="noticeIdList" id="noticeIdList" value="">
                            <input name="noticeNameList" type="text" id="noticeNameList" value="" readonly="readonly">  <input type="button" name="btn" value="选择" onclick="noticeTo()" class="btn" style="display:inline;"> <input type="button" name="btn" value="清除" onclick="clears();" class="btn" style="display:inline;">
                        </td>
                    </tr>
                    <!--
                      <tr>
                        <td class="t_r lableTd" style="width:15%;">手机短信通知<br>请输入手机号码</td>
                        <td colspan="5" style="width:85%; padding-top: 7px;">
                            <INPUT id="mobile" name="sjhm" type="text" width="100%;" /><span style="display:inline; color:red;">&nbsp;&nbsp;&nbsp;(多个号码请用逗号分隔)</span>
                        </td>
                    </tr>
                    -->
	                <s:if test="#request.lastYearCount!=null">
						<tr>
		                	<td class="t_r lableTd" style="width:15%;">备注</td>
		                    <td colspan="5" style="width:85%;">
		                    	<s:if test="#request.yearBeforeLastYearCount!=null">
		                    		您前年有<s:property value="#request.yearBeforeLastYearCount"/> 天公休未休,请在<s:property value="#request.yearBeforeLastYearEndDate"/>前休完,之后未休完公休将自动清零<br/>
		                    	</s:if>
		                    	<s:if test="#request.lastYearCount!=null">
		                    		您去年有<s:property value="#request.lastYearCount"/> 天公休未休,请在<s:property value="#request.lastYearEndDate"/>前休完,之后未休完公休将自动清零<br/>
		                    	</s:if>
		                    </td>
		                </tr>	                
	                </s:if>
	                
	                
				</tbody>
				
	            <tr class="tfoot">
            		<td colspan="6" class="t_r">
            			<s:if test="#request.ayylying!='yes'">	
            				<!-- <input type="submit"" value="保存" onclick="return check('58');"/> -->
							&nbsp;<input type="button" value="提交审核" onclick="check('55')" id="submitBt"/>&nbsp;
						</s:if>
					</td>
	            </tr>
	          </table>
       		</s:form>
		</table>
		</div>
		<!--Table End-->
	</div>
</body>
</html>
