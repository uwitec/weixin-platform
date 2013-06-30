package com.xuchunchun.abframe.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.KenEmpbranch;
import com.xuchunchun.abframe.service.KenEmpbranchService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;

@Action("kenEmpbranchAction")
public class KenEmpbranchAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private KenEmpbranchService kenEmpbranchService;
	
	private List<KenEmpbranch> kenEmpbranchs;
	
	private KenEmpbranch kenEmpbranch;
	
	//add
	public String add(){
		this.kenEmpbranchService.add(kenEmpbranch);
		return SUCCESS;
	}
	//detail
	public String detail() {
		detailTag = "detail";
		this.kenEmpbranch = kenEmpbranchService.detail(kenEmpbranch);
		return SUCCESS;
	}
	//update
	public String update(){
		this.kenEmpbranchService.update(kenEmpbranch);
		return SUCCESS;
	}
	//delete
	public String delete(){
		this.kenEmpbranchService.delete(kenEmpbranchs);
	    return SUCCESS;
	}
	//list
	public void querylist(){
		if(kenEmpbranch == null){
			kenEmpbranch = new KenEmpbranch();
		}
		kenEmpbranchs = kenEmpbranchService.querylist(kenEmpbranch, clause, page, pagesize);
		totalCount = kenEmpbranchService.queryCount(kenEmpbranch, clause);
		returnListJSON(kenEmpbranchs,totalCount);
	}
	
	public List<KenEmpbranch> getKenEmpbranchs() {
		return kenEmpbranchs;
	}
	public void setKenEmpbranchs(List<KenEmpbranch> kenEmpbranchs) {
		this.kenEmpbranchs = kenEmpbranchs;
	}
	public KenEmpbranch getKenEmpbranch() {
		return kenEmpbranch;
	}
	public void setKenEmpbranch(KenEmpbranch kenEmpbranch) {
		this.kenEmpbranch = kenEmpbranch;
	}


	
}
