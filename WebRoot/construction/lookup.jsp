<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>路网施工计划排定</title>
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
        	showLimitText();
        })
        
        
        //定义子窗口的名字
        var newWindow = ""; 
        //每3秒执行一次checkSonWindow()方法
        var refresh = setInterval("checkSonWindow()",1000);
        
        //监测子窗口是否关闭
        function checkSonWindow(){
			if(newWindow.closed==true){
				$("#form").submit();
				clearInterval(refresh);
			}
		}
        
       //打开新增页面
		function showAddPage(){
			newWindow = window.open('showUploadPage.action');
		}
        
        
 function goPage(pageNo,type){
 
 			var startDate = $("#startDate").val();
        	if(startDate!=null && startDate!=""){
			   	startDate += ":00";
				$("#hideStartDate").val(startDate);
        	}
        	var endDate = $("#endDate").val();
        	if(endDate!=null && endDate!=""){
        		endDate += ":00";
        		$("#hideEndDate").val(endDate);
        	}
 
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
        	//线路下拉框
        	document.getElementById("-1op").selected = true;
			//申请单位
			$("#applyUnit").val("");
        	//施工单位
        	$("#constructionUnit").val("");
        	//施工内容
        	$("#constructionDetail").val("");
        	//开始时间
        	$("#startDate").val("");
        	//结束时间
        	$("#endDate").val("");
        }
        
        //限制字数长度
        function showLimitText(){
        	$("td[id=showApplyUnit]").each(function(){
        		var text = $(this).text();
        		if(text.length>18){
        			text = text.substring(0,18)+"...";
        			$(this).text(text);
        		}
        	})
        	$("td[id=showConstructionUnit]").each(function(){
        		var text = $(this).text();
        		if(text.length>18){
        			text = text.substring(0,18)+"...";
        			$(this).text(text);
        		}
        	})
        	$("td[id=showConstructionDetail]").each(function(){
        		var text = $(this).text();
        		if(text.length>30){
        			text = text.substring(0,30)+"...";
        			$(this).text(text);
        		}
        	})
        	$("td[id=showResponsiblePerson]").each(function(){
        		var text = $(this).text();
        		if(text.length>8){
        			text = text.substring(0,8)+"...";
        			$(this).text(text);
        		}
        	})
        	
        }
        
        function checkForm(){
        	var startDate = $("#startDate").val();
        	if(startDate!=null && startDate!=""){
			   	startDate += ":00";
				$("#hideStartDate").val(startDate);
        	}
        	var endDate = $("#endDate").val();
        	if(endDate!=null && endDate!=""){
        		endDate += ":00";
        		$("#hideEndDate").val(endDate);
        	}
        	return true;
        }
        
        $(document).ready(function(){
        
        	steyLineNo()
        });
        
        //查询后保存lineNO的值 
        function steyLineNo(){
        	var lineNo = $("#lineNo").val();
        	if(lineNo!=null && lineNo!=""){
	        	document.getElementById(lineNo+"op").selected = true;
	        }
        }
        
$(function(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide")
		$(".filter").css("display","none");
	else
		$(".filter").css("display","block");
loadShow();
	
});
//控制显示或隐藏查询条件
function showOrHideControl(){
	var status = $("input[name=showOrHide]").val();	
	if(status=="hide"){
		$(".filter").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
		$(".filter").slideUp();
		$("input[name=showOrHide]").val("hide");
	}
} 
      
</script>



</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
<div class="posi fl">
<ul>
	<li><a href="#">路网施工计划排定</a></li>
	<li class="fin">查看</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li class="selected"><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#" onclick="showOrHideControl();">查询</a></li>
</ul>
</div>
</div>
<!--Ctrl End--> <!--Tabs_2-->

<!--Tabs_2 End--> <!--Filter-->
<div class="filter">
<input type="hidden" id="lineNo" value="<s:property value='tConstructionNoticeVO.lineNo'/>">
        	<div class="query p8">
        		<s:form action="findTConstructionNotice" name="TConstructionNotice" namespace="/construction" id="form">
        		<input type="hidden" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
        		<input type="hidden" name="pageNo" id="pageNo"/>
	        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        	  
	        	    <tr>
	        	    
	        	    <td class="t_r">线路</td>
	        	     <td>
	        	      	<select name="lineNo" id="select3" class="input_large">
							<option value="-1" id="-1op">---请选择---</option>	        	      		
	        	      		<s:iterator value="#request.metroLineList">
	        	      			<s:if test="lineName=='全网'">
	        	      				<option value="<s:property value='lineId'/>" id="<s:property value='lineId'/>op">跨线</option>
	        	      			</s:if>
	        	      			<s:else>
	        	      				<option value="<s:property value='lineId'/>" id="<s:property value='lineId'/>op"><s:property value="lineName"/></option>
	        	      			</s:else>
	        	      		</s:iterator>
	      	        	</select>
	      	          </td>
	        	    
	        	      <td class="t_r">申请单位</td>
	        	      <td>
	        	      	<input type="text" value="<s:property value='applyUnit'/>" name="applyUnit" class="input_large" id="applyUnit"/>
	        	      </td>
	        	       <td class="t_r">施工时间</td>
	        	      <td>
	        	      	<input type="hidden" value="" name="constructionStartDate" id="hideStartDate"/>
	        	      	<input type="hidden" value="" name="constructionEndDate" id="hideEndDate"/>
	        	      	<input type="text" id="startDate" name="startDate" class="input_large" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="<s:date name='tConstructionNoticeVO.constructionStartDate' format='yyyy-MM-dd HH:mm'/>" readonly="readonly"/>
	        	      	— <input type="text" id="endDate" name="endDate" class="input_large" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="<s:date name='tConstructionNoticeVO.constructionEndDate' format='yyyy-MM-dd HH:mm'/>" readonly="readonly"/>
	        	      </td>
	        	      
	      	        
	      	        </tr>
	      	       
	        	    <tr>
	        	      <td class="t_r">施工内容</td>
	        	      <td>
	        	      	<input type="text" id="constructionDetail" name="constructionDetail" value="<s:property value='constructionDetail'/>" class="input_large" />
	        	      </td>
	        	      
	        	      <td class="t_r">施工单位</td>
	        	      <td>
	        	      	<input type="text" value="<s:property value='constructionUnit'/>" name="constructionUnit" class="input_large" id="constructionUnit"/>
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
	<div class="fn">
		<input type="button"" name="button2" id="button2" value="批量导入" class="new" onclick="showAddPage();">
		<input type="button"" name="button3" id="button3" value="模板下载" class="new" onclick="window.location='downloadFile.action'">
	</div>
</div>
<!--Filter End--> <!--Table-->
<div class="mb10">
<table width="100%" class="table_1">
	<thead>
		<th colspan="15">路网施工计划排定情况</th>
	</thead>
	<tbody>
		<tr class="tit">
			<td class="t_c">线路</td>
			<td class="t_c">开始时间</td>
			<td class="t_c">结束时间</td>
			<td class="t_c">申请单位</td>
			<td class="t_c">施工单位</td>
			<td class="t_c">施工内容</td>
			<td class="t_c">负责人</td>
			<td class="t_c">计划编号</td>
			<td></td>
		</tr>
		<s:iterator value="#request.page.result">
		
			<tr>
				<td class="t_c">
				
				<s:property value="u.lineNo"/>
				
				<s:iterator value="#request.metroLineList" id="inner">
					<s:if test="#inner.lineId==lineNo">
						<s:if test="#inner.lineName=='全网'">
							跨线
						</s:if>
						<s:else>
							<s:property value="#inner.lineName"/>						
						</s:else>
					</s:if>
				</s:iterator>
				</td>
				<td class="t_c"><s:date name="constructionStartDate" format="yyyy-MM-dd HH:mm"/></td>
				<td class="t_c"><s:date name="constructionEndDate" format="yyyy-MM-dd HH:mm"/></td>
				<td id="showApplyUnit" title="<s:property value='applyUnit'/>"><s:property value="applyUnit"/></td>
				<td id="showConstructionUnit" title="<s:property value='constructionUnit'/>"><s:property value="constructionUnit"/></td>
				<td id="showConstructionDetail" title="<s:property value='constructionDetail'/>"><s:property value="constructionDetail"/></td>
				<td class="t_c" id="showResponsiblePerson" title="<s:property value='responsiblePerson'/>"><s:property value="responsiblePerson"/></td>
				<td class="t_c"><s:property value="projectNo"/></td>
				<td class="t_c"><a href="findConstructionById.action?id=<s:property value='id'/>" target="_blank"/>查看</a></td>
				
			</tr>
		</s:iterator>
	</tbody>
	<tr class="tfoot">
	      <td colspan="11"><div class="clearfix">
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
       </div></td>
     </tr>
</table>

</div>
<!--Table End--></div>
</body>
</html>
