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
  <td class="form_label"><fmt:message key="employeeId"/></td><td><input type="text" name="kenEmpbranch.id.employeeId" id="employeeId" value=""/></td>
  <td class="form_label"><fmt:message key="brBranchId"/></td><td><input type="text" name="kenEmpbranch.id.brBranchId" id="brBranchId" value=""/></td>
  </tr>
  <tr>
  <td colspan="4">
  <!--  <input type="hidden" name="clause._orderby__id_employeeId" id="employeeId_order" value="A"/>-->
  <!--  <input type="hidden" name="clause._orderby__id_brBranchId" id="brBranchId_order" value="A"/>-->
  <td colspan="4">
<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
  </td></tr></table>
</div>

<sc:grid id="grid" url="${path}kenEmpbranch/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   <sc:toolBar type="ADD" text="add" id="kenEmpbranchadd" name="kenEmpbranch_add" icon="add"  url="${path}kenEmpbranch/toadd.action"></sc:toolBar>
   <sc:toolBar type="UPDATE" id="kenEmpbranchupdate" url="${path}kenEmpbranch/detail.action" name="kenEmpbranch_update" text="update" icon="modify" entityName="'kenEmpbranch'" struct="['id.bankorgId','id.employeeId','id.brBranchId']"></sc:toolBar>
   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}kenEmpbranch/delete.action" entityName="'kenEmpbranchs'" struct="['id.bankorgId','id.employeeId','id.brBranchId']"></sc:toolBar>
   
   <sc:column name="id.employeeId" id="id.employeeId" display="employeeId"  render="function(item){return item.id.employeeId;}"></sc:column>
   <sc:column name="id.brBranchId" id="id.brBranchId" display="brBranchId"  render="function(item){return item.id.brBranchId;}"></sc:column>
</sc:grid>