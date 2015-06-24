<%@page contentType="text/html"%>
<%@page isELIgnored="false"%>
<%@page pageEncoding="UTF-8"%>
<%
    String contextPath = (String) request.getContextPath();
    String root = request.getParameter("root");
%>
<HTML>
<HEAD>
    <title>人员选择</title>
    <base target="_self" />
    <link href="<%=contextPath %>/css/organTree.css" rel="stylesheet" type="text/css">
    <script language="javascript" src='<%=contextPath %>/js/xtreeCheckbox.js'></script>
    <script language="javascript" src='<%=contextPath %>/js/peopleTree.js'></script>
    <script language="javascript" src='<%=contextPath %>/js/ajax.js'></script>

    <script language="javascript">

        //生成以"申通集团"为根节点的组织树
        var tree = new WebFXTree('申通集团','#','','<%=contextPath %>/images/forTree/foldericon.gif',
                '<%=contextPath %>/images/forTree/openfoldericon.gif','<%=root%>','1','0');
        tree.setBehavior('classic');

        //异步加载root以下的子节点
        var url = "<%=contextPath%>/holHolidaysApply/loadPersonMenu.action?id=";
        doAjaxRequest(url,'<%=root%>');
    </script>
</HEAD>

<BODY>
<div style="width:100%; overflow: auto; cursor: default; display: inline; position: absolute; height: 95%;">
    <table cellpadding='0' cellspacing='0'>
        <tr class="FixedTitleRow" >
            <td><input type='text'  id='returnText' readonly='true' size='30'/>
                <input type='hidden' id='returnId'/>
                <input type='button' class="btn" value='确定' onClick="returnText('checkbox')"/>
                <input type='button' class="btn" value='关闭' onclick='javascript:window.close()'/>
                <input type='button' class="btn" value='全选/反选' onclick='check_all()'/>
            </td></tr>
        <tr><td>
            <script language="javascript">
                document.write(tree);
            </script>
        </td></tr>
    </table>
</div>
</BODY>
</HTML>
