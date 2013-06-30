/**
 * 文件名：SystemUtil.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：系统工具类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 19, 2012
 */
package com.xuchunchun.base.util;

/**
 * 系统工具类
 * 系统相关操作
 * @author    xuchunchun
 * @version   1.0  Feb 19, 2012
 */

public class SystemUtil {
	private final static String BANKORGID="bankorgId";
	private final static String RMIADDRESS="rmiAddr";
	private final static String SYSTEMLOCATION="systemConfigLocation";
	private final static String ROOTLOCATION="rootLocation";
	private final static String EMBOSSDIR="embossDir";
	private final static String CREATTACHDIR="creAttachDir";
	private final static String LOGINSIND = "loginsInd";
	private final static String APPFILEDIR = "appFileDir";
	private final static String MAXTABCOUNT="maxTabCount";//页面最大标签数
	
	private static long bankorgId;
	private static String rmiAddr = null;
	private static String systemLocation = null;
	private static String rootLocation = null;
	private static String embossDir = null;
	private static String creAttachDir = null;
	private static String loginsInd = null;		//同一用户多处登录标识
	private static String appFileDir = null;
	private static String maxTabCount;
	
	
	public final static String EMPLOYEE_ASSIGN="9999999999";
	public final static String EMPLOYEE_SYSTEM="8888888888";
	public final static String GROUP_SYSTEM = "999";
	
	/**
	 * 获取系统参数
	 * @author: xuchunchun
	 * @param  key   系统环境变量key
	 * @return 环境变量值
	 */
	public static String getProperties(String key){
		return System.getProperty(key);
	}
	
	/**
	 * 获取当期银行机构号
	 * @author: xuchunchun
	 * @return 默认银行机构号
	 */
	public static long getCurrBankorgId(){
		if(bankorgId == 0 || bankorgId == -1)
			//bankorgId=Long.parseLong(getProperties(BANKORGID));
			
			bankorgId = SessionUtil.getBankorgId();
		if(bankorgId == -1)return getDefaultBankorgId();
		return bankorgId;
	}
	
	/**
	 * 获取默认银行机构号
	 * @author: xuchunchun
	 * @return 默认银行机构号
	 */
	public static long getDefaultBankorgId(){
		return Long.parseLong(getProperties(BANKORGID));
			
	}
	
	/**
	 * 获取RMI地址
	 * @author: xuchunchun
	 * @return rmi地址
	 */
	public static String getRMIAddress(){
		if(rmiAddr == null)rmiAddr=getProperties(RMIADDRESS);
		return rmiAddr;
	}
	
	/**
	 * 获取系统根路径
	 * @author: xuchunchun
	 * @return 系统根路径
	 */
	public static String getRootLocation(){
		if(rootLocation == null)rootLocation=getProperties(ROOTLOCATION);
		return rootLocation;
	}
	
	/**
	 * 获取系统路径
	 * @author: xuchunchun
	 * @return 系统路径
	 */
	public static String getSystemLocation(){
		if(systemLocation == null)systemLocation=getProperties(SYSTEMLOCATION);
		return systemLocation;
	}

	/**
	 * 获取制卡文件生成路径
	 * @author LuoYao
	 * @return 制卡文件生成路径
	 */
	public static String getEmbossDir(){
		if(embossDir == null)embossDir=getProperties(EMBOSSDIR);
		return embossDir;
	}
	
	/**
	 * 征信附件目录
	 * 修改日期：2012-8-19
	 * @author: tpf
	 * @return
	 */
	public static String getCreAttachDir(){
		if(creAttachDir == null) creAttachDir=getProperties(CREATTACHDIR);
		return creAttachDir;
	}
	
	/**
	 * 是否允许同一用户多处登录
	 * 修改日期：2012-8-19
	 * @author: tpf
	 * @return
	 */
	public static String getLoginsInd() {
		if(loginsInd == null) {
			loginsInd = getProperties(LOGINSIND);
		}
		return loginsInd;
	}
	
	/**
	 * 获取申请件文件生成路径
	 * @return
	 */
	public static String getAppFileDir() {
		if(appFileDir == null)appFileDir=getProperties(APPFILEDIR);
		return appFileDir;
	}
	
	/**
	 * 最大标签数
	 * @return
	 */
	public static String getMaxTabCount(){
		if(maxTabCount == null)maxTabCount=getProperties(MAXTABCOUNT);
		return maxTabCount;
	}
	
	public static String getCurrEmployee(){
		return SessionUtil.getCurrEmployee();
	}
}
