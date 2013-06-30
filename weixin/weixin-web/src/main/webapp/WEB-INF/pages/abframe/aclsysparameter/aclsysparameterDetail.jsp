<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
    <style type="text/css">
        body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:50px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
    
</head>
    <body style="padding:10px;"> 
    <div id="search">
  			<div class="search-bar-top">
		  		<div><sc:message key="caclSysparameterdetail"/></div>
		  	</div>
   </div>
    <sc:form action="${path}aclSysparameterAction/${detailTag==null?'add.action':'update.action'}" id="form1">
    <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
            <tr>
              <td class="form_label"><fmt:message key="bankorg_id"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <%-- <input name="aclSysparameter.bankorgId" value="${aclSysparameter.bankorgId }" type="text" id="txtName" type="text" validate="{required:true,number:true,maxlength:10}" ${detailTag==null?'':'readonly'}/></td> --%>
					<sc:text name="aclSysparameter.bankorgId" id="bankorgId" value="${aclSysparameter.bankorgId}" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
               <td class="form_label"><fmt:message key="fstPwdChange"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <sc:dict dictId="YorN" name="aclSysparameter.fstPwdChange" empty="false" value="${aclSysparameter.fstPwdChange }"/>
                </td>
            </tr>   
             <tr>
             <td class="form_label"><fmt:message key="chgPwdOldChk"/>:</td>
                <td align="left" class="l-table-edit-td">
                 <sc:dict dictId="YorN" name="aclSysparameter.chgPwdOldChk" empty="false" value="${aclSysparameter.chgPwdOldChk}"/>
                 </td>

              <td class="form_label"><fmt:message key="oldChangeCounts"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <input id="oldChangeCounts" type="text" name="aclSysparameter.oldChangeCounts" value="${aclSysparameter.oldChangeCounts }"  type="text" validate="{number:true,maxlength:10}"/></td>
            </tr>  
                 
            <tr>
               <td class="form_label"><fmt:message key="maxErrCount"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="aclSysparameter.maxErrCount" id="maxErrCount" value="${aclSysparameter.maxErrCount}" type="text"  type="text" validate="{number:true,maxlength:10}"/>
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