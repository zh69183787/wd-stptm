<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<!DOCTYPE html >
<html lang="en">
<head>
<meta charset="utf-8" />
<title>设备编辑</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
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
<!--<script src="../js/show.js"></script>-->
<script type="text/javascript" src="../ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>
<%String basePath = request.getContextPath(); %>
<style type="text/css">
	.must{
		color:red;
	}
</style>
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
$(function(){
	$(".odd tr:odd").css("background","#fafafa");
	
	$("input[name=useDate]").datepicker({
		inline: true,
		changeYear:true,
		changeMonth:true,
		showOtherMonths: true,
		selectOtherMonths: true
	});	
	
	//loadShow();
			
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
	
	if(!isEmpty($("#errorInfo").val())){
		alert($("#errorInfo").val());
		$("#assetId").focus();
	}
});



//表单检验
function checkForm(){

	var value ;
	
	//资产编号
	value = $("#assetId").val();
	if(isEmpty(value)){
		alert("请填写资产编号！");
		$("#assetId").focus();
		return false;
	}else{
		var reg = /^\d{20}$/;
		if(!reg.test(value)){
			alert("资产编号为20位数字！");	
			$("#assetId").focus();
			return false;	
		}
	}
	//设备编号
	value = $("input[name=equipmentId]").val();
	if(isEmpty(value)){
		alert("请填写设备编号！");
		$("input[name=equipmentId]").focus();
		return false;
	}
	//设备名称
	value = $("input[name=equipmentName]").val();
	if(isEmpty(value)){
		alert("请填写设备名称！");
		$("input[name=equipmentName]").focus();
		return false;
	}
	
	//使用寿命
	value = $("input[name=usefulLife]").val();
	if(!isEmpty(value) && value != "0"){
		var reg = /^[1-9]{1}\d*$/
		if(!reg.test(value)){
			alert("请输入正确的数据，只能填写数字！");
			$("input[name=usefulLife]").focus();
			return false;		
		}
	}
	
	//大修频率
	value = $("input[name=heavyRepairFrequency]").val();
	if(!isEmpty(value) && value != "0"){
		var reg = /^[1-9]{1}\d*$/
		if(!reg.test(value)){
			alert("请输入正确的数据，只能填写数字！");
			$("input[name=heavyRepairFrequency]").focus();
			return false;		
		}
	}
	
	//中修频率
	value = $("input[name=mediumRepairFrequency]").val();
	if(!isEmpty(value) && value != "0"){
		var reg = /^[1-9]{1}\d*$/
		if(!reg.test(value)){
			alert("请输入正确的数据，只能填写数字！");
			$("input[name=mediumRepairFrequency]").focus();
			return false;		
		}
	}
	
	//单价
	value = $("input[name=price]").val();
	if(!isEmpty(value) && value != "0"){
		var reg = /^[1-9]{1}\d*$/
		if(!reg.test(value)){
			alert("请输入正确的数据，只能填写数字！");
			$("input[name=price]").focus();
			return false;		
		}
	}
	//生产商
	value = $("#hideManufacturer").val();
	if(isEmpty(value)){
		alert("该生产商不存在");
		$("#showManufacturer").focus().select();
		return false;
	}
	
	$("#formBody tr td input").each(function(){
		var text = $(this).val();  
		$(this).val(
			text.replace(/(^\s*)|(\s*$)/g,'')
		);
	});
	var textarea = $("#formBody tr td textarea").val();
	$("#formBody tr td textarea").val(textarea.replace(/(^\s*)|(\s*$)/g,''));
	
	return true;
}

//添加参数
function addParams(){
	var addHtml = "<tr>"+
					"<td><input type='checkbox' style='float: right;' name='checkParams'/></td>"+
					"<td colspan='1' style='width: 500px;'>"+
						"参数名称：<input type='text' name='paramName'>"+
					"</td>"+
					"<td colspan='3'>"+
						"参数值：<input type='text' name='paramValue'>"+
					"</td>"+
				"</tr>";
					
	$("#formBody").append(addHtml);
}
//删除参数
function removeParams(){
	$("input[name=checkParams]").each(function(){
		if($(this).attr("checked")=="checked"){
			$(this).parent().parent().remove();
		}
	});
}


$(function(){
	var saveStatus2 = false;
	//生产商
	$("#showManufacturer" ).autocomplete({
		autoFouces : true,
		source: function( request, response ) {
			var dataParams = "<?xml version=\"1.0\" encoding=\"utf-8\"?><dataParams><userName>"+request.term.replace(/(^\s*)|(\s*$)/g,'')+"</userName></dataParams>";
			$.ajax({
				url: "<%=basePath%>/supplier/findSupplierByNameAndType.action",
				dataType: "json",
				data: {
					"type":"0",
					"name":request.term,
					"random" : Math.random()
				},
				success: function( data,obj,c ) {
					response( $.map( data, function( item,index ) {
						return {
							label: item.name,
							value: item.id
						}
					}));
				}
			});
		},
		minLength: 1,
		search:function(){
			saveStatus2 = false;
		},
		select: function( event, ui ) {
			$("#hideManufacturer").val(ui.item.value);
			$("#showManufacturer").val(ui.item.label.split("(")[0]);
			saveStatus2 = true;
			return false;
		},
		open: function() {
			$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
		},
		close: function() {
			$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
		},
		focus: function( event, ui ) {
				$( "#hideManufacturer" ).val("");
				return false;
		},
		change : function(){
			if(!saveStatus2){
				$("#hideManufacturer").val("");
			}
		}
	});
});

//是否是空
function isEmpty(value){
	if(value==null)
		return true;
	return trim(value)=="";
}
//删除左右两端的空格
function trim(str){
	return str.replace(/(^\s*)(\s*$)/g,"");
}


//清除
function clearInput(){
	$("#formBody input").val("");
}

</script>






</head>

<body>
<%
String path = request.getContextPath();
Map<String, String> cookieMap = new HashMap<String, String>();
Cookie[] cookies = request.getCookies();
String token=null;
if(cookies !=null){
	for(int i=0;i<cookies.length;i++){
		Cookie cookie = cookies[i];
		cookieMap.put(cookie.getName(), java.net.URLDecoder.decode(cookie.getValue(),"utf-8"));
		if("token".equals(cookie.getName())){
			try{
				token = java.net.URLDecoder.decode(cookie.getValue(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
}

String configPath = Thread.currentThread().getContextClassLoader().getResource("config.properties").getPath();
Properties props = new Properties();
try {
	props.load(new FileInputStream(configPath));
} catch (FileNotFoundException e) {
	System.out.println("配置文件没有找到！");
} catch (IOException e) {
	System.out.println("读取配置文件失败！");
}
String appName = request.getContextPath().substring(1);
String secret= props.getProperty("secret");
if(token==null){
	token=appName+secret;
}
%>
<input type="hidden" name="appName" value="<%=appName%>" />
<input type="hidden" name="token" value="<%=token%>" />
<input type="hidden" name="method" value="getMatchedDeptUsers" />
<input type="hidden" name="secret" value="<%=secret%>" />
<input type="hidden" name="dataType" value="xml" />
<input type="hidden" name="dataParams" value="<%=token%>" />
<input type="hidden" value="<s:property value="#request.errorInfo" />" id="errorInfo"/>


<div class="main"><!--Ctrl-->
<div class="ctrl clearfix">
<div class="fl"><img src="../images/sideBar_arrow_left.jpg"
	width="46" height="30" alt="收起" ></div>
<div class="posi fl">
<ul>
	<li><a href="#">资产管理</a></li>
	<li class="fin">设备编辑</li>
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
<s:form action="saveEdit" name="equipment" namespace="/equipment" method="post">
<input type="hidden" value="<s:property value="equipmentInfo.id"/>" name="id"/>
	<table width="100%" class="table_1">
		<thead>
			<th colspan="4" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="重 置" onclick="clearInput();"/> &nbsp;
			</th>
		</thead>
		<tbody id="formBody">
			<tr>
				<td class="t_r lableTd">资产编号</td>
				<td colspan="3" style="color: red;">
					<input type="text" id="assetId" name="assetId" class="input_large" value="<s:property value="equipmentInfo.assetId"/>" maxlength="20"/>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td class="t_r lableTd">设备编号</td>
				<td colspan="3" style="color: red;">
					<input type="text" name="equipmentId" class="input_large" value="<s:property value="equipmentInfo.equipmentId"/>"/>
					&nbsp;&nbsp;*
				</td>
			</tr>
			<tr>
				<td width="10%;" class="t_r lableTd">设备名称</td>
				<td width="40%;"><input type="text" name="equipmentName" class="input_large" value="<s:property value="equipmentInfo.equipmentName"/>"/></td>
				<td width="10%;" class="t_r lableTd">规格型号</td>
				<td width="40%;"><input type="text" name="productModel" class="input_large" value="<s:property value="equipmentInfo.productModel"/>"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">产地</td>
				<td><input type="text" id="" name="provenance" class="input_large" value="<s:property value="equipmentInfo.provenance"/>"/></td>
				<td class="t_r lableTd">使用日期</td>
				<td><input type="text" id="" name="useDate" class="input_large date" readonly="readonly" value="<s:property value="equipmentInfo.useDate"/>"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">使用寿命</td>
				<td><input type="text"  name="usefulLife" class="input_large" value="<s:property value="equipmentInfo.usefulLife"/>"/>月</td>
				<td class="t_r lableTd">安装位置</td>
				<td><input type="text"  name="installSite" class="input_large" value="<s:property value="equipmentInfo.installSite"/>"/></td>
			</tr>
			<tr>
				<td class="t_r lableTd">大修频率</td>
				<td><input type="text" id="" name="heavyRepairFrequency" class="input_large" value="<s:property value="equipmentInfo.heavyRepairFrequency"/>"/>月/次</td>
				<td class="t_r lableTd">中修修频率</td>
				<td><input type="text" id="" name="mediumRepairFrequency" class="input_large" value="<s:property value="equipmentInfo.mediumRepairFrequency"/>"/>月/次</td>
			</tr>
			<tr>
				<td class="t_r lableTd">生产厂家</td>
				<td class="must">
					<input id="hideManufacturer" name="manufacturer" type="hidden" value="<s:property value="#request.supplier.id"/>">
					<input id="showManufacturer" type="text" class="input_xxlarge" name="manufacturerName" value="<s:property value="#request.supplier.name"/>">
					&nbsp;&nbsp;*
				</td>
				<td class="t_r lableTd">单价</td>
				<td><input type="text" id="" name="price" class="input_large" value="<s:property value="equipmentInfo.price"/>"/>元</td>
			</tr>
			<tr>
				<td class="t_r lableTd">备注</td>
				<td colspan="3">
					<textarea rows="5" style="width: 500px;" name="notes"><s:property value="equipmentInfo.notes"/></textarea>
				</td>
			</tr>
			<s:iterator value="#request.paramterList" id="parameter">
				<tr>
					<td class="t_r lableTd">参数名称<input type="hidden" name="parameterId" value="<s:property value="#parameter.id"/>"></td>
					<td>
						<s:property value="#parameter.parameterName"/>
					</td>
					<td class="t_r lableTd">
					      参数值
					</td>
					<td colspan="3">
						<input type="text" name="paramValue" value="<s:property value="#parameter.parameterValue"/>">
					</td>
				</tr>
			</s:iterator>
		</tbody>
		<tr class="tfoot">
			<td colspan="6" class="t_r">
				<input type="submit" value="确 认" onclick="return checkForm();"/>&nbsp; 
				<input type="button" value="关 闭" onclick="window.close();"/> &nbsp; 
				<input type="button" value="重 置" onclick="clearInput();"/>&nbsp;
			</td>
		</tr>
	</table>
</s:form>
</div>
<!--Table End--></div>
</body>
</html>
