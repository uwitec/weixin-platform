package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.web.vo.AclFuncexcludeVo;

import com.xuchunchun.abframe.db.entity.AclFuncexclude;
import com.xuchunchun.abframe.service.AclFuncexcludeService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("aclFuncexcludeAction")
public class AclFuncexcludeAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AclFuncexcludeService aclFuncexcludeService;
	
	private List<AclFuncexclude> aclFuncexcludes;
	
	private AclFuncexclude aclFuncexclude;
	
	private List<AclFuncexcludeVo> aclFuncexcludeVos;
	
	//add
	public String add(){
		this.aclFuncexcludeService.add(aclFuncexclude);
		return SUCCESS;
	}
	//detail
	public String detail() {
		this.aclFuncexclude = aclFuncexcludeService.detail(aclFuncexclude);
		detailTag = "detail";
		return SUCCESS;
	}
	//update
	public String update(){
		this.aclFuncexcludeService.update(aclFuncexclude);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.aclFuncexcludeService.delete(aclFuncexcludes);
		return SUCCESS;
	}
	//list
	public void querylist(){
		if(aclFuncexclude == null){
			aclFuncexclude = new AclFuncexclude();
		}
		//aclFuncexcludes = aclFuncexcludeService.querylist(aclFuncexclude, clause, page, pagesize);
		aclFuncexcludeVos = aclFuncexcludeService.querylistVo(aclFuncexclude, clause, page, pagesize);
		totalCount = aclFuncexcludeService.queryCount(aclFuncexclude, clause);
//		String datacc1 = "{Rows:[{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}" +
//				",{\"CustomerID\":\"ALFKI\",\"CompanyName\":\"Alfreds Futterkiste\",\"ContactName\":\"Maria Anders\",\"ContactTitle\":\"Sales Representative\",\"Address\":\"Obere Str. 57\",\"City\":\"Berlin\",\"Region\":null,\"PostalCode\":\"12209\",\"Country\":\"Germany\",\"Phone\":\"030-0074321\",\"Fax\":\"030-0076545\"}]}";
		returnListJSON(aclFuncexcludeVos,totalCount);
	}
	
	public List<AclFuncexclude> getAclFuncexcludes() {
		return aclFuncexcludes;
	}
	public void setAclFuncexcludes(List<AclFuncexclude> aclFuncexcludes) {
		this.aclFuncexcludes = aclFuncexcludes;
	}
	public AclFuncexclude getAclFuncexclude() {
		return aclFuncexclude;
	}
	public void setAclFuncexclude(AclFuncexclude aclFuncexclude) {
		this.aclFuncexclude = aclFuncexclude;
	}
	
}
