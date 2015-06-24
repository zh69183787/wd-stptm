<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.stpt.cms.CmsInfoApi"%>
<%@ page import="com.wonders.stpt.cms.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
String searchWord = request.getParameter("searchWord");
String channelId=request.getParameter("channel");
String pageNum=request.getParameter("page");
if(pageNum==null){
	pageNum="1";
}

int nTotalNum=0;
int pageSize=10;

CmsInfoApi dwgk=new CmsInfoApi(request,response);
JSONObject channelObj=null;
JSONArray channelList=dwgk.getInfoList("JEECMS.SEARCH_CONTENT_NUM","{'channelId':'"+channelId+"','title':'"+searchWord+"'}");
if(channelList!=null){
	channelObj=channelList.getJSONObject(0);
	nTotalNum=channelObj.getInt("contentNum");
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>党务公开-高级搜索</title>
<link href="../css/dwgk/index2.css" rel="stylesheet" type="text/css" />
<link href="../css/dwgk/reset2.css" rel="stylesheet" type="text/css" />
<!--script src="include/WebCalendar.js" type="text/javascript"></script-->
</head>
<body class="scroll-barandbackground">
<form action="search.jsp" method="post" name="searchForm">
<input type="hidden" name = "channel" value="<%=channelId%>" />
<input type="hidden" name = "page" value="1"/>
<div class="top"> <img src="../images/dwgk/top.jpg"  /> </div>
<div class="place"><span>当前位置：<em>申通地铁党务公开 > 高级搜索</em></span></div>
<div class="wrap">
  <div class="leftside">
    <div class="gzxx">
      <h3><a href="#"><em>全文检索</em></a></h3>
    </div>
    <div class="f-both"></div>
    <div class="search_info">
<%
JSONArray recList=null;
JSONObject rec=null;
String shortTitle="";
String description="";
String title="";
recList=dwgk.getInfoList("JEECMS.SEARCH_CONTENT_LIST","{'channelId':'"+channelId+"','title':'"+searchWord+"','page':'"+pageNum+"','rownum':'"+pageSize+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		title=rec.getString("title");
		shortTitle=rec.getString("shortTitle");
		if(shortTitle.equals("null")){
			shortTitle=title;
		}
		
		description=rec.getString("description");
		if(description.equals("null")){
			if(!rec.getString("txt").equals("null")){
				description=StringUtil.splitAndFilterString(rec.getString("txt"),150);
			}else{
				description=title;
			}
		}		
		title=title.replaceAll(searchWord,"<font color='red'>"+searchWord+"</font>");
		description=description.replaceAll(searchWord,"<font color='red'>"+searchWord+"</font>");
		
%>
    	<dl>
        	<dt><em><%=rec.getString("releaseDate") %></em>[<a title="<%=shortTitle %>" style="font-size: 14px;color: #003366; line-height:250%;text-decoration:none;" href="contentDetail.jsp?content=<%=rec.getString("contentId")%>" target="_blank" class="content_list"><%=title%></a>]<span><%=rec.getString("channelName")%></span></dt>
         	<dd><%=description%></dd>
            <p><a href="contentDetail.jsp?content=<%=rec.getString("contentId") %>" target="_blank" style="font-size: 12px;color: #19A537;text-decoration: none;"><%=basePath%>dwgk/contentDetail.jsp?content=<%=rec.getString("contentId")%></a></p>
        </dl>
<%
	}
}
%>    
        
        <div>
<div align=center>
<%
		int nTotalPage=(nTotalNum/pageSize)+(nTotalNum%pageSize==0?0:1);
		if(nTotalNum > pageSize){
%>
			[<a href="#" onclick="document.forms[0].page.value='1';document.forms[0].submit();">首页</a>]&nbsp;&nbsp;
<%			
			if(Integer.parseInt(pageNum) > 1){
%>			
			[<a href="#" onclick="document.forms[0].page.value='<%=(Integer.parseInt(pageNum) - 1) %>';document.forms[0].submit();">上一页</a>]&nbsp;&nbsp;
<%			
			}
			if(Integer.parseInt(pageNum)<nTotalPage){
%>			
			[<a href="#" onclick="document.forms[0].page.value='<%=(Integer.parseInt(pageNum) + 1) %>';document.forms[0].submit();">下一页</a>]
<%				
			}
%>
			&nbsp;&nbsp;[<a href="#" onclick="document.forms[0].page.value='<%=nTotalPage %>';document.forms[0].submit();">尾页</a>]
<%							
		}
%>
		&nbsp;&nbsp;当前页 <input name='txtPageNo' type='text' style='FONT-SIZE: 9pt;height:19px;' class='input5' size='3' value='<%=pageNum %>'>/<%=nTotalPage %>&nbsp;<a href="#" onclick="toPage()">转到</a>&nbsp;&nbsp;记录总数：<%=nTotalNum %>
		<script>
			function toPage(){
				var pageValue=document.forms[0].txtPageNo.value;
				if(parseInt(pageValue)><%=nTotalPage %>){
					document.forms[0].txtPageNo.value=<%=nTotalPage %>;
				}else if(parseInt(pageValue)<1){
					document.forms[0].txtPageNo.value=1;
				}
				document.forms[0].page.value=document.forms[0].txtPageNo.value;
				document.forms[0].submit();
			}
		</script> 
		</div>
</div>
        
    </div>
    
    
				
								
  </div>
   <div class="rightside add_mheight_bgc">
    <div class="search_r_box"> <em > 关键字查询</em>
       <p><span>关键字:</span><input type="text" name="searchWord" class="input2" value="<%=searchWord %>"/></p>           
	  <span class="btn_info"><input type="button" onclick="submitSearch()"  value="搜索" class="btn2 add_input_mar"/></span>
	  <script>
	  function submitSearch(){
	  	document.forms[0].page.value="1";
	  	document.forms[0].txtPageNo.value="1";
	  	document.forms[0].submit();
	  }
	  
	  </script>
    </div>
   </div>
  <div class="f-both"></div>

</div>
</form>
<br><br>
</body>
</html>

