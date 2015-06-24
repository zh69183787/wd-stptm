<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>

<%@page import="com.wonders.stpt.util.*"%>

<%

		
	String contextPath = request.getContextPath();

	String username = AuthorUtil.GetLoginUserRealName();
	//processname = new String((processname).getBytes("iso8859-1"),"utf-8");
	//incident = new String((incident).getBytes("iso8859-1"),"utf-8");
	//taskid = new String((taskid).getBytes("iso8859-1"),"utf-8");
	//String user = (String) session.getAttribute("userLoginName");
	Map map1 = (Map)request.getAttribute("map");
	String processname = StringUtil.getNotNullValueString(map1.get("processName"));
	String incident = StringUtil.getNotNullValueString(map1.get("incident"));
	//System.out.println(processname+"-"+incident);
	//String processname = "";
	//String incident = "";
	String summary = "";
	
	List list = new ArrayList();
	//System.out.println(list.size());
	if(map1 != null){
	/*
		Set keySet = map1.keySet();
		summary = (String)(keySet.toArray())[0];
		System.out.println(summary);
	*/
		summary = StringUtil.getNotNullValueString((String)map1.get("summary"));
		
		list = (List)map1.get("list");
		
		if(list == null){
			list = new ArrayList();
		}
	}else{
		map1 = new HashMap();
	}
	
%>

<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv= "Content-Type " content= "text/html;charset=UTF-8">
	<link href="<%=contextPath%>/css/desktop/style-flow-<%
	String skin_css = (String) session.getAttribute("user.config.css");
	out.print(skin_css == null || "".equals(skin_css) ? "blue": skin_css);
	%>.css" rel="stylesheet" type="text/css">
	<link href="<%=contextPath%>/css/desktop/style-form-<%
	out.print(skin_css == null || "".equals(skin_css) ? "blue" : skin_css);
	%>.css" rel="stylesheet" type="text/css">
	
	<script src="../js/html5.js"></script>
	<script src="../js/jquery-1.7.1.js"></script>
	
	<script src="../js/jquery.formalize.js"></script>
	<script src="../js/high.js"></script>
		
	<script>
	function change (obj,i,m){
		
		var target = document.getElementById(i);
		var source = document.getElementById(m);
		var phone = obj;
		var a = document.getElementById(i);
		if(a.checked){
			//reg=/^1\d{10}$/gi;
			//if(!reg.test(obj)){
				
				//var bb = document.getElementById("mobile"+i);
				//bb.focus();
				//alert(target.name+"的手机号格式不正确！");
			//}
			//alert(source.value);
			
		}
		//var target1 = document.getElementById(i);
		//alert(target1.mobile);
		//alert(target);
		//target.mobile1.value = source;
		//alert(target.mobile1);
		target.mobile = obj;
		return false;
	}
	<%-- --%>
	function s(){
		//alert("bbb");
		//var k = 0;
		var text = document.getElementById("content").value;
		//alert(text);
		var serviceType ="短信催办";
		var content = text+" 催办人：<%=username%>\n\n摘要：<%=summary%>";
		var taskuser_all = "";
		var user_all = "";
		var phone_all = "";
		var taskid_all = "";
		var content_all = "";
		var userid_all = "";
		var username = "<%=username%>";
		var summary = "<%=summary%>";
		var count = 0;
		
		for (k=0;k<<%=list.size()%>;k++){
			var chk =$("#"+k);
			var a = document.getElementById(k);
			//alert(a);
			if(a.checked){
				if ($(chk).attr("mobile") ==""){
					var b = document.getElementById("mobile"+k);
					b.focus();
					alert("请输入"+$(chk).attr("name")+"的手机号！");
					return false;
				}
				//alert(a.mobile);
				reg=/^1\d{10}$/gi;
				if(!reg.test($(chk).attr("mobile"))){
			
					var b = document.getElementById("mobile"+k);
					b.focus();
					alert($(chk).attr("name")+"的手机号格式不正确！");
					return false;
				}
				//alert("true "+a.name+" "+a.taskuser+" "+a.taskid+" "+a.mobile+" ");
				/**
				taskuser_all = taskuser_all + "," + a.taskuser;
				user_all = user_all + "," + a.name;
				phone_all = phone_all + "," + a.mobile;
				taskid_all = taskid_all + "," + a.taskid;
				userid_all = userid_all + "," + a.userid;
				**/
				taskuser_all = taskuser_all + "," + $(chk).attr("taskuser");
				user_all = user_all + "," + $(chk).attr("name");
				phone_all = phone_all + "," + $(chk).attr("mobile") ;
				taskid_all = taskid_all + "," + $(chk).attr("taskid") ; 
				userid_all = userid_all + "," + $(chk).attr("userid") ; 
				
				//content_all
				count++;
			}
		}
		if(taskuser_all.substr(0,1)==',') taskuser_all = taskuser_all.substr(1,taskuser_all.length);
		if(user_all.substr(0,1)==',') user_all = user_all.substr(1,user_all.length);
		if(phone_all.substr(0,1)==',') phone_all = phone_all.substr(1,phone_all.length);
		if(taskid_all.substr(0,1)==',') taskid_all = taskid_all.substr(1,taskid_all.length);
		if(userid_all.substr(0,1)==',') userid_all = userid_all.substr(1,userid_all.length);
		
		var url = "<%=request.getContextPath()%>/myurge/sendMsgMulti.action?loginName="+taskuser_all
			+"&summary="+encodeURI(summary)+"&serviceType="+encodeURI(serviceType)+"&serviceId="+taskid_all
			+"&username="+encodeURI(username)+"&text="+encodeURI(text)+"&user="+encodeURI(user_all)
			+"&phone="+phone_all+"&count="+count+"&userid="+userid_all
			+"&processname="+encodeURI('<%=processname%>')
			+"&incident="+<%=incident%>
			+"&num="+Math.random();
		//alert("a\nb")
		//alert(url);
		if(count==0){
			alert("没有选中的催办的人员")
			if(confirm("是否关闭催办窗口")){
				self.close();
				return false;
			}
		}else{
			if(confirm("请确认短信内容：\n\n"+content+"\n\n接收人："+user_all+"\n\n接收人手机号："+phone_all)){
				
					try{
						xmlhttp = new XMLHttpRequest();
					}catch(trymicrosoft) {
						try{
							xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
						}catch(othermicrosoft) {
							try{
								xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
							}catch(failed) {
								xmlhttp = false;
							}  
						}
					}
					if (!xmlhttp){
						alert("初试化XMLHttpRequest!你的浏览器不支持某些地区功能。");
					}
					
					

					xmlhttp.open("get",url);
					xmlhttp.onreadystatechange = function(){
						if(xmlhttp.readyState == 4){
							if(xmlhttp.status == 200){
								var returnText = xmlhttp.responseText;
								alert(returnText);//
								//page(page1);
								self.close();
							}else{
								alert("网络连接失败！");
							}
						}
					}
					xmlhttp.send(null);

			}
		}
		
		return false;
	}
	
	</script>


  	<script>
	function openList(processname,incident,taskid,userid){
		
		var processname = encodeURI(processname);
		var url = "flowUrgenList.action?processname="+processname+"&incident="+incident+"&taskid="+taskid+"&userid="+userid;
		window.open(url, "window", "Width=650,Height=325,toolbar=no,menubar=no,status=no,scrollbars=yes");
		return false;
	}
	</script>


  </head>
  <body>
  <form onSubmit="return s();">
  <table width="100%" height="100%">
  <tr><td valign="middle">
  <%
  if(list.size()>0){
  %>
  
  
  <table width="100%" align="center" class="list-td1" border="0">
  <tr>
  		<td width="10%" align="center">&nbsp;</td>
  		<td width="70%" align="center"><b>当前处理人姓名</b></td>
  		<td width="20%" align="center"><b>手机号码</b></td>
  </tr>
  <%
  	for (int i=0;i<list.size();i++)
  	{
		Map temp = (Map)list.get(i);
		String t1 = (String)temp.get("taskid");
		String t2 = (String)temp.get("taskuser");
		String t3 = (String)temp.get("name");
		String t4 = (String)temp.get("mobile1");
		if (t4==null) t4="";
		String t5 = (String)temp.get("userid");
		String t6 = (String)temp.get("info");
		//String t7 = (String)temp.get("link");
		//System.out.println(t7);
  %>
  
  
  	<tr>
  		<td width="10%" align="center">
		<input type="checkbox" id="<%=i%>" taskid="<%=t1%>" taskuser="<%=t2%>" name="<%=t3%>" mobile="<%=t4%>" userid="<%=t5 %>"/>
		</td>
  		<td width="70%" align="left">

  		&nbsp;&nbsp;<%=t3%>&nbsp;&nbsp;<%=t6 %>
  		
  		</td>
  		<td width="20%" align="center"><input type="text" size="11" value="<%=t4%>" id="<%="mobile"+i%>" onBlur="javascript:return change(this.value,'<%=i%>','<%="mobile"+i%>');"/></td>
  	</tr>
  <%}
  %>
  	<tr align="left"><td colspan="3">
		<b>摘要</b>
	</td></tr>
	<tr align="left"><td colspan="3">
		<%=summary%>
	</td></tr>
	
	<tr align="left"><td colspan="3">
		<b>请输入催办内容。系统将自动在催办信息后添加催办人信息<br>和业务流程摘要，请不要重复输入数据。</b>
	</td></tr>
	<tr align="left"><td colspan="3">
		<textarea rows="5" id="content" cols="51">业务催办提醒</textarea>
	</td></tr>
	<tr align="center">
		<td colspan="3">
		<input type="submit" value="提交"/>&nbsp;&nbsp;
		<input type="button" value="关闭" onClick="self.close();"/>
		</td>	
  	</tr>
  </table>
  	</td></tr>
  </table>
  <%}else{%>
  <!--    -->
  		<div align="center"><b>找不到需要催办的当前处理人，无法进行催办</b></div> <br>
		<div align="center"><input type="button" value="关闭" onClick="self.close();"/></div>

  <%}%>
  
  
  </td></tr>
  </table>
  </form>
  </body>
</html>
