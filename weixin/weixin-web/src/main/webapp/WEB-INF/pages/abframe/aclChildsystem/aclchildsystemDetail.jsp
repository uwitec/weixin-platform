<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
</head>
<body style="padding:10px;"> 
<div id="search">
  			<div class="search-bar-top">
		  		<div><sc:message key="aclChildsystemdetail"/></div>
		  	</div>
   </div>
    <form name="form1" method="post" action="<%=request.getContextPath()%>/aclChildsystemAction/${detailTag==null?'add.action':'update.action'}" id="form1">
<div>
</div>
        <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
           
            <tr>
                <td class="form_label"><fmt:message key="systemId"/>:</td>
                <td >
                    <input name="aclChildsystem.id.bankorgId" value="${aclChildsystem.id.bankorgId }" type="hidden" />
                 <sc:text name="aclChildsystem.id.systemId" id="systemId" value="${aclChildsystem.id.systemId}" type="text" ltype="text" validate="{required:true,maxlength:20}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
                  </td> 
             
                <td class="form_label"><fmt:message key="systemName"/>:</td>
                <td>  <sc:text name="aclChildsystem.systemName" id="systemName" value="${aclChildsystem.systemName}" type="text" ltype="text" validate="{required:true,maxlength:40}"></sc:text>
           </td> 
                <td class="form_label"><fmt:message key="sysUrl"/>:</td>
                <td >
                  <sc:text name="aclChildsystem.sysUrl" id="oldChangeCounts" value="${aclChildsystem.sysUrl}" type="text" ltype="text" validate="{required:true,maxlength:200}"></sc:text>
           </td> </tr>  
       <tr><td colspan="99" style="padding:20px 20px 0 0;"><div style="text-align:right;float:right;"><input type="submit" value="<fmt:message key="submit"/>" id="subButton" class="l-button l-button-submit" /> </div></td></tr> 
        </table>

    </form>
</body>    
</html>