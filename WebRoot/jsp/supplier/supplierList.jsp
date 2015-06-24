<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>供应商管理</title>
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

</script>
 
 
<script type="text/javascript">
//定义子窗口
var sonWindow = null;
//每1秒执行一次checkSonWindow()方法
var refresh = setInterval("checkSonWindow()",1000);
 //监测子窗口是否关闭
function checkSonWindow(){
	if(sonWindow!=null && sonWindow.closed==true){
		window.location.href = "showList.action";
		clearInterval(refresh);
	}
}

//跳转到新增页面
function showAddPage(){
	sonWindow = window.open('showAdd.action');
}

//跳转到编辑页面
function showEditPage(id){
	sonWindow = window.open('showEdit.action?id='+id);
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

//清除表单数据
function clearSearch(){
	$("#search td input:lt(3)").val("");
	$("#pageNo").val(1);
}

</script> 
 
 
 
<script type="text/javascript">
//删除
function deleteData(id){
	
	if(confirm("是否删除？")){
		$.ajax({
			type : 'post',
			url : 'delete.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("form").submit();
			}
		});
	}
}

$(function(){
	var status = $("input[name=showOrHide]").val();
	if(status=="hide")
		$("#search").css("display","none");
	else
		$("#search").css("display","block");
		

});

//控制显示或隐藏查询条件
function showOrHideControl(obj){
	var status = $("input[name=showOrHide]").val();
	var $li = $(obj).parent();
	if(status=="hide"){
		$li.addClass("selected");
		$("#search").slideDown();
		$("input[name=showOrHide]").val("show");
	}else{
		$li.removeClass("selected");
		$("#search").slideUp();
		$("input[name=showOrHide]").val("hide");
	}
}
$(function(){
	 $("img").toggle(
  			function () {$("img").attr("src","../images/sideBar_arrow_right.jpg");},
  			function () {$("img").attr("src","../images/sideBar_arrow_left.jpg");}
	 );
});

</script>
 
 
 
 
</head>

<body>
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
		<div class="fl"><img src="../images/sideBar_arrow_left.jpg"width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
		<div class="posi fl">
			<ul>
				<li><a href="#">资产管理</a></li>
				<li class="fin">供应商管理</li>
			</ul>
		</div>
		<div class="fr lit_nav">
			<ul>
				<li><a class="print" href="#">打印</a></li>
				<li><a class="storage" href="#">网络硬盘</a></li>
				<li><a class="rss" href="#">订阅</a></li>
				<li><a class="chat" href="#">聊天</a></li>
				<li><a class="query" href="#" onclick="showOrHideControl(this);">查询</a></li>
			</ul>
		</div>
		</div>
		<div class="filter">
			<div class="query p8" id="search">
			<s:form action="showList" name="supplier" namespace="/supplier" method="post" id="form">
			<input type="hidden" name="showOrHide" value="<s:property value="#request.showOrHide"/>">
			<input type="hidden" name="pageNo" id="pageNo" value="<s:property value='#request.page.currentPageNo'/>"/>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr id="search">
						<td class="t_r" style="white-space:nowrap">厂商名称</td>
						<td style="white-space:nowrap">
							<input type="text" name="name" id="textfield" class="input_large" value="<s:property value='supplier.name'/>" />
						</td>
						<td class="t_r" style="white-space:nowrap">法人代表</td>
						<td style="white-space:nowrap">
							<input type="text" name="legalPerson" id="textfield" class="input_large" value="<s:property value='supplier.legalPerson'/>" >
						</td>
						<td class="t_r" style="white-space:nowrap">联系地址</td>
						<td style="white-space:nowrap">
							<input type="text" name="address" id="textfield" class="input_large" value="<s:property value='supplier.address'/>" >
						</td>
					</tr>
					<tr>
						<td colspan="6" class="t_c">
							<input type="submit" value="检 索" />&nbsp;&nbsp; 
							<input type="button" value="重 置" onclick="clearSearch();"/>
						</td>
					</tr>
				</table>
			</s:form>
			</div>
			<div class="fn">
				<input type="button"" name="button2" id="button2" value="新增" class="new" onclick="showAddPage();">
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<div class="mb10">
		<table width="100%" class="table_1">
			<thead>
				<th colspan="12">&nbsp;&nbsp;供应商列表</th>
			</thead>
			<tbody>
				<tr class="tit">
					<td width="5%;" style="white-space:nowrap">序号</td>
					<td width="10%;" class="t_r" style="white-space:nowrap">厂商名称</td>
					<td width="5%;" class="t_r" style="white-space:nowrap">法人代表</td>
					<td width="10%;" class="t_r" style="white-space:nowrap">法人代表电话</td>
					<td width="5%;" class="t_r" style="white-space:nowrap">联系人</td>
					<td width="10%;" class="t_r" style="white-space:nowrap">联系电话</td>
					<td width="20%;" class="t_r" style="white-space:nowrap" >联系地址</td>
					<td width="8%;" class="t_r" style="white-space:nowrap">邮政编码</td>
					<td width="6%;" class="t_r" style="white-space:nowrap">厂商类型</td>
					<td width="12%;" class="t_r" colspan="3" style="white-space:nowrap">操作</td>
				</tr>
				<s:iterator value="#request.page.result" id="supplier" status="st">
					<tr>
						<td class="t_c" style="word-break:break-all"><s:property value="#request.page.start+#st.index"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#supplier.name"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#supplier.legalPerson"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#supplier.legalPersonTel"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#supplier.contactPerson"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#supplier.contactTel"/></td>
						<td class="t_c" style="word-break:break-all"><s:property value="#supplier.address"/></td>
						<td class="t_c"><s:property value="#supplier.zip"/></td>
						<td class="t_c" style="word-break:break-all">
							<s:if test="#supplier.type==0">生产厂商</s:if>
							<s:else>供应商</s:else>
						</td>
						<td class="t_c">
							<a href="showView.action?id=<s:property value="#supplier.id"/>" style="float:inline;" target="_blank">查看</a>
						</td>
						<td class="t_c">
							<a href="javascript:void(0);" onclick="showEditPage(<s:property value="#supplier.id"/>);" style="float:inline;">编辑</a>
						</td>
						<td class="t_c">
							<a href="#" style="float:inline;" onclick="deleteData('<s:property value="#supplier.id"/>');">删除</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
			
			<tr class="tfoot">
			      <td colspan="12"><div class="clearfix">
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
		<!--Table End-->
	</div>
</body>
</html>
