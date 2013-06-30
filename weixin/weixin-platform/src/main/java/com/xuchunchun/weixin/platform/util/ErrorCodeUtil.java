package com.xuchunchun.weixin.platform.util;

import java.util.Map;

import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;

public class ErrorCodeUtil {
	private final static String ERRORCODE_KEY = "errcode";
	private final static String ERRORDESC_KEY = "errmsg";
	
	public static boolean isErrorMsg(Map<String,Object> msgMap){
		return msgMap.containsKey(ERRORCODE_KEY);
	}
	
	public static String getErrorCode(Map<String,Object> msgMap){
		return msgMap.get(ERRORCODE_KEY).toString();
	}
	
	public static String getErrorDesc(Map<String,Object> msgMap){
		return msgMap.get(ERRORDESC_KEY).toString();
	}
	
	public static String getErrorDescCn(Map<String,Object> msgMap){
		String code = getErrorCode(msgMap);
		if(StrUtil.isEmpty(code))return null;
		return ErrorCodeFactory.getInstance().getErrorDesc(code);
	}
}
