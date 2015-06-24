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
String inputUserName=null;
if(cookies !=null){
	//取填报人
	for (Cookie cookie:cookies) {
		if("userName".equals(cookie.getName())){
			inputUserName = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			break;
		}
	}
}
%>
<% String basePath = request.getContextPath();  %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>
跟踪计划列表
</title>
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
	    <link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
		<script src="../js/jquery.uploadify.v2.1.4.min.js"></script>
		<script src="../js/swfobject.js"></script>
		<script src="js/attachMultiple.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>		
        <script type="text/javascript">
        var basePath = "<%=basePath%>";
        var thisDate = new Date();
        var thisDateStr = thisDate.getFullYear()+"-"+(thisDate.getMonth()+1)+"-"+thisDate.getDate();
        
        String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
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
				
		});	
		
		$(function(){
			if($("[name=followChildId]").val()==""||$("[name=infoType]").val()==""){
				alert("请打开正确的链接！");
				window.close();
			}
			
			$("[id=attach]").each(function(index){
				if(index==0){
					showData("view",0);
				}else{
					if($("#followState").val()=="2"){
						showData("view",index);
					}else{
						showData("edit",index);
					}
				}
			});
			
			var infoType = $("#infoType").val();
			if(infoType=="1"){
				$("[id=showTr2]").hide();
			}else if(infoType=="2"){
				$("[id=showTr1]").hide();
				
				$("[id^=finishTime]").each(function(){
					if($(this).val()==""){
						$(this).val(thisDateStr);
					}
				});
			}
			
			var i = 0;
			$("[id=dataTr]").each(function(){
				if($(this).find("#planId").val()==""){
					if(i==0&&infoType=="2"){
						$(this).hide();
					}
					if(i>0){
						$(this).hide();
					}
					i++;
				}else{
        			$(this).find("#deleteButton").show();
        			$(this).find("#modifyButton").show();
        			$(this).find("#planName").attr("disabled",true);
        			$(this).find("[id^=planFinishTime]").attr("disabled",true);
        			$(this).find("#planResult").attr("disabled",true);
				}
			});
			
			$("[id=reasonTd]").each(function(){
				$(this).html($(this).html().replaceAll("&lt;","<").replaceAll("&gt;",">").replaceAll("&amp;","&"));
			});
			
			if($("#followState").val()=="2"){
				$("[id^=file_upload]").remove();
				$("[id^=uploadButton]").hide();
				$("#saveButton").hide();
			}
		});
		
		$(function(){
			$('[id^=planFinishTime]').each(function(){
				$(this).datepicker({
					//inline: true								
					"changeYear":true,
					"minDate":0
				});
			});
			$('[id^=finishTime]').each(function(){
				$(this).datepicker({
					//inline: true								
					"changeYear":true,
					"minDate":0
				});
			});
		});
		
		function addRow(obj){
			if($(obj).parents("tr").next().attr("style")!=null&&$(obj).parents("tr").next().attr("style").indexOf("display:none")){
				if($.trim($(obj).val())!=""){
					$(obj).parents("tr").next().show();
				}
			}
		}
		
		function modifyPlanInfo(obj){
			alert("提示：保存后才会记录修改信息。");
			var tr = $(obj).parents("tr");
			tr.find("#planName").attr("disabled",false);
			tr.find("[id^=planFinishTime]").attr("disabled",false);
			tr.find("#planResult").attr("disabled",false);
			tr.find("#changeReason").attr("disabled",false);
			tr.find("#cancelModifyButton").show();
			tr.find("#modifyButton").hide();
			tr.find("#ifChanged").val("1");
		}
		
		function cancelModifyPlanInfo(obj){
			var tr = $(obj).parents("tr");
			tr.find("#planName").attr("disabled",true);
			tr.find("#planName").val(tr.find("#hiddenPlanName").val());
			tr.find("[id^=planFinishTime]").attr("disabled",true);
			tr.find("[id^=planFinishTime]").val(tr.find("#hiddenPlanFinishTime").val());
			tr.find("#planResult").attr("disabled",true);
			tr.find("#planResult").val(tr.find("#hiddenPlanResult").val());
			tr.find("#changeReason").attr("disabled",true);
			tr.find("#changeReason").val("");
			tr.find("#cancelModifyButton").hide();
			tr.find("#modifyButton").show();
			tr.find("#ifChanged").val("0");
		}
		
		function deletePlanInfo(obj){
			$(obj).parents("tr").find("#removed").val("1");
			$(obj).parents("tr").find("#ifChanged").val("1");
			$(obj).parents("tr").hide();			
			alert("提示：表单保存后才会执行删除操作。");
		}
		
		function changeFinishDate(obj){
			$(obj).parents("tr").find("[id^=finishTime]").val(thisDateStr);
		}
		
		function saveAdd(){
			var str = "";
			var index = 0;
			var flag = true;
			//var flag2 = false;
			var index2 = 0;
			var infoType = $("#infoType").val();
			$("[id=dataTr]").each(function(){
				if($.trim($(this).find("#planName").val())!=""){
					index++;
					if(infoType=="1"){
						if($.trim($(this).find("[id^=planFinishTime]").val())==""){
							flag = false;
							alert("请填写计划完成时间！");
							$(this).find("[id^=planFinishTime]").focus();
							return false;
						}
						if($.trim($(this).find("#planResult").val())==""){
							flag = false;
							alert("请填写计划成果！");
							$(this).find("#planResult").focus();
							return false;
						}	
						if($(this).find("#ifChanged").val()=="1"&&$(this).find("#removed").val()=="0"&&$.trim($(this).find("#changeReason").val())==""){
							flag = false;
							alert("请填写变更原因！");
							$(this).find("#changeReason").focus();
							return false;
						}	
					}else if(infoType=="2"){
						if($(this).find("#hiddenFinishTime").val()!=$(this).find("[id^=finishTime]").val()||$(this).find("#attach").val()!=""){
							$(this).find("#ifChanged").val("1");
							//flag2 = true;
						}
						if($(this).find("#attachSpan").html()==""){
							index2++;
						}
					}
					if(index>1){
						str += ",";
					}
					str += "{\"id\":\""+$(this).find("#planId").val()+"\",\"planName\":\""+$(this).find("#planName").val()+"\",\"planFinishTime\":\""+$(this).find("[id^=planFinishTime]").val()+"\",\"planResult\":\""+$(this).find("#planResult").val()+"\",\"finishTime\":\""+$(this).find("[id^=finishTime]").val()+"\",\"attach\":\""+$(this).find("#attach").val()+"\",\"removed\":\""+$(this).find("#removed").val()+"\",\"changeReason\":\""+$(this).find("#changeReason").val()+"\",\"ifChanged\":\""+$(this).find("#ifChanged").val()+"\"}";
				}
			});
			if(index==0){
				alert("请填写计划！");
			//}else if(infoType=="2"&&!flag2){
			//	alert("请提交成果！");
			}else{
				if(flag){
					str = "["+str+"]";
					//str = $.parseJSON(str); 
					$("[name=planInfo]").val(str); 
					if(infoType=="2"||(infoType=="1"&&confirm("提示：提交计划后若再要修改计划需填写变更原因，且系统会记录变更信息，是否提交？"))){
						if(infoType=="2"){
							if(index2==0){
								if(confirm("部门是否已经完成所有跟踪计划？选择确定，将关闭提交成果；选择取消，仍然可以提交成果。")){
									$("#followState").val("2");//已完成
								}
							}
						}
						$("#form").submit();
					}
				}
			}
		}
        

    	</script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">督办跟踪</a></li> 
                	<li>
                		跟踪计划列表
                	</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                <!-- <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>
                <li class="selected" onclick="showOrHide(this)"><a class="query" href="#">查询</a></li> -->
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Table-->
        <div class="mb10">
        	<s:form action="saveFollowPlan" id="form" namespace="/dbFollow"> 
        	<input type="hidden" name="followChildId" value="<s:property value='#request.dbFollowChild.id'/>"/>
        	<input type="hidden" name="dbName" value="<s:property value='dbFollow.dbName'/>"/>
        	<input type="hidden" name="dealPerson" value="<%=inputUserName %>"/>
        	<input type="hidden" name="planInfo"/>
        	<input type="hidden" name="infoType" id="infoType" value="<s:property value='#request.infoType'/>"/>
        	<input type="hidden" name="followState" id="followState" value="<s:property value='#request.followState'/>">
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="30"><s:property value='dbFollow.dbName'/></th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td class="t_c">跟踪部门</td>
                                <td class="t_c" colspan="2">要求</td>
                                <td class="t_c" colspan="2">计划提交时间</td>
                              </tr>
                              <tr>
	 	                            <td class="t_c"><s:property value='#request.dbFollowChild.followDeptName'/></td>
	 	                            <td class="t_c" colspan="2"><s:property value='#request.dbFollowChild.require'/></td>
	 	                            <td class="t_c" colspan="2"><s:property value='#request.dbFollowChild.planSubmitTime'/></td>
	                          </tr>
	                          <tr>
                                <td class="t_c lableTd">
                                	<b>相关附件</b>
                                </td>
                                <td colspan="10">
 									<input type="hidden" id="attach" value='<s:property value='dbFollow.attach'/>'>
									<!-- 
									<input id="file_upload" type="file" value="上传" />
									<input type="button" value="上传" disabled="disabled" id="uploadButton" onclick="uploadifyUpload(this)" />
									 -->
									<span id="attachSpan"></span>
								</td>
							  </tr>
                              <tr class="tit">
                                <td class="t_c" style="white-space:nowrap;width:20%;">计划名称</td>
                                <td class="t_c" style="white-space:nowrap;width:10%;">计划完成时间</td>
                                <td class="t_c" style="white-space:nowrap;width:25%;">计划成果</td>
                                <td class="t_c" style="white-space:nowrap;width:30%;" id="showTr1">变更原因</td>
                                <td class="t_c" style="white-space:nowrap;width:10%;" id="showTr1">操作</td>
                                <td class="t_c" style="white-space:nowrap;width:10%;" id="showTr2">实际完成时间</td>
                                <td class="t_c" style="white-space:nowrap;width:30%;" id="showTr2">实际成果</td>
                              </tr>
                              
                               <s:iterator value="#request.list" status="st" id="list">
	 	                          <tr id="dataTr">
	 	                            <td class="t_c">
	 	                            	<input type="text" id="planName" onblur="addRow(this)" value="<s:property value='#list.planName'/>" maxlength="100" style="width:98%"/>
	 	                            	<input type="hidden" id="hiddenPlanName" value="<s:property value='#list.planName'/>">
	 	                            	<input type="hidden" id="hiddenPlanFinishTime" value="<s:property value='#list.planFinishTime'/>">
	 	                            	<input type="hidden" id="hiddenPlanResult" value="<s:property value='#list.planResult'/>">
	 	                            	<input type="hidden" id="hiddenFinishTime" value="<s:property value='#list.finishTime'/>">
	 	                            	<input type="hidden" id="planId" value="<s:property value='#list.id'/>"/>
	 	                            	<input type="hidden" id="removed" value="0"/>
	 	                            	<input type="hidden" id="ifChanged" value="0"/>
	 	                            </td>
	 	                            <td class="t_c"><input type="text" id="planFinishTime<s:property value='#st.index'/>" readonly="readonly" value="<s:property value='#list.planFinishTime'/>"/></td>
	 	                            <td class="t_c"><input type="text" id="planResult" style="width:98%" maxlength="100" value="<s:property value='#list.planResult'/>"/></td>
	 	                            <td class="t_c" id="showTr1"><input type="text" id="changeReason" style="width:98%" maxlength="200" disabled="disabled"/></td>
	 	                            <td class="t_c" id="showTr1">
	 	                            	<input type="button" value="修改" id="modifyButton" onclick="modifyPlanInfo(this);" style="display:none">
	 	                            	<input type="button" value="取消" id="cancelModifyButton" onclick="cancelModifyPlanInfo(this);" style="display:none">
	 	                            	<input type="button" id="deleteButton" value="删除" onclick="deletePlanInfo(this)" style="display:none"/>
	 	                            </td>
	 	                            <td class="t_c" id="showTr2"><input type="text" id="finishTime<s:property value='#st.index'/>" disabled="disabled" value="<s:property value='#list.finishTime'/>"/></td>
	 	                            <td class="t_c" id="showTr2">
	 	                            	<input type="hidden" id="attach" value="<s:property value='#list.attach'/>">
	 									<input id="file_upload<s:property value='#st.index'/>" type="file" value="上传" style="float: left;"/>
										<input type="button" value="上传" disabled="disabled" id="uploadButton<s:property value='#st.index'/>" onclick="uploadifyUpload(<s:property value='#st.index'/>);changeFinishDate(this);" style="float:left;"/>
										<span id="attachSpan"></span>
	 	                            </td>
	                            </tr>
	                            <s:if test="#list.planChangeHistory!=null">
	                            <tr style="display:none">
	                            	<td colspan="10" id="reasonTd"><s:property value='#list.planChangeHistory'/></td>
	                            </tr>
	                            </s:if>
	                            </s:iterator>
                              </tbody>
                              <tr class="tfoot"><td colspan="6" class="t_c"><div class="clearfix"><input type="button" value="保 存" onclick="saveAdd();" id="saveButton"/>
                              &nbsp;&nbsp;<input type="button" value="关 闭" onclick="window.close();"/></div></td></tr>
                            </table>
         </s:form>                 
      </div>
        <!--Table End-->
</div>
</body>
</html>


