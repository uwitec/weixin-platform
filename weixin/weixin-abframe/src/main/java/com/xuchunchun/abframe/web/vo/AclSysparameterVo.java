package com.xuchunchun.abframe.web.vo;

/**
 * 系统参数VO
 * @author wangyang
 *
 */
public class AclSysparameterVo {
	private Long bankorgId;
	private String fstPwdChange;
	private String chgPwdOldChk;
	private Long oldChangeCounts;
	private Long maxErrCount;
	public Long getBankorgId() {
		return bankorgId;
	}
	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}
	public String getFstPwdChange() {
		return fstPwdChange;
	}
	public void setFstPwdChange(String fstPwdChange) {
		this.fstPwdChange = fstPwdChange;
	}
	public String getChgPwdOldChk() {
		return chgPwdOldChk;
	}
	public void setChgPwdOldChk(String chgPwdOldChk) {
		this.chgPwdOldChk = chgPwdOldChk;
	}
	public Long getOldChangeCounts() {
		return oldChangeCounts;
	}
	public void setOldChangeCounts(Long oldChangeCounts) {
		this.oldChangeCounts = oldChangeCounts;
	}
	public Long getMaxErrCount() {
		return maxErrCount;
	}
	public void setMaxErrCount(Long maxErrCount) {
		this.maxErrCount = maxErrCount;
	}
	public AclSysparameterVo(Long bankorgId, String fstPwdChange,
			String chgPwdOldChk, Long oldChangeCounts, Long maxErrCount) {
		super();
		this.bankorgId = bankorgId;
		this.fstPwdChange = fstPwdChange;
		this.chgPwdOldChk = chgPwdOldChk;
		this.oldChangeCounts = oldChangeCounts;
		this.maxErrCount = maxErrCount;
	}
	public AclSysparameterVo() {
		super();
	}
	
}
