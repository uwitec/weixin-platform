package com.xuchunchun.abframe.db.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AclSysparameter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_SYSPARAMETER")
public class AclSysparameter implements java.io.Serializable {

	// Fields

	private Long bankorgId;
	private String fstPwdChange;
	private String chgPwdOldChk;
	private Long oldChangeCounts;
	private Long maxErrCount;
	private Date modiDate;
	private String modiUser;
	private Integer version;
	private Date lastBusinessDate;
	private Date currBusinessDate;
	private Date nextBusinessDate;
	private String sysparamDateFormat;
	private String languageId;

	// Constructors

	/** default constructor */
	public AclSysparameter() {
	}

	/** minimal constructor */
	public AclSysparameter(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	/** full constructor */
	public AclSysparameter(Long bankorgId, String fstPwdChange,
			String chgPwdOldChk, Long oldChangeCounts, Long maxErrCount,
			Date modiDate, String modiUser, Integer version,
			Date lastBusinessDate, Date currBusinessDate,
			Date nextBusinessDate, String sysparamDateFormat, String languageId) {
		this.bankorgId = bankorgId;
		this.fstPwdChange = fstPwdChange;
		this.chgPwdOldChk = chgPwdOldChk;
		this.oldChangeCounts = oldChangeCounts;
		this.maxErrCount = maxErrCount;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
		this.lastBusinessDate = lastBusinessDate;
		this.currBusinessDate = currBusinessDate;
		this.nextBusinessDate = nextBusinessDate;
		this.sysparamDateFormat = sysparamDateFormat;
		this.languageId = languageId;
	}

	// Property accessors
	@Id
	@Column(name = "BANKORG_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getBankorgId() {
		return this.bankorgId;
	}

	public void setBankorgId(Long bankorgId) {
		this.bankorgId = bankorgId;
	}

	@Column(name = "FST_PWD_CHANGE", length = 1)
	public String getFstPwdChange() {
		return this.fstPwdChange;
	}

	public void setFstPwdChange(String fstPwdChange) {
		this.fstPwdChange = fstPwdChange;
	}

	@Column(name = "CHG_PWD_OLD_CHK", length = 1)
	public String getChgPwdOldChk() {
		return this.chgPwdOldChk;
	}

	public void setChgPwdOldChk(String chgPwdOldChk) {
		this.chgPwdOldChk = chgPwdOldChk;
	}

	@Column(name = "OLD_CHANGE_COUNTS", precision = 10, scale = 0)
	public Long getOldChangeCounts() {
		return this.oldChangeCounts;
	}

	public void setOldChangeCounts(Long oldChangeCounts) {
		this.oldChangeCounts = oldChangeCounts;
	}

	@Column(name = "MAX_ERR_COUNT", precision = 10, scale = 0)
	public Long getMaxErrCount() {
		return this.maxErrCount;
	}

	public void setMaxErrCount(Long maxErrCount) {
		this.maxErrCount = maxErrCount;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_BUSINESS_DATE", length = 7)
	public Date getLastBusinessDate() {
		return this.lastBusinessDate;
	}

	public void setLastBusinessDate(Date lastBusinessDate) {
		this.lastBusinessDate = lastBusinessDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CURR_BUSINESS_DATE", length = 7)
	public Date getCurrBusinessDate() {
		return this.currBusinessDate;
	}

	public void setCurrBusinessDate(Date currBusinessDate) {
		this.currBusinessDate = currBusinessDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NEXT_BUSINESS_DATE", length = 7)
	public Date getNextBusinessDate() {
		return this.nextBusinessDate;
	}

	public void setNextBusinessDate(Date nextBusinessDate) {
		this.nextBusinessDate = nextBusinessDate;
	}

	@Column(name = "SYSPARAM_DATE_FORMAT", length = 20)
	public String getSysparamDateFormat() {
		return this.sysparamDateFormat;
	}

	public void setSysparamDateFormat(String sysparamDateFormat) {
		this.sysparamDateFormat = sysparamDateFormat;
	}

	@Column(name = "LANGUAGE_ID", length = 2)
	public String getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

}