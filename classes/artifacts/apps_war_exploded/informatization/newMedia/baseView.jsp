<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>团组织新媒体建设（汇总情况以公司为单位）信息查看</title>
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
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
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
                	<li><a href="#">团委信息化</a></li>                	
                	<li class="fin">团组织新媒体建设（汇总情况以公司为单位）信息查看</li>
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
        
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r">
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;        	      
       	      </th>
                                  </thead>
                              <tbody>
                                <tr>
                                <td class="t_r lableTd" style="width:25%">名称(所属直属单位团组织)</td>
                                
                                <td style="width:25%"><s:property value='avo.directlyUnitName'/></td>
                                
                                <td class="t_r lableTd" style="width:25%">开通（开展）时间</td>
                                <td style="width:25%"><s:property value='avo.startTime'/></td>
                                
                              <tr>
                                <td class="t_r lableTd">被评过的市级荣誉</td>
                                <td>
                                <s:property value="avo.honor"/>
                                </td>
                                <td class="t_r lableTd">日均访问量</td>
                                <td><s:property value="avo.averageDailyTraffic"/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">是否独立域名</td>
                                <td><s:property value="avo.independentDomain"/></td>
                                
                                </tr>
                              <tr>
                              <td class="t_r lableTd">网站（页）简介</td>
                                <td colspan="3">
                                <s:property value="avo.webIntroduction"/></td>
                              </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>

</td>
                              </tr>
                            </table>
                           
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
