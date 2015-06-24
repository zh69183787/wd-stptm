<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.wondersgroup.stjt.core.UltimasUtil"%>
<%@page import="com.wondersgroup.stjt.userconfig.action.UserConfigAction"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员(多选)</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  <%
  		String deptId = StringUtil.getNotNullValueString(request.getParameter("para0"));
  		List persons = UltimasUtil.getPersonByDeptId(deptId);
  		String temp = "";
  		String personId = "";
  		String personName = "";
  %>
  <script language="javascript">
  		function sValue1(){
  			var obj = document.all.persons; 
            var temp = "";
            	if(obj){

             		for(var i=0;i<obj.length;i++){
                	
                		if(obj[i].checked){
                 			temp += obj[i].lable +",";
               
               			} 
             		}
             		document.all.perTxts.value = temp.substr(0, temp.length-1); 
            	}
  			
  		}
  		function sValue(){
  			var obj = document.all.persons;
  			var temp = "";
  			var temp1 = "";
  			for(var i=0; i<obj.length; i++){
  				if(obj[i].checked){
  					temp += obj[i].value + ",";
  					temp1 += obj[i].lable + ",";
  				}
  			}
  			document.all.personIds.value = temp.substr(0, temp.length-1);
  		
			window.dialogArguments.idField.value = document.all.personIds.value;
			window.dialogArguments.nameField.value = temp1.substr(0, temp1.length-1);
			window.close();
  		}
  		
  		function dS(val){
  			var array;
  			if(val!=''){
  			array = val.split(",");
  			var obj = document.all.persons;
  			if(obj){
  				for(var j=0;j<array.length;j++){
             	for(var i=0;i<obj.length;i++){
                	if(obj[i].lable==array[j]){
                 		obj[i].checked='checked';
                	 	document.all.perTxts.value += obj[i].lable+",";
                	 	break;
               		} 
               		
             	} 
             	}
             	document.all.perTxts.value = document.all.perTxts.value.substr(0,document.all.perTxts.value.length-1);
        	}
        }
  		}
  </script>
  <!-- onload="javascript:jV();" -->
  <body onload=javascript:dS(window.dialogArguments.nameField.value);>
    	<input type="hidden" name="personIds" id="personIds">
    	<table border=0 cellspacing=0 cellspadding=0 align="left">
  			<tr>
  				<td align="center">
  					<center><input type="text" name="perTxts" id="perTxts" style="width:200"></center>
  				</td>
  				<td align="center">
  					<input type="button" name="确定" value="确定" onclick="javascript:sValue();">
  				</td>
  				<td align="center">
  					<input type="button" name="关闭" value="关闭" onclick="javascript:window.close();">
  				</td>
  			</tr>
  		</table>
    	<br><br>
    	<table border=0 cellspacing=0 cellspadding=0 align="left">
    	<% 
  			Iterator it = persons.iterator();
  			while(it.hasNext()){
  				temp = (String)it.next();
  				personId = temp.split(":")[0];
  				personName = temp.split(":")[1];
  		%>
    		<tr>
    			<td>
    				<input type="checkbox" name="persons" value="<%=personId%>" lable="<%=personName%>" onclick="javascript:sValue1();"><span class="fontcolor"><%=personName%></span>
    			</td>
    			
    		</tr>
    	<% 
    		}
    	%>
    	</table>
  </body>
</html>
