<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>微信金融平台管理系统</title>
    <script src="${path }ui/lib/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
    <script src="${path }ui/pwdstrength.js" type="text/javascript"></script>
    <link href="${path }css/pwdstrength.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            var tab = null;
            var accordion = null;
            var tree = null;
            var manager = null;
            $(function ()
            {
                //布局
                $("#layout1").ligerLayout({ leftWidth: 210, height: '100%',heightDiff:-24,space:4, onHeightChanged: f_heightChanged });

                var height = $(".l-layout-center").height();

                //Tab
                $("#framecenter").ligerTab({ height: height });

                //面板
                $("#accordion1").ligerAccordion({ height: height - 24, speed: null });

                $(".l-link").hover(function ()
                {
                    $(this).addClass("l-link-over");
                }, function ()
                {
                    $(this).removeClass("l-link-over");
                });
                
                tab = $("#framecenter").ligerGetTabManager();
                window['topTab'] = tab;
                accordion = $("#accordion1").ligerGetAccordionManager();
                
                $("#pageloading").hide();

                //加载树
                $("#accordion1 div ul").each(function(i,ul){
                    //ul=this $ul=$this
                    var $ul = $(ul);
                    var loadTreeString = $ul.attr('childrenString');
                    var loadTreeData = eval('('+ loadTreeString +')'); //将json字符串解析成json对象
                   var children = loadTreeData[0].children;
                    $ul.ligerTree({
	                    data: children,
	                    checkbox: false,
	                    slide: false,
	                    nodeWidth: 120,
	                    attribute: ['nodename','url'],
	                    onSelect: function (node)
	                    {
	                        if (!node.data.url) return;
	                        var tabid = $(node.target).attr("tabid");
	                        if (!tabid)
	                        {
	                            tabid = node.data.nodeId;
	                            $(node.target).attr("tabid", tabid);
	                        } 
	                        f_addTab(tabid, node.data.text, node.data.url);
	                    }
                    });
                    manager = $ul.ligerGetTreeManager();
                });
                //为子系统的登录
                loginAjax();
                //退出系统
                $("#logout").bind("click",logoutAjax);
                
                //首次登录，修改密码
                firstPassword();
                
            	//折叠所有树节点
                collapseAll();
            });
            
            //折叠所有树节点
            function collapseAll()
            {
                manager.collapseAll();
            }
            
            //展开所有树
            function expandAll()
            {
                manager.expandAll();
            }
            
            function f_heightChanged(options)
            {
                if (tab)
                    tab.addHeight(options.diff);
                if (accordion && options.middleHeight - 24 > 0)
                    accordion.setHeight(options.middleHeight - 24);
            }
            function f_addTab(tabid,text, url)
            { 
                //判断session是否过期
                if(tab.isTabItemExist(tabid)){
                     tab.selectTabItem(tabid);
                     tab.reload(tab.getSelectedTabItemID());
                }else{
                    var maxTabCount = $("#maxTabCount").val(); //最大标签数
                    if(tab.getTabItemCount()>=maxTabCount){
                        alert("页面数已达到最大值");
                        return false;
                    }
                    tab.addTabItem({ tabid : tabid,text: text, url: url });
                }
            } 
            //退出子系统的ajax方法
            function logoutAjax(){                
                var arrout = [];
                var outs = $(".logoutclass");
                for(var i=0;i<outs.length;i++){
                    arrout[i] = new CreateHTTPObj();
                    arrout[i].open("GET",$(outs[i]).val(),true);
                    arrout[i].send(null);
                }
                var out = $("#logout").attr('href_b');
                //window.location.href(out);
                window.location.href = out;
                return false;
            };
            //登录子系统的ajax方法
            function loginAjax(){
                var arr = [];
                var locats = [];
                var urls = [];
                var queryString = [];
                var ins = $(".loginclass");
                for(var i=0;i<ins.length;i++){
                    arr[i] = new CreateHTTPObj();
                    locats[i] = $(ins[i]).val();
                    urls[i] = locats[i].substring(0,locats[i].indexOf("?"));
                    queryString[i] = locats[i].substring(locats[i].indexOf("?")+1,locats[i].length);
                    arr[i].open("POST",urls[i],true);
                    arr[i].setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
                    arr[i].send(queryString[i]);
                }
            }
            //创建XMLHttpRequest
            function CreateHTTPObj(){
                var xmlhttp=null;
                if (window.XMLHttpRequest)
                  {// code for all new browsers
                  xmlhttp=new XMLHttpRequest();
                  }
                else if (window.ActiveXObject)
                  {// code for IE5 and IE6
                  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                  }
                return xmlhttp;
            }
            
            function logout() {
            	window.location.href="${path }login/logout.action";
            }
     </script> 
<style type="text/css"> 
    body,html{height:100%;}
    body{ padding:0px; margin:0;   overflow:hidden;}  
    .l-link{ display:block; height:26px; line-height:26px; padding-left:10px; text-decoration:underline; color:#333;}
    .l-link2{text-decoration:underline; color:#ADADAD;font-weight:bold; margin-left:2px;margin-right:2px;}
    .l-layout-top{background:#102A49; color:#ADADAD;font-weight:bold;}
    .l-layout-bottom{ background:#E5EDEF; text-align:center;}
    #pageloading{position:absolute; left:0px; top:0px; background:white url('loading.gif') no-repeat center; width:100%; height:100%;z-index:99999;}
    .l-link{ display:block; line-height:22px; height:22px; padding-left:16px;border:1px solid white; margin:4px;}
    .l-link-over{ background:#FFEEAC; border:1px solid #DB9F00;} 
    .l-winbar{ background:#2B5A76; height:30px; position:absolute; left:0px; bottom:0px; width:100%; z-index:99999;}
    .space{ color:#E7E7E7;}
    /* 顶部 */ 
    .l-topmenu{ margin:0; padding:0; height:77px; line-height:31px; position:relative;background-color:#ACD6FF;background:url('<%=request.getContextPath()%>/images/header_bg.png') no-repeat 0px 0px;}
    .l-topmenu-logo{ color:#E7E7E7; padding-left:35px; line-height:26px;height:75px;background:url('<%=request.getContextPath()%>/images/login/main-logo.png') no-repeat 10px 1px;}
    .l-topmenu-welcome{  position:absolute; height:24px; line-height:26px;  right:30px; top:10px;color:#070A0C;}
    .l-topmenu-welcome a{ color:#E7E7E7; text-decoration:underline} 

 </style>
</head>
<body style="padding:0px;background:#EAEEF5;" onunload="javascript:logout()">  
<div id="pageloading" ></div>  
<div id="topmenu" class="l-topmenu">
    <div class="l-topmenu-logo"></div>
    <div class="l-topmenu-welcome">
        <input type="hidden" id="aclUser_userId" value="${sessionScope.user.userId }" />
        <input type="hidden" id="changePasswordInd" value="${changePasswordInd }" />
        
        <span style="color: white;font-weight: bold;">欢迎 <a href="javascript:void(f_addTab('tochangepassword','密码修改','${path }aclUserAction/tochangepassword.action?aclUser.userId=${sessionScope.sysSession.user.userId }'))" class="l-link2">${sessionScope.sysSession.user.userId}</a><span class="space"> | </span>${sessionScope.sysSession.employee.employeeName }  ${changePasswordInd }</span>
        <span class="space">|</span>
        <span style="color: white;font-weight: bold;">系统日期：<fmt:formatDate value="${sessionScope.sysSession.systemparameter.currBusinessDate }" pattern="yyyy年MM月dd日" /> </span>
        <br />
        
		<span style="color: white;font-weight: bold;">银行机构：${sessionScope.sysSession.employee.id.bankorgId }</span>
		<span class="space">|</span>
		<span style="color: white;font-weight: bold;">分行：${sessionScope.sysSession.employee.brBranchId }</span>
        <span class="space">|</span>
        <a href="javascript:void(f_addTab('toshortcut','快捷方式设置','${path }aclRole/toshortcut.action'))" class="l-link2"><span style="background: url(<%=request.getContextPath() %>/images/set.gif) no-repeat 0 -2;padding-left: 18px;">系统设置</span></a>
        <span class="space">|</span>
        <a href="javascript:void(0);" href_b="${path }login/logout.action" class="l-link2" id="logout"><span style="background: url(<%=request.getContextPath() %>/images/logout.gif) no-repeat 0 -2;padding-left: 18px;">退出</span></a>
     	
        
     <%--  [<a href="javascript:void(logoutFun());" href_b="${path }login/logout.action" class="l-link2" id="logout">退出</a>]--%>
        <% int t=0; %>
         <c:forEach items="${logoutStrings}" var="m">
            <input type="hidden" class="logoutclass" name="loginStrings<%= ++t%>" id="loginStrings<%= t%>" value="${m }" />
         </c:forEach>
        
	     <% int i=0; %>
	     <c:forEach items="${loginStrings}" var="m">
            <input type="hidden" name="loginStrings<%= ++i%>" id="loginStrings<%= i%>" class="loginclass" value="${m }" />
         </c:forEach>
         <input type="hidden" name="maxTabCount" id="maxTabCount" value="<s:property value='@com.xuchunchun.base.util.SystemUtil@getMaxTabCount()'/>"/>
         
    </div> 
</div>
  <div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; "> 
        <div position="left"  title="主要菜单" id="accordion1"> 
            <c:forEach items="${lisTopMenuTreeForms}" var="m">
                <div title="${m.title}" class="l-scroll" style="overflow-x:auto">
                     <ul id="${m.id }" style="margin-top:3px;" childrenString='${m.childrenString }' />
                 </div>
            </c:forEach>

                
                    <%--<div title="应用场景">
                    <div style=" height:7px;"></div>
                         <a class="l-link" href="javascript:f_addTab('sysparameter','基础参数','<%=request.getContextPath()%>/aclSysparameterAction/toquery.action')">基础参数</a>
                          <a class="l-link" href="javascript:f_addTab('aclchildsystem','子系统','<%=request.getContextPath()%>/aclChildsystemAction/toquery.action')">子系统</a>
                          <a class="l-link" href="javascript:f_addTab('aclloginstragy','登录策略','<%=request.getContextPath()%>/AclLoginstrategyAction/toquery.action')">登录策略</a>
                          <a class="l-link" href="javascript:f_addTab('kenemployee','员工表','<%=request.getContextPath()%>/kenEmployeeAction/toquery.action')">系统员工表</a>
                          <a class="l-link" href="javascript:f_addTab('aclloginmemo','登录日志','<%=request.getContextPath()%>/aclLoginmemoAction/toquery.action')">登录日志</a>
                          <a class="l-link" href="javascript:f_addTab('aclfuncexclude','功能例外','<%=request.getContextPath()%>/aclFuncexcludeAction/toquery.action')">功能例外</a>
                          <a class="l-link" href="javascript:f_addTab('kenbankbranch','分行机构配置','<%=request.getContextPath()%>/kenBankbranch/toquery.action')">分行机构配置</a>
                          <a class="l-link" href="javascript:f_addTab('kenempbrach','员工所辖分行','<%=request.getContextPath()%>/kenEmpbranch/toquery.action')">员工所辖分行</a>
                          <a class="l-link" href="javascript:f_addTab('kenbranchgroup','分行组','<%=request.getContextPath()%>/kenBranchgroup/toquery.action')">分行组</a>
                          <a class="l-link" href="javascript:f_addTab('kenbranchgroupmapping','分行组配置','<%=request.getContextPath()%>/kenBranchgroupmapping/toquery.action')">分行组配置</a>
                         <a class="l-link" href="javascript:f_addTab('menutree','菜单管理','<%=request.getContextPath()%>/aclMenuTree/toquery.action')">菜单管理</a>
                         <a class="l-link" href="javascript:f_addTab('function','功能管理','<%=request.getContextPath()%>/aclFunction/toquery.action')">功能管理</a>
                         <a class="l-link" href="javascript:f_addTab('user','用户管理','<%=request.getContextPath()%>/aclUserAction/toquery.action')">用户管理</a>
                         <a class="l-link" href="javascript:f_addTab('aclrole','角色管理','<%=request.getContextPath()%>/aclRole/toquery.action')">角色管理</a>
                    </div> --%>
                
       </div>
        <div position="center" id="framecenter"> 
            <div tabid="home" title="欢迎页面" style="width:100%" >
                <iframe frameborder="0" name="home" id="home" frameborder="0" scrolling="no" src="<%=request.getContextPath()%>/login/welcomeMenu.action"></iframe>
            </div> 
        </div> 
        <div style="display:none"></div>
    </div>
    <div  style="height:22px; line-height:22px; text-align:center;">
            Copyright ©xuchunchun 2013-2014
    </div>
    
    
    <div id="target1" style="width:200px;height:80px; margin:3px; display:none;">
      <table style="width:250px;">
       <tr>
           <td>新密码</td>
           <td>
	           <input type="password" id="password1" style="margin:0px" onKeyUp="EvalPwd(this.value)";onchange="EvalPwd(this.value);" />
	           <table width=280 border="0" cellspacing="0" cellpadding="0">
					<tr height=20>
						<td width=17% class="pwd_default" id="soweak" align=center>非常低</td>
						<td width=14% class="pwd_default" id="weak" align=center>低</td>
						<td width=13% class="pwd_default" id="middleweak" align=center>中低</td>
						<td width=11% class="pwd_default" id="middle" align=center>中</td>
						<td width=16% class="pwd_default" id="middlestrong" align=center>中高</td>
						<td width=12% class="pwd_default" id="strong" align=center>高</td>
						<td width=17% class="pwd_default" id="Perfect" align=center>完美</td>
					</tr>
				</table>
	       </td>
       </tr>
       <tr>
           <td>密码确认</td>
           <td><input type="password" id="password2" style="margin:0px"/></td>
       </tr>
      </table>
    </div>
    <script type="text/javascript">
        //首次登录，密码修改相关
         function firstPassword(){
            if("Y"==$('#changePasswordInd').val()){ //强制修改密码指示
                 $(function ()
                 {
                     $.ligerDialog.open({ target: $("#target1"),allowClose:false , title:'第一次登录系统，请修改密码',
                         buttons:[{text:'确定',onclick:subfirstChange}]
                     });
                 }); 
            }
         }
         function subfirstChange(){
            if($('#password1').val()!=$('#password2').val()||$('#password1').val()==''){
                alert("请输入一致的密码");
                $('#password1').val('');
                $('#password2').val();
                return false;
            }
            if(EvalPwd($("#password1").val())<=1) {
            	alert("密码强度太低！");
                return false;
            }
            var url = "<%=request.getContextPath()%>/login/firstPasswordChange.action";
            var data = {'aclUser.userId':$('#aclUser_userId').val(),'changePasswordString':$('#password1').val()}; 
            $.ajax({
                url:url,
                data:data,
                dataType:'json',
                type:'POST',
                success:function(data){
                    if(data.ok == 'OK'){
                        alert("操作成功,请重新登录");
                        window.location.href('<%=request.getAttribute("path")%>login/logout.action');
                    }else{
                        alert("请重新输入密码");
                    }
                },
                error:function(){
                    alert("发生系统错误，请联系管理员");
                }
            });
         }
        
    </script>
</body>
</html>
