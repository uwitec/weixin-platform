package com.xuchunchun.abframe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclLoginStrategyDao;
import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.db.entity.AclLoginstrategyId;
import com.xuchunchun.abframe.service.AclLoginstrategyService;
import com.xuchunchun.abframe.web.vo.AclLoginstrategyVo;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclLoginstrategyService")
public class AclLoginstrategyServiceImpl implements AclLoginstrategyService{
	@Autowired
	private AclLoginStrategyDao aclLoginStrategyDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();
	

	@Override
	public void add(AclLoginstrategy aclLoginstrategy) {
		aclLoginstrategy.getId().setBankorgId(SystemUtil.getCurrBankorgId());
        this.aclLoginStrategyDao.save(aclLoginstrategy);	
	}

	@Override
	public void delete(List<AclLoginstrategy> aclLoginstrategys) {
        this.aclLoginStrategyDao.deleteAll(aclLoginstrategys);
	}
	
	@Override
	public void update(AclLoginstrategy aclLoginstrategy) {
       this.aclLoginStrategyDao.update(aclLoginstrategy);		
	}

	@Override
	public AclLoginstrategy detail(AclLoginstrategy aclLoginstrategy) {
		return this.aclLoginStrategyDao.findByPk(aclLoginstrategy.getId());
	}

	@Override
	public Long queryCount(AclLoginstrategy aclLoginstrategy,
			Map<String, String> clause) {
		return this.aclLoginStrategyDao.queryCount(aclLoginstrategy, clause);
	}

	@Override
	public List<AclLoginstrategy> querylist(AclLoginstrategy aclLoginstrategy,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.aclLoginStrategyDao.findByTemplateWithPage(aclLoginstrategy, clause, pageNo, pageSize);
	}
	
	@Override
	public List<AclLoginstrategyVo> querylistVo(AclLoginstrategy aclLoginstrategy,
			Map<String, String> clause, int pageNo, int pageSize){
		List<AclLoginstrategy> aclLoginstrategys = this.aclLoginStrategyDao.findByTemplateWithPage(aclLoginstrategy, clause, pageNo, pageSize);
		List<AclLoginstrategyVo> aclLoginstrategyVos = new ArrayList<AclLoginstrategyVo>();
		
		if(aclLoginstrategys != null && aclLoginstrategys.size() > 0){
			for(AclLoginstrategy acl : aclLoginstrategys){
				AclLoginstrategyVo aclVo = new AclLoginstrategyVo();
				aclVo.setId(acl.getId());
				aclVo.setStrategyName(acl.getStrategyName());
				aclVo.setMacCheck("Y".equals(acl.getMacCheck())?"是":"否");
				aclVo.setIpCheck("Y".equals(acl.getIpCheck())?"是":"否");
				aclVo.setListCheck("Y".equals(acl.getListCheck())?"是":"否");
				if("W".equals(acl.getListType())){
					aclVo.setListType("白名单");
				}else if("B".equals(acl.getListType())){
					aclVo.setListType("黑名单");
				}
				aclLoginstrategyVos.add(aclVo);
			}
		}
		return aclLoginstrategyVos;
	}


}
