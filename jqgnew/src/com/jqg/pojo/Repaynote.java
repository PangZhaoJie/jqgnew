package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ������ϸ��
 * 
 * @author Administrator
 *
 */
public class Repaynote implements Serializable {
	private Integer repayNoteId;// ����ID
	private Lssuing lssuing;// �����Ӧ�Ľ��
	private Uservip uservip;// ����û�
	private Timestamp repayDate;// ����ʱ��
	private Double moneyOne;// �����
	private Double moneyTwo;// ������Ϣ
	private Double moneyThree;// �ѻ����
	private Double moneyFour;// �����ܶ�
	private Integer repayState;// ����״̬ 0��δ����1���ѻ���
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
