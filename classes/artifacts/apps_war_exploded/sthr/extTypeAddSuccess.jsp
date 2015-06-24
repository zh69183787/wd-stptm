<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="cw" uri="/widget-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<cw:base></cw:base>
</head>
<body>
<script type="text/javascript">
	  alert("保存成功");
	    window.opener=null;	
	    window.open("","_self");
	    window.close();
	  
	</script>
	<div id="headPanel"></div>
	<div id="centerDiv"></div>
	<div id="menuPanel"></div>
	<cw:composite title="" layout="region" border="false" autoHeight="true" autoWidth="true" container="auto">
		<cw:panel title="" region="north" contentId="headPanel"></cw:panel>
		<cw:panel title="" region="west" contentId="menuPanel"></cw:panel>
		<cw:panel title="" region="center" contentId="centerDiv"></cw:panel>
	</cw:composite>
</body>
</html>