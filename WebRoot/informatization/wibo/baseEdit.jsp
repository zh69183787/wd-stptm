<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>认证微博开设情况编辑</title>
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
		$(document).ready(function(){
		  $("#certification").val($("#h_certification").val());	  
		});
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		function check(){
		  //去除多余空格
		  
		  var wiboName = $("#wiboName");
		  if(wiboName.val()==""){
			  wiboName.focus();
		    alert("请填写微博名称！");
		    return false;
		  }
		  var certification = $("#certification");
		  if(certification.val()==""){
			  certification.focus();
		    alert("请选择是否认证！");
		    return false;
		  }
		  var re=/^(0|[1-9][0-9]*)$/;
		  var re1=/^([1-9][0-9]*)$/;
		  var re2=/^[0-9]*$/;
		  var rem=/^(1[3-9]{1}[0-9]{1})\d{8}$/;
		  var mobilPhone=$("#mobilPhone").val();
		  var mobilPhones=$("#mobilPhone");
		  if(mobilPhone.length!=0)
		  if(!re2.test(mobilPhone)){
		  	alert("手机 号码为数字");
		  	mobilPhones.focus();
		  	return false;
		  }else if(mobilPhone.length!=11){
		  	alert("请填写11位正确的手机号码");
		  	mobilPhones.focus();
		  	return false;
		  }else if(!rem.test(mobilPhone)){
		  	alert("请填写正确的手机号");
		  	mobilPhones.focus();
		  	return false;
		  }
		  return true;		  
		}

$(function(){
	$("#certification").val($("#h_certification").val());
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
                	<li class="fin">团干部信息数据编辑</li>
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
        <s:form action="update" name="wibo" namespace="/wibo" method="post">
        <input type="hidden" name="removed" value="0">
        <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r"><input type="submit" value="确 认" onclick="return check();"/>
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd" style="width:25%;">微博名称</td>
                                <td style="color:red" style="width:25%;">
                                <input type="text" id="wiboName" name="wiboName" maxlength="50" class="input_large" value="<s:property value='real_nameGroupWibovo.wiboName'/>"/>
                                &nbsp;&nbsp;*
                                </td>
                           
                                <td class="t_r lableTd" style="width:25%;">是否已认证</td>
                                <td style="color:red" style="width:25%;">
                                <select name="certification" id="certification" class="input_large">
                                	<option value="">---请选择---</option>
                                    <option value="1">是</option>
                                    <option value="0">否</option>                
                                </select>
                                &nbsp;&nbsp;*
                                <input type="hidden" id="h_certification" value="<s:property value='real_nameGroupWibovo.certification'/>">
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">联系人</td>
                                <td><input type="text" id="linkName" name="linkName" maxlength="50" class="input_large" value="<s:property value='real_nameGroupWibovo.linkName'/>"/></td>
                                <td class="t_r lableTd">手机</td>
                                <td><input type="text" id="mobilPhone" name="mobilPhone" maxlength="11" class="input_large" value="<s:property value='real_nameGroupWibovo.mobilPhone'/>"/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">微博地址</td>
                                <td colspan="3"><input type="text" id="wiboAddress" name="wiboAddress" maxlength="100" style="width:82%;" value="<s:property value='real_nameGroupWibovo.wiboAddress'/>"/></td>
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
