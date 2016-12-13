package com.jqg.pojo;

import java.io.Serializable;

/**
 * �˻����ͳ��
 * 
 * @author Administrator
 */
public class Moneycount implements Serializable {
	private Integer moneyCountId;// ����
	private Uservip uservip;// �û�Id
	private Double availableMoney;// ���ý��
	private Double dueInMoney;// ���ս��
	private Double frozenMoney;// ������
	private Double totalMoney;// �˻��ܶ�
	private Double netEarnInterest;// ��׬��Ϣ
	private Double netPayInterest;// ������Ϣ
	private Double vipCertifiMoney;// vip��Ա��֤����
	private Double loanManageMoney;// �������
	private Double lateAndCollectMoney;// ���ںʹ��շ���
	private Double accuWithdrawalMoney;// �ۼ����ַ���
	private Double accuBidReward;// �ۼ�Ͷ�꽱��
	private Double accuPayBidReward;// �ۼ�֧�����꽱��
	private Double accuPromoteReward;// �ƹ㽱��
	private Double accuOfflineRechargeReward;// ���³�ֵ����
	private Double accuContinueBidReward;// �ؿ���Ͷ����
	private Double accuRechargeFee;// ��ֵ������
	private Double accuProfitAndLossMoney;// �ۼ�ӯ��
	private Double accuInvestMoney;// Ͷ�ʽ��
	private Double accuBorrowMoney;// �����
	private Double accuRechargeMoney;// ��ֵ�ܶ�
	private Double accuWidthdrawMoney;// �����ܶ�
	private Double accuPayCommission;// ֧��Ӷ��
	private Double collectInterestTotalFee;// ������Ϣ�ܶ�
	private Double payInterestTotalFee;// ��֧����Ϣ
	private String userName;
	private Double experienceMoney;// ������

	private double collectTotalMoney;//�����ܶ�

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
