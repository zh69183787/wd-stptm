<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
<title>
查看督办业务
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

         <s:set name="r" value="#request.result"></s:set>  
        <div class="mb10">
        	<table width="100%"  class="table_1">
				<thead>
                  <th colspan="30">查看督办业务</th>
              </thead>  
            <tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</td>
		</tr>
              <s:iterator value="#r.list" id="items" status="s">
			<tr><td style="background-color: #F2F2F2;text-align: right;font-weight: bold;width:12%">督办业务基本信息</td><td colspan="5"></td></tr>
			<tr>
				<td class="t_r lableTd">督办事项</td>
				<td><s:property value="#items[0]"/></td>
				<td class="t_r lableTd">立项时间 	</td>
				<td><s:property value="#items[1]"/></td>
				<td class="t_r lableTd">主办部门</td>
				<td>
					<s:property value="#items[2]"/>
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">要求回复时间</td>
				<td><s:property value="#items[3]"/></td>
				<td class="t_r lableTd">发起人</td>
				<td><s:property value="#items[4]"/></td>
				<td class="t_r lableTd">办理时间</td>
				<td>
					<s:property value="#items[10]"/>
				</td>
			</tr>
				<tr>
				<td class="t_r lableTd">超期天数</td>
				<td><s:property value="#items[11]"/></td>
				<td class="t_r lableTd">是否办结</td>
				<td><s:property value="#items[6]"/></td>
				<td class="t_r lableTd">是否跟踪</td>
				<td>
					<s:property value="#items[7]"/>
				</td>
			</tr>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
			</td>
		</tr>
                                
                                </s:iterator>
                            </table>

      </div>
        <!--Table End-->
</div>
</div>
</body>
</html>