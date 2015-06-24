function shut(){
  window.opener=null;
  window.open("","_self");
  window.close();
}

function check(){

	  var planProjectName = $("#planProjectName");
	  if(planProjectName.val()==''){
		  alert("请输入计划项目名称！");
		  planProjectName.focus();
		  return false;	  
	  }  
	  
	  var applyCompany = $("select[name=applyCompanyId]");
	  if(applyCompany.val()==''){
		  alert("请选择申报单位！");
		  applyCompany.focus();
		  return false;	  
	  }
	  $('[name=applyCompany]').val(applyCompany.find("option:selected").text());  
	
	var reg = new RegExp("^[0-9]+([.]{1}[0-9]+){0,1}$");  
	var estimate=$("#estimate");
	 if(!reg.test($.trim(estimate.val()))){  
	     alert("概算请输入数字!");  
	     estimate.focus();
	     return false;
	 }
	
  var according=$("#according");
  if(according.val().length > 500) {
    alert("立项依据最多输入500字！");
    according.focus();
    return false;
  }
  
  var plan=$("#plan");
  if(plan.val().length > 2000) {
    alert("实施方案最多输入2000字！");
    plan.focus();
    return false;
  }
  
  var target=$("#target");
  if(target.val().length > 2000) {
    alert("项目目标最多输入2000字！");
    target.focus();
    return false;
  }
  
  var remark=$("#remark");
  if(remark.val().length > 2000) {
    alert("备注最多输入2000字！");
    remark.focus();
    return false;
  }
  
}
