package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.AclExclude;
import com.xuchunchun.abframe.service.AclExcludeService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclExcludeAction")
public class AclExcludeAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AclExcludeService aclExcludeService;
	
	private List<AclExclude> aclExcludes;
	
	private AclExclude aclExclude;
	
	//add
	public String add(){
		this.aclExcludeService.add(aclExclude);
		return SUCCESS;
	}
	//detail
	public String detail() {
		detailTag = "detail";
		this.aclExclude = aclExcludeService.detail(aclExclude);
		return SUCCESS;
	}
	//update
	public String update(){
		this.aclExcludeService.update(aclExclude);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.aclExcludeService.delete(aclExcludes);
		return SUCCESS;
	}
	//list
	public void querylist(){
		if(aclExclude == null){
			aclExclude = new AclExclude();
		}
		aclExcludes = aclExcludeService.querylist(aclExclude, clause, page, pagesize);
		totalCount = aclExcludeService.queryCount(aclExclude, clause);
		returnListJSON(aclExcludes,totalCount);
	}
	
	public List<AclExclude> getAclExcludes() {
		return aclExcludes;
	}
	public void setAclExcludes(List<AclExclude> aclExcludes) {
		this.aclExcludes = aclExcludes;
	}
	public AclExclude getAclExclude() {
		return aclExclude;
	}
	public void setAclExclude(AclExclude aclExclude) {
		this.aclExclude = aclExclude;
	}


	
}
