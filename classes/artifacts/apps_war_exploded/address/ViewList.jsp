<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>集团通讯录分类视图</title>
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
		<script src="../js/high.js"></script>
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="../js/xmlhttprequest.js"></script>
		<script type="text/javascript">
			$(function(){ $('#High').high();});
		</script>
		<script type="text/javascript">
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
}  
</script>
		<script type="text/javascript">
	function trimStr(html){
		if(html.length>7){
			return (html.substr(0,7)+"...");
		}
		return html
	}
		
		var list ='${list3}';
			var list1 ='${list2}';
	$(document).ready(function(){
		$("td[id=date]").each(function(index){
			var text = $(this).text();
			var subText = text.substring(11,16);
			
			if(subText=="00:00"){
				text =  text.substring(0,10);
				text += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				$(this).html(text);
			}
		})
	})
      
  
    </script>
	</head>

	<body style="width: 100%;overflow-x:hidden;">
		<iframe id="iframe" style="display: none;"></iframe>
		<div style="width:100%;">
		
		<div class="main">
			<!--Ctrl-->
			
			
			<div class="ctrl clearfix">
				<div class="fl">
					<img src="../images/sideBar_arrow_left.jpg" width="46" height="30"
						alt="收起" onclick="crossDomainShowOrHide();">
				</div>
				 <div class="posi fl">
            	<ul>
                	<li><a href="#">我的事务</a></li>
                	<li><a href="#">公共事务</a></li>
                	<li><a href="#">集团通讯录</a></li>
                	<li class="fin">分类视图</li>
                </ul>  
            </div>
				<div class="fr lit_nav">
					<ul>
						<li class="selected">
							<a class="print" href="#">打印</a>
						</li>
						<li>
							<a class="storage" href="#">网络硬盘</a>
						</li>
						<li>
							<a class="rss" href="#">订阅</a>
						</li>
						<li>
							<a class="chat" href="#">聊天</a>
						</li>
						<li>
							<a class="query" href="#">查询</a>
						</li>
					</ul>
				</div>
			</div>
			<!--Ctrl End-->
				<div class="tabs_2">
				<ul id="urlist" style="float: left">
					<li class="selected">
						<a href="sortViewByCode.action"><span>分类视图</span> </a>
					</li>
					<li  >
						<a href="showGroupList.action"><span>列表视图</span> </a>
					</li>
				</ul>


			</div>		
			<!--Table-->
			<s:set name="list1" value="#request.list1"></s:set>
			<div class="mb10" style="position: relative;left:0%;">
				<table width="48%" class="table_1">
					<thead>
						<th colspan="15">
							培训中心
						</th>
					</thead>
					<tbody>
						<tr class="tit">
							<td>
								姓名
							</td>
							<td>
								电子邮件
							</td>
							<td>
								手机号码
							</td>
							<td>
								单位电话
							</td>
							<td>
								操 作
							</td>
						</tr>
						<s:iterator value="#list1" id="items" status="s">
							<tr>
								
								<td class="t_c" style="width: 300px;">
									<s:property value="#items[0]" />
								</td>
								
								<td class="t_c" style="width: 150px;">
									<s:property value="#items[1]" />
								</td>

								
								<td class="t_c" style="width: 150px; text-align: left;">
									<s:property value="#items[2]" />
								</td>
								
								<td class="t_c" style="width: 150px; text-align: right;">
									<s:property value="#items[4]" />
								</td>
								
									<td class="t_c" style="width: 150px; text-align: left;">
										<a href="personAddressList.action?login_name=<s:property value="#items[3]"/>" target="_blank">查看</a>												

								</td>				
							</tr>
						</s:iterator>
					</tbody>
<tr align="right">
<td colspan="5" align="right">
<p align="right"><a href="sortView.action">查看更多>></a></p>

</td>
</tr>
			
				</table>
			</div>


			<!--Table End-->
			
			
			<!--Table2-->
			<s:set name="list2" value="#request.list2"></s:set>
			<div class="mb10" style="position: relative;top:-208px;left:50%;">
				<table width="48%" class="table_1">
					<thead>
						<th colspan="15">
							资产管理公司
						</th>
					</thead>
					<tbody>
						<tr class="tit">
							<td>
								姓名
							</td>
							<td>
								电子邮件
							</td>
							<td>
								手机号码
							</td>
							<td>
								单位电话
							</td>
							<td>
								详细信息
							</td>
						</tr>
						<s:iterator value="#list2" id="items" status="s">
							<tr>
							
								<td class="t_c" style="width: 300px;">
									<s:property value="#items[0]" />
								</td>
							
								<td class="t_c" style="width: 150px;">
									<s:property value="#items[1]" />
								</td>

							
								<td class="t_c" style="width: 150px; text-align: left;">
									<s:property value="#items[2]" />
								</td>
								
								<td class="t_c" style="width: 150px; text-align: right;">
									<s:property value="#items[4]" />
								</td>
								
								<td class="t_c" style="width: 150px; text-align: left;">
										<a href="personAddressList.action?login_name=<s:property value="#items[3]"/>" target="_blank">查看</a>												
													
								</td>
														
							</tr>
						</s:iterator>
					</tbody>
<tr>
<td colspan="5">
<p align="right">
<a href="sortView1.action" >查看更多>></a>
</p>
</td>
</tr>
			
				</table>
			</div>


			<!--Table2 End-->
			
			
			<!--Table3-->
			<s:set name="list3" value="#request.list3"></s:set>
			<div class="mb10" style="position: relative;top:-190px;">
				<table width="48%"  class="table_1">
					<thead>
						<th colspan="15">
							维修保障中心
						</th>
					</thead>
					<tbody>
						<tr class="tit">
							<td>
								姓名
							</td>
							<td>
								电子邮件
							</td>
							<td>
								手机号码
							</td>
							<td>
								单位电话
							</td>
							
							<td>
								详细信息
							</td>
						</tr>
						<s:iterator value="#list3" id="items" status="s">
							<tr>								
								<td class="t_c" style="width: 300px;">
									<s:property value="#items[0]" />
								</td>
								
								<td class="t_c" style="width: 150px;">
									<s:property value="#items[1]" />
								</td>

								
								<td class="t_c" style="width: 150px; text-align: left;">
									<s:property value="#items[2]" />
								</td>
								
								<td class="t_c" style="width: 150px; text-align: right;">
									<s:property value="#items[4]" />
								</td>
								
								<td class="t_c" style="width: 150px; text-align: left;">
										<a href="personAddressList.action?login_name=<s:property value="#items[3]"/>" target="_blank">查看</a>												
								</td>					
							</tr>
						</s:iterator>
					</tbody>
					<tr>
<td colspan="5">
<p align="right">
<a href="sortView2.action" >查看更多>></a>
</p>
</td>
</tr>
				</table>
			</div>
			<!--Table3 End--><!--
			<p align="right" ><a href="showGroupList.action" style="color: red;">查看更多>></a></p>
		--></div>
		
		
		</div>
	</body>

</html>
