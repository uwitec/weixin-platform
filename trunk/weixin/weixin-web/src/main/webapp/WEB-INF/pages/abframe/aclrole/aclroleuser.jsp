<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   
</head>
<body style="overflow:hidden; padding:3px；">
    <input type="hidden" value="${aclRole.id.roldId }" id="u_roldId"/>
    
    <div id="maingridhas" style="margin:0; padding:0;overflow:hidden;"></div>
    
    <div id="search" style="margin-top:10px;width:450" class="search-bar-top">
        <fmt:message key="userId"/><input type="text" name="userId" id="userId" value="" style="width:100"/><!--
        功能名称<fmt:message key="functionName"/><input type="text" name="functionName" id="functionName" value="" style="width:100"/>
        系统ID<input type="text" name="systemId" id="systemId" value=""  style="width:100"/>
    --></div>
    <div id="maingridall" style="margin:0; padding:0;overflow:hidden;"></div>
 <div style="display:none;">
</div>

 <script type="text/javascript">
            
        var u_roldId = $('#u_roldId').val();
            
         uhg = $("#maingridhas").ligerGrid({
                height:220,
                width:450,
                url:'<%=request.getContextPath()%>/aclRole/userList.action?aclUserrole.id.roldId='+u_roldId, //action
                root:'rows',
                record:'total',
                columns: [
                { display: '用户ID', name: 'userId', align: 'center', width:100},
                { display: '员工ID', name: 'employeeId', width: 100},
                { display: '员工姓名', name: 'employeeName',width:130 },
                { display: '分行', name: 'brBranchId',width:300 }
                ], pageSize:10 ,rownumbers:true,
                toolbar: { items: [
                { text: '已包含用户' },
                { line: true },
                { text: '删除', click: p_deleteRow, icon: 'delete'}
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{}]
            });
            //删除rolefunc
            function p_deleteRow(){
               var obj = uhg; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclRole/deleteUser.action'; //action
               var delData = {'aclUserrole\.id\.roldId':u_roldId,
                               'aclUserrole\.id\.userId':selected.userId};//删除的数据
               $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:delData,
                   url:url,
                   success: function () {
                       obj.loadData(true);
                       abf.showSuccess('删除成功');
                   },
                   error: function (message) {
                       abf.showError(message);
                   }
                });
            };
            
            uallg =
            $("#maingridall").ligerGrid({
                height:220,
                width:450,
                url:'<%=request.getContextPath()%>/aclRole/userListAll.action', //action
                root:'rows',
                record:'total',
                columns: [
                { display: '用户ID', name: 'userId', align: 'center', width:100},
                { display: '员工ID', name: 'employeeId', width: 100},
                { display: '员工姓名', name: 'employeeName',width:130 },
                { display: '分行', name: 'brBranchId',width:300 }
                ], pageSize:10 ,rownumbers:true,
                toolbar: { items: [
                { text: '所有用户'},
                { line: true },
                { text: '查询', click: p_getlist, icon: 'search' },
                { line: true },
                { text: '为用户添加角色', click: p_toadd, icon: 'add' }
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{name:'aclUser\.userId',value:''}]
            });
            $("#pageloading").hide();
       
         function p_getlist(){
              var obj = uallg; //设置操作的对象
            //设置查询参数
              var newparams = [{name:'aclUser\.userId',value:$('#userId').val()}
                              ];
              abf.setParms(obj,newparams);
              obj.loadData(true); 
          };
        
         function p_toadd(){
            var obj = uallg; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var addData = {'aclUserrole\.id\.roldId':u_roldId,
                               'aclUserrole\.id\.userId':selected.userId};//添加的数据
            var url = '<%=request.getContextPath()%>/aclRole/addUser.action';
             $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:addData,
                   url:url,
                   success: function () {
                       uhg.loadData(true);
                       abf.showSuccess('添加成功');
                   },
                   error: function (message) {
                       abf.showError(message);
                   }
                });
         };
         
    </script>
</body>
</html>