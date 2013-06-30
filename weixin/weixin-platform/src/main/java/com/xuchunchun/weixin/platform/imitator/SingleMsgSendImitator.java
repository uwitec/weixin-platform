package com.xuchunchun.weixin.platform.imitator;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.PlatformMsgRspBean;
import com.xuchunchun.weixin.platform.bean.SingleMsgSendBean;
import com.xuchunchun.weixin.platform.bean.SingleMsgSendBean.MsgType;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;

public class SingleMsgSendImitator extends WebSiteImitator {
	private Logger logger = Logger.getLogger(SingleMsgSendImitator.class);

	private SingleMsgSendBean singleMsgSendBean = null;
	
	private PlatformMsgRspBean platformMsgRspBean = null;
	
	private boolean result = false;
	
	public SingleMsgSendImitator(SingleMsgSendBean singleMsgSendBean){
		this.singleMsgSendBean = singleMsgSendBean;
	}
	
	public SingleMsgSendImitator(String content,String tofakeid,MsgType msgType){
    	singleMsgSendBean = new SingleMsgSendBean();
    	singleMsgSendBean.setContent(content);
    	singleMsgSendBean.setTofakeid(tofakeid);
    	singleMsgSendBean.setType(msgType);
    	
    }

	@Override
	protected String getRspText() throws Exception {
		try {
        	logger.info("send the single message...");
        	
        	singleMsgSendBean.setToken(token);
        	
        	String url = platformParaFactory.getParameter(PlatformConstants.SINGLE_SEND);
        	String refererUrl = String.format(platformParaFactory.getParameter(PlatformConstants.SINGLE_MSG_PAGE), singleMsgSendBean.getToken(),platformParaFactory.getParameter(PlatformConstants.FAKE_ID));
        	
        	logger.info("send the single message, the url is "+url);
            
        	PostMethod post = new PostMethod(url);
            post.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
            post.setRequestHeader(PlatformConstants.REFERER_H, refererUrl);
            post.setRequestHeader("Cookie", this.cookieStr);
            Part[] parts = null;
            parts = new Part[]{
                    new StringPart("content", singleMsgSendBean.getContent(),PlatformConstants.UTF_8),
                    new StringPart("type", singleMsgSendBean.getType()),
                    new StringPart("error", singleMsgSendBean.getError()),
                    new StringPart("token", singleMsgSendBean.getToken()),
                    new StringPart("ajax", singleMsgSendBean.getAjax()),
                    new StringPart("tofakeid", singleMsgSendBean.getTofakeid()),
                    new StringPart("t", "ajax-response")};
            
            RequestEntity entity = new MultipartRequestEntity(parts,post.getParams());
            post.setRequestEntity(entity);
            int status = client.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String text = post.getResponseBodyAsString();
                
                logger.info("the reponse message is "+text);
                
                return text;
            }
            return null;
        } catch (Exception e) {
            logger.info("error of send the message to the user..");
            return null;
        }
	}

	@Override
	protected void processRspText(String rspText) throws Exception {
		platformMsgRspBean = JsonUtil.parseToBean(rspText, PlatformMsgRspBean.class);
    	
    	if(ErrorCodeFactory.getInstance().getSuccessCode().equals(platformMsgRspBean.getRet())){
    		
    		
    		result = true;
    	}else{
    		//throw new BusinessLogicException(errorCode, args)
    		
    		result = false;
    	}
		
	}

	public PlatformMsgRspBean getSendMsgRspBean() {
		return platformMsgRspBean;
	}

	public void setSendMsgRspBean(PlatformMsgRspBean sendMsgRspBean) {
		this.platformMsgRspBean = sendMsgRspBean;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	@Override
	protected boolean doCheck() {
		return true;
	}

}
