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
                url:'<%=request.getContextPath()%>/abframeLookup/employeelist.action', //action
                root:'rows',
                record:'total',
                columns: [
                { display: '员工ID', name: 'employeeId', align: 'center', width:130,render:function(item){return item.id.employeeId;}},
                { display: '员工名称', name: 'employeeName', width: 130},
                { display: '分行', name: 'brBranchId',width:130 },
                { display: '性别', name: 'gender',width:130 }
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
              var newparams = [{name:'kenEmployee\.id\.employeeId',value:$('#employeeId').val()},
                               {name:'kenEmployee\.employeeName',value:$('#employeeName').val()},
                               {name:'kenEmployee\.brBranchId',value:$('#brBranchId').val()}
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
        员工ID<input type="text" name="employeeId" id="employeeId" value="" style="width:100"/>
        员工名称<input type="text" name="employeeName" id="employeeName" value="" style="width:100"/>
        分行<input type="text" name="brBranchId" id="brBranchId" value="" style="width:100"/>
    </div>

    <div id="maingrid" style="margin:0; padding:0;overflow:hidden;"></div>
 <div style="display:none;">
  
</div>
 
</body>
</html>