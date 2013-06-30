package com.xuchunchun.abframe.web.action;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.db.entity.KenEmployeeId;
import com.xuchunchun.abframe.service.AclMenuTreeService;
import com.xuchunchun.abframe.service.AclUserService;
import com.xuchunchun.abframe.service.KenEmployeeService;
import com.xuchunchun.abframe.service.LoginService;
import com.xuchunchun.base.util.SystemUtil;
import com.xuchunchun.base.util.SessionUtil;
import com.xuchunchun.abframe.bean.AclTopMenuTreeForm;
import com.xuchunchun.abframe.common.AclConstants;
import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.service.AclSysParameterService;
import com.xuchunchun.abframe.web.vo.SystemSessionInfo;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;
import cn.sunline.suncard.tag.util.Utility;

@Action("loginAction")
public class LoginAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AclUserService aclUserService;
	@Autowired
	private KenEmployeeService kenEmployeeService;
	@Autowired
	private AclMenuTreeService aclMenuTreeService;
	private List<AclTopMenuTreeForm> lisTopMenuTreeForms; //用户菜单
	private List<AclFunction> userShortcutList;			//快捷方式
	@Autowired
	private AclSysParameterService aclSystemParameterService;
	
	private AclUser aclUser;
	private String msg;
	
	private final String USERNOTFOUND = "userNotFound"; //用户不存在
	private final String PASSWORD = "password"; //密码错误
	private final String MACCHECK = "maccheck"; //MAC地址校验不通过
	private final String IPCHECK = "ipcheck"; //IP地址校验不通过
	private final String BLIST = "blist" ;//黑名单用户
	private final String CUSER = "cuser" ;//注销用户
	private final String LUSER = "luser" ;//锁定的用户
	private final String UNSTATUS = "unstatus"; //未知状态
	
	private final String UUSER = "uuser" ;//新用户，第一次登录
	private final String NUSER = "nuser" ;//正常用户
	private final String LOGINFAIL = "loginfail";//登录失败
	
	private final String LOGOUT = "logout";//退出
	
	private String changePasswordInd;//需要修改密码指示
	
	private String changePasswordString;//前台传来的密码字符串	
	
	private List<String> logoutStrings;//退出系统的list（包括子系统）
	private List<String> loginStrings;//登录系统的list(子系统)
	
	private String CURRENT_SYSTEM_ID = "abframe";//本系统的系统ID，对应数据库acl_childsystem system_id字段
	private String SESSION_AUTH = "auth"; //功能例外的session
	
	//登录
	public String login(){
		
		String longinResult = loginService.login(aclUser);
		
		if(longinResult == USERNOTFOUND){
//			msg = "用户不存在！";
			msg = Utility.getI18n("userNotExist");
			return LOGINFAIL;
		}else if(longinResult.contains(PASSWORD)){
			String pwdErrorCount = longinResult.substring(longinResult.indexOf("-")+1);
//			msg = "密码错误"+pwdErrorCount+"次！";
			msg = Utility.getI18n("pwdErrorCount", pwdErrorCount);
			return LOGINFAIL;
		}else if(longinResult == LUSER){
//			msg = "用户已被锁定！";
			msg = Utility.getI18n("userLocked");
			return LOGINFAIL;
		}else if(longinResult == CUSER){
//			msg = "已注销的用户！";
			msg = Utility.getI18n("userLogout");
			return LOGINFAIL;
		}else if(longinResult == UNSTATUS){
//			msg = "未知的用户状态！";
			msg = Utility.getI18n("unknownUserStatus");
			return LOGINFAIL;
		}else if(longinResult == BLIST){
//			msg = "黑名单用户！";
			msg = Utility.getI18n("backListName");
			return LOGINFAIL;
		}else if(longinResult == IPCHECK){
//			msg = "IP地址校验错误！";
			msg = Utility.getI18n("IPAddrError");
			return LOGINFAIL;
		}else if(longinResult == MACCHECK){
//			msg = "MAC地址校验错误！";
			msg = Utility.getI18n("MACError");
			return LOGINFAIL;
		}
		
		
		//第一次登录，特殊处理
		if(UUSER.equalsIgnoreCase(longinResult)){
			//changePasswordInd
			if(loginService.firstLogin(aclUser)){
				changePasswordInd = "Y";
				
			}else{
				changePasswordInd = "N";
			}
		}
		
		HttpServletRequest request = ServletActionContext.getRequest();
		AclUser aclUserDetail = aclUserService.detailAclUser(aclUser);
		
		if(!checkSession(aclUserDetail)) {
//			msg = "该用户已登录！";
			msg = Utility.getI18n("userLogined");
			return LOGINFAIL;
		}
		
		KenEmployee kenEmployee = new KenEmployee(new KenEmployeeId(SystemUtil.getCurrBankorgId(),aclUserDetail.getEmployeeId()));
		kenEmployee = kenEmployeeService.detail(kenEmployee);
		AclSysparameter systemparameter = aclSystemParameterService.getSystemParam(SystemUtil.getCurrBankorgId());
		
		SystemSessionInfo sysSession = new SystemSessionInfo();
		sysSession.setUser(aclUserDetail);
		sysSession.setEmployee(kenEmployee);
		sysSession.setSystemparameter(systemparameter);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", aclUserDetail);
		session.setAttribute("employee", kenEmployee);
		session.setAttribute("systemparameter", systemparameter);
		session.setAttribute("sysSession", sysSession);
		session.setAttribute("userStr", aclUserDetail.getUserId());
		session.setAttribute("bankorgStr", aclUserDetail.getBankorgId());
		session.setAttribute("employeeStr", aclUserDetail.getEmployeeId());

		//SessionUtil.setSession(session);
		AclConstants.CURRENT_SYSTEM_NAME = request.getSession().getServletContext().getContextPath();
		lisTopMenuTreeForms = aclMenuTreeService.topMenuTreeForms(aclUser.getUserId());
		session.setAttribute("changePasswordInd", changePasswordInd);
		session.setAttribute(SESSION_AUTH, loginService.getExclude(aclUserDetail.getUserId(),CURRENT_SYSTEM_ID));
		
		//登录子系统的字符串列表
//		loginStrings = loginService.loginStrings(aclUserDetail.getUserId(),aclUserDetail.getEmployeeId());
		//退出子系统的字符串列表
//		logoutStrings = loginService.logoutStrings();
		
		// NUSER 和 UUSER
		return longinResult;
	}
	
	/**
	 * 查询欢迎页面的快捷方式
	 * 修改日期：2012-9-7
	 * @author: tpf
	 * @return
	 */
	public String welcomeMenu() {
		SystemSessionInfo sysSessionInfo = (SystemSessionInfo) ServletActionContext.getRequest().getSession().getAttribute(AclConstants.SESSION_SYSINFO);
		userShortcutList = aclMenuTreeService.getFunctionByUser(sysSessionInfo.getUser().getUserId());
		return SUCCESS;
	}
	
	/**
	 * 检查用户是否登录
	 * 修改日期：2012-8-19
	 * @author: tpf
	 * @param request
	 * @param username
	 * @return
	 */
	public boolean checkSession(AclUser aclUserDetail) {
		boolean isSession = true;
		HttpSession session = ServletActionContext.getRequest().getSession();
		String sessionId = session.getId() + session.getCreationTime();
		Hashtable hashtable = (Hashtable) session.getServletContext().getAttribute(AclConstants.LOGIN_USER_LIST);
		HashMap hashMap = (HashMap) session.getServletContext().getAttribute(AclConstants.LOGIN_SESSION_LIST);
		synchronized (this) {
			Object obj = hashtable.get(aclUserDetail.getUserId());
			if (obj != null) {
				// 这里使用Hashtable因为 Hashtable本身是synchronized 所以为了方便就使用Hashtable
				// 如果自己编写一个类实现了synchronized 并且只是放入String[只是验证登录名称] 效果会更好
				// 如果不是null就已经可以判断出来登录了，如果想进一步判断登录信息，这里做处理
				AclUser aclUser = (AclUser) obj;
				//是否让其自动登录......
				isSession = false;
			} else {
				hashtable.put(aclUserDetail.getUserId(), aclUserDetail);
				hashMap.put(sessionId, aclUserDetail.getUserId());
			}
		}
		if(SystemUtil.getLoginsInd().equals("Y") || "sysadmin".equals(aclUserDetail.getUserId())) {
			isSession = true;
		}
		return isSession;

	}
	
	//退出
	public String logout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("user") != null || session.getAttribute("employee") != null || 
				session.getAttribute("systemparameter") != null || session.getAttribute("sysSession") != null) {
			session.removeAttribute("user");
			session.removeAttribute("employee");
			session.removeAttribute("systemparameter");
			session.removeAttribute("sysSession");
			session.invalidate();
		}
		return SUCCESS;
	}
	
	//第一次登录密码修改提交
	public void firstPasswordChange(){
		loginService.firstChangePassword(changePasswordString,aclUser);
		Object ok = new String("{ok:'OK'}");
		returnJSON(ok);
	}
	
//	//加载用户树
//	public void userListTreeForm(){
//		List<ListMenuTreeBase> list = aclMenuTreeService.userListTreeForm(aclUser.getUserId());
//		returnJSONLISTOBJ(list);
//	}
	public void setAclUser(AclUser aclUser) {
		this.aclUser = aclUser;
	}

	public AclUser getAclUser() {
		return aclUser;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setAclUserService(AclUserService aclUserService) {
		this.aclUserService = aclUserService;
	}

	public AclUserService getAclUserService() {
		return aclUserService;
	}
	
	public KenEmployeeService getKenEmployeeService() {
		return kenEmployeeService;
	}

	public void setKenEmployeeService(KenEmployeeService kenEmployeeService) {
		this.kenEmployeeService = kenEmployeeService;
	}

	public void setAclMenuTreeService(AclMenuTreeService aclMenuTreeService) {
		this.aclMenuTreeService = aclMenuTreeService;
	}

	public AclMenuTreeService getAclMenuTreeService() {
		return aclMenuTreeService;
	}
	public void setLisTopMenuTreeForms(List<AclTopMenuTreeForm> lisTopMenuTreeForms) {
		this.lisTopMenuTreeForms = lisTopMenuTreeForms;
	}
	public List<AclTopMenuTreeForm> getLisTopMenuTreeForms() {
		return lisTopMenuTreeForms;
	}
	public void setChangePasswordInd(String changePasswordInd) {
		this.changePasswordInd = changePasswordInd;
	}
	public String getChangePasswordInd() {
		return changePasswordInd;
	}
	public String getChangePasswordString() {
		return changePasswordString;
	}
	public void setChangePasswordString(String changePasswordString) {
		this.changePasswordString = changePasswordString;
	}
	public void setLogoutStrings(List<String> logoutStrings) {
		this.logoutStrings = logoutStrings;
	}
	public List<String> getLogoutStrings() {
		return logoutStrings;
	}
	public void setLoginStrings(List<String> loginStrings) {
		this.loginStrings = loginStrings;
	}
	public List<String> getLoginStrings() {
		return loginStrings;
	}
	

	public List<AclFunction> getUserShortcutList() {
		return userShortcutList;
	}

	public void setUserShortcutList(List<AclFunction> userShortcutList) {
		this.userShortcutList = userShortcutList;
	}
}
