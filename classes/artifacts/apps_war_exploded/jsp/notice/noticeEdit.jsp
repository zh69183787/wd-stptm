<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>通知内容</title>
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
        	
        	function shut(){
        		window.opener=null;
			  window.open("","_self");
			  window.close();
        	}
		
        </script>
</head>

<body>
<form action="sendMsg.action" name="msgForm" id="msgForm">
 <input type="hidden" id="hretId" value="<s:property value='#request.hretList[0].hretId'/>">
 <input type="hidden" id="pageNo" value="<s:property value='#request.pageNo'/>">
  <input type="hidden" id="pageCount" value="<s:property value='#request.pageCount'/>">
  

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" style="display:run-in;"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">通知内容</a></li>                	
                	<li class="fin">撰写通知</li>
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
			<ul id="urlist2" style="float:right">
         		 <li class="selected"><a  href="#"  ><span>撰写</span></a></li>
				 <li msg-type="1" ><a href="#" onclick="submitFormByMsgType('1')"><span>收件箱</span></a></li>
				 <li msg-type="2" ><a href="#" onclick="submitFormByMsgType('2')"><span>发件箱</span></a></li>
				 <li msg-type="3" ><a href="#" onclick="submitFormByMsgType('3')"><span>已发送</span></a></li>
				 <li msg-type="4" ><a href="#" onclick="submitFormByMsgType('4')"><span>废件箱</span></a></li>
           </ul>
		</div>
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        
        <input type="hidden" id="hrId" name="hrId" value="<s:property value='hrBInfoVO.hrId'/>"> 
        <input type="hidden" id="h_politicalLandscape" name="h_politicalLandscape" value="<s:property value='hrBInfoVO.politicalLandscape'/>">  
        <input type="hidden" id="h_highestDegree" name="h_highestDegree" value="<s:property value='hrBInfoVO.highestDegree'/>"> 
       	  <TABLE cellSpacing="0" cellPadding="0" width="100%" align="center" border="0" class="table_1">
           <thead>
	            <th colspan="4" class="t_z">
	        	      &nbsp;
	        	     
	        	      &nbsp;        	      
	       	      </th>
	          </thead>
              
		<tr>
			<td class="bg" nowrap="nowrap">
			<table cellspacing="1" cellpadding="3" width="100%" align="center" border="0" name = 'jjj'>
				<tr>
					<td align="right" width="1%">
						<input type="radio" name="sendFuns" value="0" checked="checked" onclick="zntz()"/>
					</td>
					<td align="left" nowrap="nowrap" width="5%">
						发送站内通知
					</td>
					<TD class="bg" nowrap="nowrap" align="right" width="150" style="text-align:right;">
						<div id="sendUser">收件人：</div>
						<s:if test="msgUsermessage.empidsend!=null && msgUsermessage.empidsend!=0">
							<input type="hidden" name="SID_LIST" id="SID_LIST" value="<s:property value="msgUsermessage.empidsend"/>">
						</s:if>
						<s:else>
							<input type="hidden" name="SID_LIST" id="SID_LIST" value="">
						</s:else>
					</TD>					
					<TD class="bg" nowrap="nowrap" >
					<div id="sendMsgess">
						<s:if test="#request.sender!=null && #request.sender!=''">
							<input name="txt_ToUser" type="text" id="txt_ToUser"  value="<s:property value="#request.sender"/>" style="display:inline; width:60%" readonly="readonly"/>
						</s:if>	
						<s:else>
							<input name="txt_ToUser" type="text" id="txt_ToUser"  value="" style="display:inline; width:60%" readonly="readonly"/>
						</s:else>						
						<a style="display:inline; cursor:pointer;" href="#" onclick="chooseReceiver()" ><IMG  src="../images/user2.gif" style="CURSOR: hand;display:inline;" border="0" align="absmiddle" alt="选择人员"></a>
						&nbsp;<input type="button" name="btn" value="清空" onclick="clears()" class="btn" style="display:inline;">	&nbsp;		
						<input type="checkbox" id="che" name="che" style="display:inline;">手机短信提醒
                        <input type="hidden" name="type" id="type">

                        
                        
					</div>
				</tr>
				<tr>
					<td align="right" width="1%">
						<input type="radio" name="sendFuns" value="1" onclick="fsdx()"/>
					</td>
					<td align="left" width="5%" >
						发送手机短信
					</td>
					<td nowrap="nowrap" width="150" style=" text-align:right;" align="right" >
							<div id="title1" style="display:none;">请输入手机号码：</div>
                    </td>
				  
					<td >	
                    	<div id="dv_sjhm" style="display:none;">
                    
						<input type="text" id="sjhm" name="sjhm" onKeyDown='MaxLen(2000,this.value,"sjhm")' style="width:60%" style="display:inline;"/>&nbsp;
                        <span style="display:inline; color:red;">(多个号码请用逗号分隔)</span>
                        </div>
					</td>
				</tr>
				
				
				
	     </table>
			<hr>
			<table width="100%" border="0">
				
			  <tr id="filetr" style="display:none">
                <td bgcolor="#CFE3DA" colspan="4">
					<!--<iframe src="" width="100%" height="100%" frameBorder="0" scrolling="no" id="fujian"></iframe>-->
				</td>
			 </tr>
			  <tr>
					<TD class="bg" nowrap="nowrap" align="left"  colspan="2" width="6%;">
						主题：
					</TD>
					<TD  class="bg" nowrap="nowrap" width="90%" colspan="2">
						<s:if test="msgUsermessage.title!=null && msgUsermessage.title!=''">
							<input name="txt_Title" type="text" maxlength="200" id="txt_Title" style="width: 100%;" value="<s:property value="msgUsermessage.title"/>"/>
						</s:if>
						<s:else>
							<input name="txt_Title" type="text" maxlength="200" id="txt_Title" style="width: 100%;" value=""/>
						</s:else>
					</TD>
				</tr>

				<tr>
					<TD class="bg" nowrap="nowrap" colspan="2"  style="vertical-align:middle;">内容：</TD>
                    <TD class="bg" nowrap="nowrap" colspan="2">
                    	<s:if test="msgUsermessage.content!=null && msgUsermessage.content!=''">
<textarea name="txt_Content" id="txt_Content" class="input" rows="20" style="height:300;width:100%;">
















------------------------------


<s:property value="msgUsermessage.content"/>
</textarea>
                    	</s:if>
                    	<s:else>
							<textarea name="txt_Content" id="txt_Content" class="input" rows="20" style="height:300;width:100%;"></textarea>                    	
                    	</s:else>
					</TD>
				</tr>

				<tr>
                	<TD class="bg" nowrap="nowrap" colspan="3" align="center" style="text-align:right;">
                    	<span id="Label4">说明：主题最多200个字符，内容最多2000个字符<BR></span>
                    	<!-- <input type="button" value="发 送" onclick="SendMsg();"/> -->
					</TD>                    
                </tr>
				
			</table>
		</td>
		
		</tr>
         <tr class="tfoot">
             	<td colspan="4" class="t_r" style="width: 100%;">
                	<input type="button" value="发 送" onclick="SendMsg();"/>
				</td>
         </tr>
        
                
	</table>
          
         
        </div>
        <!--Table End-->
</div>
</form>
</body>


<% 
	String flag1=(String)session.getAttribute("isornotread");	
%>

<script language = "JavaScript">
function submitFormByMsgType(type) {
	//$("#msgType").attr('value',type)
	//document.forms[0].submit();
	if (confirm("是否确定离开撰写页面！")) {
		window.location.href = 'showList.action?msgType=' + type;
	}

}


function ClearMsg() {
	location.href = "/stoa/message/msgsend.do?b_query=true";
	return false;
}
var regex = /^(\d{11},|\d{11}){1,}$/
function SendMsg() {
	var type = "";
	var fun = document.getElementsByName('sendFuns');

	if (document.all("che").checked) {
		document.msgForm.type.value = "send";
	}
	if (fun[0].checked) {
		if (document.msgForm.SID_LIST.value == "") {
			alert("请选择通知的收件人！");
			return false;
		}
	}
	if (document.msgForm.txt_Title.value == "") {
		alert("请填写通知的主题！");
		$("#txt_Title").focus();
		return false;
	}
	var strCont = document.msgForm.txt_Title.value;
	if (strCont.length > 200) {
		alert("您填写的通知标题太长了！");
		$("#txt_Title").focus();
		return false;
	}

	//alert(fun);
	//alert(fun[1].checked);
	if (fun[1].checked) {
		if (!regex.test(document.all.sjhm.value)) {
			alert("输入手机号码有误，请详细检查是否有其他的字符！")
			return false;
		}
		//alert("==="+MaxLen(2000,document.all.sjhm.value,"sjhm")+"==");
		//start
		//曾静 2010-08-03
		if (MaxLens(2000, document.all.sjhm.value, "sjhm") == false) {
			return false;
		}
		//end
		if (document.all.sjhm.value == "") {
			alert("请输入您要发送短信的手机号码！如果只需发送站内通知请选择发送站内通知！");
			document.all.sjhm.focus();
			return false;
		}
	}
	if (fun[0].checked) {
		if (document.msgForm.txt_Content.value == "") {
			alert("请填写通知的内容！");
			$("#txt_Content").focus();
			return false;
		}

		strCont = document.msgForm.txt_Content.value;
		if (strCont.length > 2000) {
			alert("您填写的通知内容太长了！");
			$("#txt_Content").focus();
			return false;
		}
	}
	//document.msgForm.action="/stoa/message/msgsend.do?b_sendmsg=true&type="+type;
	document.forms[0].submit();
	return true;
}

//发送短信时候调用


function fsdx() {
	//start
	//曾静 2010-08-03
	clears(); //清除站内通知的收件人
	document.getElementById("sendUser").style.display = "none"; //隐含站内通知的收件人显示的文字

	document.getElementById("sendMsgess").style.display = "none"; //隐含站内通知的收件人的操作按钮


	//end
	document.all.sjhm.disabled = false;
	document.all.sjhm.value = '';
	document.all.che.disabled = true;
	document.all.che.checked = true;
	showOrHideTr('inline', 'jjj');
	document.all.txt_Content.disabled = true;
	document.all.txt_Content.value = '';
	$("#title1").css("display","inline");
	$("#dv_sjhm").css("display","inline");
}
//发送站内通知时候调用


function zntz() {
	//start
	//曾静 2010-08-03
	document.getElementById("sendUser").style.display = "block"; //显示站内通知的收件人显示的文字


	document.getElementById("sendMsgess").style.display = "block"; //显示站内通知的收件人显示的文字


	//end
	document.all.che.disabled = false;
	document.all.sjhm.disabled = true;
	$("#sjhm").css("display",'inline');
	document.all.sjhm.value = '';
	showOrHideTr('none', 'jjj');
	document.all.txt_Content.disabled = false;
	
	$("#title1").css("display","none");
	$("#dv_sjhm").css("display","none");
}
function setSendType() {
	if (document.msgForm.ropt[0].checked) {
		document.msgForm.SID_LIST.value = "-1";
		document.msgForm.txt_ToUser.value = "";
	}
}

function showOrHideTr(show, id) {
	var tabs = document.getElementsByTagName('table');
	var tab;
	for (var i = 0; i < tabs.length; i++) {
		if (tabs[i].name == id) {
			tab = tabs[i];
			break;
		}
	}

	if(typeof(tabl)!="undefined"){
		var tr = tab.getElementsByTagName('tr')[1];
		var tds = tr.getElementsByTagName('td');
	
		for (var i = 2; i < tds.length; i++) {
			tds[i].style.display = show;
			//alert(i);
		}
	}
}

function goBack() {
	//history.back();
	if ('<%=flag1%>' == 'read') {
		parent.document.getElementsByName('tabParent')[0].rows = "28,*";
		parent.head.location.href = "/stoa/message/msgHead.jsp?state=1&type=1";
	} else {
		parent.document.getElementsByName('tabParent')[0].rows = "28,*";
		parent.head.location.href = "/stoa/message/msgHead.jsp?state=1&type=0";
	}
	//location.href="/stoa/message/msgmanage.do?b_query=true&state=1";
}

function DisplayFJ() {
	if (document.all.filetr.style.display == "none") {
		document.all.filetr.style.display = "block";
		document.all.filetr.height = 150;
	} else {
		document.all.filetr.style.display = "none";
	}
}

//start
//曾静 2010-08-03
//用于判断输入的数值的长度(用于键盘输入使用)
function MaxLen(n, values, name) { //n表示最大的长度数,values表示要检查的内容,name表示此控件的名字
	// if   ((event.ctrlKey)&&(event.keyCode==86)){event.returnValue=false;}   //屏蔽Ctrl+v
	var txt = values;
	//判断内容是否符合要求
	if (txt.length > n - 1 && event.keyCode != 16 && event.keyCode != 17 && event.keyCode != 18 && event.keyCode != 20 && event.keyCode != 27 && event.keyCode != 9 && event.keyCode != 8 && event.keyCode != 46 && event.keyCode != 35 && event.keyCode != 36 && event.keyCode != 37 && event.keyCode != 38 && event.keyCode != 39 && event.keyCode != 40 && event.keyCode != 144) {
		alert("对不起！您已经输入超过150个的手机号码,最多允许输入150个!");
		document.all(name).value = txt.substr(0, n)
			event.keyCode = 0;
		event.returnValue = false;
		return false;
	}
}

//用于判断输入的数值的长度(用于鼠标直接粘贴复制使用)
function MaxLens(n, values, name) { //n表示最大的长度数,values表示要检查的内容,name表示此控件的名字
	// if   ((event.ctrlKey)&&(event.keyCode==86)){event.returnValue=false;}   //屏蔽Ctrl+v
	var txt = values;
	//判断内容是否符合要求
	if (txt.length > n - 1 && event.keyCode != 16 && event.keyCode != 17 && event.keyCode != 18 && event.keyCode != 20 && event.keyCode != 27 && event.keyCode != 9 && event.keyCode != 8 && event.keyCode != 46 && event.keyCode != 35 && event.keyCode != 36 && event.keyCode != 37 && event.keyCode != 38 && event.keyCode != 39 && event.keyCode != 40 && event.keyCode != 144) {
		alert("对不起！您已经输入超过150个的手机号码,最多允许输入150个!");
		return false;
	}
}

function clears()
{
	document.all("SID_LIST").value="";
	document.all("txt_ToUser").value="";
	$("#sjhm").attr('value','');
}
function chooseReceiver(){

	
	window.idField=document.getElementById('SID_LIST');
	window.nameField=document.getElementById('txt_ToUser');
	//alert(window.idField);
	var left=(window.screen.width-400)/2;
	var top=(window.screen.height-500)/2;
	var root ='0';
	window.open('choosePerson.action?root=' + root ,'',
	'height=500,width=450,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,left=' + left + ',top=' + top);
	
}


function selectPersonMultiple(url,idField,nameField,root){
	window.idField=idField;
	window.nameField=nameField;
	var left=(window.screen.width-400)/2;
	var top=(window.screen.height-500)/2;
	window.open(url + '/choosePerson.action?root=' + root ,'',
	'height=500,width=450,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no,left=' + left + ',top=' + top);
}

//-->
</script>


</html>
