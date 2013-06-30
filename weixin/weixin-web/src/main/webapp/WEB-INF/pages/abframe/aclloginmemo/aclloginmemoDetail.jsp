<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
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
    <sc:form action="${path}aclLoginmemoAction/${detailTag==null?'add.action':'update.action'}" id="form1">
        <table cellpadding="0" cellspacing="0" class=" form_table" >
            <tr>
               <td>
                 <input type="hidden" name="detailTag" id="detailTag" value="${detailTag }" />
                  
              
                  <label><fmt:message key="userId"/></label>
                  <sc:text name="aclLoginmemo.id.userId" id="userId" value="${aclLoginmemo.id.userId}" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
               </td><td align="left"></td>
            </tr>   
             <tr>
             <td>
               <label><fmt:message key="loginSeq"/></label>
               <sc:text name="aclLoginmemo.id.loginSeq" id="loginSeq" value="${aclLoginmemo.id.loginSeq }" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
             </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="loginDate"/></label>
               <sc:text name="aclLoginmemo.loginDate" id="loginDate" value="${aclLoginmemo.loginDate }" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
             </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="authMode"/></label>
                <sc:dict dictId="authMode" name="aclLoginmemo.authMode" empty="false" value="${aclLoginmemo.authMode}"/>    
                     </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="userStatus"/></label>
               <sc:text name="aclLoginmemo.userStatus" id="oldChangeCounts" value="${aclLoginmemo.userStatus }" type="text" ltype="text" ></sc:text>
            <sc:dict dictId="userStatus" name="aclLoginmemo.userStatus" empty="false" value="${aclLoginmemo.userStatus}"/>  
             </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="errCount"/></label>
               <sc:text name="aclLoginmemo.errCount" id="oldChangeCounts" value="${aclLoginmemo.errCount }" type="text" ltype="text" ></sc:text>
             </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="loginStrategy"/></label>
               <sc:text name="aclLoginmemo.loginStrategy" id="oldChangeCounts" value="${aclLoginmemo.loginStrategy }" type="text" ltype="text" ></sc:text>
             </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="loginResult"/></label>
                <sc:dict dictId="loginResult" name="aclLoginmemo.loginResult" empty="false" value="${aclLoginmemo.loginResult}"/> 
                 </td>
             </tr>
               <tr>
             <td>
               <label><fmt:message key="reasonType"/></label>
                <sc:dict dictId="reasonType" name="aclLoginmemo.reasonType" empty="false" value="${aclLoginmemo.reasonType}"/>   
                 </td>
             </tr>
              <tr>
             <td>
               <label><fmt:message key="loginMemo"/></label>
               <sc:text name="aclLoginmemo.loginMemo" id="oldChangeCounts" value="${aclLoginmemo.loginMemo }" type="text" ltype="text" ></sc:text>
             </td>
             </tr>
            <tr style="height:50px">
         <td colspan="99" style="padding:20px 20px 0 0;"><div style="text-align:right;float:right;"><input type="submit" value="<fmt:message key="submit"/>" id="subButton" class="l-button l-button-submit" /> </div></td> 
        </tr>
        </table>
 </sc:form>
</body>    
</html>
