<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
   <div id="search" style = "margin-bottom:5px;">
   <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
     <tr>
    <td class="form_label"> <sc:message key="bankorg_id"/></td><td><input type="text" name="acdDocrequestmapping.id.bankorgId" id="bankorgId" value=""/></td>
    <td class="form_label"> <sc:message key="documentCode"/></td><td><input type="text" name="acdDocrequestmapping.id.documentCode" id="documentCode" value=""/></td>
   	</tr>
  <tr>
     <td colspan="4">
     <input type="hidden" name="clause._orderby__id_documentCode" id="documentCode_order" value="A"/>
     <input type="hidden" name="clause._id_documentCode" id="documentCode_q" value="like"/>
     <input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<sc:message key="search1"/>" onclick="p_getlist();" />
</td></tr></table>
   </div>
   <sc:grid id="grid" url="${path}acdDocrequestmapping/querylist.action" height="90%" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" name="query" icon="search" ></sc:toolBar>

      <sc:column name="bankorgId" id="bankorgId" display="bankorg_id" render="function(item){return item.id.bankorgId;}"></sc:column>
      <sc:column name="documentCode" id="documentCode" display="documentCode" render="function(item){return item.id.documentCode;}" hide="true"></sc:column>
      <sc:column name="documentName" id="documentName" display="documentName" ></sc:column>
      <sc:column name="masterCopyInd" id="masterCopyInd" display="masterCopyInd" ></sc:column>
      <sc:column name="attachCopyInd" id="attachCopyInd" display="attachCopyInd" ></sc:column>
      <sc:column name="documentMemoDesc" id="documentMemoDesc" display="documentMemoDesc" ></sc:column>
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
        var reData = {'buttonType':'','documentCode':'','documentName':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
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
                reData.documentCode = selected.id.documentCode;
                reData.documentName = selected.documentName;
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
