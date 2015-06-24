<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.UserInfo" %>
<%@ page import="com.wonders.stpt.CrossIpLogin" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
Map<String, String> cookieMap = new HashMap<String, String>();
Cookie[] cookies = request.getCookies();
String token=null;
String inputUserName=null;
if(cookies !=null){
	//取填报人
	for (Cookie cookie:cookies) {
		if("userName".equals(cookie.getName())){
			inputUserName = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			break;
		}
	}
}
%>
<% String basePath = request.getContextPath();  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>
<s:if test="#request.followType==1">
新增简单跟踪
</s:if>
<s:elseif test="#request.followType==2">
新增计划跟踪
</s:elseif>
</title>
<link rel="stylesheet" href="../css/formalize.css" />

<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../css/uploadify.css" />
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
		<script src="../js/jquery.uploadify.v2.1.4.min.js"></script>
		<script src="../js/swfobject.js"></script>
		<script src="js/common.js"></script>
		<script src="js/attach.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
        <script type="text/javascript">
        var basePath = "<%=basePath%>";
		
		$(function(){
			if($("[name=dbId]").val()=="0"){
				alert("请勿重复发起督办跟踪！");
				window.opener=null;	
			    window.open("","_self");
			    window.close();
			}

			if($("[name=dbId]").val()==""||$("[name=followType]").val()==""){
				alert("请重新打开链接！");
				window.opener=null;	
			    window.open("","_self");
			    window.close();
			}
		});
		

    	</script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">督办跟踪</a></li> 
                	<li>
                		<s:if test="#request.followType==1">
                		新增简单跟踪
                		</s:if>
                		<s:elseif test="#request.followType==2">
                		新增计划跟踪
                		</s:elseif>
                	</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                <!-- <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>
                <li class="selected" onclick="showOrHide(this)"><a class="query" href="#">查询</a></li> -->
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Table-->
        <div class="mb10">
        	<s:form action="saveFollowAdd" id="form" namespace="/dbFollow"> 
        	<input type="hidden" name="createPerson" value="<%=inputUserName %>"/>   
        	<input type="hidden" name="dbId" value="<s:property value='#request.dbId'/>"/>
        	<input type="hidden" name="dbName" value="<s:property value='#request.dbName'/>"/>
        	<input type="hidden" name="followType" value="<s:property value='#request.followType'/>"/>
        	<input type="hidden" name="childInfo"/>
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="30"><s:property value='#request.dbName'/></th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td class="t_c" style="white-space:nowrap;width:20%;">跟踪部门</td>
                                <td class="t_c" style="white-space:nowrap;width:60%;">要求</td>
                                <td class="t_c" style="white-space:nowrap;width:20%;">计划提交时间</td>
                              </tr>
                              
                               <s:iterator value="#request.list" status="st" id="list">
	 	                          <tr id="dataTr">
	 	                            <td class="t_c">
	 	                            	<input type="text" id="followDeptName" onblur="addRow(this)" value="<s:property value='#list[1]'/>" maxlength="50"/>
	 	                            	<input type="hidden" id="followDeptId" value="<s:property value='#list[0]'/>"/>
	 	                            	<input type="hidden" id="followChildId"/>
	 	                            	<input type="hidden" id="removed"/>
	 	                            </td>
	 	                            <td class="t_c"><input type="text" id="require" style="width:98%" maxlength="100"/></td>
	 	                            <td class="t_c"><input type="text" id="planSubmitTime<s:property value='#st.index'/>" readonly="readonly"/></td>
	                            </tr>
	                            </s:iterator>
	                          <tr>
                                <td class="t_r lableTd">
                                	相关附件<br/>(单个文件上传最大为10Mb)
                                </td>
                                <td colspan="3">
 									<input type="hidden" name="attach" id="attach">
 									<input id="file_upload" type="file" value="上传" style="float: left;"/>
									<input type="button" value="上传" disabled="disabled" id="uploadButton" onclick="uploadifyUpload()" style="float:left;"/>
									<span id="attachSpan"></span>
								</td>
							  </tr>
                              </tbody>
                              <tr class="tfoot"><td colspan="6" class="t_c"><div class="clearfix"><input type="button" value="保 存" onclick="saveAdd();"/>
                              &nbsp;&nbsp;<input type="button" value="关 闭" onclick="window.close();"/></div></td></tr>
                            </table>
         </s:form>                 
      </div>
        <!--Table End-->
</div>
</body>
</html>


