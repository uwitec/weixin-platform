package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.AclChildsystem;
import com.xuchunchun.abframe.service.AclChildsystemService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclChildsystemAction")
public class AclChildsystemAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AclChildsystemService aclChildsystemService;
	
	private List<AclChildsystem> aclChildsystems;
	
	private AclChildsystem aclChildsystem;
	
	//add
	public String add(){
		this.aclChildsystemService.add(aclChildsystem);
		return SUCCESS;
	}
	//detail
	public String detail() {
		detailTag = "detail";
		this.aclChildsystem = aclChildsystemService.detail(aclChildsystem);
		return SUCCESS;
	}
	//update
	public String update(){
		this.aclChildsystemService.update(aclChildsystem);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.aclChildsystemService.delete(aclChildsystems);
		return SUCCESS;
	}
	//list
	public void querylist(){
		if(aclChildsystem == null){
			aclChildsystem = new AclChildsystem();
		}
		aclChildsystems = aclChildsystemService.querylist(aclChildsystem, clause, page, pagesize);
		totalCount = aclChildsystemService.queryCount(aclChildsystem, clause);
		returnListJSON(aclChildsystems,totalCount);
	}
	

	
	//子系统AclChildsystem得到方法
	public AclChildsystem getAclChildsystem() {
		return aclChildsystem;
	}
	//子系统AclChildsystem设置方法
	public void setAclChildsystem(AclChildsystem aclChildsystem) {
		this.aclChildsystem = aclChildsystem;
	}
	public List<AclChildsystem> getAclChildsystems() {
		return aclChildsystems;
	}
	public void setAclChildsystems(List<AclChildsystem> aclChildsystems) {
		this.aclChildsystems = aclChildsystems;
	}

	
}
