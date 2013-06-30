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
 * WxCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WX_CARD")
public class WxCard implements java.io.Serializable {

	// Fields

	private WxCardId id;
	private String cardType;
	private String wxCutsomerId;
	private Date modiDate;
	private String modiUser;
	private Integer version;
	private Long cardIndex;

	// Constructors

	/** default constructor */
	public WxCard() {
	}

	/** minimal constructor */
	public WxCard(WxCardId id) {
		this.id = id;
	}

	/** full constructor */
	public WxCard(WxCardId id, String cardType, String wxCutsomerId,
			Date modiDate, String modiUser, Integer version, Long cardIndex) {
		this.id = id;
		this.cardType = cardType;
		this.wxCutsomerId = wxCutsomerId;
		this.modiDate = modiDate;
		this.modiUser = modiUser;
		this.version = version;
		this.cardIndex = cardIndex;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bankorgId", column = @Column(name = "BANKORG_ID", nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "cardNo", column = @Column(name = "CARD_NO", nullable = false, length = 20)) })
	public WxCardId getId() {
		return this.id;
	}

	public void setId(WxCardId id) {
		this.id = id;
	}

	@Column(name = "CARD_TYPE", length = 1)
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Column(name = "WX_CUTSOMER_ID", length = 40)
	public String getWxCutsomerId() {
		return this.wxCutsomerId;
	}

	public void setWxCutsomerId(String wxCutsomerId) {
		this.wxCutsomerId = wxCutsomerId;
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

	@Column(name = "CARD_INDEX", precision = 10, scale = 0)
	public Long getCardIndex() {
		return this.cardIndex;
	}

	public void setCardIndex(Long cardIndex) {
		this.cardIndex = cardIndex;
	}

}