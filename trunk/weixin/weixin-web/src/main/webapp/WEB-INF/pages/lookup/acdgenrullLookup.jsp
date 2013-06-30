<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>该流水下已有的生成规则</title>
</head>
<body style="overflow-x:hidden;">
   <div id="search" class="search-bar-top">
 <!--     <sc:message key="cardProductId"/><input type="text" name="acdCardnorange.id.cardProductId" id="cardProductId" value=""/>
     <sc:message key="genNo"/><input type="text" name="acdCardnorange.id.genNo" id="genNo" value=""/>
     <input type="hidden" name="clause._orderby__id_cardProductId" id="cardProductId_order" value="A"/>
  -->     
   </div>
  <input type="hidden" name="genNo" value="${acdCardnogenrull.id.genNo}" id="genNo"/>
  <input type="hidden" name="cardProductId" value="${acdCardnogenrull.id.cardProductId}" id="cardProductId"/> 
   <sc:grid id="grid" url="${path}acdLookup/acdgenrullList.action?acdCardnogenrull.id.genNo=${acdCardnogenrull.id.genNo}&acdCardnogenrull.id.cardProductId=${acdCardnogenrull.id.cardProductId}"
    height="400" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" name="query" icon="search" ></sc:toolBar>
      <sc:column name="genRull" id="genRull" display="seqgenlaw" render="function(item){return item.id.genRull;}"></sc:column>
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
                <div style="text-align:right;float:right;width:15px">&nbsp;</div>
                <div style="text-align:right;float:right;">
                    <input type="button" value="新增规则" name="newRull" id="newRull" class="l-button l-button-submit" style="width:80"/> 
                </div>
            </td>
        </tr> 
    </table>
    
    <script>
        var reData = {'buttonType':'','genRull':''};//点击的按钮，卡产品ID，流水号，结束号
        $(function(){
            $("#close").bind('click',function(){
                reData.buttonType = "close";
                window.returnValue=reData;
                window.close();
            });
            $("#clear,#newRull").bind('click',function(){
                reData.buttonType = "clear";
                window.returnValue=reData;
                window.close();
            });
            $("#subButton").bind('click',function(){
                var obj = g; //这个是表格的grid
                var selected = obj.getSelected();
                if(selected==null){$.ligerDialog.warn("请选择一行数据");return;}
                
                reData.buttonType = "submit";
                reData.genRull = selected.id.genRull;
                var cardProductId = $("#cardProductId").val();
                var genNo = $("#genNo").val();
                var genRull = reData.genRull;
                genRull = genRull.replace('#','%23'); //#不能通过url传递，需转换
                
                urlGet = "${path}acdCardRoGenRull/getLastCardNo.action?acdCardnogenrull.id.cardProductId="
                    +cardProductId+"&acdCardnogenrull.id.genNo="
                    +genNo+"&acdCardnogenrull.id.genRull="+genRull;
                    //alert(urlGet);
                $.ajax({
                    url:urlGet,
                    type:'GET',
                    cache:false,
                    success:function(data){
                        var da = eval('('+ data +')');
                        reData.endData = da.result;
                        window.returnValue=reData;
                        window.close();
                    }
                });
                
            });
        });
    </script>
    
</body>
</html>