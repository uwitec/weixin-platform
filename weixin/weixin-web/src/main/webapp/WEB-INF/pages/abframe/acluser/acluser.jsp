<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="${path }ui/acluserDetail.css" rel="stylesheet" type="text/css" />
	<script src="${path }ui/acluserDetail.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function ()
        {
            g =
            $("#maingrid").ligerGrid({
                height:'99%',
                width:'100%',
                url:'<%=request.getContextPath()%>/aclUserAction/querylist.action', //action
                root:'rows',
                record:'total',
                columns: [
                { display: '用户ID', name: 'userId', align: 'center', width:150 },
                { display: '员工ID', name: 'employeeId', width: 130 },
                { display: '密码校验方式', name: 'authMode', width: 100 ,render:function(item){if(item.authMode!=null) if("S" == item.authMode)return "SHA"; else if("M" == item.authMode) return "MD5"; }},
                { display: '用户状态', name: 'userStatus',width:100, render:function(item){if(item.userStatus!=null) if("U" == item.userStatus)return "新用户"; else if("N" == item.userStatus) return "正常" ; else if("C" == item.userStatus) return "注销"; else if("L" == item.userStatus) return "锁定";}},
                { display: '锁定解锁时间', name: 'unlocktime',width:140 },
                { display: '上次登录时间', name: 'lastlogin',width:140 },
                { display: '登录校验错误次数', name: 'errCount',width:140 },
                { display: '用户登录策略', name: 'loginStrategy',width:140 }
                ], pageSize:30 ,rownumbers:true,
                toolbar: { items: [
                { text: '查询', click: p_getlist, icon: 'search' },
                { line: true },
                { text: '增加', click: p_toadd, icon: 'add' },
                { line: true },
                { text: '修改', click: p_detail, icon: 'modify' },
                { line: true },
                { text: '删除', click: p_deleteRow, icon: 'delete'  },
                { line: true },
                { text: '锁定', click: p_lockuser, icon: 'lock'  },
                { line: true },
                { text: '解锁', click: p_unlockuser, icon: 'unlock'  },
                { line: true },
                { text: '重置密码', click: p_resetpassword, icon: 'reset'  },
                { line: true },
                { text: '强制退出', click: p_logout, icon: 'unlock'  }
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{name:'aclUser\.userId',value:''}]
            });
            $("#pageloading").hide();
            
        });
         function p_getlist(){
                var obj = g; //设置操作的对象
                var newparams = [{name:'aclUser\.userId',value:$('#userId').val()},{name:'aclUser\.employeeId',value:$('#employeeId').val()}];//设置查询参数
                abf.setParms(obj,newparams);
                obj.loadData(true); 
            };
           
            function p_detail(){
               var obj = g; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null || obj.getCheckedRows().length!=1){$.ligerDialog.warn("请单选一行记录");return;}
               var queryString = 'aclUser.userId='+selected.userId;
            
               var dialogInfo = {};
               dialogInfo.title = '系统参数详细信息';
               dialogInfo.url = '<%=request.getContextPath()%>/aclUserAction/detail.action?'+queryString;
               dialogInfo.height = '480';
               dialogInfo.width = '800';
               dialogInfo.showMax = 'true';
               dialogInfo.showMin = 'true';
               dialogInfo.slide = 'true';
               abf.tabClose('aclUserDetail');
               top.f_addTab('aclUserDetail', '修改用户信息', '<%=request.getContextPath()%>/aclUserAction/detail.action?'+queryString);
               var loc = "<%=request.getContextPath()%>/aclUserAction/detail.action?"+queryString;
             //  addToDown(loc);
            };
            
            function p_toadd(){
                abf.tabClose('aclUserDetail');
                top.f_addTab('aclUserDetail', '新增用户', '<%=request.getContextPath()%>/aclUserAction/toadd.action');
                var loc = "<%=request.getContextPath()%>/aclUserAction/toadd.action";
             //   addToDown(loc);
            };
            
            //向下扩展的方式
            function addToDown(url){
            	$("#getupdate").empty().append(
            	$("#<iframe id='addFrm' frameborder='0' align='top' scrolling='no' width='100%' height='100%'>").attr("src",url));
            	$("#acluser_body").css("height","1320px");
            }
            
         	function p_deleteRow(){
               var obj = g; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclUserAction/delete.action'; //action
               var delData = {'aclUser\.userId':selected.userId};//删除的数据
               
               $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:delData,
                   url:url,
                   success: function () {
                       p_getlist();
                       abf.showSuccess('删除成功');
                   },
                   error: function (message) {
                       abf.showError(message);
                   }
                });
            };
            
            function p_lockuser(){
               var obj = g; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclUserAction/lockuser.action'; //action
               var delData = {'aclUser\.userId':selected.userId};//重置的数据
               
               $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:delData,
                   url:url,
                   success: function () {
                       p_getlist();
                       abf.showSuccess('成功锁定用户');
                   },
                   error: function (message) {
                       abf.showError(message);
                   }
                });
            }
            
            function p_unlockuser(){
               var obj = g; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclUserAction/unlockuser.action'; //action
               var delData = {'aclUser\.userId':selected.userId};//重置的数据
               
               $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:delData,
                   url:url,
                   success: function () {
                       p_getlist();
                       abf.showSuccess('成功解除锁定');
                   },
                   error: function (message) {
                       abf.showError(message);
                   }
                });
            }
            
            function p_resetpassword(){
               var obj = g; //操作的表格对象
               var selected = obj.getSelected();//取得行数据
               if(selected==null){$.ligerDialog.warn("请选择一行");return;}
               var url = '<%=request.getContextPath()%>/aclUserAction/resetpassword.action'; //action
               var delData = {'aclUser\.userId':selected.userId};//重置的数据
               
               $.ajax({
                   cache:false,//设置false，不缓存页面
                   data:delData,
                   url:url,
                   success: function () {
                       p_getlist();
                       abf.showSuccess('成功重置密码');
                   },
                   error: function (message) {
                       abf.showError(message);
                   }
                });
            }
            
            //强制退出
           function p_logout() {
            	var obj = g; //操作的表格对象
                var selected = obj.getSelected();//取得行数据
                if(selected==null){$.ligerDialog.warn("请选择一行");return;}
                var url = '<%=request.getContextPath()%>/aclUserAction/forceLogout.action'; //action
                var delData = {'aclUser\.userId':selected.userId};//重置的数据
                
                $.ajax({
                    cache:false,//设置false，不缓存页面
                    data:delData,
                    url:url,
                    success: function () {
                        p_getlist();
                        abf.showSuccess('强制退出成功');
                    },
                    error: function (message) {
                        abf.showError(message);
                    }
                 });
            }
    </script>
</head>
<body style="overflow-x:hidden; padding:2px;" id="acluser_body">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <div id="search" class="search-bar-top">
        <fmt:message key="userId"/><input type="text" name="userId" id="userId" value=""/>
        <fmt:message key="employeeId"/><input type="text" name="employeeId" id="employeeId" value=""/>
 </div>
 <div class="l-clear"></div>
    <div id="maingrid"></div>
 <div style="display:none;">
  
</div>
<div id="getupdate" valign="top">
	
</div> 
</body>
</html>