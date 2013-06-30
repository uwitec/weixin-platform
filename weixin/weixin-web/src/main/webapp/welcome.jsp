<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%--<%@ include file="/common.jsp"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head > 
    <script src="${path }ui/lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script> 
    <script src="${path }ui/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

	<script src="${path }ui/lib/ligerUI/js/common.js" type="text/javascript"></script>
	<script src="${path }ui/abframe.js" type="text/javascript"></script>
    <title></title>
    <style type="text/css">
    	img
		{
		    border: 0px;
		}
		
		#win {
			margin-left:10px;
		}
		
		#app
		{
		    float: left;
		    text-align: center;
		    margin: -15px 0 0 -30px;
		    list-style: none;
		}
		
		#app a
		{
		    text-decoration: none;
		    border: solid 1px transparent;
		    display: block;
		    padding: 8px;
		    margin: 20px 0 0 0;
		    color: #000000;
		    font-size:12px;
		    text-shadow: 0.2em 0.1em 0.3em #000000;
		}
		
		#app a:hover
		{
		    border: solid 1px #bbd6ec;
		    -webkit-border-radius: 3px;
		    box-shadow: inset 0 0 1px #fff;
		    -webkit-box-shadow: inset 0 0 1px #fff;
		    background-color: #5caae8;
		}
		
		#app li
		{
			margin: 10px;
		}
    </style>
    
    <%--<script type="text/javascript">
  
		function acdNewApplication() {
			abf.tabClose('acdNewApplication');
			top.f_addTab('acdNewApplication','新申请录入','${path }acdNewApplication/toquery.action');
		}
		function acdAppRegister() {
			abf.tabClose('acdAppRegister');
			top.f_addTab('acdAppRegister','申请表登记','${path}acdAppRegister/toquery.action');
		}
		function acdAppform() {
			abf.tabClose('acdAppform');
	        top.f_addTab('acdAppform','申请表流转','${path}acdAppform/toquery1.action');
		}
		function appQuery() {
			abf.tabClose('appQuery');
	        top.f_addTab('appQuery','进件申请查询','${path}acdNewApplication/queryStatus.action');
		}
		
		
		function cardNoGen() {
			abf.tabClose('cardNoGenRull');
	        top.f_addTab('cardNoGenRull','卡号生成规则','${path}acdCardRoGenRull/toquery.action');
		}
		function cardNoGrp() {
			abf.tabClose('cardNoGrp');
	        top.f_addTab('cardNoGrp','卡号组成','${path}acdCardnopart/toquery.action');
		}
		function cardNoLock() {
			abf.tabClose('cardNoLock');
	        top.f_addTab('cardNoLock','卡号锁定与分配','${path}acdCardnumberassign/toquery.action');
		}
		function cardNoManage() {
			abf.tabClose('cardNoManage');
	        top.f_addTab('cardNoManage','卡号段管理','${path}acdCardnorange/toquery.action');
		}
		
		
		function myWorkflow() {
			abf.tabClose('myWorkflow');
	        top.f_addTab('myWorkflow','我的工作队列','${path}workflow/myWorkQueue.action');
		}
		function workflowQuery() {
			abf.tabClose('workflowQuery');
	        top.f_addTab('workflowQuery','进件进度查询','${path}workflow/workQueueQuery.action');
		}
		function workflowDeploy() {
			abf.tabClose('workflowDeploy');
	        top.f_addTab('workflowDeploy','进件流程部署','${path}workflow/toquery.action');
		}
  </script>
--%>
</head>
<body style="background: url('${path }images/welcome.png');background-repeat:no-repeat;">
<%--    <img src="${path }images/welcome.png" alt="风险监控" />--%>
	<div id="win">
        <%--<ul id="app">
            <li><a href="#" onclick="acdNewApplication()">
                <img src="${path }images/welcome/appInput.png"/><br />
              	  新申请录入</a></li>
            <li><a href="#" onclick="appQuery()">
                <img src="${path }images/welcome/appQuery.png"/><br />
				进件申请查询</a></li>
			 <li><a href="#" onclick="acdAppform()">
                <img src="${path }images/welcome/appFlow.png"/><br />
              	 申请表流转</a></li>
            <li><a href="#" onclick="acdAppRegister()">
                <img src="${path }images/welcome/appRegister.png"/><br />
				申请表登记</a></li>
        </ul>
        
        <ul id="app">
            <li><a href="#" onclick="cardNoGen()">
                <img src="${path }images/welcome/app10.png"/><br />
				卡号生成规则</a></li>
            <li><a href="#" onclick="cardNoGrp()">
                <img src="${path }images/welcome/app11.png"/><br />
				卡号组成</a></li>
			 <li><a href="#" onclick="cardNoLock()">
                <img src="${path }images/welcome/cardLock.png"/><br />
              	 卡号锁定与分配</a></li>
            <li><a href="#" onclick="cardNoManage()">
                <img src="${path }images/welcome/app12.png"/><br />
				卡号段管理</a></li>
        </ul>
        
        <ul id="app">
            <li><a href="#" onclick="myWorkflow()">
                <img src="${path }images/welcome/myQueue.png"/><br />
				我的工作队列</a></li>
            <li><a href="#" onclick="workflowDeploy()">
                <img src="${path }images/welcome/app07.png"/><br />
              	  进件流程部署</a></li>
            <li><a href="#" onclick="workflowQuery()">
                <img src="${path }images/welcome/app08.png"/><br />
				进件进度查询</a></li>
        </ul> --%>
        <%int i = 0;%>
        <c:forEach items="${userShortcutList }" var="function">
        	<% if(i%4==0) { %>
        		<ul id="app">
        	<%}
        	i++;%>
        			<li><a href="#" onclick="abf.tabClose('${function.id.functionId}');top.f_addTab('${function.id.functionId}','${function.functionName}','${path}${function.funcUrl }');">
		                <img src="${path }images/welcome/app<%=i %>.png"/><br />
						${function.functionName}</a></li>
			<%if(i%4==0) { %>
        		</ul>
        	<%} %>
        	
        </c:forEach>
   </div>
</body>
</html>    