package com.jqg.pojo;

import java.io.Serializable;

/**
 * 账户金额统计
 * 
 * @author Administrator
 */
public class Moneycount implements Serializable {
	private Integer moneyCountId;// 主键
	private Uservip uservip;// 用户Id
	private Double availableMoney;// 可用金额
	private Double dueInMoney;// 待收金额
	private Double frozenMoney;// 冻结金额
	private Double totalMoney;// 账户总额
	private Double netEarnInterest;// 净赚利息
	private Double netPayInterest;// 净付利息
	private Double vipCertifiMoney;// vip会员认证费用
	private Double loanManageMoney;// 借款管理费
	private Double lateAndCollectMoney;// 逾期和催收费用
	private Double accuWithdrawalMoney;// 累计提现费用
	private Double accuBidReward;// 累计投标奖励
	private Double accuPayBidReward;// 累计支付发标奖励
	private Double accuPromoteReward;// 推广奖励
	private Double accuOfflineRechargeReward;// 线下充值奖励
	private Double accuContinueBidReward;// 回款续投奖励
	private Double accuRechargeFee;// 充值手续费
	private Double accuProfitAndLossMoney;// 累计盈亏
	private Double accuInvestMoney;// 投资金额
	private Double accuBorrowMoney;// 借款金额
	private Double accuRechargeMoney;// 充值总额
	private Double accuWidthdrawMoney;// 提现总额
	private Double accuPayCommission;// 支付佣金
	private Double collectInterestTotalFee;// 待收利息总额
	private Double payInterestTotalFee;// 待支付利息
	private String userName;
	private Double experienceMoney;// 体验金额

	private double collectTotalMoney;//待收总额

	public Moneycount() {
	}

	public Moneycount(Integer moneyCountId) {
		this.moneyCountId = moneyCountId;
	}

	public Moneycount(Integer moneyCountId, Uservip uservip,
			Double availableMoney, Double dueInMoney, Double frozenMoney,
			Double totalMoney, Double netEarnInterest, Double netPayInterest,
			Double vipCertifiMoney, Double loanManageMoney,
			Double lateAndCollectMoney, Double accuWithdrawalMoney,
			Double accuBidReward, Double accuPayBidReward,
			Double accuPromoteReward, Double accuOfflineRechargeReward,
			Double accuContinueBidReward, Double accuRechargeFee,
			Double accuProfitAndLossMoney, Double accuInvestMoney,
			Double accuBorrowMoney, Double accuRechargeMoney,
			Double accuWidthdrawMoney, Double accuPayCommission,
			Double collectInterestTotalFee, Double payInterestTotalFee,
			String userName, Double experienceMoney) {
		this.moneyCountId = moneyCountId;
		this.uservip = uservip;
		this.availableMoney = availableMoney;
		this.dueInMoney = dueInMoney;
		this.frozenMoney = frozenMoney;
		this.totalMoney = totalMoney;
		this.netEarnInterest = netEarnInterest;
		this.netPayInterest = netPayInterest;
		this.vipCertifiMoney = vipCertifiMoney;
		this.loanManageMoney = loanManageMoney;
		this.lateAndCollectMoney = lateAndCollectMoney;
		this.accuWithdrawalMoney = accuWithdrawalMoney;
		this.accuBidReward = accuBidReward;
		this.accuPayBidReward = accuPayBidReward;
		this.accuPromoteReward = accuPromoteReward;
		this.accuOfflineRechargeReward = accuOfflineRechargeReward;
		this.accuContinueBidReward = accuContinueBidReward;
		this.accuRechargeFee = accuRechargeFee;
		this.accuProfitAndLossMoney = accuProfitAndLossMoney;
		this.accuInvestMoney = accuInvestMoney;
		this.accuBorrowMoney = accuBorrowMoney;
		this.accuRechargeMoney = accuRechargeMoney;
		this.accuWidthdrawMoney = accuWidthdrawMoney;
		this.accuPayCommission = accuPayCommission;
		this.collectInterestTotalFee = collectInterestTotalFee;
		this.payInterestTotalFee = payInterestTotalFee;
		this.userName = userName;
		this.experienceMoney = experienceMoney;
	}

	public String getUserName() {
		return this.userName;
	}

	public double getCollectTotalMoney() {
		return collectTotalMoney;
	}

	public void setCollectTotalMoney(double collectTotalMoney) {
		this.collectTotalMoney = collectTotalMoney;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getMoneyCountId() {
		return this.moneyCountId;
	}

	public void setMoneyCountId(Integer moneyCountId) {
		this.moneyCountId = moneyCountId;
	}

	public Uservip getUservip() {
		return this.uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Double getAvailableMoney() {
		return this.availableMoney;
	}

	public void setAvailableMoney(Double availableMoney) {
		this.availableMoney = availableMoney;
	}

	public Double getDueInMoney() {
		return this.dueInMoney;
	}

	public void setDueInMoney(Double dueInMoney) {
		this.dueInMoney = dueInMoney;
	}

	public Double getFrozenMoney() {
		return this.frozenMoney;
	}

	public void setFrozenMoney(Double frozenMoney) {
		this.frozenMoney = frozenMoney;
	}

	public Double getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getNetEarnInterest() {
		return this.netEarnInterest;
	}

	public void setNetEarnInterest(Double netEarnInterest) {
		this.netEarnInterest = netEarnInterest;
	}

	public Double getNetPayInterest() {
		return this.netPayInterest;
	}

	public void setNetPayInterest(Double netPayInterest) {
		this.netPayInterest = netPayInterest;
	}

	public Double getVipCertifiMoney() {
		return this.vipCertifiMoney;
	}

	public void setVipCertifiMoney(Double vipCertifiMoney) {
		this.vipCertifiMoney = vipCertifiMoney;
	}

	public Double getLoanManageMoney() {
		return this.loanManageMoney;
	}

	public void setLoanManageMoney(Double loanManageMoney) {
		this.loanManageMoney = loanManageMoney;
	}

	public Double getLateAndCollectMoney() {
		return this.lateAndCollectMoney;
	}

	public void setLateAndCollectMoney(Double lateAndCollectMoney) {
		this.lateAndCollectMoney = lateAndCollectMoney;
	}

	public Double getAccuWithdrawalMoney() {
		return this.accuWithdrawalMoney;
	}

	public void setAccuWithdrawalMoney(Double accuWithdrawalMoney) {
		this.accuWithdrawalMoney = accuWithdrawalMoney;
	}

	public Double getAccuBidReward() {
		return this.accuBidReward;
	}

	public void setAccuBidReward(Double accuBidReward) {
		this.accuBidReward = accuBidReward;
	}

	public Double getAccuPayBidReward() {
		return this.accuPayBidReward;
	}

	public void setAccuPayBidReward(Double accuPayBidReward) {
		this.accuPayBidReward = accuPayBidReward;
	}

	public Double getAccuPromoteReward() {
		return this.accuPromoteReward;
	}

	public void setAccuPromoteReward(Double accuPromoteReward) {
		this.accuPromoteReward = accuPromoteReward;
	}

	public Double getAccuOfflineRechargeReward() {
		return this.accuOfflineRechargeReward;
	}

	public void setAccuOfflineRechargeReward(Double accuOfflineRechargeReward) {
		this.accuOfflineRechargeReward = accuOfflineRechargeReward;
	}

	public Double getAccuContinueBidReward() {
		return this.accuContinueBidReward;
	}

	public void setAccuContinueBidReward(Double accuContinueBidReward) {
		this.accuContinueBidReward = accuContinueBidReward;
	}

	public Double getAccuRechargeFee() {
		return this.accuRechargeFee;
	}

	public void setAccuRechargeFee(Double accuRechargeFee) {
		this.accuRechargeFee = accuRechargeFee;
	}

	public Double getAccuProfitAndLossMoney() {
		return this.accuProfitAndLossMoney;
	}

	public void setAccuProfitAndLossMoney(Double accuProfitAndLossMoney) {
		this.accuProfitAndLossMoney = accuProfitAndLossMoney;
	}

	public Double getAccuInvestMoney() {
		return this.accuInvestMoney;
	}

	public void setAccuInvestMoney(Double accuInvestMoney) {
		this.accuInvestMoney = accuInvestMoney;
	}

	public Double getAccuBorrowMoney() {
		return this.accuBorrowMoney;
	}

	public void setAccuBorrowMoney(Double accuBorrowMoney) {
		this.accuBorrowMoney = accuBorrowMoney;
	}

	public Double getAccuRechargeMoney() {
		return this.accuRechargeMoney;
	}

	public void setAccuRechargeMoney(Double accuRechargeMoney) {
		this.accuRechargeMoney = accuRechargeMoney;
	}

	public Double getAccuWidthdrawMoney() {
		return this.accuWidthdrawMoney;
	}

	public void setAccuWidthdrawMoney(Double accuWidthdrawMoney) {
		this.accuWidthdrawMoney = accuWidthdrawMoney;
	}

	public Double getAccuPayCommission() {
		return this.accuPayCommission;
	}

	public void setAccuPayCommission(Double accuPayCommission) {
		this.accuPayCommission = accuPayCommission;
	}

	public Double getCollectInterestTotalFee() {
		return this.collectInterestTotalFee;
	}

	public void setCollectInterestTotalFee(Double collectInterestTotalFee) {
		this.collectInterestTotalFee = collectInterestTotalFee;
	}

	public Double getPayInterestTotalFee() {
		return this.payInterestTotalFee;
	}

	public void setPayInterestTotalFee(Double payInterestTotalFee) {
		this.payInterestTotalFee = payInterestTotalFee;
	}

	public Double getExperienceMoney() {
		return experienceMoney;
	}

	public void setExperienceMoney(Double experienceMoney) {
		this.experienceMoney = experienceMoney;
	}
}
