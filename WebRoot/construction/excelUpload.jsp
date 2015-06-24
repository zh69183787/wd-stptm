<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>文件上传</title>

<link href="../css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" language="javascript" src="../js/swfobject.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.uploadify.v2.1.4.min.js"></script>

<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />

 
<script src="../js/html5.js"></script><!--
<script src="../js/jquery-1.7.1.js"></script> -->
<script src="../js/jquery.formalize.js"></script>


<script type="text/javascript">
$(function(){
	$(".odd tr:odd").css("background","#fafafa");			
});

//关闭窗口
function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}

$(document).ready(function() {
	$('#file_upload').uploadify({
	    'uploader'  : '../js/uploadify.swf',
	    'script'    : 'UploadAndSavdData.action',
	    'cancelImg' : '../images/cancel.png',
	    'fileDataName' : 'uploadify', 
	    'sizeLimit' : '5000000', 
	    'folder'    : 'uploads',
	    'fileExt'   : '*.xlsx;*.xls',
	    'fileDesc'  : '支持格式:xls,xlsx',
	    'buttonText': 'file',
	    'onSelect'	: function(){$("#uploadButton").attr('disabled',false);},
	    'onCancel'  : function(){$("#uploadButton").attr('disabled',true);},
	    'auto'      : false,
	    'method'	:'post',
	    'onProgress':function(){
	    				$("#showMsg").html("上传中，请等待……");
	    			},
	    'onComplete':function(event, ID, fileObj, response, data) {
	    			  	$("#showMsg").html("");
	    			  	
	    			  	var data = response.split("|");
	    			  	var addHtml ="<td class='t_r lableTd' width='250px'>上传信息</td><td id='show' width='200px' colspan='2'>";
	    			  	
	    			  	//$("#showTR").css("display","block");
	    			  	
						if(data.length>1){
							for(var i=0;i<data.length;i++){
								addHtml += data[i] +"<p>";
							}
							addHtml += "</td>";
							$("#showTR").html(addHtml); 		
						}else{
							addHtml += data +"<p>";
							addHtml += "</td>";
							$("#showTR").html(addHtml); 	
						}	    			  	
				    }
	  });
});


function uploadifyUpload(){   
    $('#file_upload').uploadifyUpload();   
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
	<li><a href="#">路网施工计划排定</a></li>
   	<li class="fin">文件上传</li>
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

</div>
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">

<table width="100%" class="table_1">
	<thead>
		<th colspan="5" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd" >上传文件</td>
			
			<td width="250px;" colspan="2">
				<input id="file_upload" type="file" name="uploadify" value="上传" /><span id="showMsg"></span>
				<input type="button" value="上传" disabled="disabled" id="uploadButton" onclick="uploadifyUpload()" style="display:inline"/>
				<input type="button" value="模板下载" id="uploadButton" onclick="window.location='downloadFile.action'"/>
			</td>
		</tr>
		 
		<tr id="showTR">
		</tr>
		
		<!-- 
		<tr>
			<td class="t_r lableTd" width="250px">模板下载</td>
			<td width="200px;" colspan="2">
				<a href="downloadFile.action">模板.xls</a>
			</td>
		</tr>
		  -->
		
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/>
		</td>
	</tr>
</table>

</div>
<!--Table End-->
</body>
</html>
