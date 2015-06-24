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
		  $("#importance").html($("#importance").html().replace("1","一般隐患").replace("0","重大隐患").replace("2","较大隐患"));
		  
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
                	<li><a href="#">运营安全评价风险点整改工作</a></li>  
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
                                  <s:property value="avo.lines"/>
                                </td>
                                </tr>
                                
                           		<tr>
                           			<td class="t_r lableTd" style="width:15%;">风险编号</td>
	                                <td style="width:35%;"><s:property value='avo.dangerNumber' />
	                                </td>
	                                <td style="width:15%;" class="t_r lableTd">子系统</td>
	                                <td style="width:35%;">
	                                <s:property value='avo.childSYS' />
	                                </td>
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">风险点</td>
	                                <td>
	                                <s:property value="avo.riskPoint"/>
	                                </td>
	                                <td class="t_r lableTd">等级</td>
	                                <td>
	                                <s:property value="avo.levels"/>
                               	 	</td>
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">责任单位/部门</td>
	                                <td><s:property value='avo.deptWork'/>
	                                </td>
	                                <td class="t_r lableTd">分管领导</td>
	                                <td>
	                               		<s:property value='avo.leaderShip'/>
	                                </td>
                           		</tr>
                           		<tr>
                           			<td class="t_r lableTd">消项情况</td>
	                                <td>
	                                <s:property value="avo.workState"/>
	                                </td>
	                                <td class="t_r lableTd">牵头部门</td>
	                                <td>
	                                <s:property value="avo.leadDept"/>
	                                </td>
                           		</tr>
                              <tr>
                                <td class="t_r lableTd">专家建议</td>
                                <td colspan="3">
                                <s:property value="avo.expertAdvice"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">现象说明</td>
                                <td colspan="3">
                                <s:property value="avo.phenomenon"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">整改措施</td>
                                <td colspan="3">
                                <s:property value="avo.rectificationMethod"/>
                                
                                </td>
                              </tr>
                               <tr>
                                <td class="t_r lableTd">节点目标</td>
                                <td colspan="3">
                                <s:property value="avo.targetNode" />
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">进展情况</td>
                                <td colspan="3">
                                <s:property value="avo.cwip" />
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注</td>
                                <td colspan="3">
                                <s:property value="avo.remark" />
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
