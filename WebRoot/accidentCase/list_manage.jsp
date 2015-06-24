<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>案例管理</title>
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
        <script src="../js/high.js"></script>
        <script src="../js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
			$(function(){ $('#High').high();});
		</script>
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
      		$("#occurSTime1").attr("value","");
      		$("#occurSTime2").attr("value","");
      		//清空关键字
      		$("#keyWord").attr("value","");
      		
      		//清空责任部门
      		$("#responsibleDeptName").attr("value","");
      		
      		//清空涉及地点
      		$("#station_select").attr("value","");
      		
      		//清空时间性质
      		document.getElementById("caseCategoryName").options[0].selected=true;
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
      
      
       $(document).ready(function() {
      		$('[id=show_keyWord]').each(
      			function(index){
      				var html = $(this).html();
      				var trimhtml = trimStr(html);
      				$(this).html(trimStr(html));
      			}
      		)
      		
      		$('[id=show_reportPersonName]').each(
      			function(index){
      				var html = $(this).html();
      				var trimhtml = trimStr(html);
      				$(this).html(trimStr(html));
      			}
      		)
      		
      		$('[id=show_caseName]').each(
      			function(index){
      				var html = $(this).html();
      				var trimhtml = trimStr(html);
      				$(this).html(trimStr(html));
      			}
      		)
      		
      		$('[id=show_metroStation]').each(
      			function(index){
      				var html = $(this).html();
      				var trimhtml = trimStr(html);
      				$(this).html(trimStr(html));
      			}
      		)
      		
      		$('[id=show_responsible]').each(
      			function(index){
      				var html = $(this).html();
      				var trimhtml = trimStr(html);
      				$(this).html(trimStr(html));
      			}
      		)
      		
      });
      
	function trimStr(html){
		if(html.length>7){
			return (html.substr(0,7)+"...");
		}
		return html
	}
	
	function submitFormByCaseCType(caseCType){
		$("#caseCTypeCode").val(caseCType);
		$("#form").submit();
		
	}
	
	function submitFormByCaseType(caseType){
	
		$("#caseType").val(caseType);
		$("#caseCTypeCode").val("");
		clearInput();
		$("#form").submit();
		
		
	}
	
	
	$(document).ready(function(){
	
		$("td[id=date]").each(function(index){
			var text = $(this).text();
			var subText = text.substring(11,16);
			
			if(subText=="00:00"){
				text =  text.substring(0,10);
				text += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				$(this).html(text);
			}
		})
	})
	
$(function(){
	 $("img").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 );
});	
	
	
	
    </script>



</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>
	
	<div class="main">
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
            <div class="posi fl">
            	<ul>
                	<li><a href="#">运营事件案例库</a></li>
                	<li><a href="#">案例管理</a></li>
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
        			<li class="selected"><a href="#" onclick="submitFormByCaseType(1)"><span>E类以上典型运营事故</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="#" onclick="submitFormByCaseType(1)"><span>E类以上典型运营事故</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==2">
        			<li class="selected"><a href="#" onclick="submitFormByCaseType(2)"><span>运营延误事件</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="#" onclick="submitFormByCaseType(2)"><span>运营延误事件</span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==3">
        			<li class="selected"><a href="#" onclick="submitFormByCaseType(3)"><span>设备安全事件 </span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="#" onclick="submitFormByCaseType(3)"><span>设备安全事件 </span></a></li>
        		</s:else>
        		
        		<s:if test="caseType==4">
        			<li class="selected"><a href="#" onclick="submitFormByCaseType(4)"><span>有责投诉</span></a></li>
        		</s:if>
        		<s:else>
        			<li><a href="#" onclick="submitFormByCaseType(4)"><span>有责投诉</span></a></li>
        		</s:else>
        		
            </ul>
        </div>
        <!--Tabs_2 End-->
        <!--Filter-->
        
      <div class="filter">
      
      		<div class="query p8">
        	
        	<input type="hidden" id="line" value="<s:property value='metroAccidentCaseVO.metroLine'/>">
        	<input type="hidden" id="station" value="<s:property value='metroAccidentCaseVO.metroStation'/>">
        	
        	
        	<s:form action="findMetroAccidentCaseByPage" id="form" name="MetroAccidentCase" namespace="/accidentCase">
        	
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	<input type="hidden" name="caseType" value="<s:property value='metroAccidentCaseVO.caseType'/>" id="caseType">
        	<input type="hidden" name="caseCTypeCode" value="<s:property value='caseCTypeCode'/>" id="caseCTypeCode"/>
        	
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <td class="t_r" style="white-space:nowrap;">发生时间</td>
        	      <td style="white-space:nowrap;"><input type="text" id="occurSTime1" name="occurSTime1" style="width:116px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value='#request.occurSTime1'/>"/>
        	      至<input type="text" id="occurSTime2" name="occurSTime2" style="width:116px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value='#request.occurSTime2'/>"/>
        	      </td>
        	      <td class="t_r" style="white-space:nowrap;">事故线路</td>
        	      <td><select name="metroLine" id="line_select" class="input_large" onchange="" >
        	      <option value="">---请选择---</option>
      	        </select></td>
        	      <td class="t_r" style="white-space:nowrap;">涉及地点</td>
        	      <td>
        	      <!-- 
        	      	<select name="metroStation" id="station_select" class="input_large" >
        	        	<option value="">---请选择---</option>
      	        	</select>
      	          -->
      	          <input type="text" name="metroStationName" id="station_select" class="input_large" value="<s:property value="metroAccidentCaseVO.metroStationName"/>"/>
      	        	</td>
      	        </tr>
        	    <tr>
        	      <td class="t_r" style="white-space:nowrap;">关键字</td>
        	      <td><input type="text" id="keyWord" name="keyWord" class="input_large" value="<s:property value="metroAccidentCaseVO.keyWord"/>"/></td>
        	      <td class="t_r" style="white-space:nowrap;">事件性质</td>
        	      <td>
        	      	<!-- <input type="text" id="caseCategory" name="caseCategory" class="input_large" value="<s:property value="metroAccidentCaseVO.caseCategory"/>"/> -->
        	      	<select name="caseCategoryName" class="input_large" id="caseCategoryName">
        	      		
        	      		<s:if test="caseType==1">
        	      		<s:if test="caseCategoryName=='特别重大'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大" selected="selected">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D">一般D</option>
	        	      		<option value="一般E">一般E</option>        	      		
        	      		</s:if>
        	      		<s:elseif test="caseCategoryName=='重大'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大" selected="selected">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D">一般D</option>
	        	      		<option value="一般E">一般E</option> 
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='较大'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大"">重大</option>
	        	      		<option value="较大" selected="selected>较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D">一般D</option>
	        	      		<option value="一般E">一般E</option> 
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='一般A'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A" selected="selected">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D">一般D</option>
	        	      		<option value="一般E">一般E</option> 
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='一般B'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B" selected="selected">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D">一般D</option>
	        	      		<option value="一般E">一般E</option> 
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='一般C'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C"  selected="selected">一般C</option>
	        	      		<option value="一般D">一般D</option>
	        	      		<option value="一般E">一般E</option> 
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='一般D'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D"  selected="selected">一般D</option>
	        	      		<option value="一般E">一般E</option> 
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='一般E'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D" >一般D</option>
	        	      		<option value="一般E" selected="selected">一般E</option> 
        	      		</s:elseif>
        	      		<s:else>
        	      			<option value="" selected="selected">---请选择---</option>
	        	      		<option value="特别重大">特别重大</option>
	        	      		<option value="重大">重大</option>
	        	      		<option value="较大">较大</option>
	        	      		<option value="一般A">一般A</option>
	        	      		<option value="一般B">一般B</option>	
	        	      		<option value="一般C">一般C</option>
	        	      		<option value="一般D" >一般D</option>
	        	      		<option value="一般E" >一般E</option> 
        	      		</s:else>
        	      		
        	      		</s:if>
        	      		<s:else>    	      		
        	      		
        	      		<s:if test="caseCategoryName=='一级'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="一级" selected="selected">一级</option>
	        	      		<option value="二级">二级</option>
	        	      		<option value="三级">三级</option>
        	      		</s:if>
        	      		<s:elseif test="caseCategoryName=='二级'">
        	      			<option value="">---请选择---</option>
	        	      		<option value="一级">一级</option>
	        	      		<option value="二级" selected="selected">二级</option>
	        	      		<option value="三级">三级</option>
        	      		</s:elseif>
        	      		<s:elseif test="caseCategoryName=='三级'">
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
        	      		</s:else>
        	      	</select>
        	      </td>
        	      <td class="t_r" style="white-space:nowrap;">责任部门</td>
        	      <td><input type="text" id="responsibleDeptName" name="responsibleDeptName" class="input_large" value="<s:property value="metroAccidentCaseVO.responsibleDeptName"/>"/></td>
      	        </tr>
      	        <tr>
      	          <td class="t_r" style="white-space:nowrap;">审核状态</td>
        	      <td>
        	      	<select id="approvalStatus_select" name="approvalStatus" class="input_large" />
	        	      	<s:if test="approvalStatus==00001">
	        	      		<option value="eeee">---请选择---</option>
	        	      		<option value="0001" selected="selected">待审核</option>
	        	      		<option value="0002">预审通过</option>
	        	      		<option value="0003">终审通过</option>
	        	      	</s:if>
	        	      	<s:elseif test="approvalStatus==00002">
	        	      		<option value="eeee">---请选择---</option>
	        	      		<option value="0001" >待审核</option>
	        	      		<option value="0002" selected="selected">预审通过</option>
	        	      		<option value="0003">终审通过</option>
	        	      	</s:elseif>
	        	      	<s:elseif test="approvalStatus==00003">
	        	      		<option value="eeee">---请选择---</option>
	        	      		<option value="0001" >待审核</option>
	        	      		<option value="0002" >预审通过</option>
	        	      		<option value="0003" selected="selected">终审通过</option>
	        	      	</s:elseif>
						<s:else>
							<option value="eeee" selected="selected">---请选择---</option>
	        	      		<option value="0001">待审核</option>
	        	      		<option value="0002">预审通过</option>
	        	      		<option value="0003">终审通过</option>
						</s:else>        	      	
        	      	</select>
        	      </td>
      	        </tr>
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
					<input type="button" value="取 消" onclick="clearInput()"/></td>
				</tr>
      	    </table>
      	    </s:form>
        	</div>
        <div class="fn">
          <input type="submit" name="button2" id="button2" value="新 增" class="new" onclick="window.open('toAdd.action?caseType=<s:property value='metroAccidentCaseVO.caseType'/>')">
        </div>
      </div>
      
        <!--Filter End-->
        <!--Table-->
        <div class="mb10">
        	<div class="fl tree_1" id="High">
            	<div class="con">
                	<ul>
                    	<s:if test="caseType==1">
                		<li>
                        	<h5><a href="#">E类以上典型运营事故</a></h5>
                            <ul>
                            	<s:if test="caseCTypeCode==5">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(5)">车辆故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(5)">车辆故障</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==6">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(6)">供电故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(6)">供电故障</a></li>
                            	</s:else>
                            
                            	<s:if test="caseCTypeCode==7">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(7)">通号故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(7)">通号故障</a></li>
                            	</s:else>
                            
                            	<s:if test="caseCTypeCode==8">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(8)">工务故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(8)">工务故障</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==9">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(9)">屏蔽门故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(9)">屏蔽门故障</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==10">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(10)">弓网故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(10)">弓网故障</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==11">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(11)">挤岔故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(11)">挤岔故障</a></li>
                            	</s:else>
                            
                            	<s:if test="caseCTypeCode==12">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(12)">晚点故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(12)">晚点故障</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==13">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(13)">违章施工</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(13)">违章施工</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==14">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(14)">违章作业</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(14)">违章作业</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==15">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(15)">人车冲突故障</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(15)">人车冲突故障</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==16">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(16)">越站通过</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(16)">越站通过</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==17">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(17)">异物侵限</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(17)">异物侵限</a></li>
                            	</s:else>
                            
                            </ul>
                        </li>
                	</s:if>
                	<s:if test="caseType==2">
                		<li>
                        	<h5><a href="#"></a></h5>
                            <ul>
                            	<li ><a href="findMetroAccidentCaseByPage.action?caseCTypeCode=1&caseType=<s:property value='caseType'/>"></a></li>
                            </ul>
                        </li>
                	</s:if>
                	<s:if test="caseType==3">
                    	<li>
                        	<h5><a href="#">设备安全事件</a></h5>
                            <ul>
                            	<s:if test="caseCTypeCode==1">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(1)">车辆专业</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(1)">车辆专业</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==2">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(2)">供电专业</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(2)">供电专业</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==3">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(3)">通号专业</a></li>
                            	</s:if>
                            	<s:else>
                            		<li><a href="#" onclick="submitFormByCaseCType(3)">通号专业</a></li>
                            	</s:else>
                            	
                            	<s:if test="caseCTypeCode==4">
                            		<li class="selected"><a href="#" onclick="submitFormByCaseCType(4)">工务专业</a></li>
                            	</s:if>
                            	<s:else>
                            		<li class="fin"><a href="#" onclick="submitFormByCaseCType(4)">工务专业</a></li>
                            	</s:else>
                            </ul>
                        </li>
                     </s:if>   
                        
					<s:if test="caseType==4">
						<li>
                        	<h5><a href="#"></a></h5>
                            <ul>
                            	<li ><a href="findMetroAccidentCaseByPage.action?caseCTypeCode=1&caseType=<s:property value='caseType'/>"></a></li>
                            	
                            </ul>
                        </li>
                	</s:if>                        
                        
                    </ul>
                </div>
            </div>
        	<div class="ml210">
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
	 	                            <td>状态</td>
	 	                            <td>操作</td>
	                            </tr>
	                            
	                            <s:iterator value="#request.page.result">
	 	                          <tr>
	 	                            <td class="t_c"><input type="checkbox" id="test_checkbox_1" name="test_checkbox_1" /></td>
	 	                            <td class="t_c" id="date"><s:date name="occurSTime" format="yyyy-MM-dd HH:mm"/></td>
	 	                            <td class="t_c">
	 	                            <!-- 
	 	                            	<s:if test="metroLine==1"><span class="lineNum l01"><s:property value="metroLine"/></span></s:if>
	 	                            -->
	 	                            <span><s:property value="metroLineName"/></span>
	 	                            </td>
	 	                            <td class="t_c" id="show_metroStation" title="<s:property value="metroStationName"/>"><s:property value="metroStationName"/></td>
	 	                            <td class="t_c" id="show_caseName" title="<s:property value="caseName"/>"><s:property value="caseName"/></td>
	 	                            <td class="t_c" id="show_keyWord" title="<s:property value="keyWord"/>"><s:property value="keyWord"/></td>
	 	                            <td class="t_c"><s:property value="caseCategoryName"/></td>
	 	                            <td class="t_c" id="show_responsible" title="<s:property value="responsibleDeptName"/>"><s:property value="responsibleDeptName"/></td>
	 	                            <td class="t_c" id="show_reportPersonName" title="<s:property value="reportPersonName"/>"><s:property value="reportPersonName"/></td>
	 	                             <td class="t_c">
										<s:if test="approvalStatus==0002">
											预审通过
										</s:if>
										<s:elseif test="approvalStatus==0003">
											终审通过
										</s:elseif>
										<s:elseif test="approvalStatus==0001">
											待审核
										</s:elseif>
	 	                            </td>
	 	                            <td class="t_c">
	  	                            <a class="fl mr5" href="findMetroAccidentCaseById.action?caseId=<s:property value='caseId'/>&type=view&caseType=<s:property value='metroAccidentCaseVO.caseType'/>" target="_blank">查看</a> 
	  	                            <a class="fl mr5" href="javascript:void(0)" onclick="deleteData('<s:property value='caseId'/>')">删除</a> 
	  	                            <a class="fl mr5" href="findMetroAccidentCaseById.action?caseId=<s:property value='caseId'/>&type=edit&caseType=<s:property value='metroAccidentCaseVO.caseType'/>" target="_blank">修改</a>
	  	                            </td>
	                            </tr>
	                            </s:iterator>
	                            
	                          </tbody>
  	                          
      	       <tr class="tfoot">
        	      <td colspan="11"><div class="clearfix"><span class="fl"><s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.page.start"/>-<s:property value="#request.page.start+#request.page.pageSize-1"/>条</span>
        	        <ul class="fr clearfix pager">
        	          <li>Pages:<s:property value="#request.page.currentPageNo"/>/<s:property value="#request.page.totalPageCount"/>
        	          	<input type="hidden" value="<s:property value='#request.page.totalPageCount'/>" id="totalPageCount">
        	            <input type="text"" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#request.page.currentPageNo'/>"/>
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

      </div>
        <!--Table End-->
</div>
</body>
</html>
