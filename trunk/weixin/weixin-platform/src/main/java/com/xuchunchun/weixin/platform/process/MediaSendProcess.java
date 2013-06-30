package com.xuchunchun.weixin.platform.process;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.AccessTokenBean;
import com.xuchunchun.weixin.platform.bean.MediaUploadBean;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.util.ErrorCodeUtil;

public class MediaSendProcess extends CommonRequestProcess {
	private Logger logger = Logger.getLogger(MediaSendProcess.class);
	
	public enum MediaType{
		image("image"),voice("voice"),video("video"),thumb("thumb");
		
		private String type;
		
		private MediaType(String type){
			this.type = type;
		}
		
		public String getType(){
			return type;
		}
	};
	
	private MediaType type = null;
	private String filePath = null;
	private String openId = null;
	
	public MediaSendProcess(MediaType type,String filePath,String openId){
		this.type = type;
		this.filePath = filePath;
		this.openId = openId;
	}
	
	public void mediaSend() throws Exception{
		logger.info("send the media with file path :"+filePath+" to the user "+openId);
		doRequest(null,RequestType.POST);
	}

	@Override
	protected String formatUrl() throws Exception {
		PlatformParaFactory platformParaFactory = PlatformParaFactory.getInstance();
		String commonUrl = platformParaFactory.getParameter(PlatformConstants.MEDIA_SEND);
		return String.format(commonUrl, getAccessToken(),type.getType(),openId);
	}

	@Override
	protected void setRequestPara(HttpMethod method) throws Exception {
		PostMethod post = (PostMethod)method;
		Part[] parts = null;
        parts = new Part[]{
                new FilePart("media", new File(filePath))};
        
        RequestEntity entity = new MultipartRequestEntity(parts,post.getParams());
        post.setRequestEntity(entity);
		
	}

}
