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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.base.exception.BusinessException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author    xuchunchun
 * @version   1.0  2012-3-23
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class PlatformProcessInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 4621981428429286103L;
	private static Logger logger = Logger.getLogger(PlatformProcessInterceptor.class);
	private static String METHOD_TYPE_STR = "methodType";
	private static String POST_CONTENT_STR = "postContent";
	
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.info("start to switch the message.....");
		ActionContext actionContext = invocation.getInvocationContext();   
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		
		
		actionContext.getParameters().put(METHOD_TYPE_STR, request.getMethod());
		
		if(BaseConstants.GET_STR.equals(request.getMethod())){
			logger.info("this is a get mothod,this is [Application message]!");
			
		}else{
			logger.info("this is a post mothod,process the push message!");
			String content = request.getReader().readLine();
			
			logger.info("the data of post content is:"+content);
			
			request.setAttribute(POST_CONTENT_STR, content);
		}
		
		return invocation.invoke();
	}
	

}
