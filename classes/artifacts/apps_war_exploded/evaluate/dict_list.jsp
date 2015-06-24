<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>定量评价角度</title>
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
<!--<script src="../js/iepng.js"></script>-->
<script src="../js/jquery.formalize.js"></script>
<script src="../js/My97DatePicker/WdatePicker.js"></script>
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
        
        $(document).ready(function(){
			$("option[id^=choice]").each(function(){
				if($(this).val()==$("#hideGMD").val()){
					$(this).attr("selected",true);
				}
			});        
			   	
        })
        
        //定义子窗口的名字
        var newWindow = ""; 
        //每3秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				var flowId = $("option:selected").val();
        		$("#hideFlowId").val(flowId);
				$("#form").submit();
				clearInterval(refresh);
			}
		}
        
       //打开新增页面
		function showAddPage(){
			newWindow = window.open('showAddPageItem.action?flowId='+$("#hideFlowId").val()+"&flowId="+$("#hideFlowId").val());
		}
		
		//打开编辑页面
		function showEditPage(id){
			newWindow = window.open('showEditPageItem.action?id='+id);
		}
        
        
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
	       		$("#pageNo").val(parseInt($("#number").val())+1);
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#pageNo").val(pageNo);
	       }
	       
       	   $("#form").submit();

        }     
        
        //清除数据
        function clearData(){
        	
        }
        
        
        function checkForm(){
        	
        	return false;
        }
        
        //删除
        function deleteEvaluationItem(id){
        	if(confirm("是否删除？")){
        		$.ajax({
        			url:"deleteEvaluationItem.action?id="+id,
        			error:function(){"系统错误，请联系管理员！"},
        			success:function(){
        				$("#form").submit();
        			}
        		});
        	}
        }
        
        //根据流程查询
        function showByFlowId(obj){
        	var flowId = $("option:selected").val();
        	$("#hideFlowId").val(flowId);
        	$("#form").submit();
        }
        
        $(document).ready(function(){
        	var flowId = $("#hideFlowId").val();
       		if(flowId!=null && flowId!=""){
        		$("#"+flowId+"flow").attr("selected",true);
        	}
        });
      
</script>



</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起"></div>
<div class="posi fl">
<ul>
	<li><a href="#">定量角度评价管理</a></li>
	<li class="fin">查看</li>
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
<!--Ctrl End--> <!--Tabs_2-->
<!-- 
	<div class="tabs_2">
	<ul>
	</ul>
	</div>
 -->
<!--Tabs_2 End--> <!--Filter-->


<div class="filter">
		
		 <!-- 
        	<div class="query p8">
        		<s:form action="showEvaluationByPage" name="OfficialBusinessEvaluation" namespace="/Evaluation" id="form">
        		<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
	        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	  
	        	    <tr>
	        	    
		        	    <td class="t_r">评价部门</td>
		        	    <td>
							<input type="text" value="<s:property value='evaluateDept'/>" name="evaluateDept" class="input_large" id="evaluateDept"/>		        	      	
		      	        </td>
	        	      	<td class="t_r">被评部门</td>
	        	     	<td>
	        	      		<input type="text" value="<s:property value='beEvaluatedDept'/>" name="beEvaluatedDept" class="input_large" id="beEvaluatedDept"/>
	        	      	</td>
	        	        <td class="t_r">评价时间</td>
	        	        <td>
		        	      	<input type="text" value="<s:property value='#request.startDate' />" name="startDate" class="input_large" id="startDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		        	      	— <input type="text" value="<s:property value='#request.endDate' />" name="endDate" class="input_large" id="endDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
	        	        </td>
	      	        </tr>
	      	       	
	        	    <tr>
	        	        <td class="t_r">评价内容</td>
	        	        <td>
		        	      	<select class="input_large" name="goodMediumBad">
		        	      		<option value="-1" id="choice1" selected="selected">---请选择---</option>
		        	      		<option value="好评" id="choice2">好评</option>
		        	      		<option value="中评" id="choice3">中评</option>
		        	      		<option value="差评" id="choice4">差评</option>
		        	      	</select>
		        	      	
	        	        </td>
	        	       	<td class="t_r">涉及流程</td>
	        	        <td>
		        	      	<input type="text" value="<s:property value='involvedProcess'/>" name="involvedProcess" class="input_large" id="involvedProcess"/>
	        	        </td> 
	      	        </tr>
	        	    <tr>
	        	      <td colspan="6" class="t_c">
	                  	<input type="submit" value="检 索" onclick="return checkForm();"/>&nbsp;&nbsp;
						<input type="button" value="重 置" onclick="clearData();"/></td>
	       	        </tr>
	      	    </table>
      	    </s:form>
        	</div>
 	      	--> 
 	      	
 	      	<!--<div class="fn">
				流程：
				<select name="flowId" onchange="showByFlowId(this);">
					<option value="-1" id="-1flow">---请选择---</option>
					<s:iterator value="#request.flowMap" id="flowMap">
						<option id="<s:property value='#flowMap.key'/>flow" value="<s:property value='#flowMap.key'/>"><s:property value='#flowMap.value'/></option>
					</s:iterator>
				</select>
				<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();"/>
			</div>
--></div>





<s:form action="showEvaluationItem" name="TFlowEvaluationItem" namespace="/evaluate" id="form">
	<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
	<input type="hidden" id="hideFlowId" name="flowId" value="<s:property value="#request.flowId"/>">
</s:form>


<!--Filter End--> <!--Table-->
<div class="mb10">
<table width="100%" class="table_1">
	<thead>
		<th colspan="15">
			<div class="fn" style="float: left;">
				办公行文评价流程：
				<select name="flowId" onchange="showByFlowId(this);">
					<option value="-1" id="-1flow">---请选择---</option>
					<s:iterator value="#request.flowMap" id="flowMap">
						<option id="<s:property value='#flowMap.key'/>flow" value="<s:property value='#flowMap.key'/>"><s:property value='#flowMap.value'/></option>
					</s:iterator>
				</select>
				<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();"/>
			</div>
		</th>
	</thead>
	<tbody>
		<tr class="tit">
			<td>所属流程</td>
			<td>排列顺序</td>
			<td>评价角度</td>
			<td>操作时间</td>
			<td>操作</td>
		</tr>
		
		<s:iterator value="#request.page.result" id="item" status="st">
			<tr>
			<td class="t_c">
				<s:iterator value="#request.flowMap" id="flowMap">
					<s:if test="#flowMap.key==#item.flowId">
						<s:property value="#flowMap.value"/>
					</s:if>					
				</s:iterator>
				</td>
				<td class="t_c"><s:property value="sortingOrder"/></td>
				<td class="t_c"><s:property value="name"/></td>
				<td class="t_c"><s:date name="createDate" format="yyyy-MM-dd"/></td>
				
				<td class="t_c">
					<a class="fl mr5" href="javascript:void(0);" onclick="deleteEvaluationItem('<s:property value="id"/>')"/>删除</a>
					<a class="fl mr5" href="javascript:void(0);" onclick="showEditPage('<s:property value="id"/>');"/>修改</a>
				</td>
			</tr>
		</s:iterator>
	</tbody>
	
	
	<tr class="tfoot">
	      <td colspan="11">
	      <div class="clearfix">
	     
	     <s:if test="#request.page.totalSize==0">
	     	<span>无相关数据</span>
	     </s:if>
	     <s:else>
		      <span class="fl">
		      	  <s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
			      <s:if test="#request.page.totalPageCount==#request.page.currentPageNo">
			      	<s:property value="#request.page.totalSize"/>条
			     </s:if>
			      <s:else>
			      	<s:property value="#request.page.start+#request.page.pageSize-1"/>条
			      </s:else>
		      	</span>
		        <ul class="fr clearfix pager">
		          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
		          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
		            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>" />
		            <input type="button"" name="button" id="button" value="Go" onclick="goPage(0,0)">
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
       		</s:else>
       		</div>
       </td>
     </tr>
     
</table>

</div>
<!--Table End--></div>
</body>
</html>
