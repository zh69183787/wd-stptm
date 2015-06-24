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
查看督办跟踪
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
		<script src="js/attachMultiple.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
        <script type="text/javascript">
        var basePath = "<%=basePath%>";
        
        String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
		}
        
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
			
			$("[id=attach]").each(function(index){
				showData("view",index);
			});
			
			
			$("[id=reasonTd]").each(function(){
				$(this).html($(this).html().replaceAll("&lt;","<").replaceAll("&gt;",">").replaceAll("&amp;","&"));
			});
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
                		查看督办跟踪
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
        	<s:form action="finishFollow" id="form" namespace="/dbFollow"> 
        	<input type="hidden" name="id" value="<s:property value='dbFollow.id'/>"/> 
        	<input type="hidden" name="modifyPerson" value="<%=inputUserName %>"/>
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="30"><s:property value='dbFollow.dbName'/></th>
                              </thead>    
                              <tbody>
                              <s:iterator value="#request.list" id="list">
	                              <tr class="tit">
	                                <td class="t_c" style="border-top:#000 2px solid;">跟踪部门</td>
	                                <td class="t_c" colspan="2" style="border-top:#000 2px solid;">要求</td>
	                                <td class="t_c" colspan="2" style="border-top:#000 2px solid;">计划提交时间</td>
	                                <s:if test="dbFollow.followType==1">
	                                <td class="t_c" style="white-space:nowrap;width:30%;border-top:#000 2px solid;">反馈附件</td>
	                                </s:if>
	                              </tr>
	                              <tr>
		 	                            <td class="t_c"><s:property value='#list[0].followDeptName'/></td>
		 	                            <td class="t_c" colspan="2"><s:property value='#list[0].require'/></td>
		 	                            <td class="t_c" colspan="2"><s:property value='#list[0].planSubmitTime'/></td>
		 	                            <s:if test="dbFollow.followType==1">
		                                <td class="t_c" >
		                                	<input type="hidden" id="attach" value='<s:property value='#list[0].attach'/>'>
											<span id="attachSpan"></span>
		                                </td>
		                                </s:if>
		                          </tr>
		                          <s:if test="dbFollow.followType==2">		                          
		                          <tr class="tit">
	                                <td class="t_c" style="white-space:nowrap;width:20%;">计划名称</td>
	                                <td class="t_c" style="white-space:nowrap;width:10%;">计划完成时间</td>
	                                <td class="t_c" style="white-space:nowrap;width:25%;">计划成果</td>
	                                <td class="t_c" style="white-space:nowrap;width:10%;">实际完成时间</td>
	                                <td class="t_c" style="white-space:nowrap;width:30%;">实际成果</td>
	                              </tr>
	                              <s:iterator value="#list[1]">
	 	                          <tr>
	 	                            <td class="t_c">
	 	                            	<s:property value='planName'/>
	 	                            </td>
	 	                            <td class="t_c"><s:property value='planFinishTime'/></td>
	 	                            <td class="t_c"><s:property value='planResult'/></td>
	 	                            <td class="t_c"><s:property value='finishTime'/></td>
	 	                            <td class="t_c">
	 	                            	<input type="hidden" id="attach" value="<s:property value='attach'/>">
										<span id="attachSpan"></span>
	 	                            </td>
	                            </tr>
	                            <s:if test="planChangeHistory!=null">
	                            <tr>
	                            	<td colspan="10" id="reasonTd"><s:property value='planChangeHistory'/></td>
	                            </tr>
	                            </s:if>
	                            </s:iterator>
	                            </s:if>
	                            <tr><td colspan="10">&nbsp;</td></tr>
                              </s:iterator>
                              
                              <tr>
                                <td class="t_c lableTd">
                                	<b>相关附件</b>
                                </td>
                                <td colspan="10">
 									<input type="hidden" id="attach" value='<s:property value='dbFollow.attach'/>'>
									<span id="attachSpan"></span>
								</td>
							  </tr>
							  <s:if test="#request.deal==1&&dbFollow.followState==1">
                              <tr>
                              	<td class="t_c lableTd">
                                	<b>办结意见</b>
                                </td>
                                <td colspan="10">
 									<input type="text" name="suggest" maxlength="500" style="width:98%">
								</td>
                              </tr>
                              </s:if>
                              <s:elseif test="dbFollow.followState==2">
                              <tr>
                              	<td class="t_c lableTd">
                                	<b>办结意见</b>
                                </td>
                                <td colspan="10">
 									<s:property value="dbFollow.suggest"/>
								</td>
                              </tr>
                              </s:elseif>
                              </tbody>
                              <tr class="tfoot"><td colspan="6" class="t_c"><div class="clearfix">
                              <s:if test="#request.deal==1&&dbFollow.followState==1">
                              <input type="submit" value="办 结"/>
                              </s:if>
                              &nbsp;&nbsp;<input type="button" value="关 闭" onclick="window.close();"/></div></td></tr>
                            </table>
         </s:form>                 
      </div>
        <!--Table End-->
</div>
</body>
</html>


