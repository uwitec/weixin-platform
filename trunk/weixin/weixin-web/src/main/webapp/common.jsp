<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.sunline.cn/tags/html" prefix="sc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<fmt:setBundle basename="resource.message"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>
<link href="${path }ui/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<link href="${path }ui/lib/ligerUI/skins/Gray/css/form.css" rel="stylesheet" type="text/css" />
<link href="${path }ui/lib/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />

<link href="${path }css/abframe.css" rel="stylesheet" type="text/css" />

<script src="${path }ui/lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>  
<script src="${path }ui/lib/ligerUI/js/ligerui.all.js" type="text/javascript"></script>

<link href="${path }ui/lib/ligerUI/css/common.css" rel="stylesheet" type="text/css" />
<script src="${path }ui/lib/ligerUI/js/common.js" type="text/javascript"></script>
<script src="${path }ui/abframe.js" type="text/javascript"></script>

<script src="${path }ui/lib/jquery-validation/jquery.validate.min.js" type="text/javascript"></script> 
<script src="${path }ui/lib/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${path }ui/lib/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${path }ui/lib/jquery.form.js" type="text/javascript"></script>
<script src="${path }ui/lib/json2.js"></script>
<script src="${path}ui/lib/ligerUI/js/validator.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/ligerui.expand.js" type="text/javascript"></script> 

<script src="${path }ui/indexdata.js" type="text/javascript"></script>
<script src="${path }ui/lib/json.js" type="text/javascript"></script>