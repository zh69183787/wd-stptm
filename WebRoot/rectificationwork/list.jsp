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
System.out.println("inputdeptId==="+inputdeptId);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>
<s:if test="#request.operateType==1">
运营安全评价风险点整改工作录入
</s:if>
<s:elseif test="#request.operateType==2">
运营安全评价风险点整改工作管理
</s:elseif>
<s:elseif test="#request.operateType==3">
运营安全评价风险点整改工作查询
</s:elseif>
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
		};
        
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
			
			var h_levels=$("#h_levels");
			  var levels=$("#levels");		  
			  for(var i=1;i<levels.children("option").length;i++){
			    if(levels.children("option:eq("+i+")").val()==h_levels.val())
			    levels.children("option:eq("+i+")").attr("selected",true);
			  }
			  var h_workState=$("#h_workState");
			  var workState=$("#workState");		  
			  for(var i=1;i<workState.children("option").length;i++){
			    if(workState.children("option:eq("+i+")").val()==h_workState.val())
			    workState.children("option:eq("+i+")").attr("selected",true);
			  }
			  var h_leadDept=$("#h_leadDept");
			  var leadDept=$("#leadDept");		  
			  for(var i=1;i<leadDept.children("option").length;i++){
			    if(leadDept.children("option:eq("+i+")").val()==h_leadDept.val())
			    leadDept.children("option:eq("+i+")").attr("selected",true);
			  }
			  
			  var h_checkState=$("#h_checkState");
			  var checkState=$("#checkState");		  
			  for(var i=1;i<checkState.children("option").length;i++){
			    if(checkState.children("option:eq("+i+")").val()==h_checkState.val())
			    checkState.children("option:eq("+i+")").attr("selected",true);
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
        	      				$(this).html("重大隐患");
        	      				$(this).parents("tr").attr("style","color:red");
        	      				break;
        	      			}
        	      case "1":$(this).html("一般隐患");break;
        	      case "2":$(this).html("较大隐患");break;
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
          $("#dangerNumber").val("");
          $("#riskPoint").val("");
          $("#leaderShip").val("");
          $("#expertAdvice").val("");
          $("#targetNode").val("");
          $("#levels").children("option:eq(0)").attr("selected",true);
          $("#workState").children("option:eq(0)").attr("selected",true);
          $("#phenomenon").val("");
          $("#cwip").val("");  
          
          $("#childSYS").val("");
          $("#deptWork").val("");
          $("#leadDept").children("option:eq(0)").attr("selected",true);
          $("#rectificationMethod").val("");
                                          
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
		
        function deleteData(id){
      	  if(confirm("确定删除?")){
      		$.ajax({
				url: "deleteRectificationWorkById.action?random="+Math.random(),
				dataType: "json",
				data: {
					"id" : id
				},
				success: function( data ) {
					//window.location.reload();
					$("form").submit();
				}
			});     		
      	  }
        }
        
        function dataAdd(){
          newWindow=window.open('toAdd.action');
        }
        
        function editData(id){
          newWindow=window.open("findRectificationWorkById.action?id="+id+"&type=edit");
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
						};
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
<input type="hidden" id="h_lines" name="h_lines" value="<s:property value='avo.lines'/>">
<input type="hidden" id="h_levels" name="h_levels" value="<s:property value='avo.levels'/>">
<input type="hidden" id="h_workState" name="h_workState" value="<s:property value='avo.workState'/>">
<input type="hidden" id="h_leadDept" name="h_leadDept" value="<s:property value='avo.leadDept'/>">
<input type="hidden" id="operateType" name="operateType" value="<s:property value='#request.operateType'/>">
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营安全评价风险点整改工作</a></li> 
                	<li>
                		<s:if test="#request.operateType==1">
						运营安全评价风险点整改工作录入
						</s:if>
						<s:elseif test="#request.operateType==2">
						运营安全评价风险点整改工作管理
						</s:elseif>
						<s:elseif test="#request.operateType==3">
						运营安全评价风险点整改工作查询
						</s:elseif>

                	</li>
                	<li class="fin">运营安全评价风险点整改工作表【注：整改责任单位涉及两个及以上单位（部门）的，由第一位单位（部门）牵头整改。】 </li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                
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
        	<s:form action="findRectificationWorkByPage" id="form" name="RectificationWork" namespace="/RectificationWork">        	
        	<input type="hidden" name="ifExport" id="ifExport"/>
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	<input type="hidden" name="companyId" id="companyId" value="<s:property value='#request.companyId'/>"/>
        	<input type="hidden"  value="<s:property value='#request.operateType'/>" name="operateType" id="operateType"/>
        	  <table style="width:100%;border: 0px;"  cellspacing="0" cellpadding="0" >
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
        	      </select>
        	      
        	      </td>
        	      <td class="t_r" style="white-space:nowrap;">风险编号</td>
        	      <td>
        	      <input type="text" id="dangerNumber" name="dangerNumber" class="input_large" value="<s:property value='avo.dangerNumber'/>">
        	     </td>
      	          
      	          <td class="t_r">子系统</td>
        	      <td >
        	      	<input type="text" id="childSYS" name="childSYS" class="input_xlarge" value="<s:property value='avo.childSYS'/>">
        	      </td> 
        	   </tr>
        	   <tr>
        	      <td class="t_r" style="white-space:nowrap;">风险点</td>
        	      <td>
        	      <input type="text" id="riskPoint" name="riskPoint" class="input_large" value="<s:property value='avo.riskPoint'/>">
        	      </td>
        	        	       
        	     <td class="t_r">等级</td>
        	     <td>
        	     <select id="levels" name="levels" class="input_large">
                      <option value="">---请选择---</option>
                      <option value="严重的">严重的</option>
                      <option value="需重视的">需重视的</option>
                 </select>
        	     </td> 
      	          <td class="t_r" style="white-space:nowrap;">责任单位/部门</td>
        	      <td >
        	      <input type="text" id="deptWork" name="deptWork" class="input_xlarge" value="<s:property value='avo.deptWork'/>">
        	      </td>
      	      </tr>
      	      <tr>
        	     <td class="t_r">分管领导</td>
        	     <td>
        	     <input type="text" id="leaderShip" name="leaderShip" class="input_large" value="<s:property value='avo.leaderShip'/>">
        	      
        	     </td> 
        	       
        	     <td class="t_r">消项情况</td>
        	      <td>
        	      <select id="workState" name="workState" class="input_large">
                       <option value="">---请选择---</option>
                       <option value="已完成">已完成</option>
                       <option value="整改中">整改中</option>
                  </select>
        	      </td>  
      	      
      	          <td class="t_r">牵头部门</td>
        	      <td>
        	      <select id="leadDept" name="leadDept" class="input_xlarge">
                      <option value="">---请选择---</option>
                      <option value="上海地铁第一运营有限公司">上海地铁第一运营有限公司</option>
                      <option value="上海地铁第二运营有限公司">上海地铁第二运营有限公司</option>
                      <option value="上海地铁第三运营有限公司">上海地铁第三运营有限公司</option>
                      <option value="上海地铁第四运营有限公司">上海地铁第四运营有限公司</option>
                      <option value="上海轨道交通运营管理中心">上海轨道交通运营管理中心</option>
                      <option value="磁浮公司">磁浮公司</option>
                      <option value="维保公司">维保公司</option>
                      </select>
              </td>
      	      </tr>
      	      
      	      <tr>
      	        <td class="t_r">专家建议</td>
                  <td> 
                  <input type="text" id="expertAdvice" name="expertAdvice" class="input_large" value="<s:property value='avo.expertAdvice'/>">
      	        </td>
                                
      	        <td class="t_r">现象说明</td>
      	         <td>
      	           <input type="text" id="phenomenon" name="phenomenon" class="input_large" value="<s:property value='avo.phenomenon'/>">
      	         </td>
      	         <td class="t_r">整改措施</td>
                  <td> 
                  <input type="text" id="rectificationMethod" name="rectificationMethod" class="input_xlarge" value="<s:property value='avo.rectificationMethod'/>">
      	         </td>
      	      </tr>
      	      <tr>
      	      <td class="t_r">节点目标</td>
      	         <td>
      	           <input type="text" id="targetNode" name="targetNode" class="input_large" value="<s:property value='avo.targetNode'/>">
      	         </td>
      	         <td class="t_r">进展情况</td>
      	         <td>
      	           <input type="text" id="cwip" name="cwip" class="input_large" value="<s:property value='avo.cwip'/>">
      	         </td>
      	      </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />
      &nbsp;
<input type="button" value="重 置" onclick="clearInput()"/> &nbsp;

</td>
       	        </tr>
      	    </table>
      	    </s:form>
        	</div>
            <div class="fn">
	            <table style="width:100%">
		            <tr>
			            <td>
			            <s:if test="#request.operateType==1">
			              <input type="button" name="button2" id="button2" value="新 增" class="new" onclick="dataAdd()">
			            </s:if>
			            </td>
			            
		            </tr>
	            </table>
            </div>
      </div>
      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table style="width:100%"  class="table_1">
                              <thead>
                                  <th colspan="30">运营安全评价风险点整改工作表</th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td class="t_c" style="white-space:nowrap;">序号</td>
                                <td class="t_c" style="white-space:nowrap;">线路/处所</td>
                                <td class="t_c" style="white-space:nowrap;">风险编号</td>
                                <td class="t_c" style="white-space:nowrap;">子系统</td>
                                <td class="t_c" style="white-space:nowrap;">风险点</td>                                                           
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;等级&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">责任单位/部门</td>
                                <td class="t_c" style="white-space:nowrap;">分管领导</td>
                                <td class="t_c" style="white-space:nowrap;">消项情况</td>
                                <td class="t_c" style="white-space:nowrap;">牵头部门</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;专家建议&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;现象说明&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;整改措施&nbsp;&nbsp;</td>                                                           
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;节点目标&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;进展情况&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;备注&nbsp;&nbsp;</td>
                                <td class="t_c" style="white-space:nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                		操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                              </tr>
                              
                               <s:iterator value="#request.page.result" status="st">
	 	                          <tr id="dataTR">
	 	                            <td class="t_c"><s:property value="#st.index+#request.page.start"/></td>
	 	                            <td id="lines_show" title="<s:property value='lines'/>">
	 	                            <s:if test="%{null!=lines&&lines.length()>10}">
	 	                            	<s:property value="%{lines.substring(0,10)}"/>...
	 	                            </s:if>
	 	                            <s:else>
	 	                            <s:property value="lines"/>
	 	                            </s:else>
	 	                            </td>
	 	                            <td id="dangerNumber_show" title="<s:property value="dangerNumber"/>"><s:property value="dangerNumber"/></td>
	 	                            <td id="childSYS_show" title="<s:property value="childSYS"/>">
	 	                            	<s:if test="%{null!=childSYS&&childSYS.length()>10}">
	 	                            	<s:property value="%{childSYS.substring(0,10)}"/>...
	 	                            	</s:if>
	 	                            	<s:else>
	 	                            		<s:property value="childSYS"/>
	 	                            	</s:else>
	 	                            </td>
	 	                            <td class="t_c" id="riskPoint_show" title="<s:property value="riskPoint"/>">
	 	                            <s:if test="%{null!=riskPoint&&riskPoint.length()>10}">
	 	                            	<s:property value="%{riskPoint.substring(0,10)}"/>...
	 	                            </s:if>
	 	                            <s:else><s:property value="riskPoint"/></s:else>
	 	                            
	 	                            </td>	                      	 	                            
	 	                            <td class="t_c"><s:property value="levels"/></td>
	 	                            <td class="t_c" title="<s:property value="deptWork"/>">
	 	                            <s:if test="%{null!=deptWork&&deptWork.length()>8}">
	 	                            	<s:property value="%{deptWork.substring(0,8)}"/>...
	 	                            </s:if>
	 	                            <s:else><s:property value="deptWork"/></s:else>
	 	                            
	 	                            </td>
	 	                            <td class="t_c" id="leaderShip_show" title="<s:property value="leaderShip"/>">
	 	                            <s:if test="%{null!=leaderShip&&leaderShip.length()>8}">
	 	                            	<s:property value="%{leaderShip.substring(0,8)}"/>...
	 	                            </s:if>
	 	                            <s:else><s:property value="leaderShip"/></s:else>
	 	                            </td> 
	 	                            <td class="t_c"><s:property value="workState" /></td>  
	 	                            <td class="t_c"><s:property value="leadDept"/></td> 
	 	                            <td class="t_c" title="<s:property value="expertAdvice"/>">
	 	                            <s:if test="%{null!=expertAdvice&&expertAdvice.length()>8}">
	 	                            <s:property value="%{expertAdvice.substring(0,8)}"/>...
	 	                            </s:if>
	 	                            <s:else><s:property value="expertAdvice"/></s:else>
	 	                            </td> 
	 	                            <td class="t_c" title="<s:property value="phenomenon"/>">
	 	                            <s:if test="%{null!=phenomenon&&phenomenon.length()>8}">
	 	                            	<s:property value="%{phenomenon.substring(0,8)}"/>...
	 	                            </s:if>
	 	                            <s:else><s:property value="phenomenon"/></s:else>
	 	                            </td> 
	 	                            <td class="t_c" id="rectificationMethod_show" title="<s:property value="rectificationMethod"/>">
	 	                            <s:if test="%{null!=rectificationMethod&&rectificationMethod.length()>8}">
	 	                            	<s:property value="%{rectificationMethod.substring(0,8)}"/>...
	 	                            </s:if><s:else><s:property value="rectificationMethod"/></s:else>
	 	                            </td> 
	 	                            <td class="t_c" title="<s:property value="targetNode"/>">
	 	                            <s:if test="%{null!=targetNode&&targetNode.length()>8}">
	 	                            	<s:property value="%{targetNode.substring(0,8)}"/>...
	 	                            </s:if><s:else><s:property value="targetNode"/></s:else>
	 	                            </td> 
	 	                            <td class="t_c" id="cwip_show" title="<s:property value="cwip"/>">
	 	                            <s:if test="%{null!=cwip&&cwip.length()>8}">
	 	                            	<s:property value="%{cwip.substring(0,8)}"/>...
	 	                            </s:if><s:else><s:property value="cwip"/></s:else>
	 	                            </td>                
	 	                            <td class="t_c" id="remark_show" title="<s:property value="remark"/>">
	 	                            <s:if test="%{null!=remark&&remark.length()>8}">
	 	                            	<s:property value="%{remark.substring(0,8)}"/>...
	 	                            </s:if><s:else><s:property value="remark"/></s:else>
	 	                            </td>
	 	                            <td class="t_c">
	  	                           		
	  	                           		<a class="fl mr5" href="findRectificationWorkAndRecordById.action?wid=<s:property value='id'/>" target="_blank" >查看工作记录</a> 
	  	                           		
	  	                            	<!-- <a class="fl mr5" href="findRectificationWorkById.action?id=<s:property value='id'/>&type=view" target="_blank" >查看</a>  -->
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='id'/>')">删除</a> 
		  	                            <a class="fl mr5" href="javascript:void(0)" onclick="editData('<s:property value='id'/>')">修改</a>
	  	                             <!-- <a class="fl mr5" href="../rectificationRecord/findRectificationRecordByPage.action?wid=<s:property value='id'/>" target="view_window">查看记录</a>
	  	                            	<a class="fl mr5" href="../rectificationRecord/toAdd.action?wid=<s:property value='id'/>" target="view_window">添加记录</a>
	  	                             	-->
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


