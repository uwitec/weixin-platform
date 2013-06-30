<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/common.jsp"%>

<div id="search" class="search-bar-top">
  <fmt:message key="employeeId"/><input type="text" name="kenEmployee.id.employeeId" id="employeeId" value=""/>
</div>

<sc:grid id="grid" url="${path}workflow/toTaskReassignEmp.action?taskId=${taskId }&workflowName=${workflowName }" height="90%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   
   <sc:column name="id.employeeId" id="id.employeeId" display="employeeId"  render="function(item){return item.id.employeeId;}"></sc:column>
   <sc:column name="brBranchId" id="brBranchId" display="brBranchId" ></sc:column>
   <sc:column name="employeeName" id="employeeName" display="employeeName" ></sc:column>
</sc:grid>

<table cellpadding="0" cellspacing="0" class="form_table " width="100%">
        <tr style="height:50px">
            <td colspan="99" style="padding:10px 15px 10px 0;">
                <div style="text-align:right;float:right;">
                    <input type="button" name="close" id="close" value="关闭" class="l-button l-button-submit" /> 
                </div>
                <div style="text-align:right;float:right;width:15px">&nbsp;</div>
                <div style="text-align:right;float:right;">
                   <input type="button" name="clear" id="clear" value="清空" class="l-button l-button-submit" /> 
                </div> 
                <div style="text-align:right;float:right;width:15px">&nbsp;</div>
                <div style="text-align:right;float:right;">
	                <input type="button" value="确认" name="subButton" id="subButton" class="l-button l-button-submit" /> 
                </div>
            </td>
        </tr> 
    </table>
    
    
    
     <script>
        var reData = {'buttonType':'','employeeId':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
        $(function(){
            $("#close").bind('click',function(){
                reData.buttonType = "close";
                window.returnValue=reData;
                window.close();
            });
            $("#clear").bind('click',function(){
                reData.buttonType = "clear";
                window.returnValue=reData;
                window.close();
            });
            $("#subButton").bind('click',function(){
                var obj = g; //这个是表格的grid
		        var selected = obj.getSelected();
		        if(selected==null){$.ligerDialog.warn("请选择一行数据");return;}
		        
                reData.buttonType = "submit";
                reData.employeeId = selected.id.employeeId;
                /*
                urlGet = "${path}acdCardRoGenRull/getLastCardNo.action?acdCardnorange.id.cardProductId="+selected.id.cardProductId+"&acdCardnorange.id.genNo="+selected.id.genNo;
                $.ajax({
                    url:urlGet,
                    type:'GET',
                    async:'false',
                    success:function(data){
                        var da = eval('('+ data +')');
                        reData.endData = da.result;
                        window.returnValue=reData;
                        window.close();
                    }
                });*/
                window.returnValue=reData;
                window.close();
            });
        });
    </script>