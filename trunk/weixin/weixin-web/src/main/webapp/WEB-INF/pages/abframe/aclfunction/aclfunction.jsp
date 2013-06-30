<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<div id="search">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
  <tr>
    <td class="form_label"><fmt:message key="functionId"/></td><td><input type="text" name="aclFunction.id.functionId" id="functionId" value=""/></td>
    <td class="form_label"><fmt:message key="functionName"/></td><td><input type="text" name="aclFunction.functionName" id="functionName" value="" style="width:100"/></td>
    <td class="form_label"><fmt:message key="systemId"/></td><td><s:select name="aclFunction.systemId" id="systemId"
                        list="@com.xuchunchun.abframe.web.combo.AclChildSystemCombo@getChildSystems()" 
                        headerKey="" headerValue="请选择"
                        listKey="id.systemId" listValue="systemName" theme="simple" cssClass="height:10000;width:100"/>
	</td>
	</tr>
	<tr>
	  <td colspan="6">
	<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
    <!-- <input type="hidden" name="clause._orderby__id_functionId" id="functionId_order" value="A"/> -->
    <input type="hidden" name="clause.functionName" id="functionName_q" value="like"/>
    </td>
</tr>
    </table>                                
    </div>

<sc:grid id="grid" url="${path}aclFunction/functionlist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   <sc:toolBar type="ADD" text="add" id="aclFunctionupdateadd" name="aclFunction_add" icon="add"  url="${path}aclFunction/toadd.action"></sc:toolBar>
   <sc:toolBar type="UPDATE" id="aclFunctionupdate" url="${path}aclFunction/detail.action" name="aclFunction_update" text="update" icon="modify" entityName="'aclFunction'" struct="['id.bankorgId','id.functionId']"></sc:toolBar>
   <sc:toolBar type="DELETE" text="delete" icon="delete" url="${path}aclFunction/delete.action" entityName="'aclFunctions'" struct="['id.bankorgId','id.functionId']"></sc:toolBar>
   
   <sc:column name="functionId" id="functionId" display="functionId"  render="function(item){return item.id.functionId;}"></sc:column>
    <sc:column name="functionName" id="functionName" display="functionName"  ></sc:column>
     <sc:column name="systemId" id="systemId" display="systemId"  ></sc:column>
      <sc:column name="funcUrl" id="funcUrl" display="funcUrl" ></sc:column>
      
   
</sc:grid>

