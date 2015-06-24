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
String userName = "";
if("on".equals(filterButton)){
	CrossIpLogin crossIpLogin = new CrossIpLogin();
	UserInfo userInfo = new UserInfo();
	crossIpLogin.setUserInfo(request,userInfo);
	loginName = userInfo.getLoginName();
	userName = userInfo.getUserName();
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>案例录入</title>
<link rel="stylesheet" href="../css/formalize.css" />

<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="../css/uploadify.css" />
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
		<script src="../js/swfobject.js"></script>
		<script src="../js/jquery.uploadify.v2.1.4.min.js"></script>
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		<%
		String firstLoad=request.getParameter("firstLoad");
		if("false".equals(firstLoad)){
		%>
		window.onload=function(){
		  //var a=$("#firsr_load").val();
		  alert("保存成功");
		}
		<%
		}
		%>
		
		var groupid = getGroupId();
		
		function getGroupId(){
			var date = new Date();
			var groupid ="";
			groupid += date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+date.getHours()+"-"+date.getMinutes()+"-"+date.getSeconds();
			return groupid; 
		}
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		var a=$("#occurSTime").val();	
		var b=$("#metroLine").val();
		var c=$("#metroStationName").val();	
		var d=$("#caseName").val();	
		  if(a==""){
		 		alert('请输入发生时间');
				$("#occurSTime").focus();
				return false;
		 }
		   if(b==""){
		 		alert('请输入所在线路');
				$("#metroLine").focus();
				return false;
		 }
		  if(c==""){
		 		alert('请输入涉及地点');
				$("#metroStationName").focus();
				return false;
		 }
		   if(d==""){
		 		alert('请输入事件名称');
				$("#caseName").focus();
				return false;
		 }
		}
		
		function moreSubmit(){
		  var text1=document.getElementById("metroLine").options[document.getElementById('metroLine').selectedIndex].text;		
		  //var text2=document.getElementById("metroStation").options[document.getElementById('metroStation').selectedIndex].text;
		  var text3=document.getElementById("caseCTypeCode").options[document.getElementById('caseCTypeCode').selectedIndex].text;
		  if(text1=="---请选择---") text1="";
		 // if(text2=="---请选择---") text2="";
		  if(text3=="---请选择---") text3="";
		  document.getElementById("metroLineName").value=text1;
		 // document.getElementById("metroStationName").value=text2;
		  document.getElementById("caseCType").value=text3;
		  
		 return check();
		}
		
		$.ajax({
			type: 'POST',
			url: 'metroLineAction!findAllMetroLine.action',
			dataType:'json',
			error:function(){alert('提交失败')},
			success: function(obj){			
				var metroLine = "<option value=''>---请选择---</option>";
				for(var i=0;i<obj.length;i++){
					metroLine +="<option value='"+obj[i].lineId+"'>"+obj[i].lineName+"</option>";
				}
				$("#metroLine").html(metroLine);
			}	  
		});
        
       function stationPick(){
      	 
       	   var lineNo = $("#metroLine").val();
      	 
	       $.ajax({
				type: 'POST',
				url: 'metroStationAction!findStationsByMetroLine.action?line='+lineNo,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
					var metroStation = "<option value=''>---请选择---</option>";
					for(var i=0;i<obj.length;i++){
						metroStation +="<option value='"+obj[i].stationId+"'>"+obj[i].stationName+"</option>";
					}
					$("#metroStation").html(metroStation);
				}	  
			});
      
       };
       
       showData();
       function showData(){
		var urlLocation = "findTAttachByGroupID.action?groupID="+groupid;
		var newData = "";
		$.ajax({
			type: 'POST',
			url: urlLocation,
			dataType:'json',
			error:function(){alert('提交失败')},
			success: function(obj){
						for(var i=0;i<obj.length;i++){
							newData +="<br/><a href='downloadFile.action?fileName="+obj[i].savefilename+"&realName="+obj[i].filename+"."+obj[i].fileextname+"' style='float: left'>"+obj[i].filename+"."+obj[i].fileextname+"</a><img style='float:left' alt='删除' src='../images/cancel.png' onClick='deleteData("+obj[i].id+")'>";
						}
						$("#attach").html(newData);
						
					}
		});  
       }
       
       function deleteData(id){
	       	$.ajax({
				type: 'POST',
				url: "deleteTAttachById.action?id="+id,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
							showData();
						}
			});  
       }
       
       
       
       
       
        </script></head>

<body>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
            	    <li>运营事件案例库</li>
                	<li class="fin">案例录入</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        <div class="tabs_2">
        	<ul>        	
        	<s:if test="caseType==1">
        			<li class="selected"><a href="toAdd.action?caseType=1"><span>E类以上典型运营事故</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="toAdd.action?caseType=1"><span>E类以上典型运营事故</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==2">
        			<li class="selected"><a href="toAdd.action?caseType=2"><span>运营延误事件</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="toAdd.action?caseType=2"><span>运营延误事件</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==3">
        			<li class="selected"><a href="toAdd.action?caseType=3"><span>设备安全事件 </span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="toAdd.action?caseType=3"><span>设备安全事件 </span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==4">
        			<li class="selected"><a href="toAdd.action?caseType=4"><span>有责投诉</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="toAdd.action?caseType=4"><span>有责投诉</span></a></li>
        		</s:else>
            </ul>
        </div>
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        <s:form action="addMetroAccidentCase" name="MetroAccidentCase" namespace="/accidentCase">
        <input type="hidden" id="reportPerson" name="reportPerson" value="<%=loginName %>">
        <input type="hidden" id="caseType" name="caseType" value="<s:property value='metroAccidentCaseVO.caseType'/>">
        <input type="hidden" id="caseCType" name="caseCType" value="">
        <input type="hidden" name="attachment" value="" id="attachment_form">
        <input type="hidden" id="approvalStatus" name="approvalStatus" value="0001">
        <input type="hidden" id="updatePerson" name="updatePerson" value="<%=loginName %>"> 
        <input type="hidden" id="first_load" name="first_load" value="<s:property value='firstLoad'/>">
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="保 存" onclick="return moreSubmit()"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      <input type="reset" value="重 置" />
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">发生时间</td>
                                <td colspan="3"><input type="datetime" id="occurSTime" name="occurSTime" class="input_large"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH时mm分'})"/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">事故所在线路</td>
                                <td><select name="metroLine" id="metroLine" class="input_large" >
                                 <option value="">---请选择---</option>
                                </select>
                                <input type="hidden" name="metroLineName" id="metroLineName">
                                </td>
                                <td class="t_r lableTd">涉及地点</td>
                                
                                <td><input type="text" name="metroStationName" id="metroStationName" class="input_xlarge"/>
                                <!-- 
                                	<select name="metroStation" id="metroStation" class="input_large">
                                  		<option value="">---请选择---</option>
                                	</select>
                                
                                
                                <input type="hidden" name="metroStationName" id="metroStationName">
                                -->
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">事件名称</td>
                                <td><input type="text" id="caseName" name="caseName" maxlength="40" class="input_xlarge" /></td>
                                <td class="t_r lableTd">子类别</td>
                                <td><select name="caseCTypeCode" id="caseCTypeCode" class="input_large">
                                <option value="">---请选择---</option>
                                <s:if test="caseType==1">
                                  <option value="5">车辆故障</option>
                                  <option value="6">供电故障</option>  
                                  <option value="7">通号故障</option>       
                                  <option value="8">工务故障</option> 
                                  <option value="9">屏蔽门故障</option>
                                  <option value="10">弓网故障</option>  
                                  <option value="11">挤岔故障</option>       
                                  <option value="12">晚点故障</option> 
                                  <option value="13">违章施工</option>
                                  <option value="14">违章作业</option>  
                                  <option value="15">人车冲突故障</option>       
                                  <option value="16">越站通过</option> 
                                  <option value="17">异物侵限</option>                                  
                                 </s:if>
                                 <s:if test="caseType==3">
                                  <option value="1">车辆专业</option>
                                  <option value="2">供电专业</option>  
                                  <option value="3">通号专业</option>       
                                  <option value="4">工务专业</option>  
                                 </s:if>                    
                                </select></td>
                                </tr>
                                 <tr>
                                 <td class="t_r lableTd">关键字</td>
                                <td><input type="text" id="keyWord" name="keyWord" maxlength="40" class="input_xlarge" /></td>
                                <td class="t_r lableTd">事件性质</td>
                                <td><select name="caseCategoryName" id="caseCategoryName" class="input_large">
                                  <option value="">---请选择---</option>
                                   <option value="一级">一级</option>
                                  <option value="二级">二级</option>  
                                  <option value="三级">三级</option>                                        
                                </select>
                                
                                </td>
                                
                                </tr>                              
                              <tr>
                                <td class="t_r lableTd">上报人</td>
                                <td><input type="text" id="reportPersonName" name="reportPersonName" value="<%=userName %>" maxlength="10" class="input_large" /></td>
                                <td class="t_r lableTd">上报时间</td>
                                <td><input type="datetime" id="reportTime" name="reportTime" class="input_large" value="<s:date name='metroAccidentCaseVO.reportTime' format='yyyy-MM-dd HH时mm分' />" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH时mm分'})"/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">责任人</td>
                                <td><input type="text" id="responsiblePersonName" name="responsiblePersonName" maxlength="10" class="input_large" /></td>
                                <td class="t_r lableTd">责任单位</td>
                                <td><input type="text" id="responsibleDeptName" name="responsibleDeptName" maxlength="20" class="input_xlarge" /></td>
                                </tr>
                                
                              <tr>
                                <td class="t_r lableTd">事故概述</td>
                                <td colspan="3"><textarea id="caseIntroduction" name="caseIntroduction" rows="3"></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">整改措施</td>
                                <td colspan="3"><textarea id="correctiveMeasures" name="correctiveMeasures" rows="3"></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">原因分析</td>
                                <td colspan="3"><textarea id="causeAnalysis" name="causeAnalysis" rows="3"></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">处置措施</td>
                                <td colspan="3"><textarea id="treatmentMeasures" name="treatmentMeasures" rows="3"></textarea></td>
                                </tr>                             
                              <tr>
                                <td class="t_r lableTd">点评分析</td>
                                <td colspan="3"><textarea id="commentsAnalysis" name="commentsAnalysis" rows="3"></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">备注</td>
                                <td colspan="3"><textarea id="remarks" name="remarks" rows="3"></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">查阅范围</td>
                                <td colspan="3"><input type="checkbox" checked="checked"/>案例库管理人员&nbsp;&nbsp;
                                <input type="checkbox" />群组1（待定）&nbsp;&nbsp;
                                <input type="checkbox" />群组2（待定）&nbsp;&nbsp;</td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">
                                	相关附件<br/>(单个文件上传最大为10Mb)
                                </td>
                                <td colspan="3">
 									<input id="file_upload" type="file" name="uploadify" value="上传" style="float: left;"/>
									<input type="button" value="上传" disabled="disabled" id="uploadButton" onclick="uploadifyUpload()" style="float:left;"/>
									<!-- <p/><a href="#" style="float: left">aaaa.jpg</a><img style="float: left" alt="删除" src="../images/cancel.png"> -->
									<span id="attach"></span>


 <script>
 
 	//var groupid = '2012aabb';
 	
 	var groupid = getGroupId();
 	$("#attachment_form").val(groupid);
 
 //生成groupid
 
 
$(document).ready(function() {
	var urlLocation ="upload!fileUpload.action?groupid="+groupid;
	$('#file_upload').uploadify({
	    'uploader'  : '../js/uploadify.swf',
	    'script'    : urlLocation,
	    'cancelImg' : '../images/cancel.png',
	    'fileDataName' : 'uploadify', 
	    'folder'    : 'uploads',
	    'fileExt'   : '*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.rar;*.jpg;*.jpeg;*.png;*.gif;*.txt;',
	    'fileDesc'  : '支持格式:DOC,DOCX,XLS,XLSX,PDF,RAR,JPG,JPEG,PNG,GIF,TXT',
	    'buttonText': 'file',
	    'onSelect'	: function(){$("#uploadButton").attr('disabled',false);},
	    'onCancel'  : function(){$("#uploadButton").attr('disabled',true);},
	    'auto'      : false,
	    'sizeLimit'	: '10240000',
	    'onComplete':function(event, queueID, fileObj, response, data){
	    				showData();
	    			}
	  });
});

function uploadifyUpload(){   
    $('#file_upload').uploadifyUpload();   
} 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 var html="<input name=title  type=file> <input type=button value=Delete onclick=\"remove(this)\">";
 function add()
 {
 var o=document.all["files"];
 var div=document.createElement("div");
 div.innerHTML=html;
 o.appendChild(div);
 div.childNodes[0].click();
 }
 function remove(obj)
 {
 obj.parentElement.parentElement.removeChild(obj.parentElement);
 }
 
 </script></td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="保 存" onclick="return moreSubmit()"/>
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>
&nbsp;
<input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                            </table>
             </s:form>
        <!-- 
        <table width="100%"  class="table_1 odd">
              <tbody>
    	                      <tr class="tit">
    	                        <td colspan="5"><ul>
                                	<h5>关联信息</h5>
    	                          <li class="selected"><a><span><i class="add">相关制度</i></span></a></li>
    	                          <li><a><span><i class="last">设备维修</i></span></a></li>
    	                          <li><a><span><i class="next">其他</i></span></a></li>
                                  <li class="fr"><a><span><i class="add">其他</i></span></a></li>
                                  <li class="button"><a><i class="add">新增</i></a></li>
  	                          </ul></td>
  	                        </tr>
    	                     
              </tbody>
        	  
      	    
      	  </table>
      	   -->	
      </div>
        <!--Table End-->
</div>
</body>
</html>
