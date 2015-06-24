String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}

$(function(){
	$('[id^=planSubmitTime]').each(function(){
		$(this).datepicker({
			//inline: true								
			"changeYear":true,
			"minDate":0
		});
	});
});	

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

$(function(){
	var i = 0;
	$("[id=dataTr]").each(function(){
		if($(this).find("#followDeptId").val()==""){
			if(i>0){
				$(this).hide();
			}
			i++;
		}
	});
});

function addRow(obj){
	if($(obj).parents("tr").next().attr("style")!=null&&$(obj).parents("tr").next().attr("style").indexOf("display:none")){
		if($.trim($(obj).val())!=""){
			$(obj).parents("tr").next().show();
		}
	}
}

function saveAdd(){
	var str = "";
	var index = 0;
	var flag = true;
	$("[id=dataTr]").each(function(){
		if($.trim($(this).find("#followDeptName").val())!=""&&$(this).find("#followDeptId").val()==""){
			flag = false;
			alert("请选择正确的单位！");
			$(this).find("#followDeptName").focus();
		}else if($.trim($(this).find("#followDeptName").val())!=""&&$(this).find("#followDeptId").val()!=""){
			index++;
			if($.trim($(this).find("#require").val())==""){
				flag = false;
				alert("请填写要求！");
				$(this).find("#require").focus();
				return false;
			}
			if($.trim($(this).find("[id^=planSubmitTime]").val())==""){
				flag = false;
				alert("请填写计划提交时间！");
				$(this).find("[id^=planSubmitTime]").focus();
				return false;
			}					
			if(index>1){
				str += ",";
			}
			str += "{\"id\":\""+$(this).find("#followChildId").val()+"\",\"followDeptId\":\""+$(this).find("#followDeptId").val()+"\",\"followDeptName\":\""+$(this).find("#followDeptName").val()+"\",\"require\":\""+$(this).find("#require").val()+"\",\"planSubmitTime\":\""+$(this).find("[id^=planSubmitTime]").val()+"\",\"removed\":\""+$(this).find("#removed").val()+"\"}";
		}
	});
	if(index==0){
		alert("请选择跟踪部门！");
	}else{
		if(flag){
			str = "["+str+"]";
			//str = $.parseJSON(str); 
			$("[name=childInfo]").val(str); 
			$("#form").submit();				
		}
	}
}
	
//自动提示模糊搜索，单位/部门
$(function(){
	$("[id=followDeptName]").each(function(){
		var saveStatus = false;
		$(this).autocomplete({
			autoFouces : true,
			source: function( request, response ) {
				$.ajax({
					url: "findDeptByName.action?random="+Math.random(),
					dataType: "json",
					data: {
						"name" : $.trim(request.term),				
						"random" : Math.random()
					},
					success: function( data ) {
						response( $.map( data.params, function( item,index ) {
							return {
								label: item[1],
								value: item[0]
							}
						}));
					}
				});
			},
			minLength: 1,
			search: function() {
				saveStatus = false;
			},
			select: function( event, ui ) {
				$(this).siblings("#followDeptId").val(ui.item.value);
				$(this).val(ui.item.label);
				saveStatus = true;
				return false;
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			},
			focus: function( event, ui ) {
					$(this).siblings("#followDeptId" ).val("");
					return false;
			},
			change : function(){
				if(!saveStatus){
					$(this).siblings("#followDeptId").val("");
				}
			}
		});
	});
});