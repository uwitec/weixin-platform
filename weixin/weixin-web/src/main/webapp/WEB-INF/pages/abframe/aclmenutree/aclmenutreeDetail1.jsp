<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<style type="text/css">
       body{ font-size:12px;}
    .l-table-edit {}
    .l-table-edit-td{ padding:4px;}
    .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
    .l-verify-tip{ left:230px; top:120px;}
</style>
<div>

<sc:form name="form1" method="post" action="${path }aclMenuTree/${detailTag==null?'add.action':'update.action'}"  id="form1">
<input type="hidden" id="topMenuInd" value="${topMenu }" />
<table width="100%" cellpadding="0px" cellspacing="0px" class="form_table" border="none" >
    <tr class="form_top">
        <td colspan="99">详细信息</td>
    </tr>
     <tr>
         <td class="form_label"><fmt:message key="nodeId"/>:</td>
         <td align="left" class="l-table-edit-td">
             <input name="aclMenutree.id.bankorgId" value="${aclMenutree.id.bankorgId }" type="hidden" id="d_bankorgId"/>
             <fmt:text type="text" name="aclMenutree.id.nodeId" value="${aclMenutree.id.nodeId }" id="d_nodeId" validate="{required:true,maxlength:20}" />
         </td>
        <td class="form_label"><fmt:message key="nodeSeq"/>:</td>
         <td align="left" class="l-table-edit-td"><input name="aclMenutree.nodeSeq" value="${aclMenutree.nodeSeq }" type="text"  id="d_nodeSeq" validate="{required:true,number:true,maxlength:10}"/></td>
     </tr>
     <tr>
        <td class="form_label"><fmt:message key="nodeName"/>:</td>
         <td align="left" class="l-table-edit-td">
              <fmt:text id="d_nodeName" type="text" name="aclMenutree.nodeName" value="${aclMenutree.nodeName }"  ltype="text" validate="{required:true,maxlength:10}"/>      
         </td>
       <td class="form_label"><fmt:message key="parentId"/>:</td>
         <td align="left" class="l-table-edit-td">
             <fmt:text name="aclMenutree.parentId" value="${aclMenutree.parentId }" type="text" id="d_parenId"  ltype="text" validate="{required:true,maxlength:20}"/>
         </td>
     </tr>
     <tr>
         <td class="form_label"><fmt:message key="isLeaf"/>:</td>
         <td align="left" class="l-table-edit-td">
             <sc:dict dictId="YorN" name="aclMenutree.isLeaf" empty="false" value="${aclMenutree.isLeaf}" />
         </td>
        <td class="form_label"><fmt:message key="functionId"/>:</td>
         <td align="left" class="l-table-edit-td">
             <input value="${aclMenutree.functionId }" type="text" id="ds_functionId"  type="text" />
             <input name="aclMenutree.functionId" value="${aclMenutree.functionId }" type="hidden" id="d_functionId" />
         </td>
     </tr>
     <tr>
         <td class="form_label"><fmt:message key="roleType"/>:</td>
         <td align="left" class="l-table-edit-td">
             <sc:dict dictId="roleType" name="aclMenutree.menuType" empty="false" value="${aclMenutree.menuType}" defaultValue="M"/>
         </td>
     </tr>
     <tr style="height:40px">
             <td colspan="99" style="padding:15px 20px 15px 0;"><div style="text-align:right;float:right;"><input type="button" value="<fmt:message key="submit"/>" id="subButton" class="l-button l-button-submit" /> </div></td> 
      </tr>
</table>

</sc:form>    
</div>
<script type="text/javascript"> 

function topMenuBind(){
    
    var topMenuInd = $('#topMenuInd').val();
    if(topMenuInd==null||topMenuInd==''){
        $("#d_parenId").ligerComboBox({
         width: 180,
         selectBoxWidth: 200,
         selectBoxHeight: 200, textField: 'nodeId',treeLeafOnly:false,
         tree: { url: '<%=request.getContextPath()%>/aclMenuTree/loadparenttree.action', checkbox: false , cache:false }
        });
    }else{
        $('#d_parenId').val(0);
        $('#d_parenId').attr('readonly','readonly');
        $('#d_isLeaf').val('N');
        $('#d_isLeaf').attr('readonly','readonly');
    }
}
topMenuBind();


  $("#ds_functionId").ligerComboBox({
      onBeforeOpen: f_selectContact, valueFieldID: 'hidCustomerID',width:300
  });
  
  function f_selectContact()
  {
      $.ligerDialog.open({ title: '选择功能', name:'winselector',width: 600, height: 400, url: '<%=request.getContextPath()%>/abframeLookup/functionto.action', buttons: [
          { text: '确定', onclick: f_selectContactOK },
          { text: '清空', onclick: f_selectContactClean },
          { text: '取消', onclick: f_selectContactCancel }
      ]
      });
      return false;
  }
  function f_selectContactOK(item, dialog)
  {
      var fn = dialog.frame.f_select || dialog.frame.window.f_select; 
      var data = fn(); 
      if (!data)
      {
          alert('请选择行!');
          return;
      }
      $("#ds_functionId").val(data.functionName);
      $("#d_functionId").val(data.id.functionId);
      dialog.close();
  }
  function f_selectContactClean(item, dialog){
      $("#ds_functionId").val('');
      $("#d_functionId").val('');
      dialog.close();
  }
  function f_selectContactCancel(item, dialog)
  {
      dialog.close();
  }
  
  //对表单绑定
  var form = $('#form1');
  abf.validateBindForm(form);
  $('#subButton').click(function(){
       if(!form.valid()){return;}; //验证判断，注意form要在前面先定义
       $.ajax({
            type: 'POST',
	        url:form.attr("action"),
	        data:form.serializeArray(),
	        dataType:"json",
	        cache: false,
	        success: function(data){
	            if(data.ok=="OK"){
	                alert("操作成功");
	                reloadtree();
	                $("#submitBtn").attr("disabled", true);
	            }
	        },
	        error: function(){
	            alert("系统异常，请联系管理员");
	        }
       });
       return false;
   });
</script> 