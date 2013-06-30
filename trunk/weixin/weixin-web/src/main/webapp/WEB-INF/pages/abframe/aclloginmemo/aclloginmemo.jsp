<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<div id="search">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
<tr>
     <td class="form_label"><fmt:message key="userId"/></td><td><input type="text" name="aclLoginmemo.id.userId" id="id.userId" value=""/></td>
       <td class="form_label"><fmt:message key="loginSeq"/></td><td><input type="text" name="aclLoginmemo.id.loginSeq" id="id.loginSeq" value=""/></td>
       </tr>
       <tr><td colspan="4">
       <!--  <input type="hidden" name="clause._orderby__id_userId" id="userId_order" value="A"/>-->
       <input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
       </td>
       </tr>
       </table>
</div>

<sc:grid id="grid" url="${path}aclLoginmemoAction/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   
   <sc:column name="aclLoginmemo.id.userId" id="id.userId" display="userId"  render="function(item){return item.id.userId;}"></sc:column>
    <sc:column name="aclLoginmemo.id.loginSeq" id="id.loginSeq" display="loginSeq"  render="function(item){return item.id.loginSeq;}"></sc:column>
     <sc:column name="loginDate" id="loginDate" display="loginDate"  ></sc:column>
    <sc:column name="authMode" id="authMode" display="authMode"  render="function(item){if(item.authMode!=null) if('S' == item.authMode)return 'SHA'; else if('M' == item.authMode) return 'MD5'; }"></sc:column>
     <sc:column name="userStatus" id="userStatus" display="userStatus"  render="function(item){if(item.userStatus!=null) if('U' == item.userStatus)return '新用户'; else if('N' == item.userStatus) return '正常' ; else if('C' == item.userStatus) return '注销'; else if('L' == item.userStatus) return '锁定';}"></sc:column>
      <sc:column name="errCount" id="errCount" display="errCount" ></sc:column>
      <sc:column name="loginStrategy" id="loginStrategy" display="loginStrategy" ></sc:column>
      <sc:column name="loginResult" id="loginResult" display="loginResult" render="function(item){if(item.loginResult!=null) if('S' == item.loginResult)return '成功'; else if('F' == item.loginResult) return '失败' ;}"></sc:column>
      <sc:column name="reasonType" id="reasonType" display="reasonType" render="function(item){if(item.reasonType!=null) if('A' == item.reasonType)return '密码校验失败'; else if('B' == item.reasonType) return 'MAC限制' ; else if('C' == item.reasonType) return 'IP限制'; else if('D' == item.reasonType) return '黑名单限制';}"></sc:column>
      <sc:column name="loginMemo" id="loginMemo" display="loginMemo" ></sc:column>
</sc:grid>

