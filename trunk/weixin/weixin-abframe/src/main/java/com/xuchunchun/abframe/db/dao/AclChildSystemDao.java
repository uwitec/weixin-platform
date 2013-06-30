/*
 * Copyright 2012-2020 the original author tpf.
 *
 */

package com.xuchunchun.abframe.db.dao;

import java.util.List;

import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.abframe.db.entity.AclChildsystemId;

import com.xuchunchun.base.dao.DefaultDao;

/**
 * @author tpf
 * 
 */
public interface AclChildSystemDao extends DefaultDao<AclChildsystem> {
	
	public AclChildsystem findByPk(AclChildsystemId id);
	
	public List<AclChildsystem> findAll();

	List<AclChildsystem> getChildsystemsByUser(String userId);
}
