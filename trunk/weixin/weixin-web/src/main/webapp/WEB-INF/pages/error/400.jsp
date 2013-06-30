<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/public/common.jsp"%>
<html>
<head>


<script language="JavaScript"> 
<!--
//  倒计时跳转函数  
 
function delayURL(url) {
	var delay = document.getElementById("time").innerHTML;
//alert(delay);
	if(delay > 0) {
		delay--;
		document.getElementById("time").innerHTML = delay;
	} else {
			window.location="${path}/login.action";
    }
    setTimeout("delayURL('" + url + "')", 1000); //每隔1000毫秒执行一次delayURL(url)函数
}
 
//-->
 
</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="fail"/></title>
</head>
<body>
<table border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td><img alt="<fmt:message key="fail"/>" src="${path }/images/fail.png"></td>
	</tr>
	<tr>
	<td><span style=" text-align:center; display:block;font-size: 30px;font-weight: bold;color: red;"><sc:message key="notexistorcannotgetit"/></span></td>
	</tr>
	<tr><td>${message }</td></tr>
	<tr>
		<td>
			
		</td>
	</tr>
</table>
<%--<div align="center">
<font size="7" color="red"><b><span id="time" style="background:yellow">10</span></b></font>
<font size="4" color="blue"><fmt:message key="herfpage"/></font><br>
</div>
<div align="center"><br/>
<a href="${path}/login.action"><fmt:message key="enterloginpage"/></a><br/><br/><br/>
</div>--%>
<script type="text/javascript"> 
	//delayURL("/login.action");
	
</script>

</body>
</html>