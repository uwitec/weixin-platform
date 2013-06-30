<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<fmt:setBundle basename="resource.message"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="fail"/></title>
</head>
<body>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td><img alt='<sc:message key="fail"/>' src="${path }/images/fail.png"></td>
	</tr>
	<tr>
	<td><span style=" text-align:center; display:block;font-size: 20px;font-weight: bold;color: red;"><!-- <sc:message key="errorconnectmanager"/> --></span></td>
	</tr>
	<tr><td style="font-weight: bold; color: red;" align="center">${message }</td></tr>
	<tr>
		<td style="color: red;">
			${code } ${errorMsg }<br/>
			${reason }<br/>
			${solution }
		</td>
	</tr>
</table>
</body>
</html>