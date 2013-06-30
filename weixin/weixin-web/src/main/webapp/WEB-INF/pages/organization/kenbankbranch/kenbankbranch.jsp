<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body style="overflow-x:hidden;">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
   <div id="search" style = "margin-bottom:5px;">
   <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
    <tr>
    <td class="form_label"> <sc:message key="brBranchId"/></td><td><input type="text" name="kenBankbranch.id.brBranchId" id="brBranchId" value=""/></td>
    <td class="form_label"> <sc:message key="brBranchName"/></td><td><input type="text" name="kenBankbranch.brBranchName" id="brBranchName" value=""/></td>
    </tr>
   <tr>
     <td colspan="4">
     	<input type="hidden" name="clause.brBranchName" id="brBranchName_q" value="like"/>
     	<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<sc:message key="search1"/>" onclick="p_getlist();" />
	 </td>
	</tr>
	</table>
   </div>
   <sc:grid id="grid" url="${path}kenBankbranch/querylist.action" height="99%" width="100%" root='rows' record='total'>
	   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
	   <sc:toolBar type="ADD" text="add" id="kenBankbranchadd" name="kenBankbranch_add" icon="add"  url="${path}kenBankbranch/toadd.action"></sc:toolBar>
	   <sc:toolBar type="UPDATE" id="kenBankbranchupdate" url="${path}kenBankbranch/detail.action" name="kenBankbranch_update" text="update" icon="modify" entityName="'kenBankbranch'" struct="['id.bankorgId','id.brBranchId']"></sc:toolBar>
	   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}kenBankbranch/delete.action" entityName="'kenBankbranchs'" struct="['id.bankorgId','id.brBranchId']"></sc:toolBar>
	   
	   <sc:column name="id.brBranchId" id="id.brBranchId" display="brBranchId"  render="function(item){return item.id.brBranchId;}"></sc:column>
	   <sc:column name="brBranchName" id="brBranchName" display="brBranchName"></sc:column>
	   <sc:column name="brCostCentre" id="brCostCentre" display="brCostCentre"></sc:column>
	   <sc:column name="branchLevel" id="branchLevel" display="branchLevel"></sc:column>
	   <sc:column name="parentBranchId" id="parentBranchId" display="parentBranchId"></sc:column>
	</sc:grid>
   
</body>
</html>