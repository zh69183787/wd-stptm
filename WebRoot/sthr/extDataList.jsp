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
<title>人事扩展信息数据项查询</title>
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
			
			$("[id=dd]").each(
			  function(index){
			    var dd = $(this).text();
			    if(dd.substring(0,3)=='下拉框')	{
			      dd="下拉框";
			      $(this).html(dd);
			    }	
			    if(dd.substring(0,3)=='多选框')	{
			      dd="多选框";
			      $(this).html(dd);
			    }		    
			  }
			);
			
			//双击修改	
			$("tbody").find("input[type=hidden]").each(function(i,n){
			  var value = $(n).attr("value");
			  var tr = $(n).parent("td").parent("tr");
			  var td0 =$(tr).children("td:eq(0)");
              var td2 =$(tr).children("td:eq(2)");
              var td4 =$(tr).children("td:eq(4)");
              var td5 =$(tr).children("td:eq(5)");
              var html5 = td5.html();
              //$(tr).unbind("dblclick");
	          $(tr).dblclick(tddblclick);          
	          function tddblclick(){
	            $(tr).unbind("dblclick");
	            var text0 = td0.text();
	            var text2 = td2.text();
	            var text4 = td4.text().replace(/(^\s*)|(\s*$)/g,'');
	            
	            td0.html("");
	            td2.html("");
	            td4.html("");
	            td5.html("");
	            
	            var input0 = $("<input type=\"text\" size=\"1\" maxlength=\"3\">");
	            var input2 = $("<input type=\"text\" size=\"20\" maxlength=\"20\">");
	            if(text4=="是")  {var input4=$("<input type=\"radio\" name=\"if_confirm\" value=\"1\" checked=\"checked\">是<input type=\"radio\" name=\"if_confirm\" value=\"0\">否<input type=\"hidden\">");}
	            else {var input4=$("<input type=\"radio\" name=\"if_confirm\" value=\"1\" >是<input type=\"radio\" name=\"if_confirm\" value=\"0\" checked>否<input type=\"hidden\">");}
	            var input5 = $("<a id='saveEdit' class='fl mr5' href='#' >保存 </a><a id='cancelEdit' class='fl mr5' href='#'>取消 </a>" );
	            
	            input0.attr("value", text0);	            
	            input2.attr("value", text2);	            
	            
	            td0.append(input0);
	            td2.append(input2);
	            td4.append(input4);
	            td5.append(input5);
	            
	            $("#saveEdit").click(function(){
	              var inputtext0 = input0.val().replace(/(^\s*)|(\s*$)/g,'');
	              if(inputtext0=="") {alert("序号不能为空");input0.focus();return false;}
	              if(!inputtext0.match(/^[0-9]*$/)) {alert("序号仅能填写正整数");input0.focus();return false;}
	              var inputtext2 = input2.val().replace(/(^\s*)|(\s*$)/g,'');
	              if(inputtext2=="") {alert("数据项名称不能为空");input2.focus();return false;}
	              var inputtext4 = $('input:radio:checked').val();
	              var updatePerson = $("#updatePerson").val();
	              
	              td0.html(inputtext0);
	              td2.html(inputtext2);
	              td4.html(inputtext4.replace("1","是").replace("0","否"));
	              td5.html(html5);
	              
	              $.ajax({
				url: 'updateHrEtDList.action',
				type: 'POST',
				data:{
					"hretdId" : value,
					"sortingOrder" : inputtext0,
					"itemName" : inputtext2,
					"isLShow" : inputtext4,					
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
	         td2.html(text2);
	         td4.html(text4);	         
	         td5.html(html5);
	         $(tr).dblclick(tddblclick);
	       });
	          }  
              
			});			
        });
        
        
        
        //清除查询条件
        function clearData(){
        	$("#itemName").attr("value","");
        	$("#sortingOrder").attr("value","");
        	document.getElementById("inputType").options[0].selected=true;
        	
        	document.getElementById("test_radio_3").checked = true;
        }
        
        //删除数据
        function deleteData(id,id2){
        	if(confirm("确定删除?")){
        		window.location.href="deleteHrEtDById.action?hretdId="+id+"&hretId="+id2;
        	}
        }
        
        //验证表单
        function checkForm(){
        	if(!$("#sortingOrder").val().match(/^[0-9]*$/)){
        		alert("排列顺序必须是数字");
        		$("#sortingOrder").focus();
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
        function addExtData(hretId){
        	newWindow = window.open("showAddDatePage.action?hretId="+hretId);
        }
		
		//打开修改页面
		function showEditPage(hretId){
			newWindow = window.open("showEditPage.action?hretdId="+hretId);		
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
                	<li><a href="#">扩展信息类别</a></li>
                	<li class="fin">“<s:property value="#request.typeName"/>”数据结构维护</li>
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
   		<s:form action="findHrEtDByHrEtId" name="HrEtD" namespace="/sthr" id="sForm">
   		  <input type="hidden"  name="hretId" value="<s:property value='hretId'/>">
	      <input type="hidden"  name="typeName" value="<s:property value='typeName'/>">
   		  <input type="submit" value="检 索" onclick="return checkForm();" style="display:none"/>
   		</s:form>
        <!--Ctrl End-->
        <!--Tabs_2-->
        <!-- 
        <div class="tabs_2">
        	<ul>
            	<li class="selected"><a href="#"><span><s:property value="#request.typeName"/>扩展数据结构</span></a></li>
            	<li><a href="#"><span>待定名称</span></a></li>
            	<li><a href="#"><span>待定名称</span></a></li>
            	<li><a href="#"><span>待定名称</span></a></li>
            </ul>
        </div>
          -->
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
      <!--  
        	<div class="query p8">
	        	<s:form action="findHrEtDByHrEtId" name="HrEtD" namespace="/HrEt" id="sForm">
	        		<input type="hidden"  name="hretId" value="<s:property value='hretId'/>">
	        		<input type="hidden"  name="typeName" value="<s:property value='typeName'/>">
	        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	    <tr>
	        	      <td class="t_r">数据项名称</td>
	        	      <td>
	       	          <input type="text" name="itemName" id="itemName" class="input_large" value="<s:property value='itemName'/>" maxlength="10"></td>
	        	      <td class="t_r">录入方式</td>
	        	      <td>
		        	      <select name="inputType" id="inputType" class="input_large">
		        	      <s:if test="inputType=='普通文本'">
		        	      	 <option value="" id="option1">---全部---</option>
		        	      	 <option selected="selected">普通文本</option>
		        	      	 <option>长文本</option>
		        	         <option>数字</option>
		        	         <option>日历</option>
		        	      </s:if>
		        	      <s:elseif test="inputType=='长文本'">
		        	      	 <option value="" id="option1">---全部---</option>
		        	      	 <option>普通文本</option>
		        	      	 <option selected="selected">长文本</option>
		        	         <option>数字</option>
		        	         <option>日历</option>
		        	      </s:elseif>
		        	      <s:elseif test="inputType=='数字'">
		        	      	 <option value="" id="option1">---全部---</option>
		        	      	 <option>普通文本</option>
		        	      	 <option>长文本</option>
		        	         <option selected="selected">数字</option>
		        	         <option>日历</option>
		        	      </s:elseif>
		        	      <s:elseif test="inputType=='日历'">
		        	      	 <option value="" id="option1">---全部---</option>
		        	      	 <option>普通文本</option>
		        	      	 <option>长文本</option>
		        	         <option>数字</option>
		        	         <option selected="selected">日历</option>
		        	      </s:elseif>
		        	      <s:else>
		        	      	 <option value="" selected="selected" id="option1">---全部---</option>
		        	      	 <option>普通文本</option>
		        	      	 <option>长文本</option>
		        	         <option>数字</option>
		        	         <option>日历</option>
		        	      </s:else>
		                  </select>
	                  </td>
	      	      </tr>
	        	    <tr>
	        	      <td class="t_r">排列顺序</td>
	        	      <td><input type="text" name="sortingOrder" id="sortingOrder" class="input_large" value="<s:property value='sortingOrder'/>" maxlength="3"></td>
	        	      <td class="t_r">是否显示</td>
	        	      <td>
	        	      	  <s:if test="isLShow==1">
	        	      	  	<input type="radio" id="test_radio_1" name="isLShow" value="1" checked="checked"/>是
	  					  	<input type="radio" id="test_radio_2" name="isLShow" value="0"/>否
	  					  	<input type="radio" id="test_radio_3" name="isLShow" value=""/>全部
	        	      	  </s:if>
	        	      	  <s:elseif test="isLShow==''">
	        	      	  	<input type="radio" id="test_radio_1" name="isLShow" value="1"/>是
	  					  	<input type="radio" id="test_radio_2" name="isLShow" value="0"/>否
	  					  	<input type="radio" id="test_radio_3" name="isLShow" value="" checked="checked"/>全部
	        	      	  </s:elseif>
	        	      	  <s:elseif test="isLShow==0">
	        	      	  	<input type="radio" id="test_radio_1" name="isLShow" value="1"/>是
	  					  	<input type="radio" id="test_radio_2" name="isLShow" value="0" checked="checked"/>否
	  					  	<input type="radio" id="test_radio_3" name="isLShow" value=""/>全部
	        	      	  </s:elseif>
	        	      	  <s:else>
	        	      	  	<input type="radio" id="test_radio_1" name="isLShow" value="1"/>是
	  					  	<input type="radio" id="test_radio_2" name="isLShow" value="0"/>否
	  					  	<input type="radio" id="test_radio_3" name="isLShow" value="" checked="checked"/>全部
	        	      	  </s:else>
	        	      	  
					  </td>
	      	        </tr>
	        	    <tr>
	        	      <td colspan="4" class="t_c">
	                  	<input type="submit" value="检 索" onclick="return checkForm();"/>&nbsp;&nbsp;
						<input type="button"" value="取 消" onclick="clearData()"/>
					  </td>
	       	        </tr>
	      	    </table>
	      	    </s:form>
        	</div>
        	-->
        	
            <div class="fn">
              <input type="button"" name="button2" id="button2" value="新 增" class="new" onclick="addExtData('<s:property value='#request.hretId'/>')">
            </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                <th colspan="8">人事扩展数据结构详细</th>
                              </thead>
                              <tbody>
                              <tr class="tit">
                                
                                <td><a href="#">序号</a></td>
                                <td>类别</td>
                                <td>数据项名称</td>
                                <td>录入方式</td>
                                <!-- <td>排列顺序</td> -->
                                <td>是否有效</td>
                                <td>操作</td>
                                </tr>
                              <s:iterator value="#request.page.result" status="st">
                              	<tr>
	                                
	                                <td class="t_c"><s:property value="sortingOrder"/></td>
	                                <td class="t_c"><s:property value="typeName"/>
	                                <input type="hidden" value="<s:property value='hretdId'/>"/></td>
	                                <td class="t_c"><s:property value="itemName"/></td>
	                                <td class="t_c" id="dd"><s:property value="inputType"/></td>
	                               <!-- <td class="t_c">001</td> --> 
	                                <td class="t_c">
	                                	<s:if test="isLShow==1">是</s:if>
	                                	<s:else>否</s:else>
	                                </td>
	                                <td class="t_c">
	                                	<a class="fl mr5" href="findHrEtDById.action?hretdId=<s:property value='hretdId'/>" target="_blank">查看</a> 
	                                	<a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='hretdId'/>','<s:property value='hretId'/>')">删除</a> 
	                                	<a class="fl mr5" href="#" onclick="showEditPage('<s:property value='hretdId'/>')">修改</a>
	                                </td>
                                </tr>
                              </s:iterator>
                              
                              </tbody>
                              <s:if test="#request.page.totalSize!=0">
                              <tr class="tfoot">
                                <td colspan="8">
                        <div class="clearfix">
                            <span class="fl">共<s:property value="#request.page.totalSize"/>条记录，当前显示
	                            <s:if test="#request.page.totalSize<1">0条</s:if>
	                            <s:else>1-<s:property value="#request.page.totalSize"/>条</s:else>
                            </span>	
                            <ul class="fr clearfix pager">
                            <!-- 
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
                                 -->
                            </ul>
                        </div>
                                </td>
                              </tr>
                              
                              </s:if><s:else>
   	        <tr class="tfoot"><td colspan="8"><div class="clearfix">无相关数据</div></td>
   	        </tr>
   	        </s:else>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>
