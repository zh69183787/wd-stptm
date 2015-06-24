$(function(){
	$('#lastDate').datepicker({
		//inline: true
		"changeYear":true,
		"showButtonPanel":true,	
		"closeText":'清除',	
		"currentText":'lastDate'//仅作为“清除”按钮的判断条件
	});
	$('#finishDate').datepicker({
		//inline: true
		"changeYear":true,
		"showButtonPanel":true,	
		"closeText":'清除',	
		"currentText":'finishDate'//仅作为“清除”按钮的判断条件
	});
	$('#inputDate').datepicker({
		//inline: true
		"changeYear":true,
		"showButtonPanel":true,	
		"closeText":'清除',	
		"currentText":'inputDate'//仅作为“清除”按钮的判断条件
	});
	//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
		
	$(".ui-datepicker-close").live("click", function (){              
      if($(this).parent("div").children("button:eq(0)").text()=="lastDate") $("#lastDate").val("");
      if($(this).parent("div").children("button:eq(0)").text()=="finishDate") $("#finishDate").val("");
      if($(this).parent("div").children("button:eq(0)").text()=="inputDate") $("#inputDate").val("");            
    });
	
});	

function shut(){
  window.opener=null;
  window.open("","_self");
  window.close();
}

function check(){
  //去除多余空格
  $("#major").val($("#major").val().replace(/(^\s*)|(\s*$)/g,''));
  $("#workPerson").val($("#workPerson").val().replace(/(^\s*)|(\s*$)/g,''));
  
  var linesNum = 0;
  var linesAll;
  $('[id=lines]').each(
		function(index){
			if($(this).attr("checked")=="checked"){
			  linesNum = linesNum +1; 
			  if(linesNum==1){
			    linesAll = $(this).val();
			  }else{
			    linesAll = linesAll+","+$(this).val();
			  }  
			}
		}
	)
  if(linesNum==0){
    alert('请选择线路/处所'); 
    return false;
  }else{
    $("#linesAll").val(linesAll);
  }
  
  var dangersState=$("#dangersState");
  if(dangersState.val().replace(/(^\s*)|(\s*$)/g,'')=="") {
    alert("请输入隐患情况！");
    dangersState.focus();
    return false;
  }
  if(dangersState.val().length > 300) {
    alert("隐患情况最多输入300字！");
    dangersState.focus();
    return false;
  }
  
  var correctMethod=$("#correctMethod");
  if(correctMethod.val().length > 600) {
    alert("整改措施最多输入600字！");
    correctMethod.focus();
    return false;
  }
  
  var dangersClass=$("#dangersClass");
  if(dangersClass.val()=="") {
    alert("请选择隐患类型！");
    dangersClass.focus();
    return false;
  }
  
  var workState=$("#workState");
  if(workState.val()=="") {
    alert("请选择落实情况！");
    workState.focus();
    return false;
  }
  
  var supervisionDept_select = $("#supervisionDept_select").val();
  if(supervisionDept_select!=""){
  	$("#supervisionDept_hidden").val(supervisionDept_select.split("#")[0]);
  	$("#supervisionDeptId_hidden").val(supervisionDept_select.split("#")[1]);
  }else{
  	$("#supervisionDept_hidden").val("");
  	$("#supervisionDeptId_hidden").val("");
  } 
  
/*
  var supervisionDept = $("#supervisionDept");
  if(supervisionDept.val()!=""){
  	if($("#supervisionDeptId").val()==""){
  		alert("您填写的督办部门不存在！");
  		supervisionDept.focus();
  		return false;
  	}
  }
  
  var checkimportance = false;
  $('[name=importance]').each(
  	function(index){
  		if($(this).attr("checked")=="checked"){
  			checkimportance = true;
  		}
  	}
  )
  if(!checkimportance){
  	alert("请选择重要程度！"); 
    return false;
  }
*/
  var remark=$("#remark");
  if(remark.val().length > 600) {
    alert("备注最多输入600字！");
    remark.focus();
    return false;
  }
  
  //saveWorkDept($("#workDept"));
  var workDeptValue = "";
  var workDeptIdValue = "";
  $("[id=workDept_checkbox]:checked").each(function(index){
  	if(index>0){
  		workDeptValue += ",";
  		workDeptIdValue += ",";
  	}
  	workDeptValue += $(this).val().split("#")[0];
  	workDeptIdValue += $(this).val().split("#")[1];
  });
  if(workDeptIdValue!=""){
  	workDeptIdValue = ","+workDeptIdValue+",";
  }
  $("#workDept_hidden").val(workDeptValue);
  $("#workDeptId_hidden").val(workDeptIdValue);
  
  var workDept=$("#workDept_hidden");
  if(workDept.val()=="") {
  	alert("请选择责任单位！");
  	//$("#workDept_add").focus();
  	return false;
  }
  
  $("#inputDept").val($("#inputDept_show").val());
  $("#createPerson").val($("#createPerson_show").val());
  
  var getValue=$("#dangersState").val();
  var endValue=(getValue.replace(/<(.+?)>/gi,"&lt;$1&gt;")).replace(/\n/gi,"<br>");
  $("#dangersState").val(endValue);
  
  var getValue1=$("#correctMethod").val();
  var endValue1=(getValue1.replace(/<(.+?)>/gi,"&lt;$1&gt;")).replace(/\n/gi,"<br>");
  $("#correctMethod").val(endValue1);
  
  var getValue2=$("#remark").val();
  var endValue2=(getValue2.replace(/<(.+?)>/gi,"&lt;$1&gt;")).replace(/\n/gi,"<br>");
  $("#remark").val(endValue2);
  
  return true;		  
}

function clickAll(obj){
	$(obj).parent().find("[type=checkbox]").each(function(){
		if($(this).val()!='全路网'){
			if($(obj).attr("checked")=="checked"){
				$(this).attr("disabled",true);
				$(this).attr("checked",false);
			}else{
				$(this).attr("disabled",false);
			}
		}
	});
}

/*

function addWorkDept(obj){
	var getValue = $(obj).parent().children("input[type=text]").val().replace(/(^\s*)|(\s*$)/g,'');
	var getValueId = $(obj).parent().children("#workDeptId_add").val().replace(/(^\s*)|(\s*$)/g,'');
	var flag = true;
	$(obj).parent().find("p").each(function(){
		if($(this).find("#hidden_id").val()==getValueId){
			flag = false;
		}
	});
	if(getValue==""){
		alert("请选择单位/部门");
		$(obj).parent().children("input[type=text]").focus();
	}else if(getValueId==""){
		alert("没有此单位/部门");
		$(obj).parent().children("input[type=text]").focus();
	}else if(!flag){
		alert("相同单位请勿重复添加");
		$(obj).parent().children("input[type=text]").focus();
	}else{
		$(obj).parent().append("<p><input type='checkbox' id='companyCheck'/>&nbsp;&nbsp;<span style='display:inline'>" +getValue + "</span><input type='hidden' id='hidden_id' value='"+getValueId+"'/></p>");
		var num = $(obj).parent().find("p").length-1;
		$(obj).parent().children("input[type=text]").val("");
		$(obj).siblings("#workDeptId_add").val("");
	}
}
function deleteWorkDept(obj){
	var num = 0;
	$("input[id=companyCheck]").each(function(index){
		if($(this).attr("checked")=="checked"){
			num += 1;
			$(this).parent("p").remove();
		}
	});
	if(num==0){
		alert("请选择要删除的项目公司！");
	}
}
function saveWorkDept(obj){
	var saveValue = "";
	var saveValueId = ",";
	if($(obj).parent().find("p").length>0){
		$(obj).parent().children("p").each(function(index){
			if(index>0){
				saveValue += ",";
			}
			saveValue += $(this).children("span").html();
			saveValueId += $(this).children("#hidden_id").val()+",";
		});
	}
	$(obj).val(saveValue);
	$(obj).siblings("#workDeptId").val(saveValueId);
}

function loadWorkDept(obj){
	if($(obj).val()!=""){
		var getValueArray = new Array();
		var getValueIdArray = new Array();
		getValueArray = $(obj).val().split(",");
		getValueIdArray = $(obj).siblings("#workDeptId").val().split(",");
		for(var i=0;i<getValueArray.length;i++){
			$(obj).parent().append("<p><input type='checkbox' id='companyCheck'/>&nbsp;&nbsp;<span style='display:inline'>" +
					getValueArray[i] +"</span><input type='hidden' id='hidden_id' value='"+getValueIdArray[i+1]+"'/></p>");
		}
	}
}
*/