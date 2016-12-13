package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Tender implements Serializable {
	private Integer tenderId;
	private Lssuing lssuing;
	private Uservip uservip;
	private Integer money;
	private Timestamp tenderTime;
	private Set records = new HashSet(0);
	private int state;
	private int buynum;
	private int transfer;//是否转让标识
	private String serialnum;
	private int transferAudit ;
	private Set<Transfer> transfer1 = new HashSet(0);
	private Set<Transfer> transfer2 = new HashSet(0);
	
	public Tender() {
		this.state = 0;
		this.transferAudit=0;
	}

	public Tender(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public Tender(Integer tenderId, Lssuing lssuing, Uservip uservip,
			Integer money, Timestamp tenderTime, Set records,int transfer) {
		this.tenderId = tenderId;
		this.lssuing = lssuing;
		this.uservip = uservip;
		this.money = money;
		this.tenderTime = tenderTime;
		this.records = records;
		this.transfer = transfer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getBuynum() {
		return buynum;
	}

	public void setBuynum(int buynum) {
		this.buynum = buynum;
	}

	public Integer getTenderId() {
		return this.tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
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

	public Integer getMoney() {
		return this.money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public Timestamp getTenderTime() {
		return this.tenderTime;
	}

	public void setTenderTime(Timestamp tenderTime) {
		this.tenderTime = tenderTime;
	}

	public Set getRecords() {
		return this.records;
	}

	public void setRecords(Set records) {
		this.records = records;
	}

	public int getTransfer() {
		return transfer;
	}

	public void setTransfer(int transfer) {
		this.transfer = transfer;
	}

	public Set<Transfer> getTransfer1() {
		return transfer1;
	}

	public void setTransfer1(Set<Transfer> transfer1) {
		this.transfer1 = transfer1;
	}

	public Set<Transfer> getTransfer2() {
		return transfer2;
	}

	public void setTransfer2(Set<Transfer> transfer2) {
		this.transfer2 = transfer2;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}

	public int getTransferAudit() {
		return transferAudit;
	}

	public void setTransferAudit(int transferAudit) {
		this.transferAudit = transferAudit;
	}
	
}
