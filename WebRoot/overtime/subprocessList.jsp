<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>合同审批查询</title>
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
<script src="../js/high.js"></script>
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../js/xmlhttprequest.js"></script>
<script type="text/javascript">
			$(function(){ $('#High').high();});
		</script>
<script type="text/javascript">
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
}  
</script>
<script type="text/javascript">
        
        
        	//linePick();
       
        
        
        
        
        
        //跳转到制定页
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
        
         //点击取消按钮后 清空所有数据
      function clearInput(){
      
      		//清空时间
      		$("#beginDate").attr("value","");
      		$("#endDate").attr("value","");
      		//清空关键字
      		$("#sender").attr("value","");
      		
      		//清空责任部门
      		$("#title").attr("value","");
      		
      		
      }
        
        
        
     
      
	function trimStr(html){
		if(html.length>7){
			return (html.substr(0,7)+"...");
		}
		return html
	}

	$(document).ready(function(){
		$("td[id=date]").each(function(index){
			var text = $(this).text();
			var subText = text.substring(11,16);
			
			if(subText=="00:00"){
				text =  text.substring(0,10);
				text += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				$(this).html(text);
			}
		})
	})
      
  $(document).ready(function(){
  $(":button").click(function(){
  document.getElementById("processstatus").options[0].selected=true;
    $(":text").attr("value","");
  });
}); 
    </script>



</head>

<body>
	<iframe id="iframe" style="display:none;"></iframe>



	<div class="main">
		<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl">
				<img src="../images/sideBar_arrow_left.jpg" width="46" height="30"
					alt="收起" onclick="crossDomainShowOrHide();">
			</div>
			<div class="posi fl">
            	<ul>
                	<li><a href="#">我的事物</a></li>
                	<li><a href="#">个人事物</a></li>
                    <li><a href="#">我的超时监控</a></li>
                    <li class="fin">合同审批查询</li>        
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
		<!--Ctrl End-->
		<!--Tabs_2-->
		<div class="tabs_2">
			<ul id="urlist" style="float:left">
				<li><a href="showReceiveList.action"><span>收文查询</span> </a>
				</li>
				<li><a href="showSendList.action"><span>发文查询</span> </a>
				</li>
				<li><a href="showReceiveDirectiveList.action"><span>呈批查询</span>
				</a>
				</li>
				<li class="selected"><a href="showSubprocessList.action"><span>合同审批查询</span>
				</a>
				</li>
				<li><a href="showTaskList.action"><span>直接备案查询</span> </a>
				</li>
				<li><a href="showIncidentsList.action"><span>维保合同审批查询</span>
				</a>
				</li>
				<li ><a href="showHtXxList.action"><span>合同审批查询（旧）</span>
				</a>
				</li>
			</ul>


		</div>
		<!--Tabs_2 End-->
		<!--Filter-->

		<div class="filter">

			<div class="query p8">
				<s:form action="showSubprocessList" id="form" name="/showSubprocessList"
					namespace="/overtime" method="post">
					<input type="hidden" id="caseType" name="caseType"
						value="<s:property value='msgUsermessage.caseType'/>">
					<input type="hidden" id="msgType" name="msgType"
						value="<s:property value='msgUsermessage.msgType'/>">
					<input type="hidden" value="" name="number" id="pageNo" />




					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="t_r" style="white-space:nowrap;">计划编号</td>
							<td><input type="text" id="sender" name="htxx.planNum"
								class="input_large" value="<s:property value="htxx.planNum"/>" />
							</td>
							<td class="t_r" style="white-space:nowrap;">合同编号</td>
							<td><input type="text" id="sender" name="htxx.contractNum"
								class="input_large"
								value="<s:property value="htxx.contractNum"/>" /></td>
							<td class="t_r" style="white-space:nowrap;">自用编号</td>
							<td><input type="text" id="sender" name="htxx.selfNum"
								class="input_large" value="<s:property value="htxx.selfNum"/>" />
							</td>
						</tr>

						<tr>
							<td class="t_r" style="white-space:nowrap;">合同名称</td>
							<td><input type="text" id="sender" name="htxx.contractName"
								class="input_large"
								value="<s:property value="htxx.contractName"/>" /></td>
							<td class="t_r" style="white-space:nowrap;">工程编号</td>
							<td><input type="text" id="sender" name="htxx.projectNum"
								class="input_large"
								value="<s:property value="htxx.projectNum"/>" /></td>
							<td class="t_r" style="white-space:nowrap;">对方单位</td>
							<td><input type="text" id="sender" name="htxx.signCop"
								class="input_large" value="<s:property value="htxx.signCop"/>" />
							</td>
						</tr>


						<tr>
							<td class="t_r" style="white-space:nowrap;">登记日期</td>
							<td style="white-space:nowrap;"><input type="text"
								id="beginDate" name="htxx.beginDate" style="width:116px"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly: true})"
								value="<s:property value="htxx.beginDate"/>" readonly="readonly" />
								至<input type="text" id="endDate" name="htxx.endDate"
								style="width:116px"
								onClick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly: true})"
								value="<s:property value="htxx.endDate"/>" readonly="readonly" />
							</td>

							<td class="t_r" style="white-space:nowrap;">登记人</td>
							<td><input type="text" id="sender" name="htxx.addPerson"
								class="input_large" value="<s:property value="htxx.addPerson"/>" />
							</td>

							<td class="t_r" style="white-space:nowrap;">合同价</td>
							<td><input type="text" id="sender" name="htxx.contractMoney"
								class="input_large"
								value="<s:property value="htxx.contractMoney"/>"
								onkeyup="if(isNaN(value))execCommand('undo')"
								onafterpaste="if(isNaN(value))execCommand('undo')" /></td>
						</tr>

						<tr>
							<td class="t_r" style="white-space:nowrap;">合同状态</td>
							<td><select id="processstatus" name="htxx.statusHt">
									<option value="">全部</option>
									<option value="0">过程中</option>
									<option value="1">预归档</option>
									<option value="99">已终止</option>
							</select>
							</td>
							<td class="t_r" style="white-space:nowrap;">超时天数</td>
							<td colspan="3"><input type="text" id="sender"
								name="htxx.overTime" class="input_large"
								value="<s:property value="htxx.overTime"/>"
								onkeyup="this.value=this.value.replace(/[^\d]/g,'') " />
							</td>



						</tr>



						<tr>
							<td colspan="6" class="t_c"><input type="submit"
								value="查  询" />&nbsp;&nbsp; <input type="button" value="重 置" />
							</td>
						</tr>
					</table>
				</s:form>
			</div>

		</div>

		<!--Filter End-->
		<!--Table-->
		<s:set name="r" value="#request.result"></s:set>
		<div class="mb10">
<table width="100%" class="table_1">
					<thead>
						<th colspan="15">
							消息列表
						</th>
					</thead>
					<tbody>
						<tr class="tit">
							<td>
								超时天数
							</td>
							<td>
								计划编号
							</td>
							<td>
								合同编号
							</td>
							<td>
								自用编号
							</td>
							<td>
								合同名称
							</td>
							<td>
								工程编号
							</td>
							<td>
								对方单位
							</td>
							<td>
								登记日期
							</td>
							<td>
								登记人
							</td>
							<td>
								合同价（万元）
							</td>
							<td>
								合同价状态
							</td>
							<td>
								表单
							</td>
							<td>
								监控
							</td>
						</tr>

						<s:iterator value="#r.list" id="items" status="s">

						<tr>

							<td class="t_c" style="width:150px;"><img
								src="../images/task_exp.gif"></img> <font color="red"><b>(超时<s:property
											value="#items[1]" />天)</b> </font></td>
							<td class="t_c" style="width:150px; text-align:center;"><s:property
									value="#items[5]" />
							</td>
							<td class="t_c" style="width:150px; text-align:left;"><s:property
									value="#items[9]" />
							</td>
							<td class="t_c" style="width:150px;text-align:left;"><s:property
									value="#items[44]" />
							</td>
							<td class="t_c" style="width:150px;text-align:left;"><s:property
									value="#items[6]" />
							</td>
							<td class="t_c" style="width:150px ; text-align:left"><s:property
									value="#items[10]" />
							</td>
							<td class="t_c" style="width:150px;text-align:left;"><s:property
									value="#items[46]" />
							</td>
						<td class="t_c" style="width:150px;text-align:center;"><s:property
									value="#items[16]" />
							</td>
							<td class="t_c" style="width:150px;text-align:left;"><s:property
									value="#items[15]" />
							</td>

							<td class="t_c" style="width:150px;text-align:center;"><s:property
									value="#items[11]" />
							</td>

							<td class="t_c" style="width:150px;text-align:center;"><s:if
									test="#items[49]==0">
											过程中
									  </s:if> <s:elseif test="#items[49]==1">
											预归档
									  </s:elseif> <s:elseif test="#items[49]==99">
											已终止
									  </s:elseif></td>
							<td class="t_c" style="width:150px;text-align:center;"
								align="center"><a
								style="cursor:pointer; text-align:center;"
								onclick="return openForm1('','<s:property value="#items[37]"/>','<s:property value="#items[34]"/>','<s:property value="#items[37]"/>','<s:property value="#items[34]"/>')"><img
									src="../images/open.gif" /> </a></td>
							<td class="t_c" style="width:150px; text-align:center;"
								align="center"><a style="cursor:pointer;text-align:center;"
								onclick="return openScan1('<s:property value="#items[37]"/>','<s:property value="#items[34]"/>')"><img
									src="../images/but9.gif" /> </a></td>
						</tr>
						</s:iterator>

					</tbody>

					<tr class="tfoot">
						<td colspan="15">
							<div class="clearfix">
								<span class="fl"><s:property value="#r.pageInfo.totalRow" />条记录,当前显示<s:property value="#request.startIndex"/>-<s:property value="#request.endIndex"/>条记录</span>
								<ul class="fr clearfix pager">
									<li>
										Pages:
										<s:property value="#r.pageInfo.currentPage" />
										/
										<s:property value="#r.pageInfo.totalPage" />
										<input type="hidden"
											value="<s:property value='#r.pageInfo.totalPage'/>"
											id="totalPageCount">
										<input type="hidden"
											value="<s:property value='#r.pageInfo.currentPage'/>"
											id="currentNumber">
										<input type="text" id="number" name="pageNumber" min="0"
											max="999" step="1" class="input_tiny"
											value="<s:property value='#r.pageInfo.currentPage'/>" />
										<input type="button" name="button" id="button" value="Go"
											onclick="goPage(0,0)">
									</li>

									<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
										<li>
											<a href="javascript:void(0)">&gt;&gt;</a>
										</li>
									</s:if>
									<s:else>
										<li>
											<a href="javascript:void(0)"
												onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a>
										</li>
									</s:else>

									<li>
										<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
											<a href="javascript:void(0)">下一页</a>
										</s:if>
										<s:else>
											<a href="javascript:void(0)"
												onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
										</s:else>
									</li>
									<li>
										<s:if test="#r.pageInfo.currentPage==1">
											<a href="javascript:void(0)">上一页</a>
										</s:if>
										<s:else>
											<a href="javascript:void(0)"
												onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
										</s:else>

									</li>

									<s:if test="#r.pageInfo.currentPage==1">
										<li>
											<a href="javascript:void(0)">&lt;&lt;</a>
										</li>
									</s:if>
									<s:else>
										<li>
											<a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a>
										</li>
									</s:else>


								</ul>
							</div>
						</td>
					</tr>
				</table>

			
		</div>


		<!--Table End-->
	</div>
</body>
<script>
		$(function(){
		
		var now = new Date();  
		var year = now.getFullYear();
		for(i=2008 ;i<=year;i++){
			$("#year").append('<option value="'+i+'">'+i+'</option>');	
		}
		
		var processStatus ='${processstatus}';
		$("#processstatus").attr('value',processStatus);
				
	});
	
	
	function openForm1(task_user_name,model_id,instance_id,processName,pinstance_id){
		var url ='http://10.1.44.18/'; 
		var urlTaskId = '../common/ajax!getFirstTaskId.action?processname='+encodeURI(processName)+'&incident=' + encodeURI(pinstance_id)+ '&aa=' + new Date().getTime();
		
		//alert(urlTaskId);
		var loginName = 'ST${loginName}'
		var task_id = doRequest(urlTaskId);
		url += '/openflowform.action';
		url +="?task_id="+encodeURI(task_id);
		url +="&task_user_name="+ encodeURI(loginName);
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
		var rtn = window.open(url);
		return false
	}
		
	function openScan1(processName,pinstance_id){
		var url1 = "";
		var urlTaskId = '../common/ajax!getFirstTaskId.action?processname='+encodeURI(processName)+'&incident=' + encodeURI(pinstance_id)+ '&aa=' + new Date().getTime();
		
			var task_id = doRequest(urlTaskId);
			url1 = 'http://${ultimusip}';
			url1 +='/sLogin/workflow/TaskStatus.aspx?TaskID=' + encodeURI(task_id);
			window.open(url1);
			return false; 
	}


</script>
</html>
