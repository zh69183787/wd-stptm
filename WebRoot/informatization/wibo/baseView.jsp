<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>认证微博开设情况查看</title>
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
			$("#certificationTd").html($("#certificationTd").html().replace("1","是").replace("0","否"));		
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
                	<li class="fin">团干部信息数据查看</li>
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
        <s:form action="update" name="wibo" namespace="/wibo" method="post">
        <input type="hidden" name="removed" value="0">
        <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r">
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd" style="width:25%;">微博名称</td>
                                <td style="width:25%;">
                                <s:property value='real_nameGroupWibovo.wiboName'/>
                                </td>
                           
                                <td class="t_r lableTd" style="width:25%;">是否已认证</td>
                                <td style="width:25%;" id="certificationTd">
                                <s:property value='real_nameGroupWibovo.certification'/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">联系人</td>
                                <td><s:property value='real_nameGroupWibovo.linkName'/></td>
                                <td class="t_r lableTd">手机</td>
                                <td><s:property value='real_nameGroupWibovo.mobilPhone'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">微博地址</td>
                                <td colspan="3"><s:property value='real_nameGroupWibovo.wiboAddress'/></td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
<input type="button" value="关 闭" onclick="shut();"/>
</td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
