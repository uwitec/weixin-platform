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
		  		<div><sc:message key="aclFuncexcludedetail"/></div>
		  	</div>
   </div>
    <sc:form action="${path}aclFuncexcludeAction/${detailTag==null?'add.action':'update.action'}" id="form1">
       <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
            <tr>
               <td class="form_label">
                 <input type="hidden" name="detailTag" id="detailTag" value="${detailTag }" />
                 <input type="hidden" name="aclFuncexclude.id.bankorgId" value="${aclFuncexclude.id.bankorgId}" />
               <fmt:message key="functionId"/> </td><td> 
                  <sc:text name="aclFuncexclude.id.functionId" id="functionId" value="${aclFuncexclude.id.functionId}" type="text" ltype="text" validate="{required:true,maxlength:20}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
             <td class="form_label">
               <fmt:message key="excludeId"/> </td><td> 
               <sc:text name="aclFuncexclude.id.excludeId" id="excludeId" value="${aclFuncexclude.id.excludeId}" type="text" ltype="text" validate="{required:true,maxlength:40}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
             </td>
             <td class="form_label">
               <fmt:message key="excludeType"/> </td><td> 
              <sc:dict dictId="excludeType" name="aclFuncexclude.excludeType" empty="false" value="${aclFuncexclude.excludeType}"/>
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
