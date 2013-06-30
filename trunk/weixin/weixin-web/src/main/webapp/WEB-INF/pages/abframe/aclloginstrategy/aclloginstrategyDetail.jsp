<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
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
    <div id="search">
  			<div class="search-bar-top">
		  		<div><sc:message key="AclLoginstrategydetail"/></div>
		  	</div>
   </div>
    <sc:form action="${path}AclLoginstrategyAction/${detailTag==null?'add.action':'update.action'}" id="form1">
        <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
            <tr>
               <td class="form_label">
                 <input type="hidden" name="detailTag" id="detailTag" value="${detailTag }" />
                 <input name="aclLoginstrategy.id.bankorgId" value="${aclLoginstrategy.id.bankorgId }" type="hidden" />
              
                  <label><fmt:message key="loginStrategy"/></label> </td><td> 
                  <sc:text name="aclLoginstrategy.id.loginStrategy" id="loginStrategy" value="${aclLoginstrategy.id.loginStrategy}" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
                  </td>
           
             <td class="form_label">
               <label><fmt:message key="strategyName"/></label> </td><td> 
               <sc:text name="aclLoginstrategy.strategyName" id="strategyName" value="${aclLoginstrategy.strategyName }" type="text" ltype="text" validate="{required:true,maxlength:10}" ></sc:text>
             </td>
                          </tr>
              <tr>
             <td class="form_label">
               <label><fmt:message key="macCheck"/></label> </td><td> 
               <sc:dict dictId="YorN" name="aclLoginstrategy.macCheck" id="macCheck" empty="false" value="${aclLoginstrategy.macCheck}"/> 
                 </td>

				<td class="form_label">
               <label><fmt:message key="ipCheck"/></label> </td><td> 
                <sc:dict dictId="YorN" name="aclLoginstrategy.ipCheck" id="ipCheck"  empty="false" value="${aclLoginstrategy.ipCheck}"/> 
             </td>
                          </tr>
              <tr>
            <td class="form_label">
               <label><fmt:message key="listCheck"/></label> </td><td> 
                <sc:dict dictId="YorN" name="aclLoginstrategy.listCheck" id="listCheck"  empty="false" value="${aclLoginstrategy.listCheck}"/>
             </td>

            <td class="form_label">
               <label><fmt:message key="listType"/></label> </td><td> 
                  <sc:dict dictId="listType" name="aclLoginstrategy.listType" id="listType"  empty="false" value="${aclLoginstrategy.listType}"/>
             </td>
             </tr>
            <tr style="height:50px">
               <td colspan="4">
<input type="submit" value="<fmt:message key="submit"/>" id="subButton" style = "float:right;margin:4px;" class="l-button l-button-submit" /> 
</td> 
       </tr>
        </table>
 </sc:form>
</body>    
</html>
