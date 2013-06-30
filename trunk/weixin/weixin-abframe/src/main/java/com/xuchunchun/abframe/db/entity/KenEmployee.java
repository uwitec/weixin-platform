package com.xuchunchun.abframe.db.entity;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * KenEmployee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "KEN_EMPLOYEE")
public class KenEmployee implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private KenEmployeeId id;
	private String brBranchId;
	private String employeeName;
	private String gender;
	private Date birthDate;
	private String empStatus;
	private String leaveInd;
	private Date leaveStartDate;
	private Date leaveEndDate;
	private Date joiningDate;
	private Date dismissDate;
	private String countryAlphaId;
	private String stateId;
	private String cityId;
	private String homeAddress1;
	private String homeAddress2;
	private String homeAddress3;
	private String homeAddress4;
	private String homeTelphone;
	private String homePostCode;
	private String email;
	private String faxno;
	private String mobileNo;
	private String officePhone;
	private String idtypeId;
	private String idNo;
	private String positionId;
	private String proflTitleId;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public KenEmployee() {
	}

	/** minimal constructor */
	public KenEmployee(KenEmployeeId id) {
		this.id = id;
	}

	/** full constructor */
	public KenEmployee(KenEmployeeId id, String brBranchId, String employeeName,
			String gender, Date birthDate, String empStatus, String leaveInd,
			Date leaveStartDate, Date leaveEndDate, Date joiningDate,
			Date dismissDate, String countryAlphaId, String stateId,
			String cityId, String homeAddress1, String homeAddress2,
			String homeAddress3, String homeAddress4, String homeTelphone,
			String homePostCode, String email, String faxno, String mobileNo,
			String officePhone, String idtypeId, String idNo,
			String positionId, String proflTitleId, Date modiDate,
			String modiUser, Integer version) {
		this.id = id;
		this.brBranchId = brBranchId;
		this.employeeName = employeeName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.empStatus = empStatus;
		this.leaveInd = leaveInd;
		this.leaveStartDate = leaveStartDate;
		this.leaveEndDate = leaveEndDate;
		this.joiningDate = joiningDate;
		this.dismissDate = dismissDate;
		this.countryAlphaId = countryAlphaId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.homeAddress1 = homeAddress1;
		this.homeAddress2 = homeAddress2;
		this.homeAddress3 = homeAddress3;
		this.homeAddress4 = homeAddress4;
		this.homeTelphone = homeTelphone;
		this.homePostCode = homePostCode;
		this.email = email;
		this.faxno = faxno;
		this.mobileNo = mobileNo;
		this.officePhone = officePhone;
		this.idtypeId = idtypeId;
		this.idNo = idNo;
		this.positionId = positionId;
		this.proflTitleId = proflTitleId;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "employeeId", column = @Column(name = "EMPLOYEE_ID", nullable = false, length = 10)) })
	public KenEmployeeId getId() {
		return this.id;
	}

	public void setId(KenEmployeeId id) {
		this.id = id;
	}

	@Column(name = "BR_BRANCH_ID", precision = 10, scale = 0)
	public String getBrBranchId() {
		return this.brBranchId;
	}

	public void setBrBranchId(String brBranchId) {
		this.brBranchId = brBranchId;
	}

	@Column(name = "EMPLOYEE_NAME", length = 20)
	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "GENDER", length = 1)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE", length = 7)
	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Column(name = "EMP_STATUS", length = 1)
	public String getEmpStatus() {
		return this.empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	@Column(name = "LEAVE_IND", length = 1)
	public String getLeaveInd() {
		return this.leaveInd;
	}

	public void setLeaveInd(String leaveInd) {
		this.leaveInd = leaveInd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LEAVE_START_DATE", length = 7)
	public Date getLeaveStartDate() {
		return this.leaveStartDate;
	}

	public void setLeaveStartDate(Date leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LEAVE_END_DATE", length = 7)
	public Date getLeaveEndDate() {
		return this.leaveEndDate;
	}

	public void setLeaveEndDate(Date leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "JOINING_DATE", length = 7)
	public Date getJoiningDate() {
		return this.joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DISMISS_DATE", length = 7)
	public Date getDismissDate() {
		return this.dismissDate;
	}

	public void setDismissDate(Date dismissDate) {
		this.dismissDate = dismissDate;
	}

	@Column(name = "COUNTRY_ALPHA_ID", length = 3)
	public String getCountryAlphaId() {
		return this.countryAlphaId;
	}

	public void setCountryAlphaId(String countryAlphaId) {
		this.countryAlphaId = countryAlphaId;
	}

	@Column(name = "STATE_ID", length = 10)
	public String getStateId() {
		return this.stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	@Column(name = "CITY_ID", length = 10)
	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	@Column(name = "HOME_ADDRESS1", length = 40)
	public String getHomeAddress1() {
		return this.homeAddress1;
	}

	public void setHomeAddress1(String homeAddress1) {
		this.homeAddress1 = homeAddress1;
	}

	@Column(name = "HOME_ADDRESS2", length = 40)
	public String getHomeAddress2() {
		return this.homeAddress2;
	}

	public void setHomeAddress2(String homeAddress2) {
		this.homeAddress2 = homeAddress2;
	}

	@Column(name = "HOME_ADDRESS3", length = 40)
	public String getHomeAddress3() {
		return this.homeAddress3;
	}

	public void setHomeAddress3(String homeAddress3) {
		this.homeAddress3 = homeAddress3;
	}

	@Column(name = "HOME_ADDRESS4", length = 40)
	public String getHomeAddress4() {
		return this.homeAddress4;
	}

	public void setHomeAddress4(String homeAddress4) {
		this.homeAddress4 = homeAddress4;
	}

	@Column(name = "HOME_TELPHONE", length = 15)
	public String getHomeTelphone() {
		return this.homeTelphone;
	}

	public void setHomeTelphone(String homeTelphone) {
		this.homeTelphone = homeTelphone;
	}

	@Column(name = "HOME_POST_CODE", length = 10)
	public String getHomePostCode() {
		return this.homePostCode;
	}

	public void setHomePostCode(String homePostCode) {
		this.homePostCode = homePostCode;
	}

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FAXNO", length = 15)
	public String getFaxno() {
		return this.faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	@Column(name = "MOBILE_NO", length = 15)
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "OFFICE_PHONE", length = 15)
	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "IDTYPE_ID", length = 2)
	public String getIdtypeId() {
		return this.idtypeId;
	}

	public void setIdtypeId(String idtypeId) {
		this.idtypeId = idtypeId;
	}

	@Column(name = "ID_NO", length = 20)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "POSITION_ID", length = 10)
	public String getPositionId() {
		return this.positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	@Column(name = "PROFL_TITLE_ID", length = 3)
	public String getProflTitleId() {
		return this.proflTitleId;
	}

	public void setProflTitleId(String proflTitleId) {
		this.proflTitleId = proflTitleId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODI_DATE", length = 7)
	public Date getModiDate() {
		return this.modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	@Column(name = "MODI_USER", length = 64)
	public String getModiUser() {
		return this.modiUser;
	}

	public void setModiUser(String modiUser) {
		this.modiUser = modiUser;
	}

	@Column(name = "VERSION", precision = 8, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}