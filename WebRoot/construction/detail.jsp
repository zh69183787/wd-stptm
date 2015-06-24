<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
        	
        	function shut(){
        		window.opener=null;
			  window.open("","_self");
			  window.close();
        	}
		
        </script>
</head>

<body>
 <input type="hidden" id="hretId" value="<s:property value='#request.hretList[0].hretId'/>">
 <input type="hidden" id="pageNo" value="<s:property value='#request.pageNo'/>">
  <input type="hidden" id="pageCount" value="<s:property value='#request.pageCount'/>">
  

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" style="display:run-in;"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">路网施工计划排定</a></li>                	
                	<li class="fin">信息查看</li>
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
        
        <input type="hidden" id="hrId" name="hrId" value="<s:property value='hrBInfoVO.hrId'/>"> 
        <input type="hidden" id="h_politicalLandscape" name="h_politicalLandscape" value="<s:property value='hrBInfoVO.politicalLandscape'/>">  
        <input type="hidden" id="h_highestDegree" name="h_highestDegree" value="<s:property value='hrBInfoVO.highestDegree'/>"> 
       	  <table width="100%"  class="table_1">
	          <thead>
	            <th colspan="10" class="t_r">
	        	      &nbsp;
	        	      <input type="button" value="关 闭" onclick="shut();"/>
	        	      &nbsp;        	      
	       	      </th>
	          </thead>
				<tbody>
					<tr>
						<td class="t_r lableTd" width="250px;"><B>线路</B></td>
						<td>
							<s:if test="#request.construction.lineNo==0">全网</s:if>
							<s:else><s:property value="#request.construction.lineNo"/>号线</s:else>
						</td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>日期</B></td>
						<td><s:date name="#request.construction.constructionDate" format="yyyy年MM月dd日"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>申请单位</B></td>
						<td><s:property value="#request.construction.applyUnit"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>施工单位</B></td>
						<td><s:property value="#request.construction.constructionUnit"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>负责人</B></td>
						<td><s:property value="#request.construction.responsiblePerson"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>施工范围</B></td>
						<td><s:property value="#request.construction.construstionRange"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>起始时间</B></td>
						<td><s:date name="#request.construction.constructionStartDate" format="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>结束时间</B></td>
						<td><s:date name="#request.construction.constructionEndDate" format="yyyy-MM-dd HH:mm"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>施工内容</B></td>
						<td><s:property value="#request.construction.constructionDetail"/></td>
					</tr>
					<tr>
						<td class="t_r lableTd"><B>牵引动力</B></td>
						<td><s:property value="#request.construction.dragDynamic"/></td>
					</tr>
					
					
					<tr>
						<td class="t_r lableTd"><B>触网停电范围</B></td>
						<s:if test="#request.construction.powerOffRange!=null">
							<td><s:property value="#request.construction.powerOffRange"/></td>
						</s:if>
						<s:else>
						<td>无</td>
						</s:else>
					</tr>
					
					<tr>
						<td class="t_r lableTd"><B>计划编号</B></td>
						<td><s:property value="#request.construction.projectNo" /></td>
					</tr>
					
				</tbody>
				<tr class="tfoot">
                	<td colspan="4" class="t_r">&nbsp;
						<input type="button" value="关 闭" onclick="shut();"/>
					</td>
                </tr>
         </table>
          
         
        </div>
        <!--Table End-->
</div>
</body>
</html>
