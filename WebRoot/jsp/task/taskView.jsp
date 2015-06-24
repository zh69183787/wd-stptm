<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>资产盘点任务查看</title>
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
<style type="text/css">
	.must{
		color:red;
	}
</style>


<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");		
			
			
		});
</script>


<!-- 该段script最后要删除-->
<script type="text/javascript">
//点击右上角的确认将所有输入框赋值为第一个数据框的值 
function setValue(){
	$("input").each(function(){
		$(this).val($("input:eq(3)").val());
	});
}


</script>



<script type="text/javascript">
$(function(){
	$("input[name=name]").parent().text();
});

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
   	$("#showView").submit();
} 

















</script>






</head>

<body>
<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">资产任务盘点查看</li>
</ul>
</div>
<div class="fr lit_nav">
<ul>
	<li><a class="print" href="#">打印</a></li>
	<li><a class="storage" href="#">网络硬盘</a></li>
	<li><a class="rss" href="#">订阅</a></li>
	<li><a class="chat" href="#">聊天</a></li>
	<li><a class="query" href="#">查询</a></li>
</ul>
</div>
</div>
<div class="mb10">
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd" style="width:20%">任务名称</td>
				<td colspan="3">
					<s:property value="assetTask.taskname"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">任务开始时间</td>
				<td><s:property value="assetTask.starttime"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">任务结束时间</td>
				<td><s:property value="assetTask.endtime"/></td>
			</tr>
			 <tr>
				<td class="t_r lableTd">任务范围</td>
				<td colspan="3">
					<textarea rows="5" style="width: 480px;" readonly="readonly" disabled="disabled"><s:property value="assetTask.taskmemo"/></textarea>
				</td>
			</tr>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</td>
		</tr>
	</table>
<s:form action="showView" name="task" namespace="/task" id="showView">
	<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
	<input type="hidden" name="id" value="<s:property value="assetTask.id"/>" />
</s:form>

	<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="14">资产信息列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td>序号</td>
					<td>资产编号</td>
					<td>资产名称</td>
					<td>规格</td>
					<td>型号</td>
					<td>原值(元)</td>
					<td>净值(元)</td>
					<td>出厂价(元)</td>
					<td>决算价(元)</td>
					<!-- 
					<td>当前设备状态</td>
					<td>所属线路</td>
					 -->
					<td></td>
				</tr>
				
				<s:iterator value="#request.page.result" id="assetInfo" status="st">
					<tr >
						<td class="t_c"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_c"><s:property value="#assetInfo.assetId"/></td>
						<td class="t_c"><s:property value="#assetInfo.assetName"/></td>
						<td class="t_c"><s:property value="#assetInfo.specification"/></td>
						<td class="t_c"><s:property value="#assetInfo.model"/></td>
						<td class="t_c"><s:property value="#assetInfo.yuanzhi"/></td>
						<td class="t_c"><s:property value="#assetInfo.jingzhi"/></td>
						<td class="t_c"><s:property value="#assetInfo.leaveFactoryPrice"/></td>
						<td class="t_c"><s:property value="#assetInfo.finalAccountPrice"/></td>
						<!-- 
						<td>
							<s:iterator value="#request.equipmentStatusList" id="equipmentStatus" >
								<s:if test="#equipmentStatus.id==#assetInfo.equipmentState">
									<s:property value="#equipmentStatus.name"/>									
								</s:if>
							</s:iterator>
						</td>
						<td>
							<s:iterator value="#request.lineList" id="line" >
								<s:if test="#line.id==#assetInfo.routeNum">
									<s:property value="#line.name"/>									
								</s:if>
							</s:iterator>
						</td>
						 -->
					</tr>
				</s:iterator>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="14"><div class="clearfix">
			      <s:if test="#request.page.totalSize==0"><span>无相关数据</span></s:if>
			      <s:else>
			      	<span class="fl">
				      	<s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/> -
					    <s:if test="#request.page.currentPageNo==#request.page.totalPageCount">
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
		       </div></td>
		     </tr>
		</table>
		</div>








</div>
<!--Table End--></div>
</body>
</html>
