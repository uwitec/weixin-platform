<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/common.jsp"%>

<div id="search">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
<tr>
  <td class="form_label"><fmt:message key="employeeId"/></td><td><input type="text" name="kenEmployee.id.employeeId" id="employeeId" value=""/></td>
 <td class="form_label"> <fmt:message key="brBranchId"/></td><td><input type="text" name="kenEmployee.brBranchId" id="brBranchId" value=""/></td>
 </tr>
 <tr><td colspan="4">
  <!--  <input type="hidden" name="clause._orderby__id_employeeId" id="employeeId_order" value="A"/>-->
   <input type="hidden" name="clause.brBranchId" id="brBranchId_q" value="like"/> 
   <td colspan="4">
<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
   </td></tr></table>
</div>

<sc:grid id="grid" url="${path}kenEmployeeAction/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   <sc:toolBar type="ADD" text="add" id="kenEmployeeadd" name="kenEmployee_add" icon="add"  url="${path}kenEmployeeAction/toadd.action"></sc:toolBar>
   <sc:toolBar type="UPDATE" id="kenEmployeeupdate" url="${path}kenEmployeeAction/detail.action" name="kenEmployee_update" text="update" icon="modify" entityName="'kenEmployee'" struct="['id.bankorgId','id.employeeId']"></sc:toolBar>
   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}kenEmployeeAction/delete.action" entityName="'kenEmployees'" struct="['id.bankorgId','id.employeeId']"></sc:toolBar>
   
   <sc:column name="id.employeeId" id="id.employeeId" display="employeeId"  render="function(item){return item.id.employeeId;}"></sc:column>
   <sc:column name="brBranchId" id="brBranchId" display="brBranchId" ></sc:column>
   <sc:column name="employeeName" id="employeeName" display="employeeName" ></sc:column>
</sc:grid>