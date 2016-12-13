package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 还款明细表
 * 
 * @author Administrator
 *
 */
public class Repaynote implements Serializable {
	private Integer repayNoteId;// 主键ID
	private Lssuing lssuing;// 还款对应的借款
	private Uservip uservip;// 借款用户
	private Timestamp repayDate;// 还款时间
	private Double moneyOne;// 还款本金
	private Double moneyTwo;// 还款利息
	private Double moneyThree;// 已还金额
	private Double moneyFour;// 还款总额
	private Integer repayState;// 还款状态 0：未还、1：已还完
	private Integer overdays;
	private Double overInterest;

	public Repaynote() {
	}

	public Integer getOverdays() {
		return overdays;
	}

	public void setOverdays(Integer overdays) {
		this.overdays = overdays;
	}

	public Double getOverInterest() {
		return overInterest;
	}

	public void setOverInterest(Double overInterest) {
		this.overInterest = overInterest;
	}

	public Repaynote(Integer repayNoteId) {
		this.repayNoteId = repayNoteId;
	}

	public Repaynote(Integer repayNoteId, Lssuing lssuing, Uservip uservip,
			Timestamp repayDate, Double moneyOne, Double moneyTwo,
			Double moneyThree, Double moneyFour, Integer repayState) {
		this.repayNoteId = repayNoteId;
		this.lssuing = lssuing;
		this.uservip = uservip;
		this.repayDate = repayDate;
		this.moneyOne = moneyOne;
		this.moneyTwo = moneyTwo;
		this.moneyThree = moneyThree;
		this.moneyFour = moneyFour;
		this.repayState = repayState;
	}

	public Integer getRepayNoteId() {
		return this.repayNoteId;
	}

	public void setRepayNoteId(Integer repayNoteId) {
		this.repayNoteId = repayNoteId;
	}

	public Lssuing getLssuing() {
		return this.lssuing;
	}

	public void setLssuing(Lssuing lssuing) {
		this.lssuing = lssuing;
	}

	public Uservip getUservip() {
		return this.uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Timestamp getRepayDate() {
		return this.repayDate;
	}

	public void setRepayDate(Timestamp repayDate) {
		this.repayDate = repayDate;
	}

	public Double getMoneyOne() {
		return this.moneyOne;
	}

	public void setMoneyOne(Double moneyOne) {
		this.moneyOne = moneyOne;
	}

	public Double getMoneyTwo() {
		return this.moneyTwo;
	}

	public void setMoneyTwo(Double moneyTwo) {
		this.moneyTwo = moneyTwo;
	}

	public Double getMoneyThree() {
		return this.moneyThree;
	}

	public void setMoneyThree(Double moneyThree) {
		this.moneyThree = moneyThree;
	}

	public Double getMoneyFour() {
		return this.moneyFour;
	}

	public void setMoneyFour(Double moneyFour) {
		this.moneyFour = moneyFour;
	}

	public Integer getRepayState() {
		return this.repayState;
	}

	public void setRepayState(Integer repayState) {
		this.repayState = repayState;
	}
}
