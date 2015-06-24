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
function reply(type){
	var href= 'newNotice.action?reply='+type+'&sid='+$('#sid').val()+'&sender='+$('#sender').html();
	window.location.href=href;
}		

function deleteNotice(){
	if (!confirm("确定删除?")) {
				return;
			}
			
		$.ajax({
			url : 'deleteItem.action',
			data : {
				sid : $('#sid').val(),
				msgType:1
			},

			type : "POST",
			dataType : 'json/xml/html/text',
			cache : false,
			async : false,
			success : function (data) {
				alert("删除成功！");
				window.close();
			},
			error : function (data) {
				alert("删除成功！");
				window.close();
			}
		});

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
                	<li><a href="#">通知内容</a></li>                	
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
        <input type="hidden" value="<s:property value="msgUsermessage.empidsend"/>" id="empidsend">
        <input type="hidden" value="<s:property value="msgUsermessage.sid"/>" id="sid">
        <input type="hidden" value="<s:property value="msgUsermessage.msgType"/>" id="msgType">
        <input type="hidden" id="hrId" name="hrId" value="<s:property value='hrBInfoVO.hrId'/>"> 
        <input type="hidden" id="h_politicalLandscape" name="h_politicalLandscape" value="<s:property value='hrBInfoVO.politicalLandscape'/>">  
        <input type="hidden" id="h_highestDegree" name="h_highestDegree" value="<s:property value='hrBInfoVO.highestDegree'/>"> 
       	  <table width="100%"  class="table_1">
	          <thead>
	            <th colspan="4" class="t_r">
	            	<input type="button" value="答复收件人" onclick="reply(1);">&nbsp;
	            	<input type="button" value="转发" onclick="reply(2);">&nbsp;
	            	<input type="button" value="打印" onclick="window.print()">&nbsp;
	            	<input type="button" value="删除" onclick="deleteNotice();">
	        	      &nbsp;
	        	      <input type="button" value="关 闭" onclick="shut();"/>
	        	      &nbsp;        	      
	       	      </th>
	          </thead>
				<tbody>
					<tr>
						<td class="t_r lableTd" width="250px;"><b>发件人</b></td>
						<td id="sender">
							<s:property value="msgUsermessage.sender"/>
						</td>
                        
                        <td class="t_r lableTd" width="250px;"><b>收件人</b></td>
						<td>
							<s:property value="msgUsermessage.receiver"/>&nbsp;&nbsp;
							
						<s:if test="msgUsermessage.msgcount==0">
							<input type="checkbox" disabled />手机短信提醒
						</s:if>
						<s:else>
							<input type="checkbox" disabled checked="true"/>手机短信提醒
						</s:else>
				
							
						</td>
                        
					</tr>
                    
                    <tr>
						<td class="t_r lableTd" width="250px;"><b>发送时间</b></td>
						<td>
							<s:property value="msgUsermessage.senddate"/>
						</td>
                        
                        <td class="t_r lableTd" width="250px;"><b>阅读时间</b></td>
						<td>
							<s:property value="msgUsermessage.seedate"/>
						</td>
                        
					</tr>
                    
					<tr>
						<td class="t_r lableTd" ><b>主题</b></td>
                        <td class="t_r lableTd" colspan="3">
                        <div  id="title"  style="background-color: white; ;width:100%; text-align:left; overflow-y:auto;">
                        	<s:property value="msgUsermessage.title" escape="false"/>
                        </div>
                        </td>
					</tr>                   
				   <tr>
						<td class="t_r lableTd" ><b>内容</b></td>
                        <td class="t_r lableTd" colspan="3">
                        <div  id="content"  style="background-color: white; height:300px;;width:100%; text-align:left; overflow-y:auto;" readonly>
                        	<s:property value="msgUsermessage.content" escape="false"/>
                        </div>
                        </td>
					</tr>
                    
                    
					
				<tr class="tfoot">
                	<td colspan="4" class="t_r">
						<input type="button" value="关 闭" onclick="shut();"/>
					</td>
                </tr>
         </table>
          
         
        </div>
        <!--Table End-->
</div>
</body>
</html>
