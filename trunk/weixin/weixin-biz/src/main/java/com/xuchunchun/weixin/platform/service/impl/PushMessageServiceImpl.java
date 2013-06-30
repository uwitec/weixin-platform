package com.xuchunchun.weixin.platform.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.common.BaseConstants;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.base.util.SystemUtil;
import com.xuchunchun.trxn.client.ClientConfig;
import com.xuchunchun.trxn.client.ClientConfigFactory;
import com.xuchunchun.trxn.client.ClientSocket;
import com.xuchunchun.trxn.client.ClientSocketFactory;
import com.xuchunchun.trxn.msg.TrxnMessage;
import com.xuchunchun.trxn.msg.TrxnMsgConfigFactory;
import com.xuchunchun.trxn.msg.TrxnMsgFactory;
import com.xuchunchun.trxn.msg.cfg.KeyField;
import com.xuchunchun.trxn.msg.cfg.TrxnConfig;
import com.xuchunchun.trxn.protocol.BaseProtocol;
import com.xuchunchun.trxn.protocol.cfg.ProtocolConfig;
import com.xuchunchun.trxn.util.FillValueUtil;
import com.xuchunchun.weixin.common.SystemConstants;
import com.xuchunchun.weixin.db.dao.WxCardDao;
import com.xuchunchun.weixin.db.dao.WxCustomerDao;
import com.xuchunchun.weixin.db.dao.WxMsgtemplateDao;
import com.xuchunchun.weixin.db.dao.WxPlatformtrxnmemoDao;
import com.xuchunchun.weixin.db.dao.WxSystemtrxnmemoDao;
import com.xuchunchun.weixin.db.dao.WxTrxnDao;
import com.xuchunchun.weixin.db.dao.WxTrxnmappingDao;
import com.xuchunchun.weixin.db.entity.WxCard;
import com.xuchunchun.weixin.db.entity.WxCardId;
import com.xuchunchun.weixin.db.entity.WxCustomer;
import com.xuchunchun.weixin.db.entity.WxCustomerId;
import com.xuchunchun.weixin.db.entity.WxMsgtemplate;
import com.xuchunchun.weixin.db.entity.WxMsgtemplateId;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemoId;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemoId;
import com.xuchunchun.weixin.db.entity.WxTrxn;
import com.xuchunchun.weixin.db.entity.WxTrxnId;
import com.xuchunchun.weixin.db.entity.WxTrxnmapping;
import com.xuchunchun.weixin.db.entity.WxTrxnmappingId;
import com.xuchunchun.weixin.platform.msg.PushMsg;
import com.xuchunchun.weixin.platform.msg.req.EventMsgRequest;
import com.xuchunchun.weixin.platform.msg.req.TextMsgRequest;
import com.xuchunchun.weixin.platform.msg.rsp.ArticleMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.ArticleSubMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.MusicMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.MusicSubMsgResponse;
import com.xuchunchun.weixin.platform.msg.rsp.TextMsgResponse;
import com.xuchunchun.weixin.platform.process.PushRequestMsgProcess;
import com.xuchunchun.weixin.platform.process.PushResponseMsgProcess;
import com.xuchunchun.weixin.platform.service.PushMessageService;
import com.xuchunchun.weixin.platform.util.PlatformUtil;
import com.xuchunchun.weixin.seq.SystemSeqFactory;

@Business("pushMessageService")
public class PushMessageServiceImpl implements PushMessageService {

	private Logger logger = Logger.getLogger(PushMessageServiceImpl.class);
	//private Long bankorgId = SystemUtil.getDefaultBankorgId();
	private static final String SUBSCRIBE = "subscribe";
	private static final String UNSUBSCRIBE = "unsubscribe";

	@Autowired
	private WxCustomerDao wxCustomerDao;

	@Autowired
	private WxTrxnmappingDao wxTrxnmappingDao;
	
	@Autowired
	private WxTrxnDao wxTrxnDao;
	
	@Autowired
	private WxCardDao wxCardDao;
	
	@Autowired
	private WxPlatformtrxnmemoDao wxPlatformtrxnmemoDao;
	
	@Autowired
	private WxSystemtrxnmemoDao wxSystemtrxnmemoDao;
	
	@Autowired
	private WxMsgtemplateDao wxMsgtemplateDao;
	
	@Override
	public String pushMsgProc(String msg) throws Exception {
		logger.info("接收到推送信息请求，开始处理......");
		long startTime=System.currentTimeMillis();
		long endTime = -1;
		long usedTime = -1;
		logger.info("进行报文解析...");
		
		HashMap<String,Object> trxnVariables = null;
		
		//step1:解析报文
		PushMsg message = PushRequestMsgProcess.parseXmlToObj(msg);
		
		Date now = new Date();
		
		logger.info("报文解析结束....");
		
		SystemSeqFactory systemSeqFactory = SystemSeqFactory.getInstance();
		
		WxPlatformtrxnmemo platformtrxnmemo = new WxPlatformtrxnmemo();
		WxPlatformtrxnmemoId platformtrxnmemoId = new WxPlatformtrxnmemoId();
		platformtrxnmemoId.setBankorgId(SystemUtil.getCurrBankorgId());
		platformtrxnmemoId.setTrxnSeq(systemSeqFactory.getSeq(SystemSeqFactory.SEQ_TYPE.PALTFORM_TRXN));
		platformtrxnmemo.setId(platformtrxnmemoId);
		
		platformtrxnmemo.setReqMsg(msg);
		platformtrxnmemo.setTrxnDate(now);
		
		
		String fromUserName = message.getFromUserName();
		String msgType = message.getMsgType();
		String toUserName = message.getToUserName();
		String createTime = new SimpleDateFormat(BaseConstants.DEFAULT_DATE_FOEMAT).format(message.getCreateTime());

		platformtrxnmemo.setPfAnotherId(fromUserName);

		
		logger.info("报文来源："+fromUserName);
		logger.info("报文类型："+msgType);
		logger.info("报文目标："+toUserName);
		logger.info("报文时间："+createTime);
		
		
				
		String reponseMsg = null;
		
		logger.info("交易前处理，如果是关注和取消关注事件....");
		
		WxCustomer customer = new WxCustomer();
		WxCustomerId customerId = new WxCustomerId();
		customerId.setBankorgId(SystemUtil.getCurrBankorgId());

		customer.setId(customerId);
		customer.setOpenId(fromUserName);
		
		List<WxCustomer> customers = wxCustomerDao.findByTemplate(customer);
		
		boolean existCust = true;
		
		if(customers == null || customers.size() == 0 || customers.get(0) == null){
			existCust = false;
		}else{
			customer = customers.get(0);
		}
		
		if(Enum.valueOf(PushMsg.requestMsgType.class, msgType) == PushMsg.requestMsgType.event){
			EventMsgRequest eventMsgRequest = (EventMsgRequest)message;
			
			String event = eventMsgRequest.getEvent();
			
			if(SUBSCRIBE.equals(event)){
				customer.setCustStatus(SystemConstants.CUST_STATUS_N);

				if(existCust){
					wxCustomerDao.update(customer);
				}else{
					customerId.setWxCustomerId(systemSeqFactory.getSeq(SystemSeqFactory.SEQ_TYPE.CUSTOMER));
					wxCustomerDao.save(customer);
					existCust = true;
					logger.info("新生成客户信息,客户ID是:"+customerId.getWxCustomerId());
				}
			}else if(UNSUBSCRIBE.equals(event)){
				if(existCust){
					customer.setCustStatus(SystemConstants.CUST_STATUS_S);
					wxCustomerDao.update(customer);
				}else{
					throw new Exception("can not found the customer with id:"+fromUserName+", can not update the customer status...");

				}
			}
		}
		
		logger.info("交易前处理，获取客户等信息.....");
		
		
		if(!existCust){
			throw new Exception("can not found the customer with id:"+fromUserName);
		}
		
		String wxCustomerId = customer.getId().getWxCustomerId();
		
		
		
		platformtrxnmemo.setWxCutsomerId(wxCustomerId);

		String cardNo = null;
		WxCard wxCard = null;
		
		if(SystemConstants.CUST_STATUS_B.equals(customer.getCustStatus())){
			logger.info("客户以做绑定操作，查询卡号..");
			wxCard = new WxCard();
			WxCardId wxCardId = new WxCardId();
			wxCardId.setBankorgId(SystemUtil.getCurrBankorgId());
			wxCard.setWxCutsomerId(wxCustomerId);
			
			if(Enum.valueOf(PushMsg.requestMsgType.class, msgType) == PushMsg.requestMsgType.text){
				TextMsgRequest textMsgRequest = (TextMsgRequest)message;
				String content = textMsgRequest.getContent();
				
				String expr = "(\\w*)C([0-9]+)$";
				Pattern pattern = Pattern.compile(expr);

				if(content != null){
					Matcher matcher = pattern.matcher(content);
					
					if(matcher.matches()){
						String op = matcher.group(1);
						long index = Long.parseLong(matcher.group(2));
						textMsgRequest.setContent(op);
						
						logger.info("the card index is "+index);
						
						
						wxCard.setCardIndex(index);
						
						
					}
				}
			}
			
			if(wxCard.getCardIndex() == null || wxCard.getCardIndex() == 0){
				logger.info("未指定卡号，默认取第一张卡....");
				wxCard.setCardIndex(1L);

			}
			
			List<WxCard> wxCards = wxCardDao.findByTemplate(wxCard);
			
			if(wxCards == null || wxCards.size() == 0 || (wxCard = wxCards.get(0)) == null){
				throw new Exception("找不到该客户对应的卡号......");
			}
			
			wxCard = wxCards.get(0);

			cardNo = wxCard.getId().getCardNo();
		}
		
		
		
		
		
		platformtrxnmemo.setCardNo(cardNo);

		logger.info("进行交易路由处理...");
		//step2:交易路由，获取交易类型
		WxTrxnmappingId wxTrxnmappingId = new WxTrxnmappingId();
		wxTrxnmappingId.setBankorgId(SystemUtil.getCurrBankorgId());
		wxTrxnmappingId.setMsgType(msgType);
		
		String msgKey = PlatformUtil.getMsgKey(message);
		logger.info("报文键值："+msgKey);

		platformtrxnmemo.setMsgKey(msgKey);
		platformtrxnmemo.setMsgType(msgType);
		
		wxTrxnmappingId.setMsgKey(msgKey);
		
		WxTrxnmapping wxTrxnmapping = wxTrxnmappingDao.findByPk(wxTrxnmappingId);
		
		
		if(wxTrxnmapping == null){
			logger.info("未找到交易，返回默认信息......");
			
			reponseMsg = PlatformUtil.getDefaultTextMessage(now, message.getFromUserName(), message.getToUserName());
			
			platformtrxnmemo.setRspMsgType("text");
			
			//platformtrxnmemo.setTrxnMemo(trxnMemo);
			

		}else{
			String trxnType = wxTrxnmapping.getTrxnId();
			
			WxTrxnId wxTrxnId = new WxTrxnId();
			wxTrxnId.setBankorgId(SystemUtil.getCurrBankorgId());
			wxTrxnId.setTrxnId(trxnType);
			WxTrxn wxTrxn = wxTrxnDao.findByPk(wxTrxnId);
			
			String clientName = wxTrxnmapping.getClientName();
			String rspMsgType = wxTrxnmapping.getRspMsgType();
			String rspMsgTempId = wxTrxnmapping.getMsgTempId();

			
			String configName = null;
			
			if(wxTrxn == null || StrUtil.isEmpty(rspMsgType) || StrUtil.isEmpty(rspMsgTempId)){
				throw new Exception("配置错误，交易相关配置存在未配置现象.....");
			}
			
			platformtrxnmemo.setRspMsgType(rspMsgType);
			platformtrxnmemo.setClientName(clientName);
			platformtrxnmemo.setConfigName(configName);
			platformtrxnmemo.setTrxnId(trxnType);
			platformtrxnmemo.setTrxnMemo(rspMsgTempId);
			
			TrxnMessage rspMsgObj = null;
			
			logger.info("生成交易变量....");
			trxnVariables = new HashMap<String,Object>();
			trxnVariables.put(SystemConstants.KEY_BANKORGID, SystemUtil.getCurrBankorgId());
			trxnVariables.put(SystemConstants.KEY_CARDNO, cardNo);
			trxnVariables.put(SystemConstants.KEY_CARDOBJ, wxCard);
			trxnVariables.put(SystemConstants.KEY_CUSTOMEROBJ, customer);
			trxnVariables.put(SystemConstants.KEY_CUSTOMERID, wxCustomerId);
			
			if(!StrUtil.isEmpty(clientName) && (configName = wxTrxn.getConfigName()) != null){
				logger.info("配置交易信息，本微信请求交易需要发送相关交易到指定客户端....");
				
				
				logger.info("生成交易日志....");
				
				WxSystemtrxnmemo wxSystemtrxnmemo = new WxSystemtrxnmemo();
				WxSystemtrxnmemoId wxSystemtrxnmemoId = new WxSystemtrxnmemoId();
				wxSystemtrxnmemoId.setBankorgId(SystemUtil.getCurrBankorgId());
				wxSystemtrxnmemoId.setTrxnSeq(systemSeqFactory.getSeq(SystemSeqFactory.SEQ_TYPE.SYSTEM_TRXN));
				wxSystemtrxnmemo.setId(wxSystemtrxnmemoId);
				wxSystemtrxnmemo.setCardNo(cardNo);
				wxSystemtrxnmemo.setClientName(clientName);
				wxSystemtrxnmemo.setConfigName(configName);
				wxSystemtrxnmemo.setTrxnDate(now);
				wxSystemtrxnmemo.setTrxnId(trxnType);
				wxSystemtrxnmemo.setWxCutsomerId(wxCustomerId);
				wxSystemtrxnmemo.setWxTrxnSeq(platformtrxnmemo.getId().getTrxnSeq());
				
				ClientConfig clientConfig = ClientConfigFactory.getInstance().getClientConfigByName(configName);
				if(clientConfig == null){
					throw new Exception("客户端配置找不到，请检查相关配置.....");
				}
				wxSystemtrxnmemo.setClientDesc(clientConfig.getIp()+":"+clientConfig.getPort());

				TrxnConfig trxnConfig = TrxnMsgConfigFactory.getInstance().getTrxnConfigByName(configName);
				if(trxnConfig == null){
					throw new Exception("交易配置找不到，请检查相关配置.....");
				}
				
				logger.info("进行交易处理...");
				//step3:生成交易，发送，处理
				TrxnMessage msgObj = TrxnMsgFactory.getInstance().getReqMessageClone(trxnConfig.getTrxnName());
				
				logger.info("交易变量预填充..");
				FillValueUtil.doPreload(trxnVariables, trxnConfig.getRequestFields().getPreloads());
				
				
				logger.info("请求报文对象组装....");
				boolean fillResult = FillValueUtil.fillReqMessage(trxnVariables, msgObj, trxnConfig.getRequestFields().getFields());
				logger.info("填充结果:"+fillResult);
				if(!fillResult){
					throw new Exception("请求报文填充失败，请检查配置文件...");
				}
				
				String className = clientConfig.getProtocolClass();
				
				Class protocolClazz = Class.forName(className);
				
				BaseProtocol protocolProcess = (BaseProtocol)protocolClazz.newInstance();
				
				String encoding = clientConfig.getEncoding();
				
				ProtocolConfig protocolConfig = clientConfig.getProtocolConfigObj(clientConfig.getName());
				
				byte[] sysReqMsg = protocolProcess.pack(msgObj, protocolConfig, encoding, trxnConfig);
				
				String sysReqMsgStr = new String(sysReqMsg,encoding);
				
				logger.info("交易请求报文:"+sysReqMsgStr);
				wxSystemtrxnmemo.setReqMsg(sysReqMsgStr);
				
				logger.info("获取交易客户端....");
				ClientSocket client = ClientSocketFactory.getInstance().getClientSocket(clientConfig.getName());
				
				endTime = System.currentTimeMillis();
				usedTime = endTime - startTime;
				logger.info("发送交易前总耗时:"+usedTime+"ms");
				
				int timeout = client.getTimeout()-(int)usedTime;
				
				logger.info("设置交易超时时间为:"+timeout);
				
				client.setTimeout(timeout);
				
				byte[] sysRspMsg = client.sendMessage(sysReqMsg);
				if(sysRspMsg == null)throw new Exception("连接不上服务器或者交易超时...");
				String sysRspMsgStr = new String(sysRspMsg,encoding);

				wxSystemtrxnmemo.setRspMsg(sysRspMsgStr);
				logger.info("交易响应报文:"+sysRspMsgStr);

				rspMsgObj = protocolProcess.unpack(sysRspMsg, protocolConfig, encoding, trxnConfig);
				
				//wxSystemtrxnmemo.setTrxnMemo(trxnMemo);
				wxSystemtrxnmemoDao.save(wxSystemtrxnmemo);
				
				logger.info("判断交易是否成功....");

				HashMap<String,KeyField> keyFields = trxnConfig.getResponseFields().getKeyFields();
				String reasonField = trxnConfig.getResponseFields().getReasonField();
				
				for(Map.Entry<String, KeyField> _keyField : keyFields.entrySet()){
					KeyField keyField = _keyField.getValue();
					String keyFieldName = keyField.getName();
					String keyFieldVal = keyField.getValText();
					
					String actureText = rspMsgObj.getSingleObj(keyFieldName).toString();
					
					if(keyFieldVal == null)throw new Exception("结果码值不能为空，请检查配置..");
					
					if(!keyFieldVal.equals(actureText)){
						logger.info("交易结果返回为失败...组成交易失败包处理....");
						String reason = rspMsgObj.getSingleObj(reasonField).toString();
						logger.info("失败原因为:"+reason);
						platformtrxnmemo.setRspMsgType("text");

						reponseMsg = PlatformUtil.getErrorTextMessage(now, message.getFromUserName(), message.getToUserName(),reason);
						break;
					}
				}
			}
			
			if(reponseMsg == null){

				logger.info("生成响应报文...");
				//step4:生成响应报文
				
				WxMsgtemplateId wxMsgtemplateId = new WxMsgtemplateId();
				wxMsgtemplateId.setBankorgId(SystemUtil.getCurrBankorgId());
				wxMsgtemplateId.setMsgTempId(rspMsgTempId);
				
				WxMsgtemplate wxMsgtemplate = wxMsgtemplateDao.findByPk(wxMsgtemplateId);
				
				if(wxMsgtemplate == null){
					reponseMsg = PlatformUtil.getDefaultTextMessage(now, message.getFromUserName(), message.getToUserName());
					
					platformtrxnmemo.setRspMsgType("text");
				}else{
					if(StrUtil.isEmpty(wxMsgtemplate.getMsgTempType()) || !wxMsgtemplate.getMsgTempType().equals(rspMsgType)){
						throw new Exception("返回消息类型不能为空并且消息类型必须和消息模板类型一致....");
					}else{
						PushMsg _rspMsgObj = genRspObj(wxMsgtemplate,trxnVariables,rspMsgObj);
						_rspMsgObj.setCreateTime(now);
						_rspMsgObj.setFromUserName(message.getToUserName());
						_rspMsgObj.setToUserName(message.getFromUserName());
						reponseMsg = PushResponseMsgProcess.parseObjToXml(_rspMsgObj);
					}
				}
			}
			logger.info("推送信息处理结束");
		}
		
		logger.info("响应报文："+reponseMsg);
		platformtrxnmemo.setRspMsg(reponseMsg);

		wxPlatformtrxnmemoDao.save(platformtrxnmemo);
		
		endTime = System.currentTimeMillis();
		usedTime = endTime - startTime;
		logger.info("总耗时:"+usedTime+"ms");
		System.out.println(usedTime);
		return reponseMsg;

		
	}
	
	private PushMsg genRspObj(WxMsgtemplate wxMsgtemplate,HashMap<String,Object> trxnCache,TrxnMessage msg) throws Exception{
		String type = wxMsgtemplate.getMsgTempType();
		switch(Enum.valueOf(PushMsg.responseMsgType.class, type)){
		case text:{
			TextMsgResponse defaultText = new TextMsgResponse();
			String content = wxMsgtemplate.getContent();
			defaultText.setContent(FillValueUtil.formatStr(content, trxnCache, msg, null));
			defaultText.setFuncFlag("0");
			defaultText.setMsgType(type);
			return defaultText;
		}
		case music:{
			MusicMsgResponse music = new MusicMsgResponse();
			MusicSubMsgResponse subMusic = new MusicSubMsgResponse();
			
			String title = wxMsgtemplate.getTitle();
			String desciption = wxMsgtemplate.getDescription();
			String musicUrl = wxMsgtemplate.getMusicUrl();
			String HqMusicUrl = wxMsgtemplate.getHqMusicUrl();
			
			subMusic.setDescription(FillValueUtil.formatStr(desciption, trxnCache, msg, null));
			subMusic.setHqMusicUrl(FillValueUtil.formatStr(HqMusicUrl, trxnCache, msg, null));
			subMusic.setMusicUrl(FillValueUtil.formatStr(musicUrl, trxnCache, msg, null));
			subMusic.setTitle(FillValueUtil.formatStr(title, trxnCache, msg, null));
			music.setMusic(subMusic);
			music.setFuncFlag("0");
			music.setMsgType(type);
			return music;
}
		case news:{
			ArticleMsgResponse article = new ArticleMsgResponse();
			
			String title = wxMsgtemplate.getTitle();
			String desciption = wxMsgtemplate.getDescription();
			String picUrl = wxMsgtemplate.getPicUrl();
			String url = wxMsgtemplate.getUrl();
			
			String loopVar = wxMsgtemplate.getLoopVar();
			List loopObjs = (List)FillValueUtil.getFillValue(trxnCache, loopVar, null, true, msg);
			
			article.setArticleCount(loopObjs.size());
			
			for(Object loopObj : loopObjs){
				ArticleSubMsgResponse subArtic = new ArticleSubMsgResponse();
				subArtic.setDescription(FillValueUtil.formatStr(desciption, trxnCache, msg, loopObj));
				subArtic.setPicUrl(FillValueUtil.formatStr(picUrl, trxnCache, msg, loopObj));
				subArtic.setUrl(FillValueUtil.formatStr(url, trxnCache, msg, loopObj));
				subArtic.setTitle(FillValueUtil.formatStr(title, trxnCache, msg, loopObj));
				article.addArticle(subArtic);
			}
			
			
			article.setFuncFlag("0");
			article.setMsgType(type);
			return article;
		}
		default:{
			throw new Exception("消息模板类型配置错误，目前类型名称为:"+type);
		}
		}
	}


}
