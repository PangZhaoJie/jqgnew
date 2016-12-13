package com.jqg.pojo;

import java.io.Serializable;

public class Onlinemodel implements Serializable {
	private Integer onlineId;
	private String payCompany;
	private Integer enable;
	private Double payPoundage;
	private String accountNumber;
	private String accountPassword;
	private String other;
	private String url1;
	private String url2;
	private String url3;
	private String InterfaceVersion;
	private int KeyType;
	private String bussIdB2B;
	
	public Onlinemodel() {
	}

	public Onlinemodel(Integer onlineId) {
		this.onlineId = onlineId;
	}

	public Onlinemodel(Integer onlineId, String payCompany, Integer enable,
			Double payPoundage, String accountNumber, String accountPassword,
			String other, String url1, String url2, String url3) {
		this.onlineId = onlineId;
		this.payCompany = payCompany;
		this.enable = enable;
		this.payPoundage = payPoundage;
		this.accountNumber = accountNumber;
		this.accountPassword = accountPassword;
		this.other = other;
		this.url1 = url1;
		this.url2 = url2;
		this.url3 = url3;
	}

	public Integer getOnlineId() {
		return this.onlineId;
	}

	public String getUrl1() {
		return this.url1;
	}

	public void setUrl1(String url1) {
		this.url1 = url1;
	}

	public String getUrl2() {
		return this.url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public String getUrl3() {
		return this.url3;
	}

	public void setUrl3(String url3) {
		this.url3 = url3;
	}

	public void setOnlineId(Integer onlineId) {
		this.onlineId = onlineId;
	}

	public String getPayCompany() {
		return this.payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Double getPayPoundage() {
		return this.payPoundage;
	}

	public void setPayPoundage(Double payPoundage) {
		this.payPoundage = payPoundage;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountPassword() {
		return this.accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getBussIdB2B() {
		return bussIdB2B;
	}

	public void setBussIdB2B(String bussIdB2B) {
		this.bussIdB2B = bussIdB2B;
	}

}
