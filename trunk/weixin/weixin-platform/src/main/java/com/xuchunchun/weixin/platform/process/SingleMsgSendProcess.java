package com.xuchunchun.weixin.platform.process;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.AccessTokenBean;
import com.xuchunchun.weixin.platform.bean.ImageSendBean;
import com.xuchunchun.weixin.platform.bean.MusicSendBean;
import com.xuchunchun.weixin.platform.bean.NewsSendBean;
import com.xuchunchun.weixin.platform.bean.TextSendBean;
import com.xuchunchun.weixin.platform.bean.UserInfoBean;
import com.xuchunchun.weixin.platform.bean.VideoSendBean;
import com.xuchunchun.weixin.platform.bean.VoiceSendBean;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.util.ErrorCodeUtil;

public class SingleMsgSendProcess extends CommonRequestProcess {
	private Logger logger = Logger.getLogger(SingleMsgSendProcess.class);
	private String msg = "";
	
	public SingleMsgSendProcess(String msg){
		this.msg = msg;
	}
	
	public SingleMsgSendProcess(TextSendBean text){
		this.msg = JsonUtil.parseBeanToJson(text);

	}
	public SingleMsgSendProcess(ImageSendBean image){
		this.msg = JsonUtil.parseBeanToJson(image);

	}
	public SingleMsgSendProcess(VoiceSendBean voice){
		this.msg = JsonUtil.parseBeanToJson(voice);

	}
	public SingleMsgSendProcess(VideoSendBean video){
		this.msg = JsonUtil.parseBeanToJson(video);

	}
	public SingleMsgSendProcess(MusicSendBean music){
		this.msg = JsonUtil.parseBeanToJson(music);

	}
	public SingleMsgSendProcess(NewsSendBean news){
		this.msg = JsonUtil.parseBeanToJson(news);

	}
	public void send() throws Exception{
		logger.info("send msg:"+msg);
		doRequest(null,RequestType.POST);
	}

	@Override
	protected String formatUrl() throws Exception {
		PlatformParaFactory platformParaFactory = PlatformParaFactory.getInstance();
		String commonUrl = platformParaFactory.getParameter(PlatformConstants.SINGLE_MSG_SEND);
		
		return String.format(commonUrl, getAccessToken());
	}

	@Override
	protected void setRequestPara(HttpMethod method) throws Exception {
		PostMethod post = (PostMethod)method;

		RequestEntity entity = new StringRequestEntity(msg,PlatformConstants.TEXT_OR_XML, PlatformConstants.UTF_8);
		post.setRequestEntity(entity);
		
	}

}
