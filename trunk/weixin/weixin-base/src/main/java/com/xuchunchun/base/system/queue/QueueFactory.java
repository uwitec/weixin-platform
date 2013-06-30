/**
 * 文件名：QueueFactory.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：队列工厂类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.base.system.queue;

import java.util.HashMap;

import org.apache.log4j.Logger;


/**
 * 队列工厂类
 * 通过该工厂类进行队列的创建
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 */

public class QueueFactory {
	private static QueueFactory queueFactory=null;
	private static HashMap<String,AbstractQueue<?>> QueuePool =null;
	private Logger logger = Logger.getLogger(QueueFactory.class);
	private static final String TRXNQUEUE="TRXN";
	private static final String SYSTEMQUEUE="SYSTEM";
	private static final String DEFAULT_TRXN_QUEUE_NAME="TRXNQUEUE";
	
	private QueueFactory(){
		QueuePool=new HashMap<String,AbstractQueue<?>>();
	}
	
	/**
	 * 创建该类的唯一实例
	 * @return 实例
	 */
	
	public static QueueFactory getInstance(){
		synchronized(QueueFactory.class){
			if(queueFactory==null)queueFactory=new QueueFactory();
			return queueFactory;
		}
	}
	
	/**
	 * 创建队列
	 * @param queueType：队列类型
	 * @param name：队列名称
	 * @return 新创建的队列，如果存在相同名称的，则返回null
	 */
	public AbstractQueue<?> createQueue(String queueType,String name){
		if(QueuePool.containsKey(name)){
			logger.debug("the pool contain the queue named "+name);
			return QueuePool.get(name);
		}
		AbstractQueue<?> mq = null;
		logger.info("create queue named:"+name+" type:"+queueType);
		if(TRXNQUEUE.equals(queueType)){
			mq = new AbstractQueue<byte[]>(name);
		}else if(SYSTEMQUEUE.equals(queueType)){
			
		}else{
			mq = new AbstractQueue(name);
		}
		QueuePool.put(name, mq);
		return mq;
	}
	
	public AbstractQueue<byte[]> createTrxnQueue(String name){
		return (AbstractQueue<byte[]>)createQueue(TRXNQUEUE,name);
	}
	
	public AbstractQueue<byte[]> createDefaultTrxnQueue(){
		return (AbstractQueue<byte[]>)createQueue(TRXNQUEUE,DEFAULT_TRXN_QUEUE_NAME);
	}
	
	/**
	 * 创建队列如果存在相同名字的队列的话则返回此队列
	 * @param queueType：队列类型
	 * @param name：队列名称
	 * @return 新创建的队列，如果存在相同名称的，则返回此队列
	 */
	public AbstractQueue<?> getOrCreateQueue(String queueType,String name){
		if(QueuePool.containsKey(name))return QueuePool.get(name);
		
		return createQueue(queueType,name);
	}
	
	public AbstractQueue<byte[]> getOrCreateTrxnQueue(String name){
		return (AbstractQueue<byte[]>)getOrCreateQueue(TRXNQUEUE,name);
	}
	
	public AbstractQueue<byte[]> getOrCreateDefaultTrxnQueue(){
		return (AbstractQueue<byte[]>)getOrCreateQueue(TRXNQUEUE,DEFAULT_TRXN_QUEUE_NAME);
	}
	
	/**
	 * 取得该名称队列
	 * @param name：队列名称
	 * @return 返回该名称的队列
	 */
	public AbstractQueue<?> getQueue(String name){
		if(QueuePool.containsKey(name))return QueuePool.get(name);
		return null;
	}
	
	/**
	 * 判断是否存在该名字的队列
	 * @param name：队列名称
	 * @return true:存在
	 *         false:不存在
	 */
	public boolean contains(String name){
		if(QueuePool.containsKey(name))return true;
		else return false;
	}
	
	/**
	 * 销毁该名字的队列
	 * @param name：队列名称
	 * 
	 */
	public void destroyQueue(String name){
		if(QueuePool.containsKey(name))QueuePool.remove(name);
	}
	
}
