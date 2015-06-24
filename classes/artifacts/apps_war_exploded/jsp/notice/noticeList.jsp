<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>我的通知</title>
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
         //定义子窗口
			var sonWindow = null;
			//每1秒执行一次checkSonWindow()方法
			var refresh = setInterval("checkSonWindow()",1000);
			 //监测子窗口是否关闭
			function checkSonWindow(){
				if(sonWindow!=null && sonWindow.closed==true){
					document.forms[0].submit();
				}
			}
        
        $(document).ready(function () {
        	var $tbInfo = $(".filter .query input:text");
        	$tbInfo.each(function () {
        		$(this).focus(function () {
        			$(this).attr("placeholder", "");
        		});
        	});

        	var $tblAlterRow = $(".table_1 tbody tr:even");
        	if ($tblAlterRow.length > 0)
        		$tblAlterRow.css("background", "#fafafa");

        	 
			
				
        });
        
        	//linePick();
       
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
      		$("#beginDate").attr("value","");
      		$("#endDate").attr("value","");
      		//清空关键字
      		$("#sender").attr("value","");
      		
      		//清空责任部门
      		$("#title").attr("value","");
      		
      		
      }
        
        
        
     
      
	function trimStr(html){
		if(html.length>7){
			return (html.substr(0,7)+"...");
		}
		return html
	}
		
	function submitFormByCaseType(type){
		$("#caseType").attr('value',type)
		document.forms[0].submit();
	}
	
	
	function submitFormByMsgType(type){
		$("#msgType").attr('value',type);
		document.forms[0].submit();
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

function showDetail(id){
	sonWindow = window.open('showDetail.action?sid='+id);
}
     
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
                	<li><a href="#">我的事务</a></li>
                	<li><a href="#">个人事务</a></li>
                    <li class="fin">我的通知</li>
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
        	<ul id="urlist" style="display:none;">
          
        		<s:if test="msgUsermessage.caseType==0">
              			 <li class="selected"><a href="#" ><span>未读通知</span></a></li>
  				</s:if>
				
        		<s:else>
        			<li><a href="#" onclick="submitFormByCaseType('0')"><span>未读通知</span></a></li>
        		</s:else>
                	
        		
            	<s:if test="msgUsermessage.caseType==1">
              			<li class="selected"><a href="#" ><span>已读通知</span></a></li>
  				</s:if>
        		<s:else>
        			<li><a href="#" onclick="submitFormByCaseType(1)"><span>已读通知</span></a></li>
        		</s:else>
          		
            </ul>
			
			<ul id="urlist2" style="float:right">
         		 <li msg-type="5" ><a href="newNotice.action"  ><span>撰写</span></a></li>
				 <li msg-type="1" ><a href="#" onclick="submitFormByMsgType('1')"><span>收件箱</span></a></li>
				 <li msg-type="2" ><a href="#" onclick="submitFormByMsgType('2')"><span>发件箱</span></a></li>
				 <li msg-type="3" ><a href="#" onclick="submitFormByMsgType('3')"><span>已发送</span></a></li>
				 <li msg-type="4" ><a href="#" onclick="submitFormByMsgType('4')"><span>废件箱</span></a></li>
           </ul>
			
			
        </div>
        <!--Tabs_2 End-->
        <!--Filter-->
        
      <div class="filter">
      
      		<div class="query p8">
        	
        	
        	
			
        	
        	<s:form action="showList" id="form" name="showList" namespace="/notice" method="post">
        	<input type="hidden" id="caseType" name="caseType" value="<s:property value='msgUsermessage.caseType'/>">
			<input type="hidden" id="msgType" name="msgType" value="<s:property value='msgUsermessage.msgType'/>">
        	<input type="hidden"  value="" name="number" id="pageNo"/>
        	
        	
        	
        	
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	    <tr>
        	      <s:if test="(msgUsermessage.msgType==1 || msgUsermessage.msgType==4)">
        	       <td class="t_r" style="white-space:nowrap;">发件人</td>	  
        	       <td><input type="text" id="sender" name="msgUsermessage.sender" class="input_large" value="<s:property value="msgUsermessage.sender"/>"/></td>
        	      </s:if>
				  
				 <s:if test="(msgUsermessage.msgType==2 || msgUsermessage.msgType==3 || msgUsermessage.msgType==4)">
				   <td class="t_r" style="white-space:nowrap;">收件人</td>	  
        	       <td><input type="text" id="sender" name="msgUsermessage.receiver" class="input_large" value="<s:property value="msgUsermessage.receiver"/>"/></td>
				 </s:if>
				  
				  <td class="t_r" style="white-space:nowrap;">标题</td>
        	      <td>
        	      
      	          <input type="text" name="msgUsermessage.title" id="title" class="input_large" value="<s:property value="msgUsermessage.title"/>"/>
   	        	  </td>
                  
                   <td class="t_r" style="white-space:nowrap;">发件时间</td>
        	      <td style="white-space:nowrap;"><input type="text" id="beginDate" name="msgUsermessage.beginDate"   style="width:116px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="msgUsermessage.beginDate"/>" readonly/>
        	      至<input type="text" id="endDate" name="msgUsermessage.endDate" style="width:116px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="msgUsermessage.endDate"/>" readonly="true"/>
        	      </td>
      	        </tr>
        	    
      	       
        	    <tr>
        	      <td colspan="6" class="t_c">
                  	<input type="submit" value="检 索" />&nbsp;&nbsp;
					<input type="button" value="重 置" onclick="clearInput()"/></td>&nbsp;&nbsp;
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
      	          <th colspan="15">消息列表</th>
      	        </thead>
	 	                        <tbody>
	 	                          <tr class="tit">
	 	                            <td>状态</td>
	 	                            <s:if test="(msgUsermessage.msgType==1 || msgUsermessage.msgType==4)">
										<td>发件人</td>
	 	                            </s:if>

									<s:if test="(msgUsermessage.msgType==2 || msgUsermessage.msgType==3 || msgUsermessage.msgType==4)">
										<td>收件人</td>
									</s:if>
									<td>标题</td>
                                    <td>发送时间</td>
	 	                            <td>操作</td>
	                            </tr>
	                            
	                            <s:iterator value="#request.list" id="items" status="s">
                                
	 	                          <tr>
	 	                            <td class="t_c" id="STATES"  style="width:150px;">
                                    	<s:if test="#items[3]==0">
											未读
										</s:if>
										<s:elseif test="#items[3]!=0">
											已读
										</s:elseif>
                                    </td>
									<s:if test="(msgUsermessage.msgType==1 || msgUsermessage.msgType==4)">
                                    <td class="t_c" id="SENDER"  style="width:150px;text-align:center">
											<s:property value="#items[0]"/>
									</td>
									</s:if>
									
									<s:if test="(msgUsermessage.msgType==2 || msgUsermessage.msgType==3 || msgUsermessage.msgType==4)">
                                    <td class="t_c" id="SENDER"  style="width:150px;text-align:center">
											<s:property value="#items[1]"/>
									</td>
									</s:if>
									
									
                                    <td class="t_c" id="TITLE" style="text-align:left;"   ><s:property value="#items[4]"/></td>
	 	                            <td class="t_c" id="SEEDATE" style="width:150px;"><s:property value="#items[5]"/></td>
	 	                            <td class="t_c" style="width:100px; text-align:center;" align="center">
										<a class="fl mr5" href="javascript:void(0);" onclick="showDetail('<s:property value='#items[8]'/>')" >查看</a> 
										<a class="fl mr5 lnk-del" sid="<s:property value='#items[8]'/>" style="cursor:pointer;" >删除</a> 
	  	                             </td>
	                            </tr>
	                            </s:iterator>
	                            
	                          </tbody>
  	                          
      	       <tr class="tfoot">
                                <td colspan="15"><div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
                           		 <span class="fl">
                                  <s:property value="#request.page.totalSize"/>条记录，当前显示<s:property value="#request.startNo"/> -
                                  <s:if test="#r.pageInfo.totalPage==#r.pageInfo.currentPage">
                                    <s:property value="#r.pageInfo.totalRow"/>条
                                 </s:if>
                                  <s:else>
                                    <s:property value="#request.endNo"/>条
                                  </s:else>
                                </span>
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
<script>
	$(function(){
		var msgType="<s:property value='msgUsermessage.msgType'/>";
		var caseType="<s:property value='msgUsermessage.caseType'/>";
		if(msgType=="1"){
			$("#urlist").css("display","inline");
		}
		
		$(document).delegate(".lnk-del", "click", function (e) {
			var event = e || window.event,
			o = (event.srcElement || event.target),
			id=$(o).attr("sid");

			if (!confirm("确定删除?")) {
				return;
			}
			
			$.ajax({
				url : 'deleteItem.action',
				data : {
					sid : id,
					msgType:msgType
				},

				type : "POST",
				dataType : 'json/xml/html/text',
				cache : false,
				async : false,
				success : function (data) {
					alert("删除成功！");
					document.forms[0].submit();
				},
				error : function (data) {
					alert("删除成功！");
					document.forms[0].submit();
				}
			});

		});
		
		var lst = $("#urlist2 li");
		for(i=0;i<lst.length;i++){
			var o = lst[i];
			var type =$(o).attr("msg-type");
			if(type==msgType){
				$(o).addClass("selected");
			}
		}
		
		
	});
</script>
</html>
