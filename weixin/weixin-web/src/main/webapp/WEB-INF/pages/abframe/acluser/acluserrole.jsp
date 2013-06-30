<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
   
</head>
<body style="overflow:hidden;">
    <div class="search-bar-top" style="padding-top:3px">
        <s:select name="aclRole_roldId" id="aclRole_roldId"
           list="@com.xuchunchun.abframe.web.combo.AclRoleCombo@getChildSystems()" 
           headerKey="" headerValue="新增 请选择"
           listKey="id.roldId" listValue="roldName" theme="simple" cssClass="height:10000;width:130"/>
    </div>
    
    <input type="hidden" value="${aclUser.userId }" id="r_userId"/>
    <div id="maingridrole"></div>
 <script type="text/javascript">
            
        var r_userId = $('#r_userId').val();
            
         function p_getlist(){
              var obj = urhg; //设置操作的对象
            //设置查询参数
              var newparams = [{name:'aclUser\.userId',value:$('#userId').val()}
                              ];
              abf.setParms(obj,newparams);
              obj.loadData(true); 
          };
        
         function p_add(dialog){
            
            var r_roldId = $('#aclRole_roldId').val();
            if(r_roldId == null||r_roldId==''){
                abf.showError("请选择新增的角色");
                return false;
            }
               var addData = {'aclUserrole\.id\.roldId':r_roldId,
                               'aclUserrole\.id\.userId':r_userId};//添加的数据
            var url = '<%=request.getContextPath()%>/aclUserAction/addrole.action';
             $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:addData,
                   url:url,
                   success: function () {
                       urhg.loadData(true);
                       abf.showSuccess('添加成功');
                       $('#aclRole_roldId').val('');
                   },
                   error: function (message) {
                       abf.showError(message);
                       dialog.close();
                   }
                });
            };
         
       var  urhg = $("#maingridrole").ligerGrid({
                height:'99%',
                width:'100%',
                url:'<%=request.getContextPath()%>/aclUserAction/rolelist.action?aclUser.userId='+r_userId, //action
                root:'rows',
                record:'total',
                columns: [
                { display: '角色ID', name: 'roldId', align: 'center', width:100,render:function(item){return item.id.roldId;}},
                { display: '角色名称', name: 'roldName', align: 'center',width: 100}
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
               var obj = urhg; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null||obj.getCheckedRows().length!=1){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclUserAction/deleterole.action'; //action
               var delData = {'aclUserrole\.id\.roldId':selected.id.roldId,
                               'aclUserrole\.id\.userId':r_userId};//删除的数据
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