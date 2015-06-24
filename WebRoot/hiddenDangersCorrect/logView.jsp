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
		}
				
		
		$(function () {
		  $("#dangersClass").html($("#dangersClass").html().replace("1","设施设备").replace("2","管理").replace("3","规章制度").replace("4","现场作业").replace("5","新线遗留"));
		  $("#workState").html($("#workState").html().replace("1","已完成").replace("2","整改中"));
		  $("#importance").html($("#importance").html().replace("1","三级").replace("0","一级").replace("2","二级"));
		  
		  $("#dangersState").html($("#dangersState").text());
		  $("#correctMethod").html($("#correctMethod").text());
		  $("#remark").html($("#remark").text());
		});
		
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
                <!-- 
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>                
                <li><a class="query" href="#">查询</a></li>
                 -->
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r">
        	      <input type="button" value="打 印" onclick="window.print();"/>&nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	     
       	      </th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">线路/处所</td>
                                <td colspan="3" >
                                  <s:property value="hiddenDangersCorrectVO.lines"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">隐患情况</td>
                                <td id="dangersState" colspan="3"><s:property value='hiddenDangersCorrectVO.dangersState'/></td>
                               
                                </tr>
                              <tr>
                                <td class="t_r lableTd">整改措施</td>
                                <td id="correctMethod" colspan="3"><s:property value='hiddenDangersCorrectVO.correctMethod'/></td>
                               
                                </tr>
                              <tr>
                                
                                <td class="t_r lableTd" style="width:15%">责任人</td>
                                <td style="width:30%"><s:property value='hiddenDangersCorrectVO.workPerson'/></td>
                                <td class="t_r lableTd" style="width:20%">督办部门</td>
                                <td style="width:35%"><s:property value='hiddenDangersCorrectVO.supervisionDept'/></td>
                              </tr>
                              <tr>
                              	<td class="t_r lableTd">所属专业</td>
                                <td><s:property value='hiddenDangersCorrectVO.major'/></td>
                                <td class="t_r lableTd">填报日期</td>
                                <td><s:date format='yyyy-MM-dd' name='hiddenDangersCorrectVO.inputDate'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">隐患类型</td>
                                <td id="dangersClass">
                                  <s:property value="hiddenDangersCorrectVO.dangersClass"/>
                                </td>
                                <td class="t_r lableTd">整改时限</td>
                                <td><s:date format='yyyy-MM-dd' name='#request.logBo.nowLastDate'/></td>
                              </tr>
                              <tr>
                               <td class="t_r lableTd">填报单位</td>
                                <td><s:property value='hiddenDangersCorrectVO.inputDept'/></td>
                                <td class="t_r lableTd">填报人</td>
                                <td><s:property value='hiddenDangersCorrectVO.createPerson'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">落实情况</td>
                                <td id="workState">
                                  <s:property value="hiddenDangersCorrectVO.workState"/>
                                </td>
                                <td class="t_r lableTd">实际完成时间</td>
                                <td><s:date format='yyyy-MM-dd' name='hiddenDangersCorrectVO.finishDate'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">重要程度</td>
                                <td id="importance" colspan="3">
                                  <s:property value='hiddenDangersCorrectVO.importance'/>
                                </td>
                                </tr>
                                 <tr>
                                <td class="t_r lableTd">责任单位/部门</td>
                                <td colspan="3">
                                    <s:property value="hiddenDangersCorrectVO.workDept"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注</td>
                                <td id="remark" colspan="3"><s:property value='hiddenDangersCorrectVO.remark'/></td>
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
