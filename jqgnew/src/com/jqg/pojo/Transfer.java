package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Transfer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2585487610493368485L;
	private Integer transferId;
	private Double money;//转让金额
	private Tender tender1;//转让人的投资记录id
	private Uservip buyUser;//受让人的投资记录id
	private Integer isTransfer;//是否转让
	private Timestamp transferTime;//转让时间
	private double profit;
	private Timestamp buyTime;//转让时间
	private Timestamp checkTime;//审核时间
	private Timestamp cancelTime;//撤消时间
	private int cancelNum;//撤消次数
	private String explains;//审核 说明



	public Transfer() {
	}

	public Transfer(Integer transferId) {
		this.transferId = transferId;
	}

	public Transfer(Integer transferId, Double money, Tender tender1,
			Uservip uservip, Integer isTransfer,Timestamp transferTime,Timestamp buyTime,
			Timestamp checkTime,Timestamp cancelTime,int cancelNum,String explains){
		this.transferId = transferId;
		this.money = money;
		this.tender1 = tender1;
		this.buyUser = uservip;
		this.isTransfer = isTransfer;
		this.transferTime = transferTime;
		this.buyTime = buyTime;
		this.checkTime = checkTime;
		this.cancelTime= cancelTime;
		this.cancelNum = cancelNum;
		this.explains = explains;
	}
	
	
	

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public Integer getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Tender getTender1() {
		return tender1;
	}

	public void setTender1(Tender tender1) {
		this.tender1 = tender1;
	}

	public Uservip getBuyUser() {
		return buyUser;
	}

	public void setBuyUser(Uservip buyUser) {
		this.buyUser = buyUser;
	}

	public Integer getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(Integer isTransfer) {
		this.isTransfer = isTransfer;
	}

	public Timestamp getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Timestamp transferTime) {
		this.transferTime = transferTime;
	}

	public Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Timestamp buyTime) {
		this.buyTime = buyTime;
	}

	public Timestamp getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Timestamp checkTime) {
		this.checkTime = checkTime;
	}

	public Timestamp getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Timestamp cancelTime) {
		this.cancelTime = cancelTime;
	}

	public int getCancelNum() {
		return cancelNum;
	}

	public void setCancelNum(int cancelNum) {
		this.cancelNum = cancelNum;
	}

	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}
	

	

}
