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
<title>公休详细</title>
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
			
		});
		
		
		function shut(){
			window.close(); 
		}
		
		function agreeApply(id,holState){
			if(confirm('确认提交？')){
				$.ajax({
					url:'submitApply.action',
					data:{
						id:id,
						holState:holState
					},
					success:function(){
						alert('审批成功！');
						window.close()
					}
				});	
			}
		
		}
		
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="<%=basePath %>/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
				<ul>
					<li><a href="#">公休事务</a></li>
					<li><a href="#">公休申请</a></li>
					<li class="fin">公休详细</li>
				</ul>
			</div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
       	  <table width="100%"  class="table_1"><!--
          	<thead>
            	<td class="t_r" colspan="4">
            		<input type="button" value="同意" onclick="return check();"/>
								&nbsp;<input type="reset" value="驳回" />&nbsp;
								<input type="button" value="关 闭" onclick="shut();"/>
       	        </dt>
           </thead>
            --><tbody>
	            <tr>
	              <td class="t_r lableTd" width="15%;">申请人</td>
	              <td>
	              	<s:property value="#request.csUser.name"/>
	              </td>
	            </tr>
	            <tr>
	            	 <td class="t_r lableTd" width="15%;">目前可用公休</td>
		              <td>
		              	<s:property value="#request.allDaysLeft+#request.holHolidayApply.holDays"/>
		              </td>
	            </tr>
	            <tr>
	              <td class="t_r lableTd" width="15%;">申请公休天数</td>
	              <td>
	              	<s:property value="#request.holHolidayApply.holDays"/>
	              </td>
	            </tr>
	            <tr>
	            	 <td class="t_r lableTd" width="15%;">审批后剩余公休</td>
	              <td>
	              	<s:property value="#request.allDaysLeft"/>
	              </td>
	            </tr>
	            <tr>
	              <td class="t_r lableTd" width="15%;">公休日期</td>
	              <td colspan="3">
	              	<s:property value="#request.holHolidayApply.startDate"/>
	              </td>
	            </tr>
	            <tr>
	              <td class="t_r lableTd" width="15%;">申请说明</td>
	              <td colspan="3">
	              	<s:property value="#request.holHolidayApply.holComment"/>
	              </td>
	            </tr>
                <tr>
                    <td class="t_r lableTd" width="15%;">通知人员</td>
                    <td colspan="3">
                        <s:property value="#request.holHolidayApply.noticeNameList"/>
                    </td>
                </tr>
            </tbody>
                              <tr class="tfoot" >
                              	<td class="t_r" colspan="4">
                              	<s:if test="#request.showStatus=='yes'">
                              		<input type="button" value="同意" onclick="agreeApply('<s:property value="#request.holHolidayApply.id"/>',59)"/>
									&nbsp;<input type="reset" value="驳回" onclick="agreeApply('<s:property value="#request.holHolidayApply.id"/>',60)"/>&nbsp;
                              	</s:if>
								<input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
            </table>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
