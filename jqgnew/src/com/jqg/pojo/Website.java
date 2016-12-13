package com.jqg.pojo;

import java.io.Serializable;

public class Website implements Serializable {
	private Integer websiteId;
	private String webName;
	private String homeTitle;
	private String webKeyword;
	private String webDeescription;
	private String webfoot;
	private String beoverdueCost;
	private String withdrawals;
	private String backAward;
	private Integer lineReward;
	private Integer investIntegral;
	private Integer borrowIntegral;
	private Double investment;
	private Double accrual;
	private Double loan;
	private Integer translateLimit;
	private String line;
	private Double profit;
	private Double income;
	private Double expond;

	private String trustAccount;
	private String publicKey;
	private String privateKey;

	public Website() {
	}

	public Website(String webName, String homeTitle, String webKeyword,
			String webDeescription, String webfoot, String beoverdueCost,
			Integer withdrawals, String backAward, Integer lineReward,
			Integer investIntegral, Integer borrowIntegral, Double investment,
			Double loan, Integer translateLimit, String line, Double accrual) {
		this.webName = webName;
		this.homeTitle = homeTitle;
		this.webKeyword = webKeyword;
		this.webDeescription = webDeescription;
		this.webfoot = webfoot;
		this.beoverdueCost = beoverdueCost;
		this.backAward = backAward;
		this.lineReward = lineReward;
		this.investIntegral = investIntegral;
		this.borrowIntegral = borrowIntegral;
		this.investment = investment;
		this.loan = loan;
		this.translateLimit = translateLimit;
		this.line = line;
		this.accrual = accrual;
	}

	public Integer getWebsiteId() {
		return this.websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public String getWebName() {
		return this.webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getHomeTitle() {
		return this.homeTitle;
	}

	public void setHomeTitle(String homeTitle) {
		this.homeTitle = homeTitle;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getExpond() {
		return expond;
	}

	public void setExpond(Double expond) {
		this.expond = expond;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public String getWebKeyword() {
		return this.webKeyword;
	}

	public void setWebKeyword(String webKeyword) {
		this.webKeyword = webKeyword;
	}

	public String getWebDeescription() {
		return this.webDeescription;
	}

	public void setWebDeescription(String webDeescription) {
		this.webDeescription = webDeescription;
	}

	public String getWebfoot() {
		return this.webfoot;
	}

	public void setWebfoot(String webfoot) {
		this.webfoot = webfoot;
	}

	public String getBeoverdueCost() {
		return this.beoverdueCost;
	}

	public void setBeoverdueCost(String beoverdueCost) {
		this.beoverdueCost = beoverdueCost;
	}

	public String getWithdrawals() {
		return this.withdrawals;
	}

	public void setWithdrawals(String withdrawals) {
		this.withdrawals = withdrawals;
	}

	public String getBackAward() {
		return this.backAward;
	}

	public void setBackAward(String backAward) {
		this.backAward = backAward;
	}

	public Integer getLineReward() {
		return this.lineReward;
	}

	public void setLineReward(Integer lineReward) {
		this.lineReward = lineReward;
	}

	public Integer getInvestIntegral() {
		return this.investIntegral;
	}

	public void setInvestIntegral(Integer investIntegral) {
		this.investIntegral = investIntegral;
	}

	public Integer getBorrowIntegral() {
		return this.borrowIntegral;
	}

	public void setBorrowIntegral(Integer borrowIntegral) {
		this.borrowIntegral = borrowIntegral;
	}

	public Double getInvestment() {
		return this.investment;
	}

	public void setInvestment(Double investment) {
		this.investment = investment;
	}

	public Double getLoan() {
		return this.loan;
	}

	public void setLoan(Double loan) {
		this.loan = loan;
	}

	public Integer getTranslateLimit() {
		return this.translateLimit;
	}

	public void setTranslateLimit(Integer translateLimit) {
		this.translateLimit = translateLimit;
	}

	public String getLine() {
		return this.line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Double getAccrual() {
		return accrual;
	}

	public void setAccrual(Double accrual) {
		this.accrual = accrual;
	}

	public String getTrustAccount() {
		return trustAccount;
	}

	public void setTrustAccount(String trustAccount) {
		this.trustAccount = trustAccount;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

}
