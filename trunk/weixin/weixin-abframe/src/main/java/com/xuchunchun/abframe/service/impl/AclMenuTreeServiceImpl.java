package com.xuchunchun.abframe.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.bean.AclTopMenuTreeForm;
import com.xuchunchun.abframe.bean.ListMenuTreeBase;
import com.xuchunchun.abframe.bean.ListMenuTreeForm;
import com.xuchunchun.abframe.bean.ListMenuTreeLeafForm;
import com.xuchunchun.abframe.common.AclConstants;
import com.xuchunchun.abframe.db.dao.AclChildSystemDao;
import com.xuchunchun.abframe.db.dao.AclFunctionDao;
import com.xuchunchun.abframe.db.dao.AclMenuTreeDao;
import com.xuchunchun.abframe.db.dao.AclRoleDao;
import com.xuchunchun.abframe.db.dao.AclRoleMenuDao;
import com.xuchunchun.abframe.db.dao.AclUserDao;
import com.xuchunchun.abframe.db.dao.AclUserRoleDao;
import com.xuchunchun.abframe.db.dao.AclUserShortcutDao;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclFunctionId;
import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclMenutreeId;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRoleId;
import com.xuchunchun.abframe.db.entity.AclRolemenu;
import com.xuchunchun.abframe.db.entity.AclRolemenuId;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.db.entity.AclUserroleId;
import com.xuchunchun.abframe.db.entity.AclUsershortcut;
import com.xuchunchun.abframe.db.entity.AclUsershortcutId;
import com.xuchunchun.abframe.service.AclMenuTreeService;
import com.xuchunchun.abframe.util.compare.AclMenutreeCompare;
import com.xuchunchun.abframe.web.vo.AclMenutreeVo;

import net.sf.json.JSONArray;
import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.StrUtil;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclMenuTreeService")
public class AclMenuTreeServiceImpl implements AclMenuTreeService{
	@Autowired
	private AclMenuTreeDao aclMenuTreeDao;
	@Autowired
	private AclFunctionDao aclFunctionDao;
	@Autowired
	private AclRoleMenuDao aclRoleMenuDao;
	@Autowired
	private AclChildSystemDao aclChildSystemDao;
	private List<AclMenutree> treeList;  //未确定的menu
	private List<AclMenutree> treeListCheck;//设置选中的menu，对应isChecked
	@Autowired
	private AclUserDao aclUserDao;
	@Autowired
	private AclUserRoleDao aclUserRoleDao;
	@Autowired
	private AclUserShortcutDao aclUserShortcutDao;
	@Autowired
	private AclRoleDao aclRoleDao;
	boolean isChecked = false; //是否对check处理
	//private Long bankorgId = SystemUtil.getDefaultBankorgId();
	
	private final String urlQueryPrefix = "?child_userId="; //查询字符串前缀
	private String urlQuery; //查询字符串，为了支持多系统，在系统的左边的URL上，添加用户的userId
	
	@Override
	public List<AclMenutree> getlist(AclMenutree aclMenutree){
		return aclMenuTreeDao.findByTemplate(aclMenutree);
	}
	
	@Override 
	public List<AclMenutreeVo> getlistVo(AclMenutree aclMenutree){
		List<AclMenutree> aclMenutrees = aclMenuTreeDao.findByTemplate(aclMenutree);
		List<AclMenutreeVo> aclMenutreeVos = new ArrayList<AclMenutreeVo>();
		if(aclMenutrees != null && aclMenutrees.size() > 0){
			for(AclMenutree acl : aclMenutrees){
				AclMenutreeId aclId = new AclMenutreeId();
				aclId.setBankorgId(acl.getId().getBankorgId());
				aclId.setNodeId(acl.getId().getNodeId());
				
				AclMenutreeVo aclVo = new AclMenutreeVo();
				aclVo.setId(aclId);
				aclVo.setNodeSeq(acl.getNodeSeq());
				aclVo.setNodeName(acl.getNodeName());
				String parentId = acl.getParentId();
				String parentName = "";
				if("0".equals(parentId)){
					parentName = "根节点";
				}else{
					List<AclMenutree> aclMenus = aclMenuTreeDao.findAll();
					if(aclMenus != null && aclMenus.size() > 0){
						for(AclMenutree acls : aclMenus){
							if(acl.getParentId().equals(acls.getId().getNodeId())){
								parentName = acls.getNodeName();
							}
						}
					}
				}
				aclVo.setParentId(parentName);
				aclVo.setIsLeaf("Y".equals(acl.getIsLeaf())?"是":"否");
				aclVo.setFunctionId(acl.getFunctionId());
				aclVo.setMenuType(acl.getMenuType());
				
				aclMenutreeVos.add(aclVo);
			}
		}
		return aclMenutreeVos;
	}
	
	@Override
	public AclMenutree detail(AclMenutree aclMenutree){
		AclMenutreeId aclMenutreeId = new AclMenutreeId(SystemUtil.getCurrBankorgId(),aclMenutree.getId().getNodeId());
		return aclMenuTreeDao.findByPk(aclMenutreeId);
	}
	
	@Override
	public void add(AclMenutree aclMenutree){
		aclMenutree.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclMenuTreeDao.save(aclMenutree);
	}
	
	@Override
	public void update(AclMenutree aclMenutree){
		aclMenuTreeDao.update(aclMenutree);
	}
	
	@Override
	public void delete(AclMenutree aclMenutree){
		//删除菜单要把对应的角色菜单记录删掉
		AclRolemenuId aclRolemenuId = new AclRolemenuId();
		aclRolemenuId.setBankorgId(SystemUtil.getCurrBankorgId());
		aclRolemenuId.setNodeId(aclMenutree.getId().getNodeId());
		AclRolemenu aclRolemenu = new AclRolemenu(aclRolemenuId);
		List<AclRolemenu> listAclRolemenus = aclRoleMenuDao.findByTemplate(aclRolemenu);
		aclRoleMenuDao.deleteAll(listAclRolemenus);
		
		//如果删除的不是叶子节点，还要把下面它的子节点删除
		AclMenutreeId aclMenutreeId = aclMenutree.getId();
		aclMenutreeId.setBankorgId(SystemUtil.getCurrBankorgId());
		aclMenutree = aclMenuTreeDao.findByPk(aclMenutreeId);
		if("N".equalsIgnoreCase(aclMenutree.getIsLeaf())){
			AclMenutree aclMenutreeTmp = new AclMenutree();
			aclMenutreeTmp.setParentId(aclMenutree.getId().getNodeId());
			List<AclMenutree> listAclMenutrees = aclMenuTreeDao.findByTemplate(aclMenutreeTmp);
			aclMenuTreeDao.deleteAll(listAclMenutrees);
		}
		
		aclMenuTreeDao.delete(aclMenutree);
	}
	
	//获得用户的顶层菜单（顶层菜单包含了子菜单）
	@Override
	public List<AclTopMenuTreeForm> topMenuTreeForms(String userId){
		
		AclUser aclUserGetEmployeeId = aclUserDao.findByPk(userId);
		this.urlQuery = this.urlQueryPrefix + userId + "&employeeId=" + aclUserGetEmployeeId.getEmployeeId();
		
		List<AclTopMenuTreeForm> formListTop = new ArrayList<AclTopMenuTreeForm>();
		
		String hql = "FROM AclMenutree WHERE " +
					  "id.nodeId IN (SELECT id.nodeId FROM AclRolemenu WHERE id.roldId IN " +
								"(SELECT id.roldId FROM AclUserrole WHERE id.userId ='"+ userId  +"'))";
		List<AclMenutree> treeListAll = aclMenuTreeDao.queryByHql(hql);
		
		//找到顶层节点的菜单
		String hqlTop = "FROM AclMenutree WHERE parentId in ('0','',null)";
		
		List<AclMenutree> treeListTop = aclMenuTreeDao.queryByHql(hqlTop); 
		Collections.sort(treeListTop,new AclMenutreeCompare());
//		for(int i=0 ;i<treeListAll.size(); i++){
//			 AclMenutree aclMenutree = treeListAll.get(i); 
//			if("0".equalsIgnoreCase(aclMenutree.getParentId())
//					||"".equalsIgnoreCase(aclMenutree.getParentId())
//						||aclMenutree.getParentId()==null){
//				treeListTop.add(aclMenutree);
//			}
//		}
		
		for(AclMenutree topMenu : treeListTop){
			treeList = new ArrayList<AclMenutree>() ;
			
			for(AclMenutree menutree : treeListAll){
				if(menutree.getParentId().equalsIgnoreCase(topMenu.getId().getNodeId())){
					treeList.add(menutree);
				}
			}
			
			//TODO，这里需要改,要改开始
			List<AclMenutree> treeListTop2 = new ArrayList<AclMenutree>() ;
			for(AclMenutree top2Menu:treeList){
				for(AclMenutree menutree : treeListAll){
					if(menutree.getParentId().equalsIgnoreCase(top2Menu.getId().getNodeId())){
						treeListTop2.add(menutree);
					}
				}
			}
			List<AclMenutree> treeListTop3 = new ArrayList<AclMenutree>() ;
			for(AclMenutree top3Menu:treeListTop2){
				for(AclMenutree menutree : treeListAll){
					if(menutree.getParentId().equalsIgnoreCase(top3Menu.getId().getNodeId())){
						treeListTop3.add(menutree);
					}
				}
			}
			
			List<AclMenutree> treeListTop4 = new ArrayList<AclMenutree>() ;
			for(AclMenutree top4Menu:treeListTop3){
				for(AclMenutree menutree : treeListAll){
					if(menutree.getParentId().equalsIgnoreCase(top4Menu.getId().getNodeId())){
						treeListTop4.add(menutree);
					}
				}
			}
				
			treeList.addAll(treeListTop2);
			treeList.addAll(treeListTop3);
			treeList.addAll(treeListTop4);
			//TODO，要改结束
			
			treeList.add(topMenu);
			
			String childrenString = parseJSONList(transformMenu()).toString();
			
			AclTopMenuTreeForm aclTopMenuTreeForm = new AclTopMenuTreeForm();
			aclTopMenuTreeForm.setId(topMenu.getId().getNodeId());
			aclTopMenuTreeForm.setTitle(topMenu.getNodeName());
			aclTopMenuTreeForm.setChildrenString(childrenString);
			
			//如果size=1，是只有顶层的，没有子节点，忽略
			if(treeList.size()>1){
				formListTop.add(aclTopMenuTreeForm);
			}
			
		}
		return formListTop;
	}
	
	@Override
	public List<ListMenuTreeBase>  userListTreeForm(String userId){
	/*	查找用户角色的sql
		SELECT *
		  FROM ACL_MENUTREE
		 WHERE NODE_ID IN
		       (SELECT NODE_ID
		          FROM ACL_ROLEMENU
		         WHERE ROLD_ID IN
		               (SELECT ROLD_ID FROM ACL_USERROLE WHERE USER_ID = ?));
	*/	
		String hql = "FROM AclMenutree WHERE id.nodeId IN (SELECT id.nodeId FROM AclRolemenu WHERE id.roldId IN (SELECT id.roldId FROM AclUserrole WHERE id.userId ='"+ userId  +"'))";
		treeList = aclMenuTreeDao.queryByHql(hql);
		
		return transformMenu();
	}
	@Override
	public List<ListMenuTreeBase> allListTreeForm() {
		String hql = "FROM AclMenutree";
		treeList = aclMenuTreeDao.queryByHql(hql);
		return transformMenu();
	}
	//角色的菜单
	@Override
	public List<ListMenuTreeBase> roleListTreeForm(String roleId){
		
		String hql = "FROM AclMenutree WHERE id.nodeId IN (SELECT id.nodeId FROM AclRolemenu WHERE id.roldId ='"+ roleId  +"')";
		treeListCheck = aclMenuTreeDao.queryByHql(hql); 
		
		//tpf加（为了满足业务视图和科技视图）
		AclRole roleT = aclRoleDao.findByPk(new AclRoleId(SystemUtil.getCurrBankorgId(), roleId));
		
		String hqlall = "FROM AclMenutree";
		if("T".equals(roleT.getRoleType()) || "B".equals(roleT.getRoleType())) {
			hqlall = "FROM AclMenutree a WHERE a.menuType='"+roleT.getRoleType()+"' or a.menuType='M' or a.menuType is null";
		}
		treeList = aclMenuTreeDao.queryByHql(hqlall);
		isChecked = true;
		return transformMenu();
	}
	
	@Override
	public List<ListMenuTreeBase> userShortcutListTreeForm(String userId) {
		List<AclUserrole> aclUserroles = aclUserRoleDao.findByTemplate(new AclUserrole(new AclUserroleId(userId, null)));
		treeListCheck = new ArrayList<AclMenutree>();
		treeList = new ArrayList<AclMenutree>();
		for (AclUserrole aclUserrole : aclUserroles) {
			
			String hql = "FROM AclMenutree WHERE id.nodeId IN (SELECT id.nodeId FROM AclUsershortcut WHERE id.userId ='"+ aclUserrole.getId().getUserId()  +"')";
			List list1 = aclMenuTreeDao.queryByHql(hql);
			if(list1 != null && !list1.isEmpty()) {
				treeListCheck.addAll(list1);
			}
			
			String hqlall = "FROM AclMenutree WHERE id.nodeId IN (SELECT id.nodeId FROM AclRolemenu WHERE id.roldId ='"+ aclUserrole.getId().getRoldId()  +"')";
			List list2 = aclMenuTreeDao.queryByHql(hqlall);
			
			if(list2 != null && !list2.isEmpty()) {
				treeList.addAll(list2);
			}
		}
		isChecked = true;
		return transformMenu();
	}
	
	@Override
	public void saveAclUsershortcut(List<AclUsershortcut> aclUsershortcutList) {
		String userId = null;
		if(aclUsershortcutList != null && !aclUsershortcutList.isEmpty()) {
			userId = aclUsershortcutList.get(0).getId().getUserId();
		}
		List<AclUsershortcut> userShortcuts = aclUserShortcutDao.findByTemplate(new AclUsershortcut(new AclUsershortcutId(SystemUtil.getCurrBankorgId(), userId, null)));
		aclUserShortcutDao.deleteAll(userShortcuts);
		for (AclUsershortcut aclUsershortcut : aclUsershortcutList) {
			aclUsershortcut.getId().setBankorgId(SystemUtil.getCurrBankorgId());
			aclUserShortcutDao.save(aclUsershortcut);
		}
	}
	
	@Override
	public List<AclFunction> getFunctionByUser(String userId) {
		List<AclFunction> functions = new ArrayList<AclFunction>();
		List<AclUsershortcut> userShortcuts = aclUserShortcutDao.findByTemplate(new AclUsershortcut(new AclUsershortcutId(SystemUtil.getCurrBankorgId(), userId, null)));
		List<AclMenutree> menutrees = new ArrayList<AclMenutree>();
		for (AclUsershortcut aclUsershortcut : userShortcuts) {
			AclMenutree mtree = aclMenuTreeDao.findByPk(new AclMenutreeId(SystemUtil.getCurrBankorgId(), aclUsershortcut.getId().getNodeId()));
			if(mtree != null) {
				menutrees.add(mtree);
			}
		}
		for (AclMenutree aclMenutree : menutrees) {
			if(StrUtil.isEmpty(aclMenutree.getFunctionId())) {
				continue;
			}
			AclFunction function = aclFunctionDao.findByPk(new AclFunctionId(SystemUtil.getCurrBankorgId(), aclMenutree.getFunctionId()));
			if(function != null) {
				functions.add(function);
			}
		}
		return functions;
	}
	
	@Override
	public List<ListMenuTreeBase> parentListMenuTreeForm(){
		String hql = "FROM AclMenutree WHERE isLeaf<>'Y'";
		treeList = aclMenuTreeDao.queryByHql(hql);
		return transformMenu();
	}
	
	//转换成页面可解析的list
	private List<ListMenuTreeBase>  transformMenu(){
		List<ListMenuTreeBase> lisTreeForm = new ArrayList<ListMenuTreeBase>() ;
		List<AclMenutree> treeListTop = new ArrayList<AclMenutree>(); 
		//找到顶层节点的菜单
		for(int i=0 ;i<treeList.size(); i++){
			 AclMenutree aclMenutree = treeList.get(i); 
			if("0".equalsIgnoreCase(aclMenutree.getParentId())
					||"".equalsIgnoreCase(aclMenutree.getParentId())
						||aclMenutree.getParentId()==null){
				treeListTop.add(aclMenutree);
			}
		}
		
		for(AclMenutree menu : treeListTop){
			lisTreeForm.add(assign(menu));
		}
		Collections.sort(lisTreeForm,new ListMenuTreeBase());
		return lisTreeForm;
	}
	
	private ListMenuTreeBase assign(AclMenutree menu){
		String nodeId = menu.getId().getNodeId();
		ListMenuTreeBase listMenuTreeBase ;
		
		if("Y".equalsIgnoreCase(menu.getIsLeaf())){
			listMenuTreeBase = new ListMenuTreeLeafForm();
			
			//如果在treeListCheck里，设为checked的
			if(isChecked){
				if(treeListCheck.contains(menu)){
					listMenuTreeBase.setIschecked(true);
				}
			}
			
			//treeList.remove(menu);
		}else{
			ListMenuTreeForm listMenuTreeForm = new ListMenuTreeForm();
			List<ListMenuTreeBase> children = new ArrayList<ListMenuTreeBase>();
			for(AclMenutree treeMenu:treeList){
				if(nodeId.equalsIgnoreCase(treeMenu.getParentId())){
					ListMenuTreeBase treeForm = assign(treeMenu);
					children.add(treeForm);
					//treeList.remove(treeMenu);
				}
			}
			Collections.sort(children,new ListMenuTreeLeafForm());
			listMenuTreeForm.setChildren(children);
			listMenuTreeBase = listMenuTreeForm;
			
			//如果在treeListCheck里，设为checked的
			if(isChecked){
				if(treeListCheck.contains(menu)){
					listMenuTreeBase.setIschecked(true);
				}
			}
		}
		
		listMenuTreeBase.setText(menu.getNodeName());
		listMenuTreeBase.setNodeId(menu.getId().getNodeId());
		if(menu.getFunctionId()!=null && !"".equals(menu.getFunctionId())){
			AclFunction aclFunction = aclFunctionDao.findByPk(new AclFunctionId(menu.getId().getBankorgId(), menu.getFunctionId()));
			if(aclFunction.getFuncUrl()!=null && !"".equals(aclFunction.getFuncUrl())){
				//子系统配置URL的例子  http://localhost:10080
				String urlString = aclFunction.getFuncUrl();
//				if(aclFunction.getSystemId()!=null&&aclFunction.getSystemId()!=""){
//					AclChildsystemId aclChildsystemId = new AclChildsystemId();
//					aclChildsystemId.setBankorgId(bankorgId);
//					aclChildsystemId.setSystemId(aclFunction.getSystemId());
//					AclChildsystem aclChildsystem = aclChildSystemDao.findByPk(aclChildsystemId);
//					if(aclChildsystem.getSysUrl()!=null&&aclChildsystem.getSysUrl()!=""){
//						//TODO 标记
//						urlString = aclChildsystem.getSysUrl()+ urlString ; // + urlQuery; //服务器地址+数据库配置的action+用户的查询字符串
//					}
//				}
				urlString = AclConstants.CURRENT_SYSTEM_NAME+"/"+urlString;
				listMenuTreeBase.setUrl(urlString);
			}
		}
		
		listMenuTreeBase.setNodeSeq(menu.getNodeSeq());
		
		return listMenuTreeBase;
	}
	
	
	//将list包装成JSON数组
	public String parseJSONList(List list){
		JSONArray jsonArray = new JSONArray();
		for(int i=0;i<list.size();i++){
			jsonArray.add(i,list.get(i));
		}
		return jsonArray.toString();
	}
}
