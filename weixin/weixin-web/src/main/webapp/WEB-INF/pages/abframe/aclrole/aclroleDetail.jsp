<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
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
    
    <script type="text/javascript"> 
        $(function(){
           var form = $('form:first');
           abf.validateBindForm(form);
           
           $('#subButton').click(function(){
                if(!form.valid()){return;}; //验证判断，注意form要在前面先定义
                //封装数据
                var url = form.attr('action');
                var subString = form.serialize();
                var ajaxData = {};
                ajaxData.type = 'POST';
                ajaxData.url = url;
                ajaxData.data = subString;
                ajaxData.success = function(){abf.showSuccess();};
                ajaxData.error = function(){abf.showError();};
                $.ajax(ajaxData);
                //form.ajaxSubmit();
                return false;
            });
        });
        
            function f_cancel(){};
            function f_save(){};
          
          
    </script> 

</head>
<body style="padding:10px;"> 
<div id="search">
  			<div class="search-bar-top">
		  		<div><sc:message key="aclRoledetail"/></div>
		  	</div>
   </div>
    <form name="form1" method="post" action="<%=request.getContextPath()%>/aclRole/${detailTag==null?'add.action':'update.action'}" id="form1">
<div>
</div>
       <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
            <tr>
                <td class="form_label"><fmt:message key="roldId"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="aclRole.id.bankorgId" value="${aclRole.id.bankorgId }" type="hidden" />
                    <%-- <input name="aclRole.id.roldId" value="${aclRole.id.roldId }" type="text" id="pd_roldId" ltype="text" validate="{required:true,maxlength:10}" ${detailTag==null?'':'readonly'}/></td> --%>
					<sc:text name="aclRole.id.roldId" id="pd_roldId" value="${aclRole.id.roldId}" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
                <td class="form_label"><fmt:message key="roldName"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input type="text" name="aclRole.roldName" value="${aclRole.roldName }" id="pd_roldName" ltype="text" validate="{required:true,maxlength:20}" />
                </td>
            </tr>
            <tr>
                <td class="form_label"><fmt:message key="roleType"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <sc:dict name="aclRole.roleType" dictId="roleType" value="${aclRole.roleType }" empty="false" defaultValue="M"></sc:dict>
                </td>
            </tr>
            <tr>   
                <td colspan="4">
<input type="submit" value="<fmt:message key="submit"/>" id="subButton" style = "float:right;margin:4px;" class="l-button l-button-submit" />
</td> 
           </tr>
        </table>
    </form>
</body>    
</html>