<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body style="overflow-x:hidden;">

   <div id="search">
   <div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
<tr>
     <td class="form_label"><fmt:message key="bankorg_id"/></td><td><input type="text" name="aclExclude.id.bankorgId" id="bankorgId" value=""/></td>
    <td class="form_label"><fmt:message key="systemId"/></td><td><input type="text" name="aclExclude.id.systemId" id="systemId" value=""/></td>
    <td class="form_label"> <fmt:message key="excludeId"/></td><td><input type="text" name="aclExclude.id.excludeId" id="excludeId" value=""/></td>
    
    </tr>
    <tr>
        <td colspan="6">
     <!--  <input type="hidden" name="clause._orderby__id_systemId" id="systemId_order" value="A"/>-->
     <input type="hidden" name="clause._id_systemId" id="systemId_q" value="like"/>
     <!--<input type="hidden" name="clause._orderby__id_excludeId" id="excludeId_order" value="A"/>-->
     <input type="hidden" name="clause._id_excludeId" id="excludeId_q" value="like"/>
<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
    </td> </tr></table>
   </div>
   <sc:grid id="grid" url="${path}aclExcludeAction/querylist.action" height="99%" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
      <sc:toolBar type="ADD" text="add" id="aclExcludeActionadd" name="aclExclude_add" icon="add"  url="${path}aclExcludeAction/toadd.action"></sc:toolBar>
      <sc:toolBar type="UPDATE" id="aclExcludeActionupdate" url="${path}aclExcludeAction/detail.action" name="aclExclude_update" text="update" icon="modify" entityName="'aclExclude'" struct="['id.systemId','id.bankorgId','id.excludeId']"></sc:toolBar>
      <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}aclExcludeAction/delete.action" entityName="'aclExcludes'" struct="['id.systemId','id.bankorgId','id.excludeId']"></sc:toolBar>


      <sc:column name="bankorgId" id="bankorgId" display="bankorg_id" render="function(item){return item.id.bankorgId;}"></sc:column>
      <sc:column name="systemId" id="systemId" display="systemId" render="function(item){return item.id.systemId;}"></sc:column>
      <sc:column name="excludeId" id="excludeId" display="excludeId" render="function(item){return item.id.excludeId;}"></sc:column>
      <sc:column name="excludeName" id="excludeName" display="excludeName" ></sc:column>
   </sc:grid>
</body>
</html>