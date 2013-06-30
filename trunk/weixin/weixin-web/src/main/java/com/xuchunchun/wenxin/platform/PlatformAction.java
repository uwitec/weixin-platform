package com.xuchunchun.wenxin.platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;
import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.weixin.common.SystemConstants;
import com.xuchunchun.weixin.platform.msg.ApplicationMsg;
import com.xuchunchun.weixin.platform.service.ApplicationMsgService;
import com.xuchunchun.weixin.platform.service.PushMessageService;

@Action("platformAction")
public class PlatformAction extends DefaultAction{
	private static final long serialVersionUID = 5770164753028659872L;
	private Logger logger = Logger.getLogger(PlatformAction.class);
	
	@Autowired
	private ApplicationMsgService applicationMsgService;
	
	@Autowired
	private PushMessageService pushMessageService;
	
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	
	public void process(){
		logger.info("start to switch the message.....");
		try {
		
			HttpServletRequest request= ServletActionContext.getRequest();
			logger.info("the method is "+request.getMethod());
			
			if(BaseConstants.GET_STR.equals(request.getMethod())){
				logger.info("this is a get mothod,this is [Application message]!");
				
				ApplicationMsg msg = new ApplicationMsg();
				msg.setEchostr(echostr);
				msg.setNonce(nonce);
				msg.setSignature(signature);
				msg.setTimestamp(timestamp);
				msg.setToken(System.getProperty(BaseConstants.TOKEN_STR));
				
				logger.info("echostr:"+msg.getEchostr());
				logger.info("nonce:"+msg.getNonce());
				logger.info("signature:"+msg.getSignature());
				logger.info("timestamp:"+msg.getTimestamp());
				logger.info("token:"+msg.getToken());
				
				String result = applicationMsgService.process(msg);
				
				logger.info("return result is: "+result);
				
				if(result != null)returnString(result);
			}else{
				logger.info("this is a post mothod,process the push message!");
				StringBuffer content = new StringBuffer();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(),BaseConstants.BASE_CHARSET));
				String line;
				while((line = reader.readLine()) != null){
					content.append(line);
				}
				
				
				logger.info("the data of post content is:"+content.toString());
				
				String result = pushMessageService.pushMsgProc(content.toString());
				
				logger.info("the response message is :"+result);
				
				if(result != null)returnString(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e, e.getCause());
		}
		
		logger.info("end of process the message....");
	}
	

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	
	
	
}
