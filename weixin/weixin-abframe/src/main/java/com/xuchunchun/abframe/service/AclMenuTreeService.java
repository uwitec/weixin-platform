package com.xuchunchun.abframe.service;

import java.util.List;

import com.xuchunchun.abframe.bean.AclTopMenuTreeForm;
import com.xuchunchun.abframe.bean.ListMenuTreeBase;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclUsershortcut;
import com.xuchunchun.abframe.web.vo.AclMenutreeVo;


public interface AclMenuTreeService {

	//用户的menu
	List<ListMenuTreeBase> userListTreeForm(String userId);
	//所有的menu
	List<ListMenuTreeBase> allListTreeForm();
	List<AclMenutree> getlist(AclMenutree aclMenutree);
	public List<AclMenutreeVo> getlistVo(AclMenutree aclMenutree);
	
	AclMenutree detail(AclMenutree aclMenutree);
	void add(AclMenutree aclMenutree);
	void update(AclMenutree aclMenutree);
	List<ListMenuTreeBase> parentListMenuTreeForm();
	List<ListMenuTreeBase> roleListTreeForm(String roleId);
	void delete(AclMenutree aclMenutree);
	List<AclTopMenuTreeForm> topMenuTreeForms(String userId);
	/**
	 * 查询快捷方式
	 * 修改日期：2012-9-7
	 * @author: tpf
	 * @param roleId
	 * @return
	 */
	List<ListMenuTreeBase> userShortcutListTreeForm(String userId);
	/**
	 * 更新快捷方式
	 * 修改日期：2012-9-7
	 * @author: tpf
	 * @param aclRoleshortcutList
	 */
	void saveAclUsershortcut(List<AclUsershortcut> aclUsershortcutList);
	/**
	 * 查询该用户的快捷方式
	 * 修改日期：2012-9-7
	 * @author: tpf
	 * @param userId
	 * @return
	 */
	List<AclFunction> getFunctionByUser(String userId);
}
