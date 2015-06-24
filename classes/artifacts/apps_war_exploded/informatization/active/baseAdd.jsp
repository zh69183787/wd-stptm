<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>新媒体活动展开情况新增</title>
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
		
		$(function(){
			$('#startTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"yearRange":'1900:+nn' 
			});
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
				
			$(".ui-datepicker-close").live("click", function (){              
              $("#startTime").val("");              
            });
			
		});	
		
		$(document).ready(function(){
		  var addSuccess = $("#addSuccess").val();
		  if(addSuccess=="success") alert("添加成功");		  
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		  //去除多余空格
		  var activeName = $("#activeName");
		  if(activeName.val()==""){
			  activeName.focus();
		    alert(" 活动名称必须选!!!");
		    return false;
		  }
		  return true;		  
		}


        </script></head>

<body>
<input type="hidden" id="addSuccess" value="<s:property value='#request.addSuccess'/>"/>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">团委信息化</a></li>                	
                	<li class="fin">新媒体活动展开情况新增</li>
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
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        <s:form action="add" name="leader" namespace="/active" method="active">
        <input type="hidden" name="removed" value="0">
        <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      <input type="reset" value="取 消" />
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd" style="width:25%;">活动(话题)名称</td>
                                <td style="color:red" style="width:25%;">
                                <input type="text" id="activeName" name="activeName" class="input_large" maxlength="50" />  &nbsp;&nbsp;*
                                </td>
                           		<td class="t_r lableTd" style="width:25%;">开展时间</td>
                                <td style="width:25%;"><input type="date" readonly="readonly" id="startTime" name="startTime" maxlength="18" class="input_large" /></td>
                                
                                
                                </tr>
                              <tr>
	                       			<td class="t_r lableTd">活动简介（100字以内）</td>
	                                <td colspan="3"><input type="text" id="activeIntroduction" name="activeIntroduction" class="input_large" maxlength="150" style="width:70%" />&nbsp;&nbsp;
	                                &nbsp;&nbsp;&nbsp;&nbsp;</td>         
                                </tr>
                              <tr>
                                <td class="t_r lableTd">影响覆盖青年情况</td>
                                <td colspan="3">
                                	<textarea rows="4" id="influence_covers_the_youth" name="influence_covers_the_youth"></textarea>
                                </td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>
&nbsp;
<input type="reset" value="取 消" />&nbsp;</td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
