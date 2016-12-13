package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Record implements Serializable {
	private Integer recordId;
	private Tender tender;
	private Uservip uservip;
	private Timestamp recordDate;
	private Double recordMoney;
	private Double recordRate; 
	private Double recordInterest;
	private Integer recordState;
	private Integer overdays;
	private Double overInterest;
	private Timestamp repaymentDate;//还款时间
	
	private Integer isState;//是否可以进行还款续投奖励

	private int ispayward;// 是否处理回款续投奖励
	private int isTransfer;//是否债权转

	public Record() {
		this.overdays = 0;
		this.overInterest = 0.0;
		this.ispayward = 0;
	}

	public Record(Tender tender, Uservip uservip, Timestamp recordDate,
			Double recordMoney, Double recordRate, Double recordInterest,
			Integer recordState,Timestamp repaymentDate,Integer isState,int isTransfer) {
		this.tender = tender;
		this.uservip = uservip;
		this.recordDate = recordDate;
		this.recordMoney = recordMoney;
		this.recordRate = recordRate;
		this.recordInterest = recordInterest;
		this.recordState = recordState;
		this.repaymentDate=repaymentDate;
		this.isState=isState;
		this.isTransfer = isTransfer;
	}

	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public int getIspayward() {
		return ispayward;
	}

	public void setIspayward(int ispayward) {
		this.ispayward = ispayward;
	}

	public Tender getTender() {
		return this.tender;
	}

	public void setTender(Tender tender) {
		this.tender = tender;
	}

	public Uservip getUservip() {
		return this.uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Timestamp getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Timestamp recordDate) {
		this.recordDate = recordDate;
	}

	public Double getRecordMoney() {
		return this.recordMoney;
	}

	public void setRecordMoney(Double recordMoney) {
		this.recordMoney = recordMoney;
	}

	public Double getRecordRate() {
		return this.recordRate;
	}

	public void setRecordRate(Double recordRate) {
		this.recordRate = recordRate;
	}

	public Double getRecordInterest() {
		return this.recordInterest;
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

	public void setRecordInterest(Double recordInterest) {
		this.recordInterest = recordInterest;
	}

	public Integer getRecordState() {
		return this.recordState;
	}

	public void setRecordState(Integer recordState) {
		this.recordState = recordState;
	}

	public Timestamp getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Timestamp repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public Integer getIsState() {
		return isState;
	}

	public void setIsState(Integer isState) {
		this.isState = isState;
	}

	public int getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(int isTransfer) {
		this.isTransfer = isTransfer;
	}
	
}
