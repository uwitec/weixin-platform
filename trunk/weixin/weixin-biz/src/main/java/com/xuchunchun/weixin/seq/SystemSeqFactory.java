package com.xuchunchun.weixin.seq;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.base.util.ApplicationContextUtil;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.base.util.SystemUtil;
import com.xuchunchun.weixin.db.dao.WxCustomerDao;
import com.xuchunchun.weixin.db.dao.WxMenuprocmemoDao;
import com.xuchunchun.weixin.db.dao.WxPlatformtrxnmemoDao;
import com.xuchunchun.weixin.db.dao.WxSystemtrxnmemoDao;
import com.xuchunchun.weixin.db.entity.WxCustomer;
import com.xuchunchun.weixin.db.entity.WxCustomerId;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemo;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemoId;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxPlatformtrxnmemoId;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemo;
import com.xuchunchun.weixin.db.entity.WxSystemtrxnmemoId;


public class SystemSeqFactory {

	private Logger logger = Logger.getLogger(SystemSeqFactory.class);
	private static final String DATE_FORMAT = "yyyyMMddhhssmm";
	
	private static SystemSeqFactory systemSeqFactory = null;
	
	private HashMap<String,Integer> seqs = new HashMap<String,Integer>();
	
	private WxCustomerDao wxCustomerDao;
	
	private WxPlatformtrxnmemoDao wxPlatformtrxnmemoDao;
	
	private WxSystemtrxnmemoDao wxSystemtrxnmemoDao;
	
	private WxMenuprocmemoDao wxMenuprocmemoDao;
	
	public static enum SEQ_TYPE{
		CUSTOMER,PALTFORM_TRXN,SYSTEM_TRXN,MENU_PROC_SEQ
	};
	
	private boolean isInit = false;
	
	private SystemSeqFactory(){
		wxCustomerDao = ApplicationContextUtil.getInstanceByBeanId("wxCustomerDao", WxCustomerDao.class);
		wxPlatformtrxnmemoDao = ApplicationContextUtil.getInstanceByBeanId("wxPlatformtrxnmemoDao", WxPlatformtrxnmemoDao.class);
		wxSystemtrxnmemoDao = ApplicationContextUtil.getInstanceByBeanId("wxSystemtrxnmemoDao", WxSystemtrxnmemoDao.class);
		wxMenuprocmemoDao = ApplicationContextUtil.getInstanceByBeanId("wxMenuprocmemoDao", WxMenuprocmemoDao.class);

	}
	
	public static SystemSeqFactory getInstance(){
		if(systemSeqFactory == null)systemSeqFactory = new SystemSeqFactory();
		return systemSeqFactory;
	}
	
	public synchronized String getSeq(SEQ_TYPE type){
		if(!isInit)init();
		switch(type){
		case CUSTOMER:{
			String key = getKey(type,null);
			int seq = 0;
			if(seqs.containsKey(key))seq = seqs.get(key);
			seq = seq+1;
			seqs.put(key, seq);
			return StrUtil.fill("L", '0', String.valueOf(seq), 20);

		}
		case MENU_PROC_SEQ:{
			String key = getKey(type,null);
			int seq = 0;
			if(seqs.containsKey(key))seq = seqs.get(key);
			seq = seq+1;
			seqs.put(key, seq);
			return StrUtil.fill("L", '0', String.valueOf(seq), 20);

		}
		case PALTFORM_TRXN:{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			String now = simpleDateFormat.format(new Date());
			String key = getKey(type,now.substring(0, 8));
			int seq = 0;
			if(seqs.containsKey(key))seq = seqs.get(key);
			seq = seq+1;
			seqs.put(key, seq);
			String seqStr = StrUtil.fill("L", '0', String.valueOf(seq), 5);
			return now+seqStr;
		}
		case SYSTEM_TRXN:{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
			String now = simpleDateFormat.format(new Date());
			String key = getKey(type,now.substring(0, 8));
			int seq = 0;
			if(seqs.containsKey(key))seq = seqs.get(key);
			seq = seq+1;
			seqs.put(key, seq);
			String seqStr = StrUtil.fill("L", '0', String.valueOf(seq), 5);
			return now+seqStr;
		}
		default : return null;
		}
		
	}
	
	private void initCustomerSeq(){
		logger.info("start to init the customer seq....");

		WxCustomer customer = new WxCustomer();
		WxCustomerId customerId = new WxCustomerId();
		customerId.setBankorgId(SystemUtil.getDefaultBankorgId());
		customer.setId(customerId);
				
		List<WxCustomer> customers = wxCustomerDao.findByTemplateWithSizeAndOrders(customer, 1, new String[]{"id.wxCustomerId","D"});
		
		String customerKey = getKey(SEQ_TYPE.CUSTOMER,null);
		
		if(customers == null || customers.size() == 0){
			seqs.put(customerKey, 0);
		}else{
			WxCustomer customerMax = customers.get(0);
			if(customerMax == null)			
				seqs.put(customerKey, 0);
			else{
				int seq = Integer.parseInt(customerMax.getId().getWxCustomerId());
				seqs.put(customerKey, seq);

			}

		}
		
		logger.info("the customer seq is "+seqs.get(customerKey));
	}
	
	private void initMenuProcSeq(){
		logger.info("start to init the menu process seq....");

		WxMenuprocmemo memo = new WxMenuprocmemo();
		WxMenuprocmemoId memoId = new WxMenuprocmemoId();
		memoId.setBankorgId(SystemUtil.getDefaultBankorgId());
		memo.setId(memoId);
		
		List<WxMenuprocmemo> memos = wxMenuprocmemoDao.findByTemplateWithSizeAndOrders(memo, 1, new String[]{"id.memoSeq","D"});
		
		String memoKey = getKey(SEQ_TYPE.MENU_PROC_SEQ,null);
		
		if(memos == null || memos.size() == 0){
			seqs.put(memoKey, 0);
		}else{
			WxMenuprocmemo memoMax = memos.get(0);
			if(memoMax == null)			
				seqs.put(memoKey, 0);
			else{
				int seq = Integer.parseInt(memoMax.getId().getMemoSeq());
				seqs.put(memoKey, seq);

			}

		}
		
		logger.info("the memo precess seq is "+seqs.get(memoKey));
	}
	
	private void initPlfTrxnSeq(){
		logger.info("start to init the paltform transaction seq....");
		WxPlatformtrxnmemo wxPlatformtrxnmemo = new WxPlatformtrxnmemo();
		WxPlatformtrxnmemoId wxPlatformtrxnmemoId = new WxPlatformtrxnmemoId();
		wxPlatformtrxnmemoId.setBankorgId(SystemUtil.getDefaultBankorgId());
		wxPlatformtrxnmemo.setId(wxPlatformtrxnmemoId);

		List<WxPlatformtrxnmemo> platformtrxnmemos = wxPlatformtrxnmemoDao.findByTemplateWithSizeAndOrders(wxPlatformtrxnmemo, 1, new String[]{"TRXN_SEQ","D"});
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		String now = simpleDateFormat.format(new Date());
		String platformtrxnKey = getKey(SEQ_TYPE.PALTFORM_TRXN,now);
		
		if(platformtrxnmemos == null || platformtrxnmemos.size() == 0){
			seqs.put(platformtrxnKey, 0);
		}else{
			WxPlatformtrxnmemo platformtrxnmemoMax = platformtrxnmemos.get(0);
			if(platformtrxnmemoMax == null)			

				seqs.put(platformtrxnKey, 0);
			else{
				String trxnSeq = platformtrxnmemoMax.getId().getTrxnSeq();
				platformtrxnKey = getKey(SEQ_TYPE.PALTFORM_TRXN,trxnSeq.substring(0, 8));
				int seq = Integer.parseInt(trxnSeq.substring(14));
				seqs.put(platformtrxnKey, seq);

			}

		}
		
		logger.info("the platform seq is "+seqs.get(platformtrxnKey));
	}
	
	private void initSysTrxnSeq(){
		logger.info("start to init the system transaction seq....");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
		String now = simpleDateFormat.format(new Date());
		
		WxSystemtrxnmemo wxSystemtrxnmemo = new WxSystemtrxnmemo();
		WxSystemtrxnmemoId wxSystemtrxnmemoId = new WxSystemtrxnmemoId();
		wxSystemtrxnmemoId.setBankorgId(SystemUtil.getDefaultBankorgId());
		wxSystemtrxnmemo.setId(wxSystemtrxnmemoId);

		List<WxSystemtrxnmemo> systemtrxnmemos = wxSystemtrxnmemoDao.findByTemplateWithSizeAndOrders(wxSystemtrxnmemo, 1, new String[]{"TRXN_SEQ","D"});
		
		
		String systemtrxnKey = getKey(SEQ_TYPE.SYSTEM_TRXN,now);
		
		if(systemtrxnmemos == null || systemtrxnmemos.size() == 0){
			seqs.put(systemtrxnKey, 0);
		}else{
			WxSystemtrxnmemo systemtrxnmemoMax = systemtrxnmemos.get(0);
			if(systemtrxnmemoMax == null)			

				seqs.put(systemtrxnKey, 0);
			else{
				String trxnSeq = systemtrxnmemoMax.getId().getTrxnSeq();
				systemtrxnKey = getKey(SEQ_TYPE.SYSTEM_TRXN,trxnSeq.substring(0, 8));
				int seq = Integer.parseInt(trxnSeq.substring(14));
				seqs.put(systemtrxnKey, seq);

			}

		}
		
		logger.info("the system seq is "+seqs.get(systemtrxnKey));
	}
	
	public void init(){
		logger.info("start to init the seq factory....");
		
		
		initCustomerSeq();
		
		initSysTrxnSeq();
		
		initPlfTrxnSeq();
		
		initMenuProcSeq();
		
		isInit = true;
		
	}
	
	private String getKey(SEQ_TYPE type,String subKey){
		if(subKey != null)
			return type.toString()+"-"+subKey;
		else return type.toString();
	}
	
	public static void main(String args[]){
		String expr = "(\\w*)C([0-9]+)$";
		Pattern pattern = Pattern.compile(expr);
		Matcher matcher = pattern.matcher("11C31");
		System.out.println(matcher.matches());

	}

}
