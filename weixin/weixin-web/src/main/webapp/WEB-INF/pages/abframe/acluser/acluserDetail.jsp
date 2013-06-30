<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
/*	//var DepartmentData = { Rows: [], Total: 0 };
	var EmployeeData = { Rows: [], Total: 0 };    
	//var DepartmentList = DepartmentData.Rows;
        var sexData = [{ Sex: 1, text: '男' }, { Sex: 2, text: '女'}];
        $(f_initGridMac);
        var managerMac, gMac;
        function f_initGridMac()
        { 
        	gMac =  managerMac = $("#macAddr").ligerGrid({
                columns: [
                { display: 'MAC地址', name: 'aclUsermacs.maccode',
                    editor: { type: 'text' }
                }
                ],
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                data:EmployeeData,
                width: '100%'
            });   
        }
        
        function deleteMacRow()
        { 
        	managerMac.deleteSelectedRow();
        }
        var newrowid = 100;
        function addMacNewRow()
        {
            var row = managerMac.getSelectedRow();
            //参数1:rowdata(非必填)
            //参数2:插入的位置 Row Data 
            //参数3:之前或者之后(非必填)
            managerMac.addRow({ 
                maccode: "MAC"
            }, row, false);
        } 
        function getMacSelected()
        { 
            var row = managerMac.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getMacData()
        { 
            var data = managerMac.getData();
            alert(JSON.stringify(data));
            return JSON.stringify(data);
        } 
        
        */
    </script>
    
    <script type="text/javascript">
	//var DepartmentData = { Rows: [], Total: 0 };
/*		var EmployeeData = { Rows: [], Total: 0 };    
	//var DepartmentList = DepartmentData.Rows;
        var sexData = [{ Sex: 1, text: '男' }, { Sex: 2, text: '女'}];
        $(f_initGridIp);
        var managerIp, gIp;
        function f_initGridIp()
        { 
           gIp =  managerIp = $("#ipAddr").ligerGrid({
                columns: [
                { display: 'IP地址', name: 'aclUserips.ipaddress',
                    editor: { type: 'text' }
                }
                ],
                onSelectRow: function (rowdata, rowindex)
                {
                    $("#txtrowindex").val(rowindex);
                },
                enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
                data:EmployeeData,
                width: '100%'
            });   
        }
        
        function deleteIpRow()
        { 
        	managerIp.deleteSelectedRow();
        }
        var newrowid = 100;
        function addIpNewRow()
        {
            var row = managerIp.getSelectedRow();
            //参数1:rowdata(非必填)
            //参数2:插入的位置 Row Data 
            //参数3:之前或者之后(非必填)
            managerIp.addRow({ 
                ipaddress: "IP"
            }, row, false);
        } 
        function getIpSelected()
        { 
            var row = managerIp.getSelectedRow();
            if (!row) { alert('请选择行'); return; }
            alert(JSON.stringify(row));
        }
        function getIpData()
        { 
            var data = managerIp.getData();
            alert(JSON.stringify(data));
            return JSON.stringify(data);
        } */
    </script>
    <script type="text/javascript">
 /*   	function getMacAndIp() {
    		document.getElementById("maccode").value=getMacData();
    		document.getElementById("ipaddress").value=getIpData();
    		alert(document.getElementById("maccode").value);
    		alert(document.getElementById("ipaddress").value);
    	}*/
    </script>
    
</head>
<body style="padding:10px;">
<div id="layout" style="width:100%">
    <div position="left" title="用户信息" id="mainmenu">
    <form name="form1" method="post" action="<%=request.getContextPath()%>/aclUserAction/${detailTag==null?'add.action':'update.action'}" id="form1">
		<input type="hidden" name="maccode" id="maccode" />
		<input type="hidden" name="ipaddress" id="ipaddress" />
<table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >   
         <tr>
                <td class="form_label"><fmt:message key="userId"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <%-- <input name="aclUser.userId" value="${aclUser.userId }" type="text" id="userId" ltype="text" validate="{required:true,maxlength:10}" ${detailTag==null?'':'readonly'}/></td> --%>
                    <sc:text name="aclUser.userId" id="userId" value="${aclUser.userId}" type="text" ltype="text" validate="{required:true,maxlength:10}" readonly="${detailTag==null?'':'readonly'}"></sc:text>
            </tr>
           
            <tr>
                <td class="form_label"><fmt:message key="employeeId"/>:</td>
                <td align="left" class="l-table-edit-td">
                	
                    <input type="text" value="${aclUser.employeeId }" id="employeeId" validate="{required:true,maxlength:10}" ltype="text" />
                    <input name="aclUser.employeeId" value="${aclUser.employeeId }" type="hidden" id="d_employeeId" />
            </tr>   
             <tr>
               <td class="form_label"><fmt:message key="authMode"/>:</td>
                <td align="left" class="l-table-edit-td">
                <sc:dict dictId="authMode" name="aclUser.authMode" empty="false" value="${aclUser.authMode}"/>
                </td>
            </tr>
            <tr>
                <td class="form_label"><fmt:message key="userStatus"/>:</td>
                <td align="left" class="l-table-edit-td">
                     <sc:dict dictId="userStatus" name="aclUser.userStatus" empty="false" value="${aclUser.userStatus}"/>     
                </td>
            </tr>  
            <tr>
               <td class="form_label" style="width:200px;"><fmt:message key="loginStrategy"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <s:select name="aclUser.loginStrategy" 
                    list="@com.xuchunchun.abframe.web.combo.AclLoginStrategyCombo@getloginStrategys()" 
                    listKey="id.loginStrategy" listValue="strategyName" theme="simple" cssStyle="width:133"/>
                </td>
            </tr>
            <tr>
               <td class="form_label"><fmt:message key="bankorg_id"/>:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="aclUser.bankorgId" value="${aclUser.bankorgId }" type="text" id="bankorgId"  ltype="text" validate="{number:true,maxlength:10}"/>
                </td>
            </tr>
            <tr>
                 <td colspan="2">
<input type="submit" value="<fmt:message key="submit"/>" id="subButton" style = "float:right;margin:4px;" class="l-button l-button-submit" /> 
            </td>
            </tr>
        </table>
    </form>
</div>


<div position="center" title="<fmt:message key="detailInfo"/>" style="width:100%;height:100%"> 
<c:if test="${detailTag!=null }" >
<div id="navtab1" style="width: 450;overflow:hidden; border:1px solid #A3C0E8;height:500 ">

<div  title="<fmt:message key="loginMemory"/>" tabid="log" lselected="true">
    <div id="user_log" style="margin:0px;padding:0;height:420">
        <iframe frameborder="0" style="height:420" name="showmessage" src="<%=request.getContextPath()%>/aclUserAction/logforword.action?aclUser.userId=${aclUser.userId}"></iframe>
    </div>
</div>

<div tabid="role" title="<fmt:message key="userRole"/>" >
    <div id="user_role" style="margin:0px;padding:0;height:420">
        <iframe frameborder="0" style="height:100%" name="showmessage" src="<%=request.getContextPath()%>/aclUserAction/roleforword.action?aclUser.userId=${aclUser.userId}"></iframe>
    </div>
</div>

<div  title="<fmt:message key="userMAC"/>" tabid="mac" >
<div id="user_mac" style="margin:0;padding:0;height:420;"></div>
</div>
<div  title="<fmt:message key="userIP"/>" tabid="ip">
<div id="user_ip" style="margin:0;padding:0;height:420;"></div>
</div>
</div>
 
</c:if>
 </div>

 <script type="text/javascript"> 
     
        $(function(){
            
            $("#navtab1").ligerTab();
            var layout = $("#layout").ligerLayout({ leftWidth: 320,allowLeftResize:false });
            
            var form1 = $('#form1');
           abf.validateBindForm(form1);
           
           $('#subButton').click(function(){
                if(!form1.valid()){return;}; //验证判断，注意form要在前面先定义
                //封装数据
                var url = form1.attr('action');
                var subString = form1.serialize();
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
            
            var urlrole = '<%=request.getContextPath()%>/aclUserAction/roleforword.action?aclUser.userId='+$('#userId').val();
            var urllog = '<%=request.getContextPath()%>/aclUserAction/logforword.action?aclUser.userId='+$('#userId').val();
            var urlmac = '<%=request.getContextPath()%>/aclUserAction/macforword.action?aclUser.userId='+$('#userId').val();
            var urlip = '<%=request.getContextPath()%>/aclUserAction/ipforword.action?aclUser.userId='+$('#userId').val();             
             
             $("#navtab1").ligerTab({ onBeforeSelectTabItem: function (tabid)
             {
                if("role" == tabid){
                    $('#user_role').load(urlrole);
                }
                if("mac" == tabid){
                    $('#user_mac').load(urlmac);
                }
                if("ip" == tabid){
                    $('#user_ip').load(urlip);
                }
             }
            });
        });
        
        //选择员工
        $("#employeeId").ligerComboBox({
            onBeforeOpen: f_selectContact, valueFieldID: 'hidCustomerID',width:300
        });
        
        function f_selectContact()
        {
            $.ligerDialog.open({ title: '选择功能', name:'winselector',width: 600, height: 400, url: '<%=request.getContextPath()%>/abframeLookup/employeeto.action', buttons: [
                { text: '确定', onclick: f_selectContactOK },
                { text: '清空', onclick: f_selectContactClean },
                { text: '取消', onclick: f_selectContactCancel }
            ]
            });
            return false;
        }
        function f_selectContactOK(item, dialog)
        {
            var fn = dialog.frame.f_select || dialog.frame.window.f_select; 
            var data = fn(); 
            if (!data)
            {
                alert('请选择行!');
                return;
            }
            $("#employeeId").val(data.employeeName);
            $("#d_employeeId").val(data.id.employeeId);
            dialog.close();
        }
        function f_selectContactClean(item, dialog){
            $("#employeeId").val('');
            $("#d_employeeId").val('');
            dialog.close();
        }
        function f_selectContactCancel(item, dialog)
        {
            dialog.close();
        }
        
        
    </script> 
 
</body>    
</html>