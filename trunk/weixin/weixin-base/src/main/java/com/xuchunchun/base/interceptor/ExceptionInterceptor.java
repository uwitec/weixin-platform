/**
 * 文件名：ExceptionInterceptor.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：[描述]
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-3-23
 */
package com.xuchunchun.base.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author    tpf
 * @version   1.0  2012-3-23
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	private static Logger logger = Logger.getLogger(ExceptionInterceptor.class);
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String actionClassName = invocation.getAction().getClass().getName();
		String actionName = invocation.getInvocationContext().getName();
		String result = "";
		try {
			result = invocation.invoke();
			return result;
		} catch(Exception e) {
			
			logger.error("发生异常的action类为："+actionClassName+"-->发生异常的action为："+actionName);
			logger.error("异常信息："+e.getMessage());
			e.printStackTrace();
			ActionContext ac = invocation.getInvocationContext();
			Map request = (Map)ac.get("request");
			if(e instanceof BusinessException) {
				BusinessException be = (BusinessException)e;
				request.put("code",be.getErrorCode());
				request.put("errorMsg", be.getErrorMessage());
				request.put("reason", be.getErrorReason());
				request.put("solution", be.getErrorSolution());
			} else {
				request.put("message", "操作失败，出现异常！");
			}
			return "error";
		}
//		return result;
	}

}
