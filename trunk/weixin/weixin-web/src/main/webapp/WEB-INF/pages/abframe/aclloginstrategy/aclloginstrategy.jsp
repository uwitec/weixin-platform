<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<div id="search">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
   <tr>
     <td class="form_label"><fmt:message key="bankorg_id"/></td>
     <td><input type="text" name="aclLoginstrategy.id.bankorgId" id="id.bankorgId" value=""/></td>
     <td class="form_label"><fmt:message key="loginStrategy"/></td>
     <td><input type="text" name="aclLoginstrategy.id.loginStrategy" id="id.loginStrategy" value=""/></td>
   </tr>
   <tr>
	  <td colspan="4">
	  <!-- <input type="hidden" name="clause._orderby__id_loginStrategy" id="loginStrategy_order" value="A"/> -->
	  <input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
	  </td>
   </tr>
  </table>
</div>

<sc:grid id="grid" url="${path}AclLoginstrategyAction/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   <sc:toolBar type="ADD" text="add" id="AclLoginstrategyActionupdateadd" name="AclLoginstrategy_add" icon="add"  url="${path}AclLoginstrategyAction/toadd.action"></sc:toolBar>
   <sc:toolBar type="UPDATE" id="AclLoginstrategyActionupdate" url="${path}AclLoginstrategyAction/detail.action" name="AclLoginstrategy_update" text="update" icon="modify" entityName="'aclLoginstrategy'" struct="['id.bankorgId','id.loginStrategy']"></sc:toolBar>
   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}AclLoginstrategyAction/delete.action" entityName="'aclLoginstrategys'" struct="['id.bankorgId','id.loginStrategy']"></sc:toolBar>
   
    <sc:column name="id.bankorgId" id="id.bankorgId" display="bankorg_id"  render="function(item){return item.id.bankorgId;}"></sc:column>
   <sc:column name="id.loginStrategy" id="id.loginStrategy" display="loginStrategy"  render="function(item){return item.id.loginStrategy;}"></sc:column>
    <sc:column name="strategyName" id="strategyName" display="strategyName"  ></sc:column>
     <sc:column name="macCheck" id="macCheck" display="macCheck"  ></sc:column>
      <sc:column name="ipCheck" id="ipCheck" display="ipCheck" ></sc:column>
       <sc:column name="listCheck" id="listCheck" display="listCheck" ></sc:column>
        <sc:column name="listType" id="listType" display="listType" ></sc:column>
      
   
</sc:grid>
