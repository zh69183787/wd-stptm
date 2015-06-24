<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>项目计划</title>
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
        
        var newWindow = ""; 
        String.prototype.replaceAll = function(s1,s2) { 
		    return this.replace(new RegExp(s1,"gm"),s2); 
		}
        
        
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
				
			$('#year').val('<s:property value="year"/>');			
        });
        
        
        
        function clearInput(){
          $("#applyCompanyId").val("");
          $("#planProjectName").val("");
          $("#year").val("");
        }
     
        
        //每1秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				var number = $("#number").val();
				$("#pageNo").val(number);
				$("#form").submit();	//当前页面重新提交			
				clearInterval(refresh);
			}
		}
		
        function deleteData(id){
      	  if(confirm("确定删除?")){
      		$.ajax({
				url: "deleteById!deleteById.action?random="+Math.random(),
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

        function toExport(){
        	newWindow=window.open('fileUpload.jsp');
        }
        
        function dataAdd(){
          	newWindow=window.open('toAdd.action');
        }
        
        function editData(id){
          newWindow=window.open("findProjectPlanById.action?id="+id+"&type=edit");
        }
        
        function showOrHide(obj){
          if($(obj).attr("class")=="selected"){
            $("#search").hide();
            $(obj).attr("class","");
          }else{
            $("#search").show();
            $(obj).attr("class","selected");
          }
          
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
      
      	//导出Excel
        function exportExcel(){
        	$("#ifExport").val("yes");
        	$("#form").submit();
        	$("#ifExport").val("");
        }


    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li class="fin">
						项目计划查询
                	</li>
                </ul>
            </div>
            <div class="fr lit_nav">
            	<ul>
                <!-- <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li> -->
                <li class="selected" onclick="showOrHide(this)"><a class="query" href="#">查询</a></li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
        	<div class="query p8" id="search">
        	<s:form action="findProjectPlanByPage" id="form" name="ProjectPlan" namespace="/projectPlan">        	
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	<input type="hidden" name="ifExport" id="ifExport"/>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    
      	      <tr>
      	      <td class="t_r">申报单位</td>
      	         <td>
                                	<select name="applyCompanyId" id="applyCompanyId">
										<option value="">---------请选择---------</option>
										<s:iterator value="#request.deptList" id="dept">
											<s:if test="#request.applyCompanyId==#dept[0]">
												<option value="<s:property value="#dept[0]"/>" selected="selected"><s:property value="#dept[1]"/></option>
											</s:if>
											<s:else>
												<option value="<s:property value="#dept[0]"/>"><s:property value="#dept[1]"/></option>
											</s:else>
										</s:iterator>
									</select>
      	         </td>
      	         <td class="t_r">计划项目名称</td>
      	         <td>
      	           <input type="text" id="planProjectName" name="planProjectName" class="input_large" value="<s:property value='planProjectName'/>">
      	         </td>
      	         <td class="t_r">计划年份</td>
      	         <td>
							<select name="year" id="year">
								<option value="">全部</option>
								<%int curYear = (Integer)request.getAttribute("curYear");
									for(int i=2015;i<curYear+5;i++){
								%>
								<option><%=i%></option>
								<%
									}
								%>								
							</select>      	         
      	         </td>      	         
      	      </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />
      &nbsp;
<input type="button" value="重 置" onclick="clearInput()"/> 
</td>
       	        </tr>
      	    </table>
      	    </s:form>
        	</div>
            <div class="fn">
			              <input type="button" name="button2" id="button2" value="新 增" class="new" onclick="dataAdd()">
			              <input type="button" value="导出" class="new" onclick="exportExcel();">
			              <input type="button" value="Excel导入" class="new" onclick="toExport();">
            </div>
      </div>
      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="30">项目计划表</th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                              	<td class="t_c" width="4%">序</td>
                                <td class="t_c" width="10%">申报单位</td>
                                <td class="t_c" width="10%">计划项目名称</td>
                                <td class="t_c" width="10%">项目属性</td>
                                <td class="t_c" width="10%">概算(万元)</td>
                                <td class="t_c" width="10%">立项依据</td>
                                <td class="t_c" width="10%">实施方案</td>
                                <td class="t_c" width="10%">项目目标</td>
                                <td class="t_c" width="10%">备注</td>
                                <td class="t_c" width="11%">操作</td>
                              </tr>
                              
                               <s:iterator value="#request.page.result" status="st">
	 	                          <tr id="dataTR">
	 	                            <td class="t_c"><s:property value="#st.index+#request.page.start"/></td>
	 	                            <td ><s:property value="applyCompany"/></td>
	 	                            <td ><s:property value="planProjectName"/></td>
	 	                            <td ><s:property value="property"/></td>
	 	                            <td class="t_r"><s:property value="estimate"/></td>
	 	                            <td ><div style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis"><s:property value="according"/></div></td>
	 	                            <td ><div style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis"><s:property value="plan"/></div></td>
	 	                            <td ><div style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis"><s:property value="target"/></div></td>
	 	                            <td ><div style="width:120px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis"><s:property value="remark"/></div></td>
	 	                            <td class="t_c">
	  	                            	<a style="display:inline" href="findProjectPlanById.action?id=<s:property value='id'/>&type=view" target="_blank" >查看</a> 
	  	                            	<a style="display:inline" href="javascript:void(0)" onclick="editData('<s:property value='id'/>')">修改</a>
	  	                            	<a style="display:inline" href="javascript:void(0)" onclick="deleteData('<s:property value='id'/>')">删除</a>
	  	                            </td>
	                            </tr>
	                            </s:iterator>
                              </tbody>
                              <s:if test="#request.page.totalSize!=0">
                              <tr class="tfoot">
        	      <td colspan="30"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-
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
      	      <tr class="tfoot"><td colspan="30"><div class="clearfix">无相关数据</div></td>
   	          </tr>
      	      </s:else>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>


