<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.sunline.cn/tags/html" prefix="sc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<fmt:setBundle basename="resource.message"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", basePath);
%>
<link href="${path }ui/lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>   
<link href="${path }ui/lib/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
<![endif]-->   
<!--[if gte IE 7]>   
<link href="${path }ui/lib/ligerUI/skins/Silvery/css/form.css" rel="stylesheet" type="text/css" />
<![endif]--> 
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
<script src="${path }ui/acd.js" type="text/javascript"></script>
<link href="${path }css/acd.css" rel="stylesheet" type="text/css" />
<!-- 
<script src="${path }ui/lib/ligerUI/js/core/base.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${path }ui/lib/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
 -->
