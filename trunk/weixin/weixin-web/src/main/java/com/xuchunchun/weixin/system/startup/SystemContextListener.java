/**
 * 文件名：SuncardContextListener.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：服务器上下文监听类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 15, 2012
 */
package com.xuchunchun.weixin.system.startup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoaderListener;

import com.xuchunchun.abframe.common.AclConstants;
import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.trxn.client.ClientConfigFactory;
import com.xuchunchun.trxn.client.ClientSocketFactory;
import com.xuchunchun.trxn.msg.NamedSqlFactory;
import com.xuchunchun.trxn.msg.TrxnMsgConfigFactory;
import com.xuchunchun.trxn.msg.TrxnMsgFactory;
import com.xuchunchun.weixin.common.SystemConstants;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.seq.SystemSeqFactory;

import cn.sunline.suncard.tag.util.Utility;


/**
 * 
 * @author    tpf
 * @version   1.0  Feb 15, 2012
 */

public class SystemContextListener extends ContextLoaderListener implements ServletContextListener,ApplicationContextAware {
	
	private final static String SYSTEM_CONFIG="systemConfigLocation";
	private final static String I18N_CONFIG = "i18nConfigLocation";
	Logger logger = Logger.getLogger(SystemContextListener.class);
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		// TODO Auto-generated method stub
	
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("stop the server...");
		//notifyAll();
		ServletContext servletContext = event.getServletContext();
		servletContext.removeAttribute(AclConstants.LOGIN_USER_LIST);
		servletContext.removeAttribute(AclConstants.LOGIN_SESSION_LIST);
//		systemThread.close();
		super.contextDestroyed(event);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("init the dp system start....");
		ServletContext servletContext = event.getServletContext();
		
		//初始化系统级属性配置
		initConfig(servletContext);
		
		//登录的用户管理
		manageSession(servletContext);
		
		//初始化SPRING
		super.contextInitialized(event);
		
		//初始化工厂
		initFactory();
		
	}
	
	
	/**
	 * 登录的用户管理
	 * 〈功能详细描述〉
	 * 修改日期：2012-8-19
	 * @author: tpf
	 * @param servletContext
	 */
	private void manageSession(ServletContext servletContext) {
		servletContext.setAttribute(AclConstants.LOGIN_USER_LIST,new Hashtable());
		servletContext.setAttribute(AclConstants.LOGIN_SESSION_LIST, new HashMap());
	}
	
	private void initFactory(){
		try {
			//交易配置文件装载
			logger.info("init the transaction config file...");
			TrxnMsgConfigFactory.getInstance().init();
			
			//交易实体初始化,顺序必须正确，先进行文件装载
			logger.info("init the transaction object...");
			TrxnMsgFactory.getInstance().init();
			
			//客户端配置初始化
			logger.info("load the client config...");
			ClientConfigFactory.getInstance().init();
			
			//命名SQL装载
			logger.info("load the named sql...");
			NamedSqlFactory.getInstance().init();
			
			//序号初始化
//			logger.info("init the seq....");
//			SystemSeqFactory.getInstance().init();
//			
			//客户端初始化
			logger.info("init the client socket....");
			ClientSocketFactory.getInstance().init();
			
			//微信平台错误码加载
			logger.info("load the error code ....");
			ErrorCodeFactory.getInstance().init();
			
			//微信平台参数加载
			logger.info("load the parameters of the platform");
			PlatformParaFactory.getInstance().init();
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化系统级属性配置
	 * 修改日期：2012-2-25
	 * @author: tpf
	 * @param ServletContext servletContext
	 */
	private void initConfig(ServletContext servletContext) {
		
		String rootLocation = servletContext.getRealPath("/");
		String systemLocation = servletContext.getInitParameter(SYSTEM_CONFIG);
		String i18nLocation = servletContext.getInitParameter(I18N_CONFIG);
		System.setProperty(BaseConstants.ROOT_LOCATION, rootLocation);
		
		//初始化国际配置文件
		logger.info("init the i118 file...");
		Utility.initDefaultResourceBundle(rootLocation+i18nLocation);
		logger.info("i18n file path is : "+rootLocation+i18nLocation);
//		System.out.println("国际化文件位置："+rootLocation+i18nLocation);
		logger.info("system configuration file:"+rootLocation+systemLocation);
		Properties systemProperties = new Properties();
		
		logger.info("set the system parameters.....");
		try {
			systemProperties.load(servletContext.getResourceAsStream(systemLocation));
			logger.info("read the parameters:");
			for(Map.Entry<Object, Object> entry : systemProperties.entrySet()){
				logger.info("add the parameter:"+entry.getKey()+":["+entry.getValue()+"] into the system eviroment");
				System.setProperty(entry.getKey().toString(), entry.getValue().toString());
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			e.printStackTrace();
		}
	}
	
}
