package com.xuchunchun.trxn.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import com.xuchunchun.trxn.util.TrxnUtil;

public class ClientSocket {
	private ClientConfig clientConfig = null;
	private static Logger logger = Logger.getLogger(ClientSocket.class);
	private Socket socket = null;  
	private OutputStream out = null;  
	private InputStream in = null;  
	private InetSocketAddress endpoint = null;
	private static final int K_SIZE = 1024;
	private static final int HEAD_LENGTH = 4;
	private int timeout;

	public ClientSocket(ClientConfig clientConfig) throws Exception{
		this.clientConfig = clientConfig;
		init();
	}
	
	public void init() throws Exception{
		logger.info("初始化客户端配置");
		endpoint = new InetSocketAddress(clientConfig.getIp() , clientConfig.getPort());
		socket = new Socket();  
		//设置发送逗留时间
		int linger = clientConfig.getLinger();
		logger.info("设置发送逗留时间:"+linger);
		if(linger>0)
			socket.setSoLinger(true, linger);  
		else socket.setSoLinger(false, 0);
		//设置InputStream上调用 read()阻塞超时时间
		timeout = clientConfig.getTimeout();
		logger.info("设置InputStream上调用 read()阻塞超时时间:"+timeout);
		socket.setSoTimeout(timeout);  
		//设置socket发包缓冲；  
		logger.info("设置socket发包缓冲:"+clientConfig.getSendBufferSize()+"K");
		socket.setSendBufferSize(clientConfig.getSendBufferSize()*K_SIZE);  
		//设置socket底层接收缓冲 
		logger.info("设置socket底层接收缓冲:"+clientConfig.getReceiveBufferSize()+"K");
		socket.setReceiveBufferSize(clientConfig.getReceiveBufferSize()*K_SIZE);  
		//关闭Nagle算法.立即发包  
		logger.info("关闭Nagle算法.立即发包");
		socket.setTcpNoDelay(true);  
		//设置连接保持
		logger.info("是否保留长连接设置:"+clientConfig.isLongConn());
		socket.setKeepAlive(clientConfig.isLongConn());

	}
	
	public int getTimeout(){
		return timeout;
	}
	
	private byte[] readMessage(int length , InputStream in) throws IOException{
		byte[] buffer = new byte[length];
		int index = 0;
		int maxCount = length;
		while(index<maxCount){
			index = index + in.read(buffer, index, maxCount-index);
		}
		return buffer;
	}
	
	private void writeMessage(OutputStream out , byte[] message) throws IOException{
		int requestMessageLength = message.length;
		byte[] requestContent = new byte[requestMessageLength+HEAD_LENGTH];
		byte[] requestHead = TrxnUtil.toByteArray(requestMessageLength);
		
		System.arraycopy(requestHead, 0, requestContent, 0, HEAD_LENGTH);
		System.arraycopy(message, 0, requestContent, HEAD_LENGTH, requestMessageLength);
		
		out.write(requestContent);
		out.flush();
	}

	
	public synchronized byte[] sendMessage(byte[] message) throws Exception{
		int reConnCount = 0;
		while(!isServerAlive()){
			reConnCount++;
			if(reConnCount > clientConfig.getMaxReConn()){
				logger.info("已超过最大连接次数："+clientConfig.getMaxReConn()+"次，不再尝试连接");
				return null;
			}
			logger.info("目前无连接,尝试连接，目前连接次数："+reConnCount);
			try {
				connect();
				logger.info("连接成功.........");

			} catch (Exception e) {
				logger.debug(e.getMessage());
				logger.info("连接失败.........");

			}
		}
		
		
		try{
			logger.info("进行数据发送...........");
			
			// 输出请求
			writeMessage(out,message);
			
			logger.info("进行数据接收...........");
			
			// 接收应答
			
			byte[] responseHead = readMessage(HEAD_LENGTH,in);
			
			int bodyLength = TrxnUtil.byteToInt(responseHead);
			
			byte[] responseBody = readMessage(bodyLength,in);

			return responseBody;
		}catch(SocketTimeoutException ste){
			logger.info(ste);
			return null;
		}catch(Exception e){
			logger.info(e);
			throw e;
		}finally{
			if(!clientConfig.isLongConn()){
				try{
					close();
				}catch(IOException se){
					logger.error(se, se.getCause());
				}
			}
		}
	}
	
	public void connect() throws IOException{
		//连接服务器  
		logger.info("连接服务器："+clientConfig.getIp()+":"+clientConfig.getPort());
		socket.connect(endpoint);  
		//获取输出输入流  
		logger.info("获取输出输入流");
		out = socket.getOutputStream();  
		in = socket.getInputStream(); 

	}
	
	public boolean isServerAlive(){
		logger.info("获取服务器状态.......");
//		try {
//			socket.sendUrgentData(0);// 发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
//			return true;
//		} catch (Exception se) {
//			return false;
//		}
		
		if (this.socket == null) return false;
		if (this.socket.isClosed()) return false;
		try {
			this.socket.getInputStream();
			socket.getOutputStream().flush();
			//TODO:
			//sockets[i].sendUrgentData(0);
			return true;
		} catch (IOException e) {
			return false;
		}

	}
	
	public void close() throws IOException{
		logger.info("关闭连接.........");
		if (out != null) {
			out.close();
			logger.info("关闭输出流");
		}
		if (in != null) {
			in.close();
			logger.info("关闭输入流");
		}
		if (socket != null) {
			socket.close();
			logger.info("关闭连接");
		}

	}
	
	public void setTimeout(int timeout) throws Exception{
		if(socket != null)		
			socket.setSoTimeout(timeout);  

	}
	
	public void refresh(ClientConfig clientConfig) throws Exception{
		logger.info("进行配置刷新，重新载入配置");
		this.clientConfig = clientConfig;
		init();
	}
	
}
