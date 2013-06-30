package com.xuchunchun.weixin.db.entity;

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
 * WxCustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_CUSTOMER")
public class WxCustomer implements java.io.Serializable {

	// Fields

	private WxCustomerId id;
	private String platformId;
	private String openId;
	private String ecifNo;
	private String customerName;
	private String sexId;
	private Integer age;
	private String custStatus;
	private String fakeId;
	private String nickName;
	private String remarkName;
	private String groupId;
	private String language;
	private String city;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public WxCustomer() {
	}

	/** minimal constructor */
	public WxCustomer(WxCustomerId id) {
		this.id = id;
	}

	/** full constructor */
	public WxCustomer(WxCustomerId id, String platformId, String openId,
			String ecifNo, String customerName, String sexId, Integer age,
			String custStatus, String fakeId, String nickName,
			String remarkName, String groupId, String language, String city,
			Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.platformId = platformId;
		this.openId = openId;
		this.ecifNo = ecifNo;
		this.customerName = customerName;
		this.sexId = sexId;
		this.age = age;
		this.custStatus = custStatus;
		this.fakeId = fakeId;
		this.nickName = nickName;
		this.remarkName = remarkName;
		this.groupId = groupId;
		this.language = language;
		this.city = city;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "wxCustomerId", column = @Column(name = "WX_CUSTOMER_ID", nullable = false, length = 40)) })
	public WxCustomerId getId() {
		return this.id;
	}

	public void setId(WxCustomerId id) {
		this.id = id;
	}

	@Column(name = "PLATFORM_ID", length = 40)
	public String getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	@Column(name = "OPEN_ID", length = 40)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "ECIF_NO", length = 40)
	public String getEcifNo() {
		return this.ecifNo;
	}

	public void setEcifNo(String ecifNo) {
		this.ecifNo = ecifNo;
	}

	@Column(name = "CUSTOMER_NAME", length = 40)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "SEX_ID", length = 1)
	public String getSexId() {
		return this.sexId;
	}

	public void setSexId(String sexId) {
		this.sexId = sexId;
	}

	@Column(name = "AGE", precision = 8, scale = 0)
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "CUST_STATUS", length = 1)
	public String getCustStatus() {
		return this.custStatus;
	}

	public void setCustStatus(String custStatus) {
		this.custStatus = custStatus;
	}

	@Column(name = "FAKE_ID", length = 40)
	public String getFakeId() {
		return this.fakeId;
	}

	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}

	@Column(name = "NICK_NAME", length = 40)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "REMARK_NAME", length = 60)
	public String getRemarkName() {
		return this.remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	@Column(name = "GROUP_ID", length = 40)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "LANGUAGE", length = 40)
	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "CITY", length = 40)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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