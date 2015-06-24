
function shut(){
  window.opener=null;
  window.open("","_self");
  window.close();
}

function check(){
/**
	if($("[name=correctMethod]").val().length>600){
		alert("整改措施不能超过600个字!!!");
		$("[name=correctMethod]").focus();
		  return false;
	}
	if($("[name=remark]").val().length>600){
		alert("备注不能超过600个字");
		$("[name=remark]").focus();
		  return false;
	}
  if($("[name=name]").val().length>10){
	  alert("姓名不能超过10个字！");
	  $("[name=name]").focus();
	  return false;
  }
  if($("[name=job]").val().length>50){
	  alert("职务不能超过50个字！");
	  $("[name=job]").focus();
	  return false;
  }
  if($("[name=phone]").val().length>50){
	  alert("电话不能超过50个字！");
	  $("[name=phone]").focus();
	  return false;
  }
  if($("[name=duty]").val().length>500){
	  alert("工作分工不能超过500个字！");
	  $("[name=duty]").focus();
	  return false;
  }
  var linesNum = 0;
  var linesAll;
  $('[id=lines]').each(//遍历复选框
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
  }*/
  if($("[name=rectificationMethod]").val().length>0&&$("[name=rectificationMethod]").val().length<600){}else{
	  alert("整改措施不能为空或字数超过600");
	  $("[name=rectificationMethod]").focus();
	  return false;
  }
  if($("[name=targetNode]").val().length>600){
	  alert("节点目标字数不能超过600");
	  $("[name=targetNode]").focus();
	  return false;
  }
  if($("[name=cwip]").val().length>600){
	  alert("进展情况字数不能超过600");
	  $("[name=cwip]").focus();
	  return false;
  }
  return true;
}

function clickAll(obj){
	var lines=document.getElementsByName("line");
	//var lines=document.getElementById("lines");
	var len=lines.length;
	var i;
	if(lines[0].checked==true){
		for(i=1;i<len;i++){
			lines[i].disabled=true;
		}
	}
	else if(lines[0].checked==false){
		for(i=1;i<len;i++){
			lines[i].disabled=false;
		}
	}
}