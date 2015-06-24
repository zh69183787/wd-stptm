		var groupid = getGroupId();
        
		function getGroupId(){
			var date = new Date();
			var groupid ="";
			groupid += date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds();
			return groupid; 
		}		

		$(document).ready(function() {
			if($("#attach").val()==""){
				$("#attach").val(groupid);
			}else{
				groupid = $("#attach").val();
			}
			var urlLocation = basePath+"/tAttach/upload.action?groupid="+groupid;
			$('#file_upload').uploadify({
			    'uploader'  : '../js/uploadify.swf',
			    'script'    : urlLocation,
			    'cancelImg' : '../images/cancel.png',
			    'fileDataName' : 'uploadify', 
			    'folder'    : 'uploads',
			    'fileExt'   : '*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.rar;*.jpg;*.jpeg;*.png;*.gif;*.txt;',
			    'fileDesc'  : '支持格式:DOC,DOCX,XLS,XLSX,PDF,RAR,JPG,JPEG,PNG,GIF,TXT',
			    'buttonText': 'file',
			    'onSelect'	: function(){$("#uploadButton").attr('disabled',false);},
			    'onCancel'  : function(){$("#uploadButton").attr('disabled',true);},
			    'auto'      : false,
			    'sizeLimit'	: '10240000',
			    'onComplete':function(event, queueID, fileObj, response, data){
			    				showData("edit");
			    			}
			  });
		});
		
		function uploadifyUpload(){   
		    $('#file_upload').uploadifyUpload();   
		} 
		
		function showData(type){
			var urlLocation = basePath+"/tAttach/findTAttachByGroupID.action?groupID="+groupid;
			var newData = "";
			$.ajax({
				type: 'POST',
				url: urlLocation,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
							for(var i=0;i<obj.length;i++){
								if(type=="edit"){
									newData +="<br/><a href='"+basePath+"/tAttach/downloadFile.action?fileName="+obj[i].savefilename+"&realName="+obj[i].filename+"."+obj[i].fileextname+"' style='float: left'>"+obj[i].filename+"."+obj[i].fileextname+"</a><img style='float:left' alt='删除' src='../images/cancel.png' onClick='deleteData("+obj[i].id+")'>";
								}else if(type=="view"){
									if(i>0){
										newData += "<br>";
									}
									newData +="<a href='"+basePath+"/tAttach/downloadFile.action?fileName="+obj[i].savefilename+"&realName="+obj[i].filename+"."+obj[i].fileextname+"' style='float: left'>"+obj[i].filename+"."+obj[i].fileextname+"</a>";
								}
							}
							$("#attachSpan").html(newData);
							
						}
			});  
        }
       
       function deleteData(id){
	       	$.ajax({
				type: 'POST',
				url: basePath+"/tAttach/deleteTAttachById.action?id="+id,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
							showData("edit");
						}
			});  
       }