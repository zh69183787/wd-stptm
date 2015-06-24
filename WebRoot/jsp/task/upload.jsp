<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>盘点任务上传</title>

<link href="../css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" language="javascript" src="../js/swfobject.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.uploadify.v2.1.4.min.js"></script>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />

 <!--
<script src="../js/html5.js"></script>
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

//uploadFile.action
$(document).ready(function() {
	$('#file_upload').uploadify({
		'script'    : 'uploadFile.action',
	    'uploader'  : '../js/uploadify.swf',
	    'cancelImg' : '../images/cancel.png',
	    'fileDataName' : 'file', 
	    'sizeLimit' : '50000000',
	    'folder'    : 'uploads',
	    'fileExt'   : '*.txt',
	    'fileDesc'  : '支持格式:txt',
	    'buttonText': 'file',
	    'onSelect'	: function(){$("#uploadButton").attr('disabled',false);},
	    'onCancel'  : function(){$("#uploadButton").attr('disabled',true);},
	    'auto'      : false,
	    'onProgress': function(){
	    				$("#showMsg").html("上传中，请等待……");
	    			},
	    'onComplete':function(event, ID, fileObj, response, data) {
	    			  	$("#showMsg").html("");
	    			  	var addHtml ="<td class='t_r lableTd'>&nbsp;</td><td id='show'>"+response+"</td>";
	    			  	$("#showTR").html(addHtml); 	
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
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
   	<li>资产管理</li>
   	<li class="fin">盘点任务上传</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li><a class="print" href="#">打印</a></li>
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
		<th colspan="2" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd" style="white-space: nowrap;width: 200px;">上传文件</td>
			<td>
				<input id="file_upload" type="file" name="file" value="上传" />
				<input  type="button" value="上传" disabled="disabled" id="uploadButton" onclick="uploadifyUpload()" />
				<span id="showMsg"></span>
			</td>
		</tr>
		<tr id="showTR">
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="2" class="t_r">
			<input type="button" value="关 闭" onclick="shut()"/>
		</td>
	</tr>
</table>

</div>
<!--Table End-->
</body>
</html>
