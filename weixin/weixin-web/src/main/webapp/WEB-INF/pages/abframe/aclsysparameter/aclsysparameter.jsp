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
	  <td class="form_label"><fmt:message key="oldChangeCounts"/></td>
	  <td><input type="text" name="aclSysparameter.oldChangeCounts" id="oldChangeCounts" value=""/></td>
	  <td class="form_label"><fmt:message key="maxErrCount"/></td>
	  <td><input type="text" name="aclSysparameter.maxErrCount" id="maxErrCount" value=""/></td>
	</tr>
	<tr>
	  <td colspan="4"><input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" /></td>
	</tr>
 </table>
</div>

<sc:grid id="grid" url="${path}aclSysparameterAction/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   <sc:toolBar type="ADD" text="add" id="aclSysparameterActionadd" name="aclSysparameter_add" icon="add"  url="${path}aclSysparameterAction/toadd.action"></sc:toolBar>
   <sc:toolBar type="UPDATE" id="aclSysparameterActionupdate" url="${path}aclSysparameterAction/detail.action" name="aclSysparameter_update" text="update" icon="modify" entityName="'aclSysparameter'" struct="['bankorgId']"></sc:toolBar>
   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}aclSysparameterAction/delete.action" entityName="'listSysParameters'" struct="['bankorgId']"></sc:toolBar>
   
   <sc:column name="bankorgId" id="bankorgId" display="bankorg_id" ></sc:column>
   <sc:column name="fstPwdChange" id="fstPwdChange" display="fstPwdChange" ></sc:column>
   <sc:column name="chgPwdOldChk" id="chgPwdOldChk" display="chgPwdOldChk" ></sc:column>
   <sc:column name="oldChangeCounts" id="oldChangeCounts" display="oldChangeCounts" ></sc:column>
   <sc:column name="maxErrCount" id="maxErrCount" display="maxErrCount" ></sc:column>
</sc:grid>