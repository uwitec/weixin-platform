package com.xuchunchun.abframe.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;


import com.xuchunchun.abframe.db.dao.AclSysParameterDao;
import com.xuchunchun.abframe.db.entity.AclSysparameter;
import com.xuchunchun.abframe.service.AclSysParameterService;
import com.xuchunchun.abframe.service.DefaultQueryService;
import com.xuchunchun.abframe.web.vo.AclSysparameterVo;

import com.xuchunchun.base.annotation.Business;

@Business("aclSysParameterService")
public class AclSysParameterServiceImpl implements AclSysParameterService{
	
	@Autowired
	private AclSysParameterDao aclSysParameterDao;

	
	@Override
	public AclSysparameter detail(AclSysparameter aclSysparameter){
		return this.aclSysParameterDao.findByPk(aclSysparameter.getBankorgId());
	}
	
	@Override
	public void delete(List<AclSysparameter> listSysParameters){
		this.aclSysParameterDao.deleteAll(listSysParameters);
	}
	
	@Override
	public void update(AclSysparameter aclSysparameter){
		this.aclSysParameterDao.update(aclSysparameter);
	}
	
	@Override
	public void add(AclSysparameter aclSysparameter){
		this.aclSysParameterDao.save(aclSysparameter);
	}
	
	@Override
	public List<AclSysparameter> querylist(AclSysparameter aclSysparameter, Map<String,String> clause,int pageNo, int pageSize){
		return this.aclSysParameterDao.findByTemplateWithPage(aclSysparameter, clause, pageNo, pageSize);
	}
	
	@Override
	public List<AclSysparameterVo> querylistVo(AclSysparameter aclSysparameter,
			Map<String, String> clause, int pageNo, int pageSize){
		List<AclSysparameter> aclSysparameters = this.aclSysParameterDao.findByTemplateWithPage(aclSysparameter, clause, pageNo, pageSize);
		List<AclSysparameterVo> aclSysparameterVos = new ArrayList<AclSysparameterVo>();
		
		if(aclSysparameters != null && aclSysparameters.size() != 0){
			for(AclSysparameter acl : aclSysparameters){
				AclSysparameterVo aclVo = new AclSysparameterVo();
				aclVo.setBankorgId(acl.getBankorgId());
				aclVo.setFstPwdChange("Y".equals(acl.getFstPwdChange())?"是":"否");
				aclVo.setChgPwdOldChk("Y".equals(acl.getChgPwdOldChk())?"是":"否");
				aclVo.setMaxErrCount(acl.getMaxErrCount());
				aclVo.setOldChangeCounts(acl.getOldChangeCounts());
				aclSysparameterVos.add(aclVo);
			}
		}
		return aclSysparameterVos;
	}
	
	@Override
	public Long queryCount(AclSysparameter aclSysparameter, Map<String,String> clause){
		return this.aclSysParameterDao.queryCount(aclSysparameter, clause);
	}
	
	
	@Override
	public AclSysparameter getSystemParam(long bankorgId) {
		AclSysparameter systemParameter = aclSysParameterDao.findByPk(bankorgId);
		return systemParameter;
	}
	
	@Override
	public void updateSysDate() {
		List<AclSysparameter> sysParameters = aclSysParameterDao.findAll();
		for (AclSysparameter systemparameter : sysParameters) {
			systemparameter.setLastBusinessDate(systemparameter.getCurrBusinessDate());
			systemparameter.setCurrBusinessDate(DateUtils.addDays(systemparameter.getCurrBusinessDate(), 1));
			systemparameter.setNextBusinessDate(DateUtils.addDays(systemparameter.getNextBusinessDate(), 1));
		}
	}
}
