/**
 * 文件名：TrxnQueue.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：队列抽象类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.base.system.queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xuchunchun.base.system.task.CaseTask;
import com.xuchunchun.base.system.task.MonitorTask;
import com.xuchunchun.base.system.task.NotifyTask;
import com.xuchunchun.base.util.SystemUtil;


/**
 * 队列抽象类
 * 该类实现基本队列操作
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 */

public class TaskQueueImpl implements TaskQueue<NotifyTask> {
	private Logger logger = Logger.getLogger(TaskQueueImpl.class);
	//private KeySet<String,NotifyTask> notifyQueue = new KeySet<String,NotifyTask>();
	private HashMap<String,LinkedList<NotifyTask>> notifyQueue = new HashMap<String,LinkedList<NotifyTask>>();
	private HashMap<String,MonitorTask> tasks = new HashMap<String,MonitorTask>();
	private HashMap<String,String> tasksOwner = new HashMap<String,String>();
	private String name = null;
	
	public TaskQueueImpl(String name){
		this.name=name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public HashMap<String,NotifyTask> getAllTasks(String name){
		HashMap<String,NotifyTask> hTasks = new HashMap<String,NotifyTask>();
		for(Map.Entry<String, String> entry : tasksOwner.entrySet()){
			if(name.equals(entry.getValue())){
				MonitorTask monitorTask = tasks.get(entry.getKey());
				NotifyTask tTask = new NotifyTask();
				tTask.setProcessType(NotifyTask.ADD_PROCESS_TYPE);
				tTask.setTask(monitorTask);
				tTask.setTarNotifyUser(name);
				String key = monitorTask.getKey()+"_"+NotifyTask.ADD_PROCESS_TYPE;
				hTasks.put(key, tTask);
			}
				
		}
		return hTasks;
	}
	
	/**
	 * 根据用户和任务id查找队列
	 * 修改日期：2012-8-23
	 * @author: tpf
	 * @param taskId
	 * @param employeeId
	 * @return
	 */
	public HashMap<String,NotifyTask> getAllTasks(String actorId, String taskAssignType, String groupAssignType,String taskId,String groupId){
		HashMap<String,NotifyTask> hTasks = new HashMap<String,NotifyTask>();
		if("E".equals(taskAssignType)) {
			for(Map.Entry<String, String> entry : tasksOwner.entrySet()){
				if(actorId.equals(entry.getValue()) || SystemUtil.EMPLOYEE_ASSIGN.equals(entry.getValue())){
					MonitorTask monitorTask = tasks.get(entry.getKey());
					CaseTask cTask = null;
					if(monitorTask instanceof CaseTask) {
						cTask = (CaseTask) monitorTask;
					}
					if(cTask != null && taskId.equals(cTask.getTaskId())) {
						NotifyTask tTask = new NotifyTask();
						tTask.setProcessType(NotifyTask.ADD_PROCESS_TYPE);
						tTask.setTask(monitorTask);
						tTask.setTarNotifyUser(actorId);
						String key = monitorTask.getKey()+"_"+NotifyTask.ADD_PROCESS_TYPE;
						
						hTasks.put(key, tTask);
					}
				}
			}
		} else if("C".equals(taskAssignType) && "C".equals(groupAssignType)) {
			for(Map.Entry<String, String> entry : tasksOwner.entrySet()){
				if(actorId.equals(entry.getValue()) || groupId.equals(entry.getValue())){
					MonitorTask monitorTask = tasks.get(entry.getKey());
					CaseTask cTask = null;
					if(monitorTask instanceof CaseTask) {
						cTask = (CaseTask) monitorTask;
					}
					if(cTask != null && taskId.equals(cTask.getTaskId())) {
						NotifyTask tTask = new NotifyTask();
						tTask.setProcessType(NotifyTask.ADD_PROCESS_TYPE);
						tTask.setTask(monitorTask);
						tTask.setTarNotifyUser(actorId);
						String key = monitorTask.getKey()+"_"+NotifyTask.ADD_PROCESS_TYPE;
						
						hTasks.put(key, tTask);
					}
				}
			}
		} else {
			for(Map.Entry<String, String> entry : tasksOwner.entrySet()){
				if(actorId.equals(entry.getValue())){
					MonitorTask monitorTask = tasks.get(entry.getKey());
					CaseTask cTask = null;
					if(monitorTask instanceof CaseTask) {
						cTask = (CaseTask) monitorTask;
					}
					NotifyTask tTask = new NotifyTask();
					tTask.setProcessType(NotifyTask.ADD_PROCESS_TYPE);
					tTask.setTask(monitorTask);
					tTask.setTarNotifyUser(actorId);
					String key = monitorTask.getKey()+"_"+NotifyTask.ADD_PROCESS_TYPE;
					hTasks.put(key, tTask);
				}
					
			}
		}
		return hTasks;
	}
	
	/**
	 * 查找所有队列任务（）
	 * 修改日期：2012-8-23
	 * @author: tpf
	 * @return
	 */
	public HashMap<String,NotifyTask> getAllTasks(){
		HashMap<String,NotifyTask> hTasks = new HashMap<String,NotifyTask>();
		for(Map.Entry<String, String> entry : tasksOwner.entrySet()){
			MonitorTask monitorTask = tasks.get(entry.getKey());
			NotifyTask tTask = new NotifyTask();
			tTask.setProcessType(NotifyTask.ADD_PROCESS_TYPE);
			tTask.setTask(monitorTask);
			tTask.setTarNotifyUser(tasksOwner.get(monitorTask.getKey()));
			String key = monitorTask.getKey()+"_"+NotifyTask.ADD_PROCESS_TYPE;
			hTasks.put(key, tTask);
				
		}
		return hTasks;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addTask(String srcName, String tarName, MonitorTask task) {
		NotifyTask nTask = new NotifyTask();
		nTask.setProcessType(NotifyTask.ADD_PROCESS_TYPE);
		nTask.setSrcNotifyUser(srcName);
		nTask.setTarNotifyUser(tarName);
		nTask.setTask(task);
		String key = task.getKey();
		tasks.put(key, task);
		tasksOwner.put(key, tarName);
		putNotify(nTask);
		
	}

	@Override
	public void deleteTask(String name, MonitorTask task) {
		NotifyTask nTask = new NotifyTask();
		nTask.setProcessType(NotifyTask.DELETE_PROCESS_TYPE);
		nTask.setSrcNotifyUser(name);
		String key = task.getKey();
		nTask.setTarNotifyUser(tasksOwner.remove(key));
		nTask.setTask(task);
		
		tasks.remove(key);
		
		putNotify(nTask);
	}

	@Override
	public NotifyTask popNotify(String name) {
		LinkedList<NotifyTask> pQueue = getQueue(name);
		
		if(notifyIsNull(name)){
			synchronized(pQueue){
				try {
					pQueue.wait();
				} catch (InterruptedException e) {
					logger.debug("the thread is Interrupted", e);
				}
			}
		}
		logger.debug("get a element from the queue,the queue size is "+notifySize(name));
		return pQueue.poll();
	}

	@Override
	public void updateTask(String name, MonitorTask task) {
		NotifyTask nTask = new NotifyTask();
		nTask.setProcessType(NotifyTask.UPDATE_PROCESS_TYPE);
		nTask.setSrcNotifyUser(name);
		String key = task.getKey();
		nTask.setTarNotifyUser(tasksOwner.get(key));
		nTask.setTask(task);
		
		tasks.put(key,task);
		
		putNotify(nTask);
		
	}
	
	public void transferTask(String sName,String tName, MonitorTask task) {
		deleteTask(sName,task);
		
		addTask(sName,tName,task);
	}
	
	/**
	 * 判断队列内是否存在元素
	 * @return true:队列内不存在元素
	 * 		   false:队列内存在元素
	 */
	public boolean notifyIsNull(String name) {
		if(notifySize(name)==0){
			return true;
		}
		else return false;
	}
	
	public LinkedList<NotifyTask> getQueue(String name){
		if(!notifyQueue.containsKey(name)){
			LinkedList<NotifyTask> pQueue = new LinkedList<NotifyTask>();
			notifyQueue.put(name, pQueue);
			return pQueue;
		}
		return notifyQueue.get(name);
	}
	
	
	/**
	 * 将元素放入队列内
	 * @param t:将要放入队列内的元素
	 */
	public void putNotify(NotifyTask notifyTask) {
		LinkedList<NotifyTask> pQueue = getQueue(notifyTask.getTarNotifyUser());
		
		synchronized(pQueue){
			pQueue.offer(notifyTask);
			logger.debug("put a element from the queue,the queue size is "+notifySize(notifyTask.getTarNotifyUser()));
			pQueue.notifyAll();
		}
		
	}
	
	/**
	 * 取得该队列内元素个数
	 * @return 队列内元素个数
	 */
	public int notifySize(String name) {
		if(!notifyQueue.containsKey(name)){
			return 0;
		}
		return notifyQueue.get(name).size();
	}
	
	
	public NotifyTask popNotifyWithTimeOut(String name,boolean getRecord,int timeout) {
		
		LinkedList<NotifyTask> pQueue = getQueue(name);
		
		if(notifyIsNull(name)){
			
			if(getRecord)return null;
			
			synchronized(pQueue){
			
				try {
					pQueue.wait(timeout);
					
					
				} catch (InterruptedException e) {
					logger.debug("the thread is Interrupted", e);
				}
			}
		}
		NotifyTask nTask = pQueue.poll();
		if(nTask != null)logger.debug("get a element from the queue,the queue size is "+notifySize(name));
		return nTask;
	}

	/* (non-Javadoc)
	 * @see cn.sunline.suncard.acd.queue.TaskQueue#clearQueue()
	 */
	@Override
	public void clearQueue() {
		tasks.clear();
		tasksOwner.clear();
	}
	

}
