/**
 * 文件名：ConnectionCount.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：链接统计类
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.trxn.server;

import java.util.HashMap;

import org.apache.log4j.Logger;



/**
 * 链接统计类
 * 该类进行链接的统计，以及相关操作
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 */

public class ConnectionCount {
	private static Logger logger = Logger.getLogger(ConnectionCount.class);
	private static ConnectionCount connectionCount=null;
	private HashMap<String,ServerSocketProcess> serverSocketProcessMap=null;
	
	private ConnectionCount(){
		serverSocketProcessMap=new HashMap<String,ServerSocketProcess>();
	}
	
	public static ConnectionCount getInstance(){
		synchronized(ConnectionCount.class){
			if(connectionCount==null)connectionCount=new ConnectionCount();
			return connectionCount;
		}
	}
	
	public int getConnCount(){
		return serverSocketProcessMap.size();
	}
	
	public synchronized void remove(String key){
		logger.info("close the client with name: "+key);
		if(serverSocketProcessMap.containsKey(key))serverSocketProcessMap.remove(key);
	}
	
	public synchronized void removeAll() throws Exception{
		logger.info("close all the clients....");

		for(String key:serverSocketProcessMap.keySet()){
			serverSocketProcessMap.get(key).close();
		}
		serverSocketProcessMap.clear();
	}
	
	public synchronized void add(String key,ServerSocketProcess serverSocketProcess){
		logger.info("add a client with name："+key);
		serverSocketProcessMap.put(key, serverSocketProcess);
	}
}
