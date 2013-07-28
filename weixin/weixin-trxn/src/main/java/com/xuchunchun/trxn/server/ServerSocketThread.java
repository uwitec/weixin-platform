/**
 * 文件名：ServerSocketThread.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：监控服务器端程序
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.trxn.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


/**
 * 监控服务器端程序
 * 该程序接收所有socket的链接，并且接受交易信息
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 */

public class ServerSocketThread implements Runnable {
	private ServerSocket serverSocket;
	private Logger logger = Logger.getLogger(ServerSocketThread.class);
	private boolean avaiable=true;
	private ConnectionCount connCount=null;
	private long index=0;
	private int port;
	
	public ServerSocketThread(int port){
		this.port=port;
	}
	
	public void run() {
		try{
			serverSocket=new ServerSocket(port);
			logger.info("服务器已启动");
			connCount=ConnectionCount.getInstance();
			
			while(avaiable){
				Socket socket=serverSocket.accept();
				if(serverSocket.isClosed())break;
				index=index+1;
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String threadName=df.format(new Date())+String.format("%03d", index);
				ServerSocketProcess serverSocketProcess=new ServerSocketProcess(socket,threadName);
				Thread serverProcess = new Thread(serverSocketProcess);
				serverProcess.setName(threadName);
				serverProcess.start();
				
				connCount.add(threadName, serverSocketProcess);
				
				logger.info("有连接接入,现在总连接数:"+connCount.getConnCount());
			}
		}catch(SocketException se){
			if(avaiable){
				logger.error("服务器端socket异常，异常信息:"+se.getMessage()+"，系统关闭服务器进程");
				close();
			}
		}catch(Exception e){
			logger.error(e);
		}
	}
	
	public void close(){
		try{
			connCount.removeAll();
			avaiable=false;
			serverSocket.close();
		}catch(Exception e){
			logger.error(e);
		}
	}
}
