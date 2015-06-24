<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
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
String deptId = "";
String deptName = "";
if("on".equals(filterButton)){
	CrossIpLogin crossIpLogin = new CrossIpLogin();
	UserInfo userInfo = new UserInfo();
	crossIpLogin.setUserInfo(request,userInfo);
	loginName = userInfo.getLoginName();
	userName = userInfo.getUserName();
	deptId = userInfo.getDeptId();
	deptName = userInfo.getDeptName();
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
		
		function checkJobNumber(){
		  var jobNumber = $("#jobNumber").val().replace(/(^\s*)|(\s*$)/g,'');
		  var name = $("#name").val().replace(/(^\s*)|(\s*$)/g,'');
		  $.ajax({
		    type: 'POST',
			url: 'checkJobNumber.action',
			dataType:'json',
			data:{
					"jobNumber" : jobNumber,	
					"name" : name,				
					"random" : Math.random()
				},
			error: function(){
					alert('系统错误,请与管理员联系!');
				},
			success: function(obj){						
					if(obj.info=="wrongJobNumber") {
					  alert("您填写的工号有误或数据库中没有此工号");
					  $("#jobNumber").focus();
					}
					else if(obj.info=="success") {
					  alert("您填写的工号与姓名正确无误");
					}
					else {
					  alert("您填写的姓名与工号不符，应为\""+obj.info+"\"");	
					  $("#name").focus();
					}											
				}		
		  });
		}
		
		function check(){
		  //去除多余空格
		  $("#jobNumber").val($("#jobNumber").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#name").val($("#name").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#idCard").val($("#idCard").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#nation").val($("#nation").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#birthplace").val($("#birthplace").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#residentialAddress").val($("#residentialAddress").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#zipCode").val($("#zipCode").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#homePhone").val($("#homePhone").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#mobilePhone").val($("#mobilePhone").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#cCompany").val($("#cCompany").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#position").val($("#position").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#jobTitles").val($("#jobTitles").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#technicalGrade").val($("#technicalGrade").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#companyAddress").val($("#companyAddress").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#companyZip").val($("#companyZip").val().replace(/(^\s*)|(\s*$)/g,''));
		  $("#companyPhone").val($("#companyPhone").val().replace(/(^\s*)|(\s*$)/g,''));
		  
		  
		  var jobNumber = $("#jobNumber");
		  if(jobNumber.val()==""){
		    jobNumber.focus();
		    alert("工号不能为空");
		    return false;
		  }
		  var name = $("#name");
		  if(name.val()==""){
		    name.focus();
		    alert("姓名不能为空");
		    return false;
		  }
		  //验证身份证号
		  var id_card=$("#idCard").val();		
		  if(id_card!="")  {
		    if(isIdCardNo(id_card)==false) return false;		
		  }
		  
		  //验证居住地邮编
		  var zip_code=$("#zipCode").val();	
		  if(zip_code!="")  {	  
		    if(isPostCode(zip_code)==false) return false;
		  }
		  
		  //验证居住地电话
		  var homePhone=$("#homePhone").val();
		  if(homePhone!=null&&homePhone!=""&&!homePhone.match(/^[0-9-]*$/)){		   
		      alert("居住地电话仅能输入数字和中划线");
		      $("#homePhone").focus();
		      return false;		   
		  }
		  
		  //验证手机号码
		  var mobile_phone=$("#mobilePhone").val();	
		  if(mobile_phone!="")  {	 
		    if(isMobileNO(mobile_phone)==false) return false;
		  }
		  
		  //验证单位邮编
		  var company_zip=$("#companyZip").val();	
		  if(company_zip!="")  {	  
		    if(isPostCode2(company_zip)==false) return false;
		  }
		  
		  //验证单位电话
		  var companyPhone=$("#companyPhone").val();
		  if(companyPhone!=null&&companyPhone!=""&&!companyPhone.match(/^[0-9-]*$/)){		   
		      alert("单位电话仅能输入数字和中划线");
		      $("#companyPhone").focus();
		      return false;		   
		  }
		  
		  //验证备注
		  var remarks=$("#remarks");
		  if(remarks.val().length > 500) {
		    alert("备注最多输入500字！");
		    remarks.focus();
		    return false;
		  }
		  		  
		  if($("#birthday").val()!=null && $("#birthday").val()!="" && !$("#birthday").val().match(/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/)){
        		alert("请在出生年月处使用控件输入合法的时间");        		      		
        		$("#birthday").focus();
        		return false;
        	}
		  
		  return true;		  
		}
		
		
//--身份证号码验证-支持新的带x身份证
function isIdCardNo(num) 
{
    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
    var error;
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;    
    var idCard=$("#idCard");
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {        
        alert("输入身份证号码长度不对！");
        idCard.focus();
        return false;
    }    
    // check and set value
    for(i=0;i<intStrLen;i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {            
            alert("错误的身份证号码！");
            idCard.focus();
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i]*factorArr[i];
        }
    }
    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6,14);        
        if (checkDate(date8) == false) {            
            alert("身份证中日期信息与您填写的出生年月不符！");
            idCard.focus();
            return false;
        }        
        // calculate the sum of the products
        for(i=0;i<17;i++) {
            lngProduct = lngProduct + varArray[i];
        }        
        // calculate the check digit
        intCheckDigit = 12 - lngProduct % 11;
        switch (intCheckDigit) {
            case 10:
                intCheckDigit = 'X';
                break;
            case 11:
                intCheckDigit = 0;
                break;
            case 12:
                intCheckDigit = 1;
                break;
        }        
        // check last digit
        if (varArray[17].toUpperCase() != intCheckDigit) {            
            alert("身份证效验位（末位）错误!...正确为： " + intCheckDigit );
            idCard.focus();
            return false;
        }
    } 
    else{        //length is 15
        //check date
        var date6 = idNumber.substring(6,12);
        if (checkDate(date6) == false) {
            alert("身份证中日期信息与您填写的出生年月不符！");
            idCard.focus();
            return false;
        }
    }    
    //return true;
}

//验证身份证中的出生年月是否与填写的出生年月相符
function checkDate(date)
{
    var birthday=$("#birthday").val();
    birthday=(birthday.replace("-","")).replace("-","");    
    if(birthday==date||birthday.substring(2,8)==date)   
    return true;
    else return false;
}
		
		
//邮政编码验证
function isPostCode(num){         
         var zipCode=$("#zipCode");
         //if(num=="" || num==null){ 
                  // alert("请输入邮政编码！");
                  // zipCode.focus();
                   //return false;
         //} 
         var regExp = /^[1-9][0-9]{5}$/;
         if(regExp.test(num)){
                   return true;
         }else if(num.length!=6){
                   alert("邮政编码是6位数字！");                   
                   zipCode.focus();
                   return false;
         }else {
                   alert("邮政编码格式不对！");                   
                   zipCode.focus();
                   return false;
         }
}

//单位邮编验证
function isPostCode2(num){         
         var companyZip=$("#companyZip");
         //if(num=="" || num==null){ 
                  // alert("请输入邮政编码！");
                  // zipCode.focus();
                   //return false;
         //} 
         var regExp = /^[1-9][0-9]{5}$/;
         if(regExp.test(num)){
                   return true;
         }else if(num.length!=6){
                   alert("单位邮编是6位数字！");                   
                   companyZip.focus();
                   return false;
         }else {
                   alert("单位邮编格式不对！");                   
                   companyZip.focus();
                   return false;
         }
}

//验证手机号码
function isMobileNO(num){              
         var mobilePhone=$("#mobilePhone");
         //if(num=="" || num==null){ 
                  // return false;
         //} 
         if(num.length!=11){
                   alert('请输入11位手机号码！');
                   mobilePhone.focus();
                   return false;
         }
         var myreg = /^(1[3-9]{1}[0-9]{1})\d{8}$/;
         if(!myreg.test(num)){
                   alert('请输入合法的手机号码！');                   
                   mobilePhone.focus();
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
                	<li><a href="#">工会人事数据库</a></li>                	
                	<li class="fin">数据录入</li>
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
        <s:form action="addHrBInfo" name="HrBInfo" namespace="/sthr">
        <input type="hidden" name="reportPerson" value="<%=loginName %>"/>
        <input type="hidden" name="reportPersonName" value="<%=userName %>"/>
        <input type="hidden" name="updatePerson" value="<%=loginName %>"/>
        <input type="hidden" name="reportDept" value="<%=deptId %>"/>
        <input type="hidden" name="reportDeptName" value="<%=deptName %>"/>
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
                                <td class="t_r lableTd">工号</td>
                                <td style="color:red"><input type="text" id="jobNumber" name="jobNumber" class="input_large" maxlength="20"/>&nbsp;&nbsp;*</td>
                           
                                <td class="t_r lableTd">姓名</td>
                                <td style="color:red"><input type="text" id="name" name="name" class="input_large" maxlength="20" />&nbsp;&nbsp;*
                                &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="校 验" onclick="checkJobNumber()"/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">身份证号</td>
                                <td><input type="text" id="idCard" name="idCard" maxlength="18" class="input_large" /></td>
                                <td class="t_r lableTd">性别</td>
                                <td><input type="radio" id="sex" name="sex" value="1"/>
                                  男
                                  <input type="radio" id="sex" name="sex" value="0"/>
                                  女</td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">出生日期</td>
                                <td><input id="birthday" name="birthday" type="date" readonly="readonly" class="input_large"/></td>
                                <td class="t_r lableTd">民族</td>
                                <td><input type="text" id="nation" name="nation" maxlength="20" class="input_large" /></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">籍贯</td>
                                <td><input type="text" id="birthplace" name="birthplace" maxlength="20" class="input_large" /></td>
                                <td class="t_r lableTd">政治面貌</td>
                                <td><select name="politicalLandscape" id="politicalLandscape" class="input_large">
                                    <option value="">---请选择---</option>
                                    <option value="中共党员">中共党员</option>
                                    <option value="民主党派">民主党派</option>
                                    <option value="共青团员">共青团员</option>
                                    <option value="群众">群众</option>                                    
                                </select></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">最高学历</td>
                                <td>
                                  <select name="highestDegree" id="highestDegree" class="input_large">
                                    <option value="">---请选择---</option>                                   
                                    <option value="博士">博士</option>                                    
                                    <option value="硕士">硕士</option>                                    
                                    <option value="本科">本科</option>
                                    <option value="大专">大专</option>
                                    <option value="中专">中专</option>
                                    <option value="高中">高中</option>
                                    <option value="初中">初中</option>
                                    <option value="小学">小学</option>
                                    
                                </select></td>
                                <td class="t_r lableTd">居住地址</td>
                                <td><input type="text" id="residentialAddress" name="residentialAddress" class="input_xlarge" maxlength="100" style="width:80%"/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">邮政编码</td>
                                <td><input type="text" id="zipCode" name="zipCode" maxlength="6" class="input_large" /></td>
                                <td class="t_r lableTd">居住地电话</td>
                                <td><input type="text" id="homePhone" name="homePhone" maxlength="20" class="input_large" /></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">手机号码</td>
                                <td><input type="text" id="mobilePhone" name="mobilePhone" maxlength="11" class="input_large" /></td>
                                <td class="t_r lableTd">目前工作单位</td>
                                <td>
                                <select name="cCompany" id="cCompany" class="input_large" style="width:55%">
                                    <option value="">---请选择---</option>   
                                    <option value="集团机关">集团机关</option> 
                                    <option value="上海共和新路高架发展有限公司">上海共和新路高架发展有限公司</option>
                                    <option value="上海轨道交通长宁线发展有限公司">上海轨道交通长宁线发展有限公司</option>
                                    <option value="上海轨道交通二号线东延伸发展有限公司">上海轨道交通二号线东延伸发展有限公司</option>
                                    <option value="上海轨道交通明珠线发展有限公司">上海轨道交通明珠线发展有限公司</option>
                                    <option value="上海轨道交通宝山线发展有限公司">上海轨道交通宝山线发展有限公司</option>
                                    <option value="上海轨道交通明珠线（二期）发展有限公司">上海轨道交通明珠线（二期）发展有限公司</option>
                                    <option value="上海莘闵轨道交通线发展有限公司">上海莘闵轨道交通线发展有限公司</option> 
                                    <option value="上海轨道交通浦东线发展有限公司">上海轨道交通浦东线发展有限公司</option>   
                                    <option value="上海轨道交通七号线发展有限公司">上海轨道交通七号线发展有限公司</option>
                                    <option value="上海轨道交通杨浦线发展有限公司">上海轨道交通杨浦线发展有限公司</option>
                                    <option value="上海轨道交通申松线发展有限公司">上海轨道交通申松线发展有限公司</option>
                                    <option value="上海轨道交通十号线发展有限公司">上海轨道交通十号线发展有限公司</option> 
                                    <option value="上海轨道交通申嘉线发展有限公司">上海轨道交通申嘉线发展有限公司</option>                                   
                                    <option value="上海轨道交通十一号线南段发展有限公司">上海轨道交通十一号线南段发展有限公司</option>
                                    <option value="上海轨道交通十二号线发展有限公司">上海轨道交通十二号线发展有限公司</option>
                                    <option value="上海轨道交通十三号线发展有限公司">上海轨道交通十三号线发展有限公司</option>
                                    <option value="上海申通地铁股份有限公司">上海申通地铁股份有限公司</option>
                                    <option value="上海申通轨道交通研究咨询有限公司">上海申通轨道交通研究咨询有限公司</option>
                                    <option value="上海地铁资产经营管理有限公司">上海地铁资产经营管理有限公司</option>   
                                    <option value="上海黄浦江大桥建设有限公司">上海黄浦江大桥建设有限公司</option>  
                                    <option value="上海轨道交通运营管理中心">上海轨道交通运营管理中心</option>  
                                    <option value="上海地铁第一运营有限公司">上海地铁第一运营有限公司</option>                                    
                                    <option value="上海地铁第二运营有限公司">上海地铁第二运营有限公司</option>                                    
                                    <option value="上海地铁第三运营有限公司">上海地铁第三运营有限公司</option>
                                    <option value="上海地铁第四运营有限公司">上海地铁第四运营有限公司</option>
                                    <option value="上海轨道交通维护保障中心">上海轨道交通维护保障中心</option>
                                    <option value="上海轨道交通维护保障中心车辆分公司">上海轨道交通维护保障中心车辆分公司</option>
                                    <option value="上海轨道交通维护保障中心供电分公司">上海轨道交通维护保障中心供电分公司</option>
                                    <option value="上海轨道交通维护保障中心工务分公司">上海轨道交通维护保障中心工务分公司</option>
                                    <option value="上海轨道交通维护保障中心通号分公司">上海轨道交通维护保障中心通号分公司</option>
                                    <option value="上海轨道交通维护保障中心物流和后勤分公司">上海轨道交通维护保障中心物流和后勤分公司</option>
                                    <option value="上海轨道交通培训中心">上海轨道交通培训中心</option>
                                    <option value="上海轨道交通技术研究中心">上海轨道交通技术研究中心</option>  
                                    <option value="上海轨道交通资金管理中心">上海轨道交通资金管理中心</option>
                                    <option value="上海轨道交通信息管理中心">上海轨道交通信息管理中心</option>
                                    <option value="上海轨道交通资产管理中心">上海轨道交通资产管理中心</option>
                                    <option value="上海轨道交通建设管理中心">上海轨道交通建设管理中心</option> 
                                    <option value="上海市隧道工程轨道交通设计研究院">上海市隧道工程轨道交通设计研究院</option>  
                                    <option value="上海地铁运营人力资源有限公司">上海地铁运营人力资源有限公司</option>
                                </select>
                                </td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">目前职务</td>
                                <td><input type="text" id="position" name="position" maxlength="20" class="input_large" /></td>
                               <td class="t_r lableTd">技术等级</td>
                                <td>
                                  <select name="technicalGrade" id="technicalGrade" class="input_large">
                                    <option value="">---请选择---</option>   
                                    <option value="工人">工人</option>                                    
                                    <option value="技术人员">技术人员</option>                                    
                                    <option value="高级工">高级工</option>
                                    <option value="干部">干部</option>                                                               
                                </select>
                                </td> 
                              </tr>
                              <tr>
                                <td class="t_r lableTd">最高职称</td>
                                <td>
                                <select name="jobTitles" id="jobTitles" class="input_large">
                                    <option value="">---请选择---</option>   
                                    <option value="初级职称">初级职称</option>                                    
                                    <option value="中级职称">中级职称</option>
                                    <option value="高级职称">高级职称</option>                                    
                                    <option value="副高职称">副高职称</option>
                                    <option value="正高职称">正高职称</option>                                                                 
                                </select>
                                </td>
                                
                                <td class="t_r lableTd">技术专业</td>
                                <td>
                                <select name="technicalMajor" id="technicalMajor" class="input_large">
                                    <option value="">---请选择---</option>   
                                    <option value="经济师">经济师</option>                                    
                                    <option value="政工师">政工师</option>
                                    <option value="会计师">会计师</option>                                    
                                    <option value="工程师">工程师</option>
                                </select>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">单位邮编</td>
                                <td><input type="text" id="companyZip" name="companyZip" maxlength="6" class="input_large" /></td>
                                <td class="t_r lableTd">单位电话</td>
                                <td><input type="text" id="companyPhone" name="companyPhone" maxlength="20" class="input_large" /></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">支内或农口</td>
                                <td><select name="hukou" id="hukou" class="input_large">
                                  <option value="">---请选择---</option>
                                  <option value="支内">支内</option>
                                  <option value="农口">农口</option>
                                </select></td>
                                <td class="t_r lableTd">在职或退休</td>
                                <td><select name="isRetire" id="isRetire" class="input_large">
                                  <option value="">---请选择---</option>
                                  <option value="在职">在职</option>
                                  <option value="退休">退休</option>
                                </select></td>
                              </tr>
                              <tr>
                              <td class="t_r lableTd">工作单位地址</td>
                                <td colspan="3"><input type="text" id="companyAddress" name="companyAddress" maxlength="50" class="input_xlarge" style="width:64%"/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd">备注说明</td>
                                <td colspan="3"><textarea id="remarks" name="remarks" rows="5"></textarea></td>
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
