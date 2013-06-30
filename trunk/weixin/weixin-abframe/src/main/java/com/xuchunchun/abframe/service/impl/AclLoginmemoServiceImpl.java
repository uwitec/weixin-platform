package com.xuchunchun.abframe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.dao.AclLoginMemoDao;
import com.xuchunchun.abframe.db.entity.AclLoginmemo;
import com.xuchunchun.abframe.service.AclLoginmemoService;

import com.xuchunchun.base.annotation.Business;

@Business("aclLoginmemoService")
public class AclLoginmemoServiceImpl implements AclLoginmemoService {
	@Autowired
	private AclLoginMemoDao aclLoginMemoDao;

	@Override
	public AclLoginmemo detail(AclLoginmemo aclLoginmemo) {
		return this.aclLoginMemoDao.findByPk(aclLoginmemo.getId());
	}

	@Override
	public void delete(AclLoginmemo aclLoginmemo) {
		this.aclLoginMemoDao.delete(aclLoginmemo);
	}

	@Override
	public void update(AclLoginmemo aclLoginmem) {
		this.aclLoginMemoDao.update(aclLoginmem);
	}

	@Override
	public void add(AclLoginmemo aclLoginmem) {
		this.aclLoginMemoDao.save(aclLoginmem);
	}

	@Override
	public List<AclLoginmemo> querylist(AclLoginmemo aclLoginmem,
			Map<String, String> clause, int pageNo, int pageSize) {
		return this.aclLoginMemoDao.findByTemplateWithPage(aclLoginmem, clause,
				pageNo, pageSize);
	}

	@Override
	public Long queryCount(AclLoginmemo aclLoginmem, Map<String, String> clause) {
		return this.aclLoginMemoDao.queryCount(aclLoginmem, clause);
	}
}
