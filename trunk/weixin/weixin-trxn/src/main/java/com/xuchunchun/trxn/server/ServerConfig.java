package com.xuchunchun.trxn.server;

import java.util.HashMap;

import com.xuchunchun.trxn.protocol.cfg.ProtocolConfig;


public class ServerConfig {
	private String name;
	private String desc;
	private int port;
	private int timeout;
	private String encoding;
	private boolean longConn;
	private int linger;
	private int sendBufferSize;
	private int receiveBufferSize;
	private int maxReConn;
	private String protocolClass;
	private String protocolConfig;
	
	private HashMap<String,ProtocolConfig> protocolConfigObjs = null;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public boolean isLongConn() {
		return longConn;
	}
	public void setLongConn(boolean longConn) {
		this.longConn = longConn;
	}
	public int getLinger() {
		return linger;
	}
	public void setLinger(int linger) {
		this.linger = linger;
	}
	public int getSendBufferSize() {
		return sendBufferSize;
	}
	public void setSendBufferSize(int sendBufferSize) {
		this.sendBufferSize = sendBufferSize;
	}
	public int getReceiveBufferSize() {
		return receiveBufferSize;
	}
	public void setReceiveBufferSize(int receiveBufferSize) {
		this.receiveBufferSize = receiveBufferSize;
	}
	public int getMaxReConn() {
		return maxReConn;
	}
	public void setMaxReConn(int maxReConn) {
		this.maxReConn = maxReConn;
	}
	public String getProtocolClass() {
		return protocolClass;
	}
	public void setProtocolClass(String protocolClass) {
		this.protocolClass = protocolClass;
	}
	public String getProtocolConfig() {
		return protocolConfig;
	}
	public void setProtocolConfig(String protocolConfig) {
		this.protocolConfig = protocolConfig;
	}
	public HashMap<String, ProtocolConfig> getProtocolConfigObjs() {
		return protocolConfigObjs;
	}
	public void setProtocolConfigObjs(
			HashMap<String, ProtocolConfig> protocolConfigObjs) {
		this.protocolConfigObjs = protocolConfigObjs;
	}
	
	public void addProtocolConfigObjs(ProtocolConfig protocolConfigObj){
		if(protocolConfigObjs == null)protocolConfigObjs = new HashMap<String, ProtocolConfig>();
		protocolConfigObjs.put(protocolConfigObj.getTrxnName(), protocolConfigObj);
	}
	
	public ProtocolConfig getProtocolConfigObj(String trxnName){
		return protocolConfigObjs.get(trxnName);
	}
}
