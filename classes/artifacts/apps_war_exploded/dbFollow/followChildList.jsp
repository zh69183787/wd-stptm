<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>
部门督办跟踪列表
</title>
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
        	$('#planSubmitTimeStart').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'planSubmitTimeStart'//仅作为“清除”按钮的判断条件						
			});
						
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			$('#planSubmitTimeEnd').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'planSubmitTimeEnd'//仅作为“清除”按钮的判断条件
			});	
			
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="planSubmitTimeStart") $("#planSubmitTimeStart").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="planSubmitTimeEnd") $("#planSubmitTimeEnd").val("");     
            });
        });
        
        $(function(){
        	var h_followType=$("#h_followType");
		    var followType=$("#followType");		  
		    for(var i=1;i<followType.children("option").length;i++){
		      if(followType.children("option:eq("+i+")").val()==h_followType.val())
		      followType.children("option:eq("+i+")").attr("selected",true);
		    }	
        });
        
        function clearInput(){
          $("#dbName").val("");
          $("#planSubmitTimeStart").val("");
          $("#planSubmitTimeEnd").val("");
          $("#followType").children("option:eq(0)").attr("selected",true);
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
      

    </script>



</head>

<body>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick=""></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">督办跟踪</a></li> 
                	<li>
                		部门督办跟踪列表
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
        	<s:form action="findDbFollowChildByPage" id="form" namespace="/dbFollow"> 
        		<input type="hidden"  value="" name="number" id="pageNo"/>
        		<input type="hidden" name="id" value="<s:property value='#request.id'/>"> 
        		<input type="hidden" id="h_followType" value="<s:property value='#request.followType'/>"> 
        		<input type="hidden" name="oldDeptId" value="<s:property value='#request.oldDeptId'/>">     	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    
        	   <tr>
        	      <td class="t_r" style="white-space:nowrap;">督办流程名称</td>
        	     <td>
        	     <input type="text" id="dbName" name="dbName" class="input_large" value="<s:property value='#request.dbName'/>">
        	     </td> 
        	      
        	      <td class="t_r" style="white-space:nowrap;">跟踪类型</td>
        	      <td>
        	      <select name="followType" id="followType" class="input_large">
        	        <option value="">--请选择--</option>
        	        <option value="1">简单跟踪</option>
        	        <option value="2">按计划跟踪</option>
        	      </select>
        	      </td>
        	      
        	      <td class="t_r" style="white-space:nowrap;">计划提交时间</td>
        	      <td>
        	      <input readonly="readonly" name="planSubmitTimeStart" id="planSubmitTimeStart" type="text" value="<s:property value='#request.planSubmitTimeStart'/>" class="input_large"/>至
        	      <input readonly="readonly" name="planSubmitTimeEnd" id="planSubmitTimeEnd" type="text" value="<s:property value='#request.planSubmitTimeEnd'/>" class="input_large"/>
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
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                  <th colspan="30">部门督办跟踪列表</th>
                              </thead>    
                              <tbody>
                              <tr class="tit">
                                <td class="t_c" style="white-space:nowrap;width:3%;">序号</td>
                                <td class="t_c" style="white-space:nowrap;width:40%;">督办流程名称</td>
                                <td class="t_c" style="white-space:nowrap;width:8%;">跟踪类型</td>
                                <td class="t_c" style="white-space:nowrap;width:25%;">要求</td>
                                <td class="t_c" style="white-space:nowrap;width:10%;">计划提交时间</td>
                                <td class="t_c" style="white-space:nowrap;width:14%;">操作</td>
                              </tr>
                              
                               <s:iterator value="#request.page.result" status="st" id="list">
	 	                          <tr>
	 	                            <td class="t_c"><s:property value="#st.index+#request.page.start"/></td>
	 	                            <td ><s:property value="#list[1].dbName"/></td>
	 	                            <td class="t_c">
	 	                            	<s:if test="#list[1].followType==1">
	 	                            	简单跟踪
	 	                            	</s:if>
	 	                            	<s:elseif test="#list[1].followType==2">
	 	                            	按计划跟踪
	 	                            	</s:elseif>
	 	                            </td>
	 	                            <td ><s:property value="#list[0].require"/></td>
	 	                            <td class="t_c" ><s:property value="#list[0].planSubmitTime"/></td>
	  	                            <td >
	  	                            	<s:if test="#list[1].followType==1">
	  	                            	<a class="fl mr5" href="simpleFollow.action?followChildId=<s:property value="#list[0].id"/>" target="_blank" >上传附件</a>
	  	                            	</s:if>
	  	                            	<s:elseif test="#list[1].followType==2">
	  	                            	<a class="fl mr5" href="findFollowPlanList.action?followChildId=<s:property value="#list[0].id"/>&infoType=1" target="_blank" >跟踪计划</a> 
		  	                            	<s:if test="#list[0].followState==2">
		  	                            	<a class="fl mr5" href="findFollowPlanList.action?followChildId=<s:property value="#list[0].id"/>&infoType=2&followState=2" target="_blank" >查看</a>
	  	                            		</s:if>
	  	                            		<s:else>
	  	                            		<a class="fl mr5" href="findFollowPlanList.action?followChildId=<s:property value="#list[0].id"/>&infoType=2" target="_blank" >提交成果</a>
	  	                            		</s:else>
	  	                            	</s:elseif>
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


