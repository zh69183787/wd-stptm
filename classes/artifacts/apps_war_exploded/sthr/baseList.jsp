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
<title>人事信息列表</title>
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
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
}  
</script>		
        <script type="text/javascript">
        
        var newWindow = ""; 
        
        $(function(){
			$('#birthdayStart').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'birthdayStart',//仅作为“清除”按钮的判断条件						
				"yearRange":'1900:+nn' 
			});
						
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			$('#birthdayEnd').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'birthdayEnd',//仅作为“清除”按钮的判断条件
				"yearRange":'1900:+nn' 
			});	
			
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="birthdayStart") $("#birthdayStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="birthdayEnd") $("#birthdayEnd").val("");              
            });
		});	
        
        //控制权限
        function changeLimit(){
          var limit = $("#limitSelect").val();
          $.ajax({
      		  type: 'POST',
			  url: 'changeLimit.action?limit='+limit,			  			  
			  error: function(){
					alert('系统错误,请与管理员联系!');
				},
			  success: function(){	
			    alert("权限修改成功！");
			  }	
      		});
        }
        
        //导出EXCEL
        function exportExcel(){          
          var updateTimeOrder = $("#updateTimeOrder").val();
          var hretName = $("#hretName").val();
          var jobNumber = $("#jobNumber").val();
          var name = $("#name").val();
          var sex = $("input[id='sex']:checked").val();
          if(sex==null)sex="";
          var birthdayStart = $("#birthdayStart").val();
          var birthdayEnd = $("#birthdayEnd").val();
          var cCompany = $("#cCompany").val();
          var politicalLandscape = $("#politicalLandscape").val();
          var isRetire = $("#isRetire").val();
          //var position = $("#position").val();
          var param = "?updateTimeOrder="+updateTimeOrder+"&hretName="+hretName+"&jobNumber="+jobNumber+"&name="+name+"&sex="+sex+"&birthdayStart="+birthdayStart+"&birthdayEnd="+birthdayEnd+"&politicalLandscape="+politicalLandscape+"&isRetire="+isRetire+"&cCompany="+cCompany;    
          //alert(param);    
          window.location.href="exportExcel!exportExcel.action"+param;
        }
        
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
				
			  var h_cCompany=$("#h_cCompany");
			  var cCompany=$("#cCompany");		  
			  for(var i=1;i<cCompany.children("option").length;i++){
			    if(cCompany.children("option:eq("+i+")").val()==h_cCompany.val())
			    cCompany.children("option:eq("+i+")").attr("selected",true);
			  }	
			  
			  var h_isRetire=$("#h_isRetire");
			  var isRetire=$("#isRetire");		  
			  for(var i=1;i<isRetire.children("option").length;i++){
			    if(isRetire.children("option:eq("+i+")").val()==h_isRetire.val())
			    isRetire.children("option:eq("+i+")").attr("selected",true);
			  }
			  
			  var h_politicalLandscape=$("#h_politicalLandscape");
			  var politicalLandscape=$("#politicalLandscape");		  
			  for(var i=1;i<politicalLandscape.children("option").length;i++){
			    if(politicalLandscape.children("option:eq("+i+")").val()==h_politicalLandscape.val())
			    politicalLandscape.children("option:eq("+i+")").attr("selected",true);
			  }
			
				
			$('[id=show_sex]').each(
      			function(index){
      				var html = $(this).html();
      				html=(html.replace("1","男")).replace("0","女");
      				$(this).html(html);
      			}
      		)
      		
      		
      		$('[id=show_cCompany]').each(
      			function(index){
      				var html = $(this).html();
      				if(html.length>24){
      				  html=html.substr(0,24)+"...";
      				  $(this).html(html);
      				}  
      			}
      		)
      		
      		var limit = $("#limit").val();
      		if(limit=='1'){
      		  $("#limitSelect").children("option:eq(0)").attr("selected",true);
      		}
      		if(limit=='0'){
      		  $("#limitSelect").children("option:eq(1)").attr("selected",true);
      		}
      		
      		var limitFlag = 0;
      		var limitPerson = new Array();
      		limitPerson = $("#limitPerson").val().split(",");
      		for(var i=0;i<limitPerson.length;i++) {
      		  if(limitPerson[i]==$("#updatePerson").val()){
      		    limitFlag = 1;
      		    break;
      		  }
      		}
      		if(limitFlag==0){
      		  $("#limitSelect").remove();
      		  $("#limitButton").remove();
      		}
      		
      		
      		$.ajax({
      		  type: 'POST',
			  url: 'findAllTypeName.action',
			  dataType:'json',			  
			  error: function(){
					alert('系统错误,请与管理员联系!');
				},
			  success: function(obj){	
			    var allTypeName = "<option value=''>全部</option>";
					for(var i=0;i<obj.length;i++){
						if($("#typeName").val()==obj[i].typeName){
							allTypeName +="<option value='"+obj[i].typeName+"' selected>"+obj[i].typeName+"</option>";
						}else{
							allTypeName +="<option value='"+obj[i].typeName+"'>"+obj[i].typeName+"</option>";
						}
					}
					$("#hretName").html(allTypeName);
			  }	
      		});
      	
        

//双击修改
         $("tbody").find("input[type=hidden]").each(function(i,n){ 
          var value = $(n).attr("value");
          var tr = $(n).parent("td").parent("tr");
          var td1 =$(tr).children("td:eq(1)");
          var td2 =$(tr).children("td:eq(2)");
          var td3 =$(tr).children("td:eq(3)");
          var td4 =$(tr).children("td:eq(4)");
          var td5 =$(tr).children("td:eq(5)");
          var td6 =$(tr).children("td:eq(6)");
          var td7 =$(tr).children("td:eq(7)");                    
          var td9 =$(tr).children("td:eq(9)");
          var html9 = td9.html();
          //$(tr).bind("dblclick");
	      $(tr).dblclick(tddblclick);          
	      function tddblclick(){
	        $(tr).unbind("dblclick");
	        
	         
	        var text1 = td1.text();
	        var text2 = td2.text();
	        var text3 = td3.text();
	        var text4 = td4.text();
	        var text5 = td5.text();
	        var text6 = td6.text();
	        var text7 = td7.text();
	       	        
	        td1.html("");
	        td2.html("");
	        td3.html("");
	        td4.html("");
	        td5.html("");
	        td6.html("");
	        td7.html("");	        
	        td9.html("");
	        
	        var input1 = $("<input type=\"text\" size=\"9\" maxlength=\"10\"> ");
	        var input2 = $("<input type=\"text\" size=\"5\" maxlength=\"20\">");
	        if(text3=="男")  {var input3=$("<input type=\"radio\" name=\"if_confirm\" value=\"1\" checked=\"checked\">男<input type=\"radio\" name=\"if_confirm\" value=\"0\">女<input type=\"hidden\">");}
	        else {var input3=$("<input type=\"radio\" name=\"if_confirm\" value=\"1\" >男<input type=\"radio\" name=\"if_confirm\" value=\"0\" checked>女<input type=\"hidden\">");}
	        //var input3 = $("<input type=\"text\" size=\"1\">");	        
	        var input4 = $("<input type=\"text\" readonly=\"readonly\" id=\"indicatorDate1\" size=\"6\">");
	        var input5 = $("<input type=\"text\" size=\"8\" maxlength=\"11\">");
	        var input6 = $("<input type=\"text\" size=\"40\" maxlength=\"100\">");
	        var input7 = $("<input type=\"text\" size=\"15\" maxlength=\"20\">");
	        var input9 = $("<a id='saveEdit' class='fl mr5' href='#' >保存 </a><a id='cancelEdit' class='fl mr5' href='#'>取消 </a>" );
	        
	        input1.attr("value", text1);
	        input2.attr("value", text2);
	        //input3.attr("value", text3);
	        input4.attr("value", text4);
	        input5.attr("value", text5);
	        input6.attr("value", text6);
	        input7.attr("value", text7);
	        
	        td1.append(input1);
	        td2.append(input2);
	        td3.append(input3);
	        td4.append(input4);
	        td5.append(input5);
	        td6.append(input6);
	        td7.append(input7);
	        td9.append(input9);
	        
	        $('#indicatorDate1').datepicker({
				//inline: true
				"changeYear":true,
				"yearRange":'1900:+nn' 
			});
			
	        
	        $("#saveEdit").click(function(){
	          var inputtext1 = input1.val().replace(/(^\s*)|(\s*$)/g,'');
	          if(inputtext1=="") {alert("工号不能为空");input1.focus();return false;}
	          var inputtext2 = input2.val().replace(/(^\s*)|(\s*$)/g,'');
	          if(inputtext2=="") {alert("姓名不能为空");input2.focus();return false;}
	          var inputtext3 = $('input:radio:checked').val(); 		               
	          var inputtext4 = input4.val().replace(/(^\s*)|(\s*$)/g,'');
	          var inputtext5 = input5.val().replace(/(^\s*)|(\s*$)/g,'');
	          var inputtext6 = input6.val().replace(/(^\s*)|(\s*$)/g,'');
	          var inputtext7 = input7.val().replace(/(^\s*)|(\s*$)/g,'');
	          var updatePerson = $("#updatePerson").val();
	          
	          td1.html(inputtext1);
	          td2.html(inputtext2);
	          td3.html(inputtext3.replace("1","男").replace("0","女"));
	          td4.html(inputtext4);
	          td5.html(inputtext5);
	          td6.html(inputtext6);
	          td7.html(inputtext7);
	          td9.html(html9);
	          
	          $.ajax({
				url: 'updateHrBInfoList.action',
				type: 'POST',
				data:{
					"hrId" : value,
					"jobNumber" : inputtext1,
					"name" : inputtext2,
					"sex" : inputtext3,
					"birthday" : inputtext4,
					"mobilePhone" : inputtext5,
					"cCompany" : inputtext6,
					"position" : inputtext7,
					"updatePerson" : updatePerson,
					"random" : Math.random()
				},						
				error: function(){
					alert('系统错误,请与管理员联系!');
				},
				success: function(){				
					$(tr).dblclick(tddblclick);
				}
			});     
	       });
	       
	       $("#cancelEdit").click(function(){
	         td1.html(text1);
	         td2.html(text2);
	         td3.html(text3);
	         td4.html(text4);
	         td5.html(text5);
	         td6.html(text6);
	         td7.html(text7);
	         td9.html(html9);
	         $(tr).dblclick(tddblclick);
	       });
	       
	       
	      //$(tr).unbind("dblclick");
	      }
	      
        });	
        	
        });
        
        
        
        function clearInput(){
          $("#jobNumber").val("");
          $("#name").val("");
          $("#sex").attr("checked",false);          
          $("#cCompany").children("option:eq(0)").attr("selected",true);
          //$("#position").val("");
          $("#politicalLandscape").children("option:eq(0)").attr("selected",true);
          $("#isRetire").children("option:eq(0)").attr("selected",true);
          $("#birthdayStart").val("");
          $("#birthdayEnd").val("");  
          $("#hretName").children("option:eq(0)").attr("selected",true);                                        
        }
        
       function submitFormOrderById(order){
		$("#updateTimeOrder").val(order);
		//$("#orderColumn").val(orderColumn);
		//$("#order").val(order);
		$("#form").submit();
				
	   }
       
        function check(){
          
		  
		  if($("#birthdayStart").val()!=null && $("#birthdayStart").val()!="" && !$("#birthdayStart").val().match(/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/)){
        		alert("请使用控件输入合法的时间");        		      		
        		$("#birthdayStart").focus();
        		return false;
        	}
        	
        	if($("#birthdayEnd").val()!=null && $("#birthdayEnd").val()!="" && !$("#birthdayEnd").val().match(/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/)){
        		alert("请使用控件输入合法的时间");        		      		
        		$("#birthdayEnd").focus();
        		return false;
        	}
        	if($("#birthdayStart").val()!=null && $("#birthdayStart").val()!="" && $("#birthdayEnd").val()!=null && $("#birthdayEnd").val()!="" && $("#birthdayEnd").val()<$("#birthdayStart").val()){
        		alert("起始时间不能大于结束时间");        		      		
        		$("#birthdayEnd").focus();
        		return false;
        	}
    		return true; 
        }
        
        //每1秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				var number = $("#number").val();
				$("#pageNo").val(number);
				$("#form").submit();				
				clearInterval(refresh);
			}
		}
		
        function deleteData(hrId){
      	  if(confirm("确定删除?")){
      		window.location.href="deleteByHrId!deleteByHrId.action?hrId="+hrId;      		
      	  }
        }
        
        function dataAdd(){
          newWindow=window.open('toAdd.action');
        }
        
        function editData(hrId){
          newWindow=window.open("findHrBInfoById.action?hrId="+hrId+"&type=edit");
        }
        //跳转到制定页
        function goPage(pageNo,type){
			//type=0,直接跳转到制定页
	       if(type=="0"){
	       		
	       		var pageCount = $("#totalPageCount").val();
	       		var number = $("#number").val();
	       		if(!number.match(/^[0-9]*$/)){
	       			alert("请输入数字");
	       			$("#number").val("");
	       			$("#number").focus();
	       			return ;
	       		}
	       		if(parseInt(number)>parseInt(pageCount)){
	       			$("#number").val(pageCount);
	       			$("#pageNo").val(pageCount);
	       		}else{
	       			$("#pageNo").val(number);
	       		}
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#pageNo").val(parseInt($("#number").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	            //alert($("#number").val());	       		
	       		$("#pageNo").val(parseInt($("#number").val())+1);
	       		//alert($("#pageNo").val());
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#pageNo").val(pageNo);
	       }
       	   $("#form").submit();

        }
$(function(){
	 $("#imgLeft").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 );
});        
    </script>



</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>	
<input type="hidden" id="typeName" value="<s:property value='#request.hretName'/>">
<input type="hidden" id="updatePerson" name="updatePerson" value="<%=loginName %>">
<input type="hidden" id="h_cCompany" name="h_cCompany" value="<s:property value='hrBInfoVO.cCompany'/>">
<input type="hidden" id="h_isRetire" name="h_isRetire" value="<s:property value='hrBInfoVO.isRetire'/>">
<input type="hidden" id="h_politicalLandscape" name="h_politicalLandscape" value="<s:property value='hrBInfoVO.politicalLandscape'/>">
<input type="hidden" id="limit" value="<s:property value='#request.limit'/>"/>
<input type="hidden" id="limitPerson" value="<s:property value='#request.limitPerson'/>"/>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="imgLeft" src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">工会人事数据库</a></li>                	
                	<li class="fin">人事信息列表</li>
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
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
        	<div class="query p8">
        	<s:form action="findHrBInfoByPage" id="form" name="HrBInfo" namespace="/sthr">        	
        	<input type="hidden" id="updateTimeOrder" name="updateTimeOrder" value="<s:property value='#request.updateTimeOrder'/>"/>
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r">工号</td>
        	      <td>
       	          <input type="text" name="jobNumber" id="jobNumber" class="input_large" value="<s:property value="hrBInfoVO.jobNumber"/>"></td>
        	      <td class="t_r">姓名</td>
        	      <td><input type="text" name="name" id="name" class="input_large" value="<s:property value="hrBInfoVO.name"/>"></td>
      	          
      	          <td class="t_r">出生日期</td>
        	      <td >
        	      <input readonly="readonly" name="birthdayStart" id="birthdayStart" type="text" value="<s:property value='#request.birthdayStart'/>" class="input_small"/>至
        	      <input readonly="readonly" name="birthdayEnd" id="birthdayEnd" type="text" value="<s:property value='#request.birthdayEnd'/>" class="input_small"/>
        	      </td> 
      	          
        	      
        	   </tr>
        	   <tr>
        	      <!-- 
        	      <input type="date" class="input_large" id="birthday" name="birthday" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date format='yyyy-MM-dd' name='hrBInfoVO.birthday'/>"/>
        	       -->
        	      
        	      <!-- 
        	      <td class="t_r">目前职务</td>
        	      <td>
       	          <input type="text" name="position" id="position" class="input_large" value="<s:property value="hrBInfoVO.position"/>"></td>
        	       -->
        	     <td class="t_r">性别</td>
        	      <td>
        	      <s:if test="hrBInfoVO.sex==1">
        	        <input type="radio" id="sex" name="sex" value="1" checked="checked"/>男
        	      </s:if>
        	      <s:else>
        	        <input type="radio" id="sex" name="sex" value="1"/>男
        	      </s:else>
        	      <s:if test="hrBInfoVO.sex==0">
        	        <input type="radio" id="sex" name="sex" value="0" checked="checked"/>女
        	      </s:if>
        	      <s:else>
        	        <input type="radio" id="sex" name="sex" value="0"/>女
        	      </s:else>
        	     </td>      	       
        	     
      	        
        	     
        	     <td class="t_r">政治面貌</td>
        	     <td><select name="politicalLandscape" id="politicalLandscape" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="中共党员">中共党员</option>
                                    <option value="民主党派">民主党派</option>
                                    <option value="共青团员">共青团员</option>
                                    <option value="群众">群众</option>                                    
                                </select></td> 
        	      
      	      
      	          <td class="t_r">目前工作单位</td>
        	      <td colspan="4">
        	      <select name="cCompany" id="cCompany" class="input_large" style="width:220px">
                                    <option value="">---请选择---</option>   
                                    <option value="集团机关">集团机关</option> 
                                    <option value="上海共和新路高架发展有限公司">上海共和新路高架发展有限公司</option>
                                    <option value="上海轨道交通长宁线发展有限公司">上海轨道交通长宁线发展有限公司</option>
                                    <option value="上海轨道交通二号线东延伸发展有限公司">上海轨道交通二号线东延伸发展有限公司</option>
                                    <option value="上海轨道交通明珠线发展有限公司">上海轨道交通明珠线发展有限公司</option>
                                    <option value="上海轨道交通宝山线发展有限公司">上海轨道交通宝山线发展有限公司</option>
                                    <option value="上海轨道交通明珠线（二期）发展有限公司">上海轨道交通明珠线（二期）发展有限公司</option>
                                    <option value="上海莘闵轨道交通线发展有限公司">上海莘闵轨道交通线发展有限公司</option> 
                                    <option value="上海轨道交通浦东线发展有限公司">上海轨道交通浦东线发展有限公司</option>   
                                    <option value="上海轨道交通七号线发展有限公司">上海轨道交通七号线发展有限公司</option>
                                    <option value="上海轨道交通杨浦线发展有限公司">上海轨道交通杨浦线发展有限公司</option>
                                    <option value="上海轨道交通申松线发展有限公司">上海轨道交通申松线发展有限公司</option>
                                    <option value="上海轨道交通十号线发展有限公司">上海轨道交通十号线发展有限公司</option> 
                                    <option value="上海轨道交通申嘉线发展有限公司">上海轨道交通申嘉线发展有限公司</option>                                   
                                    <option value="上海轨道交通十一号线南段发展有限公司">上海轨道交通十一号线南段发展有限公司</option>
                                    <option value="上海轨道交通十二号线发展有限公司">上海轨道交通十二号线发展有限公司</option>
                                    <option value="上海轨道交通十三号线发展有限公司">上海轨道交通十三号线发展有限公司</option>
                                    <option value="上海申通地铁股份有限公司">上海申通地铁股份有限公司</option>
                                    <option value="上海申通轨道交通研究咨询有限公司">上海申通轨道交通研究咨询有限公司</option>
                                    <option value="上海地铁资产经营管理有限公司">上海地铁资产经营管理有限公司</option>   
                                    <option value="上海黄浦江大桥建设有限公司">上海黄浦江大桥建设有限公司</option>  
                                    <option value="上海轨道交通运营管理中心">上海轨道交通运营管理中心</option>  
                                    <option value="上海地铁第一运营有限公司">上海地铁第一运营有限公司</option>                                    
                                    <option value="上海地铁第二运营有限公司">上海地铁第二运营有限公司</option>                                    
                                    <option value="上海地铁第三运营有限公司">上海地铁第三运营有限公司</option>
                                    <option value="上海地铁第四运营有限公司">上海地铁第四运营有限公司</option>
                                    <option value="上海轨道交通维护保障中心">上海轨道交通维护保障中心</option>
                                    <option value="上海轨道交通维护保障中心车辆分公司">上海轨道交通维护保障中心车辆分公司</option>
                                    <option value="上海轨道交通维护保障中心供电分公司">上海轨道交通维护保障中心供电分公司</option>
                                    <option value="上海轨道交通维护保障中心工务分公司">上海轨道交通维护保障中心工务分公司</option>
                                    <option value="上海轨道交通维护保障中心通号分公司">上海轨道交通维护保障中心通号分公司</option>
                                    <option value="上海轨道交通维护保障中心物流和后勤分公司">上海轨道交通维护保障中心物流和后勤分公司</option>                                    
                                    <option value="上海轨道交通培训中心">上海轨道交通培训中心</option>
                                    <option value="上海轨道交通技术研究中心">上海轨道交通技术研究中心</option>  
                                    <option value="上海轨道交通资金管理中心">上海轨道交通资金管理中心</option>
                                    <option value="上海轨道交通信息管理中心">上海轨道交通信息管理中心</option>
                                    <option value="上海轨道交通资产管理中心">上海轨道交通资产管理中心</option>
                                    <option value="上海轨道交通建设管理中心">上海轨道交通建设管理中心</option> 
                                    <option value="上海市隧道工程轨道交通设计研究院">上海市隧道工程轨道交通设计研究院</option>  
                                    <option value="上海地铁运营人力资源有限公司">上海地铁运营人力资源有限公司</option>
                                </select>
        	      </td>
      	      </tr>
      	      <tr>
      	        <td class="t_r">在职或退休</td>
                                <td><select name="isRetire" id="isRetire" class="input_large">
                                  <option value="">---请选择---</option>
                                  <option value="在职">在职</option>
                                  <option value="退休">退休</option>
                                </select></td>
                                
      	        <td class="t_r">数据类别</td>
      	         <td><select name="hretName" class="input_large" id="hretName">
        	          <option value="">---全部---</option>        	          
      	         
      	         </select></td>
      	      </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" onclick="return check();"/>
      &nbsp;&nbsp;
<input type="button" value="取 消" onclick="clearInput()"/> &nbsp;&nbsp;
<input type="button" value="导出EXCEL" onclick="exportExcel()"/>
</td>
       	        </tr>
      	    </table>
      	    </s:form>
        	</div>
            <div class="fn">
	            <table width="100%">
		            <tr>
			            <td>
			              <input type="button" name="button2" id="button2" value="新 增" class="new" onclick="dataAdd()">
			            </td>
			            <td style="text-align: right">
			              <select id="limitSelect">
			                <option value="1">各单位员工只能查看其所在单位的人事信息</option>
			                <option value="0">没有权限，可以查看所有人事信息</option>
			              </select>
			              <input type="button" name="limitButton" id="limitButton" value="确  认" class="new" onclick="changeLimit()">
			            </td>
		            </tr>
	            </table>
            </div>
      </div>
      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="18">工会人事数据库</th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td>序号</td>
                                <td>工号</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>出生日期</td>                                                           
                                <td>手机</td>
                                <td>工作单位</td>
                                <td>职务</td>
                                <td >更新日期<a style="display:inline" href="#" onclick="submitFormOrderById('<s:if test="#request.updateTimeOrder=='asc'">desc</s:if><s:else>asc</s:else>')"><img style="display:inline" src="../images/<s:if test="#request.updateTimeOrder=='asc'">up</s:if><s:else>down</s:else>.png"></img></a></td>
                                <td>操作</td>
                              </tr>
                              
                               <s:iterator value="#request.page.result" status="st">
	 	                          <tr id="dataTR">
	 	                            <td class="t_c"><s:property value="#st.index+#request.page.start"/><input type="hidden" value="<s:property value='hrId'/>"/></td>
	 	                            <td class="t_c"><s:property value="jobNumber"/></td>
	 	                            <td class="t_c"><s:property value="name"/></td>
	 	                            <td class="t_c" id="show_sex"><s:property value="sex"/></td>
	 	                            <td class="t_c" id="show_birthday"><s:date name="birthday" format="yyyy-MM-dd"/></td>	                      	 	                            
	 	                            <td class="t_c"><s:property value="mobilePhone"/></td>
	 	                            <td id="show_cCompany" title="<s:property value='cCompany'/>"><s:property value="cCompany"/></td>
	 	                            <td class="t_c"><s:property value="position"/></td> 
	 	                            <td class="t_c"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>                      
	 	                            <td class="t_c">
	  	                            <a class="fl mr5" href="findHrBInfoById.action?hrId=<s:property value='hrId'/>&type=view" target="_blank" >查看</a> 
	  	                            <a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='hrId'/>')">删除</a> 
	  	                            <a class="fl mr5" href="#" onclick="editData('<s:property value='hrId'/>')">修改</a>
	  	                            
	  	                            </td>
	                            </tr>
	                            </s:iterator>
	                            
	                            
	                            
	                            
                              </tbody>
                              <s:if test="#request.page.totalSize!=0">
                              <tr class="tfoot">
        	      <td colspan="13"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-
        	      <s:if test="#request.page.totalSize<(#request.page.start+#request.page.pageSize-1)">
        	        <s:property value="#request.page.totalSize"/>
        	      </s:if>
        	      <s:else>
        	        <s:property value="#request.page.start+#request.page.pageSize-1"/>
        	      </s:else>
        	      条</span>
        	        <ul class="fr clearfix pager">
        	          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
        	          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
        	            <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>"/>
        	            <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
      	            </li>
        	          
                       <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
                       	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
                       </s:if>
                       <s:else>
                        <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
                       </s:else> 
                      <li>
                      	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
                      		<a href="javascript:void(0)">下一页</a>
                      	</s:if>
                      	<s:else>
                      		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
                      	</s:else>
                      </li>
                      <li>
                      	<s:if test="#request.page.currentPageNo==1">
                      		<a href="javascript:void(0)">上一页</a>
                      	</s:if>
                      	<s:else>
                      		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
                      	</s:else>
                      </li> 
                      <s:if test="#request.page.currentPageNo==1">
                      	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
                      </s:if>
                      <s:else>
                      	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
                      </s:else>
      	          </ul>
      	        </div></td>
      	      </tr></s:if><s:else>
      	      <tr class="tfoot"><td colspan="13"><div class="clearfix">无相关数据</div></td>
   	          </tr>
      	      </s:else>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>


