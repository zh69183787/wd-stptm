<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.UserInfo" %>
<%@ page import="com.wonders.stpt.CrossIpLogin" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%
Properties properties = new Properties();
String path = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
properties.load(new FileInputStream(path));
String filterButton=properties.getProperty("filterButton");
String loginName = "";
if("on".equals(filterButton)){
	CrossIpLogin crossIpLogin = new CrossIpLogin();
	UserInfo userInfo = new UserInfo();
	crossIpLogin.setUserInfo(request,userInfo);
	loginName = userInfo.getLoginName();
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>人事扩展信息数据项编辑</title>
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
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		function clearData(){
		
			$("#itemName").attr("value","");
			$("#sortingOrder").attr("value","");
		}
		
		
		$(document).ready(function(){
			var status;
			status = $("#itemNameStatus").val();
			if(status!=null && status!=""){
				alert(status);
				//$("#itemName").attr("value","");
				$("#itemName").focus();
			}
			status = $("#sortingOrderStatus").val();
			if(status!=null && status!=""){
				alert(status);
				//$("#sortingOrder").attr("value","");
				$("#sortingOrder").focus();
			}
			status = $("#outOfDB").val();
			if(status!=null && status!=""){
				alert(status);
				clearData();
			}
			status = $("#outOfShow").val();
			if(status!=null && status!=""){
				alert(status);
			}
			
			
			if($("#inputType").val().substring(0,3)=='下拉框'){
			  $("#dd").html("下拉框");
			  var optionInputType = $("#inputType").val().substring(14);			  
			  $("#sss").after("<tr><td class=\"t_r lableTd\">下拉框数据项</td><td id='ff'><textarea id='gg'>"+optionInputType+"</textarea></td></tr>");
			}
			if($("#inputType").val().substring(0,3)=='多选框'){
			  $("#dd").html("多选框");
			  var optionInputType = $("#inputType").val().substring(4);			  
			  $("#sss").after("<tr><td class=\"t_r lableTd\">多选框数据项</td><td id='ff'><textarea id='hh'>"+optionInputType+"</textarea></td></tr>");
			}
			
		});
		
		function checkForm(){
			
			var value ;
			value = $("#itemName").val();
			if(value==""){
				alert("请填写数据项名称");
				$("#itemName").focus();
				return false;
			}
			
			$("#itemName").val($("#itemName").val().replace(/(^\s*)|(\s*$)/g,''));
			
			value = $("#sortingOrder").val();
			if(value==""){
				alert("请填写排列顺序");
				$("#sortingOrder").focus();
				return false;
			}
			$("#sortingOrder").val($("#sortingOrder").val().replace(/(^\s*)|(\s*$)/g,''));
			if(!$("#sortingOrder").val().match(/^[0-9]*$/)){
				alert("请填写数字");
				//$("#sortingOrder").val("");
				$("#sortingOrder").focus();
				return false;
			}
			
			//replaceall方法
			String.prototype.replaceAll  = function(s1,s2){  
              return this.replace(new RegExp(s1,"gm"),s2);   
            }
			
			if($("#dd").html()=="下拉框"){			  
			  //alert("ff="+$("#gg").val());
			  if($("#gg").val()==""){
			    alert("下拉框数据项不能为空");
			    $("#gg").focus();
			    return false;
			  }
			  var textArea = $("#gg").val().replaceAll("，",",");
			  var inputType = "下拉框";
			  inputType = inputType+","+"---请选择---,"+textArea;	
			  if(inputType.length>2000)	{alert("下拉框数据项最多输入2000字");	  $("#gg").focus();return false;}
			  $("#inputType").val(inputType);		  
			  
			}
			
			if($("#dd").html()=="多选框"){
			  if($("#hh").val()==""){
			    alert("多选框数据项不能为空");
			    $("#hh").focus();
			    return false;
			  }
			  var textArea = $("#hh").val().replaceAll("，",",");
			  var inputType = "多选框";
			  inputType = inputType+","+textArea;	
			  if(inputType.length>2000)	{alert("多选框数据项最多输入2000字");	  $("#hh").focus();return false;}
			  $("#inputType").val(inputType);
			}
			return true;
		}
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
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
	<li><a href="#">工会人事数据库</a></li>
   	<li><a href="#">扩展信息类别</a></li>
   	<li><a href="#">数据项</a></li>
   	<li class="fin">编辑</li>
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
<!--Ctrl End--> <!--Tabs_2-->
<!-- 
<div class="tabs_2">
<ul>
	<li class="selected"><a href="#"><span>公会人事数据库</span></a></li>
	
	<li><a href="#"><span>待定名称</span></a></li>
	<li><a href="#"><span>待定名称</span></a></li>
	<li><a href="#"><span>待定名称</span></a></li>
	
</ul>
 -->
</div>
<!--Tabs_2 End--> <!--Filter--><!--Filter End--> <!--Table-->
<div class="mb10">


<input type="hidden" name="" value="<s:property value='#request.itemNameStatus'/>" id="itemNameStatus">
<input type="hidden" name="" value="<s:property value='#request.sortingOrderStatus'/>" id="sortingOrderStatus">
<input type="hidden" name="" value="<s:property value='#request.outOfDB'/>" id="outOfDB">
<input type="hidden" name="" value="<s:property value='#request.outOfShow'/>" id="outOfShow">


<s:form action="editHrEtD" name="HrEtD" namespace="/sthr">
<input type="hidden" name="updatePerson" value="<%=loginName %>"/>
<input type="hidden" name="hretId" value="<s:property value='#request.hrEtD.hretId'/>" />
<input type="hidden" name="hretdId" value="<s:property value='#request.hrEtD.hretdId'/>" />
<input type="hidden" name="itemFName" value="<s:property value='#request.hrEtD.itemFName'/>" />
<input type="hidden" name="inputType" id="inputType" value="<s:property value='#request.hrEtD.inputType'/>" />
<input type="hidden" name="isShow" value="<s:property value='#request.hrEtD.isLShow'/>" />

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 
		</th>
	</thead>
	<tbody>
		<tr>
			<td class="t_r lableTd">类别名称</td>
			<td>
				<input type="hidden"" id="typeName" name="typeName" class="input_xlarge" value="<s:property value='#request.hrEtD.typeName'/>"/>
				<s:property value="#request.hrEtD.typeName"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">数据项名称</td>
			<td><input type="text" id="itemName" name="itemName" class="input_xlarge" value="<s:property value='#request.hrEtD.itemName'/>" maxlength="20"/></td>
		</tr>
		<tr id="sss">
			<td  class="t_r lableTd">录入方式</td>
			<td id="dd">
				<!-- 
				<select name="inputType" id="inputType" class="input_large">
				<s:if test="#request.hrEtD.inputType=='普通文本'">
					 <option value="普通文本" selected="selected">普通文本</option>
        	         <option value="长文本">长文本</option>
        	         <option value="数字">数字</option>
        	         <option value="日历">日历</option>
				</s:if>
				<s:elseif test="#request.hrEtD.inputType=='长文本'">
					 <option value="普通文本">普通文本</option>
        	         <option value="长文本" selected="selected">长文本</option>
        	         <option value="数字">数字</option>
        	         <option value="日历">日历</option>
				</s:elseif>
				<s:elseif test="#request.hrEtD.inputType=='数字'">
					 <option value="普通文本">普通文本</option>
        	         <option value="长文本">长文本</option>
        	         <option value="数字" selected="selected">数字</option>
        	         <option value="日历">日历</option>
				</s:elseif>
				<s:elseif test="#request.hrEtD.inputType=='日历'">
					 <option value="普通文本">普通文本</option>
        	         <option value="长文本">长文本</option>
        	         <option value="数字">数字</option>
        	         <option value="日历" selected="selected">日历</option>
				</s:elseif>
                </select>-->
                <s:property  value="#request.hrEtD.inputType"/>
			</td>
		</tr>
		<tr>
			<td class="t_r lableTd">排列顺序</td>
			<td><input type="text" id="sortingOrder" name="sortingOrder" class="input_large" value="<s:property value='#request.hrEtD.sortingOrder'/>" maxlength="3"/></td>
		</tr>
		<tr>
			<td class="t_r lableTd">是否有效</td>
			<td>
				<s:if test="#request.hrEtD.isLShow==1">
					<input type="radio" id="isLShow_1" name="isLShow" value="1" checked="checked"/>是
  					<input type="radio" id="isLShow_2" name="isLShow" value="0"/>否
				</s:if>
				<s:else>
					<input type="radio" id="isLShow_1" name="isLShow" value="1"/>是
  					<input type="radio" id="isLShow_2" name="isLShow" value="0" checked="checked"/>否
				</s:else>
				
			</td>
		</tr>
	</tbody>
	<tr class="tfoot">
		<td colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/>
		</td>
	</tr>
</table>
</s:form>

</div>
<!--Table End--></div>
</body>
</html>
