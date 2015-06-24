<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>团干部信息数据录入</title>
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
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		$(function(){
			$('#birthday').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"yearRange":'1900:+nn' 
			});
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
				
			$(".ui-datepicker-close").live("click", function (){              
              $("#birthday").val("");              
            });
			
		});	
		
		$(document).ready(function(){
		  var addSuccess = $("#addSuccess").val();
		  if(addSuccess=="success") alert("添加成功");		  
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		  //去除多余空格
		  
		  //验证数字
		  var re=/^(0|[1-9][0-9]*)$/;
		  var re1=/^([1-9][0-9]*)$/;
		  var re2=/^[0-9]*$/;
		  var branchNumber=$("#branchNumber").val();
		  var branchNumbers=$("#branchNumber")
		  if(!re2.test(branchNumber)){
		  	alert("请输入数字");
		  	branchNumbers.focus();
		  	return false;
		  }else if(branchNumber.length>1){
		  	if(!re1.test(branchNumber)){
		  		alert("两位以上数字不能以0开头");
		  		branchNumbers.focus();
		  		return false;
		  	}
		  }
		  //验证手机号
		  var mobilPhone=$("#mobilPhone").val();
		  var mobilPhones=$("#mobilPhone");
		  var rem=/^(1[3-9]{1}[0-9]{1})\d{8}$/;
		  if(mobilPhone.length!=0){
			  if(!re2.test(mobilPhone)){
			  	alert("手机号码是纯数字");
			  	mobilPhones.focus();
			  	return  false;
			  }else if(mobilPhone.length!=11){
			  	alert("请输入11位正确的手机号码");
			  	mobilPhones.focus();
			  	return false;
			  }else if(!rem.test(mobilPhone)){
			  	alert("请输入正确的手机号");
			  	mobilPhones.focus();
			  	return false;
			  }
			}  
		 var unit = $("#unitdirectly");
		  var gender=$("#gender");
		  var politicsStatus=$("#politicsStatus");
		  var job=$("#job");
		  var levelEducation=$("#levelEducation");
		  var degree=$("#degree");
		  if(unit.val()==""){
		  alert("所属直属单位团组织必须选择");
		  	return false;
		  }
		  if(degree.val()==""){
		  	alert("学位必须选择");
		  	return false;
		  }
		  if(levelEducation.val()==""){
		  	alert("文化程度必须选择");
		  	return false;
		  }
		  if(gender.val()==""){
		  	alert("性别必须选择");
		  	return false;
		  }
		  if(politicsStatus.val()==""){
		  	alert("政治面貌必须选择");
		  	return false;
		  }
		  if(job.val()==""){
		  	alert("担任团内职务必须选择");
		  	return false;
		  }
		  return true;		  
		}


        </script></head>

<body>
<input type="hidden" id="addSuccess" value="<s:property value='#request.addSuccess'/>"/>

	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">团委信息化</a></li>                	
                	<li class="fin">团干部信息数据录入</li>
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
        <s:form action="add" name="leader" namespace="/leader" method="post">
        <input type="hidden" name="removed" value="0">
        <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;
        	      <input type="reset" value="取 消" />
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd">所属直属单位团组织</td>
                                <td style="color:red">
                                <select name="unitdirectly" id="unitdirectly" class="input_large">
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
                                </td>
                           
                                <td class="t_r lableTd">支部名称</td>
                                <td style="color:red"><input type="text" id="branchName" name="branchName" class="input_large" maxlength="50" />&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">支部书记</td>
                                <td><input type="text" id="branchSecretary" name="branchSecretary" maxlength="80" class="input_large" /></td>
                                <td class="t_r lableTd">支部人数</td>
                                <td>
                                <input type="text" id="branchNumber" name="branchNumber" maxlength="18" class="input_large" /></td>
                                
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">担任团内职务</td>
                                <td style="color:red">
                                <select name="job" id="job" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="书记">书记</option>
                                    <option value="运营一公司团委">副书记</option>
                                    <option value="运营二公司团委">委员</option>
                                    <option value="运营一公司团委">候补委员</option>
                                    <option value="运营二公司团委">干事</option>
                                                                 
                                </select>&nbsp;&nbsp;*
                                </td>
                                <td class="t_r lableTd">性别</td>
                                <td style="color:red">
                                <select name="gender" id="gender" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="男">男</option>
                                    <option value="女">女</option>                
                                </select>&nbsp;&nbsp;*
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">出生年月</td>
                                <td><input type="text" id="birthday" name="birthday" maxlength="20" class="input_large" /></td>
                                <td class="t_r lableTd">政治面貌</td>
                                <td style="color:red">
                                <select name="politicsStatus" id="politicsStatus" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="中共党员">中共党员</option>
                                    <option value="预备党员">预备党员</option>
                                    <option value="共青团员">共青团员</option>
                                                                 
                                </select>&nbsp;&nbsp;*
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">文化程度</td>
                                <td style="color:red">
                                <select name="levelEducation" id="levelEducation" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="研究生">研究生</option>
                                    <option value="本科">本科</option>
                                    <option value="大专">大专</option>
                                    <option value="中专">中专</option>
                                    <option value="中等职业教育">中等职业教育</option>
                                    <option value="高中">高中</option>                        
                                </select>&nbsp;&nbsp;*
                                 </td>
                                <td class="t_r lableTd">学位</td>
                                <td style="color:red">
                                <select name="degree" id="degree" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="无">---无---</option>   
                                    <option value="博士">博士</option>
                                    <option value="硕士">硕士</option>
                                    <option value="学士">学士</option>
                                                                 
                                </select>&nbsp;&nbsp;*
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">岗位职务</td>
                                <td><input type="text" id="postOffice" name="postOffice" maxlength="60" class="input_large" /></td>
                                <td class="t_r lableTd">职称或技术等级</td>
                                <td><input type="text" id="titleorTechnicalLevel" name="titleorTechnicalLevel" maxlength="60" class="input_large" /></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">手机</td>
                                <td><input type="text" id="mobilPhone" name="mobilPhone" maxlength="11" class="input_large" /></td>
                                <td class="t_r lableTd">微博与微信名</td>
                                <td><input type="text" id="wiboorMicro" name="wiboorMicro" maxlength="50" class="input_large" /></td>
                             </tr>
                           		<tr>
                                <td class="t_r lableTd">全职或兼职</td>
                                <td><input type="text" id="fullorParttime" name="fullorParttime" maxlength="60" class="input_large" /></td>
                              </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>
&nbsp;
<input type="reset" value="取 消" />&nbsp;</td>
                              </tr>
                            </table>
                             </s:form>
       	  
        </div>
        <!--Table End-->
</div>
</body>
</html>
