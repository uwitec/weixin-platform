package com.xuchunchun.abframe.web.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuchunchun.abframe.db.entity.KenBankbranch;
import com.xuchunchun.abframe.service.KenBankbranchService;
import com.xuchunchun.base.action.DefaultAction;
import com.xuchunchun.base.annotation.Action;
@Action("kenBankbranchAction")
public class KenBankbranchAction extends DefaultAction{
	private static final long serialVersionUID = 1L;
	@Autowired
	private  KenBankbranchService kenBankbranchService;
	
	private List<KenBankbranch> kenBankbranchs;
	
	private KenBankbranch kenBankbranch;
	
	//add
	public String add() throws UnsupportedEncodingException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		KenBankbranch ken = kenBankbranch;
		String brBranchName = new String(kenBankbranch.getBrBranchName().getBytes("iso-8859-1"),"UTF-8");
		String addr = new String(kenBankbranch.getBnPhyAddr().getBytes("iso-8859-1"),"UTF-8");
		String state = new String(kenBankbranch.getBnPhyState().getBytes("iso-8859-1"),"UTF-8");
		String city = new String(kenBankbranch.getBnPhyCity().getBytes("iso-8859-1"),"UTF-8");
		String persion = new String(kenBankbranch.getBnContactPerson().getBytes("iso-8859-1"),"UTF-8");
		String title = new String(kenBankbranch.getBnContactTitle().getBytes("iso-8859-1"),"UTF-8");
		ken.setBrBranchName(brBranchName);
		ken.setBnPhyAddr(addr);
		ken.setBnPhyState(state);
		ken.setBnPhyCity(city);
		ken.setBnContactPerson(persion);
		ken.setBnContactTitle(title);*/
		this.kenBankbranchService.add(kenBankbranch);
		return SUCCESS;
	}
	//detail
	public String detail() {
		detailTag = "detail";
		this.kenBankbranch = kenBankbranchService.detail(kenBankbranch);
		return SUCCESS;
	}
	//update
	public String update() throws UnsupportedEncodingException{
		/*HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		KenBankbranch ken = kenBankbranch;
		String brBranchName = new String(kenBankbranch.getBrBranchName().getBytes("iso-8859-1"),"UTF-8");
		String addr = new String(kenBankbranch.getBnPhyAddr().getBytes("iso-8859-1"),"UTF-8");
		String state = new String(kenBankbranch.getBnPhyState().getBytes("iso-8859-1"),"UTF-8");
		String city = new String(kenBankbranch.getBnPhyCity().getBytes("iso-8859-1"),"UTF-8");
		String persion = new String(kenBankbranch.getBnContactPerson().getBytes("iso-8859-1"),"UTF-8");
		String title = new String(kenBankbranch.getBnContactTitle().getBytes("iso-8859-1"),"UTF-8");
		ken.setBrBranchName(brBranchName);
		ken.setBnPhyAddr(addr);
		ken.setBnPhyState(state);
		ken.setBnPhyCity(city);
		ken.setBnContactPerson(persion);
		ken.setBnContactTitle(title);*/
		this.kenBankbranchService.update(kenBankbranch);
		return SUCCESS;
	}
	//delete
	public void delete(){
		this.kenBankbranchService.delete(kenBankbranchs);
	}
	//list
	public void querylist(){
		if(kenBankbranch == null){
			kenBankbranch = new KenBankbranch();
		}
		kenBankbranchs = kenBankbranchService.querylist(kenBankbranch, clause, page, pagesize);
		totalCount = kenBankbranchService.queryCount(kenBankbranch, clause);
		returnListJSON(kenBankbranchs,totalCount);
	}
	
	public List<KenBankbranch> getKenBankbranchs() {
		return kenBankbranchs;
	}
	public void setKenBankbranchs(List<KenBankbranch> kenBankbranchs) {
		this.kenBankbranchs = kenBankbranchs;
	}
	public KenBankbranch getKenBankbranch() {
		return kenBankbranch;
	}
	public void setKenBankbranch(KenBankbranch kenBankbranch) {
		this.kenBankbranch = kenBankbranch;
	}
	
}
