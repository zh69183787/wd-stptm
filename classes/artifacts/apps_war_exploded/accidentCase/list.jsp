<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<!--[if IE 6.0]>
           <script src="../js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
        
		<script src="../js/jquery.formalize.js"></script>
		<script src="../js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function crossDomainShowOrHide(){
	$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
}  
</script>		
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
        
        
       	linePick();
       
        //页面加载调用该方法，查询所有地铁线路
        function linePick(){
        	$.ajax({
				type: 'POST',
				url: 'metroLineAction!findAllMetroLine.action',
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
					var metroLine = "<option value=''>---请选择---</option>";
					for(var i=0;i<obj.length;i++){
						if($("#line").val()==obj[i].lineId){
							metroLine +="<option value='"+obj[i].lineId+"' selected>"+obj[i].lineName+"</option>";
						}else{
							metroLine +="<option value='"+obj[i].lineId+"'>"+obj[i].lineName+"</option>";
						}
					}
					$("#line_select").html(metroLine);
					//stationPick();
				}	  
			});
        }
       
        
       function stationPick(){
       	   var lineNo = $("#line_select").val();
	       $.ajax({
				type: 'POST',
				url: "metroStationAction!findStationsByMetroLine.action?line="+lineNo,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
					var metroStation = "<option value=''>---请选择---</option>";
					for(var i=0;i<obj.length;i++){
						if($("#station").val()==obj[i].stationId){
							metroStation +="<option value='"+obj[i].stationId+"' selected>"+obj[i].stationName+"</option>";
						}else{
							metroStation +="<option value='"+obj[i].stationId+"'>"+obj[i].stationName+"</option>";
						}
					}
					$("#station_select").html(metroStation);
				}	  
			});
       };
       
       //跳转到制定页
       function goPage(pageNo,type){
       
       		//type=0,直接跳转到制定页
	       if(type=="0"){
	   	    	$("#pageNo").val($("#number").val());
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
      
      
      //点击取消按钮后 清空所有数据
      function clearInput(){
      
      		//清空时间
      		$("#occurSTime").attr("value","");
      		//清空关键字
      		$("#keyWord").attr("value","");
      		
      		//清空责任部门
      		$("#responsibleDept").attr("value","");
      		
      		//清空涉及地点
      		$("#station_select").attr("value","");
      		
      		//清空时间性质
      		document.getElementById("caseCategory").options[0].selected=true;
      		//清空线路
      		document.getElementById("line_select").options[0].selected=true;
      		
      		//document.getElementById("station_select").options[0].selected=true;
      		//清空审核状态
      		document.getElementById("approvalStatus_select").options[0].selected=true;
      		
      		
      }
      
      //删除数据
      function deleteData(caseId){
      	if(confirm("确定删除?")){
      		window.location.href="metroAccidentCaseAction!deleteMetroAccidentCaseById.action?caseId="+caseId+"&caseType="+$("#caseType").val();
      	}
      	
      }
      
        
       
    </script>



</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
            <div class="posi fl">
            	<ul>
                	<li><a href="#">首页</a></li>
                	<li><a href="#">栏目名称</a></li>
                	<li class="fin">当前栏目名称</li>
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
        		<s:if test="caseType==1">
        			<li class="selected"><a href="findMetroAccidentCaseByPage.action?caseType=1"><span>E类以上典型运营事故</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="findMetroAccidentCaseByPage.action?caseType=1"><span>E类以上典型运营事故</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==2">
        			<li class="selected"><a href="findMetroAccidentCaseByPage.action?caseType=2"><span>运营延误事件</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="findMetroAccidentCaseByPage.action?caseType=2"><span>运营延误事件</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==3">
        			<li class="selected"><a href="findMetroAccidentCaseByPage.action?caseType=3"><span>设备安全事件</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="findMetroAccidentCaseByPage.action?caseType=3"><span>设备安全事件</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==4">
        			<li class="selected"><a href="findMetroAccidentCaseByPage.action?caseType=4"><span>有责投诉</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="findMetroAccidentCaseByPage.action?caseType=4"><span>有责投诉</span></a></li>
        		</s:else>
            </ul>
        </div>
        <!--Tabs_2 End-->
        <!--Filter-->
      <div class="filter">
        	<div class="query p8">
        	
        	<input type="hidden" id="line" value="<s:property value='metroAccidentCaseVO.metroLine'/>">
        	<input type="hidden" id="station" value="<s:property value='metroAccidentCaseVO.metroStation'/>">
        	
        	
        	<s:form action="findMetroAccidentCaseByPage.action" id="form">
        	
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	<input type="hidden" value="<s:property value='metroAccidentCaseVO.caseType'/>" name="caseType" id="caseType">
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r">发生时间</td>
        	      <td><input type="text" id="occurSTime" name="occurSTime" class="input_large" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<s:date name='metroAccidentCaseVO.occurSTime' format='yyyy-MM-dd HH:mm:ss'/>"/></td>
        	      <td class="t_r">事故线路</td>
        	      <td><select name="metroLine" id="line_select" class="input_large" onchange="stationPick()" >
        	      <option value="">---请选择---</option>
      	        </select></td>
        	      <td class="t_r">涉及地点</td>
        	      <td>
        	      <!-- 
        	      	<select name="metroStation" id="station_select" class="input_large" >
        	        	<option value="">---请选择---</option>
      	        	</select>
      	          -->
      	          <input type="text" name="metroStation" id="station_select" class="input_large" value="<s:property value="metroAccidentCaseVO.metroStation"/>"/>
      	        	</td>
      	        </tr>
        	    <tr>
        	      <td class="t_r">关键字</td>
        	      <td><input type="text" id="keyWord" name="keyWord" class="input_large" value="<s:property value="metroAccidentCaseVO.keyWord"/>"/></td>
        	      <td class="t_r">事件性质</td>
        	      <td>
        	      	<!-- <input type="text" id="caseCategory" name="caseCategory" class="input_large" value="<s:property value="metroAccidentCaseVO.caseCategory"/>"/> -->
        	      	<select name="caseCategory" class="input_large" id="caseCategory">
        	      		<s:if test="caseCategory=='一级'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="一级" selected="selected">一级</option>
	        	      		<option value="二级">二级</option>
	        	      		<option value="三级">三级</option>
        	      		</s:if>
        	      		<s:elseif test="caseCategory=='二级'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="一级">一级</option>
	        	      		<option value="二级" selected="selected">二级</option>
	        	      		<option value="三级">三级</option>
        	      		</s:elseif>
        	      		<s:elseif test="caseCategory=='三级'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="一级">一级</option>
	        	      		<option value="二级">二级</option>
	        	      		<option value="三级" selected="selected">三级</option>
        	      		</s:elseif>
        	      		<s:else>
        	      			<option value="" selected="selected">---请选择---</option>
	        	      		<option value="一级">一级</option>
	        	      		<option value="二级">二级</option>
	        	      		<option value="三级">三级</option>
        	      		</s:else>
        	      	</select>
        	      </td>
        	      <td class="t_r">责任部门</td>
        	      <td><input type="text" id="responsibleDept" name="responsibleDept" class="input_large" value="<s:property value="metroAccidentCaseVO.responsibleDept"/>"/></td>
      	        </tr>
      	        <tr>
      	          <td class="t_r">审核状态</td>
        	      <td>
        	      	<select id="approvalStatus_select" name="approvalStatus" class="input_large" />
	        	      	<s:if test="approvalStatus==00001">
	        	      		<option value="">---请选择---</option>
	        	      		<option value="0001" selected="selected">待审核</option>
	        	      		<option value="0002">预审通过</option>
	        	      		<option value="0003">终审通过</option>
	        	      	</s:if>
	        	      	<s:elseif test="approvalStatus==00002">
	        	      		<option value="">---请选择---</option>
	        	      		<option value="0001" >待审核</option>
	        	      		<option value="0002" selected="selected">预审通过</option>
	        	      		<option value="0003">终审通过</option>
	        	      	</s:elseif>
	        	      	<s:elseif test="approvalStatus==00003">
	        	      		<option value="">---请选择---</option>
	        	      		<option value="0001" >待审核</option>
	        	      		<option value="0002" >预审通过</option>
	        	      		<option value="0003" selected="selected">终审通过</option>
	        	      	</s:elseif>
						<s:else>
							<option value="" selected="selected">---请选择---</option>
	        	      		<option value="0001">待审核</option>
	        	      		<option value="0002">预审通过</option>
	        	      		<option value="0003">终审通过</option>
						</s:else>        	      	
        	      	</select>
        	      </td>
      	        </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />
      &nbsp;&nbsp;
<input type="button" value="取 消" onclick="clearInput()"/></td>
</tr>
      	    </table>
      	    </s:form>
        	</div>
            <div class="fn">
              <input type="button" name="button2" id="button2" value="新 增" onclick="window.open('toAdd.action?caseType=<s:property value='metroAccidentCaseVO.caseType'/>')" class="new">
            </div>
      </div>
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<table width="100%"  class="table_1">
                              <thead>
                                    <th colspan="15">设备安全事故列表</th>
                              </thead>
                              <tbody>
                              <tr class="tit">
                                <td><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>
                                <td>发生时间</td>
                                <td>事故线路</td>
                                <td>涉及地点</td>
                                <td>事件名称</td>
                                <td>关键字</td>
                                <td>事件性质</td>
                                <td>责任部门</td>
                                <td>上报人</td>
                                <td>操作</td>
                                </tr>
                              <s:iterator value="#request.page.result">
                              <tr>
                                <td class="t_c"><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>
                                <td class="t_c"><s:date name="occurSTime" format="yyyy-MM-dd HH:mm"/></td>
                                <td class="t_c"><s:property value="metroLineName"/></td>
                                <td class="t_c"><s:property value="metroStation"/></td>
                                <td class="t_c"><s:property value="caseName"/></td>
                                <td class="t_c"><s:property value="keyWord"/></td>
                                <td class="t_c"><s:property value="caseCategory"/></td>
                                <td class="t_c"><s:property value="responsibleDept"/></td>
                                <td class="t_c"><s:property value="reportPerson"/></td>
                                <td class="t_c"><a class="fl mr5" href="findMetroAccidentCaseById.action?caseId=<s:property value='caseId'/>&type=view" target="_blank">查看</a> 
									<a class="fl mr5" href="javascript:void(0)" onclick="deleteData(<s:property value='caseId'/>)">删除</a>
                                 <a class="fl mr5" href="findMetroAccidentCaseById.action?caseId=<s:property value='caseId'/>&type=edit" target="_blank">修改</a></td>
                                </tr>
                                </s:iterator>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="10"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-<s:property value="#request.page.start+#request.page.pageSize-1"/>条</span>
                           			 <ul class="fr clearfix pager">
                              <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
	                                  <input type="text" id="number" name="number" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>"/>
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
                        </div>
                                </td>
                              </tr>
                            </table>

      </div>
        <!--Table End-->
</div>
</body>
</html>