<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body style="overflow-x:hidden; overflow-y:hidden;padding:2px;">
<div class="l-loading" style="display:block" id="pageloading1"></div>
<input type="hidden" value="${aclUser.userId }" id="l_userId" />
 <div id="maingridlog"></div>
  
 <script type="text/javascript">
        $("#pageloading1").hide();
        var l_userId = $("#l_userId").val();
        l_url = '<%=request.getContextPath()%>/aclLoginmemoAction/querylist.action?aclLoginmemo.id.userId='+l_userId;
        $(function ()
        {
            $("#pageloading1").hide();
            g =
            $("#maingridlog").ligerGrid({
                checkbox:false,
                height:'99%',
                width:'100%',
                url:l_url, //action
                root:'rows',
                record:'total',
                columns: [
                { display: '登录序号', name: 'loginSeq', width: 100,render:function(item){return item.id.loginSeq;} },
                { display: '登录日期', name: 'loginDate', width: 150,render:function(item){if(item.loginDate!=null) return new Date(parseInt(item.loginDate.time)).format("yyyy-MM-dd hh:mm:ss");} },
                { display: '密码校验方式', name: 'authMode',width:80 },
                  { display: '用户状态', name: 'userStatus', width: 80 },
                { display: '登录校验错误次数', name: 'errCount', width: 100 },
                { display: '用户登录策略', name: 'loginStrategy',width:80 },
                  { display: '登录结果', name: 'loginResult', width: 80 },
                { display: '失败原因类型', name: 'reasonType', width: 100 }
           //     { display: '登录备忘', name: 'loginMemo',width:130 }
                //{ display: '最大登录校验错误次数', name: 'maxErrCount',width:140 }
                ], pageSize:30 ,rownumbers:true,
                toolbar: { items: [
           //     { text: '查询', click: p_getlist, icon: 'search' }
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[] //{name:'aclLoginmemo\.id\.userId',value:''}
            });
            $("#pageloading1").hide();
        });
        /*
         function p_getlist(){
                var obj = g; //设置操作的对象
                var newparams = [{name:'aclLoginmemo\.id\.userId',value:$('#userId').val()},{name:'aclLoginmemo\.id\.loginSeq',value:$('#loginSeq').val()}];//设置查询参数
                abf.setParms(obj,newparams);
                obj.loadData(true); 
            };*/
        
    </script>
</body>
</html>