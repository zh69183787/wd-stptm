<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>
督办统计
</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/show.js"></script>
<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	

<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
.ui-datepicker-title span {display:inline;}
button.ui-datepicker-current { display: none; }
</style>		
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
        
            
       
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">督办业务</a></li> 
                	<li>
                		督办统计
                	</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                <!-- <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li> -->
                <li class="selected" onclick=""><a class="query" href="#">查询</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
          <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter-->
      


      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.result"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1">
        					 <thead>
                                  <th colspan="30">督办统计</th>
                              </thead>  
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c" width="20%">单位名称</td>
                                <td class="t_c" width="20%">总督办事项（条）</td>
                                <td class="t_c" width="20%">总完成数（条）</td>
                                <td class="t_c" width="20%">未完成数（条）</td>
                                <td class="t_c" width="20%">超时数（条）</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              	<td  class="t_c"><s:property value="#items.deptName" /></td>
                              	<td  class="t_c"><s:property value="#items.countAll"/></td>
                               	<td  class="t_c"><s:property value="#items.countDone" /></td>
                                <td  class="t_c"><s:property value="#items.countUnDone"/></td>
                                <td  class="t_c"><s:property value="#items.countOverTime"/></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="15">
                                </td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>