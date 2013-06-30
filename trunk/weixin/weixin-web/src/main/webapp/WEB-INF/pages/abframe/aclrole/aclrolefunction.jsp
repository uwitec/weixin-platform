<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   
</head>
<body style="overflow:hidden; padding:6px；">
    <input type="hidden" value="${aclRole.id.roldId }" id="f_roldId"/>
    
    <div id="maingridhas" style="margin:0; padding:0;overflow:hidden;"></div>
    
    <div id="search" style="margin-top:10px;width:450" class="search-bar-top">
        <fmt:message key="functionId"/><input type="text" name="functionId" id="functionId" value="" style="width:80"/>
        <fmt:message key="functionName"/><input type="text" name="functionName" id="functionName" value="" style="width:80"/>
        <fmt:message key="systemId"/>
        <s:select name="systemId" id="systemId"
              list="@com.xuchunchun.abframe.web.combo.AclChildSystemCombo@getChildSystems()" 
              headerKey="" headerValue="select"
              listKey="id.systemId" listValue="systemName" theme="simple" cssClass="height:10000"/>
    </div>
    <div id="maingridall" style="margin:0; padding:0;overflow:hidden;"></div>
 <div style="display:none;">
</div>

 <script type="text/javascript">
            
        var f_roldId = $('#f_roldId').val();

         hg = $("#maingridhas").ligerGrid({
                height:220,
                width:450,
                url:'<%=request.getContextPath()%>/aclRole/listfunction.action?aclRolefunc.id.roldId='+f_roldId, //action
                root:'rows',
                record:'total',
                columns: [
                { display: '功能ID', name: 'functionId', align: 'center', width:100,render:function(item){return item.id.functionId;}},
                { display: '功能名称', name: 'functionName', width: 200},
                { display: '系统ID', name: 'systemId',width:130 },
                { display: 'URL地址', name: 'funcUrl',width:300 }
                ], pageSize:10 ,rownumbers:true,
                toolbar: { items: [
                { text: '已包含功能' },
                { line: true },
                { text: '删除', click: p_deleteRow, icon: 'delete'}
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{}]
            });
            //删除rolefunc
            function p_deleteRow(){
               var obj = hg; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclRole/deletefunction.action'; //action
               var delData = {'aclRolefunc\.id\.roldId':f_roldId,
                               'aclRolefunc\.id\.functionId':selected.id.functionId};//删除的数据
               //alert(url);
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
            
            allg =
            $("#maingridall").ligerGrid({
                height:220,
                width:450,
                url:'<%=request.getContextPath()%>/aclRole/listfunctionlistAll.action', //action
                root:'rows',
                record:'total',
                columns: [
                { display: '功能ID', name: 'functionId', align: 'center', width:100,render:function(item){return item.id.functionId;}},
                { display: '功能名称', name: 'functionName', width: 200},
                { display: '系统ID', name: 'systemId',width:130 },
                { display: 'URL地址', name: 'funcUrl',width:300 }
                ], pageSize:10 ,rownumbers:true,
                toolbar: { items: [
                { text: '所有功能'},
                { line: true },
                { text: '查询', click: p_getlist, icon: 'search' },
                { line: true },
                { text: '增加给角色', click: p_toadd, icon: 'add' }
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{name:'aclFunction\.id\.functionId',value:''}]
            });
            $("#pageloading").hide();
       
         function p_getlist(){
              var obj = allg; //设置操作的对象
            //设置查询参数
              var newparams = [{name:'aclFunction\.id\.functionId',value:$('#functionId').val()},
                               {name:'aclFunction\.functionName',value:$('#functionName').val()},
                               {name:'aclFunction\.systemId',value:$('#systemId').val()}
                              ];
              abf.setParms(obj,newparams);
              obj.loadData(true); 
          };
        
         function p_toadd(){
            var obj = allg; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var addData = {'aclRolefunc\.id\.roldId':f_roldId,
                               'aclRolefunc\.id\.functionId':selected.id.functionId};//添加的数据
            var url = '<%=request.getContextPath()%>/aclRole/addfunction.action';
             $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:addData,
                   url:url,
                   success: function () {
                       hg.loadData(true);
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