package com.xuchunchun.weixin.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.exception.BusinessLogicException;
import com.xuchunchun.base.util.JsonUtil;
import com.xuchunchun.base.util.SystemUtil;
import com.xuchunchun.weixin.db.dao.WxCustomizemenuDao;
import com.xuchunchun.weixin.db.dao.WxMenuprocmemoDao;
import com.xuchunchun.weixin.db.entity.WxCustomizemenu;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemo;
import com.xuchunchun.weixin.db.entity.WxMenuprocmemoId;
import com.xuchunchun.weixin.platform.bean.CustomizeMenuBean;
import com.xuchunchun.weixin.platform.bean.ReponseMsgBean;
import com.xuchunchun.weixin.platform.factory.ErrorCodeFactory;
import com.xuchunchun.weixin.platform.process.CustomizeMenuProcess;
import com.xuchunchun.weixin.platform.service.CustomizeMenuService;
import com.xuchunchun.weixin.seq.CustomizeMenuUtil;
import com.xuchunchun.weixin.seq.SystemSeqFactory;

@Business("customizeMenuService")
public class CustomizeMenuServiceImpl implements CustomizeMenuService {
	private Logger logger = Logger.getLogger(CustomizeMenuServiceImpl.class);
	private static final String SUCCESS_FLAG = ErrorCodeFactory.getInstance().getSuccessCode();
	
	@Autowired
	private WxMenuprocmemoDao wxMenuprocmemoDao;
	
	@Autowired
	private WxCustomizemenuDao wxCustomizemenuDao;

	@Override
	public void createMenu(String menuJson) throws Exception {
		
		logger.info("发送创建请求给服务器....");
		
		ReponseMsgBean reponseMsgBean = CustomizeMenuProcess.createMenu(menuJson);
		
		String errorCode = reponseMsgBean.getErrcode();
		String errorDesc = ErrorCodeFactory.getInstance().getErrorDesc(errorCode);
		
		logger.info("请求响应码:"+errorCode+",描述:"+errorDesc);
		
		long bankorgId = SystemUtil.getCurrBankorgId();
		String employeeId = SystemUtil.getCurrEmployee();
		
		logger.info("记录操作备忘..");
		
		WxMenuprocmemoId wxMenuprocmemoId = new WxMenuprocmemoId();
		wxMenuprocmemoId.setBankorgId(bankorgId);
		wxMenuprocmemoId.setMemoSeq(SystemSeqFactory.getInstance().getSeq(SystemSeqFactory.SEQ_TYPE.MENU_PROC_SEQ));
		
		WxMenuprocmemo wxMenuprocmemo = new WxMenuprocmemo();
		wxMenuprocmemo.setErrorCode(errorCode);
		wxMenuprocmemo.setErrorMsg(reponseMsgBean.getErrmsg());
		wxMenuprocmemo.setErrorMsgCn(errorDesc);
		wxMenuprocmemo.setId(wxMenuprocmemoId);
		wxMenuprocmemo.setProcDate(new Date());
		wxMenuprocmemo.setProcMemo("");
		wxMenuprocmemo.setProcType("CREATE");
		wxMenuprocmemo.setProcUser(employeeId);
		
		wxMenuprocmemoDao.save(wxMenuprocmemo);
		
		if(SUCCESS_FLAG.equals(errorCode)){
			
			
			logger.info("创建成功，将原有数据库菜单数据删除....");
			wxCustomizemenuDao.deleteAllData(bankorgId);
			
			logger.info("将菜单数据保存进数据库");
			List<WxCustomizemenu> menus = CustomizeMenuUtil.changeStrToEntity(menuJson);
			wxCustomizemenuDao.saveBatch(menus);
			
		}else{
			throw new BusinessLogicException("0010", errorCode,errorDesc);
		}
		
	}

	@Override
	public String queryMenu() throws Exception {
		logger.info("发送查询请求给服务器....");
		
		CustomizeMenuBean customizeMenuBean = CustomizeMenuProcess.queryMenu();
		
		long bankorgId = SystemUtil.getCurrBankorgId();
		String employeeId = SystemUtil.getCurrEmployee();
		
		logger.info("记录操作备忘..");
		
		WxMenuprocmemoId wxMenuprocmemoId = new WxMenuprocmemoId();
		wxMenuprocmemoId.setBankorgId(bankorgId);
		wxMenuprocmemoId.setMemoSeq(SystemSeqFactory.getInstance().getSeq(SystemSeqFactory.SEQ_TYPE.MENU_PROC_SEQ));
		
		WxMenuprocmemo wxMenuprocmemo = new WxMenuprocmemo();
		
		wxMenuprocmemo.setId(wxMenuprocmemoId);
		wxMenuprocmemo.setProcDate(new Date());
		wxMenuprocmemo.setProcMemo("");
		wxMenuprocmemo.setProcType("QUERY");
		wxMenuprocmemo.setProcUser(employeeId);
		wxMenuprocmemoDao.save(wxMenuprocmemo);

		
		if(customizeMenuBean != null){
			
			
			
			logger.info("查询成功,将原有数据库菜单数据删除....");
			wxCustomizemenuDao.deleteAllData(bankorgId);
			
			logger.info("将菜单数据保存进数据库");
			List<WxCustomizemenu> menus = CustomizeMenuUtil.changeBeanToEntity(customizeMenuBean);
			wxCustomizemenuDao.saveBatch(menus);
			
		}else{
			throw new BusinessLogicException("0011", "");
		}
		
		String menuStr = JsonUtil.parseBeanToJson(customizeMenuBean);
		logger.info("菜单数据为:"+menuStr);
		return menuStr;
	}

	@Override
	public void deleteMenu() throws Exception {
		logger.info("发送删除请求给服务器....");
		
		ReponseMsgBean reponseMsgBean = CustomizeMenuProcess.deleteMenu();
		
		String errorCode = reponseMsgBean.getErrcode();
		String errorDesc = ErrorCodeFactory.getInstance().getErrorDesc(errorCode);
		
		logger.info("请求响应码:"+errorCode+",描述:"+errorDesc);
		
		long bankorgId = SystemUtil.getCurrBankorgId();
		String employeeId = SystemUtil.getCurrEmployee();
		
		logger.info("记录操作备忘..");
		
		WxMenuprocmemoId wxMenuprocmemoId = new WxMenuprocmemoId();
		wxMenuprocmemoId.setBankorgId(bankorgId);
		wxMenuprocmemoId.setMemoSeq(SystemSeqFactory.getInstance().getSeq(SystemSeqFactory.SEQ_TYPE.MENU_PROC_SEQ));
		
		WxMenuprocmemo wxMenuprocmemo = new WxMenuprocmemo();
		wxMenuprocmemo.setErrorCode(errorCode);
		wxMenuprocmemo.setErrorMsg(reponseMsgBean.getErrmsg());
		wxMenuprocmemo.setErrorMsgCn(errorDesc);
		wxMenuprocmemo.setId(wxMenuprocmemoId);
		wxMenuprocmemo.setProcDate(new Date());
		wxMenuprocmemo.setProcMemo("");
		wxMenuprocmemo.setProcType("DELETE");
		wxMenuprocmemo.setProcUser(employeeId);
		
		wxMenuprocmemoDao.save(wxMenuprocmemo);
		
		if(SUCCESS_FLAG.equals(errorCode)){
			
			
			logger.info("删除成功，将原有数据库菜单数据删除....");
			wxCustomizemenuDao.deleteAllData(bankorgId);
			
			
			
		}else{
			throw new BusinessLogicException("0010", errorCode,errorDesc);
		}
	}

	@Override
	public void updateMenu(String menuJson) throws Exception {
		logger.info("进行菜单更新操作，因为逻辑和创新相同，所以调用创建菜单功能...");
		createMenu(menuJson);
	}

}
