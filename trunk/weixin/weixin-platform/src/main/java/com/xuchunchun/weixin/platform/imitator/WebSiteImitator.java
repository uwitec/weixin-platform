package com.xuchunchun.weixin.platform.imitator;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessException;
import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.base.util.PWD;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.LoginRspBean;
import com.xuchunchun.weixin.platform.bean.PlatformMsgRspBean;
import com.xuchunchun.weixin.platform.bean.SingleMsgSendBean;
import com.xuchunchun.weixin.platform.bean.SingleMsgSendBean.MsgType;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;

public abstract class WebSiteImitator {
	private Logger logger = Logger.getLogger(WebSiteImitator.class);
	
    protected HttpClient client = new HttpClient();
    
    protected PlatformParaFactory platformParaFactory = null;
    
    protected static Cookie[] cookies;
    protected static String cookieStr;
    protected static String token;
    
    private String user = null;
	private String password = null;
	
	private static final String LOGGINED_STR_1 = "登录超时";
	private static final String LOGGINED_STR_2 = "重新登录";
	
	private static boolean isLogin = false;


    public WebSiteImitator(){
    	platformParaFactory = PlatformParaFactory.getInstance();
    	user = platformParaFactory.getParameter(PlatformConstants.LOGIN_USER);
    	password = PWD.dec(platformParaFactory.getParameter(PlatformConstants.LOGIN_PASSWORD));
    	loginCount = Integer.parseInt(platformParaFactory.getParameter(PlatformConstants.LOGIN_COUNTS));
    	client = new HttpClient();
    }
	
    private static int loginCount = -1;
    
    private LoginRspBean retcode = null;


    public void login() throws BusinessException {
        boolean result = false;
        int count = 0;
        while (!result && count<loginCount) {
           
            logger.info("try to loging,the count is "+(count+1));
            result = _login();
            count++;
            if(!result){
            	logger.info("loging failed,try to logging....");
            	try {
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                
	            }
            }

        }
        
        isLogin = result;
        
        if(!result){
        	throw new BusinessLogicException("0013", "");
        }
        
        logger.info("loggin process.....OK");
        
        logger.info("check the reponse message...");
        
        String errorCode = String.valueOf(retcode.getErrCode());
    	String desc = ErrorCodeFactory.getInstance().getErrorDesc(errorCode);
        
        if(ErrorCodeFactory.getInstance().getSuccessCode().equals(errorCode)){
            isLogin = true;
        	logger.info("set the cookies message...");
        	this.cookies = client.getState().getCookies();
            StringBuffer cookie = new StringBuffer();
            for (Cookie c : client.getState().getCookies()) {
                cookie.append(c.getName()).append("=")
                        .append(c.getValue()).append(";");
            }
            this.cookieStr = cookie.toString();
            
            logger.info("set the token message...");
            this.token = getToken(retcode.getErrMsg());
            
            if(token == null){
            	//解析token失败
            	throw new BusinessLogicException("0014", "");
            }
        }else{
            isLogin = false;

        	throw new BusinessLogicException("0012", errorCode,desc);
        }
    }

    private boolean _login() {
        try {
        	String url = platformParaFactory.getParameter(PlatformConstants.LOGIN_URL);
        	
        	logger.info("login url:"+url);
        	logger.info("login user:"+user);
        	
            PostMethod post = new PostMethod(url);
            post.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
            NameValuePair[] params = new NameValuePair[]{
                    new NameValuePair("username", user),
                    new NameValuePair("pwd", DigestUtils.md5Hex(password.getBytes())), 
                    new NameValuePair("f", "json"),
                    new NameValuePair("imagecode", "")};
            post.setQueryString(params);
            int status = client.executeMethod(post);
            if (status == HttpStatus.SC_OK) {
                String ret = post.getResponseBodyAsString();
                logger.info("finish loging,the response message is :"+ret);
                retcode = JsonUtil.parseToBean(ret, LoginRspBean.class);
                if (retcode.getRet() == 302 ) {
                    
                    return true;
                }
                
            }
        } catch (Exception e) {
            logger.info("[logging failed][the reason is" + e.getMessage() + "]");
            return false;
        }
        return false;
    }
    
    private String getToken(String s) {
        try {
            if (StrUtil.isEmpty(s))
                return null;
            String[] ss = s.split("\\?");
            String[] params = null;
            if (ss.length == 2) {
                if (!StrUtil.isEmpty(ss[1]))
                    params = ss[1].split("&");
            } else if (ss.length == 1) {
                if (!StrUtil.isEmpty(ss[0]) && ss[0].indexOf("&") != -1)
                    params = ss[0].split("&");
            } else {
                return null;
            }
            for (String param : params) {
                if (StrUtil.isEmpty(param))
                    continue;
                String[] p = param.split("=");
                if (null != p && p.length == 2 && "token".equalsIgnoreCase(p[0]))
                    return p[1];

            }
        } catch (Exception e) {
            logger.info("failed of parsing Token][the message is ：" + e.getMessage() + "]");
            //e.printStackTrace();
        	return null;
        }
        return null;
    }
    
    
    public void logout() throws HttpException, IOException{
    	String url = platformParaFactory.getParameter(PlatformConstants.LOGOUT_URL);
    	
    	logger.info("do the logout process the url is "+url);
    	
        GetMethod get = new GetMethod(url);
        get.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
        get.setRequestHeader("Cookie", this.cookieStr);
        int status = client.executeMethod(get);
        if (status == HttpStatus.SC_OK) {
            logger.info("sucess of logout");
        }
    }
    
    public InputStream code() throws HttpException, IOException {
    	String url = platformParaFactory.getParameter(PlatformConstants.VERIFY_CODE);
    	
    	logger.info("get the cerify code the url is "+url);
    	
        GetMethod get = new GetMethod(url);
        get.setRequestHeader(PlatformConstants.USER_AGENT_H, PlatformConstants.USER_AGENT);
        get.setRequestHeader("Cookie", this.cookieStr);
        NameValuePair[] params = new NameValuePair[]{
                new NameValuePair("username", user),
                new NameValuePair("r", "1365318662649")};
        get.setQueryString(params);
        int status = client.executeMethod(get);
        if (status == HttpStatus.SC_OK) {
            return get.getResponseBodyAsStream();
        }
        return null;
    }
    
    protected abstract String getRspText() throws Exception;
    
    protected abstract boolean doCheck();
    
    public void doProcess() throws Exception{
    	
    	logger.info("check the input parameters....");
    	if(!doCheck()){
    		logger.info("the chech result is false...");
    		throw new BusinessLogicException("0017", "");
    	}
    	
    	if(!isLogin){
    		logger.info("not logging....");
    		login();
    	}
    	
    	String rspText = getRspText();
    	
    	if(rspText == null)throw new BusinessLogicException("0015", "");
    	
    	boolean result = checkIsLogin(rspText);
    	logger.info("check is logging....the result is "+result);
    	
    	if(!result){
    		login();
    		
    		if(!isLogin){
        		logger.info("logging failed....");
        		return;
        	}
        	
        	logger.info("get the response text again....");
        	
        	rspText = getRspText();
        	if(rspText == null)throw new BusinessLogicException("0015", "");

    	}
    	
    	logger.info("parse the text....");
    	
    	processRspText(rspText);
    }
    
    protected abstract void processRspText(String rspText) throws Exception;
    
    private boolean checkIsLogin(String rspText){
    	if(rspText.indexOf(LOGGINED_STR_1) > 0 && rspText.indexOf(LOGGINED_STR_2) > 0)return false;
    	return true;
    }
    
    public static void main(String args[]){
		String s ="/cgi-bin/indexpage?t=wxm-index&lang=zh_CN&token=1331604278";
		//System.out.println(getToken(s));
	}
}
