<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
<script type="text/javascript"> 
    $(function(){
        
       abf.showSuccess("操作成功",close);
   
    });
    function close(){
       var tabTmp = window.parent.topTab;
       if(tabTmp == null) {
       		return;
       }
       var currentTabId = tabTmp.getSelectedTabItemID();
       tabTmp.removeTabItem(currentTabId);
    }
</script>
</head>
<body> 
</body>
</html>