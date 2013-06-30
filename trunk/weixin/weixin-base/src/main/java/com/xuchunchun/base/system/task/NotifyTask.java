/**
 * 文件名：NotifyTask.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：通知任务
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 22, 2012
 */
package com.xuchunchun.base.system.task;

/**
 * 进行通知
 * 通知处理
 * @author    xuchunchun
 * @version   1.0  Feb 22, 2012
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

public class NotifyTask {
	public final static String ADD_PROCESS_TYPE="A";
	public final static String DELETE_PROCESS_TYPE="D";
	public final static String UPDATE_PROCESS_TYPE="U";
	
	private String processType;
	
	private MonitorTask task;
	
	private String srcNotifyUser;
	
	private String tarNotifyUser;

	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}

	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}

	/**
	 * @return the task
	 */
	public MonitorTask getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(MonitorTask task) {
		this.task = task;
	}

	/**
	 * @return the srcNotifyUser
	 */
	public String getSrcNotifyUser() {
		return srcNotifyUser;
	}

	/**
	 * @param srcNotifyUser the srcNotifyUser to set
	 */
	public void setSrcNotifyUser(String srcNotifyUser) {
		this.srcNotifyUser = srcNotifyUser;
	}

	/**
	 * @return the tarNotifyUser
	 */
	public String getTarNotifyUser() {
		return tarNotifyUser;
	}

	/**
	 * @param tarNotifyUser the tarNotifyUser to set
	 */
	public void setTarNotifyUser(String tarNotifyUser) {
		this.tarNotifyUser = tarNotifyUser;
	}
}
