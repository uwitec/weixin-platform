<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
</head>
<body style="overflow-x:hidden;">
   <div id="search" class="search-bar-top">
     <sc:message key="bankorg_id"/><input type="text" name="acdStatecity.id.bankorgId" id="bankorgId" value=""/>
     <sc:message key="stateId1"/><input type="text" name="acdStatecity.id.stateId" id="stateId" value=""/>
     <sc:message key="cityId1"/><input type="text" name="acdStatecity.id.cityId" id="cityId" value=""/>
     <input type="hidden" name="clause._orderby__id_stateId" id="stateId_order" value="A"/>
     <input type="hidden" name="clause._id_stateId" id="stateId_q" value="like"/>
   </div>
   <sc:grid id="grid" url="${path}acdStatecity/querylist.action" height="90%" width="100%" root='rows' record='total'>
      <sc:toolBar type="QUERY" text="query" name="query" icon="search" ></sc:toolBar>
      <sc:toolBar type="ADD" text="add" id="acdStatecityadd" name="add" icon="add"  url="${path}acdStatecity/toadd.action"></sc:toolBar>
      <sc:toolBar type="UPDATE" id="acdStatecityupdate" url="${path}acdStatecity/detail.action" name="modify" text="update" icon="modify" entityName="'acdStatecity'" struct="['id.stateId','id.bankorgId','id.countryAlphaId','id.cityId']"></sc:toolBar>
      <sc:toolBar type="DELETE" text="delete" name="delete" icon="delete" url="${path}acdStatecity/delete.action" entityName="'acdStatecitys'" struct="['id.stateId','id.bankorgId','id.countryAlphaId','id.cityId']"></sc:toolBar>

      <sc:column name="bankorgId" id="bankorgId" display="bankorg_id" render="function(item){return item.id.bankorgId;}"></sc:column>
      <sc:column name="stateId" id="stateId" display="stateId1" render="function(item){return item.id.stateId;}"></sc:column>
      <sc:column name="cityId" id="cityId" display="cityId1" render="function(item){return item.id.cityId;}"></sc:column>
      <sc:column name="countryAlphaId" id="countryAlphaId1" display="stateId" render="function(item){return item.id.countryAlphaId;}"></sc:column>
      <sc:column name="stateName" id="stateName" display="stateName" ></sc:column>
      <sc:column name="cityName" id="cityName" display="cityName" ></sc:column>
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
        var reData = {'buttonType':'','stateName':'','cityName':'',endData:''};//点击的按钮，卡产品ID，流水号，结束号
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
                reData.stateName = selected.stateName;
                reData.cityName = selected.cityName;
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
       

    
