<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <script type="text/javascript">
        
        $(function ()
        {
            g =
            $("#maingrid").ligerGrid({
                height:'100%',
                width:'100%',
                url:'<%=request.getContextPath()%>/abframeLookup/functionlist.action', //action
                root:'rows',
                record:'total',
                columns: [
                { display: '功能ID', name: 'functionId', align: 'center', width:100,render:function(item){return item.id.functionId;}},
                { display: '功能名称', name: 'functionName', width: 200},
                { display: '系统ID', name: 'systemId',width:130 },
                { display: 'URL地址', name: 'funcUrl',width:140 }
                ], pageSize:10 ,rownumbers:true,
                toolbar: { items: [
                { text: '查询', click: p_getlist, icon: 'search' }
                ]},
                //可以放任何数据({name:'',value:''}对象数据),name只能是单个字符串（'entity.\fieldname'），name和value不能是对象
                parms:[{}]
            });
            $("#pageloading").hide();
            
        });
         function p_getlist(){
              var obj = g; //设置操作的对象
            //设置查询参数
              var newparams = [{name:'aclFunction\.id\.functionId',value:$('#functionId').val()},
                               {name:'aclFunction\.functionName',value:$('#functionName').val()},
                               {name:'aclFunction\.systemId',value:$('#systemId').val()}
                              ];
              abf.setParms(obj,newparams);
              obj.loadData(true); 
          };
        function f_select()
        {
            return g.getSelectedRow();
        }    
    </script>
</head>
<body style="overflow:hidden;">
     <div id="search" class="search-bar-top">
        功能ID<input type="text" name="functionId" id="functionId" value="" style="width:100"/>
        功能名称<input type="text" name="functionName" id="functionName" value="" style="width:100"/>
        系统ID<s:select name="aclFunction.systemId" id="systemId"
                        list="@com.xuchunchun.abframe.web.combo.AclChildSystemCombo@getChildSystems()" 
                        headerKey="" headerValue="请选择"
                        listKey="id.systemId" listValue="systemName" theme="simple" cssClass="height:10000;width:100"/>
    </div>

    <div id="maingrid" style="margin:0; padding:0;overflow:hidden;"></div>
 <div style="display:none;">
  
</div>
 
</body>
</html>