/**
 * 文件名：LoginInterceptor.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：登录拦截器
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-2-29
 */
package com.xuchunchun.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 登录拦截器
 * @author    tpf
 * @version   1.0  2012-2-29
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class LoginInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(LoginInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.info("拦截器执行了......");
		ActionContext actionContext = invocation.getInvocationContext();   
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null && session.getAttribute("sysSession") != null) {
			logger.info("login..............."+invocation+"="+invocation.getAction().toString());
			return invocation.invoke();
		}
		return "login";
	}

}
