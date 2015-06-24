<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html lang="en">
<head>
<title>资产清册</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../js/datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<!--[if IE 6.0]>
    <script src="js/iepng.js" type="text/javascript"></script>
    <script type="text/javascript">
         EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
    </script>
<![endif]-->
<script src="../js/html5.js"></script>
<script src="../js/jquery-1.7.1.js"></script>
<script src="../js/jquery.formalize.js"></script>
<script src="../js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<% String basePath = request.getContextPath();  %>
<script type="text/javascript">
function crossDomainShowOrHide(){
	//$("#iframe").attr("src","http://10.1.14.20:8088/portal/portal.jsp?random=Math.random()");
	$("#iframe").attr("src","http://10.1.40.201:8088/portal/portal.jsp?random=Math.random()");
}  
</script>

<style>
	.ui-autocomplete {
		max-height: 150px;
		overflow-y: auto;
		/* prevent horizontal scrollbar */
		overflow-x: hidden;
		/* add padding to account for vertical scrollbar */
		padding-right: 20px;
		max-width: 300px;
		width: 300px;
	}
	/* IE 6 doesn't support max-height
	 * we use height instead, but this forces the menu to always be this tall
	 */
	* html .ui-autocomplete {
		height: 150px;
	}
</style>

<script type="text/javascript">
$(document).ready(function () {
	//
	var $tbInfo = $(".filter .query input:text");
    $tbInfo.each(function () {
         $(this).focus(function () {
             $(this).attr("placeholder", "");
         });
    });
		
	var h1 = $("dl").width();
	var h2 = $("dt").width();
	$("dd").width(h1-h2-10);

	$(window).resize(function() {
    	var h1 = $("dl").width();
		var h2 = $("dt").width();
		$("dd").width(h1-h2-10);
	});
	var $tblAlterRow = $(".table_1 tbody tr:even");
	if ($tblAlterRow.length > 0)
	$tblAlterRow.css("background","#fafafa");
	
	
});
    
    
</script>
 
 <script type="text/javascript">
 function showChildName(id){
 	$.ajax({
			type : 'post',
			url : '<%=basePath%>/asset/findTypeAndValueByCodeInfoId.action?random='+Math.random(),
			dataType:'json',
			cache : false,
			data:{
				id:id
			},
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				$("#mid1").show();
				var option="";
				if(object!=null && object.length>1){
					for(var i=0; i<object.length; i++){
						option +="<li id="+object[i].id+"><a href='showInventory.action?type1="+id+"&type2="+object[i].id+"' onclick=''>"+object[i].name+"(<span color='#ED6D00'>"+object[i].value+"</span>)</a></li>"; 
					}
				}
				$("#mid2").html(option);
			}	
		});
		$("#ul").children(".selected").removeClass();
		$("#"+id).addClass("selected");
		
 }
 
 </script>
 
 <style type="text/css">
#contractTypeList ul li a {
	display:inline;
	margin: 5px;
}
</style>
 
</head>

<body>
<iframe id="iframe" style="display:none;"></iframe>	
	<div class="main"><!--Ctrl-->
		<div class="ctrl clearfix">
			<div class="fl"><img src="../images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起" onclick="crossDomainShowOrHide();"></div>
			<div class="posi fl">
				<ul>
					<li><a href="#">资产管理</a></li>
					<li class="fin">资产清册</li>
				</ul>
			</div>
		</div>
		<div class="filter">
			<div class="query" id="quickSearch">
				<div class="p8 stat_3">
					<dl class="clearfix" id="dl1">
						<dt>资产类型：</dt>
						<dd>
							<ul id ="ul">
							<!--  
								<li class="selected" id="00"><a href="#" onclick="">全部(0)</a></li>-->
								<s:iterator value="#request.type1List" id="type1">
									<li id="<s:property value="#type1.id"/>"><a href="#" onclick="showChildName(<s:property value="#type1.id"/>)" name="queryCompany"><s:property value="#type1.name"/>(<span><s:property value="#request.map.get(#type1.typeId+'/'+#type1.code)"/></span>)</a></li>
								</s:iterator>
							</ul>
						</dd>
					</dl>
					<dl class="clearfix" style="display:none;" id="mid1">
						<dt>中类：</dt>
						<dd>
							<ul id="mid2">
							</ul>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>所属线路：</dt>
						<dd>
							<ul>
								<!-- <li class="selected" id="00"><a href="#" onclick="">全部(0)</a></li>-->
								<s:iterator value="#request.lineList" id="line">
									<li id="<s:property value="#line.id"/>"><a href="showInventory.action?routeNum=<s:property value="#line.id"/>" onclick="" name="queryCompany"><s:property value="#line.name"/>(<span><s:property value="#request.map.get(#line.typeId+'/'+#line.code)"/></span>)</a></li>
								</s:iterator>
							</ul>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>是否入册：</dt>
						<dd>
							<ul>
								<li id="1"><a href="showInventory.action?registry=1" onclick="" name="queryCompany">已入册</a>(<span><s:property value="#request.map.get('1/1')"/></span>)</li>
								<li id="2"><a href="showInventory.action?registry=0" onclick="" name="queryCompany">待审核</a>(<span><s:property value="#request.map.get('1/0')"/></span>)</li>
							</ul>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt>权属单位：</dt>
						<dd>
							<ul>
								<!-- <li class="selected" id="00"><a href="#" onclick="">全部(0)</a></li>-->
								<s:iterator value="#request.unitList" id="unit">
									<li id="<s:property value="#unit.id"/>"><a href="showInventory.action?ownerDuty=<s:property value="#unit.id"/>" onclick="" name="queryCompany"><s:property value="#unit.name"/>(<span><s:property value="#request.map.get(#unit.typeId+'/'+#unit.code)"/></span>)</a></li>
								</s:iterator>
							</ul>
						</dd>
					</dl>
				</div>
			</div>
		</div>
		<!--Filter End--> <!--Table-->
		<!--Table End-->
	</div>
</body>
</html>
