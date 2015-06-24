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
if("on".equals(filterButton)){
	CrossIpLogin crossIpLogin = new CrossIpLogin();
	UserInfo userInfo = new UserInfo();
	crossIpLogin.setUserInfo(request,userInfo);
	loginName = userInfo.getLoginName();
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>人事扩展信息类别查询</title>
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
        <script type="text/javascript">
        
        //定义子窗口的名字
        var newWindow = ""; 
        
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
				
			
			//双击修改
			$("tbody").find("input[type=hidden]").each(function(i,n){ 
			  var value = $(n).attr("value");
			  var tr = $(n).parent("td").parent("tr");
              var td0 =$(tr).children("td:eq(0)");
              var td1 =$(tr).children("td:eq(1)");
              var td3 =$(tr).children("td:eq(3)");
              var html3 = td3.html();
              //$(tr).unbind("dblclick");
	          $(tr).dblclick(tddblclick);          
	          function tddblclick(){
	            $(tr).unbind("dblclick");
	            var text0 = td0.text();
	            var text1 = td1.text();
	            
	            td0.html("");
	            td1.html("");
	            td3.html("");
	            
	            var input0 = $("<input type=\"text\" size=\"1\" maxlength=\"3\">");
	            var input1 = $("<input type=\"text\" size=\"30\" maxlength=\"50\">");
	            var input3 = $("<a id='saveEdit' class='fl mr5' href='#' >保存 </a><a id='cancelEdit' class='fl mr5' href='#'>取消 </a>" );
	            
	            input0.attr("value", text0);
	            input1.attr("value", text1);
	            
	            td0.append(input0);
	            td1.append(input1);
	            td3.append(input3);
	        
	            $("#saveEdit").click(function(){
	              var inputtext0 = input0.val().replace(/(^\s*)|(\s*$)/g,'');
	              if(inputtext0=="") {alert("序号不能为空");input0.focus();return false;}
	              if(!inputtext0.match(/^[0-9]*$/)) {alert("序号仅能填写正整数");input0.focus();return false;}
	              var inputtext1 = input1.val().replace(/(^\s*)|(\s*$)/g,'');
	              if(inputtext1=="") {alert("名称不能为空");input1.focus();return false;}
	              var updatePerson = $("#updatePerson").val();
	              
	              td0.html(inputtext0);
	              td1.html(inputtext1);
	              td3.html(html3);
	              
	              $.ajax({
				url: 'sthr/updateHrEtList.action',
				type: 'POST',
				data:{
					"hretId" : value,
					"typeName" : inputtext1,
					"sortingOrder" : inputtext0,					
					"updatePerson" : updatePerson,
					"random" : Math.random()
				},						
				error: function(){
					alert('系统错误,请与管理员联系!');
				},
				success: function(){				
							$(tr).dblclick(tddblclick);
				}
	            });  
	          });
	          
	          $("#cancelEdit").click(function(){
	         td0.html(text0);
	         td1.html(text1);
	         td3.html(html3);
	         $(tr).dblclick(tddblclick);
	       });
	       }
			});		
        });
        
        
        //删除
        function deleteData(id){
        	if(confirm("确定删除?")){
        		window.location.href="deleteHrEtById.action?hretId="+id;
        	}
        }
        
        //清除查询条件
        function clearData(){
        	$("#typeName").val("");
        	$("#startDate").val("");
        	$("#endDate").val("");
        }
        
        //验证查询条件
        function checkForm(){
        	if($("#startDate").val()!=null && $("#startDate").val()!="" && !$("#startDate").val().match(/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/)){
        		alert("请使用控件输入合法的时间");
        		$("#startDate").focus();
        		return false;
        	}
        	if($("#endDate").val()!=null && $("#endDate").val()!="" && !$("#endDate").val().match(/^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$/)){
        		alert("请使用控件输入合法的时间");
        		$("#endDate").focus();
        		return false;
        	}
    		return true;    	
        }
        
        //每1秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				$("#sForm").submit();
				clearInterval(refresh);
			}
		}
		
		//打开新增页面
		function showAddPage(){
			newWindow = window.open('showAddTypePage.action');
		}
		
		//打开修改页面
		function showEditPage(hretId){
			newWindow = window.open("showHrEtEditPage.action?hretId="+hretId);		
		}
		
$(function(){
	 $("img").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 );
});        
        
    </script>



</head>

<body>
<input type="hidden" id="updatePerson" name="updatePerson" value="<%=loginName %>">
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">工会人事数据库</a></li>
                	<li class="fin">扩展信息类别</li>
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
        <!--
        <div class="tabs_2">
        	
        	<ul>
            	 <li class="selected"><a href="#"><span>人事扩展信息类别查询</span></a></li> 
            </ul>
            
        </div>
        -->
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
      <s:form action="findHrEtByPage" name="HrEt" namespace="/sthr" id="sForm">
      <input type="submit" value="检 索"  onclick="return checkForm();" style="display:none"/>
      </s:form>
      <!--  
        	<div class="query p8">
        	<s:form action="findHrEtByPage" name="HrEt" namespace="/HrEt" id="sForm">
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r">类别名称</td>
        	      <td>
       	          <input type="text" name="typeName" id="typeName" class="input_large" value="<s:property value='typeName'/>" maxlength="50"></td>
        	      <td class="t_r">最后更新时间</td>
        	      <td><input type="text"" id="startDate" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='#request.sDate' format='yyyy-MM-dd'/>"/>至
       	              <input type="text" id="endDate" name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date name='#request.eDate' format='yyyy-MM-dd'/>"/></td>
      	      	  </tr>
        	    <tr>
        	      <td colspan="4" class="t_c">
                  	<input type="submit" value="检 索"  onclick="return checkForm();"/>&nbsp;&nbsp;
					<input type="button" value="取 消" onclick="clearData()"/>
				  </td>
       	        </tr>
      	    </table>
      	    </s:form>
        	</div>
        	-->
            <div class="fn">
              <input type="submit" name="button2" id="button2" value="新 增" class="new" onclick="showAddPage();">
            </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                <th colspan="5">人事扩展数据结构</th>
                              </thead>
                              <tbody>
                              <tr class="tit">
                                
                                <td><a href="#">序号</a></td>
                                <td>名称</td>
                                <td>最后更新时间</td>
                                <td>操作</td>
                                </tr>
                                <s:iterator value="#request.page.result" status="st">
                                	<tr>
		                               	
		                                <td class="t_c"><s:property value="sortingOrder" /></td>
		                                <td class="t_c"><s:property value="typeName" /></td>
		                                <td class="t_c"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/>
		                                <input type="hidden" value="<s:property value='hretId'/>"/></td>
		                                <td class="t_c">
		                                	<a class="fl mr5" href="findHretByHretId.action?hretId=<s:property value='hretId' />" target="_blank">查看</a>
		                                	<a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='hretId' />')">删除</a>
		                                	<a class="fl mr5" href="#" onclick="showEditPage('<s:property value='hretId' />')">修改</a>
		                                	<a class="fl mr5" href="findHrEtDByHrEtId.action?hretId=<s:property value='hretId' />" target="_blank">数据结构</a>
		                                </td>
	                                </tr>
                                </s:iterator>
                              </tbody>
                              <s:if test="#request.page.totalSize!=0">
                              <tr class="tfoot">
                                <td colspan="5">
                        <div class="clearfix">
                            <span class="fl">共<s:property value="#request.page.totalSize"/>条记录，当前显示
	                            <s:if test="#request.page.totalSize<1">0条</s:if>
	                            <s:else>1-<s:property value="#request.page.totalSize"/>条</s:else>
                            </span>	
                            <!-- 
                            <ul class="fr clearfix pager">
                              <li>Pages:1/123
                                  <input type="number" id="number" name="number" min="0" max="999" step="1" class="input_tiny" />
                                  <input type="submit" name="button" id="button" value="Go">
                                </li>
                                <li><a href="#">&gt;&gt;</a></li>
                                <li><a href="#">100</a></li>
                                <li><a href="#">...</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">2</a></li>
                                <li class="selected"><a href="#">1</a></li>
                                <li><a href="#">&lt;&lt;</a></li>
                            </ul>
                             -->
                        </div>
                                </td>
                              </tr>
                              
                               </s:if><s:else>
   	        <tr class="tfoot"><td colspan="5"><div class="clearfix">无相关数据</div></td>
   	        </tr>
   	        </s:else>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>
