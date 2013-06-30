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
		  		<div><sc:message key="aclFunctiondetail"/></div>
		  	</div>
   </div>
    <sc:form action="${path}aclFunction/${detailTag==null?'add.action':'update.action'}" id="form1" method="post">
        <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
            <tr>
               <td class="form_label">
                 <input type="hidden" name="detailTag" id="detailTag" value="${detailTag }"  />
                     <input type="hidden" name="aclFunction.id.bankorgId" value="${aclFunction.id.bankorgId }" />
                  <label><fmt:message key="functionId"/></label></td>
                  <td>
                  <sc:text name="aclFunction.id.functionId" id="functionId" value="${aclFunction.id.functionId}" type="text" ltype="text" validate="{required:true,maxlength:20}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
               </td>

             <td class="form_label">
               <label><fmt:message key="functionName"/></label></td>
                  <td>
               <sc:text name="aclFunction.functionName" id="functionName" value="${aclFunction.functionName}" type="text" ltype="text" validate="{required:true,maxlength:20}"></sc:text>
             </td>

             <td class="form_label">
               <label><fmt:message key="systemId"/></label></td>
                  <td>
              <s:select name="aclFunction.systemId" 
	                    list="@com.xuchunchun.abframe.web.combo.AclChildSystemCombo@getChildSystems()" 
	                    headerKey="" headerValue="请选择"
	                    listKey="id.systemId" listValue="systemName" theme="simple" cssClass="height:10000"/>
                </td>
             </tr>
              <tr>
            <td class="form_label">
               <label><fmt:message key="funcUrl"/></label></td>
                  <td>
               <sc:text name="aclFunction.funcUrl" id="funcUrl" value="${aclFunction.funcUrl}" type="text" ltype="text" validate="{required:true,maxlength:200}"></sc:text>
             </td>
             <td class="form_label">
			</td>
			<td></td>
			<td class="form_label">
			</td>
			<td></td>
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
    
