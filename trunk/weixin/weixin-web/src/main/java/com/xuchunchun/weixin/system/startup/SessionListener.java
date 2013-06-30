/**
 * 文件名：SessionListener.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：[描述]
 * 修改人：tpf
 * 修改内容：新增
 * 修改时间：2012-8-19
 */
package com.xuchunchun.weixin.system.startup;

import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.xuchunchun.abframe.common.AclConstants;
import com.xuchunchun.weixin.common.SystemConstants;


/**
 * sission动态监听器
 * @author    tpf
 * @version   1.0  2012-8-19
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */
public class SessionListener implements HttpSessionListener {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext servletContext = session.getServletContext();
		Hashtable hashtable = (Hashtable)servletContext.getAttribute(AclConstants.LOGIN_USER_LIST);
		HashMap sessionMap = (HashMap) servletContext.getAttribute(AclConstants.LOGIN_SESSION_LIST);
		String sessionId = session.getId()+session.getCreationTime();
		String userName = (String) sessionMap.get(sessionId);
		if(hashtable != null && userName != null) { 
			hashtable.remove(userName);
		}
		if(sessionMap != null && sessionId != null) {
			sessionMap.remove(sessionId);
		}
	}

}
