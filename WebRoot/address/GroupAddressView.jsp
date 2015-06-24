<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>查看</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
       	<script src="../js/jquery-1.7.1.js"></script>
        <script src="../js/html5.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script src="../js/show.js"></script>
		<script type="text/javascript"><!--
		var object ='${obj}';
		var name ='${name}';
        $(function(){
			$(".odd tr:odd").css("background","#fafafa");		
			loadShow();	
		});		
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
 
 function open(){
		window.location.href="personAddressList.action?login_name=<%=request.getParameter("login_name")%>";		
		}
        --></script>
       
</head>

<body >

	<div class="main" >
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<img src="../images/sideBar_arrow_left.jpg" width="46" height="30"
						alt="收起" onclick="crossDomainShowOrHide();">
            <div class="posi fl">
            	<ul>
                	<li><a href="#">我的事务</a></li>
                	<li><a href="#">公共事务</a></li>
                	<li><a href="#">集团通讯录</a></li>      
                	<li class="fin">查看</li>
                </ul>
            </div>
           
   		</div>
        <!--Ctrl End-->
        <!--Table-->
        <div class="mb10 pt45">
        
          <!--<div class="filter">
           	 <div class="fn"><h5>详细信息</h5></div>            	
           </div>
       	  --><table width="100%"  class="table_1">
 <s:set name="obj" value="#request.obj"></s:set>
                               <tbody>
                               <tr>
                                <td class="t_r lableTd">姓名：</td>
                                <td>${name }</td>
                               	<td class="t_r lableTd">所属部门：</td>
                                <td>${dept_name }</td>
                               </tr>
                              
                              <tr>
                               <td class="t_r lableTd">邮件地址</td>
                                <td>
                                ${email }
                                </td>
                                <td class="t_r lableTd">单位地址：</td>
                                <td>
                                ${address }
                                </td>
                               </tr>
                               <tr>
                                <td class="t_r lableTd">手机</td>
                                <td>${moblie }</td>
                                <td class="t_r lableTd">单位电话</td>
                                <td>${office_phone }</td>
                               </tr>
                               <tr>
                                <td class="t_r lableTd">家庭电话</td>
                                <td>${home_phone}</td>
                                <td class="t_r lableTd">单位传真</td>
                                <td>${fax }</td>
                              </tr> 
                                                      
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input type="button" value="关闭" onclick="javascript:shut()"/>
                                </td>
                              </tr>                                                         
                            </table>                                                                   
      </div>                             
        <!--Table End-->
</div>
</body>
</html>
