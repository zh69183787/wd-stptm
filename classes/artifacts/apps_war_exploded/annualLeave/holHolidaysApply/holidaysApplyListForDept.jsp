﻿<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%String basePath = request.getContextPath(); %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>公休列表</title>
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

<script type="text/javascript">
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
}  
</script>
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

$(function(){
	$("#beginDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("#endDate").datepicker( "option", "minDate", selectedDate );
		}
	});
	$("#endDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true,
		onSelect: function( selectedDate ) {
			$("#beginDate").datepicker( "option", "maxDate", selectedDate );
		}
	});

	
	$("#setEndDate").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
});



//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		window.location.href = "showList.action";
		clearInterval(refresh);
	}
}

//跳转到新增页面
function showAddPage(){
	sonWindow = window.open('showAdd.action');
}

/*
//跳转到编辑页面
function showEditPage(id){
	sonWindow = window.open('showEdit.action?id='+id);
}*/

function goPage(pageNo,type){
	//type=0,直接跳转到制定页
	if(type=="0"){
  		var pageCount = $("#totalPageCount").val();
  		var number = $("#number").val();
  		if(!number.match(/^[0-9]*$/)){
  			alert("请输入数字");
  			$("#number").val("");
  			$("#number").focus();
  			return ;
  		}
  		if(parseInt(number)>parseInt(pageCount)){
  			$("#number").val(pageCount);
  			$("#pageNo").val(pageCount);
  		}else{
  			$("#pageNo").val(number);
  		}
  	}
	//type=1,跳转到上一页	       
    if(type=="1"){
    	$("#pageNo").val(parseInt($("#number").val())-1);
    }
	//type=2,跳转到下一页	       
    if(type=="2"){
   		$("#pageNo").val(parseInt($("#number").val())+1);
    }
     //type=3,跳转到最后一页,或第一页
    if(type=="3"){
    	$("#pageNo").val(pageNo);
    }
   	$("#form").submit();
} 

//清除表单数据
function clearSearch(){
	$("#search td input:lt(3)").val("");
	$("select option:first").attr("selected",true);
	$("#pageNo").val(1);
}




</script> 
 
 
 
<script type="text/javascript">
//删除
function deleteData(id){
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : "<%=basePath%>/holidays/deleteHoliday.action?random="+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("#form").submit();
			}
		});
	}
}

$(function(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide")
		$("#search").css("display","none");
	else
		$("#search").css("display","block");


	//loadShow();
});

//控制显示或隐藏查询条件
function showOrHideControl(obj){
	var status = $("input[name=showOrHide]").val();
	var $li = $(obj).parent();
	if(status=="hide"){
		$li.addClass("selected");
		$("#search").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
		$li.removeClass("selected");
		$("#search").slideUp();
		$("input[name=showOrHide]").val("hide");
	}
}

//显示编辑
function showEdit(target,id){
	var origin = $(target).html();
	var addHtml='<input type="text" name="days" value="'+origin+'" class="input_tiny"><input id=="submit" type="button" value="提交" onclick=\"updateholDays(this,\''+id+'\')\"/>'+
		'<input is="cancel" type="button" value="取消" onclick=\"hideEdit(this,\''+origin+'\')\"/>';
	var width = $(target).width();
	$(target).html(addHtml);
	$(target).width(width);
}
//隐藏编辑
function hideEdit(target,value){
	$(target).parent().html(value);
}
//更新年假天数
function updateholDays(target,id){
	var $input = $(target).parent().find('input[type="text"]');
	var days = $input.val();
	if(days.indexOf('.')!=-1 || !$.isNumeric(days)){
		alert('请输入数字！');
		$input.focus();
		return ;
	} 
	$.ajax({
		url:'updateHolHolidays.action',
		data:{
			id:id,
			days:days
		},
		success:function(){
			alert('更新成功！');
			$(target).parent().html(days);
		}
	});
}
function showSetEnd(){
	$.ajax({
		url:'findHolHolidaySettime.action',
		dataType:'json',
		success:function(data){
			$('#overSelect').find('option[value="'+data.overyear+'"]').attr('selected',true);
			$('#monthSelect').find('option[value="'+data.month+'"]').attr('selected',true);
			$("#cover").show();
		}
	});
	
}
function hideSetEnd(){
	$("#cover").hide();
}
function updateHolHolidaySettime(){
	if(confirm('是否确认修改？')){
		$.ajax({
		url:'updateHolHolidaysSettime.action',
		data:{
			overyear:$('#overSelect').val(),
			month:$('#monthSelect').val()
		},
		dataType:'json',
		success:function(data){
			if(data!=null && data.message=='success'){
				alert('修改成功');
				$("#cover").hide();
			}
		}
	});
	}
}
</script>
 
 
 
 
</head>

<body>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img src="<%=basePath%>/images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
			<div class="posi fl">
				<ul>
					<li><a href="#">公休事务</a></li>
					<li class="fin">部门公休</li>
				</ul>
			</div>
			<div class="fr lit_nav" >
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
				<li><a href="showHolHolidaysApplyAdd.action"><span>公休申请</span></a></li>
				<li><a href="findHolHolidayApplyByPage.action"><span>公休查询</span></a></li>
				<li class="selected"><a href="findHolHolidayApplyByPageForDept.action?"><span>部门公休</span></a></li>
           		<li><a href="findHolHolidayApplyByPageForApprove.action?holState=55"><span>公休审批</span></a></li>
            </ul>
        </div>
		<div class="filter">
			<div class="query p8" id="search">
			<s:form action="findHolHolidayApplyByPageForDept" name="holHolidaysApply" namespace="/holHolidaysApply" method="post" id="form">
			<input type="hidden" name="showOrHide" value="<s:property value='#request.showOrHide'/>"/>
			<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr id="search">
						<td class="t_r">申请人</td>
						<td>
							<input type="text" name="applyName" id="applyName" class="input" value="<s:property value='#request.applyName'/>">
						</td>
						<td class="t_r">公休日期</td>
						<td>
							<input type="text" name="startDate" id="startDate" class="input" value="<s:property value='#request.startDate'/>">
						</td>
						<td class="t_r">公休天数</td>
						<td>
							<input type="text" name="holDays" id="holDays" class="input" value="<s:property value='#request.holDays'/>" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onblur="this.value=this.value.replace(/[^\d]/g,'')">
						</td>
						<td class="t_r">公休状态</td>
						<td>
							<select name="holState">
								<option value="">全部</option>
								<s:if test="#request.holState==55"><option value="55" selected="selected">申请中</option></s:if>
								<s:else><option value="55">申请中</option></s:else>
								
								<s:if test="#request.holState==58"><option value="58" selected="selected">初始保存</option></s:if>
								<s:else><option value="58">初始保存</option></s:else>
								
								<s:if test="#request.holState==59"><option value="59" selected="selected">同意</option></s:if>
								<s:else><option value="59">同意</option></s:else>
								
								<s:if test="#request.holState==60"><option value="60" selected="selected">驳回</option></s:if>
								<s:else><option value="60">驳回</option></s:else>
								
								<s:if test="#request.holState==61"><option value="61" selected="selected">申请取消</option></s:if>
								<s:else><option value="61">申请取消</option></s:else>
								
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="8" class="t_c">
							<input type="submit" value="检 索" />&nbsp;&nbsp; 
							<input type="button" value="重 置" onclick="clearSearch();"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="10">&nbsp;&nbsp;公休列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td style="white-space: nowrap;">序号</td>
					<td style="white-space: nowrap;">申请人</td>
					<td style="white-space: nowrap;">公休日期</td>
					<td style="white-space: nowrap;">公休天数</td>
					<td style="white-space: nowrap;">当前状态</td>
					<td style="white-space: nowrap;">操作</td>
				</tr>
				<s:iterator value="#request.page.result" id="holidayApply" status="st">
					<tr>
						<td class="t_c"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_l"><s:property value="#request.applyUserList[#st.index].name" /></td>
						<td class="t_l"><s:property value="#holidayApply.startDate" /></td>
						<td class="t_c"><s:property value="#holidayApply.holDays"/></td>
						<td class="t_c">
							<s:if test="#holidayApply.holState==55">申请中</s:if>
							<s:elseif test="#holidayApply.holState==58">初始保存</s:elseif>
							<s:elseif test="#holidayApply.holState==59">同意</s:elseif>
							<s:elseif test="#holidayApply.holState==60">驳回</s:elseif>
							<s:elseif test="#holidayApply.holState==61">申请取消</s:elseif>
						</td>
						<td class="t_c" style="width: 60px;">
							<img alt="详细" src="<%=basePath %>/annualLeave/images/folder_open.gif" onclick="window.open('showApplyView.action?id=<s:property value="#holidayApply.id"/>')" style="cursor: pointer;padding-left: 20px;">
						</td>
					</tr>
				</s:iterator>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="14"><div class="clearfix">
			      <s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>
			      <s:else>
			      	<span class="fl">
				      	<s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
					    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
					      	<s:property value="#request.page.totalSize"/>条
					    </s:if>
					    <s:else>
					    	<s:property value="#request.page.start+#request.page.pageSize-1"/>条
						</s:else>
				    </span>
			        <ul class="fr clearfix pager">
			          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
			          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
			            <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
			            <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		           	  </li>
		           	  
		               <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
		               	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		               </s:if>
		               <s:else>
		                <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
		               </s:else>
		                
		              <li>
		              	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
		              		<a href="javascript:void(0)">下一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
		              	</s:else>
		              </li>
		              
		              <li>
		              	<s:if test="#request.page.currentPageNo==1">
		              		<a href="javascript:void(0)">上一页</a>
		              	</s:if>
		              	<s:else>
		              		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
		              	</s:else>
		              </li>
		              
		              <s:if test="#request.page.currentPageNo==1">
		              	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
		              </s:if>
		              <s:else>
		              	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
		              </s:else>
		         </ul>
		       </s:else>
		       </div>
		      </td>
		     </tr>
		</table>
		
		</div>
		<!--Table End-->
	</div>
</body>
</html>
