package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Translate implements Serializable {
	private Integer translateId;
	private Uservip uservip;
	private Timestamp occurTime;
	private String bankNum;// 所属银行+开户行+银行帐号
	private Double affectMoney;
	private Double availableBalance;
	private Integer state;
	private String texplain;
	private String dealUser;
	private Timestamp dealTime;
	private Double fee;
	private String bankNums;
	
	
	private int banktypeId;
	private int bankProvinceId;
	private int bankCityId;
	private String serialnum;

	public Translate() {
	}

	public Translate(Uservip uservip, Timestamp occurTime, String bankNum,
			Double affectMoney, Double availableBalance, Integer state,
			String texplain, String dealUser, Timestamp dealTime, Double fee) {
		this.uservip = uservip;
		this.occurTime = occurTime;
		this.bankNum = bankNum;
		this.affectMoney = affectMoney;
		this.availableBalance = availableBalance;
		this.state = state;
		this.dealUser = dealUser;
		this.dealTime = dealTime;
		this.fee = fee;
		this.texplain = texplain;
		setTexplain(texplain);
	}

	public Integer getTranslateId() {
		return this.translateId;
	}

	public void setTranslateId(Integer translateId) {
		this.translateId = translateId;
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

	public String getBankNum() {
		return this.bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
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

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTexplain() {
		return this.texplain;
	}

	public void setTexplain(String texplain) {
		this.texplain = texplain;
	}

	public String getDealUser() {
		return this.dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public Timestamp getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getBankNums() {
		return bankNums;
	}

	public void setBankNums(String bankNums) {
		this.bankNums = bankNums;
	}

	public int getBanktypeId() {
		return banktypeId;
	}

	public void setBanktypeId(int banktypeId) {
		this.banktypeId = banktypeId;
	}

	public int getBankProvinceId() {
		return bankProvinceId;
	}

	public void setBankProvinceId(int bankProvinceId) {
		this.bankProvinceId = bankProvinceId;
	}

	public int getBankCityId() {
		return bankCityId;
	}

	public void setBankCityId(int bankCityId) {
		this.bankCityId = bankCityId;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String serialnum) {
		this.serialnum = serialnum;
	}
	
}
