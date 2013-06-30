<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
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
		  		<div><sc:message key="kenEmpbranchdetail"/></div>
		  	</div>
   </div>
    <sc:form action="${path}kenEmpbranch/${detailTag==null?'add.action':'update.action'}" id="form1">
       <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
                <tr>
                <td class="form_label"><fmt:message key="employeeId"/>:</td>
                <td align="left" class="l-table-edit-td">
                <input name="kenEmpbranch.id.bankorgId" value="${kenEmpbranch.id.bankorgId }" type="hidden" />
                <s:select name="kenEmpbranch.id.employeeId" 
	                  list="@com.xuchunchun.abframe.web.combo.KenEmployeeCombo@getKenEmployees()" 
	                    headerKey="" headerValue="请选择"
	                    listKey="id.employeeId" listValue="employeeName" theme="simple" cssClass="height:10000"/>
                </td>
                <td class="form_label"><fmt:message key="brBranchId"/>:</td>
                 <td align="left" class="l-table-edit-td">
                    <s:select name="kenEmpbranch.id.brBranchId" 
	                    list="@com.xuchunchun.abframe.web.combo.KenBankBranchCombo@getKenBankbranchs()" 
	                    headerKey="" headerValue="请选择"
	                    listKey="id.brBranchId" listValue="brBranchName" theme="simple" cssClass="height:10000"/>
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