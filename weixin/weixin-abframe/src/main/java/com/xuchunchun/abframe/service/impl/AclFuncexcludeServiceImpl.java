package com.xuchunchun.abframe.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclFuncExcludeDao;
import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.service.AclFuncexcludeService;
import com.xuchunchun.abframe.web.vo.AclFuncexcludeVo;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclFuncexcludeService")
public class AclFuncexcludeServiceImpl implements AclFuncexcludeService {
	@Autowired
	private AclFuncExcludeDao aclFuncExcludeDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();

	@Override
	public AclFuncexclude detail(AclFuncexclude aclFuncexclude) {
		return this.aclFuncExcludeDao.findByPk(aclFuncexclude.getId());
	}

	@Override
	public void delete(List<AclFuncexclude> aclFuncexcludes) {
		this.aclFuncExcludeDao.deleteAll(aclFuncexcludes);
	}

	@Override
	public void update(AclFuncexclude aclFuncexclude) {
		this.aclFuncExcludeDao.update(aclFuncexclude);
	}

	@Override
	public void add(AclFuncexclude aclFuncexclude) {
		aclFuncexclude.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		this.aclFuncExcludeDao.save(aclFuncexclude);
	}

	@Override
	public List<AclFuncexclude> querylist(AclFuncexclude aclFuncexclude,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.aclFuncExcludeDao.findByTemplateWithPage(aclFuncexclude,
				clause, pageNo, pageSize);
	}
	
	@Override
	public List<AclFuncexcludeVo> querylistVo(AclFuncexclude aclFuncexclude,
			Map<String, String> clause, int pageNo, int pageSize){
		List<AclFuncexclude> aclFuncexcludes = this.aclFuncExcludeDao.findByTemplateWithPage(aclFuncexclude,
				clause, pageNo, pageSize);
		List<AclFuncexcludeVo> aclFuncexcludeVos = new ArrayList<AclFuncexcludeVo>();
		if(aclFuncexcludes != null && aclFuncexcludes.size() > 0){
			for(AclFuncexclude acl : aclFuncexcludes){
				AclFuncexcludeVo aclVo = new AclFuncexcludeVo();
				aclVo.setId(acl.getId());
				aclVo.setExcludeType("A".equals(acl.getExcludeType())?"显示":"不显示");
				aclFuncexcludeVos.add(aclVo);
			}
		}
		return aclFuncexcludeVos;
	}

	@Override
	public Long queryCount(AclFuncexclude aclFuncexclude,
			Map<String, String> clause) {
		return this.aclFuncExcludeDao.queryCount(aclFuncexclude, clause);
	}
}
