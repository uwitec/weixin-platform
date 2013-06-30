<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<div class="search-bar-top" style="margin-top:5px;">
	<b><sc:message key="query"/></b>
  </div>
   <div id="search" style = "margin-bottom:5px;">
   <table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
     <tr>
    <td class="form_label"> <sc:message key="bankorg_id"/></td><td><input type="text" name="acdCardface.id.bankorgId" id="bankorgId" value=""/></td>
    <td class="form_label"> <sc:message key="cardfaceId"/></td><td><input type="text" name="acdCardface.id.cardfaceId" id="cardfaceId" value=""/></td>
   	</tr>
  <tr>
     <td colspan="4">
     <input type="hidden" name="clause._orderby__id_cardfaceId" id="cardfaceId_order" value="A"/>
     <input type="hidden" name="clause._id_cardfaceId" id="cardfaceId_q" value="like"/>
     <input id="QUERY" class="l-button l-button-submit" type="button" style = "float:right;margin:4px;" value="<sc:message key="search1"/>" onclick="p_getlist();" />
</td></tr></table>
   </div>
   <sc:grid id="grid" url="${path}acdCardface/querylist.action" height="90%" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" name="query" icon="search" ></sc:toolBar>

      <sc:column name="bankorgId" id="bankorgId" display="bankorg_id" render="function(item){return item.id.bankorgId;}"></sc:column>
      <sc:column name="cardfacdId" id="cardfaceId" display="cardfaceId" render="function(item){return item.id.cardfaceId;}" hide="true"></sc:column>
      <sc:column name="cardfaceName" id="cardfaceName" display="cardfaceName" ></sc:column>
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
        var reData = {'buttonType':'','cardfaceId':'','cardfaceName':''};//点击的按钮
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
                reData.cardfaceId = selected.id.cardfaceId;
                reData.cardfaceName = selected.cardfaceName;
                window.returnValue=reData;
                window.close();
            });
        });
    </script>
