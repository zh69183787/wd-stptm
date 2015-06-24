<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
					<li><a href="#">我的事务</a>
					</li>
					<li><a href="#">公共事务</a>
					</li>
					<li class="fin">个人通讯录</li>
				</ul>
			</div>

		</div>

		<s:form action="showAddPersonAddressList" method="post">
			<table width="100%" class="table_1">
				<thead>
					<th colspan="15">添加个人通讯录</th>
				</thead>
				<tbody>

					<tr>
						
						<td width="10%;" class="t_r lableTd" style="white-space: nowrap;">姓名</td>
						<td width="40%;">
						<input type="text" id="" name="person.name"
							class="input_large" value="" maxlength="20" />
							</td>
						
						<td width="10%;" class="t_r lableTd" style="white-space: nowrap;">电子邮箱</td>
						<td width="40%;"><input type="text" id="" name="person.email"
							class="input_large" value="" maxlength="20" /></td>
					</tr>
					
					<tr>
						<td  width="10%;"  class="t_r lableTd" style="white-space: nowrap;">手机</td>
						<td width="40%;"><input type="text" id="" name="person.mobil"
							class="input_large" value="" maxlength="20" /></td>
						<td  width="10%;" class="t_r lableTd" style="white-space: nowrap;">单位电话</td>
						<td width="40%;"><input type="text" id="" name="person.unitTel"
							class="input_large" value="" maxlength="20" /></td>
					</tr>
					
					<tr>
						<td width="10%;" class="t_r lableTd" style="white-space: nowrap;">单位传真</td>
						
						<td width="40%;"><input type="text" class="input_large" id="" name="person.unitFax" value=""
							maxlength="20" /></td>
							
							
						<td width="10%;" class="t_r lableTd" style="white-space: nowrap;">部门名称</td>
						
						<td width="40%;"><input type="text" id="" name="person.unitName"
							class="input_large" value="" maxlength="20" /></td>
					</tr>
				</tbody>
				<tr class="tfoot" align="center">
					<td colspan="6" class="t_r"><input type="submit" value="确 认" />&nbsp;
						<input type="button" class="btn" value="返回"
						onclick="window.history.back()" /> &nbsp; &nbsp;</td>
				</tr>
			</table>
		</s:form>
</body>

<script type="text/javascript">

//重置
function clearInput(){
	$("tbody input:lt(7)").val("");
	$("textarea").val("");
}


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
</script>


</html>
