<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资产盘点任务管理</title>
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


$(function(){
	$("input[name=start1]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=start2]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=end1]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	$("input[name=end2]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});
	
});
    
</script>
 
 
<script type="text/javascript">
//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		window.location.href = "showListManage.action";
		clearInterval(refresh);
	}
}

//新增--先跳转到assetList页面
function showAssetSearchPage(){
	sonWindow = window.open('showAssetSearchPage.action');
}

//跳转到编辑页面
function showEditPage(id){
	sonWindow = window.open('showEdit.action?id='+id);
}

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
	$("#search table tr td input:lt(5)").val("");
	$("#pageNo").val(1);
}

</script> 
 
 
 
<script type="text/javascript">
//删除
function deleteData(id){
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'delete.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("form").submit();
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
$(function(){
	 $("img").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 );
});

</script>
 
 
 
 
</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">资产管理</a></li>
				<li class="fin">资产盘点任务管理</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl(this);">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8" id="search">
			<s:form action="showListManage" name="task" namespace="/task" method="post" id="form">
			<input type="hidden" name="showOrHide" value="<s:property value='#request.showOrHide'/>"/>
			<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
			<input type="hidden" name="checkpersonlist" value="<s:property value="#request.userId"/>">
			<input type="hidden" name="taskuser" value="<s:property value="#request.userId"/>">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr id="search">
						<td class="t_r" width="10%;" >任务名称</td>
						<td width="20%;">
							<input type="text" name="taskname" id="textfield" class="input_large" value="<s:property value='assetTask.taskname'/>" />
						</td>
						<td class="t_r" width="10%:">任务开始时间</td>
						<td width="25%;">
							<input type="text" name="start1" class="input_small" value="<s:property value='#request.start1'/>" readonly="readonly">至
							<input type="text" name="start2" class="input_small" value="<s:property value='#request.start2'/>" readonly="readonly">
						</td>
						<td class="t_r" width="10%;" >任务结束时间</td>
						<td width="25%;">
							<input type="text" name="end1" class="input_small" value="<s:property value='#request.end1'/>" readonly="readonly">至
							<input type="text" name="end2" class="input_small" value="<s:property value='#request.end2'/>" readonly="readonly">
						</td>
					</tr>
					<tr>
						<td colspan="6" class="t_c">
							<input type="submit" value="查询" />&nbsp;&nbsp; 
							<input type="button" value="重 置" onclick="clearSearch();"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			<div class="fn">
				<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAssetSearchPage();">
				<input type="button"" name="button2" id="button2" value="上传盘点任务" class="new" onclick="javascript:window.open('showUploadPage.action')">
				<!--<input type="button"" name="button2" id="button2" value="生成盘点xml" class="new" onclick="javascript:window.open('generateXML.action')">-->
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="14">盘点任务列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td>序号</td>
					<td>任务名称</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>任务状态</td>
					<!--<td>出错数量</td>-->
					<td>完成率</td>
					<td>盘点结果</td>
					<td colspan="3">操作</td>
				</tr>
				<s:iterator value="#request.page.result" id="task" status="st">
					<tr class="t_c">
						<td class="t_c"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_c"><s:property value="#task.taskname"/></td>
						<td class="t_c"><s:property value="#task.starttime"/></td>
						<td class="t_c"><s:property value="#task.endtime"/></td>
						<td class="t_c">
							<s:if test="#task.endtime >= #request.today">进行中</s:if>
							<s:else>已过期</s:else>
						</td class="t_c">
						<!-- 
						<td><s:property value="#task."/></td>
						 -->
						<td class="t_c"><s:property value="#request.finishRate[#st.index]"/>%</td>
						<td class="t_c">
							<s:if test="#request.finishRate[#st.index]!=0">
								<a href="showCheckResult.action?id=<s:property value="#task.id"/>" style="float:inline;" target="_blank">查看结果</a>
							</s:if>
						</td>
						<!-- 
						<td class="t_c">
							<s:if test="#request.finishRate[#st.index]!=0">
								<a href="showView.action?id=<s:property value="#task.id"/>" style="float:inline;" target="_blank">盘点结果</a>
							</s:if>
						</td> -->
						<td class="t_c">
							<a href="showView.action?id=<s:property value="#task.id"/>" style="float:inline;" target="_blank">任务详情</a>
						</td>
						<td class="t_c">
							<a href="downloadAssetTaskAsXML.action?paramId=<s:property value="#task.id"/>" style="float:inline;">下载xml</a>
						</td>
						<td class="t_c">
							<a href="downloadAssetTaskAsTXT.action?paramId=<s:property value="#task.id"/>" style="float:inline;">下载盘点rar</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="15"><div class="clearfix">
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
			            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
			            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
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
		       </div></td>
		     </tr>
		</table>
		
		</div>
		<!--Table End-->
	</div>
</body>
</html>
