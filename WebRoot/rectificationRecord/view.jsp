<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.UserInfo" %>
<%@ page import="com.wonders.stpt.CrossIpLogin" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>数据查看</title>
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
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
		};
				
		
		
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营安全隐患问题整改推进表</a></li>  
                	<li class="fin">数据查看</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        
       	  <table style="width:100%;"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r">
        	      <input type="button" value="打 印" onclick="window.print();"/>&nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	     
       	      </th>
                                  </thead>
                              <tbody>
                              		<tr>
                                <td class="t_r lableTd" style="width:10%;">整改措施</td>
                                <td colspan="3" style="width:89%;">
                                	 <textarea rows="3" cols="4" name="rectificationMethod" readonly="readonly" style="border: none;background-color: transparent;"><s:property value="avo.rectificationMethod"/>
                               		 </textarea>
                                </td>
                              </tr>
                               <tr>
                                <td class="t_r lableTd">节点目标</td>
                                <td colspan="3">
                                	 <textarea rows="3" cols="4" name="targetNode" readonly="readonly" style="border: none;background-color: transparent;"><s:property value="avo.targetNode"/>
                                	 </textarea>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">进展情况</td>
                                <td colspan="3">
                                	 <textarea rows="3" cols="4" name="cwip" readonly="readonly" style="border: none;background-color: transparent;"><s:property value="avo.cwip"/>
                               		 </textarea>
                                </td>
                              </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
                                <input type="button" value="打 印" onclick="window.print();"/>&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>

</td>
                              </tr>
                            </table>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
