<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

	<div id="search" class="search-bar-top">
     <sc:message key="APPL_PRODUCT_ID"/><input type="text" name="acdAppProduct.id.applProductId" id="applProductId" value=""/>
     <input type="hidden" name="clause._orderby__id_applProductId" id="applProductId_order" value="A"/>
     <input type="hidden" name="clause._id_applProductId" id="applProductId_q" value="like"/>
   </div>
   <sc:grid id="grid" url="${path}acdAppProductcode/querylist.action" height="99%" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" name="query" icon="search" ></sc:toolBar>

      <sc:column name="bankorgId" id="bankorgId" display="bankorg_id" render="function(item){return item.id.bankorgId;}"></sc:column>
      <sc:column name="applProductId" id="applProductId" display="APPL_PRODUCT_ID" render="function(item){return item.id.applProductId;}"></sc:column>
      <sc:column name="productTypeId" id="productTypeId" display="PRODUCT_TYPE_ID" ></sc:column>
      <sc:column name="applProductName" id="applProductName" display="APPL_PRODUCT_NAME" ></sc:column>
      <sc:column name="limitProcessType" id="limitProcessType" display="LIMIT_PROCESS_TYPE" ></sc:column>
      <sc:column name="forceAuditInd" id="forceAuditInd" display="FORCE_AUDIT_IND" ></sc:column>
      <sc:column name="limitAdditionInd" id="limitAdditionInd" display="LIMIT_ADDITION_IND" ></sc:column>
      <sc:column name="limitPeriodType" id="limitPeriodType" display="LIMIT_PERIOD_TYPE" ></sc:column>
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
        var reData = {'buttonType':'','applProductId':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
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
                reData.applProductId = selected.id.applProductId;
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