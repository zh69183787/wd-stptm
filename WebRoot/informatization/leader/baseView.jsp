<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>团干部信息查看</title>
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
                	<li class="fin">团干部信息查看</li>
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
                                <td class="t_r lableTd" style="width:25%">所属直属单位团组织</td>
                                
                                <td style="width:25%"><s:property value='avo.unitdirectly'/></td>
                                
                                <td class="t_r lableTd" style="width:25%">支部名称</td>
                                <td style="width:25%"><s:property value='avo.branchName'/></td>
                                
                              <tr>
                                <td class="t_r lableTd">支部书记</td>
                                <td>
                                <s:property value="avo.branchSecretary"/></td>
                                <td class="t_r lableTd">支部人数</td>
                                <td>
                                <s:property value="avo.branchNumber"/></td>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">担任团内职务</td>
                                <td><s:property value="avo.job"/></td>
                                <td class="t_r lableTd">性别</td>
                                <td><s:property value="avo.gender"/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">出生年月</td>
                                <td><s:property value="avo.birthday"/></td>
                                <td class="t_r lableTd">政治面貌</td>
                                <td><s:property value="avo.politicsStatus"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">文化程度</td>
                                <td>
                                <s:property value="avo.levelEducation"/>
                                </td>
                                <td class="t_r lableTd">学位</td>
                                <td><s:property value="avo.degree"/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">岗位职务</td>
                                <td>
                                <s:property value="avo.postOffice"/></td>
                                <td class="t_r lableTd">职称或技术等级</td>
                                <td>
                                <s:property value="avo.titleorTechnicalLevel"/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">手机</td>
                                <td>
                                <s:property value="avo.mobilPhone"/></td>
                                <td class="t_r lableTd">微博与微信名</td>
                                <td>
                                <s:property value="avo.wiboorMicro"/></td>
                             </tr>
                           		<tr>
                                <td class="t_r lableTd">全职或兼职</td>
                                <td>
                               	<s:property value="avo.fullorParttime"/>
                               	</td>
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
