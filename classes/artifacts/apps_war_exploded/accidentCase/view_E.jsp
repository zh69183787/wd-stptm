<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>案例查看</title>
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
		<style>
			.table_1 td {line-height:200%;font-size:18px;border-bottom:#000 1px solid;	border-right:#000 1px solid;	border-top:#000 1px solid;}
			.table_1{border:#ccc 1px solid; width:960px; }
		</style>
		<script type="text/javascript">
         $(function(){
			$(".odd tr:odd").css("background","#fafafa");			
		});		
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
		
		$.ajax({
			type: 'POST',
			url: 'metroLineAction!findAllMetroLine.action',
			dataType:'json',
			error:function(){alert('提交失败')},
			success: function(obj){			
			    var td1 = $("tbody").children("tr:eq(1)").children("td:eq(1)");	
			    var text= td1.text();		    				
				var line=document.getElementById("line");							
				//alert("ss"+station.value);							
				for(var i=0;i<obj.length;i++){
				    if(line.value==obj[i].lineId)
					  td1.html(obj[i].lineName);						  				
				}
				
				$.ajax({
				type: 'POST',
				url: 'metroStationAction!findStationsByMetroLine.action?line='+text,
				dataType:'json',
				error:function(){alert('提交失败')},
				success: function(obj){
					var td2 = $("tbody").children("tr:eq(1)").children("td:eq(3)");	
					var station=document.getElementById("station");
					//alert(station.value);
					for(var i=0;i<obj.length;i++){
					    if(station.value==obj[i].stationId)
						  td2.html(obj[i].stationName);
					}
					
				}	  
			});
			}	  
		});
		
       window.onload=function showData(){
		var urlLocation = "findTAttachByGroupID.action?groupID="+$("#attachment").val();
		var newData = "";
		$.ajax({
			type: 'POST',
			url: urlLocation,
			dataType:'json',
			error:function(){alert('提交失败')},
			success: function(obj){
						for(var i=0;i<obj.length;i++){
							newData +="<a href='downloadFile.action?fileName="+obj[i].savefilename+"&realName="+obj[i].filename+"."+obj[i].fileextname+"' style='float: left'>"+obj[i].filename+"."+obj[i].fileextname+"</a><br/>";
						}
						$("#attach").html(newData);
						
					}
		});  
       }
		
		
		
		
        
        </script></head>

<body>

	<input type="hidden"  id="attachment" name="attachment" value="<s:property value='metroAccidentCaseVO.attachment'/>">
	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
            	<ul>
                	<li>运营事件案例库</li>
                	<li class="fin">案例查看</li>
                </ul>
            </div>
           
   		</div>
        <!--Ctrl End-->
        <!--Tabs_2-->
       
        <!--Tabs_2 End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10">
        
        <input type="hidden" id="" name="caseId" value="<s:property value='metroAccidentCaseVO.caseId'/>"/>
        <input type="hidden" id="line" name="line"  value="<s:property value='metroAccidentCaseVO.metroLine'/>"/>
        <input type="hidden" id="station" name="station"  value="<s:property value='metroAccidentCaseVO.metroStation'/>"/>
        <input type="hidden" id="case_type" name="case_type" value="<s:property value='metroAccidentCaseVO.caseType'/>"/>
       	  <table  class="table_1" align="center">
                              <thead>
            <th colspan="4">
        	      <h4 class="fl mr5">
        	      	<s:if test="metroAccidentCaseVO.caseType==1">
        			<li class="selected"><a href="#"><span>E类以上典型运营事故</span></a></li>
	        		</s:if>
	        		
	        		
	        		<s:if test="metroAccidentCaseVO.caseType==2">
	        			<li class="selected"><a href="#"><span>运营延误事件</span></a></li>
	        		</s:if>
	        		
	        		
	        		<s:if test="metroAccidentCaseVO.caseType==3">
	        			<li class="selected"><a href="#"><span>设备安全事件</span></a></li>
	        		</s:if>
	        	
	        		
	        		<s:if test="metroAccidentCaseVO.caseType==4">
	        			<li class="selected"><a href="#"><span>有责投诉</span></a></li>
	        		</s:if>
        	      </h4>
        	      <input type="button" value="关 闭" onclick="shut();" class="fr mr5"/>
        	      &nbsp;
        	      </th>
                                  </thead>
                              <tbody>
                              <tr>
                              
                                <td class="t_r lableTd" style="width:13%;border-top:#ccc 1px solid;">时间</td>
                                <td id="occurStime" style="width:37%;border-top:#ccc 1px solid;"><s:date name="metroAccidentCaseVO.occurSTime" format="yyyy-MM-dd HH时mm分" /></td>
                                <td class="t_r lableTd" style="width:13%;border-top:#ccc 1px solid;">事故名称</td>
                                <td style="border-right:#ccc 1px solid;border-top:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.caseName'/></td>
                                <!-- 
                                <td class="t_r lableTd" style="width:13%;">审核状态</td>
                                <td><s:property value='metroAccidentCaseVO.approvalStatus'/></td>
                                 -->
                                
                                </tr>
                              <tr>
                                <td class="t_r lableTd">线路</td>
                                <td><s:property value='metroAccidentCaseVO.metroLine'/></td>
                                <td class="t_r lableTd">地点</td>
                                <td style="border-right:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.metroStationName'/></td>
                                </tr>
                             
                                
                                <!-- 
                                <td class="t_r lableTd">子类别</td>
                                <td><s:property value='metroAccidentCaseVO.caseCType'/></td>
                                 -->
                              
                                <tr>
                                <!-- 
                                <td class="t_r lableTd">关键字</td>
                                <td><s:property value='metroAccidentCaseVO.keyWord'/></td>
                                 -->
                                 <td class="t_r lableTd">责任单位</td>
                                <td><s:property value='metroAccidentCaseVO.responsibleDeptName'/></td>
                                <td class="t_r lableTd">事故性质</td>
                                <td style="border-right:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.caseCategoryName'/></td>                                 
                                </tr>
                              <!-- 
                              <tr>
                                <td class="t_r lableTd">上报人</td>
                                <td><s:property value='metroAccidentCaseVO.reportPersonName'/></td>
                                <td class="t_r lableTd">上报时间</td>
                                <td><s:date name="metroAccidentCaseVO.reportTime" format="yyyy-MM-dd HH时mm分" /></td>
                              </tr>
                               -->
                              
                                
                                <!-- 
                                <td class="t_r lableTd">责任人</td>
                                <td><s:property value='metroAccidentCaseVO.responsiblePersonName'/></td>
                                 -->
                                
                                
                              <tr>
                                <td class="t_r lableTd">事故概述</td>
                                <td colspan="3" style="border-right:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.caseIntroduction'/></td>
                                
                                </tr>
                              <!-- 
                              <tr>
                                <td class="t_r lableTd">整改措施</td>
                                <td colspan="3"><s:property value='metroAccidentCaseVO.correctiveMeasures'/></td>
                                </tr>
                                 -->
                                  <tr>
                                <td class="t_r lableTd">处置措施</td>
                                <td colspan="3" style="border-right:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.treatmentMeasures'/></td>
                                </tr>  
                              <tr>
                                <td class="t_r lableTd">原因分析</td>
                                <td colspan="3" style="border-right:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.causeAnalysis'/></td>
                                </tr>
                                                         
                              <tr>
                                <td class="t_r lableTd" style="border-bottom:#ccc 1px solid;">点评分析</td>
                                <td colspan="3" style="border-right:#ccc 1px solid;border-bottom:#ccc 1px solid;"><s:property value='metroAccidentCaseVO.commentsAnalysis'/></td>
                                </tr>
                              <tr style="display:none">
                                <td class="t_r lableTd">备注</td>
                                <td colspan="3"><s:property value='metroAccidentCaseVO.remarks'/></td>
                                </tr>
                              <tr style="display:none">
                                <td class="t_r lableTd">相关附件</td>
                                <td colspan="3"><span id="attach"></span></td>
                                </tr>
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r" style="border-right:#ccc 1px solid;border-bottom:#ccc 1px solid;border-top:#ccc 1px solid;">
<input type="button" value="关 闭" onclick="shut();"/>

</td>
                              </tr>
                            </table>                            
         
        	
<script>
 var td = $("tbody").children("tr:eq(0)").children("td:eq(3)");	
 var text=td.text(); 
 text=((text.replace("0001","待审核")).replace("0002","预审通过")).replace("0003","终审通过");
 td.html(text);
 
  var occurStime = $("#occurStime").html();
  if("00时00分"==occurStime.substr(11,16)){
    occurStime = occurStime.substr(0,10);
    $("#occurStime").html(occurStime);
  }
 </script>      
            
      </div>
        <!--Table End-->
</div>
</body>
</html>
