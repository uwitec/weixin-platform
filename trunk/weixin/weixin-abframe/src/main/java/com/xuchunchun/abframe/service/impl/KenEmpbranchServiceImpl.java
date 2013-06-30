package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.KenEmpBranchDao;
import com.xuchunchun.abframe.db.entity.KenEmpbranch;
import com.xuchunchun.abframe.service.KenEmpbranchService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("kenEmpbranchService")
public class KenEmpbranchServiceImpl implements KenEmpbranchService {
	@Autowired
	private KenEmpBranchDao kenEmpBranchDao;
	//Long bankorgId = SystemUtil.getDefaultBankorgId();

	@Override
	public KenEmpbranch detail(KenEmpbranch kenEmpbranch) {
		return this.kenEmpBranchDao.findByPk(kenEmpbranch.getId());
	}

	@Override
	public void delete(List<KenEmpbranch> kenEmpbranchs) {
		this.kenEmpBranchDao.deleteAll(kenEmpbranchs);
	}

	@Override
	public void update(KenEmpbranch kenEmpbranch) {
		this.kenEmpBranchDao.update(kenEmpbranch);
	}

	@Override
	public void add(KenEmpbranch kenEmpbranch) {
		kenEmpbranch.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		this.kenEmpBranchDao.save(kenEmpbranch);
	}

	@Override
	public List<KenEmpbranch> querylist(KenEmpbranch kenEmpbranch,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.kenEmpBranchDao.findByTemplateWithPage(kenEmpbranch,
				clause, pageNo, pageSize);
	}

	@Override
	public Long queryCount(KenEmpbranch kenEmpbranch, Map<String, String> clause) {
		return this.kenEmpBranchDao.queryCount(kenEmpbranch, clause);
	}
}
