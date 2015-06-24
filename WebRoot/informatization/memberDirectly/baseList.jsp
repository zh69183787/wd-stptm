<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>主题活动信息</title>
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
        });
        
        function clearInput(){
          $("#unit").val("");
        }
        function check(){
        
		  return true;
        }
        
        //每1秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				var number = $("#number").val();
				$("#pageNo").val(number);
				$("#form").submit();				
				clearInterval(refresh);
			}
		}
		
        function deleteData(id){
	  if(confirm("确定删除?")){
		$.ajax({
			url: "<%=basePath%>/memberDirectly/deleteData.action",
			dataType: "json",
			data: {
				"id" : id
			},
			success: function( data ) {
				window.location.reload();
			}
		});     		
	  }
  }
        
        function dataAdd(){
          newWindow=window.open('toAdd.action');
        }
        
        function editData(id){
          newWindow=window.open("findGroupMemberDirectlyById.action?id="+id+"&types=edit");
        }
        //跳转到制定页
        function goPage(pageNo,type){
			//type=0,直接跳转到制定页
	       if(type=="0"){
	       		
	       		var pageCount = $("#totalPageCount").val();
	       		var number = $("#number").val();
	       		if(!number.match(/^[0-9]*$/)){
	       			alert("请输入数字");
	       			$("#number").val("");
	       			$("#number").focus();
	       			return ;
	       		}
	       		if(parseInt(number)>parseInt(pageCount)){
	       			$("#number").val(pageCount);
	       			$("#pageNo").val(pageCount);
	       		}else{
	       			$("#pageNo").val(number);
	       		}
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#pageNo").val(parseInt($("#number").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	            //alert($("#number").val());	       		
	       		$("#pageNo").val(parseInt($("#number").val())+1);
	       		//alert($("#pageNo").val());
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#pageNo").val(pageNo);
	       }
       	   $("#form").submit();

        }
$(function(){
	 $("#imgLeft").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 ); 
	 
	 $("#unit").val($("#h_unit").val());
});        
    </script>



</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="imgLeft" src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">团委信息化</a></li>                	
                	<li class="fin">主题活动信息</li>
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
        <div class="tabs_2">
        	<ul>
           		<li><a href="<%=basePath %>/member/findGroupMemberByPage.action"><span>青年信息</span></a></li>
           		<li><a href="<%=basePath %>/leader/findGroupLeaderByPage.action"><span>团干部信息</span></a></li>
           		<li class="selected"><a href="<%=basePath %>/memberDirectly/findGroupMemberDirectlyByPage.action"><span>主题活动</span></a></li>
           		<li><a href="<%=basePath %>/newMedia/findGroupNewMediaByPage.action"><span>团组织网站建设情况</span></a></li>
           		<li><a href="<%=basePath %>/work/findGroupWorkByPage.action"><span>其他新媒体开展工作</span></a></li>
           		<li><a href="<%=basePath %>/communication/findByPage.action"><span>拓宽沟通和响应渠道</span></a></li>
           		<li><a href="<%=basePath %>/active/findByPage.action"><span>新媒体活动</span></a></li>
           		<li><a href="<%=basePath %>/wibo/findByPage.action"><span>微博开设情况</span></a></li>
            </ul>
        </div>
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
        	<div class="query p8">
        	<s:form action="findGroupMemberDirectlyByPage" id="form" name="memberDirectly" namespace="/memberDirectly">        	
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r">单位</td>
        	      <td>
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
                                </select>
                                <input type="hidden" id="h_unit" value="<s:property value='avo.unit'/>">
       	          
       	          </td></tr>
       	          <!--  
        	      <td class="t_r">共青团员男</td>
        	      <td><input type="text" name="groupMale" id="groupMale" class="input_large" value=""></td>
      	         
      	          <td class="t_r">更新日期</td>
        	      <td >
        	      <input readonly="readonly" name="updateDate" id="updateDate" type="text" value="" class="input_small"/>
        	      </td>  
      	           
        	      
        	   </tr>
        	   <tr>
        	      <!-- 
        	      <input type="date" class="input_large" id="birthday" name="birthday" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:date format='yyyy-MM-dd' name='hrBInfoVO.birthday'/>"/>
        	       -->
        	      
        	      <!-- 
        	      <td class="t_r">目前职务</td>
        	      <td>
       	          <input type="text" name="position" id="position" class="input_large" value="<s:property value="hrBInfoVO.position"/>"></td>
        	       -->
        	       <!-- 
        	     <td class="t_r">35岁以下青年（含35岁）男</td>
        	      <td>
        	      	<input type="text" name="under_35YouthMale" id="under_35YouthMale" class="input_large" value="<s:property value="avo.under_35YouthMale"/>">
      	          
        	     </td>      	       
        	     
        	     <td class="t_r">共青团员女</td>
        	     <td><input type="text" name="groupFemMale" id="groupFemMale" class="input_large" value="<s:property value="avo.groupFemMale"/>">
      	         </td> 
        	      
      	          <td class="t_r">28岁以下未入团青年（含28岁）</td>
        	      <td colspan="4"><input type="text" name="under_28YouthNotGroup" id="under_28YouthNotGroup" class="input_large" value="<s:property value="avo.under_28YouthNotGroup"/>">
      	           </td>
      	      </tr>
      	      <tr>
      	        <td class="t_r">35岁以下青年（含35岁）女</td>
                                <td><input type="text" name="under_35YouthFemale" id="under_35YouthFemale" class="input_large" value="<s:property value="avo.under_35YouthFemale"/>">
      	           				 </td>
                                
      	        <td class="t_r">28岁党员（含28岁）</td>
      	         <td><input type="text" name="member28" id="member28" class="input_large" value="<s:property value="avo.member28"/>">
      	           	</td>
      	           	<td class="t_r">35岁党员（含35岁）</td>
      	         <td><input type="text" name="member35" id="member35" class="input_large" value="<s:property value="avo.member35"/>">
      	           	</td>
      	      </tr>-->
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" onclick="return check();"/>
      &nbsp;&nbsp;
<input type="button" value="重置" onclick="clearInput()"/> &nbsp;&nbsp;

</td>
       	        </tr>
       	        
      	    </table>
      	    </s:form>
        	</div>
            <div class="fn">
	            <table width="100%">
		            <tr>
			            <td>
			              <input type="button" name="button2" id="button2" value="新 增" class="new" onclick="dataAdd()">
			            </td>
			            <td style="text-align: right" type="hidden">
			             </td>
		            </tr>
	            </table>
            </div>
      </div>
      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="18">(各直属单位团组织)主题活动信息</th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td>序号</td>
                                <td>单位</td> 
                                <td>活动类型</td>
                                <td>活动主题或内容</td>
                                <td >时间</td>                                                           
                                <td>地点</td>
                                <td>负责人</td>
                                <td>参与人数</td>
                                <td>操作</td>
                              </tr>
                              
                               <s:iterator value="#request.page.result" status="st">
	 	                          <tr id="dataTR">
	 	                            <td class="t_c"><s:property value="#st.index+#request.page.start"/><input type="hidden" value="<s:property value='id'/>"/></td>
	 	                            <td class="t_c"><s:property value="unit"/></td>
	 	                           
	 	                            <td class="t_c"><s:property value="type"/></td>
	 	                            <td class="t_c"><s:property value="activeTitleOrContext"/></td>
	 	                            <td class="t_c"><s:property value="time"/></td>	                      	 	                            
	 	                            <td class="t_c"><s:property value="address"/></td>
	 	                            <td class="t_c" ><s:property value="head"/></td>
	 	                            <td class="t_c"><s:property value="participation"/></td> 
	 	                            
	 	                            <td class="t_c">
	  	                            <a class="fl mr5" href="findGroupMemberDirectlyById.action?id=<s:property value='id'/>&types=view" target="_blank" >查看</a> 
	  	                            <a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='id'/>')">删除</a>
	  	                            <a class="fl mr5" href="#" onclick="editData('<s:property value='id'/>')">修改</a>
	  	                            
	  	                            </td>
	                            </tr>
	                            </s:iterator>
	                            
	                           
                              </tbody>
                              <s:if test="#request.page.totalSize!=0">
                              <tr class="tfoot">
        	      <td colspan="13"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-
        	      <s:if test="#request.page.totalSize<(#request.page.start+#request.page.pageSize-1)">
        	        <s:property value="#request.page.totalSize"/>
        	      </s:if>
        	      <s:else>
        	        <s:property value="#request.page.start+#request.page.pageSize-1"/>
        	      </s:else>
        	      条</span>
        	        <ul class="fr clearfix pager">
        	          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
        	          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
        	            <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>"/>
        	            <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
      	            </li>
        	          
                       <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
                       	 <li><a href="javascript:void(0)">&gt;&gt;</a></li>
                       </s:if>
                       <s:else>
                        <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.totalPageCount'/>,3)">&gt;&gt;</a></li>
                       </s:else> 
                      <li>
                      	<s:if test="#request.page.currentPageNo==#request.page.totalPageCount">	
                      		<a href="javascript:void(0)">下一页</a>
                      	</s:if>
                      	<s:else>
                      		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,2)">下一页</a>
                      	</s:else>
                      </li>
                      <li>
                      	<s:if test="#request.page.currentPageNo==1">
                      		<a href="javascript:void(0)">上一页</a>
                      	</s:if>
                      	<s:else>
                      		<a href="javascript:void(0)" onclick="goPage(<s:property value='#request.page.currentPageNo'/>,1)">上一页</a>
                      	</s:else>
                      </li> 
                      <s:if test="#request.page.currentPageNo==1">
                      	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
                      </s:if>
                      <s:else>
                      	<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
                      </s:else>
      	          </ul>
      	        </div></td>
      	      </tr></s:if><s:else>
      	      <tr class="tfoot"><td colspan="13"><div class="clearfix">无相关数据</div></td>
   	          </tr>
      	      </s:else>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>


