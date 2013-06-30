package com.xuchunchun.weixin.platform.factory;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Synchronization;

import org.apache.log4j.Logger;

import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.AccessTokenBean;
import com.xuchunchun.weixin.platform.bean.ReponseMsgBean;
import com.xuchunchun.weixin.platform.process.AccessTokenProcess;
import com.xuchunchun.weixin.platform.process.CommonRequestProcess;


public class AccessTokenFactory {
	private static Logger logger = Logger.getLogger(AccessTokenFactory.class);
	
	private static AccessTokenFactory accessTokenFactory = null;
	
	private static int maxCounts = -1;
	
	private static Date expiryDate = null;
	private static AccessTokenBean accessTokenBean = null;
	private AccessTokenProcess accessTokenProcess = null;
	 
	
	private AccessTokenFactory(){
		accessTokenProcess = new AccessTokenProcess();
		maxCounts = Integer.parseInt(PlatformParaFactory.getInstance().getParameter(PlatformConstants.COMMON_REQUEST_MAX_COUNTS));
		try {
			loadData();
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			e.printStackTrace();
		}
	}
	
	public static AccessTokenFactory getInstance(){
		if(accessTokenFactory == null)accessTokenFactory = new AccessTokenFactory();
		return accessTokenFactory;
	}
	
	public synchronized String getAccessToken() throws Exception{
		boolean result = false;
		int count = 1;
		if(expiryDate == null || accessTokenBean == null){
			logger.info("the data is not init,load the data....");
			while(!(result = loadData()) && count <= maxCounts){
				logger.info("finish the "+count+"th loading.....");
				count++;
			}
			
			return result?accessTokenBean.getAccess_token():null;
		}
		
		Calendar nowCalendar = Calendar.getInstance();

		Calendar expiryDateCalendar = Calendar.getInstance();
		expiryDateCalendar.setTime(expiryDate);
		
		if(expiryDateCalendar.after(nowCalendar)){
			logger.info("the data is expiry,reload the data....");
			
			while(!(result = loadData()) && count <= maxCounts){
				logger.info("finish the "+count+"th loading.....");
				count++;
			}
			
			return result?accessTokenBean.getAccess_token():null;
		}
		
		return accessTokenBean.getAccess_token();

	}
	
	private boolean loadData() throws Exception{
		
		
		accessTokenBean = accessTokenProcess.getAccessTokenBean();
		
		if(accessTokenBean == null){
			ReponseMsgBean reponseMsgBean = accessTokenProcess.getErrorBean();
			logger.info("error of getting access token ["+reponseMsgBean.getErrcode()+":"+reponseMsgBean.getErrmsg()+"]");
			return false;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, accessTokenBean.getExpires_in());
		expiryDate = calendar.getTime();
		logger.info("success getting the access token...the value is "+accessTokenBean.getAccess_token());
		return true;
		
		
	}

}
