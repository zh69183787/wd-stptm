<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>团员青年信息修改</title>
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
		<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
		</style>
		<script type="text/javascript">
		
		 //定义子窗口的名字
        var newWindow = ""; 
        
        $(function(){
			$('#updateDate').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"yearRange":'1900:+nn' 
			});
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
				
			$(".ui-datepicker-close").live("click", function (){              
              $("#updateDate").val("");              
            });
			
		});	
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		var re=/^(0|[1-9][0-9]*)$/;
		  var re1=/^([1-9][0-9]*)$/;
		  var re2=/^[0-9]*$/;
		  //验证数字
		  var groupMale=$("#groupMale").val();
		  var groupMales=$("#groupMale");
		  if(!re2.test(groupMale)){
		  	alert("请输入数字");
		  	groupMales.focus();
		  	return false;
		  }else if(groupMale.length>1){
		  	if(!re.test(groupMale)){
			  	alert("只能输入非0开头的正整数");
			  	groupMales.focus();
			  	return false;
			  }
		  }
		  var groupFemMale=$("#groupFemMale").val();
		  var groupFemMales=$("#groupFemMale");
		  if(!re2.test(groupFemMale)){
		  	alert("请输入数字");
		  	groupFemMales.focus();
		  	return false;
		  }else if(groupFemMale.length>1){
		  	if(!re.test(groupFemMale)){
			  	alert("只能输入非0开头的正整数");
			  	groupFemMales.focus();
			  	return false;
			  }
		  }
		  var under_35YouthMale=$("#under_35YouthMale").val();
		  var under_35YouthMales=$("#under_35YouthMale");
		  if(!re2.test(under_35YouthMale)){
		  	alert("请输入数字");
		  	under_35YouthMales.focus();
		  	return false;
		  }else if(under_35YouthMale.length>1){
		  	if(!re.test(under_35YouthMale)){
			  	alert("只能输入非0开头的正整数");
			  	under_35YouthMales.focus();
			  	return false;
			  }
		  }
		  
		  var under_35YouthFemale=$("#under_35YouthFemale").val();
		  var under_35YouthFemales=$("#under_35YouthFemale");
		  if(!re2.test(under_35YouthFemale)){
		  	alert("请输入数字");
		  	under_35YouthFemales.focus();
		  	return false;
		  }else if(under_35YouthFemale.length>1){
		  	if(!re.test(under_35YouthFemale)){
			  	alert("只能输入非0开头的正整数");
			  	under_35YouthFemales.focus();
			  	return false;
			  }
		  }
		  
		  var under_28YouthNotGroup=$("#under_28YouthNotGroup").val();
		  var under_28YouthNotGroups=$("#under_28YouthNotGroup");
		 if(!re2.test(under_28YouthNotGroup)){
		  	alert("请输入数字");
		  	under_28YouthNotGroups.focus();
		  	return false;
		  }else if(under_28YouthNotGroup.length>1){
		  	if(!re.test(under_28YouthNotGroup)){
			  	alert("只能输入非0开头的正整数");
			  	under_28YouthNotGroups.focus();
			  	return false;
			  }
		  }
		  var member28=$("#member28").val();
		  var member28s=$("#member28");
		  if(!re2.test(member28)){
		  	alert("请输入数字");
		  	member28s.focus();
		  	return false;
		  }else if(member28.length>1){
		  	if(!re.test(member28)){
			  	alert("只能输入非0开头的正整数");
			  	member28s.focus();
			  	return false;
			  }
		  }
		  var member35=$("#member35").val();
		  var member35s=$("#member35");
		  if(!re2.test(member35)){
		  	alert("请输入数字");
		  	member35s.focus();
		  	return false;
		  }else if(member35.length>1){
		  	if(!re.test(member35)){
			  	alert("只能输入非0开头的正整数");
			  	member35s.focus();
			  	return false;
			  }
		  }
		  var newGroup=$("#newGroup").val();
		  var newGroups=$("#newGroup");
		  if(!re2.test(newGroup)){
		  	alert("请输入数字");
		  	newGroups.focus();
		  	return false;
		  }else if(newGroup.length>1){
		  	if(!re.test(newGroup)){
			  	alert("只能输入非0开头的正整数");
			  	newGroups.focus();
			  	return false;
			  }
		  }
		  var newMember=$("#newMember").val();
		  var newMembers=$("#newMember");
		  if(!re2.test(newMember)){
		  	alert("请输入数字");
		  	newMembers.focus();
		  	return false;
		  }else if(newMember.length>1){
		  	if(!re.test(newMember)){
			  	alert("只能输入非0开头的正整数");
			  	newMembers.focus();
			  	return false;
			  }
		  }
		
		
		  var unit = $("#unit");
		  if(unit.val()==""){
		  alert("单位必须选择");
		  	return false;
		  }
		  return true;		  
		}
		

$(function(){
	$("#unit").val($("#h_unit").val());
});
        </script></head>

<body>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">团委信息化</a></li>                	
                	<li class="fin">团员青年信息修改</li>
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
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        <s:form action="updateGroupMemberId" name="member" namespace="/member">
       	 <input type="hidden" id="id" name="id" value="<s:property value='avo.id'/>"/>
       	  <input type="hidden" name="removed" value="0">
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;        	      
       	      </th>
                                  </thead>
                              <tbody>
                                <tr>
                                <td class="t_r lableTd">单位</td>
                                <td style="color:red">
	                                <select name="unit" id="unit" class="input_large">
	                                    <option value="">---请选择---</option>
	                                    <option value="集团机关团总支">集团机关团总支</option>
	                                    <option value="运营一公司团委">运营一公司团委</option>
	                                    <option value="运营二公司团委">运营二公司团委</option>
	                                    <option value="运营三公司团委">运营三公司团委</option>
	                                    <option value="运营四公司团委">运营四公司团委</option>
	                                    <option value="运管中心团委">运管中心团委</option>
	                                    <option value="维保公司团委">维保公司团委</option>
	                                    <option value="隧道院团委">隧道院团委</option>  
	                                    <option value="磁浮公司团委">磁浮公司团委</option>
	                                    <option value="技术中心团委">技术中心团委</option>
	                                    <option value="资产公司团委">资产公司团委</option>
	                                    <option value="大桥公司团委">大桥公司团委</option> 
	                                    <option value="培训中心团委">培训中心团委</option>                                   
	                                </select>&nbsp;&nbsp;*
	                                <input  type="hidden" id="h_unit" value="<s:property value='avo.unit' />">
                                </td>
                                <td class="t_r lableTd">共青团员男</td>
                                <td >
                                <input type="text" id="groupMale" name="groupMale" maxlength="20" class="input_large" value="<s:property value='avo.groupMale'/>"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">共青团员女</td>
                                <td>
                                <input type="text" id="groupFemMale" name="groupFemMale" maxlength="20" class="input_large" value="<s:property value='avo.groupFemMale'/>"/>
                                </td>
                                <td class="t_r lableTd">35岁以下青年（含35岁）男</td>
                                <td>
                                <input type="text" id="under_35YouthMale" name="under_35YouthMale" maxlength="20" class="input_large" value="<s:property value='avo.under_35YouthMale'/>"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">更新时间</td>
                                <td>
                                <input id="updateDate" name="updateDate" type="date" readonly="readonly" class="input_large"  value="<s:property value='avo.updateDate'/>"/>
                                </td>
                                <td class="t_r lableTd">35岁以下青年（含35岁）女</td>
                                <td>
                                <input type="text" id="under_35YouthFemale" name="under_35YouthFemale" maxlength="20" class="input_large" value="<s:property value='avo.under_35YouthFemale'/>"/>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">28岁以下未入团青年（含28岁）</td>
                                <td>
                                <input type="text" id="under_28YouthNotGroup" name="under_28YouthNotGroup" maxlength="20" class="input_large" value="<s:property value='avo.under_28YouthNotGroup'/>"/>
                                </td>
                                <td class="t_r lableTd">28岁党员（含28岁）</td>
                                <td>
                                <input type="text" id="member28" name="member28" maxlength="20" class="input_large" value="<s:property value='avo.member28'/>"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">35岁党员（含35岁）</td>
                                <td>
                                <input type="text" id="member35" name="member35" maxlength="20" class="input_large" value="<s:property value='avo.member35'/>"/>
                                </td>
                                <td class="t_r lableTd">今年新入团人数</td>
                                <td>
                                <input type="text" id="newGroup" name="newGroup" maxlength="20" class="input_large" value="<s:property value='avo.newGroup'/>"/>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">今年推优入党人数</td>
                                <td>
                                <input type="text" id="newMember" name="newMember" maxlength="20" class="input_large" value="<s:property value='avo.newMember'/>"/>
                                </td>
                              </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>

</td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
