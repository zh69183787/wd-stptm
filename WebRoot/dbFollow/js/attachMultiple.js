		var groupid_single = getGroupId();
		var groupid = new Array();
		for(var i=0;i<20;i++){
			groupid[i] = groupid_single + "-" + i;
		}
        
		function getGroupId(){
			var date = new Date();
			var groupid ="";
			groupid += date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds();
			return groupid; 
		}		

		$(document).ready(function() {
			$("[id=attach]").each(function(index){
				if($(this).val()==""){
					//$(this).val(groupid[index]);
				}else{
					groupid[index] = $(this).val();
				}
			});
			
			var urlLocation = basePath+"/tAttach/upload.action?groupid=";
			$("[id^=file_upload]").each(function(index){
				$("#file_upload"+index).uploadify({
				    'uploader'  : '../js/uploadify.swf',
				    'script'    : urlLocation + groupid[index+1],
				    'cancelImg' : '../images/cancel.png',
				    'fileDataName' : 'uploadify', 
				    'folder'    : 'uploads',
				    'fileExt'   : '*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.rar;*.jpg;*.jpeg;*.png;*.gif;*.txt;',
				    'fileDesc'  : '支持格式:DOC,DOCX,XLS,XLSX,PDF,RAR,JPG,JPEG,PNG,GIF,TXT',
				    'buttonText': 'file',
				    'onSelect'	: function(){$("#uploadButton"+index).attr('disabled',false);},
				    'onCancel'  : function(){$("#uploadButton"+index).attr('disabled',true);},
				    'auto'      : false,
				    'sizeLimit'	: '10240000',
				    'onComplete':function(event, queueID, fileObj, response, data){
				    				showData("edit",index+1);
				    			}
				});
			});
		});
		
		function uploadifyUpload(index){   
		    $("#file_upload"+index).uploadifyUpload();   
		} 
		
		function showData(type,index){
			var urlLocation = basePath+"/tAttach/findTAttachByGroupID.action?groupID="+groupid[index];
			var newData = "";
			$.ajax({
				type: 'POST',
				url: urlLocation,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
							for(var i=0;i<obj.length;i++){
								if(type=="edit"){
									newData +="<br/><a href='"+basePath+"/tAttach/downloadFile.action?fileName="+obj[i].savefilename+"&realName="+obj[i].filename+"."+obj[i].fileextname+"' style='float: left'>"+obj[i].filename+"."+obj[i].fileextname+"</a><img style='float:left' alt='删除' src='../images/cancel.png' onClick='deleteData("+obj[i].id+","+index+")'>";
								}else if(type=="view"){
									if(i>0){
										newData += "<br>";
									}
									newData +="<a href='"+basePath+"/tAttach/downloadFile.action?fileName="+obj[i].savefilename+"&realName="+obj[i].filename+"."+obj[i].fileextname+"' style='float: left'>"+obj[i].filename+"."+obj[i].fileextname+"</a>";
								}
							}
							$("[id=attachSpan]:eq("+index+")").html(newData);
							if(type=="edit"){
								if(newData==""){
									$("[id=attach]:eq("+index+")").val("");
								}else{
									$("[id=attach]:eq("+index+")").val(groupid[index]);
								}
							}
						}
			});  
        }
       
       function deleteData(id,index){
	       	$.ajax({
				type: 'POST',
				url: basePath+"/tAttach/deleteTAttachById.action?id="+id,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
							showData("edit",index);
						}
			});  
       }