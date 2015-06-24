<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.stpt.cms.CmsInfoApi"%>
<%@ page import="com.wonders.stpt.cms.StringUtil"%>
<%
String path = request.getContextPath();
CmsInfoApi dwgk=new CmsInfoApi(request,response);
String cmsUrl=dwgk.getUrlCms();
JSONArray recList=null;
JSONObject rec=null;
int tpxwNum=5;
int tbttNum=6;
int dtgzNum=5;
int jqgkNum=12;

String dwlmId="355";//当前单位总栏目ID
String tpxwId="1423";//图片新闻
String tbttId="1423";//头版头条
String dtgzId="255";//动态工作
//公开查询
String dzzjyId="264";//党组织决议决定及执行
String ddsxId="275";//党的思想建设
String ddzzId="282";//党的组织管理
String ldbzId="283";//领导班子建设
String gbxrId="284";//干部选任和管理
String lxhffId="285";//联系和服务党员、群众
String dflzId="286";//党风廉政建设
String qtgkId="343";//其他公开内容

String jqgkId="256";//近期公开重点

//公开制度机制
String gzgkId="259";//工作公开组织结构
String dwgkId="260";//党务公开目录
String dzwjId="261";//工作文件制度
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>申通地铁集团协同平台党务公开专栏</title>
<link type="text/css"  rel="stylesheet" href="../css/dwgk/index.css"/>
<link type="text/css"  rel="stylesheet" href="../css/dwgk/reset.css"/>
<script type="text/javascript" src="../js/dwgk/jquery.js" ></script>
<script type="text/javascript" src="../js/dwgk/menu_select.js" ></script>
<script type="text/javascript" src="../js/dwgk/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../js/dwgk/menu_event2.js"></script>
<script type="text/javascript" src="../js/dwgk/jquery.iFadeSldie.pack.js"></script>
</head>
<body>
<div class="top"> <img src="../images/dwgk/top.jpg"  /> </div>
<div class="wrap">
  <div class="leftside">
    <div class="gzxx">
    	<div class="l-pic">
        <div id="slide">
        
        	<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- 新闻头条 -->
				<tr><td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">						
						<tr><td class="left_middle">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>
							<td width="5"></td>
							<td width="280" valign="top">
							<table width="100%"  border="0" align="left" cellpadding="0" cellspacing="0">
			    <tr>
			      <td align="left" width="43%">
      
      <table align="left" width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
                    <tr>
                      <td height="100%">
<%
String pics="",links="",texts="";
recList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+tpxwId+"','hasTitleImg':'1','typeId':'2,3','rownum':'"+tpxwNum+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		texts+="|"+StringUtil.MySubstring(rec.getString("title") ,15);
		pics+="|"+cmsUrl+"/"+rec.getString("titleImg");
		links+="|contentDetail.jsp?content="+rec.getString("contentId");	
	}
}
%>						
	<script language="JavaScript" type="text/javascript">
					var pics="<%=pics%>",links="<%=links%>",texts="<%=texts%>"; 
					pics=pics.substring(1);
					links=links.substring(1);
					texts=texts.substring(1);
					
					var text_height=16;
					var swf_height=190;
					var focus_width=245;
					var focus_height=170;
					document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
					document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="<%=path %>/flash/pixviewer.swf"><param name="quality" value="high"><param name="bgcolor" value="#f5f5f5">');
					document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
					document.write('<param name="FlashVars" value="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
					document.write('<embed src="<%=path %>/flash/pixviewer.swf" wmode="opaque" FlashVars="pics='+pics+'&links='+links+'&texts='+texts+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'" menu="false" bgcolor="#f5f5f5" quality="high" width="'+ focus_width +'" height="'+ swf_height +'" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />');
					document.write('</object>');
					//<!---->
				      </script>
					</td>				
                </tr>
             </table> 
		</td></tr>
		</table>
							</td>
							<td width="5"></td>
							<td width="300" valign='top'>
	  					</td><td width="10"></td></tr></table>
						</td></tr>
						<%//头条----------------------------------- %>
						<tr><td class="left_bottom">
						</td></tr>
					</table>
        </td></tr></table>
          <div class="ico">
          </div>
        </div>
      </div>
      <span class="more"><a href="channelInfo.jsp?channel=<%=tbttId %>" target="_blank" >更多>></a></span>
      <h3><a href="#" style="cursor:default"><em>动态工作信息</em></a></h3>
      <div class="gzxx-info" >
        <ul style="min-height:185px;">
<%
String shortTitle="";
recList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+tbttId+"','rownum':'"+tbttNum+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		shortTitle=rec.getString("shortTitle");
		if(shortTitle.equals("null")){
			shortTitle=rec.getString("title");
		}
%>
          <li><a title="<%=shortTitle %>" href="contentDetail.jsp?content=<%=rec.getString("contentId") %>" target="_blank"><%=StringUtil.MySubstring(rec.getString("title") ,26) %></a></li>
<%
	}
	for(int i=0;i<dtgzNum-recList.size();i++){

%>           
        <li>&nbsp;&nbsp;</li>
<% 
	}
}else{
	for(int i=0;i<dtgzNum;i++){

%>           
        <li>&nbsp;&nbsp;</li>
<% 
	}
}
%>          
        </ul>
        
        <ul class="other">
<%
recList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+dtgzId+"','rownum':'"+dtgzNum+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		shortTitle=rec.getString("shortTitle");
		if(shortTitle.equals("null")){
			shortTitle=rec.getString("title");
		}		
%>         	
        <li><a title="<%=shortTitle %>" href="contentDetail.jsp?content=<%=rec.getString("contentId") %>" target="_blank"><%=StringUtil.MySubstring(rec.getString("title") ,30) %></a><em><%=rec.getString("releaseDate") %></em></li>
<%
	}
	for(int i=0;i<dtgzNum-recList.size();i++){

%>           
        <li>&nbsp;&nbsp;</li>
<% 
	}
}else{
	for(int i=0;i<dtgzNum;i++){

%>           
        <li>&nbsp;&nbsp;</li>
<% 
	}
}
%>         
        </ul>
        <span class="last"><a href="channelInfo.jsp?channel=<%=dtgzId %>" target="_blank">更多>></a></span>
      </div>
    </div>
    
    <div class="nrcx">
      <h3><em class="f-yh" style="cursor:default">公开内容查询</em></h3>
      <%
JSONObject numObj=null;
JSONArray numArr=null;
String[] channelIds=new String[]{dzzjyId,ddsxId,ddzzId,ldbzId,gbxrId,lxhffId,dflzId,qtgkId};
String[] contentNums=new String[]{"0","0","0","0","0","0","0","0"};
for(int k=0;k<channelIds.length;k++){
	numArr=dwgk.getInfoList("JEECMS.GET_CONTENT_NUM","{'channelId':'"+channelIds[k]+"'}");
	if(numArr!=null){
		numObj=numArr.getJSONObject(0);
		contentNums[k]=numObj.getString("contentNum");
	}
}
%>      
      <ul class="nrcx-info">
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[0] %></div><em class="info1"><a href="channelInfo.jsp?channel=<%=dzzjyId %>" target="_blank">党组织决议<br />
          决定及执行</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[1] %></div><em class="info2"><a href="channelInfo.jsp?channel=<%=ddsxId %>" target="_blank">党的思想<br />
          建设</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[2] %></div><em class="info3"><a href="channelInfo.jsp?channel=<%=ddzzId %>" target="_blank">党的组织<br />
          管理</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[3] %></div><em class="info4"><a href="channelInfo.jsp?channel=<%=ldbzId %>" target="_blank">领导班子<br />
          建设</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[4] %></div><em class="info5"><a href="channelInfo.jsp?channel=<%=gbxrId %>" target="_blank">干部选任<br />
          和管理</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[5] %></div><em class="info6"><a href="channelInfo.jsp?channel=<%=lxhffId %>" target="_blank">联系和服务<br />
          党员、群众</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[6] %></div><em class="info7"><a href="channelInfo.jsp?channel=<%=dflzId %>" target="_blank">党风廉政<br />
          建设</a></em></div></li>
        <li><div class="Num_P"><div class="Num_1"><%=contentNums[7] %></div><em class="info8"><a href="channelInfo.jsp?channel=<%=qtgkId %>" target="_blank">其他公开<br />
          内容</a></em></div></li>
      </ul>
    </div>
    <div class="p_link">
      <h3><em class="pubic"><a href="#" class="f-yh" style="cursor:default">各直属党组织链接</a></em></h3>
   <ul class="t_news_tit">
        <li><a href="main.jsp" class="f-yh current">集团机关</a></li>
        <li><a href="yyzx.jsp" class="f-yh ">运管中心</a></li>
        <li><a href="wbzx.jsp" class="f-yh ">维保中心</a></li>
        <li><a href="zcgs.jsp" class="f-yh ">资产公司</a></li>
        <li><a href="yy1gs.jsp" class="f-yh ">运营一公司</a></li>
        <li><a href="yy2gs.jsp" class="f-yh ">运营二公司</a></li>
        <li><a href="yy3gs.jsp" class="f-yh ">运营三公司</a></li>
        <li><a href="yy4gs.jsp" class="f-yh ">运营四公司</a></li>
        <li><a href="jsyjzx.jsp" class="f-yh ">技术研究中心</a></li>
        <li><a href="sdsjy.jsp" class="f-yh ">隧道设计院</a></li>
        <li><a href="dqgs.jsp" class="f-yh ">大桥公司</a></li>
        <li><a href="pxzx.jsp" class="f-yh ">培训中心</a></li>
        <!--<li><a href="line10.jsp" class="f-yh">10号线</a></li>
        <li><a href="line_shenjia.jsp" class="f-yh">申嘉线</a></li>
        <li><a href="line12.jsp" class="f-yh">12号线</a></li>
        <li><a href="line13.jsp" class="f-yh">13号线</a></li>
        <li><a href="line9.jsp" class="f-yh">9号线</a></li>
      --></ul>
    </div>
  </div>
  <div class="rightside">
      <form name="searchForm" action="search.jsp" method="post" target="_blank">	
      <div class="search"> <em > 关键字查询</em>
      <p>
      	<input id="channel" name="channel" type="hidden" value="<%=dwlmId%>"/>   
       	<input id="searchWord" name="searchWord" type="text"  class="input1" style="width:127px;" value=""/>        
        <input onClick="javascript:submitSearch()" src="../images/dwgk/search_botton.jpg" type="button"  class="btn1" value="查询"/>
		<script type="text/javascript">	 
		function submitSearch(){
			var form = document.searchForm;
			form.submit();
		}
		</script>
      </p>
    </div>
    </form>
    <div class="gkzd">
      <h3><em class="pubic"><a href="#" class="f-yh" style="cursor:default">近期公开重点</a></em></h3>
      <ul class="r_info">
<%
recList=dwgk.getInfoList("JEECMS.GET_CONTENT_LIST","{'channelId':'"+jqgkId+"','rownum':'"+jqgkNum+"'}");
if(recList!=null){
	for(int i=0;i<recList.size();i++){
		rec=recList.getJSONObject(i);
		shortTitle=rec.getString("shortTitle");
		if(shortTitle.equals("null")){
			shortTitle=rec.getString("title");
		}	
%>           
        <li><em>·</em><a title="<%=shortTitle %>" href="contentDetail.jsp?content=<%=rec.getString("contentId") %>" target="_blank" class="content_list"><%=StringUtil.MySubstring(rec.getString("title") ,10)%></a></li>
<% 
	}
	for(int i=0;i<jqgkNum-recList.size();i++){

%>           
        <li>&nbsp;&nbsp;</li>
<% 
	}
}else{
	for(int i=0;i<jqgkNum;i++){

%>           
        <li>&nbsp;&nbsp;</li>
<% 
	}
}
%>        
      </ul>
      <span class="last"> <a href="channelInfo.jsp?channel=<%=jqgkId %>" target="_blank">更多>></a></span> </div>
    <div class="gkzd">
      <h3><em class="pubic"><a href="#" class="f-yh" style="cursor:default">公开制度机制</a></em></h3>
      <ul class="gkjz">
        <li><a href="channelInfo.jsp?channel=<%=gzgkId %>" target="_blank">工作公开组织机构</a></li>
        <li><a href="channelInfo.jsp?channel=<%=dwgkId %>" target="_blank">党务公开目录</a></li>
        <li><a href="channelInfo.jsp?channel=<%=dzwjId %>" target="_blank">工作文件制度</a></li>
      </ul>
    </div>
  </div>
  <div class="f-both"></div>
</div>
</body>
</html>