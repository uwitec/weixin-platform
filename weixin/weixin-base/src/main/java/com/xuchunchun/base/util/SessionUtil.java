package com.xuchunchun.base.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class SessionUtil {
	//private static final ThreadLocal<HttpSession> currentSession = new ThreadLocal<HttpSession>();
	
	public static final String USER = "user";
	public static final String EMPLOYEE = "employee";
	public static final String SYSTEM_PARA = "systemparameter";
	public static final String SYSTEM_SESSION = "sysSession";
	public static final String USER_STR = "userStr";
	public static final String BANKORG_STR = "bankorgStr";
	public static final String EMPLOYEE_STR = "employeeStr";

	
//	public static void setSession(HttpSession httpSession){
//		currentSession.set(httpSession);
//	}
	
	public static HttpSession getSession(){
		HttpServletRequest request= ServletActionContext.getRequest();
		return request.getSession();
	}
	
	public static String getCurrUser(){
		Object userObj = getSession().getAttribute(USER_STR);
		if(userObj == null)return null;
		return userObj.toString();
	}
	
	public static long getBankorgId(){
		Object bankorgIdObj = getSession().getAttribute(BANKORG_STR);
		if(bankorgIdObj == null)return -1;
		return Long.parseLong(bankorgIdObj.toString());
	}
	
	public static String getCurrEmployee(){
		Object employeeObj = getSession().getAttribute(EMPLOYEE_STR);
		if(employeeObj == null)return null;
		return employeeObj.toString();
	}
}
