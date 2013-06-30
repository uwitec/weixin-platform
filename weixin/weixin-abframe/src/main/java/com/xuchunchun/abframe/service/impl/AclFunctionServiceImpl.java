package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclFunctionDao;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclFunctionId;
import com.xuchunchun.abframe.service.AclFunctionService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclFunctionService")
public class AclFunctionServiceImpl implements AclFunctionService{
	@Autowired
	private AclFunctionDao aclFunctionDao;
	
	//private Long bankorgId = SystemUtil.getDefaultBankorgId();
	
	@Override
	public List<AclFunction> listFunctions(AclFunction aclFunction,Map<String,String> clause,int pageNo, int pageSize){
		return aclFunctionDao.findByTemplateWithPage(aclFunction, clause, pageNo,  pageSize);
	}
	@Override
	public Long totalFunction(AclFunction aclFunction,Map<String,String> clause){
		return aclFunctionDao.queryCount(aclFunction, clause);
	}
	@Override
	public AclFunction detaiAclFunction(AclFunction aclFunction){
		AclFunctionId aclFunctionId = new AclFunctionId(SystemUtil.getCurrBankorgId(),aclFunction.getId().getFunctionId());
		return aclFunctionDao.findByPk(aclFunctionId);
	}
	@Override
	public void updateAclfunction(AclFunction aclFunction){
		aclFunctionDao.update(aclFunction);
	}
	@Override
	public void deleteAclfunction(List<AclFunction> aclFunctions){
		for(AclFunction aclFunctionTmp : aclFunctions){
			aclFunctionTmp.getId().setBankorgId(SystemUtil.getCurrBankorgId());
			aclFunctionDao.delete(aclFunctionTmp);
		}
//		aclFunctionDao.deleteAll(aclFunctions);
	}
	@Override
	public void addAclfunction(AclFunction aclFunction){
		aclFunction.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclFunctionDao.save(aclFunction);
	}
}
