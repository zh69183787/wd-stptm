<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
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
String inputdept=null;
String inputperson=null;
String inputdeptId = null;
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
	
	//取填报人
	for (Cookie cookie:cookies) {
		if("userName".equals(cookie.getName())){
			inputperson = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			break;
		}
	}
	
	//取填报部门id
	for (Cookie cookie:cookies) {
		if("deptId".equals(cookie.getName())){
			inputdeptId = cookie.getValue();
			break;
		}
	}
	System.out.println("inputdeptId=="+inputdeptId);
	if("2921".equals(inputdeptId)||"2856".equals(inputdeptId)){
		inputdept = "上海地铁第一运营有限公司";
	}else if("2922".equals(inputdeptId)||"2884".equals(inputdeptId)){
		inputdept = "上海地铁第二运营有限公司";
	}else if("2923".equals(inputdeptId)||"2880".equals(inputdeptId)){
		inputdept = "上海地铁第三运营有限公司";
	}else if("2924".equals(inputdeptId)||"2882".equals(inputdeptId)){
		inputdept = "上海地铁第四运营有限公司";
	}else if("2920".equals(inputdeptId)||"2888".equals(inputdeptId)||"2516".equals(inputdeptId)||"2564".equals(inputdeptId)||"2561".equals(inputdeptId)){
		inputdept = "上海轨道交通运营管理中心";
	}else if("2925".equals(inputdeptId)||"2582".equals(inputdeptId)){
		inputdept = "维保公司";
	}else if("2962".equals(inputdeptId)){
		inputdept = "磁浮公司";
	}else{
		inputdept ="";
	}
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>数据录入</title>
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
		<script src="js/common.js"></script>
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
		
		//自动提示模糊搜索，责任单位
		$(function(){
		var saveStatus = false;
		$("#workDept_add" ).autocomplete({
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
			$("#workDeptId_add").val("");
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#workDeptId_add").val(ui.item.value);
			$("#workDept_add").val(ui.item.label);
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
				$("#workDeptId_add").val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#workDeptId_add").val("");
			}
		}
		});
		});

		//自动提示模糊搜索，督办部门
		$(function(){
		var saveStatus = false;
		$("input[name=supervisionDept]" ).autocomplete({
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
			$("#supervisionDeptId").val("");
			saveStatus = false;
		},
		select: function( event, ui ) {
			$("#supervisionDeptId").val(ui.item.value);
			$("#supervisionDept").val(ui.item.label);
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
				$( "#supervisionDeptId" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus){
				$("#supervisionDeptId").val("");
			}
		}
		});
		});
		
		
		
		function cancel(){
			$("#supervisionDeptId_hidden").val("");
			$("#supervisionDept_hidden").val("");
			$("#workDept_hidden").val("");
			$("#workDeptId_hidden").val("");
			//$("#workDept").parent().find("p").remove();
		}
		
		
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营安全隐患问题整改推进表</a></li>  
                	<li class="fin">数据录入</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                <!-- 
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>                
                <li><a class="query" href="#">查询</a></li>
                 -->
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        <s:form action="addHiddenDangersCorrect" name="HiddenDangersCorrect" namespace="/hiddenDangersCorrect">
        <input type="hidden" id="linesAll" name="lines">
        
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;<input type="reset" value="重 置" onclick="cancel();"/>&nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">线路/处所</td>
                                <td colspan="3" >
                                <input type="checkbox" id="lines" value='全路网' onclick="clickAll(this);" >全路网
							    <input type="checkbox" id="lines" value='1号线'>1号线
							    <input type="checkbox" id="lines" value='2号线'>2号线
							    <input type="checkbox" id="lines" value='3号线'>3号线
							    <input type="checkbox" id="lines" value='4号线'>4号线
							    <input type="checkbox" id="lines" value='5号线'>5号线
							    <input type="checkbox" id="lines" value='6号线'>6号线
							    <input type="checkbox" id="lines" value='7号线'>7号线
							    <input type="checkbox" id="lines" value='8号线'>8号线
							    <input type="checkbox" id="lines" value='9号线'>9号线
							    <input type="checkbox" id="lines" value='10号线'>10号线
							    <input type="checkbox" id="lines" value='11号线'>11号线
							    <input type="checkbox" id="lines" value='12号线'>12号线
							    <input type="checkbox" id="lines" value='13号线'>13号线
							    <input type="checkbox" id="lines" value='14号线'>14号线
							    <input type="checkbox" id="lines" value='15号线'>15号线
							    <input type="checkbox" id="lines" value='16号线'>16号线
							    <input type="checkbox" id="lines" value='17号线'>17号线
							    <input type="checkbox" id="lines" value='18号线'>18号线
							    <input type="checkbox" id="lines" value='19号线'>19号线
							    <input type="checkbox" id="lines" value='20号线'>20号线
                                &nbsp;&nbsp;<span style="color:red;display:inline">*</span></td>
                           
                                
                                </tr>
                              <tr>
                                <td class="t_r lableTd">隐患情况</td>
                                <td colspan="3"><textarea id="dangersState" name="dangersState" rows="5" cols="10" style="overflow:auto;  border-width:1"></textarea>&nbsp;&nbsp;<span style="color:red;display:inline">*</span></td>
                               
                                </tr>
                              <tr>
                                <td class="t_r lableTd">整改措施</td>
                                <td colspan="3"><textarea id="correctMethod" name="correctMethod" cols="60" rows="5" style="overflow:auto;  border-width:1" maxlength="600"></textarea></td>
                               
                                </tr>
                               
                              <tr>
                              	<td class="t_r lableTd" style="width:15%">责任人</td>
                                <td style="width:30%"><input type="text" id="workPerson" name="workPerson" maxlength="20" class="input_large" /></td>
                                <td class="t_r lableTd" style="width:20%">督办单位</td>
                                <td  style="width:35%">
                                <select id="supervisionDept_select">
                                	<option value="">---请选择---</option>
                                	<option value="上海轨道交通运营管理中心#2540">上海轨道交通运营管理中心</option>
                                	<option value="上海地铁第一运营有限公司#2541">上海地铁第一运营有限公司</option>
                                	<option value="上海地铁第二运营有限公司#2542">上海地铁第二运营有限公司</option>
                                	<option value="上海地铁第三运营有限公司#2543">上海地铁第三运营有限公司</option>
                                	<option value="上海地铁第四运营有限公司#2544">上海地铁第四运营有限公司</option>
                                	<option value="上海轨道交通维护保障中心#2545">上海轨道交通维护保障中心</option>
                                	<option value="维保中心-车辆公司#2718">维保中心-车辆公司</option>
                                	<option value="维保中心-供电公司#2719">维保中心-供电公司</option>
                                	<option value="维保中心-通号公司#2720">维保中心-通号公司</option>
                                	<option value="维保中心-工务公司#2721">维保中心-工务公司</option>
                                	<option value="维保中心-物资和后勤公司#2722">维保中心-物资和后勤公司</option>
                                </select>
                                <input type="hidden" id="supervisionDeptId_hidden" name="supervisionDeptId" />
                                <input type="hidden" id="supervisionDept_hidden" name="supervisionDept" />
                                <!-- 
                                <input type="text" id="supervisionDept" name="supervisionDept" maxlength="50" class="input_xxlarge">
                                <input type="hidden" id="supervisionDeptId" name="supervisionDeptId" />
                                -->
                                
                                </td>
                              </tr>
                              <tr>
                              	<td class="t_r lableTd">所属专业</td>
                                <td><input type="text" id="major" name="major" maxlength="20" class="input_large"/></td>
                                <td class="t_r lableTd">填报日期</td>
                                <td><input type="text" id="inputDate" name="inputDate" value="<s:date format='yyyy-MM-dd' name='hiddenDangersCorrectVO.inputDate'/>" readonly="readonly" class="input_large"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">隐患类型</td>
                                <td>
                                  <select name="dangersClass" id="dangersClass" class="input_large">
                                    <option value="">--请选择--</option>
								    <option value="1">设施设备</option>
									<option value="2">管理</option>
									<option value="3">规章制度</option>
									<option value="4">现场作业</option>
									<option value="5">新线遗留</option>                                    
                                </select>
                                &nbsp;&nbsp;<span style="color:red;display:inline">*</span></td>
                                <td class="t_r lableTd">整改时限</td>
                                <td><input type="text" id="lastDate" name="lastDate" readonly="readonly" class="input_large"/></td>
                              </tr>
                               <tr>
                               <td class="t_r lableTd">填报单位</td>
                                <td>
                                <input type="text" id="inputDept_show" class="input_xlarge" disabled="disabled" value="<%=inputdept %>"/>
                                <input type="hidden" id="inputDept" name="inputDept" />
                                <input type="hidden" id="inputDeptId" name="inputDeptId" value="<%=inputdeptId%>"/></td>
                                <td class="t_r lableTd">填报人</td>
                                <td><input type="text" id="createPerson_show" class="input_large" disabled="disabled" value="<%=inputperson%>"/>
                                <input type="hidden" id="createPerson" name="createPerson" /></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">落实情况</td>
                                <td><select name="workState" id="workState" class="input_large">
                                    <option value="">--请选择--</option>
									<option value="1">已完成</option>
									<option value="2">整改中</option>
                                </select>
                                &nbsp;&nbsp;<span style="color:red;display:inline">*</span></td>
                                <td class="t_r lableTd">实际完成时间</td>
                                <td><input type="text" id="finishDate" name="finishDate" readonly="readonly" class="input_large"/></td>
                              </tr>
                              
                             
                              <tr>
                                <td class="t_r lableTd">重要程度</td>
                                <td colspan="3">
                                <input type="radio" name="importance" value='1'>三级<!-- 一般隐患 -->
                                <input type="radio" name="importance" value='2'>二级<!-- 较大隐患 -->
                                <input type="radio" name="importance" value='0'>一级<!-- 重大隐患 -->
                                </td>
                                </tr>
                               <tr>
                                <td class="t_r lableTd">责任单位</td>
                                <td colspan="3">
	                                <input type="checkbox" id="workDept_checkbox" value="上海轨道交通运营管理中心#2540"/>上海轨道交通运营管理中心&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="上海地铁第一运营有限公司#2541"/>上海地铁第一运营有限公司&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="上海地铁第二运营有限公司#2542"/>上海地铁第二运营有限公司&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="上海地铁第三运营有限公司#2543"/>上海地铁第三运营有限公司&nbsp;&nbsp;
	                                <br>
	                                <input type="checkbox" id="workDept_checkbox" value="上海地铁第四运营有限公司#2544"/>上海地铁第四运营有限公司&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="上海轨道交通维护保障中心#2545"/>上海轨道交通维护保障中心&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="维保中心-车辆公司#2718"/>维保中心-车辆公司&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="维保中心-供电公司#2719"/>维保中心-供电公司&nbsp;&nbsp;
	                                <br>
	                                <input type="checkbox" id="workDept_checkbox" value="维保中心-通号公司#2720"/>维保中心-通号公司&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="维保中心-工务公司#2721"/>维保中心-工务公司&nbsp;&nbsp;
	                                <input type="checkbox" id="workDept_checkbox" value="维保中心-物资和后勤公司#2722"/>维保中心-物资和后勤公司
	                                
	                                <input type="hidden" id="workDept_hidden" name="workDept"/>
	                                <input type="hidden" id="workDeptId_hidden" name="workDeptId"/>
	                                <!-- 
	                                <input type="text" id="workDept_add" style="width: 300px;" />
	                                &nbsp;&nbsp;<span style="color:red;display:inline">*</span>
									<input type="button" value="添加" onclick="addWorkDept(this)"/>
									<input type="button" value="删除" onclick="deleteWorkDept(this)"/>
									<input type="hidden" id="workDeptId_add"/>
									<input type="hidden" name="workDept" id="workDept" />
	                                <input type="hidden" id="workDeptId" name="workDeptId" />
	                                 -->
								</td>
                                
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注</td>
                                <td colspan="3"><textarea id="remark" name="remark" cols="60" rows="5" style="overflow:auto; border-width:1" maxlength="600"></textarea></td>
                               </tr>
                              
                                
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;<input type="reset" value="重 置" onclick="cancel();"/>&nbsp;
<input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
