<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%--<meta http-equiv="Refresh" content="0;url=<%=request.getContextPath()%>/tologin.action" />  --%>

<Script language="javascript">
   //top.location.href('<%=request.getContextPath()%>/tologin.action');
   top.location.href = "<%=request.getContextPath()%>/tologin.action";//兼容IE,Firefox,Chrome
</Script>

<title>微信金融平台管理系统</title>

</head>
<body>

</body>
</html>
