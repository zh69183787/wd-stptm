<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.stpt.cms.CmsInfoApi"%>
<%@ page import="com.wonders.stpt.cms.StringUtil"%>
<%@ page import="java.util.Properties"%>
<%@ page import="java.io.FileInputStream"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort(); 



String contentId=request.getParameter("content");
CmsInfoApi dwgk=new CmsInfoApi(request,response);
String cmsUrl=dwgk.getUrlCms();
dwgk.getInfoList("JEECMS.ADD_CONTENT_VIEWER","{'contentId':'"+contentId+"'}");
	
JSONObject contentObj=null;
JSONArray contentList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'contentId':'"+contentId+"'}");
if(contentList!=null){
	contentObj=contentList.getJSONObject(0);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>
  	<%

  	if(!contentObj.getString("shortTitle").equals("null")){
  	%>
		<%= contentObj.getString("shortTitle")%>
  	<%
  	}else{
  	%>
		<%= contentObj.getString("title")%>
  	<%
  	}

   %>
</title>
<link href="../css/dwgk/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function winopen() { 
	window.open("viewerList.jsp?content=<%=contentId%>","_blank","location=no,width=400,height=500");
}
</script>
</head>
<body>
<center>
<div class="top"><img src="../images/dwgk/top.jpg"  /> </div>
<div class="wrap"> 
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
	    <td class="news_content_td2" align="center" style="text-align:center">
	    <br/>
	     <table width=100% border=0 cellpadding=0 cellspacing=0>
		     <tr>
		     		<th style=font-size:14px;color:#C82100><%=contentObj.getString("title") %></th>
		     </tr>
		     
		     <tr>
		     		<td style="font-size:12px;color:#666666;line-height:200%;"  align="center"><%=contentObj.getString("releaseDate") %>&nbsp;&nbsp;
		     	<%
		     	if(!contentObj.getString("origin").equals("null")){
		     	%>
					来源：<%=contentObj.getString("origin") %>
		     	<%
		     	}
		      %>
		     		</td>
		     </tr>
		     <tr height="20px">
		     	<td style="line-height:1px;"><div style="border-bottom:1px dashed #CDCDCD;"></div></td>
		     </tr>
		     <tr>
		     	<td height="2px">
		     	
		     	<%
		     	if(contentObj.getString("hasTitleImg").equals("1")){
		     	%>
		     	<div style="padding-top:5px;padding-bottom:10px"><img src="<%=cmsUrl%><%= contentObj.getString("titleImg")%>"/></div>
		     	<%
		     	}

		     	if(!contentObj.getString("description").equals("null")){
		     	%>
		     	<div align="left" style="background:#CCCCCC;padding-top:5px;padding-bottom:5px;padding-left:5px;"><FONT style="font-size:13px;color:#034278;line-height:200%" >
					&nbsp;&nbsp;&nbsp;&nbsp;<strong>摘要：</strong><%= contentObj.getString("description")%>
					</FONT>
					</div>
		     	<%
		     	}else if(!contentObj.getString("txt").equals("null")){
		     	%>
		     	<div align="left" style="background:#CCCCCC;padding-top:5px;padding-bottom:5px;padding-left:5px;"><FONT style="font-size:13px;color:#034278;line-height:200%" >
					&nbsp;&nbsp;&nbsp;&nbsp;<strong>摘要：</strong><%= StringUtil.splitAndFilterString(contentObj.getString("txt"),150)%>
					</FONT>
					</div>
		     	<%
		     	}
		      %>
	
		        			     			    		    
		     	</td>
		     </tr>
		     	<%
		     	if(contentObj.getString("hasTitleImg").equals("1")||!contentObj.getString("description").equals("null")){
		     	%>		     
		      <tr height="20px">
		     	<td style="line-height:1px;"><div style="border-bottom:1px dashed #CDCDCD;"></div></td>
		     </tr>
		     	<%
		     	}
		      %>	
		     	<%
		     	if(!contentObj.getString("txt").equals("null")){
		     	%>		      	     
	     	 <tr><td align="left"><div><FONT style="FONT-SIZE: 10.5pt"><%= contentObj.getString("txt")%></FONT></div></td></tr>
		     <tr height="20px">
		     	<td style="line-height:1px;"><div style="border-bottom:1px dashed #CDCDCD;"></div></td>
		     </tr>	 
		     	<%
		     	}
		      %>		         	 
<%

JSONObject viewerObj=null;
int nViewerNum=0;
JSONArray viewerList=dwgk.getInfoList("JEECMS.GET_VIEWER_NUM","{'contentId':'"+contentId+"'}");
if(viewerList!=null){
	viewerObj=viewerList.getJSONObject(0);
	nViewerNum=viewerObj.getInt("viewerNum");
}	   
	
 %>  	 

		     <tr height="20px">
		     	<td align="right">	   
		     		<a href="#" onclick="winopen()" class="news_content" style="cusor:hand; text-decoration:none; font-size:12px;" >已有<font color="red"><%=nViewerNum%></font>人浏览</a>
		     	</td>
		     </tr>  
	      	 
<% 
JSONArray fileList=null;
JSONObject fileObj=null;
String filePath="";
String imgType="";
fileList=dwgk.getInfoList("JEECMS.GET_FILE_LIST","{'contentId':'"+contentId+"'}");
if(fileList!=null){
%>
		     <tr height="20px">
		     	<td style="line-height:1px;"><div style="border-bottom:1px dashed #CDCDCD;"></div></td>
		     </tr>
		     <tr height="20px">
		     	<td align="left" style="font-size:14px;color:#454545;">【附件】
		     	</td>
		     </tr> 		     
<%
	for(int i=0;i<fileList.size();i++){
		fileObj=fileList.getJSONObject(i);
		filePath=fileObj.getString("filePath");
		if(filePath.indexOf(".bmp")!=-1||filePath.indexOf(".jpg")!=-1||filePath.indexOf(".gif")!=-1||filePath.indexOf(".png")!=-1){
			imgType="jpg";
		}else if(filePath.indexOf(".pdf")!=-1){
			imgType="pdf";
		}else if(filePath.indexOf(".xls")!=-1){
			imgType="excel";
		}else if(filePath.indexOf(".ppt")!=-1){
			imgType="powerpoint";
		}else if(filePath.indexOf(".rm")!=-1||filePath.indexOf(".avi")!=-1){
			imgType="mpeg";
		}else if(filePath.indexOf(".rar")!=-1||filePath.indexOf(".zip")!=-1){
			imgType="zip";
		}else{
			imgType="word";
		}
   %>
      <tr>
      	<td align="left" style="margin:0;padding-left:15px;">	   
      		<a href="<%=cmsUrl%><%=filePath %>" target="_blank"><img height="28px"   alt="<%=fileObj.getString("fileName") %>" src="../images/dwgk/<%=imgType%>.gif" align="absMiddle" border="0"><%=fileObj.getString("fileName") %></a>      		
      	</td>	  
	  </tr>	 
  <%
	}
}
  %>

		</table>
	  	<br/>
		</td>
</tr>
</table>
</div>
<br/>
             
<a href="javascript:window.close();" class="news_content" style="cusor:hand; text-decoration:none; font-size:12px;"> 【关闭窗口】 </a>
</div>
</center>
</body>
</html>
<% 
}else{
	response.sendRedirect(basePath+path);
}
%>