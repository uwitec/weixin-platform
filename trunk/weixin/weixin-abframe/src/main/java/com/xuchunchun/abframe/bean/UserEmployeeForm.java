package com.xuchunchun.abframe.bean;

/**
 * 用户员工Form
 * @author Dai
 *
 */
public class UserEmployeeForm {
	private Long bankorgId;
	private String userId;
	private String employeeId;
	private String employeeName;
	private String brBranchId;
	
	public Long getBankorgId() {
		return bankorgId;
	}
	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBrBranchId() {
		return brBranchId;
	}
	public void setBrBranchId(String brBranchId) {
		this.brBranchId = brBranchId;
	}
}
