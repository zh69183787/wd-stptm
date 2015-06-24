<%@ page contentType="text/html; charset=UTF-8" language="java" 
import="java.util.*,com.jspsmart.upload.*,com.wonders.stpt.util.*" errorPage="stoa/error/error.jsp" %>
<TITLE></TITLE>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="../images/css.css" type="text/css" rel="STYLESHEET"> 
<head>
<SCRIPT language="Javascript">

function fn_SelectAllAttachment( theForm ){
	for ( i = 0 ; i < theForm.elements.length ; i ++ ){
		if ( theForm.elements[i].type == "checkbox" && theForm.elements[i].name.indexOf("del") != -1 ){
			theForm.elements[i].checked = ! theForm.elements[i].checked ;
		}
	}
}

function fn_CheckAttach ( theForm ){
	if ( theForm.uploadfile.value == "" ){
		alert ( "请先选择要上载的文件,点击“浏览”选择本地文件。" );<!-- 请先选择要上载的文件,点击“浏览”选择本地文件. -->
		theForm.uploadfile.focus ( ) ;
		return false ;
	}
	return true ;
}

function fn_CheckRemove ( theForm ){
	var temp;
	bSelected = false ;
	
	for ( i = 0 ; i < theForm.elements.length ; i ++ ){
		if ( theForm.elements[i].type == "checkbox"
		  && theForm.elements[i].name.indexOf("del") != -1
		  && theForm.elements[i].checked ){
			bSelected = true ;
			break;
		}
	}
	if ( ! bSelected ){
		alert ( "请选择要删除的附件！" );	<!-- 请选择要删除的附件！ -->
		return false ;
	}
	return true ;
}
function EC(TheTR,img){
	var DataTR = eval('document.all.' + TheTR);
	if (DataTR.style.display=="block"){
		DataTR.style.display="none";
		img.children[1].children[0].src='./images/down.gif';
	}
	else{
		DataTR.style.display="block";
		img.children[1].children[0].src='./images/up.gif';
	}
	//this.document.all.attetr.height=this.document.body.scrollHeight;
	parent.document.all.filetr.height=this.document.body.scrollHeight+5;
}
</SCRIPT>
</HEAD> 
<%	
	//文件保存的路径

	String getFilePath=FileUtil.getDocumentPath();
	
	ArrayList al = (ArrayList)session.getAttribute("up_load");
	if(request.getParameter("flag")==null && request.getParameter("flags")==null){
	SmartUpload su = new SmartUpload();
	// 上传初始化

	su.initialize(pageContext);
	// 新建一个SmartUpload对象
	// 设定上传限制
	// 1.限制每个上传文件的最大长度.
	 su.setMaxFileSize(10000000);  //单个文件最大10M
	// 2.限制总上传数据的长度.
	 su.setTotalMaxFileSize(20000000); //总容量限制在20M

	try{
		su.upload();
	}catch(Exception e){
	%>
		<script language="javascript">
			alert("您上传的文件容量超过规定的大小！");<!-- 您上传的文件容量超过规定的大小！ -->
		</script>
	<%	}
	
	for (int i=0;i<su.getFiles().getCount();i++){
		//com.jspsmart.upload.File file = su.getFiles().getFile(i);
		com.jspsmart.upload.SmartFile file = su.getFiles().getFile(i);

		// 若文件不存在则继续

		if (file.isMissing()) continue;

		String strValue=String.valueOf(GlobalFunc.getSequence("SEQ_T_DOCS_FJ"));
		System.out.println(strValue);
		String fileRealPath=FileUtil.getDocumentPath();
		
		if(al !=null){	
			//判断重复添加文件
			if(al.indexOf(file.getFileName())>=0){	
	%>
		<Script language="javascript">
			alert("你已经添加了这个附件! ");<!-- 你已经添加了这个附件! -->
		</script>	
	<%	}
		else{
			//文件名			
			//al.add(new String(file.getFileName().getBytes("gbk"),"utf-8"));
			al.add(file.getFileName());
			//扩展名

			al.add(file.getFileExt());			
			//文件大小
			al.add(String.valueOf(file.getSize()));
			//修改后的文件名

			al.add(strValue);			
			//文件的路径

			al.add(fileRealPath);			
			}
		}
		else{
			al=new ArrayList();
			//文件名

			//al.add(new String(file.getFileName().getBytes("gbk"),"utf-8")); 
			al.add(file.getFileName());
			//扩展名

			al.add(file.getFileExt());
			//文件大小
			al.add(String.valueOf(file.getSize()));
			//修改后的文件名

			al.add(strValue);
			//文件的路径

			al.add(fileRealPath);
			session.setAttribute("up_load",al);
	   }
	   
	   file.saveAs(fileRealPath+strValue);
	}
}
%>
<html>
<head>
<TITLE></TITLE>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" >
<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr id="attechment" >
	<td colspan="2">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr id="attetr">
				<td valign="top">

	<table cellpadding=1 cellspacing=1 border=0 width=100%> 
	<TBODY>
	<TR><!-- <td width="108" valign="top"><IMG SRC="./images/fujian.jpg" WIDTH="108" BORDER=0 ALT=""></td> -->
		<TD background="/images/03bj.gif" width="*"> 
		<TABLE border=0 cellPadding=3 cellSpacing=0 align=center>
		<TBODY>
<form method="post" action="dealFj.jsp" name="theForm" ENCTYPE="multipart/form-data">
		<TR>
			<TD width=3%>1．</td>
			<td width=97%>单击“浏览”选择文件,或在下面的框中键入文件的路径.</TD>	 <!-- 单击“浏览”选择文件,或在下面的框中键入文件的路径. -->
		</TR>
		<TR>
			<TD>&nbsp;</td>	<!-- 查找文件 -->
			<td>查找文件:<input id=uploadfile2 tabindex=1 type="FILE" size="40" name="uploadfile" ContentEditable="false">
				&nbsp;&nbsp;
				<input onClick="return fn_CheckAttach(this.form);" tabindex=2 class="bottoncss" type=submit value="新增" name=upload>
			</TD> <!-- 新增 -->
		</TR>
</form>
<form method="post" action="dealFj.jsp">
<%
		String[] strchValue=request.getParameterValues("del");
		//out.print(al);
		if(strchValue!=null){
			if(al!=null)
				al=FileUtil.getAfterDel(al,strchValue,5);
		}
%>
		<TR>
			<TD>2．</td>
			<td>为保证系统的正常运行,单个文件大小限制在 10MB 之内,总文件大小限制在 20MB 之内.</TD> <!-- 为保证系统的正常运行,单个文件大小限制在 10MB 之内,总文件大小限制在 20MB 之内. -->
		</TR>
		<TR>
			<TD>&nbsp;</td>
			<td>
			<TABLE cellSpacing=0 borderColorDark=#e4edf3 cellPadding=3 borderColorLight=#5999c8 border=1 width=100%>
				<TR height="18">
					<td width=5% align=center valign="bottom">
						<IMG SRC="../images/files.gif" WIDTH="14" HEIGHT="17" BORDER=0 ALT="">
					</td>
					<td width=50% class="bg">附件名称</td><!-- 附件名称 -->
					<td width=25% class="bg">扩展名</td><!-- 扩展名 -->
					<td width=20% align="right" class="bg">文件大小(K)</td><!-- 文件大小 -->
					<input type=hidden name="flags" value="1">
				</TR>
<%
		int j=0;
		if((ArrayList)session.getAttribute("up_load")!=null){
			ArrayList alValue=(ArrayList)session.getAttribute("up_load");
			int i=0;
			String sTemp="";
			System.out.println("alValue.size()=:"+alValue.size());
			while(i<alValue.size()){			
				j++;
				sTemp=(String)alValue.get(i+1);
				if(!FjTag.IsExtName(sTemp,pageContext))
					sTemp="null";
%>
				<TR>
					<td width=5% align=center>
						<INPUT id=del<%=j%> type=checkbox name=del value="<%=j%>">
					</td>
					<td width=50%>
						<IMG SRC="../images/files/<%=sTemp%>.gif" WIDTH="16" HEIGHT="16" BORDER="0" ALT="">
						<b><%=alValue.get(i)%></b><input type="hidden" name="fileName<%=j%>" value="<%=alValue.get(i)%>" >
					</td>
					<td width=15%>
						<LABEL for=del<%=j%>><b><%=alValue.get(i+1)%></b></LABEL>&nbsp;
					</td>
					<td width=20% align="right">
<%
						float fTempSize=Float.valueOf(alValue.get(i+2).toString()).floatValue();
%>
						<b><%=fTempSize/1000.0%></b>
					</td>
				</TR>
<%	
				i=i+5;	
			}
		}
%>	
			</table>
			</TD>
		</TR>
		
		<TR>
			<td></td>
			<TD align=left>
				<input onClick="return fn_CheckRemove(this.form)" tabindex=3 type=submit class="bottoncss" value="删除" name=dodelete><!-- 删除 -->
			</TD>
		</TR>
</form>

		</TABLE>
		 
	</TD></TR>
	</TBODY>
	</TABLE>
				</td>
			</tr>
		</table>
	</td></tr>
	<!-- <tr style="CURSOR: hand" onclick="EC('attechment',this);" bgColor="#C1D4D0" height="25" name="attabottom" id="attabottom">
		<td><b>&nbsp;相关附件信息&nbsp;(<font color="red"><%=j%></font>&nbsp;个附件)</b></td> -->
		<!-- <TD style="PADDING-RIGHT: 7px" align="right"><IMG hspace=1 src="./images/up.gif" align=absMiddle border=0></td> --> <!-- 相关附件信息 -->		  <!-- 个附件 -->
	<!-- </tr>  -->
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
	//var attach="<%=j%>";
	//if(attach=="0"){
	//	document.all.attechment.style.display="none";
	//	document.all.attabottom.children[1].children[0].src='./images/down.gif';
	//}
	//else{
	//	document.all.attechment.style.display="block";
	//	document.all.attabottom.children[1].children[0].src='./images/up.gif';
	//}
//-->
</SCRIPT>
</body>
</html>
<SCRIPT LANGUAGE="JavaScript">
<!--
	//this.document.all.attetr.height=this.document.body.scrollHeight;
	parent.document.all.filetr.height=this.document.body.scrollHeight+5;
//-->
</SCRIPT>
			
