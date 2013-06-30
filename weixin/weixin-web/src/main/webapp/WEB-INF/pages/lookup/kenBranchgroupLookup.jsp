<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="resource.message"/>
<%@ include file="/common.jsp"%>

<div id="search">
<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
  <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
<tr>
  <td class="form_label"><fmt:message key="branchGroupId"/></td><td><input type="text" name="kenBranchgroup.id.branchGroupId" id="branchGroupId" value=""/></td>
  <td class="form_label"><fmt:message key="branchGroupName"/></td><td><input type="text" name="kenBranchgroup.branchGroupName" id="branchGroupName" value=""/></td>
  </tr>
  <tr>
  <td colspan="4">
  <input type="hidden" name="clause._orderby__id_branchGroupId" id="branchGroupId_order" value="A"/>
   <input type="hidden" name="clause.branchGroupName" id="branchGroupName_q" value="like"/>
<input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<fmt:message key="search"/>" onclick="p_getlist();" />
   </td></tr></table>   
</div>

<sc:grid id="grid" url="${path}kenBranchgroup/querylist.action" height="99%" width="100%" root='rows' record='total'>
   <sc:toolBar type="QUERY" text="query" icon="search" ></sc:toolBar>
   
   <sc:column name="id.branchGroupId" id="id.branchGroupId" display="branchGroupId"  render="function(item){return item.id.branchGroupId;}"></sc:column>
   <sc:column name="branchGroupName" id="branchGroupName" display="branchGroupName"></sc:column>
   <sc:column name="areaCode" id="areaCode" display="areaCode"></sc:column>
   <sc:column name="areaLength" id="areaLength" display="areaLength"></sc:column>
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
        var reData = {'buttonType':'','branchGroupId':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
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
                reData.branchGroupId = selected.id.branchGroupId;
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