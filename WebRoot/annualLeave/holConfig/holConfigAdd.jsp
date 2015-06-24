<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
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
<%String basePath = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>节假日新增</title>
<link rel="stylesheet" href="<%=basePath %>/css/formalize.css" />

<link rel="stylesheet" href="<%=basePath %>/css/page.css" />
<link rel="stylesheet" href="<%=basePath %>/css/imgs.css" />
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="<%=basePath %>/js/html5.js"></script>
        <script src="<%=basePath %>/js/jquery-1.7.1.js"></script>
		<script src="<%=basePath %>/js/jquery.formalize.js"></script>
		<script src="<%=basePath %>/js/common.js"></script>
		<link type="text/css" href="<%=basePath %>/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="<%=basePath %>/datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");		
			
			$("#hdate").datepicker({
				inline: true,
				changeYear:true,
				changeMonth:true,
				showOtherMonths: true,
				selectOtherMonths: true
			});	
		});
		
		function check(){
			var hdate = $('#hdate').val();
			var memo = $('#memo').val();
			if(hdate==null || hdate==''){
				alert('请选择日期！');
				$('#hdate').focus();
				return ;
			}
			$.ajax({
				type : 'post',
				url : 'saveHolConfig.action?random='+Math.random(),
				data:{
					hdate:hdate,
					memo:memo
				},
				dataType:'text',
				error:function(){
					alert("系统连接失败，请稍后再试！")
				},
				success:function(data){
					if(data!=null && data=='success'){
						alert('保存成功！')
						window.close(); 
					}else if(data!=null && data=='exist'){
						alert('该日期已存在！');
					}
				}	
			});
			
		}
		
		function shut(){
			window.close(); 
		}
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="<%=basePath %>/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li><a href="#">行政事务</a></li>
					<li><a href="#">人力资源管理</a></li>
					<li><a href="#">节假日管理</a></li>
                	<li class="fin">节假日新增</li>  
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        <s:form action="save" name="holidays" namespace="/holidays">
        
       	  <table width="100%"  class="table_1">
          
                              <tbody>
                              
                              <tr>
                                <td class="t_r lableTd" style="width:15%;">节假日日期选择</td>
                                <td style="width:85%;">
                                	<input type="text" id="hdate" name="hdate" class="input" readonly="readonly"/>
                                </td>
                               </tr>
                              <tr>
                              	<td class="t_r lableTd" style="width:15%;">节假日名称</td>
                                <td style="width:85%;">
                                <input type="text" id="memo" name="memo" class="input_large" class="input" maxlength="20"/></td>
                              </tr>
                              
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="2" class="t_r">
                                	<input type="button" value="确 认" onclick="return check();"/>
								&nbsp;<input type="reset" value="重 置" />&nbsp;
									<input type="button" value="关 闭" onclick="shut();"/>
								</td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
