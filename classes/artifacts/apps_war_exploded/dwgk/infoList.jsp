<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.stpt.cms.CmsInfoApi"%>
<%@ page import="com.wonders.stpt.cms.StringUtil"%>
<%
String channelId=request.getParameter("channel");
String pageNum=request.getParameter("page");
if(pageNum==null){
	pageNum="1";
}
int nTotalNum=0;
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path; 
CmsInfoApi dwgk=new CmsInfoApi(request,response);

JSONObject channelObj=null;
JSONArray channelList=dwgk.getInfoList("JEECMS.GET_CHANNEL_LIST","{'channelId':'"+channelId+"'}");
if(channelList!=null){
	channelObj=channelList.getJSONObject(0);
	nTotalNum=channelObj.getInt("contentNum");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="../css/dwgk/index.css" rel="stylesheet" type="text/css" />
<link href="../css/dwgk/reset.css" rel="stylesheet" type="text/css" />
<script src="../js/dwgk/jquery-1.5.1.min.js"></script>
<script src="../ui/jquery.ui.core.js"></script>
<script src="../ui/jquery.ui.widget.js"></script>
<script>


	</script>
</head>
<body>
    <div class="gzxx">
    <h3><a href="#"><em><%=channelObj.getString("channelName") %></em></a></h3>
    <div class="gzxx-info">
      <ul class="other">
<% 
String shortTitle="";
JSONArray recList=null;
JSONObject rec=null;
recList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+channelId+"','page':'"+pageNum+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		shortTitle=rec.getString("shortTitle");
		if(shortTitle.equals("null")){
			shortTitle=rec.getString("title");
		}
%>
      <li><a title="<%=shortTitle %>" href="contentDetail.jsp?content=<%=rec.getString("contentId") %>" class="news_list" target="_blank"><%=StringUtil.MySubstring(rec.getString("title") ,60) %></a><em><%=rec.getString("releaseDate") %></em></li>
  <% 
	}
}
  %>
      </ul>
      </div>
    <div class="next">
<%
int nCurrentPageNum=Integer.valueOf(pageNum);
int nTotalPage=(nTotalNum/10)+(nTotalNum%10==0?0:1);
if(nTotalPage>0){
	if(nCurrentPageNum>1){
%>
	<a href='infoList.jsp?channel=<%=channelId %>&page=<%=nCurrentPageNum-1 %>' class="new_pages">[上一页]</a>&nbsp;&nbsp;
<%	
	}
	for(int p=1;p<=nTotalPage;p++){
		if(p==nCurrentPageNum){
%>
	[<%=p %>]&nbsp;&nbsp;
<%		
		}else{
%>
	<a href='infoList.jsp?channel=<%=channelId %>&page=<%=p %>' class="new_pages">[<%=p %>]</a>&nbsp;&nbsp;
<%		
		}
	}
	if(nCurrentPageNum<nTotalPage){
%>
	<a href='infoList.jsp?channel=<%=channelId %>&page=<%=nCurrentPageNum+1 %>' class="new_pages">[下一页]</a>&nbsp;&nbsp;
<%	
	}
%>
	当前页 <input id='txtPageNo' name='txtPageNo' type='text' style='FONT-SIZE: 9pt;height:16px;line-height:9px;width:24px;' class='input5' size='3' value='<%=pageNum %>'>/<%=nTotalPage %><a href= "#" class="new_pages" onclick="page();">[转到]</a>
	<script>
	function page(){
		var txtPageNo = document.getElementById('txtPageNo').value;
		if(txtPageNo!=null&&txtPageNo.length>0){
			var pageNo = parseInt(txtPageNo);
			if(pageNo>=1&&pageNo<=<%=nTotalPage%>){
				window.location.href='infoList.jsp?channel=<%=channelId%>&page='+pageNo;
			}else{
				if(pageNo<0){
					window.location.href='infoList.jsp?channel=<%=channelId%>';
				}else{
					window.location.href='infoList.jsp?channel=<%=channelId%>&page=<%=nTotalPage%>';
				}
			}
		}else{
			alert("请输入页数");
		}
	}
	</script>

<%	
}

%>
	</div>
  </div>

   <div class="f-both"></div>
</body>
</html>
<% 
}else{
	response.sendRedirect(basePath);
}
%>
