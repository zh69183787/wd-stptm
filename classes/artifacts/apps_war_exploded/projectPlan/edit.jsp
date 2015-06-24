<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目计划编辑</title>
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
		
		
        </script></head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">项目计划编辑</li>
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
        <s:form action="updateProjectPlanById" name="ProjectPlan" namespace="/projectPlan">
        <input type="hidden" name="id" value="<s:property value='projectPlanVO.id'/>">
        <input type="hidden" name="operateTime" value="<s:property value='projectPlanVO.operateTime'/>">
        <input type="hidden" name="operateUser" value="<s:property value='projectPlanVO.operateUser'/>">
        <input type="hidden" name="removed" value="<s:property value='projectPlanVO.removed'/>">
        <input type="hidden" name="applyCompany" value="<s:property value='projectPlanVO.applyCompany'/>"/>
        <input type="hidden" name="year" value="<s:property value='projectPlanVO.year'/>"/>
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
	                              <td class="t_r lableTd" style="width:20%;">计划项目名称</td>
	                                <td style="width:30%;"><input type="text" id="planProjectName" name="planProjectName" maxlength="50" class="input_xxlarge" value="<s:property value='projectPlanVO.planProjectName'/>"/></td>
                                
                              	<td class="t_r lableTd" style="width:20%;">申报单位</td>
                                <td style="width:30%;">
                                	<select name="applyCompanyId">
										<option value="">---------请选择---------</option>
										<s:iterator value="#request.deptList" id="dept">
											<s:if test="#request.projectPlanVO.applyCompanyId==#dept[0]">
												<option value="<s:property value="#dept[0]"/>" selected="selected"><s:property value="#dept[1]"/></option>
											</s:if>
											<s:else>
												<option value="<s:property value="#dept[0]"/>"><s:property value="#dept[1]"/></option>
											</s:else>
										</s:iterator>
									</select>
                                </td>
                              </tr>
                              <tr>
                              	<td class="t_r lableTd">项目属性</td>
                                <td><input type="text" id="property" name="property" maxlength="50" class="input_large" value="<s:property value='projectPlanVO.property'/>"/></td>
                                <td class="t_r lableTd">概算(万元)</td>
                                <td><input type="text" id="estimate" name="estimate" maxlength="50" class="input_large" value="<s:property value='projectPlanVO.estimate'/>"/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">立项依据</td>
                                <td colspan="3"><textarea id="according" name="according" rows="5" cols="10" style="overflow:auto;  border-width:1"><s:property value='projectPlanVO.according'/></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">实施方案</td>
                                <td colspan="3"><textarea id="plan" name="plan" rows="5" cols="10" style="overflow:auto;  border-width:1"><s:property value='projectPlanVO.plan'/></textarea></td>
                                </tr>
                               <tr>
                                <td class="t_r lableTd">项目目标</td>
                                <td colspan="3"><textarea id="target" name="target" rows="5" cols="10" style="overflow:auto;  border-width:1"><s:property value='projectPlanVO.target'/></textarea></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">备注</td>
                                <td colspan="3"><textarea id="remark" name="remark" cols="60" rows="5" style="overflow:auto; border-width:1" maxlength="600"><s:property value='projectPlanVO.remark'/></textarea></td>
                               </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/></td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
