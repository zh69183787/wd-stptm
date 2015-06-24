<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
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
<title>扩展信息编辑</title>
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
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		$(function(){
			$("input[id^=itemFNameSS]").each(function(){
				$(this).datepicker({
					"changeYear":true,
					"showButtonPanel":true,	
				    "closeText":'清除',	
					"currentText":$(this).attr("id"),//仅作为“清除”按钮的判断条件	
					"yearRange":'1900:+nn' 
			     });
			});
			
			$(".ui-datepicker-close").live("click", function (){              
              var text = $(this).parent("div").children("button:eq(0)").text();
              $("input[id^=itemFNameSS]").each(function(index){
                if(text==$(this).attr("id")) $(this).val("");
              });
            });
		});	
			
		
		$(document).ready(function () {
		  
		  
		  var data=new Array();
		  var oData = new Array();
		  var cData = new Array();
		  $("[id=hidden_data]").each(
		    function(index){		      
		      data[index]=$(this).val();		      
		    }
		  )
		  $("[id^=itemFName]").each(
		    function(index){		      
		      $(this).val(data[index]);		      
		    }
		  )
		  
		  $("[id=itemFName][name=aa]").each(
		    function(index){
		      oData[index]=$(this).val();
		    }
		  )
		  $("[id=itemFName][name=bb]").each(
		    function(index){
		      cData[index]=$(this).val();
		    }
		  )
		  
		  var sOption = "";
		  var aOption = new Array();
		  var optionAdd = "";
		  $("input[id=sOption]").each( 
		    function(index){	
		      sOption = $(this).val();
		      aOption = sOption.split(",");
		      optionAdd = "<option value=''>"+aOption[1]+"</option>";
		      for (var i=2 ; i< aOption.length ; i++){		        
		          if(oData[index]==aOption[i]) optionAdd += "<option value='"+aOption[i]+"' selected='selected'>"+aOption[i]+"</option>";
		          else optionAdd += "<option value='"+aOption[i]+"'>"+aOption[i]+"</option>";      		         
		      }
		      $(this).parent("td").find("#option").html(optionAdd);		      	
		    }
		  );
		  
		  var cOption = "";
		  var optionFlag = 0;
		  var splitCData = new Array();		  
		  $("input[id=cOption]").each( 
		    function(index){			      
		      cOption = $(this).val();
		      aOption = cOption.split(",");
		      optionAdd = "";
		      splitCData = cData[index].split(",");
		      for (var i=1 ; i< aOption.length ; i++){
		        for(var j=0;j<splitCData.length;j++){
		          if(splitCData[j]==aOption[i]) {
		            optionAdd += "<input type='checkbox' id='checkbox"+index+"' value='"+aOption[i]+"' checked='checked'/>"+aOption[i]+"&nbsp;&nbsp;";
		            optionFlag = 1;
		            break;
		          }else{
		            optionFlag = 0;
		          }
		        }
		        if(optionFlag == 0){
		          optionAdd += "<input type='checkbox' id='checkbox"+index+"' value='"+aOption[i]+"'/>"+aOption[i]+"&nbsp;&nbsp;";
		        }
		      }
		      $(this).parent("td").append(optionAdd);		      	
		    }
		  );	
		});
				
		function checkForm(){			
			$("[id=itemFName]").each(			  
			   function(index){			    
			    var value =$(this).val().replace(/(^\s*)|(\s*$)/g,'');	
			    $(this).val(value);			   
			  }
			)
			
			var status = true; 			
			$("[type=date]").each(			  
			   function(index){			    
			    var value =$(this).val();	
			    var name= $(this).parent("td").prev().html();			    
			    if(value!=""&&!value.match(/^-?\d+[.\d]{0,}$/)){
			      $(this).focus();			      
			      status = false;
			      alert(name+"项仅能填写数字！");	
			      return false;			      
			    }
			  }
			)
			
			if(!status){
				return false;
			}
			
			var checkboxText = new Array();
			var checkboxId = "";
			$("[id^=checkbox]").each(
			    function(index){
			      checkboxId = $(this).attr("id").substring(8,9);
			      if($(this).attr("checked")){
			        if(checkboxText[checkboxId]==null){
			          checkboxText[checkboxId] = $(this).val();			          
			        }else{
			          checkboxText[checkboxId] = checkboxText[checkboxId]+","+$(this).val();
			        }
			      }	      			      
			    }
			);
			$("input[id='cExt']").each(
			  function(index){				    
			    if(checkboxText[index]!=null){			      
			      $("form").append("<input type='hidden' name='"+$(this).val()+"' value='"+checkboxText[index]+"'>");
			    }
			  }  
			);		
			
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
	<li class="fin">“<s:property value="#request.typeName"/>”信息编辑</li>
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

<div class="mb10">

<s:form action="updateHrExtInfoById" name="HrExtInfo" namespace="/sthr">
 <input type="hidden" name="updatePerson" value="<%=loginName %>"/>
 <input type="hidden" id="itemFName" name="reportPerson" >
  <input type="hidden" id="itemFName" name="reportPersonName" >
  <input type="hidden" name="id" value="<s:property value='#request.id'/>"/>	
  <input type="hidden" name="hrId" value="<s:property value='#request.hrId'/>"/>
  <input type="hidden" name="hretId" value="<s:property value='#request.hretId'/>"/>
  <input type="hidden" name="hretName" value="<s:property value='#request.typeName'/>"/>
                <s:iterator value="#request.list3" >	  	      			    	      		
					      <input type="hidden" id="hidden_data" value="<s:property />"/>		    	      			   	      		
	   	      	</s:iterator>

<table width="100%" class="table_1">
	<thead>
		<th colspan="4" class="t_r">
			<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
			<input type="button" value="关 闭" onclick="shut()"/> &nbsp; 			
		</th>
	</thead>
	<tbody>
		<s:iterator value="#request.page.result" status="st"> 
		<tr>
		  <td class="t_r lableTd"><s:property value='itemName'/></td>		  
		  <s:if test="inputType=='普通文本（200字）'">
		    <td>
		    <input type="text" id="itemFName" name="<s:property value='itemFName'/>" class="input_xlarge" maxlength="2000"/>	   		    
		    </td>		    
		  </s:if>
		  <s:elseif test="inputType=='长文本（4000字）'">
		    <td colspan="3">
		    <textarea rows="5" id="itemFName" name="<s:property value='itemFName'/>"></textarea>
		    </td>
		  </s:elseif>
		  <s:elseif test="inputType=='日期（年月日）'">
		    <td>
		    <input type="tel" id="itemFNameSS<s:property value='#st.index'/>" name="<s:property value='itemFName'/>" />
		    </td>
		  </s:elseif>
		  <s:elseif test="inputType=='数字'">
		    <td>
		    <input type="date" id="itemFName" name="<s:property value='itemFName'/>" class="input_xlarge" />		   
		    </td>
		  </s:elseif>
		  <s:elseif test="itemFName=='sExt1'||itemFName=='sExt2'||itemFName=='sExt3'||itemFName=='sExt4'||itemFName=='sExt5'">
		    <td>
		      <input type="hidden" id="sOption" value="<s:property value='inputType'/>"/>
		      <input type="hidden" id="itemFName" name="aa"/>
		      <select name="<s:property value='itemFName'/>" id="option" class="input_large"></select>
		    </td>
		  </s:elseif>
		  <s:else>
		    <td>
		      <input type="hidden" id="cOption" value="<s:property value='inputType'/>"/>
		      <input type="hidden" id="cExt" value="<s:property value='itemFName'/>"/>
		      <input type="hidden" id="itemFName" name="bb"/> 
 		    </td>
		  </s:else>
		</tr>
		</s:iterator>
		
		
		
		
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
