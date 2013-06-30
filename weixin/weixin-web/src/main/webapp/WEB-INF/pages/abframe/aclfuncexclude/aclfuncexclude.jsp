<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.sunline.cn/tags/html" prefix="sc" %>
<fmt:setBundle basename="resource.message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<fmt:setBundle basename="resource.message"/>

<div id="search">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
<tr>
  <td class="form_label"> <fmt:message key="functionId"/></td><td><input type="text" name="functionId" id="functionId" value=""/></td>
     <td class="form_label"> <fmt:message key="excludeId"/></td><td><input type="text" name="excludeId" id="excludeId" value=""/></td>
     </tr><tr>
     <td colspan="4">
      <!--  <input type="hidden" name="clause._orderby__id_functionId" id="functionId_order" value="A"/>-->
	<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
     </td>
     </tr>
     </table>
    
</div>

<sc:grid id="grid" url="${path}aclFuncexcludeAction/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   <sc:toolBar type="ADD" text="add" id="aclFuncexcludeActionupdateadd" name="aclFuncexclude_add" icon="add"  url="${path}aclFuncexcludeAction/toadd.action"></sc:toolBar>
   <sc:toolBar type="UPDATE" id="aclFuncexcludeActionupdate" url="${path}aclFuncexcludeAction/detail.action" name="aclFuncexclude_update" text="update" icon="modify" entityName="'aclFuncexclude'" struct="['id.bankorgId','id.functionId','id.excludeId']"></sc:toolBar>
   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}aclFuncexcludeAction/delete.action" entityName="'aclFuncexcludes'" struct="['id.bankorgId','id.functionId','id.excludeId']"></sc:toolBar>
   
   <sc:column name="id.bankorgId" id="id.bankorgId" display="bankorg_id"  render="function(item){return item.id.bankorgId;}"></sc:column>
   <sc:column name="id.functionId" id="id.functionId" display="functionId"  render="function(item){return item.id.functionId;}"></sc:column>
   <sc:column name="id.excludeId" id="id.excludeId" display="excludeId"  render="function(item){return item.id.excludeId;}"></sc:column>
   <sc:column name="excludeType" id="excludeType" display="excludeType"  ></sc:column>
  
</sc:grid>
</body>
</html>