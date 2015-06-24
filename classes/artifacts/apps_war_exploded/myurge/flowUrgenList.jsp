<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>我的催办列表</title>
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
<!--<script src="../js/iepng.js"></script>-->
<script src="../js/jquery.formalize.js"></script>
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/xmlhttprequest.js"></script>
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
        
        $(document).ready(function(){
			$("option[id^=choice]").each(function(){
				if($(this).val()==$("#hideGMD").val()){
					$(this).attr("selected",true);
				}
			});        
			   	
        })
        
        //定义子窗口的名字
        var newWindow = ""; 
        //每3秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				$("#form").submit();
				clearInterval(refresh);
			}
		}
        
       //打开新增页面
		function showAddPage(){
			newWindow = window.open("showAddPage.action?flowId="+$("select[name=flowId]").val());
		}
		
		//打开编辑页面
		function showEditPage(id){
			newWindow = window.open('showEditPage.action?id='+id);
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
        
        //清除数据
        function clearData(){
        	$("#priorities").val("");
        	$("#processname").val("");
        	$("#summary").val("");
        	$("#username").val("");
        	$("#deptname").val("");
        	
        }
        
        
        function checkForm(){
        	        	
        	$("#pageNo").val(1);
        	return true;
        }
        
        //删除
       
      
</script>
<script>
	function openForm1(task_user_name,model_id,instance_id,processName,pinstance_id){
		var url =''; 
		var urlTaskId = '../common/ajax!getFirstTaskId.action?processname='+encodeURI(processName)+'&incident=' + encodeURI(pinstance_id)+ '&aa=' + new Date().getTime();
		var task_id = doRequest(urlTaskId);
			if((processName=='非招标合同审批流程')||(processName=='非招标合同审批流程直接备案')||(processName=='非招标合同审批会签子流程')||(processName=='非招标合同审批会签确认子流程')||(processName=='非招标合同审批领导审批子流程'))
			{
					url = 'http://';
					url +='/slogin/workflow/OpenForm.aspx?TaskID=' + encodeURI(task_id) + '&UserName='+encodeURI(task_user_name);
			}else{
				url += '/openflowform.action';
				url +="?task_id="+encodeURI(task_id);
				url +="&task_user_name="+ encodeURI(task_user_name);
				if (model_id == ''){
					url +="&model_id=" + encodeURI(processName);
				}else{
					url +="&model_id=" + encodeURI(model_id);
				}

				if (instance_id == ''){
					url +="&instance_id="+ encodeURI(pinstance_id);
				}else{
					url +="&instance_id="+ encodeURI(instance_id);
				}
				url +="&step_name=aa";
				url +="&pinstance_id=" + encodeURI(pinstance_id);
				url +="&processName=" + encodeURI(processName);
				url +="&task_type=1" ;
			}
			//alert(url);
		/*
		else{
			url = '/slogin/workflow/OpenForm.aspx';
			url += '?TaskID=' + task_id;
			url += '&UserName=' + task_user_name;
		}
		*/
		rtn = window.open(url);
		
		return false
	}
	function openScan1(processName,pinstance_id){
		
		var url1 = "";
		var urlTaskId = '../common/ajax!getFirstTaskId.action?processname='+encodeURI(processName)+'&incident=' + encodeURI(
		pinstance_id) + '&aa=' + new Date().getTime();
		var task_id = doRequest(urlTaskId);
		url1 =  'http://10.1.44.17';
// 		url1 +='/sLogin/workflow/TaskStatus.aspx?TaskID=' + encodeURI(task_id);
		url1 +='/sLogin/workflow/TaskStatus.aspx?TaskID=' + encodeURI(task_id);
		window.open(url1);
		return false; 
	}

	function sendMsgLeader1(processname,incident){
		var processname1 = encodeURI(processname);
		var incident1 = encodeURI(incident);
		var url = "/apps/myurge/sendMsgLeader.action?processName="+processname1+"&incident="+incident1+"&num="+Math.random();
		//alert(url);
		var left = (window.screen.width-400)/2;
		var top = (window.screen.height-500)/2;
		window.showModalDialog(url,window,'resizable:yes;dialogWidth:550px;dialogHeight:325px;');
		//window.open(url, "window", "Width=550,Height=325,toolbar=no,menubar=no,status=yes,scrollbars=yes");
		return false;
	}
	
	
	function sendMsg(processname,incident,taskid,process_status){
		//alert(processname);
		var processname = encodeURI(processname);
		var incident = encodeURI(incident);
		var taskid = encodeURI(taskid);
		var process_status = encodeURI(process_status);
		var user = "";
		user = encodeURI(user);
		//var url = "/urge/cuiBan.jsp?processname="+processname+"&incident="+incident+"&taskid="+taskid+"&user="+user+"&num="+Math.random()+"&type=1";
		var url = "/apps/myurge/sendMsgPerson.action?processName="+processname+"&incident="+incident+""+"&num="+Math.random();
		//alert(url);
		var left = (window.screen.width-400)/2;
		var top = (window.screen.height-500)/2;
		window.showModalDialog(url,window,'resizable:yes;dialogWidth:460px;dialogHeight:300px;');
		return false;
	}
	function onAddOver(obj,processname,incident,status){
		if(status == '1'){
			if(obj != null){
				var tr = getParentElement(obj,'tr');
				if(tr != null){
					var processname = encodeURI(processname);
					var incident = encodeURI(incident);
					var url = "/getProcessingPersons.action?processname="+processname+"&incident="+incident+"&num="+Math.random();
					var str = '当前处理人:\n'+doRequest(url);
					obj.title = str;
					//alert(str);
				}
			}
		}
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
	<li><a href="#">我的催办列表</a></li>
	<li class="fin">查看</li>
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

<!--Tabs_2 End--> <!--Filter-->

<!--Filter End--> <!--Table-->
<div class="mb10">
<table width="100%" class="table_1">
	<thead>
		<th colspan="15">催办短信记录查询 </th>
	</thead>
	<tbody>
		<tr class="tit">
			<td>流程名称</td>
			<td>摘要</td>
			<td>被催办人</td>
			<td>手机号</td>
			<td>催办时间</td>
			<td>催办人</td>
		</tr>
		 <s:set name="r" value="#request.page"></s:set> 
		<s:iterator value="#r.list" id="items" status="s">
			<tr>
				<td class="t_c"><s:property value="#items[1]"/></td>
				<td class="t_c"><s:property value="#items[12]"/></td>
				<td class="t_c"><s:property value="#items[5]"/></td>
				<td class="t_c"><s:property value="#items[9]"/></td>
				<td class="t_c"><s:property value="#items[6]"/></td>
				<td class="t_c"><s:property value="#items[8]"/></td>
	
			</tr>
		</s:iterator>
	</tbody>
	
	 
	<tr class="tfoot">
                                <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
                           		<span class="fl">
                                  <s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.startNo"/> -
                                  <s:if test="#r.pageInfo.totalPage==#r.pageInfo.currentPage">
                                    <s:property value="#r.pageInfo.totalRow"/>条
                                 </s:if>
                                  <s:else>
                                    <s:property value="#request.endNo"/>条
                                  </s:else>
                                </span>
                                <ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#r.pageInfo.currentPage"/>/<s:property value="#r.pageInfo.totalPage"/>
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.totalPage'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#r.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
	                             	</s:if>
	                             	<s:else>
	                             		<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
	                             	</s:else>
	                             
                                
                            </ul>
                        </div>
                                </td>
                              </tr>
</table>

</div>
<!--Table End--></div>
</body>
</html>
