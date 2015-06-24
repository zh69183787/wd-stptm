<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>数据查看</title>
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
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});
		
		
		function deleteData(id){
      	  if(confirm("确定删除?")){      		
      		window.location.href="deleteById!deleteById.action?id="+id+"&hrId="+$("#hrId").val()+"&type=view";      		
      	  }
        }
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		var showDividePageHtml ="";
		
		$(document).ready(function(){
			showDividePageHtml = $("#showDividePage").html();
			if($("#showType").nextAll().text()==""){
				var addHtml = "<td colspan='13'><div class='clearfix'>无相关数据</div></td>";
				$("#showDividePage").html(addHtml);
			}
		})
		
		
		function showTypeInfo(obj,hrId,hretId,pageNo){
		
			$("#hretId").val(hretId);
			if(obj!="empty"){
				$("li[id=type]").attr("class","");
				$(obj).parent().attr("class","selected");
			}
			$.ajax({
				type: 'POST',
				url: 'findHrInfoByType.action?hrId='+hrId+"&hretId="+hretId+"&pageNo="+pageNo,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
				    var hrId=$("#hrId").val();				    
					var html ="";
					var divideNum = 0;
					if(obj!=null){
						for(var i=0;i<obj.length;i++){
							if(i < obj.length-1){
								if(obj[i].length != obj[i+1].length){
									divideNum = (i+1);
									break;
								}
							}
						}
						var html = "";				    
						if(divideNum!=0){
							var pageData = obj[obj.length-1];	//页面信息(0:当前页数,1:总条数,2:总页数,3:显示开始页数,4:显示结束页数)
							$("#pageCount").val(pageData[2]);
							$("#pageNo").val(pageData[0]);
							$("#number").val(pageData[0]);
							
							//$("#lastPage").attr("onclick","showPageByPageNo('"+$("#hrId").val()+"','-1',4)");
							
							if(pageData[1]==0){
								$("#showFootInfo").text(pageData[1]+"条记录，当前显示 0 条");
							}else{
								$("#showFootInfo").text(pageData[1]+"条记录，当前显示"+pageData[3]+"-"+pageData[4]+"条");
							}
							
							var nextAll = $("#showPageNow > input");
							$("#showPageNow").text("Pages:"+pageData[0]+"/"+pageData[2]);
							$("#showPageNow").append(nextAll);
							
							for(var i=0;i<divideNum;i++){
								html += "<td class='hideLayer_1 t_c'><b>"+obj[i][0]+"</b></td>";
							}
							html += "<td class='hideLayer_1 t_c'>&nbsp;</td>";
								//"</tr>";
								
							$("#showType").html(html);
							
							//var addNewHtml = "<a target='blank' href='findHrEtDByHrEtId.action?type=extAdd&hretId="+obj[divideNum][obj[divideNum].length-1]+"&hrId="+hrId+"'><i class='add'>新增</i></a>";						
							//$("li[class=button]").html(addNewHtml);
							
							/********************/
							$("#showType").nextAll().remove();
							
							html = "<tr>";
							for(var i=divideNum;i<obj.length-1;i++){
								for(var j=0;j<obj[i].length;j++){
									if(j < obj[i].length-3){
										if(obj[i][j]==null){
											html += "<td class='t_c'></td>";
										}else{
											html += "<td class='t_c' title='"+obj[i][j]+"' id='dataCell'>"+obj[i][j]+"</td>";
										}								
									}
								}
								html += "<td class='t_c'>"+
						   	      				"<a class='fl mr5' target='_blank' href='findHrEtDByHrEtId.action?type=extView&hretId="+obj[divideNum][obj[divideNum].length-2]+"&id="+obj[divideNum][obj[divideNum].length-3]+"'>查看</a>"+
						   	      				//"<a class='fl mr5' href='#' onclick=\"deleteData('"+obj[divideNum][obj[divideNum].length-2]+"')\">删除</a>"+
						   	      				//"<a class='fl mr5' target='blank' href='findHrEtDByHrEtId.action?type=extEdit&hretId="+obj[divideNum][obj[divideNum].length-1]+"&id="+obj[divideNum][obj[divideNum].length-2]+"'>修改</a>"+
						   	      			"</td>";
								html +="</tr>";
							}
							$("#tbody").append(html);
						
							if($("#showType").nextAll().text()==""){
								var addHtml = "<td colspan='13'><div class='clearfix'>无相关数据</div></td>";
								$("#showDividePage").html(addHtml);
							}else{
								//最下面的分页信息
								$("#showDividePage").html(showDividePageHtml);
								
								/************************************/
//alert("else");
								var addText = pageData[1]+"条记录，当前显示"+pageData[3]+"-"+pageData[4]+"条"
								$("#showFootInfo").text(addText);
								
								var nextAll = $("#showPageNow > input");
								$("#showPageNow").text("Pages:"+pageData[0]+"/"+pageData[2]);
								$("#showPageNow").append(nextAll);
							}
							
							//var addNewHtml = "<a target='blank' href='findHrEtDByHrEtId.action?type=extAdd&hretId="+obj[0][obj[0].length-1]+"&hrId="+hrId+"'><i class='add'>新增</i></a>";
							//$("li[class=button]").html(addNewHtml);
							
						}else{
							for(var i=0;i<obj.length;i++){
								html += "<td class='hideLayer_1 t_c'><b>"+obj[i][0]+"</b></td>"
							}
							html += "<td class='hideLayer_1 t_c'>&nbsp;</td>";
							$("#showType").html(html);
							$("#showType").nextAll().remove();
							
							$("#pageNo").val(1);
							$("#number").val(pageData[0]);
							
							var addHtml = "<td colspan='13'><div class='clearfix'>无相关数据</div></td>";
							$("#showDividePage").html(addHtml);
							
							
							
						}
					}else{
				
						//var addNewHtml = "<a href='#'><i class='add'>新增</i></a>";
						//$("li[class=button]").html(addNewHtml);
						$("#showType").html("");
						$("#showType").nextAll().remove();
						
						var addHtml = "<td colspan='13'><div class='clearfix'>无相关数据</div></td>";
						$("#showDividePage").html(addHtml);
						
						var nextAll = $("#showPageNow > input");
						$("#showPageNow").text("Pages:1/0");
						$("#showPageNow").append(nextAll);
						$("#number").val(1);
					}
					subData();		
				}	  
			});
			
		}
		
		$(document).ready(function(){
			subData();
		})
		
		
		//页面截取字符串显示
		function subData(){
			$("td[id=dataCell]").each(function(){
				var html = $(this).html();
				if(html.length > 10){
					$(this).html(($(this).html().substring(0,11)+"..."))
				}				
			})
		}
		
		//跳转到指定页面
		function showPageByPageNo(hrId,pageNo,type){
			//跳到尾页
			if(type==4){
				var hretId = $("#hretId").val();
				pageNo = $("#pageCount").val();
				showTypeInfo("empty",hrId,hretId,pageNo);
			}
			
			//跳到首页
			if(type==5){
				var hretId = $("#hretId").val();
				$("#pageNo").val(pageNo);
				showTypeInfo("empty",hrId,hretId,pageNo);
			}
			//跳转到指定页
			if(type==1){
				var hretId = $("#hretId").val();
				pageNo = $("#number").val();
				if(!pageNo.match(/^[0-9]*$/) || pageNo==0){
					$("#number").focus();
					alert("请输入正整数");
				}else{
					if(parseInt(pageNo) >= parseInt($("#pageCount").val())){
						$("#number").val($("#pageCount").val());
						$("#pageNo").val($("#pageCount").val());
						showTypeInfo("empty",hrId,hretId,$("#pageCount").val());
					}else{
						$("#pageNo").val(pageNo);
						showTypeInfo("empty",hrId,hretId,pageNo);
					}
				}
			}
			
			//跳转到上一页
			if(type==2){
				var hretId = $("#hretId").val();
				pageNo = parseInt($("#pageNo").val());
				if(pageNo > 1){
					pageNo = pageNo - 1;
					$("#pageNo").val(pageNo);
					showTypeInfo("empty",hrId,hretId,pageNo);
				}else{
					return false;
				}
			}
			
			//跳转到下一页
			if(type==3){
				var hretId = $("#hretId").val();
				pageNo = parseInt($("#pageNo").val());
				if(pageNo < parseInt($("#pageCount").val())){
					pageNo = pageNo + 1;
					$("#pageNo").val(pageNo);
					showTypeInfo("empty",hrId,hretId,pageNo);
				}else{
					return false;
				}
			}
		}
		
		
        </script></head>

<body>
 <input type="hidden" id="hretId" value="<s:property value='#request.hretList[0].hretId'/>">
 <input type="hidden" id="pageNo" value="<s:property value='#request.pageNo'/>">
  <input type="hidden" id="pageCount" value="<s:property value='#request.pageCount'/>">
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">工会人事数据库</a></li>                	
                	<li class="fin">“<s:property value='hrBInfoVO.name'/>”信息查看</li>
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
        
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        
        <input type="hidden" id="hrId" name="hrId" value="<s:property value='hrBInfoVO.hrId'/>"> 
        <input type="hidden" id="h_politicalLandscape" name="h_politicalLandscape" value="<s:property value='hrBInfoVO.politicalLandscape'/>">  
        <input type="hidden" id="h_highestDegree" name="h_highestDegree" value="<s:property value='hrBInfoVO.highestDegree'/>"> 
       	  <table width="100%"  class="table_1">
                              <thead>
            <th colspan="4" class="t_r">
        	      &nbsp;
        	      <input type="button" value="关 闭" onclick="shut();"/>
        	      &nbsp;        	      
       	      </th>
                                  </thead>
                              <tbody>
                              <tr>
                                <td class="t_r lableTd"><B>工号</B></td>
                                <td  width="30%"><s:property value='hrBInfoVO.jobNumber'/></td>
                           
                                <td class="t_r lableTd"><B>姓名</B></td>
                                <td><s:property value='hrBInfoVO.name'/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd"><B>身份证号</B></td>
                                <td><s:property value='hrBInfoVO.idCard'/></td>
                                <td class="t_r lableTd"><B>性别</B></td>
                                <td><s:if test="hrBInfoVO.sex==1"> 男 </s:if>
        	                        <s:if test="hrBInfoVO.sex==0"> 女 </s:if>        	     
        	                    </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd"><B>出生年月</B></td>
                                <td><s:date format='yyyy-MM-dd' name='hrBInfoVO.birthday' /></td>
                                <td class="t_r lableTd"><B>民族</B></td>
                                <td><s:property value='hrBInfoVO.nation'/></td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd"><B>政治面貌</B></td>
                                <td><s:property value="hrBInfoVO.politicalLandscape"/></td>
                                <td class="t_r lableTd"><B>籍贯</B></td>
                                <td><s:property value='hrBInfoVO.birthplace'/></td>                                
                              </tr>
                              <tr>
                                <td class="t_r lableTd"><B>最高学历</B></td>
                                <td>
                                  <s:property value="hrBInfoVO.highestDegree"/></td>
                                  <td class="t_r lableTd"><B>手机号码</B></td>
                                <td><s:property value='hrBInfoVO.mobilePhone'/></td>                                
                              </tr>
                              <tr>                                
                                <td class="t_r lableTd"><B>技术等级</B></td>
                                <td><s:property value='hrBInfoVO.technicalGrade'/></td>
                                <td class="t_r lableTd"><B>目前职务</B></td>
                                <td><s:property value='hrBInfoVO.position'/></td>
                              </tr>                             
                              <tr>                                
                                <td class="t_r lableTd"><B>最高职称</B></td>
                                <td><s:property value='hrBInfoVO.jobTitles'/></td>
                                <td class="t_r lableTd"><B>技术专业</B></td>
                                <td><s:property value='hrBInfoVO.technicalMajor'/></td>
                                     
                              </tr>
                              <tr>                                
                                <td class="t_r lableTd"><B>在职或退休</B></td>
                                <td><s:property value='hrBInfoVO.isRetire'/></td>
                                <td class="t_r lableTd"><B>支内或农口</B></td>
                                <td><s:property value='hrBInfoVO.hukou'/></td>
                              </tr>                             
                              <tr>
                              <td class="t_r lableTd"><B>居住地址</B></td>
                                <td colspan="3"><s:property value='hrBInfoVO.residentialAddress'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd"><B>居住地邮编</B></td>
                                <td><s:property value='hrBInfoVO.zipCode'/></td>
                                <td class="t_r lableTd"><B>居住地电话</B></td>
                                <td><s:property value='hrBInfoVO.homePhone'/></td>
                              </tr>
                              
                              <tr>
                                <td class="t_r lableTd"><B>目前工作单位</B></td>
                                <td><s:property value='hrBInfoVO.cCompany'/></td>
                                <td class="t_r lableTd"><B>工作单位地址</B></td>
                                <td><s:property value='hrBInfoVO.companyAddress'/></td>
                              </tr>
                              <tr>
                                <td class="t_r lableTd"><B>单位邮编</B></td>
                                <td><s:property value='hrBInfoVO.companyZip'/></td>
                                <td class="t_r lableTd"><B>单位电话</B></td>
                                <td><s:property value='hrBInfoVO.companyPhone'/></td>
                              </tr>                              
                              <tr>
                                <td class="t_r lableTd"><B>备注说明</B></td>
                                <td colspan="3"><s:property value='hrBInfoVO.remarks'/></td>
                                </tr>                                
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r">
&nbsp;
<input type="button" value="关 闭" onclick="shut();"/>

</td>
                              </tr>
                            </table>
                           
       	  <table width="100%"  class="table_1 odd">
       	    <tbody id="tbody">
       	      <tr class="tit">
       	        <td colspan="50">
       	        <ul>
       	          <h5 onclick=" subData()">关联信息</h5>
       	          <s:iterator value="#request.hretList" status="st">
       	          	<s:if test="#st.index==0">
       	          		<li class="selected" id="type">
	       	          		<a onclick="showTypeInfo(this,'<s:property value="hrBInfoVO.hrId"/>','<s:property value="hretId"/>',1)"><span><s:property value="typeName"/></span></a>
	       	          	</li>
       	          	</s:if>
       	          	<s:else>
       	          		<li id="type">
	       	          		<a onclick="showTypeInfo(this,'<s:property value="hrBInfoVO.hrId"/>','<s:property value="hretId"/>',1)"><span><s:property value="typeName"/></span></a>
	       	          	</li>
       	          	</s:else>
       	          </s:iterator>
       	          <li class="button">
       	          	<!-- <a target="blank" href="findHrEtDByHrEtId.action?type=extAdd&hretId=<s:property value='#request.hretList[0].hretId'/>&hrId=<s:property value='hrBInfoVO.hrId'/>"><i class="add">新增</i></a> -->
       	          </li>
     	          </ul>
     	         </td>
   	          </tr>
   	          
   	          
       	        <tr id="showType">
	       	      	<!-- <td class="hideLayer_1 t_c"><b>序号</b></td> -->
		       	      	<s:iterator value="#request.hretdList">
		       	      		<td class="hideLayer_1 t_c"><b><s:property value="itemName"/></b></td>
		       	      	</s:iterator>
	       	      	<td class="hideLayer_1 t_c">&nbsp;</td>
   	            </tr>
   	            
	   	      	<s:iterator value="#request.hrExtInfoList" status="st">
	   	      		<tr>
	    	      		<s:iterator value="#request.hrExtInfoList[#st.index]" status="st2">
	    	      			<s:if test="#request.hrExtInfoList[#st.index].length-2 > #st2.index">
								<td class="t_c" title="<s:property />" id="dataCell"><s:property /></td>	    
							</s:if>  		
	    	      		</s:iterator>
	   	      			<td class="t_c">
	   	      				<a class="fl mr5" target="_blank" href="findHrEtDByHrEtId.action?type=extView&hretId=<s:property value='#request.hretList[0].hretId'/>&id=<s:property value='#request.hrExtInfoList[#st.index][#request.hrExtInfoList[#st.index].length-2]'/>">查看</a>
	   	      			</td>
	   	      		</tr>
	   	      	</s:iterator>
		   	      	
   	        </tbody>
       	    <tr class="tfoot" id="showDividePage">
	       	      <td colspan="13"><div class="clearfix"><span class="fl" id="showFootInfo"><s:property value="#request.count"/>条记录，当前显示1-<s:property value="#request.hrExtInfoList.size"/>条</span>
	                  <ul class="fr clearfix pager">
	                    <li id="showPageNow">
	                    	Pages:1/<s:property value="#request.pageCount"/>
	                        <input type="text" id="number" name="number" min="0" max="999" step="1" class="input_tiny" />
	                        <input type="button"" name="button" id="" value="Go" onclick="showPageByPageNo('<s:property value='hrBInfoVO.hrId'/>','-1',1)">
	                      </li>
	                      <li><a id="lastPage" href="javascript:void(0);" onclick="showPageByPageNo('<s:property value='hrBInfoVO.hrId'/>','-1',4)">&gt;&gt;</a></li>
	                      <li><a href="javascript:void(0);" onclick="showPageByPageNo('<s:property value='hrBInfoVO.hrId'/>','-1',3)">下一页</a></li>
	                      <li><a href="javascript:void(0);" onclick="showPageByPageNo('<s:property value='hrBInfoVO.hrId'/>','-1',2)">上一页</a></li>
	                      <li><a href="javascript:void(0);" onclick="showPageByPageNo('<s:property value='hrBInfoVO.hrId'/>','1',5)">&lt;&lt;</a></li>
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
