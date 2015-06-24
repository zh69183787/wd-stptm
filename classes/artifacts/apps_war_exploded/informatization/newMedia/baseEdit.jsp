<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>团组织新媒体建设（汇总情况以公司为单位）信息修改</title>
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
			$('#startTime').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"yearRange":'1900:+nn' 
			});
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
				
			$(".ui-datepicker-close").live("click", function (){              
              $("#startTime").val("");              
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
		  var directlyUnitName = $("#directlyUnitName");		  
		  if(directlyUnitName.val()==""){
		    alert("单位必须选择");
		    return false;
		  }
		  var independentDomain=$("#independentDomain");
		  if(independentDomain.val()==""){
		  	alert("是否独立域名必须选择");
		  	return false;
		  }
		  var re=/^(0|[1-9][0-9]*)$/;
		  var re1=/^([1-9][0-9]*)$/;
		  var re2=/^[0-9]*$/;
		  var averageDailyTraffic=$("#averageDailyTraffic").val();
		  var averageDailyTraffics=$("#averageDailyTraffic")
		  if(!re2.test(averageDailyTraffic)){
		  	alert("日均访问量为数字计数，请填写正确的数字");
		  	averageDailyTraffics.focus();
		  	return false;
		  }else if(averageDailyTraffic.length>1){
		  	if(!re1.test(averageDailyTraffic)){
		  		alert("两位以上的数字不能以0开头");
		  		averageDailyTraffics.focus();
		  		return false;
		  	}
		  }
		  return true;		  
		}
		

$(function(){
	$("#independentDomain").val($("#h_independentDomain").val());
	$("#directlyUnitName").val($("#h_directlyUnitName").val());
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
                	<li class="fin">团组织新媒体建设（汇总情况以公司为单位）信息修改</li>
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
        <s:form action="updateGroupNewMediaId" name="newMedia" namespace="/newMedia">
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
                                <td class="t_r lableTd">名称(所属直属单位团组织)</td>
                                <td style="color:red">
                                <select name="directlyUnitName" id="directlyUnitName" class="input_large">
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
                                <input type="hidden" id="h_directlyUnitName" value="<s:property value='avo.directlyUnitName'/>"/>
                                
                                </td>
                           
                                <td class="t_r lableTd">开通（开展）时间</td>
                                <td style="color:red"><input type="text" id="startTime" name="startTime" value="<s:property value='avo.startTime'/>" class="input_large" maxlength="20" />&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">是否独立域名</td>
                                <td style="color:red">
                                <select name="independentDomain" id="independentDomain" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="是">是</option>
                                    <option value="否">否</option>                                 
                                </select>&nbsp;&nbsp;*
                                <input type="hidden" id="h_independentDomain" value="<s:property value='avo.independentDomain'/>" />
                                </td>
                                <td class="t_r lableTd">日均访问量</td>
                                <td>
                                <input type="text" id="averageDailyTraffic" value="<s:property value='avo.averageDailyTraffic' />" name="averageDailyTraffic" maxlength="18" class="input_large" />
                                </td>
                                </tr>
                              	<tr>
                                <td class="t_r lableTd">被评过的市级荣誉</td>
                                <td>
                                <input type="text" id="honor" name="honor" value="<s:property value='avo.honor'/>" maxlength="18" class="input_large" /></td>
                                </td>
                                </tr>
                                <tr row="3" >
                                <td class="t_r lableTd">网站（页）简介</td>
                                <td colspan="3">
                                <textarea rows="3" cols="60" maxlength="500" id="webIntroduction" name="webIntroduction">
                                	<s:property value='avo.webIntroduction'/>
                                </textarea>
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
