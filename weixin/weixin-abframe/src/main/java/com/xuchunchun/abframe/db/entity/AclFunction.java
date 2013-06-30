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
 * AclFunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACL_FUNCTION")
public class AclFunction implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private AclFunctionId id;
	private String functionName;
	private String systemId;
	private String funcUrl;
	private Date modiDate;
	private String modiUser;
	private Integer version;

	// Constructors

	/** default constructor */
	public AclFunction() {
	}

	/** minimal constructor */
	public AclFunction(AclFunctionId id) {
		this.id = id;
	}

	/** full constructor */
	public AclFunction(AclFunctionId id, String functionName, String systemId,
			String funcUrl, Date modiDate, String modiUser, Integer version) {
		this.id = id;
		this.functionName = functionName;
		this.systemId = systemId;
		this.funcUrl = funcUrl;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "functionId", column = @Column(name = "FUNCTION_ID", nullable = false, length = 20)) })
	public AclFunctionId getId() {
		return this.id;
	}

	public void setId(AclFunctionId id) {
		this.id = id;
	}

	@Column(name = "FUNCTION_NAME", length = 40)
	public String getFunctionName() {
		return this.functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	@Column(name = "SYSTEM_ID", length = 20)
	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@Column(name = "FUNC_URL", length = 200)
	public String getFuncUrl() {
		return this.funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
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