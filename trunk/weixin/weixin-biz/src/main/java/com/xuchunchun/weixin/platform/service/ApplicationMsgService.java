package com.xuchunchun.weixin.platform.service;

import com.xuchunchun.weixin.platform.msg.ApplicationMsg;

public interface ApplicationMsgService {
	
	public String process(ApplicationMsg msg) throws Exception;
	
	
}
