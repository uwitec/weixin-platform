<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/common.jsp"%>

<div id="search" class="search-bar-top">
  <fmt:message key="brBranchId"/><input type="text" name="kenBankbranch.id.brBranchId" id="brBranchId" value=""/>
  <input type="hidden" name="clause._orderby__id_brBranchId" id="brBranchId_order" value="A"/>
   <input type="hidden" name="clause.brBranchName" id="brBranchName_q" value="like"/>    
</div>

<sc:grid id="grid" url="${path}kenBankbranch/querylist.action" height="90%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   
   <sc:column name="id.brBranchId" id="id.brBranchId" display="brBranchId"  render="function(item){return item.id.brBranchId;}"></sc:column>
   <sc:column name="brBranchName" id="brBranchName" display="brBranchName"></sc:column>
   <sc:column name="brCostCentre" id="brCostCentre" display="brCostCentre"></sc:column>
   <sc:column name="branchLevel" id="branchLevel" display="branchLevel"></sc:column>
   <sc:column name="parentBranchId" id="parentBranchId" display="parentBranchId"></sc:column>
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
        var reData = {'buttonType':'','brBranchId':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
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
                reData.brBranchId = selected.id.brBranchId;
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