<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
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
    <script src="${path }ui/pwdstrength.js" type="text/javascript"></script>
    <link href="${path }css/pwdstrength.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript"> 
        $(function(){
           var form = $('form:first');
           abf.validateBindForm(form);
           
           $('#subButton').click(function(){
                if(!form.valid()){return;}; //验证判断，注意form要在前面先定义
                if($('#newPassword').val()!=$('#newPassword2').val()){
                    alert("请确认新密码一致");
                    return false;
                }
                if(EvalPwd($("#newPassword").val())<=1) {
                	alert("密码强度太低！");
                    return false;
                }
                
                var data = {'aclUser.userId':$('#userId').val(),currentPassword:$('#currentPassword').val(),destPassword:$('#newPassword').val()};
                            
                //var url = "<%=request.getContextPath()%>/aclUserAction/changepassword.action";
                var url = $('#form1').attr('action');
                $.ajax({
                    cache:false,
                    url:url,
                    data:data,
                    success:function(da){
                        var data = eval('('+da+')');
                        if(data.ok == "OK"){
                            alert("操作成功");
                        }else if(data.ok == "currentWrong"){
                            alert("输入的当前密码不正确");
                            return;
                        }else if(data.ok == "historyWrong"){
                            alert("和最近"+$('#oldChangeCounts').val() +"的历史密码重复，请更改新密码");
                        }
                    },
                    error:function(){
                        alert("系统错误，请联系管理员");                
                    }
                });
                return false;
            });
        });
                
    </script> 

</head>
<body style="padding:10px;">
<div style="height: auto;">
<div id="tab" class="search-bar-top">
	<div>密码修改</div>
</div>
<form name="form1" method="post" action="<%=request.getContextPath()%>/aclUserAction/changepassword.action" id="form1">
<div>
        <table width="100%" cellspacing="0px" cellpadding="0px" align="center" class="form_table">
           
            <tr>
                <td class="form_label">当前密码:</td>
                <td align="left" class="l-table-edit-td">
                    <input type="hidden" name="userId" type="text" id="userId" value="${aclUser.userId }" ltype="text" />
                    <input type="hidden" name="oldChangeCounts" type="text" id="oldChangeCounts" value="${aclSysparameter.oldChangeCounts }" ltype="text" />
                    <input type="password" name="currentPassword" type="text" id="currentPassword" ltype="text" validate="{required:true}" />
                </td><td align="left"></td>
            </tr>
             <c:if test="${aclSysparameter.chgPwdOldChk=='Y'}"> 
	            <tr>
	                <td class="form_label">新密码规则:</td>
	                <td style="font-size:12;color: red;">新密码不能和最近的${aclSysparameter.oldChangeCounts }次密码相同 </td>
	            </tr>
	        </c:if>
            <tr>
                <td class="form_label">新密码:</td>
                <td align="left" class="l-table-edit-td">
                	<input name="newPassword"  id="newPassword" type="password" size="32" validate="{required:true}" onKeyUp="EvalPwd(this.value)" onchange="EvalPwd(this.value);"/>
                	<table width=280 border="0" cellspacing="0" cellpadding="0">
						<tr height=20>
							<td width=17% class="pwd_default" id="soweak" align=center>非常低</td>
							<td width=14% class="pwd_default" id="weak" align=center>低</td>
							<td width=13% class="pwd_default" id="middleweak" align=center>中低</td>
							<td width=11% class="pwd_default" id="middle" align=center>中</td>
							<td width=16% class="pwd_default" id="middlestrong" align=center>中高</td>
							<td width=12% class="pwd_default" id="strong" align=center>高</td>
							<td width=17% class="pwd_default" id="Perfect" align=center>完美</td>
						</tr>
					</table>
                </td>
                <td align="left"></td>
            </tr>
            <tr>
                <td class="form_label">密码确认:</td>
                <td align="left" class="l-table-edit-td"><input name="newPassword2"  id="newPassword2" type="password" validate="{required:true}"/></td>
                <td align="left"></td>
            </tr>
            <tr>
            	<td colspan="99" style="padding-right:50px;">
            		<div style="text-align:right;float:right;"><input type="submit" value="<fmt:message key="submit"/>" id="subButton" class="l-button l-button-submit" /></div>
            	</td>
            </tr>
        </table>
    </div>
</form>
</div>
</body>    
</html>