<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="com.wonders.stpt.cms.CmsInfoApi"%>
<%@ page import="com.wonders.stpt.cms.StringUtil"%>
<%
String channelId=request.getParameter("channel");
String path = request.getContextPath();
CmsInfoApi dwgk=new CmsInfoApi(request,response);

JSONObject channelObj=null;
JSONArray channelList=dwgk.getInfoList("JEECMS.GET_CHANNEL_LIST","{'channelId':'"+channelId+"'}");
if(channelList!=null){
	channelObj=channelList.getJSONObject(0);
}
String currentChannelId=channelId;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=channelObj.getString("channelName") %></title>
<link href="../css/dwgk/index.css" rel="stylesheet" type="text/css" />
<link href="../css/dwgk/reset.css" rel="stylesheet" type="text/css" />
<link href="../css/dwgk/jquery-ui-1.8.14.custom.css" rel="stylesheet" type="text/css"/>
<script src="../js/dwgk/jquery-1.5.1.min.js"></script>
<script src="../ui/jquery.ui.core.js"></script>
<script src="../ui/jquery.ui.widget.js"></script>
<script src="../ui/jquery.ui.accordion.js"></script>
<script src="../ui/jquery.ui.button.js"></script>
<script>

	$(function() {
		var icons = {
			header: "ui-icon-circle-arrow-e",
			headerSelected: "ui-icon-circle-arrow-s"
		};
		$( "#accordion" ).accordion({
			icons: icons,
			autoHeight: false
		});
		$( "#toggle" ).button().toggle(function() {
			$( "#accordion" ).accordion( "option", "icons", false );
		}, function() {
			$( "#accordion" ).accordion( "option", "icons", icons );
		});
	});
	</script>
</head>
<body>
<div class="top"> <img src="../images/dwgk/top.jpg"  /> </div>
<div class="place"><span>当前位置：<em>党务公开专栏 > <%=channelObj.getString("channelName") %>  </em></span></div>
<div class="wrap">
  <div class="l_sider">
  <%
  JSONArray lv1ChannelList=dwgk.getInfoList("JEECMS.GET_CHANNEL_LIST","{'parentId':'"+channelId+"'}");
  if(lv1ChannelList==null){
  %>
	<img src="../images/dwgk/leftpic.jpg" width="100%"/>
 	
  <%
  }else{
  %>
  
    <div id="accordion">
  	<%
  	JSONObject lv1Channel=null;
	for(int j=0;j<lv1ChannelList.size();j++){
		lv1Channel=lv1ChannelList.getJSONObject(j);
  	%>
  		<h3><a href="#"><%=lv1Channel.getString("channelName") %></a></h3>
  		<div>
  		<%
  		JSONArray lv2ChannelList=dwgk.getInfoList("JEECMS.GET_CHANNEL_LIST","{'parentId':'"+lv1Channel.getString("channelId")+"'}");
  		if(lv2ChannelList!=null){
  		  	JSONObject lv2Channel=null;
			for(int k=0;k<lv2ChannelList.size();k++){
				
				lv2Channel=lv2ChannelList.getJSONObject(k);
				if(j==0 && k==0){currentChannelId=lv2Channel.getString("channelId");}
		%>
				<a href="javaScript:showInfoList('<%=lv2Channel.getString("channelId") %>')"><img src="../images/dwgk/097.gif"/><%=lv2Channel.getString("channelName") %></a>
		<%		
			}
  		}
  		 %>
  			
  		</div>

  	<%  		
  	}  	
  	 %>

  	</div> 
  <%
  }
  %>


</div>
<script>
function showInfoList(channelId){
	$(document).find("#listFrame").attr("src","infoList.jsp?channel="+channelId);
}
</script>
 <iframe style="position:relative;top:0px;right:0px;" src="infoList.jsp?channel=<%=currentChannelId %>"  frameborder="no" border="0" framespacing="0" name="listFrame"  scrolling="no"   height="700px" width="730px"  noresize="noresize" id="listFrame" title="listFrame"></iframe>
   <div class="f-both"></div>
</div>
</body>
</html>
