<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>(各直属单位团组织)主题活动信息修改</title>
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
			$('#time').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"yearRange":'1900:+nn' 
			});
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
				
			$(".ui-datepicker-close").live("click", function (){              
              $("#time").val("");              
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
		  var unit = $("#unitdirectly");
		  var type=$("#type");
		  if(unit.val()==""){
		  alert("单位必须选择");
		  	return false;
		  }
		  if(type.val()==""){
		  	alert("活动类型必须选");
		  	return false;
		  }
		  var re=/^(0|[1-9][0-9]*)$/;
		  var re1=/^([1-9][0-9]*)$/;
		  var re2=/^[0-9]*$/;
		  var participation=$("#participation").val();
		  var participations=$("#participation");
		  if(!re2.test(participation)){
		  	alert("请输入数字");
		  	participations.focus();
		  	return false;
		  }else if(participation>1){
		  	if(!re1.test(participation)){
		  		alert("两位以上数字不能以0开头");
		  		participations.focus();
		  		return false;
		  	}
		  }
		  return true;		  
		}
		

$(function(){
	$("#unit").val($("#h_unit").val());
	$("#type").val($("#h_type").val());
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
                	<li class="fin">(各直属单位团组织)主题活动信息修改</li>
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
        <s:form action="updateGroupMemberDirectlyId" name="memberDirectly" namespace="/memberDirectly">
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
                                <input type="hidden" id="h_unit" value="<s:property value='avo.unit'/>" />
                                </td>
                           
                                <td class="t_r lableTd">活动类型</td>
                                <td style="color:red">
                                <select name="type" id="type" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="志愿者活动">志愿者活动</option>
                                    <option value="公益活动">公益活动</option>
                                    <option value="党建带团建">党建带团建</option>
                                    <option value="团建共建">团建共建</option>
                                    <option value="群众路线">群众路线</option>
                                    <option value="我的中国梦">我的中国梦</option>
                                    <option value="青年人才培养">青年人才培养</option>
                                    <option value=">听研评荐">听研评荐</option>  
                                    <option value="青年品牌创建">青年品牌创建</option>
                                    <option value="创新创效">创新创效</option>
                                    <option value="青年导师团">青年导师团</option>
                                    <option value="其他主题活动">其他主题活动</option>                                  
                                </select>&nbsp;&nbsp;*
                                <input type="hidden" id="h_type" value="<s:property value='avo.type' />"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;</td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">活动主题或内容</td>
                                <td><input type="text" id="activeTitleOrContext" name="activeTitleOrContext" value="<s:property value='avo.activeTitleOrContext' />" maxlength="18" class="input_large" /></td>
                                <td class="t_r lableTd">时间</td>
                                <td>
                                <input type="date" id="time" name="time" maxlength="20" value="<s:property value='avo.time' />" class="input_large" /></td>
                                
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">地点</td>
                                <td>
                                <input type="text"  id="address" name="address" value="<s:property value='avo.address' />" class="input_large" />
                                </td>
                                <td class="t_r lableTd">负责人</td>
                                <td>
                                <input type="text" name="head" id="head" value="<s:property value='avo.head' />"  class="input_large">
                                    
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">参与人数</td>
                                <td><input type="text" id="participation" value="<s:property value='avo.participation'/>" name="participation" maxlength="20" class="input_large" /></td>
                                <
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
