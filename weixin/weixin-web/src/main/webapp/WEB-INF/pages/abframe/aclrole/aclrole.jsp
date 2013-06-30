<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
  
    <style type="text/css">
    .l-panel td.l-grid-row-cell-editing { padding-bottom: 2px;padding-top: 2px;}
    </style>
</head>
<body style="padding:2px;height:100%; text-align:center;">
  
  <div id="layout" style="width:100%">
    <div position="left" title="<fmt:message key="rolelist"/>" id="mainmenu">
    <div id="search" class="search-bar-top">
	   <fmt:message key="roldId"/><input type="text" name="roldId" id="roldId" value="" style="width:90"/>
	     <fmt:message key="roldName"/><input type="text" name="roldName" id="roldName" value="" style="width:90"/>
	 </div>
        <form id="mainform" >
        <div id="maingrid"  style="margin:2px;" style="height:40%"></div> 
        </form>
     </div>
    <div position="center" title="<fmt:message key="detailInfo"/>" style="width:100%"> 
        <div id="formtable" style="border:1px,solid;margin:10px">
        </div>
    </div>
  </div>
  
  <script type="text/javascript">
 
  var layout = $("#layout").ligerLayout({ leftWidth: 320 });

  $(function ()
 {
     g =
     $("#maingrid").ligerGrid({
         height:'100%',
         width:320,
         url:'<%=request.getContextPath()%>/aclRole/list.action', //action
         root:'rows',
         record:'total',
         columns: [
         { display: '角色ID', name: 'roldId', align: 'left', width: 128, minWidth: 60, render:function(item){return item.id.roldId;}},
         { display: '角色名称', name: 'roldName', align: 'left', width: 128, minWidth: 60}
         ], pageSize:30 ,rownumbers:true,
         toolbar: { items: [
         { text: '查询', click: p_getlist, icon: 'search' },
         { line: true },
         { text: '增加', click: p_toadd, icon: 'add' },
         { line: true },
         { text: '修改', click: p_detail, icon: 'modify' },
         { line: true },
         { text: '删除', click: p_deleteRow, icon: 'delete'},
         { line: true },
         { text: '功能', click: p_function, icon: 'archives'},
         { line: true },
         { text: '菜单', click: p_menu, icon: 'menu'},
         { line: true },
         { text: '用户', click: p_user, icon: 'memeber'}
         
         ]},
         //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
         parms:[{name:'aclRole\.id\.roldId',value:''}]
     });
     $("#pageloading").hide();
     
 });
  function p_getlist(){
         var obj = g; //设置操作的对象
         var newparams = [{name:'aclRole\.id\.roldId',value:$('#roldId').val()},{name:'aclRole\.roldName',value:$('#roldName').val()}];//设置查询参数
         abf.setParms(obj,newparams);
         obj.loadData(true); 
     };
    
     function p_detail(){
    	 /* 	 var obj = g; //操作的表格对象
         var selected = obj.getSelected();//取得行数据
         if(selected==null){$.ligerDialog.warn("请选择一行");return;}

           var queryString = 'aclRole.id.bankorgId='+selected.bankorgId;
      
         var dialogInfo = {};
         dialogInfo.title = '系统参数详细信息';
         dialogInfo.url = '<%=request.getContextPath()%>/aclRole/detail.action?'+queryString;
         dialogInfo.height = '480';
         dialogInfo.width = '800';
         $.ligerDialog.open(dialogInfo);*/
    	 
    	 
        var obj = g; //操作的表格对象
        var selected = obj.getSelected();//取得行数据
        if(selected==null || obj.getCheckedRows().length!=1){$.ligerDialog.warn("请单选一行记录");return;}

        var queryString = 'aclRole.id.bankorgId='+selected.id.bankorgId;
        queryString += '&aclRole.id.roldId='+selected.id.roldId;
        abf.tabClose('aclRoleDetail');
        top.f_addTab('aclRoleDetail', '修改角色', '<%=request.getContextPath()%>/aclRole/detail.action?'+queryString);
     };
     function p_toadd(){
    	abf.tabClose('aclRoleDetail');
        top.f_addTab('aclRoleDetail', '新增角色', '<%=request.getContextPath()%>/aclRole/toadd.action');
     };
  function p_deleteRow(){
        var obj = g; //操作的表格对象
        var selected = obj.getSelected();//取得行数据
        if(selected==null || obj.getCheckedRows().length!=1){$.ligerDialog.warn("请单选一行记录");return;}
        if(!confirm("确认删除角色?")){return false;}
        var url = '<%=request.getContextPath()%>/aclRole/delete.action'; //action
        var delData = {'aclRole\.id\.bankorgId':selected.id.bankorgId,'aclRole\.id\.roldId':selected.id.roldId};//删除的数据
        
        $.ajax({
            cache:false,//设置false，不缓存页面
            data:delData,
            url:url,
            success: function () {
            	p_getlist();
                abf.showSuccess('删除成功');
            },
            error: function (message) {
                //abf.showError(message);
                abf.showError();
            }
         });
     }; 
     function p_function(){
    	 var obj = g; //操作的表格对象
         var selected = obj.getSelected();//取得行数据
         if(selected==null){$.ligerDialog.warn("请选择一行");return;}
         var queryString = "?aclRole.id.roldId=" + selected.id.roldId;
         $("#formtable").load('<%=request.getContextPath()%>/aclRole/aclrolefunction.action'+queryString);
     }
     function p_menu(){
         var obj = g; //操作的表格对象
         var selected = obj.getSelected();//取得行数据
         if(selected==null){$.ligerDialog.warn("请选择一行");return;}
         var queryString = "?aclRole.id.roldId=" + selected.id.roldId;
         $("#formtable").load('<%=request.getContextPath()%>/aclRole/aclrolemenu.action'+queryString);
     }
     function p_user(){
    	 var obj = g; //操作的表格对象
         var selected = obj.getSelected();//取得行数据
         if(selected==null){$.ligerDialog.warn("请选择一行");return;}
         var queryString = "?aclRole.id.roldId=" + selected.id.roldId;
         $("#formtable").load('<%=request.getContextPath()%>/aclRole/aclroleuser.action'+queryString);
     }
  </script>
</body>
</html> 
