<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.stpt.UserInfo" %>
<%@ page import="com.wonders.stpt.CrossIpLogin" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.UnsupportedEncodingException"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashMap"%>
<%
Map<String, String> cookieMap = new HashMap<String, String>();
Cookie[] cookies = request.getCookies();
String token=null;
String inputdeptId=null;
if(cookies !=null){
	for(int i=0;i<cookies.length;i++){
		Cookie cookie = cookies[i];
		cookieMap.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
		if("token".equals(cookie.getName())){
			try{
				token = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	//取填报部门id
	for (Cookie cookie:cookies) {
		if("deptId".equals(cookie.getName())){
			inputdeptId = cookie.getValue();
			break;
		}
	}
}
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>
<s:if test="#request.operateType==1">
隐患录入
</s:if>
<s:elseif test="#request.operateType==2">
隐患管理
</s:elseif>
<s:elseif test="#request.operateType==3">
隐患审核
</s:elseif>
<s:else>
隐患查询
</s:else>
</title>
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
        
        var newWindow = ""; 
        String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
		}
        
        $(function(){
			$('#lastDateStart').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'lastDateStart'//仅作为“清除”按钮的判断条件						
			});
						
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			$('#lastDateEnd').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'lastDateEnd'//仅作为“清除”按钮的判断条件
			});	
			
			$('#finishDateStart').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'finishDateStart'//仅作为“清除”按钮的判断条件						
			});
						
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			$('#finishDateEnd').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'finishDateEnd'//仅作为“清除”按钮的判断条件
			});	
			
			$('#inputDateStart').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'inputDateStart'//仅作为“清除”按钮的判断条件						
			});
						
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			$('#inputDateEnd').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'inputDateEnd'//仅作为“清除”按钮的判断条件
			});	
			
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="lastDateStart") $("#lastDateStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="lastDateEnd") $("#lastDateEnd").val("");     
              if($(this).parent("div").children("button:eq(0)").text()=="finishDateStart") $("#finishDateStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="finishDateEnd") $("#finishDateEnd").val("");        
              if($(this).parent("div").children("button:eq(0)").text()=="inputDateStart") $("#inputDateStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="inputDateEnd") $("#inputDateEnd").val("");                 
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
				
			
			var h_lines=$("#h_lines");
			  var lines=$("#lines");		  
			  for(var i=1;i<lines.children("option").length;i++){
			    if(lines.children("option:eq("+i+")").val()==h_lines.val())
			    lines.children("option:eq("+i+")").attr("selected",true);
			  }	
			
			var h_dangersClass=$("#h_dangersClass");
			  var dangersClass=$("#dangersClass");		  
			  for(var i=1;i<dangersClass.children("option").length;i++){
			    if(dangersClass.children("option:eq("+i+")").val()==h_dangersClass.val())
			    dangersClass.children("option:eq("+i+")").attr("selected",true);
			  }	
			  var h_workState=$("#h_workState");
			  var workState=$("#workState");		  
			  for(var i=1;i<workState.children("option").length;i++){
			    if(workState.children("option:eq("+i+")").val()==h_workState.val())
			    workState.children("option:eq("+i+")").attr("selected",true);
			  }	
			  var h_importance=$("#h_importance");
			  var importance=$("#importance");		  
			  for(var i=1;i<importance.children("option").length;i++){
			    if(importance.children("option:eq("+i+")").val()==h_importance.val())
			    importance.children("option:eq("+i+")").attr("selected",true);
			  }	
			  
			  var h_checkState=$("#h_checkState");
			  var checkState=$("#checkState");		  
			  for(var i=1;i<checkState.children("option").length;i++){
			    if(checkState.children("option:eq("+i+")").val()==h_checkState.val())
			    checkState.children("option:eq("+i+")").attr("selected",true);
			  }	
			  
			  var h_inputDept=$("#h_inputDept");
			  var inputDept=$("#inputDept");		  
			  for(var i=1;i<inputDept.children("option").length;i++){
			    if(inputDept.children("option:eq("+i+")").val()==h_inputDept.val())
			    inputDept.children("option:eq("+i+")").attr("selected",true);
			  }
			    
        	$("[id=remark_show]").each(
        	  function(index){
        	    $(this).unbind();
        	    $(this).bind({
        	      mouseenter: function(){
        	        if($(this).children("span").html()!=""){
        	          $(this).attr("title",$(this).children("span").text().replaceAll("<br>","\n"));
        	        }else{
        	          $(this).attr("title","无");
        	        }
        	      },
        	      mouseleave: function(){
			
		          }        	      
        	    });
        	  }
        	);
        	
        	$("[id=dangersState_show]").each(
        	  function(index){        	    
        	    var content = $(this).text();
        	    if(content.length>32){
        	    	content = content.substring(0,32)+"...";
        	    }
        	    $(this).html(content);
        	  }
        	);
        	
        	$("[id=correctMethod_show]").each(
        	  function(index){        	    
        	    var content = $(this).text();
        	    if(content.length>32){
        	    	content = content.substring(0,32)+"...";
        	    }
        	    $(this).html(content);
        	  }
        	);
        	
        	$("[id=dangersClass_show]").each(
        	  function(index){
        	    switch($(this).html()){
        	      case "1":$(this).html("设施设备");break;
        	      case "2":$(this).html("管理");break;
        	      case "3":$(this).html("规章制度");break;
        	      case "4":$(this).html("现场作业");break;
        	      case "5":$(this).html("新线遗留");break;
        	    }
        	  }
        	);
        	
        	$("[id=workState_show]").each(
        	  function(index){
        	    switch($(this).html()){
        	      case "1":$(this).html("已完成");break;
        	      case "2":$(this).html("整改中");break;
        	    }
        	  }
        	);
        	
        	$("[id=importance_show]").each(
        	  function(index){
        	    switch($(this).html()){
        	      case "0":{
        	      				$(this).html("一级");
        	      				$(this).parents("tr").attr("style","color:red");
        	      				break;
        	      			}
        	      case "1":$(this).html("三级");break;
        	      case "2":$(this).html("二级");break;
        	    }
        	  }
        	);
        	
        	$("[id=checkState_show]").each(
        	  function(index){
        	    switch($(this).html()){
        	      case "1":$(this).html("未审核");break;
        	      case "2":$(this).html("审核通过");break;
        	      case "3":$(this).html("返回修改");break;
        	    }
        	  }
        	);
        	
        	/*
        	//找到部门所在单位的id
        	var deptId = "<%=inputdeptId%>";
        	//var deptId = "2500";
        	console.log("deptId=="+deptId);
        	var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><id>"+deptId+"</id></dataParams>";
        	$.ajax({
				url: "findOwnerWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"token" : "<%=token%>",
					"method" : "getNodesInfo",				
					"dataType" : "json",
					"dataParams":dataParams
				},
				success: function( obj ) {
					console.log("result=="+JSON.stringify(obj));
					console.log("companyId=="+obj.params.param.pid);
					if(obj.params.param.pid!=null){
						$("#companyId").val(obj.params.param.pid);
					}
				}
			});
			*/
        });
        
        
        
        function clearInput(){
          $("#lines").children("option:eq(0)").attr("selected",true);
          $("#dangersClass").children("option:eq(0)").attr("selected",true);
          $("#lastDateStart").val("");
          $("#lastDateEnd").val("");  
          
          $("#major").val("");
          $("#workDept").val("");
          $("#finishDateStart").val("");
          $("#finishDateEnd").val("");  
          
          $("#workPerson").val("");          
          $("#workState").children("option:eq(0)").attr("selected",true);
          $("#inputDateStart").val("");
          $("#inputDateEnd").val("");  
          
          $("#importance").children("option:eq(0)").attr("selected",true);
          $("#inputDept").val("");
          $("#checkState").children("option:eq(0)").attr("selected",true);                                 
        }
     
        
        //每1秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				var number = $("#number").val();
				$("#pageNo").val(number);
				$("#form").submit();	//当前页面重新提交			
				clearInterval(refresh);
			}
		}
		
        function deleteData(id){
      	  if(confirm("确定删除?")){
      		$.ajax({
				url: "deleteById!deleteById.action?random="+Math.random(),
				dataType: "json",
				data: {
					"id" : id
				},
				success: function( data ) {
					window.location.reload();
				}
			});     		
      	  }
        }
        
        function dataAdd(){
        	
        	dept=<%=inputdeptId%>;
        	var flag=false;
        	var deptid=new Array("2921","2856","2922","2884","2923","2880","2924","2882","2920","2888",
        	"2516","2516","2561","2925","2582","2962");
        	for(var i=0;i<deptid.length;i++){
        		if(dept==deptid[i]){
        			flag=true;
        		}
        	}
        	if(flag){
          		newWindow=window.open('toAdd.action');
          	}else{
          		alert("您没有添加事务的权限");
          	}
        }
        
        function editData(id){
          newWindow=window.open("findHiddenDangersCorrectById.action?id="+id+"&type=edit");
        }
        
        function showOrHide(obj){
          if($(obj).attr("class")=="selected"){
            $("#search").hide();
            $(obj).attr("class","");
          }else{
            $("#search").show();
            $(obj).attr("class","selected");
          }
          
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
      
      	//自动提示模糊搜索，责任单位/部门
		$(function(){
		var saveStatus = false;
		$("input[name=workDept]" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><deptName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</deptName><typeId>0</typeId></dataParams>";
			$.ajax({
				url: "findOwnerWithFuzzySearch.action?random="+Math.random(),
				dataType: "json",
				data: {
					"type" : 'post',
					"token" : "<%=token%>",
					"method" : "getMatchedDepts",				
					"dataType" : "json",
					"dataParams":dataParams
				},
				success: function( data ) {
					response( $.map( data.params, function( item,index ) {
						return {
							label: item.description,
							value: item.id
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
			$("#workDeptId").val(ui.item.value);
			$("#workDept").val(ui.item.label);
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
				$( "#workDeptId" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#workDeptId").val("");
			}
		}
		});
		});

function checkFuc(type,id){
	var content = "";
	if(type=="2"){
		content = "确定审核通过?";
	}else if(type=="3"){
		content = "确定返回修改?";
	}
	
	if(confirm(content)){
      		$.ajax({
				url: "updateCheckState!updateCheckState.action?random="+Math.random(),
				dataType: "json",
				data: {
					"id" : id,
					"check_state":type
				},
				success: function(  ) {
					window.location.reload();
				}
			});     		
     }
}

function exportExcel(){
	$("#ifExport").val("yes");
	$("#form").submit();
	$("#ifExport").val("");
}
    </script>



</head>

<body>
<input type="hidden" id="h_lines" name="h_lines" value="<s:property value='hiddenDangersCorrectVO.lines'/>">
<input type="hidden" id="h_dangersClass" name="h_dangersClass" value="<s:property value='hiddenDangersCorrectVO.dangersClass'/>">
<input type="hidden" id="h_workState" name="h_workState" value="<s:property value='hiddenDangersCorrectVO.workState'/>">
<input type="hidden" id="h_importance" name="h_importance" value="<s:property value='hiddenDangersCorrectVO.importance'/>">
<input type="hidden" id="h_checkState" name="h_checkState" value="<s:property value='hiddenDangersCorrectVO.checkState'/>">
<input type="hidden" id="h_inputDept" name="h_inputDept" value="<s:property  value='hiddenDangersCorrectVO.inputDept'/>" />
	<div class="main" style="width:1304px;margin: auto;">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">隐患排查</a></li> 
                	<li>
                		<s:if test="#request.operateType==1">
						隐患录入
						</s:if>
						<s:elseif test="#request.operateType==2">
						隐患管理
						</s:elseif>
						<s:elseif test="#request.operateType==3">
						隐患审核
						</s:elseif>
						<s:else>
						隐患查询
						</s:else>
                	</li>
                	<li class="fin">运营安全隐患问题整改推进表【注：整改责任单位涉及两个及以上单位（部门）的，由第一位单位（部门）牵头整改。】 </li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                <!-- <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li> -->
                <li class="selected" onclick="showOrHide(this)"><a class="query" href="#">查询</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
        	<div class="query p8" id="search">
        	<s:form action="findHiddenDangersCorrectByPage" id="form" name="HiddenDangersCorrect" namespace="/hiddenDangersCorrect">        	
        	<input type="hidden" name="ifExport" id="ifExport"/>
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	<input type="hidden" name="companyId" id="companyId" value="<s:property value='#request.companyId'/>"/>
        	<input type="hidden"  value="<s:property value='#request.operateType'/>" name="operateType" id="operateType"/>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r" style="white-space:nowrap;">线路/处所</td>
        	      <td>
        	      <select name="lines" id="lines" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="全路网">全路网</option>
        	        <option value="1号线">1号线</option>
        	        <option value="2号线">2号线</option>
        	        <option value="3号线">3号线</option>
        	        <option value="4号线">4号线</option>
        	        <option value="5号线">5号线</option>
        	        <option value="6号线">6号线</option>
        	        <option value="7号线">7号线</option>
        	        <option value="8号线">8号线</option>
        	        <option value="9号线">9号线</option>
        	        <option value="10号线">10号线</option>
        	        <option value="11号线">11号线</option>
        	        <option value="12号线">12号线</option>
        	        <option value="13号线">13号线</option>
        	        <option value="14号线">14号线</option>
        	        <option value="15号线">15号线</option>
        	        <option value="16号线">16号线</option>
        	        <option value="17号线">17号线</option>
        	        <option value="18号线">18号线</option>
        	        <option value="19号线">19号线</option>
        	        <option value="20号线">20号线</option>
        	      </select></td>
        	      <td class="t_r" style="white-space:nowrap;">所属专业</td>
        	      <td>
        	      <input type="text" id="major" name="major" class="input_large" value="<s:property value='hiddenDangersCorrectVO.major'/>">
        	     </td>
      	          
      	          <td class="t_r">整改时限</td>
        	      <td >
        	      <input readonly="readonly" name="lastDateStart" id="lastDateStart" type="text" value="<s:property value='#request.lastDateStart'/>" class="input_large"/>至
        	      <input readonly="readonly" name="lastDateEnd" id="lastDateEnd" type="text" value="<s:property value='#request.lastDateEnd'/>" class="input_large"/>
        	      </td> 
      	          
        	      
        	   </tr>
        	   <tr>
        	      <td class="t_r" style="white-space:nowrap;">隐患类型</td>
        	      <td>
        	      <select name="dangersClass" id="dangersClass" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="1">设施设备</option>
        	        <option value="2">管理</option>
        	        <option value="3">规章制度</option>
        	        <option value="4">现场作业</option>
        	        <option value="5">新线遗留</option>
        	      </select>
        	      </td>
        	        	       
        	     <td class="t_r">责任部门</td>
        	     <td>
        	     <input type="text" id="workDept" name="workDept" class="input_large" value="<s:property value='hiddenDangersCorrectVO.workDept'/>">
        	     </td> 
        	      
      	      
      	          <td class="t_r" style="white-space:nowrap;">实际完成时间</td>
        	      <td style="white-space:nowrap;">
        	      <input readonly="readonly" name="finishDateStart" id="finishDateStart" type="text" value="<s:property value='#request.finishDateStart'/>" class="input_large"/>至
        	      <input readonly="readonly" name="finishDateEnd" id="finishDateEnd" type="text" value="<s:property value='#request.finishDateEnd'/>" class="input_large"/>
        	      </td>
      	      </tr>
      	      <tr>
        	     <td class="t_r">落实情况</td>
        	     <td>
        	     <select name="workState" id="workState" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="1">已完成</option>
        	        <option value="2">整改中</option>
        	      </select>
        	     </td> 
        	       
        	     <td class="t_r">责任人</td>
        	      <td>
        	      <input type="text" id="workPerson" name="workPerson" class="input_large" value="<s:property value='hiddenDangersCorrectVO.workPerson'/>">
        	     </td>  
      	      
      	          <td class="t_r">填报时间</td>
        	      <td>
        	      <input readonly="readonly" name="inputDateStart" id="inputDateStart" type="text" value="<s:property value='#request.inputDateStart'/>" class="input_large"/>至
        	      <input readonly="readonly" name="inputDateEnd" id="inputDateEnd" type="text" value="<s:property value='#request.inputDateEnd'/>" class="input_large"/>
        	      </td>
      	      </tr>
      	      
      	      
      	      <tr>
      	        <td class="t_r">重要程度</td>
                  <td> 
                  <select name="importance" id="importance" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="1">三级<!-- 一般隐患 --></option>
        	        <option value="2">二级<!-- 较大隐患 --></option>
        	        <option value="0">一级<!-- 重大隐患 --></option>
        	      </select>
        	      </td>
                                
      	        <td class="t_r">填报单位</td>
      	         <td>
      	           <select name="inputDept" id="inputDept" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="上海地铁第一运营有限公司">上海地铁第一运营有限公司</option>
        	        <option value="上海地铁第二运营有限公司">上海地铁第二运营有限公司</option>
        	        <option value="上海地铁第三运营有限公司">上海地铁第三运营有限公司</option>
        	        <option value="上海地铁第四运营有限公司">上海地铁第四运营有限公司</option>
        	        <option value="上海轨道交通运营管理中心">上海轨道交通运营管理中心</option>
        	        <option value="磁浮公司">磁浮公司</option>
        	        <option value="维保公司">维保公司</option>
        	      </select>
      	         </td>
      	        <%--  <td class="t_r">审核状态</td>
                  <td> 
                  <select name="checkState" id="checkState" class="input_large">
        	        <option value="">--请选择--</option>
        	        <s:if test="#request.operateType!=2">
        	        <option value="1">未审核</option>
        	        </s:if>
        	        <option value="2">审核通过</option>
        	        <option value="3">返回修改</option>
        	      </select>
        	      </td> --%>
        	      <td class="t_r">隐患情况</td>
      	         <td>
      	           <input type="text" id="dangersState" name="dangersState" class="input_large" value="<s:property value='hiddenDangersCorrectVO.dangersState'/>">
      	         </td>
      	      </tr>
      	      <tr>
      	      
      	         <td class="t_r">整改措施</td>
      	         <td>
      	           <input type="text" id="correctMethod" name="correctMethod" class="input_large" value="<s:property value='hiddenDangersCorrectVO.correctMethod'/>">
      	         </td>
      	      </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />
      &nbsp;
<input type="button" value="重 置" onclick="clearInput()"/> &nbsp;
<input type="button" value="导出Excel" onclick="exportExcel();"/>
</td>
       	        </tr>
      	    </table>
      	    </s:form>
        	</div>
        	<!--s:if test="#request.operateType==1"-->
            <div class="fn">
	            <table width="100%">
		            <tr>
			            <td>
			              <input type="button" name="button2" id="button2" value="新 增" class="new" onclick="dataAdd()">
			            </td>
		            </tr>
	            </table>
            </div>
      		<!--/s:if-->
      </div>
      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="30">运营安全隐患问题整改推进表</th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td class="t_c" style="white-space:nowrap;">序号</td>
                                <td class="t_c" style="white-space:nowrap;">线路/处所</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                	隐患情况&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                	整改措施&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;隐患类型&nbsp;&nbsp;</td>                                                           
                                <td class="t_c" style="white-space:nowrap;">所属专业</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;整改时限&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">落实情况</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;实际完成时间&nbsp;<!-- <a style="display:inline" href="#" onclick="submitFormOrderById('<s:if test="#request.updateTimeOrder=='asc'">desc</s:if><s:else>asc</s:else>')"><img style="display:inline" src="../images/<s:if test="#request.updateTimeOrder=='asc'">up</s:if><s:else>down</s:else>.png"></img></a> --></td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;责任单位/部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;责任人&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">填报单位</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;重要程度&nbsp;&nbsp;</td>                                                           
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填报时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;备注&nbsp;&nbsp;</td>
                                <!-- <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;审核状态&nbsp;&nbsp;</td> -->
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                		操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                              </tr>
                              
                               <s:iterator value="#request.page.result" status="st">
	 	                          <tr id="dataTR">
	 	                            <td class="t_c"><s:property value="#st.index+#request.page.start"/></td>
	 	                            <td ><s:property value="lines"/></td>
	 	                            <td id="dangersState_show" title="<s:property value="dangersState"/>">
	 	                            <s:property value="dangersState"/>
	 	                            </td>
	 	                            <td id="correctMethod_show" title="<s:property value="correctMethod"/>">
	 	                            <s:property value="correctMethod"/>
	 	                            </td>
	 	                            <td class="t_c" id="dangersClass_show"><s:property value="dangersClass"/></td>	                      	 	                            
	 	                            <td class="t_c"><s:property value="major"/></td>
	 	                            <td class="t_c"><s:date name="lastDate" format="yyyy-MM-dd"/></td>
	 	                            <td class="t_c" id="workState_show"><s:property value="workState"/></td> 
	 	                            <td class="t_c"><s:date name="finishDate" format="yyyy-MM-dd"/></td>  
	 	                            <td class="t_c"><s:property value="workDept"/></td> 
	 	                            <td class="t_c"><s:property value="workPerson"/></td> 
	 	                            <td class="t_c"><s:property value="inputDept"/></td> 
	 	                            <td class="t_c" id="importance_show"><s:property value="importance"/></td> 
	 	                            <td class="t_c"><s:date name="inputDate" format="yyyy-MM-dd"/></td> 
	 	                            <td class="t_c" id="remark_show">详细<span style="display:none"><s:property value="remark"/></span></td>                
	 	                            <%-- <td class="t_c" id="checkState_show"><s:property value="checkState"/></td> --%>
	 	                      		<td class="t_c">
	  	                            <s:if test="#request.operateType==1">
	  	                            	<a style="display:inline" href="findHiddenDangersCorrectById.action?id=<s:property value='id'/>&type=view" target="_blank" >查看</a> 
	  	                            	<s:if test="checkState==1||checkState==3">
	  	                            		<a style="display:inline" href="javascript:void(0)" onclick="editData('<s:property value='id'/>')">修改</a>
	  	                            	</s:if>
	  	                            </s:if>
	  	                            <s:elseif test="#request.operateType==3">
		  	                            <a class="fl mr5" href="findHiddenDangersCorrectById.action?id=<s:property value='id'/>&type=view" target="_blank" >查看</a> 
		  	                            <%-- <a class="fl mr5" href="javascript:void(0)" onclick="checkFuc(2,'<s:property value='id'/>')">审核通过</a>
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="checkFuc(3,'<s:property value='id'/>')">返回修改</a> --%>
	  	                            </s:elseif>
	  	                            <s:elseif test="#request.operateType==2">
	  	                            	<a class="fl mr5" href="findHiddenDangersCorrectById.action?id=<s:property value='id'/>&type=view" target="_blank" >查看</a> 
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='id'/>')">删除</a> 
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="editData('<s:property value='id'/>')">修改</a>
	  	                            </s:elseif>
	  	                            <s:else>
	  	                            	<a class="fl mr5" href="findHiddenDangersCorrectById.action?id=<s:property value='id'/>&type=view" target="_blank" >查看</a> 
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='id'/>')">删除</a> 
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="editData('<s:property value='id'/>')">修改</a>
		  	                            <%-- <a class="fl mr5" href="javascript:void(0)" onclick="checkFuc(2,'<s:property value='id'/>')">审核通过</a>
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="checkFuc(3,'<s:property value='id'/>')">返回修改</a> --%>
	  	                            </s:else>
	  	                            </td>
	                            </tr>
	                            </s:iterator>
                              </tbody>
                              <s:if test="#request.page.totalSize!=0">
                              <tr class="tfoot">
        	      <td colspan="30"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-
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
      	      <tr class="tfoot"><td colspan="30"><div class="clearfix">无相关数据</div></td>
   	          </tr>
      	      </s:else>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>


