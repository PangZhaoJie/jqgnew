package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Lssuing implements Serializable {
	private Integer lssuingId;
	private LssuingComp lssuingComp;
	private Manager manager;
	private Periodtime periodtime;
	private Moneyuse moneyuse;
	private Moneymax moneymax;
	private Validtime validtime;
	private Periodday periodday;
	private Uservip uservip;
	private Moneymin moneymin;
	private Returnway returnway;
	private Timestamp borrowTime;
	private String lssuingNum;
	private Integer lssuingType; //标的类型（担保标、抵押标。。。）
	private String borrowMoney;
	private Double rate;
	private Double returnMoney;
	private Integer borrowPeriod;
	private Integer isAward;
	private String awardRate;
	private String awardMoney;
	private Integer tenderLimit;
	private String moneyLimit;
	private String title;
	private Integer isOrient;
	private String orientPassword;
	private String explains;
	private Integer state;
	private int repayState;
	private Set tenderwordses = new HashSet(0);
	private Set<Tender> tenders = new HashSet(0);
	private Set lssingphotos = new HashSet(0);
	private Set repaynotes = new HashSet(0);

	private Timestamp everyReturnTime;
	private String explainOne;
	private String explianTwo;
	private Timestamp dealTime;
	private int isInvest;
	private Double manageMoney;
	private Timestamp verify_time;
	private Integer totalUnit;
	private Double unitmoney;
	private Double award;
	private Integer recommend;
	private boolean validtimestate;
	
	private Integer copies;

	private Double scale;
	private Integer scales;

	private Double rates;

	

	public Integer getScales() {
		return scales;
	}

	public void setScales(Integer scales) {
		this.scales = scales;
	}

	public boolean isValidtimestate() {
		return validtimestate;
	}

	public void setValidtimestate(boolean validtimestate) {
		this.validtimestate = validtimestate;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public Timestamp getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Timestamp verify_time) {
		this.verify_time = verify_time;
	}

	public Lssuing() {
	}

	public Lssuing(Integer lssuingId) {
		this.lssuingId = lssuingId;
	}

	public Lssuing(Integer lssuingId, Manager manager, Periodtime periodtime,
			Moneyuse moneyuse, Moneymax moneymax, Validtime validtime,
			Periodday periodday, Uservip uservip, Moneymin moneymin,
			Returnway returnway, Timestamp borrowTime, String lssuingNum,
			Integer lssuingType, String borrowMoney, Double rate,
			Integer borrowPeriod, Integer isAward, String awardRate,
			String awardMoney, Integer tenderLimit, String moneyLimit,
			String title, Integer isOrient, String orientPassword,
			String explains, Integer state, Integer repayState,
			Double returnMoney, Timestamp everyReturnTime, String explainOne,
			String explianTwo, Timestamp dealTime, Set tenderwordses,
			Set tenders, Set repaynotes, Set lssingphotos, int isInvest,
			Double manageMoney) {
		this.lssuingId = lssuingId;
		this.manager = manager;
		this.periodtime = periodtime;
		this.moneyuse = moneyuse;
		this.moneymax = moneymax;
		this.validtime = validtime;
		this.periodday = periodday;
		this.uservip = uservip;
		this.moneymin = moneymin;
		this.returnway = returnway;
		this.borrowTime = borrowTime;
		this.lssuingNum = lssuingNum;
		this.lssuingType = lssuingType;
		this.borrowMoney = borrowMoney;
		this.rate = rate;
		this.borrowPeriod = borrowPeriod;
		this.isAward = isAward;
		this.awardRate = awardRate;
		this.awardMoney = awardMoney;
		this.tenderLimit = tenderLimit;
		this.moneyLimit = moneyLimit;
		this.title = title;
		this.isOrient = isOrient;
		this.orientPassword = orientPassword;
		this.explains = explains;
		this.state = state;
		this.repayState = repayState.intValue();
		this.returnMoney = returnMoney;
		this.everyReturnTime = everyReturnTime;
		this.explainOne = explainOne;
		this.explianTwo = explianTwo;
		this.dealTime = dealTime;
		this.tenderwordses = tenderwordses;
		this.tenders = tenders;
		this.repaynotes = repaynotes;
		this.lssingphotos = lssingphotos;
		this.isInvest = isInvest;
		this.manageMoney = manageMoney;
		
	}

	public Integer getLssuingId() {
		return this.lssuingId;
	}

	public Double getManageMoney() {
		return this.manageMoney;
	}

	public void setManageMoney(Double manageMoney) {
		this.manageMoney = manageMoney;
	}

	public int getIsInvest() {
		return this.isInvest;
	}

	public void setIsInvest(int isInvest) {
		this.isInvest = isInvest;
	}

	public void setRepayState(int repayState) {
		this.repayState = repayState;
	}

	public void setLssuingId(Integer lssuingId) {
		this.lssuingId = lssuingId;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Periodtime getPeriodtime() {
		return this.periodtime;
	}

	public void setPeriodtime(Periodtime periodtime) {
		this.periodtime = periodtime;
	}

	public Moneyuse getMoneyuse() {
		return this.moneyuse;
	}

	public void setMoneyuse(Moneyuse moneyuse) {
		this.moneyuse = moneyuse;
	}

	public Moneymax getMoneymax() {
		return this.moneymax;
	}

	public void setMoneymax(Moneymax moneymax) {
		this.moneymax = moneymax;
	}

	public Validtime getValidtime() {
		return this.validtime;
	}

	public void setValidtime(Validtime validtime) {
		this.validtime = validtime;
	}

	public Periodday getPeriodday() {
		return this.periodday;
	}

	public void setPeriodday(Periodday periodday) {
		this.periodday = periodday;
	}

	public Uservip getUservip() {
		return this.uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Moneymin getMoneymin() {
		return this.moneymin;
	}

	public void setMoneymin(Moneymin moneymin) {
		this.moneymin = moneymin;
	}

	public Returnway getReturnway() {
		return this.returnway;
	}

	public void setReturnway(Returnway returnway) {
		this.returnway = returnway;
	}

	public Timestamp getBorrowTime() {
		return this.borrowTime;
	}

	public void setBorrowTime(Timestamp borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getLssuingNum() {
		return this.lssuingNum;
	}

	public void setLssuingNum(String lssuingNum) {
		this.lssuingNum = lssuingNum;
	}

	public Integer getLssuingType() {
		return this.lssuingType;
	}

	public void setLssuingType(Integer lssuingType) {
		this.lssuingType = lssuingType;
	}

	public String getBorrowMoney() {
		return this.borrowMoney;
	}

	public void setBorrowMoney(String borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getBorrowPeriod() {
		return this.borrowPeriod;
	}

	public void setBorrowPeriod(Integer borrowPeriod) {
		this.borrowPeriod = borrowPeriod;
	}

	public Integer getIsAward() {
		return this.isAward;
	}

	public void setIsAward(Integer isAward) {
		this.isAward = isAward;
	}

	public String getAwardRate() {
		return this.awardRate;
	}

	public void setAwardRate(String awardRate) {
		this.awardRate = awardRate;
	}

	public String getAwardMoney() {
		return this.awardMoney;
	}

	public void setAwardMoney(String awardMoney) {
		this.awardMoney = awardMoney;
	}

	public Integer getTenderLimit() {
		return this.tenderLimit;
	}

	public void setTenderLimit(Integer tenderLimit) {
		this.tenderLimit = tenderLimit;
	}

	public String getMoneyLimit() {
		return this.moneyLimit;
	}

	public void setMoneyLimit(String moneyLimit) {
		this.moneyLimit = moneyLimit;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsOrient() {
		return this.isOrient;
	}

	public void setIsOrient(Integer isOrient) {
		this.isOrient = isOrient;
	}

	public String getOrientPassword() {
		return this.orientPassword;
	}

	public void setOrientPassword(String orientPassword) {
		this.orientPassword = orientPassword;
	}

	public String getExplains() {
		return this.explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRepayState() {
		return Integer.valueOf(this.repayState);
	}

	public void setRepayState(Integer repayState) {
		this.repayState = repayState.intValue();
	}

	public Double getReturnMoney() {
		return this.returnMoney;
	}

	public void setReturnMoney(Double returnMoney) {
		this.returnMoney = returnMoney;
	}

	public Timestamp getEveryReturnTime() {
		return this.everyReturnTime;
	}

	public void setEveryReturnTime(Timestamp everyReturnTime) {
		this.everyReturnTime = everyReturnTime;
	}

	public String getExplainOne() {
		return this.explainOne;
	}

	public void setExplainOne(String explainOne) {
		this.explainOne = explainOne;
	}

	public String getExplianTwo() {
		return this.explianTwo;
	}

	public void setExplianTwo(String explianTwo) {
		this.explianTwo = explianTwo;
	}

	public Timestamp getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Timestamp dealTime) {
		this.dealTime = dealTime;
	}

	public Set getTenderwordses() {
		return this.tenderwordses;
	}

	public void setTenderwordses(Set tenderwordses) {
		this.tenderwordses = tenderwordses;
	}

	public Set getTenders() {
		return this.tenders;
	}

	public void setTenders(Set tenders) {
		this.tenders = tenders;
	}

	public Set getRepaynotes() {
		return this.repaynotes;
	}

	public void setRepaynotes(Set repaynotes) {
		this.repaynotes = repaynotes;
	}

	public Set getLssingphotos() {
		return this.lssingphotos;
	}

	public void setLssingphotos(Set lssingphotos) {
		this.lssingphotos = lssingphotos;
	}

	public Double getRates() {
		return rates;
	}

	public void setRates(Double rates) {
		this.rates = rates;
	}

	public LssuingComp getLssuingComp() {
		return lssuingComp;
	}

	public void setLssuingComp(LssuingComp lssuingComp) {
		this.lssuingComp = lssuingComp;
	}

	public Integer getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(Integer totalUnit) {
		this.totalUnit = totalUnit;
	}

	public Double getUnitmoney() {
		return unitmoney;
	}

	public void setUnitmoney(Double unitmoney) {
		this.unitmoney = unitmoney;
	}

	public Double getAward() {
		return award;
	}

	public void setAward(Double award) {
		this.award = award;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getCopies() {
		return copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	

}
