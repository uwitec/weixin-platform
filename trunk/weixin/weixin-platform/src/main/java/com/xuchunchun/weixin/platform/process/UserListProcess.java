package com.xuchunchun.weixin.platform.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;

import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.weixin.platform.PlatformConstants;
import com.xuchunchun.weixin.platform.bean.AccessTokenBean;
import com.xuchunchun.weixin.platform.bean.UserInfoBean;
import com.xuchunchun.weixin.platform.bean.UserListBean;
import com.xuchunchun.weixin.platform.factory.PlatformParaFactory;
import com.xuchunchun.weixin.platform.util.ErrorCodeUtil;

public class UserListProcess extends CommonRequestProcess {
	private Logger logger = Logger.getLogger(UserListProcess.class);
	private String nextOpenId = "";
	private String _nextOpenId = "";
	private List<String> userList = new ArrayList<String>();
	
	public UserListProcess(String nextOpenId){
		this.nextOpenId = nextOpenId;
		this._nextOpenId = nextOpenId;
	}
	
	public List<String> getUserList() throws Exception{
		logger.info("get list of user with next open id :"+nextOpenId);
		boolean stopInd = false;
		int counts = 0;
		while(!stopInd){
			UserListBean userListBean = doRequest(UserListBean.class,RequestType.GET);
			_nextOpenId = userListBean.getNext_openid();
			int total = userListBean.getTotal();
			int currCount = userListBean.getCount();
			counts = counts + currCount;
			logger.info("the total counts is"+total);
			logger.info("current counts is"+currCount);
			logger.info("counts is"+counts);
			

			if(StrUtil.isEmpty(_nextOpenId) || counts >= total){
				stopInd = true;
			}
			
			List<String> currUserList = userListBean.getData().getOpenid();

			
			if(stopInd && counts == currCount)return currUserList;
			else{
				userList.addAll(currUserList);
			}
			
		}
		return userList;
	}

	@Override
	protected String formatUrl() throws Exception {
		PlatformParaFactory platformParaFactory = PlatformParaFactory.getInstance();
		String commonUrl = platformParaFactory.getParameter(PlatformConstants.USER_LIST);
		
		return String.format(commonUrl, getAccessToken(),_nextOpenId);
	}

	@Override
	protected void setRequestPara(HttpMethod method) throws Exception {
		
		
	}

}
