/**
 * 文件名：ServerSocketProcess.java
 * 版权：Copyright 2012-2020 Sunline Tech. Co. Ltd. All Rights Reserved. 
 * 描述：服务器处理进程
 * 修改人：xuchunchun
 * 修改内容：新增
 * 修改时间：Feb 14, 2012
 */
package com.xuchunchun.trxn.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;



/**
 * 服务器处理进程
 * 该进程将接受消息，放入队列内
 * @author    xuchunchun
 * @version   1.0  Feb 14, 2012
 */

public class ServerSocketProcess implements Runnable {
	private Socket socket;
	private String name;
	private Logger logger = Logger.getLogger(ServerSocketProcess.class);
	private boolean avaiable = true;
	private DataInputStream serverIn = null;
	private QueueFactory queueFactory = null;
	
	public ServerSocketProcess(Socket socket,String threadName){
		this.socket=socket;
		this.name=threadName;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void close(){
		avaiable=false;
		try {
			serverIn.close();
			socket.close();
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public void run() {
		logger.info("start to receive transaction message!");
		try{
	        serverIn = new DataInputStream (new BufferedInputStream (socket.getInputStream ()));
			while(avaiable){
				int len = getMessageLength();
				logger.debug("the message length is "+len);
				byte[] buffer = new byte[len];
				int index=0;
				int readLen=0;
				while(index<len){
					readLen = serverIn.read(buffer,index,len-index);
					if (readLen > 0){ 
                        index = index + len; 
                    } 
                    else{ 
                        break; 
                    } 
				}
				
				logger.debug("has finish reading the message! start to put the message into the queue");
				queueFactory = QueueFactory.getInstance();
				
				queueFactory.getOrCreateDefaultTrxnQueue().put(buffer);
			}
		}catch(IOException ioe){
			
		}
	}
	
	protected int getMessageLength() throws IOException{
        int l = 0;
        int lengthSize=4;
        byte[] b = new byte[lengthSize];
        while (l == 0) {
        	serverIn.readFully(b,0,lengthSize);
        	String lengthString = new String(b);
        	Pattern pattern = Pattern.compile("\\d*");
        	if(!pattern.matcher(lengthString).matches())continue;
        	l=Integer.parseInt(lengthString);
        	logger.info("receive message Length:"+l);
        }
        
        logger.info("acture message Length:"+l);
        
        return l;
    }
	
}
