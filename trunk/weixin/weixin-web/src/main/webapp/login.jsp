<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${path }css/login.css" type="text/css" media="all" />  

<title>&#29992;&#25143;&#30331;&#38470;</title>
</head>

<style type="text/css"> 

ul, li{
	margin:0;
	padding:0;
}
input{
	border:1px solid #218abd;
}
.inputover{
	border:1px solid #218abd;
	background-color:#bdeaff;
}
.inputout{
	border:1px solid #218abd;
}
</style>

<body>
<div class="main-content">
<div id="login-logo"></div>
  <div class="centertable">
  <form action="${path }login/login.action" method="post" id="form" onsubmit="return validate();">
    <table width="100%" cellpadding="0" cellspacing="0" class="content">
      <tr>
        <td align="right" style="font-size: 12px;">用户名：</td>
        <td align="left"> 
        	<input type="text" name="aclUser.userId" id="txtUsername" style="width: 180px;" value="" onMouseOver="this.className='inputover'" onMouseOut="this.className='inputout'"/>        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td align="right" style="font-size: 12px;">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
        <td align="left">
        <input type="password" name="aclUser.password" id="txtPassword" style="width: 180px;" onMouseOver="this.className='inputover'" onMouseOut="this.className='inputout'"/>        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
	  <tr height="40">
          <td>&nbsp;</td>
          <td align="left">
              <input type="submit"  onmouseover="this.className='btn-over'" onMouseOut="this.className='btn'" class="btn" value="登录" />&nbsp;&nbsp;&nbsp;
              <input id="reset" type="button" onClick="myreset()" value="重置" onMouseOver="this.className='btn-over'" onMouseOut="this.className='btn'" class="btn" />
          </td>						
      </tr>
      <tr height="40">
      	<td>&nbsp;</td>
      	<td id="msg" style="color: red; font-size: 12px;">${msg }</td>
      </tr>
    </table>
    </form>
  </div>
   <div id="login-footer">Copyright © 2013 sunline </div>
</div>
</body>
<script type="text/javascript">
   function myreset(){
        document.getElementById("userid").value = "";
        document.getElementById("password").value = "";
   }
   
   function validate() {
	   var userName = $("#txtUsername").val();
	   var password = $("#txtPassword").val();
	   if(userName == "") {
		   $("#msg").html("用户名不得为空！");
		   return false;
	   } else if(password == "") {
		   $("#msg").html("密码不得为空！");
		   return false;
	   }
	   return true;
   }
   
   //初始化页面
   function init() {
	   document.getElementById("txtUsername").focus();
   }
   
   init();
</script>
</html>
