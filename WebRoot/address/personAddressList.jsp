<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>列表查询</title>
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
function showJsp(){
window.showModalDialog("Open.jsp");
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
      
  
    </script>
</head>

<body>
	<iframe id="iframe" style="display: none;"></iframe>



	<div class="main">
		<!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl">
				<img src="../images/sideBar_arrow_left.jpg" width="46" height="30"
					alt="收起" onclick="crossDomainShowOrHide();">
			</div>
			<div class="posi fl">
				<ul>
					<li><a href="#">我的事务</a></li>
					<li><a href="#">公共事务</a></li>
					<li class="fin">个人通讯录</li>
				</ul>
			</div>
			<!-- 	<div class="fr lit_nav">
				<ul>
					<li class="selected"><a class="print" href="#">打印</a></li>
					<li><a class="storage" href="#">网络硬盘</a></li>
					<li><a class="rss" href="#">订阅</a></li>
					<li><a class="chat" href="#">聊天</a></li>
					<li><a class="query" href="#">查询</a></li>
				</ul>
			</div> -->
		</div>
		<!--Ctrl End-->
		<!--Tabs_2-->

		<!--Tabs_2 End-->
		<!--Filter-->

		<div class="filter">

			<div class="query p8">

				<s:form action="showPersonAddressList" id="form"
					name="/showPersonAddressList" namespace="/address" method="post">

					<input type="hidden" id="caseType" name="caseType"
						value="<s:property value='msgUsermessage.caseType'/>">
					<input type="hidden" id="msgType" name="msgType"
						value="<s:property value='msgUsermessage.msgType'/>">
					<input type="hidden" value="" name="number" id="pageNo" />

					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>


							<td class="t_r" style="white-space: nowrap;">姓名</td>
							<td><input type="text" id="sender" name="person.name"
								class="input_large" value="<s:property value="person.name"/>" />
							</td>

							<td class="t_r" style="white-space: nowrap;">电子邮件</td>
							<td><input type="text" id="sender" name="person.email"
								class="input_large" value="<s:property value="person.email"/>" />
							</td>

							<td class="t_r" style="white-space: nowrap;">手机</td>
							<td><input type="text" id="sender" name="person.mobil"
								class="input_large" value="<s:property value="person.mobil"/>"
								onkeyup="this.value=this.value.replace(/[^\d]/g,'') " />
							</td>
						</tr>

						<tr>
							<td class="t_r" style="white-space: nowrap;">单位电话</td>
							<td><input type="text" id="sender" name="person.unitTel"
								class="input_large" value="<s:property value="person.unitTel"/>"
								onkeyup="this.value=this.value.replace(/[^\d]/g,'') " />
							</td>

							<td class="t_r" style="white-space: nowrap;">单位传真</td>
							<td><input type="text" id="sender" name="person.unitFax"
								class="input_large" value="<s:property value="person.unitFax"/>" />
							</td>




							<td class="t_r" style="white-space: nowrap;">部门名称</td>
							<td><input type="text" id="sender" name="person.unitName"
								class="input_large"
								value="<s:property value="person.unitName"/>" /></td>

						</tr>
						<tr>
							<td colspan="6" class="t_c"><input type="submit" value="检 索" />
								&nbsp;&nbsp; <input type="button" value="重 置" /></td>
						</tr>
					</table>
				</s:form>

			</div>

		</div>

		<!--Filter End-->
		<!-- -->

		<!--Table-->
		<s:set name="r" value="#request.result"></s:set>
		<div class="mb10">
			<table width="100%" class="table_1">
				<thead>
					<th colspan="15">消息列表</th>
				</thead>
				<tbody>
					<tr class="tit">
						<td>姓名</td>
						<td>电子邮件</td>
						<td>手机</td>
						<td>单位电话</td>
						<td>单位传真</td>
						<td>部门名称</td>
						<td colspan="3">操作</td>

					</tr>
					<s:iterator value="#r.list" id="items" status="s">

						<tr>
							<!-- 姓名 -->
							<td class="t_c" style="width: 150px;"><s:property
									value="#items[2]" />
							</td>
							<!-- 电子邮件 -->
							<td class="t_c" style="width: 150px;"><s:property
									value="#items[3]" />
							</td>
							<!-- 手机-->
							<td class="t_c" style="width: 150px; text-align: left;"><s:property
									value="#items[4]" />
							</td>
							<!-- 单位电话 -->
							<td class="t_c" style="width: 150px; text-align: right;"><s:property
									value="#items[10]" />
							</td>
							<!-- 单位传真-->
							<td class="t_c" style="width: 150px;"><s:property
									value="#items[11]" />
							</td>
							<!-- 部门名称-->
							<td class="t_c" style="width: 150px; text-align: left;"><s:property
									value="#items[8]" />
							</td>

							<td class="t_c" style="width: 50px"><a
								href="findByIdPersonAddress.action?id=<s:property value="#items[0]" />">修改</a>
							</td>
							<td class="t_c" style="width: 50px"><a
								href="showToAddPersonAddressList.action">添加</a> <!-- <a href="#" onclick="openwin()">添加</a>  -->
							</td>
							<td class="t_c" style="width: 50px"><a
								class="fl mr5 lnk-del" style="cursor:pointer;">删除</a>
							</td>

						</tr>
					</s:iterator>
				</tbody>

				<tr class="tfoot">
					<td colspan="15">
						<div class="clearfix">
							<span class="fl"><s:property value="#r.pageInfo.totalRow" />条记录,当前显示<s:property
									value="#r.pageInfo.currentPage*20-19" />-<s:property
									value="#r.pageInfo.currentPage*20" />条记录</span>
							<ul class="fr clearfix pager">
								<li>Pages: <s:property value="#r.pageInfo.currentPage" />
									/ <s:property value="#r.pageInfo.totalPage" /> <input
									type="hidden"
									value="<s:property value='#r.pageInfo.totalPage'/>"
									id="totalPageCount"> <input type="hidden"
									value="<s:property value='#r.pageInfo.currentPage'/>"
									id="currentNumber"> <input type="text" id="number"
									name="pageNumber" min="0" max="999" step="1" class="input_tiny"
									value="<s:property value='#r.pageInfo.currentPage'/>" /> <input
									type="button" name="button" id="button" value="Go"
									onclick="goPage(0,0)"></li>

								<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
									<li><a href="javascript:void(0)">&gt;&gt;</a></li>
								</s:if>
								<s:else>
									<li><a href="javascript:void(0)"
										onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a>
									</li>
								</s:else>

								<li><s:if
										test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
										<a href="javascript:void(0)">下一页</a>
									</s:if> <s:else>
										<a href="javascript:void(0)"
											onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
									</s:else></li>
								<li><s:if test="#r.pageInfo.currentPage==1">
										<a href="javascript:void(0)">上一页</a>
									</s:if> <s:else>
										<a href="javascript:void(0)"
											onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
									</s:else></li>

								<s:if test="#r.pageInfo.currentPage==1">
									<li><a href="javascript:void(0)">&lt;&lt;</a></li>
								</s:if>
								<s:else>
									<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a>
									</li>
								</s:else>


							</ul>
						</div></td>
				</tr>
			</table>


		</div>


		<!--Table End-->
	</div>
</body>
<script>
/* 	function del(){    
	  var i=window.confirm("您确定删除吗");      
	  return i;
	  
	} 
	 */
	
	$(function(){
		var msgType="<s:property value='msgUsermessage.msgType'/>";
		var caseType="<s:property value='msgUsermessage.caseType'/>";
		if(msgType=="1"){
			$("#urlist").css("display","inline");
		}
		
		$(document).delegate(".lnk-del", "click", function (e) {
			var event = e || window.event,
			o = (event.srcElement || event.target),
			id=$(o).attr("id");

			if (!confirm("确定删除?")) {
				return;
			}
			
			$.ajax({
				url : 'delPersonAddress.action?id=<s:property value="#items[0]" />',
				data : {
					id : id,
					msgType:msgType
				},

				type : "POST",
				dataType : 'json/xml/html/text',
				cache : false,
				async : false,
				success : function (data) {
					document.forms[0].submit();
				},
				error : function (data) {
					alert("删除成功！");
					document.forms[0].submit();
				}
			});

		});
		
		
		
		
		
		var lst = $("#urlist2 li");
		for(i=0;i<lst.length;i++){
			var o = lst[i];
			var type =$(o).attr("msg-type");
			if(type==msgType){
				$(o).addClass("selected");
			}
		}
		
	
	});
	
	
	function openForm1(task_user_name,model_id,instance_id,processName,pinstance_id){
		var url =''; 
		var urlTaskId = '../common/ajax!getFirstTaskId.action?processname='+encodeURI(processName)+'&incident=' + encodeURI(pinstance_id)+ '&aa=' + new Date().getTime();
		
		alert(urlTaskId);
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


	var popup_dragging = false;
	var popup_target;
	var popup_mouseX;
	var popup_mouseY;
	var popup_mouseposX;
	var popup_mouseposY;
	var popup_oldfunction;
	function popup_display(x) {
		var win = window.open();
		for ( var i in x)
			win.document.write(i + ' = ' + x[i] + '<br>');
	}
	function popup_mousedown(e) {
		var ie = navigator.appName == "Microsoft Internet Explorer";
		if (ie && window.event.button != 1)
			return;
		if (!ie && e.button != 0)
			return;
		popup_dragging = true;
		popup_target = this['target'];
		popup_mouseX = ie ? window.event.clientX : e.clientX;
		popup_mouseY = ie ? window.event.clientY : e.clientY;
		if (ie)
			popup_oldfunction = document.onselectstart;
		else
			popup_oldfunction = document.onmousedown;
		if (ie)
			document.onselectstart = new Function("return false;");
		else
			document.onmousedown = new Function("return false;");
	}
	function popup_mousemove(e) {
		if (!popup_dragging)
			return;
		var ie = navigator.appName == "Microsoft Internet Explorer";
		var element = document.getElementById(popup_target);
		var mouseX = ie ? window.event.clientX : e.clientX;
		var mouseY = ie ? window.event.clientY : e.clientY;
		element.style.left = (element.offsetLeft + mouseX - popup_mouseX)
				+ 'px';
		element.style.top = (element.offsetTop + mouseY - popup_mouseY) + 'px';
		popup_mouseX = ie ? window.event.clientX : e.clientX;
		popup_mouseY = ie ? window.event.clientY : e.clientY;
	}
	function popup_mouseup(e) {
		if (!popup_dragging)
			return;
		popup_dragging = false;
		var ie = navigator.appName == "Microsoft Internet Explorer";
		var element = document.getElementById(popup_target);
		if (ie)
			document.onselectstart = popup_oldfunction;
		else
			document.onmousedown = popup_oldfunction;
	}
	function popup_exit(e) {
		var ie = navigator.appName == "Microsoft Internet Explorer";
		var element = document.getElementById(popup_target);
		popup_mouseup(e);
		element.style.visibility = 'hidden';
		element.style.display = 'none';
	}
	function popup_show() {
		element = document.getElementById('popup');
		drag_element = document.getElementById('popup_drag');
		exit_element = document.getElementById('popup_exit');
		element.style.position = "absolute";
		element.style.visibility = "visible";
		element.style.display = "block";
		element.style.left = (document.documentElement.scrollLeft
				+ popup_mouseposX - 10)
				+ 'px';
		element.style.top = (document.documentElement.scrollTop
				+ popup_mouseposY - 10)
				+ 'px';
		drag_element['target'] = 'popup';
		drag_element.onmousedown = popup_mousedown;
		exit_element.onclick = popup_exit;
	}
	function popup_mousepos(e) {
		var ie = navigator.appName == "Microsoft Internet Explorer";
		popup_mouseposX = ie ? window.event.clientX : e.clientX;
		popup_mouseposY = ie ? window.event.clientY : e.clientY;
	}
	if (navigator.appName == "Microsoft Internet Explorer")
		document.attachEvent('onmousedown', popup_mousepos);
	else
		document.addEventListener('mousedown', popup_mousepos, false);
	if (navigator.appName == "Microsoft Internet Explorer")
		document.attachEvent('onmousemove', popup_mousemove);
	else
		document.addEventListener('mousemove', popup_mousemove, false);
	if (navigator.appName == "Microsoft Internet Explorer")
		document.attachEvent('onmouseup', popup_mouseup);
	else
		document.addEventListener('mouseup', popup_mouseup, false);
		
		
		$(document).ready(function(){
  $(":button").click(function(){
    $(":text").attr("value","");
  });
});
	
	
/* function openwin() 
{ 
window.open ("findByIdPersonAddress.action?id=<s:property value="#items[0]" />", "newwindow", "height=100, width=400, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no") 
}  */
	
/* 	function openwin() 
{ 
window.open ("showToAddPersonAddressList.action", "newwindow", "height=300, width=600, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no") 
} 	 */
</script>


</html>
