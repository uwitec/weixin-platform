package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclExcludeDao;
import com.xuchunchun.abframe.db.entity.AclExclude;
import com.xuchunchun.abframe.service.AclExcludeService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclExcludeService")
public class AclExcludeServiceImpl implements AclExcludeService{
	@Autowired
	private AclExcludeDao aclExcludeDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();


	

	@Override
	public AclExclude detail(AclExclude aclExclude){
		return this.aclExcludeDao.findByPk(aclExclude.getId());
	}
	
	@Override
	public void delete(List<AclExclude> aclExcludes){
		this.aclExcludeDao.deleteAll(aclExcludes);
	}
	
	@Override
	public void update(AclExclude aclExclude){
		this.aclExcludeDao.update(aclExclude);
	}
	
	@Override
	public void add(AclExclude aclExclude){
		aclExclude.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		this.aclExcludeDao.save(aclExclude);
	}
	
	@Override
	public List<AclExclude> querylist(AclExclude aclExclude, Map<String,String> clause,int pageNo, int pageSize){
		return this.aclExcludeDao.findByTemplateWithPage(aclExclude, clause, pageNo, pageSize);
	}
	
	@Override
	public Long queryCount(AclExclude aclExclude, Map<String,String> clause){
		return this.aclExcludeDao.queryCount(aclExclude, clause);
	}
}
