package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Moneyhistorydetail implements Serializable {
	private Integer moneyHistoryDetailId;
	private Eventtype eventtype;
	private Uservip uservip;
	private Timestamp occurTime;
	private Double affectMoney;
	private Double availableBalance;
	private Double frozenMoney;
	private Double collectMoney;
	private String introduction;
	private String loanno;

	public Moneyhistorydetail() {
		this.collectMoney = 0.0;
		this.frozenMoney = 0.0;

	}

	public String getLoanno() {
		return loanno;
	}

	public void setLoanno(String loanno) {
		this.loanno = loanno;
	}

	public Moneyhistorydetail(Integer moneyHistoryDetailId) {
		this.moneyHistoryDetailId = moneyHistoryDetailId;
	}

	public Moneyhistorydetail(Integer moneyHistoryDetailId,
			Eventtype eventtype, Uservip uservip, Timestamp occurTime,
			Double affectMoney, Double availableBalance, Double frozenMoney,
			Double collectMoney, String introduction) {
		this.moneyHistoryDetailId = moneyHistoryDetailId;
		this.eventtype = eventtype;
		this.uservip = uservip;
		this.occurTime = occurTime;
		this.affectMoney = affectMoney;
		this.availableBalance = availableBalance;
		this.frozenMoney = frozenMoney;
		this.collectMoney = collectMoney;
		this.introduction = introduction;
	}

	public Integer getMoneyHistoryDetailId() {
		return this.moneyHistoryDetailId;
	}

	public void setMoneyHistoryDetailId(Integer moneyHistoryDetailId) {
		this.moneyHistoryDetailId = moneyHistoryDetailId;
	}

	public Eventtype getEventtype() {
		return this.eventtype;
	}

	public void setEventtype(Eventtype eventtype) {
		this.eventtype = eventtype;
	}

	public Uservip getUservip() {
		return this.uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Timestamp getOccurTime() {
		return this.occurTime;
	}

	public void setOccurTime(Timestamp occurTime) {
		this.occurTime = occurTime;
	}

	public Double getAffectMoney() {
		return this.affectMoney;
	}

	public void setAffectMoney(Double affectMoney) {
		this.affectMoney = affectMoney;
	}

	public Double getAvailableBalance() {
		return this.availableBalance;
	}

	public void setAvailableBalance(Double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public Double getFrozenMoney() {
		return this.frozenMoney;
	}

	public void setFrozenMoney(Double frozenMoney) {
		this.frozenMoney = frozenMoney;
	}

	public Double getCollectMoney() {
		return this.collectMoney;
	}

	public void setCollectMoney(Double collectMoney) {
		this.collectMoney = collectMoney;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
}
