<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>卡号段管理</title>
</head>
<body style="overflow-x:hidden;">
   <div id="search" class="search-bar-top">
     <sc:message key="cardProductId"/><input type="text" name="acdCardnorange.id.cardProductId" id="cardProductId" value=""/>
     <sc:message key="genNo"/><input type="text" name="acdCardnorange.id.genNo" id="genNo" value=""/>
     <input type="hidden" name="clause._orderby__id_cardProductId" id="cardProductId_order" value="A"/>
   </div>
   <sc:grid id="grid" url="${path}acdLookup/acdCardnorangeList.action" height="400" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" name="query" icon="search" ></sc:toolBar>
      
      <sc:column name="cardProductId" id="cardProductId" display="cardNoRanger.card_product_id" render="function(item){return item.id.cardProductId;}"></sc:column>
      <sc:column name="genNo" id="genNo" display="cardNoRanger.gen_no" render="function(item){return item.id.genNo;}"></sc:column>
      <sc:column name="cardNumberClass" id="cardNumberClass" display="cardNoRanger.card_number_class"></sc:column>
      
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
        var reData = {'buttonType':'','productId':'','genNo':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
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
                reData.productId = selected.id.cardProductId;
                reData.genNo = selected.id.genNo;
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
    
</body>
</html>