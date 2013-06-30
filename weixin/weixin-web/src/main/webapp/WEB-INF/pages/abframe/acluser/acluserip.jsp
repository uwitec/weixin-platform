<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   
</head>
<body style="overflow:hidden; padding:6px；">
    <div class="search-bar-top">
       <fmt:message key="newIPaddress"/><input type="text" name="user_ip_addr" id="user_ip_addr"/>
    </div>
    <input type="hidden" value="${aclUser.userId }" id="i_userId"/>
    <div id="maingridip"></div>
 <script type="text/javascript">
            
        var i_userId = $('#i_userId').val();
                    
         function p_add(dialog){
            
            var user_ip = $('#user_ip_addr').val();
            if(user_ip == null||user_ip==''){
                abf.showError("请输入新增的IP地址");
                return false;
            }
               var addData = {'aclUserips\.id\.ipaddress':user_ip,
                               'aclUserips\.id\.userId':i_userId};//添加的数据
            var url = '<%=request.getContextPath()%>/aclUserAction/addip.action';
             $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:addData,
                   url:url,
                   success: function () {
                       iacg.loadData(true);
                       abf.showSuccess('添加成功');
                       $('#aclRole_roldId').val('');
                   },
                   error: function (message) {
                       abf.showError(message);
                       dialog.close();
                   }
                });
            };
         
       var  iacg = $("#maingridip").ligerGrid({
                height:'99%',
                width:'100%',
                url:'<%=request.getContextPath()%>/aclUserAction/iplist.action?aclUser.userId='+i_userId, //action
                root:'rows',
                record:'total',
                columns: [
                { display: 'IP地址', name: 'ipaddress', align: 'center', width:200,render:function(item){return item.id.ipaddress;}}
                ], pageSize:10 ,rownumbers:true,
                toolbar: { items: [
                { text: '新增' , click: p_add,icon: 'add'},
                { line: true },
                { text: '删除', click: p_deleteRow, icon: 'delete'}
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{}]
            });
         
         //删除rolefunc
            function p_deleteRow(){
               var obj = iacg; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclUserAction/deleteip.action'; //action
               var delData = {'aclUserips\.id\.ipaddress':selected.id.ipaddress,
                               'aclUserips\.id\.userId':i_userId};//删除的数据
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
    </script>
    
</body>
</html>