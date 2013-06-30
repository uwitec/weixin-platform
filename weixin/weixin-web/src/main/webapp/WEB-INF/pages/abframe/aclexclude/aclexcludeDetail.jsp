<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
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
		  		<div><sc:message key="caclExcludedetail"/></div>
		  	</div>
   </div>
    <sc:form name="form1" method="post" action="${path }aclExcludeAction/${detailTag==null?'add.action':'update.action'}" id="form1">
<div>
</div>
<table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >           
            <tr>
                <td class="form_label"><fmt:message key="systemId"/>:</td>
                <td >
                 <input name="aclExclude.id.bankorgId" value="${aclExclude.id.bankorgId }" type="hidden" />
                 <sc:text name="aclExclude.id.systemId" id="systemId" value="${aclExclude.id.systemId}" type="text" ltype="text" validate="{required:true,maxlength:20}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
                </td>
               <td class="form_label"><fmt:message key="excludeId"/>:</td>
                <td>
                <sc:text name="aclExclude.id.excludeId" id="excludeId" value="${aclExclude.id.excludeId}" type="text" ltype="text" validate="{required:true,maxlength:40}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
                </td>
                <td class="form_label"><fmt:message key="excludeName"/>:</td>
                <td>
                <sc:text name="aclExclude.excludeName" id="excludeName" value="${aclExclude.excludeName}" type="text" ltype="text" validate="{required:true,maxlength:40}"></sc:text>
                </td>
            </tr>
            <tr style="height:50px">
     <td colspan="6">
<input type="submit" value="<fmt:message key="submit"/>" id="subButton" style = "float:right;margin:4px;" class="l-button l-button-submit" /> 
</td>
            </tr> 
        </table>

    </sc:form>
</body>    
</html>