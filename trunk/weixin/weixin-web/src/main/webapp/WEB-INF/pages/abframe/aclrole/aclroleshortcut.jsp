<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title> 
  
    <style type="text/css">
    .l-panel td.l-grid-row-cell-editing { padding-bottom: 2px;padding-top: 2px;}
    </style>
</head>
<body style="padding:2px;height:100%; text-align:center;">
  
  <div id="layout" style="width:100%">
    <div position="left" title="<fmt:message key="menulist"/>" id="mainmenu">
        <div style="height:400px;width:250px;overflow:auto;10px;border:1px solid #84a0c4;" class="l-scroll">
        <input type="hidden" name="aclRole.id.roldId" value="${aclRole.id.roldId }" id="menu_roldId"/>
        <ul id="treelist"></ul>
	     </div>
	     <div align="center">
            <input type="submit" value="<fmt:message key="save"/>" id="subButton" class="l-button l-button-submit" /> 
         </div>
     </div>
     <div position="center" title="<fmt:message key="detailInfo"/>" style="width:100%;"> 
        <form id="mainform" >
        <div id="maingrid"  style="margin:2px;" style="height:40%"></div> 
        </form>
        <div id="formtable" style="border:1px,solid;margin:10px">
        </div>
    </div>
  </div>
  
  <script type="text/javascript">
 
  var layout = $("#layout").ligerLayout({ leftWidth:'250'});
//树
  var tree = $("#treelist").ligerTree({
      url: '<%=request.getContextPath()%>/aclRole/loadUserShortcut.action',
      checkbox: true,
      slide: false,
      nodeWidth: 120,
      attribute: ['nodename', 'url'],
      onSelect: function (node)
      {
          if (!node.data.nodeId) return;
          var url = '<%=request.getContextPath()%>/aclRole/loadUserShortcut.action';
          grid.set('url',url);
      }
  });
  
  var manager = $("#treelist").ligerGetTreeManager(); 
  
  $('#subButton').bind('click',function(){
	  var notes = manager.getChecked();
	  var listAclRolemenus = [];
	  var text = "";
      for (var i = 0; i < notes.length; i++)
      {
    	  //拼接查询字符串
    	  if(i==0) {
    	 	text += "lsitAclUsershortcuts["+i+"].id.nodeId="+notes[i].data.nodeId+"&"+"lsitAclUsershortcuts["+i+"].id.userId=${sessionScope.sysSession.user.userId}";
    	  } else {
    		  text += "&lsitAclUsershortcuts["+i+"].id.nodeId="+notes[i].data.nodeId+"&"+"lsitAclUsershortcuts["+i+"].id.userId=${sessionScope.sysSession.user.userId}";
    	  }
      }
      var url = '<%=request.getContextPath()%>/aclRole/saveAclUsershortcut.action';
      var ajaxData = {};
      ajaxData.type = 'POST';
      ajaxData.url = url;
      ajaxData.dataType = 'json';
      ajaxData.data = text;// subString;//{'listAclRolemenus':listAclRolemenus,'aclRole':aclRole};
      ajaxData.success = function(){abf.showSuccess();};
      ajaxData.error = function(){abf.showError();};
      $.ajax(ajaxData);
  });
  
  </script>
</body>
</html> 
