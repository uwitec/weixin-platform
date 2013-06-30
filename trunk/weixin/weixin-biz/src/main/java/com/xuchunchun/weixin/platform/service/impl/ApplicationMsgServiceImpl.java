package com.xuchunchun.weixin.platform.service.impl;

import org.apache.log4j.Logger;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.weixin.platform.msg.ApplicationMsg;
import com.xuchunchun.weixin.platform.process.AppMsgCheck;
import com.xuchunchun.weixin.platform.service.ApplicationMsgService;

@Business("applicationMsgService")
public class ApplicationMsgServiceImpl implements ApplicationMsgService {
	private Logger logger = Logger.getLogger(ApplicationMsgServiceImpl.class);
	@Override
	public String process(ApplicationMsg msg) throws Exception {
		logger.info("接收到应用申请请求，开始处理.............");
		boolean result = AppMsgCheck.check(msg);
		logger.info("处理完成，结果是："+result);
		if(result)return msg.getEchostr();
		
		else return null;
	}

}
