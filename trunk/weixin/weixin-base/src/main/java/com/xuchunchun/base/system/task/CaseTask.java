/**
 * 文件名：CaseTask.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：案件任务
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 21, 2012
 */
package com.xuchunchun.base.system.task;

import java.util.HashMap;

/**
 * 案件任务
 * 案件任务
 * @author    xuchunchun
 * @version   1.0  Feb 21, 2012
 * @see       [相关类/方法]
 * @since     [产品/模块版本] 
 */

public class CaseTask implements MonitorTask {
	private String taskId;
	private String workflowName;
	private String instanceId;
	private String caseId;	//案例
	
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseId() {
		return caseId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	@Override
	public String getKey() {
		return caseId;
	}

}
