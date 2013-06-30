package com.xuchunchun.weixin.platform.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.base.util.ClassUtil;
import com.xuchunchun.weixin.platform.msg.PushMsg;
import com.xuchunchun.weixin.platform.msg.rsp.TextMsgResponse;
import com.xuchunchun.weixin.platform.process.PushResponseMsgProcess;

public class PlatformUtil {
	private static final String PATTERN_STR = "\\[(\\w*)\\]";
	
	public static String getMsgKey(PushMsg msg) throws Exception{
		String configKey = msg.getMsgType()+BaseConstants.CONTENT_KEY;
		String configVal = System.getProperty(configKey);
		if(configVal == null || "".equalsIgnoreCase(configVal))return null;
		Pattern pattern = Pattern.compile(PATTERN_STR);
		Matcher matcher = pattern.matcher(configVal);
		
		Class clazz = msg.getClass();
		
		while(matcher.find()){
			configVal = configVal.replaceFirst(PATTERN_STR, ClassUtil.getObj(msg, matcher.group(1)).toString());
		}
		return configVal;
	}
	
	public static String getDefaultTextMessage(Date now,String toUser,String fromUser) throws Exception{
		TextMsgResponse defaultText = new TextMsgResponse();
		
		defaultText.setContent(System.getProperty(BaseConstants.DEFAULT_WEIXIN_ERROR_MESSAGE));
		defaultText.setCreateTime(now);
		//test.setCreateTime(System.currentTimeMillis());
		defaultText.setFromUserName(fromUser);
		defaultText.setFuncFlag("0");
		defaultText.setMsgType("text");
		defaultText.setToUserName(toUser);
		
		return PushResponseMsgProcess.parseObjToXml(defaultText);
	}
	
	public static String getErrorTextMessage(Date now,String toUser,String fromUser,String reason) throws Exception{
		TextMsgResponse defaultText = new TextMsgResponse();
		
		defaultText.setContent(System.getProperty(BaseConstants.DEFAULT_WEIXIN_ERROR_MESSAGE_HEAD)+reason);
		defaultText.setCreateTime(now);
		//test.setCreateTime(System.currentTimeMillis());
		defaultText.setFromUserName(fromUser);
		defaultText.setFuncFlag("0");
		defaultText.setMsgType("text");
		defaultText.setToUserName(toUser);
		
		return PushResponseMsgProcess.parseObjToXml(defaultText);
	}
	
	
}
