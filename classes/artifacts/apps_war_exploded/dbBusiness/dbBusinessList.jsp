<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>
督办业务列表
</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/show.js"></script>
<script src="js/processComm.js"></script>
<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	

<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<style type="text/css">
.ui-datepicker-title span {display:inline;}
button.ui-datepicker-current { display: none; }
</style>		
	<script type="text/javascript">
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
        
            $(function(){
			   $('#creatTimeStart').datepicker({
			    //inline: true        
			    "changeYear":true,
			    "showButtonPanel":true, 
			    "closeText":'清除', 
			    "currentText":'creatTimeStart',//仅作为“清除”按钮的判断条件      
			    "yearRange":'1900:+nn' 
			   });
			      
			   //$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			   $('#creatTimeEnd').datepicker({
			    //inline: true
			    "changeYear":true,
			    "showButtonPanel":true, 
			    "closeText":'清除', 
			    "currentText":'creatTimeEnd',//仅作为“清除”按钮的判断条件
			    "yearRange":'1900:+nn' 
			   }); 
			   
			   $('#returnTimeStart').datepicker({
			    //inline: true
			    "changeYear":true,
			    "showButtonPanel":true, 
			    "closeText":'清除', 
			    "currentText":'modifyTimeStart',//仅作为“清除”按钮的判断条件
			    "yearRange":'1900:+nn' 
			   }); 
			   $('#returnTimeEnd').datepicker({
			    //inline: true
			    "changeYear":true,
			    "showButtonPanel":true, 
			    "closeText":'清除', 
			    "currentText":'modifyTimeEnd',//仅作为“清除”按钮的判断条件
			    "yearRange":'1900:+nn' 
			   }); 
			   
   			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="creatTimeStart") $("#creatTimeStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="creatTimeEnd") $("#creatTimeEnd").val("");    
              
              if($(this).parent("div").children("button:eq(0)").text()=="returnTimeStart") $("#returnTimeStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="returnTimeEnd") $("#returnTimeEnd").val("");         
              
            });
  }); 
        
          $(function(){
        	var h_isEnd=$("#h_isEnd");
		    var isEnd=$("#isEnd");		  
		    for(var i=1;i<isEnd.children("option").length;i++){
		      if(isEnd.children("option:eq("+i+")").val()==h_isEnd.val())
		      isEnd.children("option:eq("+i+")").attr("selected",true);
		    }	
		    
		    var h_isFollow=$("#h_isFollow");
		    var isFollow=$("#isFollow");		  
		    for(var i=1;i<isFollow.children("option").length;i++){
		      if(isFollow.children("option:eq("+i+")").val()==h_isFollow.val())
		      isFollow.children("option:eq("+i+")").attr("selected",true);
		    }	
		    
		    var h_orderValue=$("#h_orderValue");
		    var orderValue=$("#orderValue");		  
		    for(var i=1;i<orderValue.children("option").length;i++){
		      if(orderValue.children("option:eq("+i+")").val()==h_orderValue.val())
		      orderValue.children("option:eq("+i+")").attr("selected",true);
		    }	
        });
        
			function clearInput(){
          $("#creatememo").val("");
          $("#deptName").val("");  
          
          $("#userName").val("");
          $("#manageTime").val("");
          $("#beyondDay").val("");
          $("#isEnd").children("option:eq(0)").attr("selected",true);
          $("#isFollow").children("option:eq(0)").attr("selected",true);
          
          $("#creatTimeStart").val("");  
          $("#creatTimeEnd").val("");    
          $("#returnTimeStart").val("");
          $("#returnTimeEnd").val("");      
                                           
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
       
    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">督办业务</a></li> 
                	<li>
                		督办业务列表
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
          <!--Ctrl End-->
        <!--Tabs_2-->
        
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
        	<div class="query p8" id="search">
        	<s:form action="findDbBusinessByPage" id="form" method="post" namespace="/dbBusiness"> 
        	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
        	<input type="hidden" id="h_isEnd" value="<s:property value='#request.isEnd'/>">
        	<input type="hidden" id="h_isFollow" value="<s:property value='#request.isFollow'/>">
        	<input type="hidden" id="h_orderValue" value="<s:property value='#request.orderValue'/>">
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    
        	   <tr>
        	      <td class="t_r" style="white-space:nowrap;">督办事项</td>
        	     <td>
        	     <input type="text" id="creatememo" name="creatememo" class="input_large" value="<s:property value='#request.creatememo'/>">
        	     </td> 
        	      
        	      <td class="t_r">立项时间</td>
        	      <td >
        	      <input readonly="readonly" name="creatTimeStart" id="creatTimeStart" type="text" value="<s:property value='#request.creatTimeStart'/>" class="input_small"/>到
        	     <input readonly="readonly" name="creatTimeEnd" id="creatTimeEnd" type="text" value="<s:property value='#request.creatTimeEnd'/>" class="input_small"/>
        	       </td>
        	      
        	     <td class="t_r" style="white-space:nowrap;">主办部门</td>
        	     <td>
        	     <input type="text" id="deptName" name="deptName" class="input_large" value="<s:property value='#request.deptName'/>">
        	     </td> 
      	      </tr>
      	       <tr>
        	      <td class="t_r" style="white-space:nowrap;">发起人</td>
        	     <td>
        	     <input type="text" id="userName" name="userName" class="input_large" value="<s:property value='#request.userName'/>">
        	     </td> 
        	      
        	     <td class="t_r">要求回复时间</td>
        	      <td >
        	      <input readonly="readonly" name="returnTimeStart" id="returnTimeStart" type="text" value="<s:property value='#request.returnTimeStart'/>" class="input_small"/>到
        	      <input readonly="readonly" name="returnTimeEnd" id="returnTimeEnd" type="text" value="<s:property value='#request.returnTimeEnd'/>" class="input_small"/>
        	     </td>
        	     <!--
        	     <td class="t_r" style="white-space:nowrap;">办理时间</td>
        	     <td>
        	     <input type="text" id="manageTime" name="manageTime" class="input_large" value="<s:property value='#request.manageTime'/>">
        	     </td> 
      	      --></tr>
      	      <tr>
      	      <!--
        	      <td class="t_r" style="white-space:nowrap;">超期天数</td>
        	     <td>
        	     <input type="text" id="beyondDay" name="beyondDay" class="input_large" value="<s:property value='#request.beyondDay'/>">
        	     </td> 
        	      --><td class="t_r" style="white-space:nowrap;">是否办结</td>
        	      <td>
        	      <select name="isEnd" id="isEnd" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="1">未办结</option>
        	        <option value="2">已办结</option>
        	      </select>
        	      </td>
        	      
        	      <td class="t_r" style="white-space:nowrap;">是否跟踪</td>
        	      <td>
        	      <select name="isFollow" id="isFollow" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="1">简单跟踪</option>
        	        <option value="2">计划跟踪</option>
        	      </select>
        	      </td>
      	      </tr>
      	      <tr>
      	      	<td class="t_r" style="white-space:nowrap;">排序字段</td>
        	      <td>
        	      <select name="orderValue" id="orderValue" class="input_large">
        	        <option value="1">按立项时间升序</option>
        	        <option value="2" selected="selected">按立项时间倒序</option>
        	        <option value="3">按要求回复时间升序</option>
        	        <option value="4">按要求回复时间倒序</option>
        	        <option value="5">按办理时间升序</option>
        	        <option value="6">按办理时间倒序</option>
        	        <option value="7">按超期天数升序</option>
        	        <option value="8">按超期天数倒序</option>
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
      </div>


      
        <!--Filter End-->
        <!--Table-->
         <s:set name="r" value="#request.result"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1">
        					 <thead>
                                  <th colspan="30">督办业务列表</th>
                              </thead>  
                              <tbody>
                              <tr class="tit">
                                <!-- <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>-->
                                <td class="t_c" width="3%">序号</td>
                                <td class="t_c" width="21%">督办事项</td>
                                <td class="t_c" width="10%">立项时间</td>
                                <td class="t_c" width="11%">主办部门</td>
                                <td class="t_c" width="8%">要求回复时间</td>
                                <td class="t_c" width="6%">发起人</td>
                                <td class="t_c" width="5%">办理时间</td>
                                <td class="t_c" width="5%">超期天数</td>
                                <td class="t_c" width="7%">是否办结</td>
                                <td class="t_c" width="7%">是否跟踪</td>
                                <td class="t_c" width="18%">操作</td>
                                </tr>
                              <s:iterator value="#r.list" id="items" status="s">
                              <tr>
                              <s:if test="#items[11]>0 && #items[8]==null">
                              	<td style="color: red; " class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<td style="color: red; " class="t_c"><s:property value="#items[0]" /></td>
                              	<td style="color: red; " class="t_c"><s:property value="#items[1]"/></td>
                               	<td style="color: red; " class="t_c"><s:property value="#items[2]" /></td>
                                <td style="color: red; " class="t_c"><s:property value="#items[3]"/></td>
                                <td style="color: red; " class="t_c"><s:property value="#items[4]" /></td>
                                <td style="color: red; " class="t_c"><s:property value="#items[10]" /></td>
                                <td style="color: red; " class="t_c"><s:property value="#items[11]" /></td>
                                <td style="color: red; " class="t_c"><s:property value="#items[6]" /></td>
                                <td style="color: red; " class="t_c"><s:property value="#items[7]" /></td>
                                <td class="t_c">
	  	                            	<a class="fl mr5" href="http://10.1.40.40:8088/findDbById.action?id=<s:property value="#items[9]"/>&type=print&current=1" target="_blank" >表单</a> 
	  	                            	<a class="fl mr5" href="http://10.1.40.95/sLogin/workflow/TaskStatus.aspx?TaskID=<s:property value="#items[12]"/>" target="_blank" >监控</a> 
	  	                            	<s:if test="#items[7]=='不跟踪'">
	  	                            	<a class="fl mr5" href="<%=path %>/dbFollow/toFollowAdd.action?dbId=<s:property value='#items[9]'/>&followType=1 " target="_blank" >简单跟踪</a> 
	  	                            	<a class="fl mr5" href="<%=path %>/dbFollow/toFollowAdd.action?dbId=<s:property value='#items[9]'/>&followType=2 " target="_blank" >按计划跟踪</a>
	  	                            	</s:if>
	  	                        </td>
                               </s:if>
                               <s:else>
                              	<td class="t_c"><s:property value="#s.index+1+(#r.pageInfo.currentPage-1)*10"/></td>
                              	<td class="t_c"><s:property value="#items[0]" /></td>
                              	<td class="t_c"><s:property value="#items[1]"/></td>
                               	<td class="t_c"><s:property value="#items[2]" /></td>
                                <td class="t_c"><s:property value="#items[3]"/></td>
                                <td class="t_c"><s:property value="#items[4]" /></td>
                                <td class="t_c"><s:property value="#items[10]" /></td>
                                <td class="t_c"><s:property value="#items[11]" /></td>
                                <td class="t_c"><s:property value="#items[6]" /></td>
                                <td class="t_c"><s:property value="#items[7]" /></td>
                                <td class="t_c">
	  	                            	<a class="fl mr5" href="http://10.1.40.40:8088/findDbById.action?id=<s:property value="#items[9]"/>&type=print&current=1" target="_blank" >表单</a> 
	  	                            	<a class="fl mr5" href="http://10.1.40.95/sLogin/workflow/TaskStatus.aspx?TaskID=<s:property value="#items[12]"/>" target="_blank" >监控</a> 
	  	                            	<s:if test="#items[7]=='不跟踪'">
	  	                            	<a class="fl mr5" href="<%=path %>/dbFollow/toFollowAdd.action?dbId=<s:property value='#items[9]'/>&followType=1 " target="_blank" >简单跟踪</a> 
	  	                            	<a class="fl mr5" href="<%=path %>/dbFollow/toFollowAdd.action?dbId=<s:property value='#items[9]'/>&followType=2 " target="_blank" >按计划跟踪</a>
	  	                            	</s:if> 
	  	                        </td>
                               </s:else>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#r.pageInfo.currentPage"/>/<s:property value="#r.pageInfo.totalPage"/>
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.totalPage'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#r.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
	                             	</s:if>
	                             	<s:else>
	                             		<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
	                             	</s:else>
	                             
                                
                            </ul>
                        </div>
                                </td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>