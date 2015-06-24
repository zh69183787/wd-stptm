<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>扩展信息查看</title>
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
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		$(document).ready(function () {
		  var data=new Array();
		  $("[id=hidden_data]").each(
		    function(index){		      
		      data[index]=$(this).val();		      
		    }
		  )
		  $("[id=itemFName]").each(
		    function(index){	      		      
		      $(this).html(data[index+2]);		      
		    }
		  )
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
</script>
</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">工会人事数据库</a></li>	
	<li class="fin">“<s:property value="#request.typeName"/>”信息查看</li>
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

<div class="mb10">


                <s:iterator value="#request.list3" >	  	      			    	      		
					      <input type="hidden" id="hidden_data" value="<s:property />"/>		    	      			   	      		
	   	      	</s:iterator>

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 			
		</th>
	</thead>
	<tbody>
		<s:iterator value="#request.page.result"> 
		<tr>
		  <td class="t_r lableTd"><s:property value='itemName'/></td>	 		  
		    <td id="itemFName">		      		    
		    </td>
		</tr>
		</s:iterator>
		
		
		
		
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> 
		</td>
	</tr>
</table>


</div>
<!--Table End--></div>
</body>
</html>
