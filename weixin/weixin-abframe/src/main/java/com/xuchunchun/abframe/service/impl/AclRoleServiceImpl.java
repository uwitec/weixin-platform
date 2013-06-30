package com.xuchunchun.abframe.service.impl;

import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.bean.UserEmployeeForm;
import com.xuchunchun.abframe.db.dao.AclFunctionDao;
import com.xuchunchun.abframe.db.dao.AclMenuTreeDao;
import com.xuchunchun.abframe.db.dao.AclRoleDao;
import com.xuchunchun.abframe.db.dao.AclRoleFuncDao;
import com.xuchunchun.abframe.db.dao.AclRoleMenuDao;
import com.xuchunchun.abframe.db.dao.AclUserDao;
import com.xuchunchun.abframe.db.dao.AclUserRoleDao;
import com.xuchunchun.abframe.db.dao.KenEmployeeDao;
import com.xuchunchun.abframe.db.dao.impl.AclRoleMenuDaoImpl;
import com.xuchunchun.abframe.db.entity.AclFunction;
import com.xuchunchun.abframe.db.entity.AclFunctionId;
import com.xuchunchun.abframe.db.entity.AclMenutree;
import com.xuchunchun.abframe.db.entity.AclMenutreeId;
import com.xuchunchun.abframe.db.entity.AclRole;
import com.xuchunchun.abframe.db.entity.AclRoleId;
import com.xuchunchun.abframe.db.entity.AclRolefunc;
import com.xuchunchun.abframe.db.entity.AclRolefuncId;
import com.xuchunchun.abframe.db.entity.AclRolemenu;
import com.xuchunchun.abframe.db.entity.AclRolemenuId;
import com.xuchunchun.abframe.db.entity.AclUser;
import com.xuchunchun.abframe.db.entity.AclUserrole;
import com.xuchunchun.abframe.db.entity.AclUserroleId;
import com.xuchunchun.abframe.db.entity.KenEmployee;
import com.xuchunchun.abframe.db.entity.KenEmployeeId;
import com.xuchunchun.abframe.service.AclRoleService;

import com.xuchunchun.base.annotation.Business;
import com.xuchunchun.base.util.SystemUtil;

@Business("aclRoleService")
public class AclRoleServiceImpl implements AclRoleService{
	@Autowired
	private AclRoleDao aclRoleDao;
	@Autowired
	private AclRoleFuncDao aclRoleFuncDao;
	@Autowired
	private AclRoleMenuDao aclRoleMenuDao;
	@Autowired
	private AclFunctionDao aclFunctionDao;
	@Autowired
	private AclUserRoleDao aclUserRoleDao;
	@Autowired
	private AclUserDao aclUserDao;
	@Autowired
	private KenEmployeeDao kenEmployeeDao;
	@Autowired
	private AclMenuTreeDao aclMenuTreeDao;
	
	//private Long bankorgId = SystemUtil.getDefaultBankorgId();
	
	@Override
	public List<AclRole> listAclrole(AclRole aclRole, Map<String,String> clause,int pageNo, int pageSize){
		return aclRoleDao.findByTemplateWithPage(aclRole, clause, pageNo,  pageSize);
	}
	@Override
	public Long queryCount(AclRole aclRole, Map<String,String> clause){
		return aclRoleDao.queryCount(aclRole, clause);
	}
	
	@Override
	public void addAclrole(AclRole aclRole){
		aclRole.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclRoleDao.save(aclRole);
	}
	@Override
	public void updateAclrole(AclRole aclRole){
		aclRole.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclRoleDao.update(aclRole);
	}
	@Override
	public void deleteAclrole(AclRole aclRole){
		String roldId = aclRole.getId().getRoldId();
		//删除role，会把ACL_USERROLE,ACL_ROLEFUNC,ACL_ROLEMENU里相关的删除
		
		AclUserroleId aclUserroleId = new AclUserroleId();
		aclUserroleId.setRoldId(roldId);
		AclUserrole aclUserrole = new AclUserrole(aclUserroleId);
		List<AclUserrole> listUserroles = aclUserRoleDao.findByTemplate(aclUserrole);
		aclUserRoleDao.deleteAll(listUserroles);
		
		AclRolefuncId aclRolefuncId = new AclRolefuncId();
		aclRolefuncId.setBankorgId(SystemUtil.getCurrBankorgId());
		aclRolefuncId.setRoldId(roldId);
		AclRolefunc aclRolefunc = new AclRolefunc(aclRolefuncId);
		List<AclRolefunc> listAclRolefuncs = aclRoleFuncDao.findByTemplate(aclRolefunc);
		aclRoleFuncDao.deleteAll(listAclRolefuncs);
		
		AclRolemenuId aclRolemenuId = new AclRolemenuId();
		aclRolemenuId.setBankorgId(SystemUtil.getCurrBankorgId());
		aclRolemenuId.setRoldId(roldId);
		AclRolemenu aclRolemenu = new AclRolemenu(aclRolemenuId);
		List<AclRolemenu> listAclRolemenus = aclRoleMenuDao.findByTemplate(aclRolemenu);
		aclRoleMenuDao.deleteAll(listAclRolemenus);
		
		aclRole.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclRoleDao.delete(aclRole);
	}
	@Override
	public AclRole detail(AclRole aclRole){
		AclRoleId aclRoleId = new AclRoleId();
		aclRoleId.setBankorgId(SystemUtil.getCurrBankorgId());
		aclRoleId.setRoldId(aclRole.getId().getRoldId());
		return aclRoleDao.findByPk(aclRoleId);
	}
	
	//功能相关
	@Override
	public List<AclFunction> listFunctionAll(AclFunction aclFunction, Map<String,String> clause,int pageNo, int pageSize){
		return aclFunctionDao.findByTemplateWithPage(aclFunction, clause, pageNo,  pageSize);
	}
	@Override
	public Long countFunctionAll(AclFunction aclFunction, Map<String,String> clause){
		return aclFunctionDao.queryCount(aclFunction, clause);
	}
	//特定角色的功能
	@Override 
	public List<AclFunction> listAclrAclRolefuncs(AclRolefunc aclRolefunc, Map<String,String> clause,int pageNo, int pageSize){
		List<AclRolefunc> listRolefuncs = aclRoleFuncDao.findByTemplateWithPage(aclRolefunc, clause, pageNo, pageSize);
		List<AclFunction> listFuntions= new ArrayList<AclFunction>();
		for(AclRolefunc rolefuncTmp:listRolefuncs){
			AclFunctionId aclFunctionId = new AclFunctionId();
			aclFunctionId.setBankorgId(SystemUtil.getCurrBankorgId());
			aclFunctionId.setFunctionId(rolefuncTmp.getId().getFunctionId());
			listFuntions.add(aclFunctionDao.findByPk(aclFunctionId));
		}
		return listFuntions;
	}
	@Override
	public Long countAclrAclRolefuncs(AclRolefunc aclRolefunc, Map<String,String> clause){
		aclRolefunc.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		return aclRoleFuncDao.queryCount(aclRolefunc, clause);
	}
	@Override
	public void deleteAclRolefunc(AclRolefunc aclRolefunc){
		aclRolefunc.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclRoleFuncDao.delete(aclRolefunc);
	}
	@Override
	public void addAclRolefunc(AclRolefunc aclRolefunc){
		aclRolefunc.getId().setBankorgId(SystemUtil.getCurrBankorgId());
		aclRoleFuncDao.save(aclRolefunc);
	}
	
	//菜单相关
	@Override
	public void saveAclMenu(AclRole aclRole,List<AclRolemenu> list){
		//获得角色ID
		AclRolemenuId aclRolemenuId = new AclRolemenuId();
		aclRolemenuId.setBankorgId(SystemUtil.getCurrBankorgId());
		aclRolemenuId.setRoldId(aclRole.getId().getRoldId());
		AclRolemenu aclRolemenu = new AclRolemenu(aclRolemenuId);
		List<AclRolemenu> delList = aclRoleMenuDao.findByTemplate(aclRolemenu);
		aclRoleMenuDao.deleteAll(delList);
		
		//listAll所有的判断后所有要加的，包括父级
		List<AclRolemenu> listAll = new ArrayList<AclRolemenu>();
		listAll.addAll(list);
		//要改beginning
		List<AclRolemenu> list1 = new ArrayList<AclRolemenu>(); 
		List<AclRolemenu> list2 = new ArrayList<AclRolemenu>();
		List<AclRolemenu> list3 = new ArrayList<AclRolemenu>();
		List<AclRolemenu> list4 = new ArrayList<AclRolemenu>();
		for(AclRolemenu aclRolemenuTmp:list){
			aclRolemenuTmp.getId().setBankorgId(SystemUtil.getCurrBankorgId());
			aclRoleMenuDao.save(aclRolemenuTmp);
			
			AclMenutree aclMenutree = aclMenuTreeDao.findByPk(new AclMenutreeId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getNodeId()));
			if(aclMenutree==null){
				continue;
			}
			String parentId = aclMenutree.getParentId();
			
			boolean prarentNotInd = false;
			for(AclRolemenu aclRolemenuTmp2:listAll){
				if(parentId.equalsIgnoreCase(aclRolemenuTmp2.getId().getNodeId())){
					prarentNotInd = true;
					break;
				}
			}
			if(prarentNotInd==false){
				AclRolemenuId aclRolemenuIdParent = new AclRolemenuId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getRoldId(),parentId);
				AclRolemenu aclRolemenuParent = new AclRolemenu(aclRolemenuIdParent);
				aclRoleMenuDao.save(aclRolemenuParent);
				list1.add(aclRolemenuParent);
				listAll.add(aclRolemenuParent);
			}
		}
		for(AclRolemenu aclRolemenuTmp:list1){
			AclMenutree aclMenutree = aclMenuTreeDao.findByPk(new AclMenutreeId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getNodeId()));
			if(aclMenutree==null){
				continue;
			}
			String parentId = aclMenutree.getParentId();
			
			boolean prarentNotInd = false;
			for(AclRolemenu aclRolemenuTmp2:listAll){
				if(parentId.equalsIgnoreCase(aclRolemenuTmp2.getId().getNodeId())){
					prarentNotInd = true;
					break;
				}
			}
			if(prarentNotInd==false){
				AclRolemenuId aclRolemenuIdParent = new AclRolemenuId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getRoldId(),parentId);
				AclRolemenu aclRolemenuParent = new AclRolemenu(aclRolemenuIdParent);
				aclRoleMenuDao.save(aclRolemenuParent);
				list2.add(aclRolemenuParent);
				listAll.add(aclRolemenuParent);
			}
		}
		for(AclRolemenu aclRolemenuTmp:list2){
			AclMenutree aclMenutree = aclMenuTreeDao.findByPk(new AclMenutreeId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getNodeId()));
			if(aclMenutree==null){
				continue;
			}
			String parentId = aclMenutree.getParentId();
			
			boolean prarentNotInd = false;
			for(AclRolemenu aclRolemenuTmp2:listAll){
				if(parentId.equalsIgnoreCase(aclRolemenuTmp2.getId().getNodeId())){
					prarentNotInd = true;
					break;
				}
			}
			if(prarentNotInd==false){
				AclRolemenuId aclRolemenuIdParent = new AclRolemenuId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getRoldId(),parentId);
				AclRolemenu aclRolemenuParent = new AclRolemenu(aclRolemenuIdParent);
				aclRoleMenuDao.save(aclRolemenuParent);
				list3.add(aclRolemenuParent);
				listAll.add(aclRolemenuParent);
			}
		}
		for(AclRolemenu aclRolemenuTmp:list3){
			AclMenutree aclMenutree = aclMenuTreeDao.findByPk(new AclMenutreeId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getNodeId()));
			if(aclMenutree==null){
				continue;
			}
			String parentId = aclMenutree.getParentId();
			
			boolean prarentNotInd = false;
			for(AclRolemenu aclRolemenuTmp2:listAll){
				if(parentId.equalsIgnoreCase(aclRolemenuTmp2.getId().getNodeId())){
					prarentNotInd = true;
					break;
				}
			}
			if(prarentNotInd==false){
				AclRolemenuId aclRolemenuIdParent = new AclRolemenuId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getRoldId(),parentId);
				AclRolemenu aclRolemenuParent = new AclRolemenu(aclRolemenuIdParent);
				aclRoleMenuDao.save(aclRolemenuParent);
				list4.add(aclRolemenuParent);
				listAll.add(aclRolemenuParent);
			}
		}
		for(AclRolemenu aclRolemenuTmp:list4){
			AclMenutree aclMenutree = aclMenuTreeDao.findByPk(new AclMenutreeId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getNodeId()));
			if(aclMenutree==null){
				continue;
			}
			String parentId = aclMenutree.getParentId();
			
			boolean prarentNotInd = false;
			for(AclRolemenu aclRolemenuTmp2:listAll){
				if(parentId.equalsIgnoreCase(aclRolemenuTmp2.getId().getNodeId())){
					prarentNotInd = true;
					break;
				}
			}
			if(prarentNotInd==false){
				AclRolemenuId aclRolemenuIdParent = new AclRolemenuId(SystemUtil.getCurrBankorgId(),aclRolemenuTmp.getId().getRoldId(),parentId);
				AclRolemenu aclRolemenuParent = new AclRolemenu(aclRolemenuIdParent);
				aclRoleMenuDao.save(aclRolemenuParent);
			}
		}
		//要改end
	}
	
	public AclMenuTreeDao getAclMenuTreeDao() {
		return aclMenuTreeDao;
	}
	public void setAclMenuTreeDao(AclMenuTreeDao aclMenuTreeDao) {
		this.aclMenuTreeDao = aclMenuTreeDao;
	}
	//用户相关
	@Override
	public List<UserEmployeeForm> listUserAll(AclUser aclUser, Map<String,String> clause,int pageNo, int pageSize){
		List<UserEmployeeForm> listUserAll = new ArrayList<UserEmployeeForm>();
		List<AclUser> userList = aclUserDao.findByTemplateWithPage(aclUser, clause, pageNo, pageSize);
		for(AclUser aclUserTmp:userList){
			UserEmployeeForm userEmployeeForm = new UserEmployeeForm();
			userEmployeeForm.setUserId(aclUserTmp.getUserId());
			userEmployeeForm.setEmployeeId(aclUserTmp.getEmployeeId());
			
			if(aclUserTmp.getEmployeeId()!=null){
				KenEmployeeId kenEmployeeId = new KenEmployeeId();
				kenEmployeeId.setBankorgId(SystemUtil.getCurrBankorgId());
				kenEmployeeId.setEmployeeId(aclUserTmp.getEmployeeId());
				KenEmployee kenEmployee = kenEmployeeDao.findByPk(kenEmployeeId);
				if(kenEmployee!=null){
					if(kenEmployee.getEmployeeName()!=null){
						userEmployeeForm.setEmployeeName(kenEmployee.getEmployeeName());
					}
					if(kenEmployee.getBrBranchId()!=null){
						userEmployeeForm.setBrBranchId(kenEmployee.getBrBranchId());
					}
				}
			}
			listUserAll.add(userEmployeeForm);
		}
		return listUserAll;
	}
	@Override
	public Long countUserAll(AclUser aclUser, Map<String,String> clause){
		return aclUserDao.queryCount(aclUser , clause);
	}
	
	/**
	 * 特定角色的用户
	 * @param aclUserrole
	 * @param clause
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override 
	public List<UserEmployeeForm> listAclRoleUsers(AclUserrole aclUserrole, Map<String,String> clause,int pageNo, int pageSize){
		List<UserEmployeeForm> listUserRole = new ArrayList<UserEmployeeForm>();

		List<AclUserrole> listAclUserroles = aclUserRoleDao.findByTemplateWithPage(aclUserrole, clause, pageNo, pageSize);
		for(AclUserrole aclUserroleTmp:listAclUserroles){
			AclUser aclUser =  aclUserDao.findByPk(aclUserroleTmp.getId().getUserId());
			UserEmployeeForm userEmployeeForm = new UserEmployeeForm();
			userEmployeeForm.setUserId(aclUser.getUserId());
			userEmployeeForm.setEmployeeId(aclUser.getEmployeeId());
			
			if(aclUser.getEmployeeId()!=null){
				KenEmployeeId kenEmployeeId = new KenEmployeeId();
				kenEmployeeId.setBankorgId(SystemUtil.getCurrBankorgId());
				kenEmployeeId.setEmployeeId(aclUser.getEmployeeId());
				KenEmployee kenEmployee = kenEmployeeDao.findByPk(kenEmployeeId);
				if(kenEmployee!=null){
					userEmployeeForm.setEmployeeName(kenEmployee.getEmployeeName());
					userEmployeeForm.setBrBranchId(kenEmployee.getBrBranchId());
				}
				
			}
			listUserRole.add(userEmployeeForm);
		}
		
		return listUserRole;
	}
	@Override
	public Long countAclRoleUsers(AclUserrole aclUserrole, Map<String,String> clause){
		return aclUserRoleDao.queryCount(aclUserrole, clause);
	}
	@Override
	public void deleteAclUserrole(AclUserrole aclUserrole){
		aclUserRoleDao.delete(aclUserrole);
	}
	@Override
	public void addAclUserrole(AclUserrole aclUserrole){
		aclUserRoleDao.save(aclUserrole);
	}
	
	
	
}
