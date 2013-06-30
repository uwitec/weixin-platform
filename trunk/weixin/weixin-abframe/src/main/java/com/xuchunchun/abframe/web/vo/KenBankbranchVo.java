package com.xuchunchun.abframe.web.vo;

public class KenBankbranchVo {
	private Long bankorgId;
	private String brBranchId;
	private String brBranchName;
	public KenBankbranchVo(Long bankorgId,String brBranchId,String brBranchName){
		this.bankorgId = bankorgId;
		this.brBranchId = brBranchId;
		this.brBranchName = brBranchName;
	}
	public Long getBankorgId() {
		return bankorgId;
	}
	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}
	public String getBrBranchId() {
		return brBranchId;
	}
	public void setBrBranchId(String brBranchId) {
		this.brBranchId = brBranchId;
	}
	public String getBrBranchName() {
		return brBranchName;
	}
	public void setBrBranchName(String brBranchName) {
		this.brBranchName = brBranchName;
	}
	
}
