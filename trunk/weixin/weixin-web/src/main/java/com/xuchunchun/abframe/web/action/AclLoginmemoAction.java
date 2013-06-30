package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.AclLoginmemo;
import com.xuchunchun.abframe.db.entity.AclLoginstrategy;
import com.xuchunchun.abframe.service.AclLoginmemoService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclLoginmemoAction")
public class AclLoginmemoAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AclLoginmemoService aclLoginmemoService;
	
	private List<AclLoginmemo> aclLoginmemos;
	
	private AclLoginmemo aclLoginmemo;
	
	//add
	public String add(){
		this.aclLoginmemoService.add(aclLoginmemo);
		return SUCCESS;
	}
	//detail
	public String detail() {
		this.aclLoginmemo = aclLoginmemoService.detail(aclLoginmemo);
		detailTag = "detail";
		return SUCCESS;
	}
	//update
	public String update(){
		this.aclLoginmemoService.update(aclLoginmemo);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.aclLoginmemoService.delete(aclLoginmemo);
		return SUCCESS;
	}
	//list
	public void querylist(){
		if(aclLoginmemo==null){
			aclLoginmemo = new AclLoginmemo();
		}
		aclLoginmemos = aclLoginmemoService.querylist(aclLoginmemo, clause, page, pagesize);
		totalCount = aclLoginmemoService.queryCount(aclLoginmemo, clause);
//		String datacc1 = "{Rows:[{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}" +
//				",{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}]}";
		returnListJSON(aclLoginmemos,totalCount);
	}
	
	public List<AclLoginmemo> getAclLoginmemos() {
		return aclLoginmemos;
	}
	public void setAclLoginmemos(List<AclLoginmemo> aclLoginmemos) {
		this.aclLoginmemos = aclLoginmemos;
	}
	public AclLoginmemo getAclLoginmemo() {
		return aclLoginmemo;
	}
	public void setAclLoginmemo(AclLoginmemo aclLoginmemo) {
		this.aclLoginmemo = aclLoginmemo;
	}


	
}
