/**
 * 文件名：TrxnQueue.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：队列抽象类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.base.system.queue;

import java.util.LinkedList;

import org.apache.log4j.Logger;

/**
 * 队列抽象类
 * 该类实现基本队列操作
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 */

public class AbstractQueue<T> implements Queue<T> {
	private Logger logger = Logger.getLogger(AbstractQueue.class);
	private LinkedList<T> queue = null;
	private String name = null;
	
	
	public AbstractQueue(){
		queue = new LinkedList<T>();
	}
	
	public AbstractQueue(String name){
		queue = new LinkedList<T>();
		this.name = name;
	}
	
	/**
	 * 设置队列名称
	 * @param name:队列的名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 取得队列名称
	 * @return 返回队列名称
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * 判断队列内是否存在元素
	 * @return true:队列内不存在元素
	 * 		   false:队列内存在元素
	 */
	public boolean isNull() {
		if(size()==0)return true;
		else return false;
	}
	
	/**
	 * 从队列内取出第一个元素,如果没有则等待
	 * @return 返回取出的第一个元素
	 */
	public synchronized T pop() {
		if(isNull()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				logger.debug("the thread is Interrupted", e);
			}
		}
		logger.debug("get a element from the queue,the queue size is "+size());
		return queue.poll();
	}
	
	/**
	 * 将元素放入队列内
	 * @param t:将要放入队列内的元素
	 */
	public synchronized boolean put(T t) {
		boolean flag = queue.offer(t);
		if(flag){
			this.notifyAll();
			logger.debug("put a element from the queue,the queue size is "+size());
		}
		return flag;
	}
	
	/**
	 * 取得该队列内元素个数
	 * @return 队列内元素个数
	 */
	public int size() {
		return queue.size();
	}

	

}
