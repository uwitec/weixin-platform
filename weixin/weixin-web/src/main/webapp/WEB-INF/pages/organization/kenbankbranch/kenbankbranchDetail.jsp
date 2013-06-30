<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>
</title>
	<meta content="text/html;charset=UTF-8" http-equiv="Content-Type"/>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>
    <script type="text/javascript">
    	$(function(){
    		encodeURI($("#brBranchName").val());
    	});
    </script>
</head>
<body style="padding:10px;"> 
<div id="search">
	<div class="search-bar-top">
		<div><sc:message key="rlpcountrydetail"/></div>
	</div>
</div>
    <sc:form action="${path}kenBankbranch/${detailTag==null?'add.action':'update.action'}" id="form1" method="post">
<div>
</div>
        <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
           
            <tr>
                <td class="form_label"><sc:message key="brBranchId"/>:</td>
                <td>
                    <input name="kenBankbranch.id.bankorgId" value="${kenBankbranch.id.bankorgId }" type="hidden" />
                   <sc:text name="kenBankbranch.id.brBranchId" id="brBranchId" value="${kenBankbranch.id.brBranchId}" type="text" ltype="text" validate="{required:true,number:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
                 </td>
                <td class="form_label"><sc:message key="brBranchName"/>:</td>
                <td>
                   <sc:text name="kenBankbranch.brBranchName" id="brBranchName" value="${kenBankbranch.brBranchName}" validate="{maxlength:40}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="brCostCentre"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.brCostCentre" id="brCostCentre" value="${kenBankbranch.brCostCentre}" validate="{number:true,maxlength:10}" type="text" ltype="text" ></sc:text>
                </td>
              </tr>

              <tr>
                <td class="form_label"><sc:message key="branchLevel"/>:</td>
                <td>
                   <sc:text name="kenBankbranch.branchLevel" id="branchLevel" value="${kenBankbranch.branchLevel}" validate="{number:true,maxlength:3}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="parentBranchId"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.parentBranchId" id="parentBranchId" value="${kenBankbranch.parentBranchId}" validate="{number:true,maxlength:10}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnPhyCountry"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnPhyCountry" id="bnPhyCountry" value="${kenBankbranch.bnPhyCountry}" validate="{maxlength:3}" type="text" ltype="text" ></sc:text>
                </td>
             </tr>
             
             <tr>
                <td class="form_label"><sc:message key="bnCntryCode"/>:</td>
                <td>
                   <sc:text name="kenBankbranch.bnCntryCode" id="bnCntryCode" value="${kenBankbranch.bnCntryCode}" validate="{maxlength:3}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnPhyPostcd"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnPhyPostcd" id="bnPhyPostcd" value="${kenBankbranch.bnPhyPostcd}" validate="{maxlength:10}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnPhyAddr"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnPhyAddr" id="bnPhyAddr" value="${kenBankbranch.bnPhyAddr}" validate="{maxlength:40}" type="text" ltype="text" ></sc:text>
                </td>
             </tr>
             
             <tr>
                <td class="form_label"><sc:message key="bnPhyState"/>:</td>
                <td>
                   <sc:text name="kenBankbranch.bnPhyState" id="bnPhyState" value="${kenBankbranch.bnPhyState}" validate="{maxlength:20}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnPhyCity"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnPhyCity" id="bnPhyCity" value="${kenBankbranch.bnPhyCity}" validate="{maxlength:20}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnTelephoneNo1"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnTelephoneNo1" id="bnTelephoneNo1" value="${kenBankbranch.bnTelephoneNo1}" validate="{maxlength:15}" type="text" ltype="text" ></sc:text>
                </td>
             </tr>
             
             <tr>
                <td class="form_label"><sc:message key="bnTelephoneNo2"/>:</td>
                <td>
                   <sc:text name="kenBankbranch.bnTelephoneNo2" id="bnTelephoneNo2" value="${kenBankbranch.bnTelephoneNo2}" validate="{maxlength:15}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnTelex1"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnTelex1" id="bnTelex1" value="${kenBankbranch.bnTelex1}" validate="{maxlength:15}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnTelex2"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnTelex2" id="bnTelex2" value="${kenBankbranch.bnTelex2}" validate="{maxlength:15}" type="text" ltype="text" ></sc:text>
                </td>
             </tr>
             
             <tr>
                <td class="form_label"><sc:message key="bnContactPerson"/>:</td>
                <td>
                   <sc:text name="kenBankbranch.bnContactPerson" id="bnContactPerson" value="${kenBankbranch.bnContactPerson}" validate="{maxlength:20}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnContactTitle"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnContactTitle" id="bnContactTitle" value="${kenBankbranch.bnContactTitle}" validate="{maxlength:20}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"><sc:message key="bnEmail"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnEmail" id="bnEmail" value="${kenBankbranch.bnEmail}" validate="{maxlength:40}" type="text" ltype="text" ></sc:text>
                </td>
             </tr>
             
             <tr>
                <td class="form_label"><sc:message key="bnWebsiteUrl"/>:</td>
                <td>
                	<sc:text name="kenBankbranch.bnWebsiteUrl" id="bnWebsiteUrl" value="${kenBankbranch.bnWebsiteUrl}" validate="{maxlength:40}" type="text" ltype="text" ></sc:text>
                </td>
                <td class="form_label"></td>
                <td>
                </td>
                <td class="form_label"></td>
                <td>
                </td>
             </tr>
              
            <tr style="height:50px">
                 <td colspan="6">
				 <input type="submit" value="<sc:message key="submit"/>" id="subButton" style = "float:right;margin:4px;" class="l-button l-button-submit" />  
				</td> 
		    </tr> 
        </table>

    </sc:form>
</body>    
</html>