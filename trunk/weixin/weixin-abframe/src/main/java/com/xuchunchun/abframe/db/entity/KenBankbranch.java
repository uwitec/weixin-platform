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
 * KenBankbranch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "KEN_BANKBRANCH")
public class KenBankbranch implements java.io.Serializable {

	// Fields

	private KenBankbranchId id;
	private String brBranchName;
	private String brCostCentre;
	private Short branchLevel;
	private String parentBranchId;
	private String bnPhyCountry;
	private String bnCntryCode;
	private String bnPhyPostcd;
	private String bnPhyAddr;
	private String bnPhyState;
	private String bnPhyCity;
	private String bnTelephoneNo1;
	private String bnTelephoneNo2;
	private String bnTelex1;
	private String bnTelex2;
	private String bnContactPerson;
	private String bnContactTitle;
	private String bnEmail;
	private String bnWebsiteUrl;
	private String modiUser;
	private Date modiDate;
	private Integer version;

	// Constructors

	/** default constructor */
	public KenBankbranch() {
	}

	/** minimal constructor */
	public KenBankbranch(KenBankbranchId id) {
		this.id = id;
	}

	/** full constructor */
	public KenBankbranch(KenBankbranchId id, String brBranchName,
			String brCostCentre, Short branchLevel, String parentBranchId,
			String bnPhyCountry, String bnCntryCode, String bnPhyPostcd,
			String bnPhyAddr, String bnPhyState, String bnPhyCity,
			String bnTelephoneNo1, String bnTelephoneNo2, String bnTelex1,
			String bnTelex2, String bnContactPerson, String bnContactTitle,
			String bnEmail, String bnWebsiteUrl, String modiUser,
			Date modiDate, Integer version) {
		this.id = id;
		this.brBranchName = brBranchName;
		this.brCostCentre = brCostCentre;
		this.branchLevel = branchLevel;
		this.parentBranchId = parentBranchId;
		this.bnPhyCountry = bnPhyCountry;
		this.bnCntryCode = bnCntryCode;
		this.bnPhyPostcd = bnPhyPostcd;
		this.bnPhyAddr = bnPhyAddr;
		this.bnPhyState = bnPhyState;
		this.bnPhyCity = bnPhyCity;
		this.bnTelephoneNo1 = bnTelephoneNo1;
		this.bnTelephoneNo2 = bnTelephoneNo2;
		this.bnTelex1 = bnTelex1;
		this.bnTelex2 = bnTelex2;
		this.bnContactPerson = bnContactPerson;
		this.bnContactTitle = bnContactTitle;
		this.bnEmail = bnEmail;
		this.bnWebsiteUrl = bnWebsiteUrl;
		this.modiUser = modiUser;
		this.modiDate = modiDate;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "brBranchId", column = @Column(name = "BR_BRANCH_ID", nullable = false, length = 10)) })
	public KenBankbranchId getId() {
		return this.id;
	}

	public void setId(KenBankbranchId id) {
		this.id = id;
	}

	@Column(name = "BR_BRANCH_NAME", length = 40)
	public String getBrBranchName() {
		return this.brBranchName;
	}

	public void setBrBranchName(String brBranchName) {
		this.brBranchName = brBranchName;
	}

	@Column(name = "BR_COST_CENTRE", length = 10)
	public String getBrCostCentre() {
		return this.brCostCentre;
	}

	public void setBrCostCentre(String brCostCentre) {
		this.brCostCentre = brCostCentre;
	}

	@Column(name = "BRANCH_LEVEL", precision = 3, scale = 0)
	public Short getBranchLevel() {
		return this.branchLevel;
	}

	public void setBranchLevel(Short branchLevel) {
		this.branchLevel = branchLevel;
	}

	@Column(name = "PARENT_BRANCH_ID", length = 10)
	public String getParentBranchId() {
		return this.parentBranchId;
	}

	public void setParentBranchId(String parentBranchId) {
		this.parentBranchId = parentBranchId;
	}

	@Column(name = "BN_PHY_COUNTRY", length = 3)
	public String getBnPhyCountry() {
		return this.bnPhyCountry;
	}

	public void setBnPhyCountry(String bnPhyCountry) {
		this.bnPhyCountry = bnPhyCountry;
	}

	@Column(name = "BN_CNTRY_CODE", length = 3)
	public String getBnCntryCode() {
		return this.bnCntryCode;
	}

	public void setBnCntryCode(String bnCntryCode) {
		this.bnCntryCode = bnCntryCode;
	}

	@Column(name = "BN_PHY_POSTCD", length = 10)
	public String getBnPhyPostcd() {
		return this.bnPhyPostcd;
	}

	public void setBnPhyPostcd(String bnPhyPostcd) {
		this.bnPhyPostcd = bnPhyPostcd;
	}

	@Column(name = "BN_PHY_ADDR", length = 40)
	public String getBnPhyAddr() {
		return this.bnPhyAddr;
	}

	public void setBnPhyAddr(String bnPhyAddr) {
		this.bnPhyAddr = bnPhyAddr;
	}

	@Column(name = "BN_PHY_STATE", length = 20)
	public String getBnPhyState() {
		return this.bnPhyState;
	}

	public void setBnPhyState(String bnPhyState) {
		this.bnPhyState = bnPhyState;
	}

	@Column(name = "BN_PHY_CITY", length = 20)
	public String getBnPhyCity() {
		return this.bnPhyCity;
	}

	public void setBnPhyCity(String bnPhyCity) {
		this.bnPhyCity = bnPhyCity;
	}

	@Column(name = "BN_TELEPHONE_NO1", length = 15)
	public String getBnTelephoneNo1() {
		return this.bnTelephoneNo1;
	}

	public void setBnTelephoneNo1(String bnTelephoneNo1) {
		this.bnTelephoneNo1 = bnTelephoneNo1;
	}

	@Column(name = "BN_TELEPHONE_NO2", length = 15)
	public String getBnTelephoneNo2() {
		return this.bnTelephoneNo2;
	}

	public void setBnTelephoneNo2(String bnTelephoneNo2) {
		this.bnTelephoneNo2 = bnTelephoneNo2;
	}

	@Column(name = "BN_TELEX1", length = 15)
	public String getBnTelex1() {
		return this.bnTelex1;
	}

	public void setBnTelex1(String bnTelex1) {
		this.bnTelex1 = bnTelex1;
	}

	@Column(name = "BN_TELEX2", length = 15)
	public String getBnTelex2() {
		return this.bnTelex2;
	}

	public void setBnTelex2(String bnTelex2) {
		this.bnTelex2 = bnTelex2;
	}

	@Column(name = "BN_CONTACT_PERSON", length = 20)
	public String getBnContactPerson() {
		return this.bnContactPerson;
	}

	public void setBnContactPerson(String bnContactPerson) {
		this.bnContactPerson = bnContactPerson;
	}

	@Column(name = "BN_CONTACT_TITLE", length = 20)
	public String getBnContactTitle() {
		return this.bnContactTitle;
	}

	public void setBnContactTitle(String bnContactTitle) {
		this.bnContactTitle = bnContactTitle;
	}

	@Column(name = "BN_EMAIL", length = 40)
	public String getBnEmail() {
		return this.bnEmail;
	}

	public void setBnEmail(String bnEmail) {
		this.bnEmail = bnEmail;
	}

	@Column(name = "BN_WEBSITE_URL", length = 40)
	public String getBnWebsiteUrl() {
		return this.bnWebsiteUrl;
	}

	public void setBnWebsiteUrl(String bnWebsiteUrl) {
		this.bnWebsiteUrl = bnWebsiteUrl;
	}

	@Column(name = "MODI_USER", length = 10)
	public String getModiUser() {
		return this.modiUser;
	}

	public void setModiUser(String modiUser) {
		this.modiUser = modiUser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MODI_DATE", length = 10)
	public Date getModiDate() {
		return this.modiDate;
	}

	public void setModiDate(Date modiDate) {
		this.modiDate = modiDate;
	}

	@Column(name = "VERSION", precision = 8, scale = 0)
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}