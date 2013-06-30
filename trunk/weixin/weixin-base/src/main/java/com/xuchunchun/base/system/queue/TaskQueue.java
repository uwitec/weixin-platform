/**
 * 文件名：NamedQueue.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：任务队列接口
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.base.system.queue;

import com.xuchunchun.base.system.task.MonitorTask;


/**
 * 任务队列接口
 * 任务队列接口
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

public interface TaskQueue<NotifyTask> {
	
	public void addTask(String sName,String tName,MonitorTask task);
	
	public void deleteTask(String sName,MonitorTask task);
	
	public void updateTask(String sName,MonitorTask task);
	
	public NotifyTask popNotify(String name);
	
	/**
	 * 清空队列
	 * 修改日期：2012-9-20
	 * @author: tpf
	 */
	public void clearQueue();
}
