package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Uservip implements Serializable {
	private Integer userId;
	private Manager manager;
	private Uservip uservip;
	private Creditlevel creditlevel;
	private Investmentlevel investmentlevel;
	private String userName;
	private String password;
	private String mail;
	private Integer enable;
	private Integer userType;
	private Timestamp registerDate;
	private String photoOne;
	private String photoTwo;
	private String photoThree;
	private String status;
	private Integer creditLevelId;
	private Integer investmentLevelId;
	private Integer questionresult;
	private Integer quota;
	private Integer itegral;
	private Double investItegral;
	private String payPwd;
	private Timestamp videoTime;
	private String videoResult;
	private Timestamp sceneTime;
	private String sceneResult;
	private String nameResult;
	private String phoneResult;
	private String realName;
	private Integer isApplyVIP;
	private Integer isVIP;
	private Integer vipMonthe;
	private Timestamp vipEndTime;
	private int trustStatus;
	private String trustAccount;
	private int authorizeStatus;
	private Set questionsets = new HashSet(0);
	private Set moneyhistorydetails = new HashSet(0);
	private Set uservipnotes = new HashSet(0);
	private Set bonuses = new HashSet(0);
	private Set inboxes = new HashSet(0);
	private Set integraldetails = new HashSet(0);
	private Set personalbankinfors = new HashSet(0);
	private Set requestquotas = new HashSet(0);
	private Set tenderwordses = new HashSet(0);
	private Set uservips = new HashSet(0);
	private Set automaticbids = new HashSet(0);
	private Set matercertifis = new HashSet(0);
	private Set tenders = new HashSet(0);
	private Set offlinerecharges = new HashSet(0);
	private Set usercomments = new HashSet(0);
	private Set moneycounts = new HashSet(0);
	private Set certifications = new HashSet(0);
	private Set basicinfors = new HashSet(0);
	private Set lssuings = new HashSet(0);
	private Set investstrategies = new HashSet(0);
	private Set translates = new HashSet(0);
	private Set records = new HashSet(0);
	private Set userLog = new HashSet(0);
	private String userNames;

	public Uservip() {
	}

	public Uservip(Integer userId) {
		this.userId = userId;
	}

	public Uservip(Integer userId, Manager manager, Uservip uservip,
			Creditlevel creditlevel, Investmentlevel investmentlevel,
			String userName, String password, String mail, Integer enable,
			Integer userType, Timestamp registerDate, String photoOne,
			String photoTwo, String photoThree, String status,
			Integer creditLevelId, Integer investmentLevelId,
			Integer questionresult, Integer quota, Integer itegral,
			Set questionsets, Set moneyhistorydetails, Set uservipnotes,
			Set bonuses, Set inboxes, Set integraldetails,
			Set personalbankinfors, Set requestquotas, Set tenderwordses,
			Set uservips, Set automaticbids, Set matercertifis, Set tenders,
			Set offlinerecharges, Set usercomments, Set moneycounts,
			Set translates, Set certifications, Set basicinfors, Set lssuings,
			Set records, Set investstrategies, String payPwd,
			Timestamp videoTime, String videoResult, Timestamp sceneTime,
			String sceneResult, String nameResult, Double investItegral,
			String phoneResult, String realName,Integer isApplyVIP, Integer isVIP,Integer vipMonthe,Timestamp vipEndTime) {
		this.userId = userId;
		this.manager = manager;
		this.uservip = uservip;
		this.creditlevel = creditlevel;
		this.investmentlevel = investmentlevel;
		this.userName = userName;
		this.password = password;
		this.mail = mail;
		this.enable = enable;
		this.userType = userType;
		this.registerDate = registerDate;
		this.photoOne = photoOne;
		this.photoTwo = photoTwo;
		this.photoThree = photoThree;
		this.status = status;
		this.creditLevelId = creditLevelId;
		this.investmentLevelId = investmentLevelId;
		this.questionresult = questionresult;
		this.quota = quota;
		this.itegral = itegral;
		this.questionsets = questionsets;
		this.moneyhistorydetails = moneyhistorydetails;
		this.uservipnotes = uservipnotes;
		this.bonuses = bonuses;
		this.inboxes = inboxes;
		this.integraldetails = integraldetails;
		this.personalbankinfors = personalbankinfors;
		this.requestquotas = requestquotas;
		this.tenderwordses = tenderwordses;
		this.uservips = uservips;
		this.automaticbids = automaticbids;
		this.matercertifis = matercertifis;
		this.tenders = tenders;
		this.offlinerecharges = offlinerecharges;
		this.usercomments = usercomments;
		this.moneycounts = moneycounts;
		this.certifications = certifications;
		this.basicinfors = basicinfors;
		this.lssuings = lssuings;
		setTranslates(translates);
		this.investstrategies = investstrategies;
		this.payPwd = payPwd;
		this.videoTime = videoTime;
		this.videoResult = videoResult;
		this.sceneTime = sceneTime;
		this.sceneResult = sceneResult;
		this.nameResult = nameResult;
		this.investItegral = investItegral;
		this.records = records;
		this.isApplyVIP=isApplyVIP;
		this.isVIP = isVIP;
		this.vipMonthe=vipMonthe;
		this.vipEndTime=vipEndTime;
		this.phoneResult = phoneResult;
		this.realName = realName;
	}

	public String getPayPwd() {
		return this.payPwd;
	}

	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
	}

	public Timestamp getVideoTime() {
		return this.videoTime;
	}

	public void setVideoTime(Timestamp videoTime) {
		this.videoTime = videoTime;
	}

	public String getVideoResult() {
		return this.videoResult;
	}

	public void setVideoResult(String videoResult) {
		this.videoResult = videoResult;
	}

	public Timestamp getSceneTime() {
		return this.sceneTime;
	}

	public void setSceneTime(Timestamp sceneTime) {
		this.sceneTime = sceneTime;
	}

	public String getSceneResult() {
		return this.sceneResult;
	}

	public void setSceneResult(String sceneResult) {
		this.sceneResult = sceneResult;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Uservip getUservip() {
		return this.uservip;
	}

	public void setUservip(Uservip uservip) {
		this.uservip = uservip;
	}

	public Creditlevel getCreditlevel() {
		return this.creditlevel;
	}

	public void setCreditlevel(Creditlevel creditlevel) {
		this.creditlevel = creditlevel;
	}

	public Investmentlevel getInvestmentlevel() {
		return this.investmentlevel;
	}

	public void setInvestmentlevel(Investmentlevel investmentlevel) {
		this.investmentlevel = investmentlevel;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Timestamp getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getPhotoOne() {
		return this.photoOne;
	}

	public void setPhotoOne(String photoOne) {
		this.photoOne = photoOne;
	}

	public String getPhotoTwo() {
		return this.photoTwo;
	}

	public void setPhotoTwo(String photoTwo) {
		this.photoTwo = photoTwo;
	}

	public String getPhotoThree() {
		return this.photoThree;
	}

	public void setPhotoThree(String photoThree) {
		this.photoThree = photoThree;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCreditLevelId() {
		return this.creditLevelId;
	}

	public void setCreditLevelId(Integer creditLevelId) {
		this.creditLevelId = creditLevelId;
	}

	public Integer getInvestmentLevelId() {
		return this.investmentLevelId;
	}

	public void setInvestmentLevelId(Integer investmentLevelId) {
		this.investmentLevelId = investmentLevelId;
	}

	public Integer getQuestionresult() {
		return this.questionresult;
	}

	public void setQuestionresult(Integer questionresult) {
		this.questionresult = questionresult;
	}

	public Integer getQuota() {
		return this.quota;
	}

	public void setQuota(Integer quota) {
		this.quota = quota;
	}

	public Integer getItegral() {
		return this.itegral;
	}

	public void setItegral(Integer itegral) {
		this.itegral = itegral;
	}

	public Set getQuestionsets() {
		return this.questionsets;
	}

	public void setQuestionsets(Set questionsets) {
		this.questionsets = questionsets;
	}

	public Set getMoneyhistorydetails() {
		return this.moneyhistorydetails;
	}

	public void setMoneyhistorydetails(Set moneyhistorydetails) {
		this.moneyhistorydetails = moneyhistorydetails;
	}

	public Set getUservipnotes() {
		return this.uservipnotes;
	}

	public void setUservipnotes(Set uservipnotes) {
		this.uservipnotes = uservipnotes;
	}

	public Set getBonuses() {
		return this.bonuses;
	}

	public void setBonuses(Set bonuses) {
		this.bonuses = bonuses;
	}

	public Set getInboxes() {
		return this.inboxes;
	}

	public void setInboxes(Set inboxes) {
		this.inboxes = inboxes;
	}

	public Set getIntegraldetails() {
		return this.integraldetails;
	}

	public void setIntegraldetails(Set integraldetails) {
		this.integraldetails = integraldetails;
	}

	public Set getPersonalbankinfors() {
		return this.personalbankinfors;
	}

	public void setPersonalbankinfors(Set personalbankinfors) {
		this.personalbankinfors = personalbankinfors;
	}

	public Set getRequestquotas() {
		return this.requestquotas;
	}

	public void setRequestquotas(Set requestquotas) {
		this.requestquotas = requestquotas;
	}

	public Set getTenderwordses() {
		return this.tenderwordses;
	}

	public void setTenderwordses(Set tenderwordses) {
		this.tenderwordses = tenderwordses;
	}

	public Set getUservips() {
		return this.uservips;
	}

	public void setUservips(Set uservips) {
		this.uservips = uservips;
	}

	public Set getAutomaticbids() {
		return this.automaticbids;
	}

	public void setAutomaticbids(Set automaticbids) {
		this.automaticbids = automaticbids;
	}

	public Set getMatercertifis() {
		return this.matercertifis;
	}

	public void setMatercertifis(Set matercertifis) {
		this.matercertifis = matercertifis;
	}

	public Set getTenders() {
		return this.tenders;
	}

	public void setTenders(Set tenders) {
		this.tenders = tenders;
	}

	public Set getOfflinerecharges() {
		return this.offlinerecharges;
	}

	public void setOfflinerecharges(Set offlinerecharges) {
		this.offlinerecharges = offlinerecharges;
	}

	public Set getUsercomments() {
		return this.usercomments;
	}

	public void setUsercomments(Set usercomments) {
		this.usercomments = usercomments;
	}

	public Set getMoneycounts() {
		return this.moneycounts;
	}

	public void setMoneycounts(Set moneycounts) {
		this.moneycounts = moneycounts;
	}

	public Set getCertifications() {
		return this.certifications;
	}

	public void setCertifications(Set certifications) {
		this.certifications = certifications;
	}

	public Set getBasicinfors() {
		return this.basicinfors;
	}

	public void setBasicinfors(Set basicinfors) {
		this.basicinfors = basicinfors;
	}

	public Set getLssuings() {
		return this.lssuings;
	}

	public void setLssuings(Set lssuings) {
		this.lssuings = lssuings;
	}

	public Set getInveststrategies() {
		return this.investstrategies;
	}

	public void setInveststrategies(Set investstrategies) {
		this.investstrategies = investstrategies;
	}

	public String getNameResult() {
		return this.nameResult;
	}

	public void setNameResult(String nameResult) {
		this.nameResult = nameResult;
	}

	public Double getInvestItegral() {
		return investItegral;
	}

	public void setInvestItegral(Double investItegral) {
		this.investItegral = investItegral;
	}

	public String getPhoneResult() {
		return this.phoneResult;
	}

	public void setPhoneResult(String phoneResult) {
		this.phoneResult = phoneResult;
	}

	public Set getTranslates() {
		return this.translates;
	}

	public void setTranslates(Set translates) {
		this.translates = translates;
	}

	public Set getRecords() {
		return this.records;
	}

	public void setRecords(Set records) {
		this.records = records;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public Integer getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(Integer isVIP) {
		this.isVIP = isVIP;
	}

	public Integer getVipMonthe() {
		return vipMonthe;
	}

	public void setVipMonthe(Integer vipMonthe) {
		this.vipMonthe = vipMonthe;
	}

	public Integer getIsApplyVIP() {
		return isApplyVIP;
	}

	public void setIsApplyVIP(Integer isApplyVIP) {
		this.isApplyVIP = isApplyVIP;
	}

	public Timestamp getVipEndTime() {
		return vipEndTime;
	}

	public void setVipEndTime(Timestamp vipEndTime) {
		this.vipEndTime = vipEndTime;
	}

	public Set getUserLog() {
		return userLog;
	}

	public void setUserLog(Set userLog) {
		this.userLog = userLog;
	}

	public int getTrustStatus() {
		return trustStatus;
	}

	public void setTrustStatus(int trustStatus) {
		this.trustStatus = trustStatus;
	}

	public String getTrustAccount() {
		return trustAccount;
	}

	public void setTrustAccount(String trustAccount) {
		this.trustAccount = trustAccount;
	}

	public int getAuthorizeStatus() {
		return authorizeStatus;
	}

	public void setAuthorizeStatus(int authorizeStatus) {
		this.authorizeStatus = authorizeStatus;
	}
	




}
