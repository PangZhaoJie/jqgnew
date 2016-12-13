package com.jqg.struts;

import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Uservip;
import com.jqg.service.BasicinforService;
import com.jqg.service.UservipService;
import com.jqg.service.impl.BasicinforServiceImpl;
import com.jqg.service.impl.UservipServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

public class BasicinforAction extends BaseAction
{
  private Basicinfor basicinfor;
  private Integer basicInforId;
  private String realName;
  private String idNum;
  private String phoneNum;
  private Integer age;
  private String birthPlace;
  private String currentLiveCity;
  private String currentLiveCity1;
  private String currentLiveCity2;
  private String currentLiveCity3;
  private String sex;
  private String marryStatus;
  private String highestEdu;
  private String monthIncome;
  private String job;
  private String personalProfile;
  private String currentAddress;
  private String homePhone;
  private String linkmanOne;
  private String relationOne;
  private String phoneOne;
  private String otherOne;
  private String linkmanTwo;
  private String relationTwo;
  private String phoneTwo;
  private String otherTwo;
  private String linkmanThree;
  private String relationThree;
  private String phoneThree;
  private String otherThree;
  private String companyName;
  private String companyPhone;
  private String companyAddress;
  private String workYear;
  private String reference;
  private String referPhone;
  private String monthAverEarn;
  private String incomeDescribe;
  private String monthAverPay;
  private String payDescribe;
  private String houseCondition;
  private String houseValue;
  private String ifByCar;
  private String carValue;
  private String joinCompanyName;
  private String joinCompanyResource;
  private String otherResourceDescribe;
  private String houseAddress;
  private String buildingArea;
  private String buildingYear;
  private String contributionStatus;
  private String ownerOne;
  private String ownerOneShare;
  private String ownerTwo;
  private String ownerTwoShare;
  private String loanYear;
  private Double monthCont;
  private Double oweLoanBala;
  private String mortgageContact;
  private String cosignerOne;
  private String cosignerOneRelation;
  private String cosignerOnePhone;
  private String cosignerTwo;
  private String cosignerTwoRelation;
  private String cosignerTwoPhone;
  private String cosignerOneCard;
  private String cosignerTwoCard;
  private String result;
  private Integer userId;
  private String mark;
  private String flag1;
  private BasicinforService basicinforService = new BasicinforServiceImpl();
  private UservipService uservipService = new UservipServiceImpl();

  public Basicinfor getBasicinfor()
  {
    return this.basicinfor;
  }
  public void setBasicinfor(Basicinfor basicinfor) {
    this.basicinfor = basicinfor;
  }
/**
 * 用户基本资料 
 * @return
 * @throws Exception
 */
  public String search()
    throws Exception
  {
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");

    this.basicinfor = this.basicinforService.findBasicinforByUserId(uservip.getUserId().intValue());
    String str = this.basicinfor.getCurrentLiveCity();
    if ((str != null) && (!"".equals(str.trim())) && 
      (str.indexOf(",") >= 0) && (str.split(",").length == 3)) {
      this.currentLiveCity1 = str.split(",")[0];
      this.currentLiveCity2 = str.split(",")[1];
      this.currentLiveCity3 = str.split(",")[2];
    }

    this.basicInforId = this.basicinfor.getBasicInforId();
    return "success";
  }
  /**
   * 创建基本信息
   * @return
   * @throws Exception
   */
  public String createBasicinfor() throws Exception {
    Basicinfor basicinfor1 = new Basicinfor();
    Uservip uservip = (Uservip)ActionContext.getContext().getSession().get("uservip");
    basicinfor1.setUservip(uservip);

    Basicinfor basicinfor2 = this.basicinforService.findBasicinforByUserId(uservip.getUserId().intValue());
    if (basicinfor2 == null)
    {
      basicinfor1.setSex("0");
      basicinfor1.setMarryStatus("0");
      basicinfor1.setHighestEdu("0");
      basicinfor1.setMonthIncome("0");
      basicinfor1.setRelationOne("0");
      basicinfor1.setRelationTwo("0");
      basicinfor1.setRelationThree("0");
      basicinfor1.setWorkYear("0");
      basicinfor1.setHouseCondition("0");
      basicinfor1.setIfByCar("0");
      basicinfor1.setContributionStatus("0");
      basicinfor1.setCosignerOneRelation("0");
      basicinfor1.setCosignerTwoRelation("0");
      basicinfor1.setCount1(Integer.valueOf(0));
      basicinfor1.setCount2(Integer.valueOf(0));
      basicinfor1.setCount3(Integer.valueOf(0));
      basicinfor1.setCount4(Integer.valueOf(0));
      basicinfor1.setCount5(Integer.valueOf(0));
      basicinfor1.setCount6(Integer.valueOf(0));
      Integer basicInforId1 = this.basicinforService.createBasicinfor(basicinfor1);
      this.basicinfor = basicinfor1;
      if (basicInforId1 != null) {
        this.basicInforId = basicInforId1;
        this.result = "success";
      } else {
        this.result = "false";
      }
    }
    return "success";
  }
/**
 * 修改基本信息
 * @return
 * @throws Exception
 */

  public String updateBasicinforlist1() throws Exception {
    Basicinfor basicinfor1 = this.basicinforService.findBasicinforByBasicInforId(this.basicInforId.intValue());
    this.currentLiveCity = (this.currentLiveCity1 + "," + this.currentLiveCity2 + "," + this.currentLiveCity3);
    basicinfor1.setSex(this.basicinfor.getSex());
    basicinfor1.setMarryStatus(this.basicinfor.getMarryStatus());
    basicinfor1.setHighestEdu(this.basicinfor.getHighestEdu());
    basicinfor1.setMonthIncome(this.basicinfor.getMonthIncome());
    basicinfor1.setCurrentLiveCity(this.currentLiveCity);
    basicinfor1.setJob(this.basicinfor.getJob());
    basicinfor1.setPersonalProfile(this.basicinfor.getPersonalProfile());
    basicinfor1.setCount1(Integer.valueOf(1));
    boolean flag = this.basicinforService.updateBasicinfor(basicinfor1);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    this.mark = "2";
    return this.result;
  }

  public String updateBasicinforlist2() throws Exception {
    Basicinfor basicinfor1 = this.basicinforService.findBasicinforByBasicInforId(this.basicInforId.intValue());
    basicinfor1.setCurrentAddress(this.basicinfor.getCurrentAddress());
    basicinfor1.setHomePhone(this.basicinfor.getHomePhone());
    basicinfor1.setLinkmanOne(this.basicinfor.getLinkmanOne());
    basicinfor1.setRelationOne(this.basicinfor.getRelationOne());
    basicinfor1.setPhoneOne(this.basicinfor.getPhoneOne());
    basicinfor1.setOtherOne(this.basicinfor.getOtherOne());
    basicinfor1.setLinkmanTwo(this.basicinfor.getLinkmanTwo());
    basicinfor1.setRelationTwo(this.basicinfor.getRelationTwo());
    basicinfor1.setPhoneTwo(this.basicinfor.getPhoneTwo());
    basicinfor1.setOtherTwo(this.basicinfor.getOtherTwo());
    basicinfor1.setLinkmanThree(this.basicinfor.getLinkmanThree());
    basicinfor1.setRelationThree(this.basicinfor.getRelationThree());
    basicinfor1.setPhoneThree(this.basicinfor.getPhoneThree());
    basicinfor1.setOtherThree(this.basicinfor.getOtherThree());
    if ("0".equals(this.flag1)) {
      basicinfor1.setCount2(Integer.valueOf(1));
    }
    boolean flag = this.basicinforService.updateBasicinfor(basicinfor1);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    this.mark = "3";
    return this.result;
  }
  public String updateBasicinforList3() throws Exception {
    Basicinfor basicinfor1 = this.basicinforService.findBasicinforByBasicInforId(this.basicInforId.intValue());
    basicinfor1.setCompanyName(this.basicinfor.getCompanyName());
    basicinfor1.setCompanyPhone(this.basicinfor.getCompanyPhone());
    basicinfor1.setCompanyAddress(this.basicinfor.getCompanyAddress());
    basicinfor1.setWorkYear(this.basicinfor.getWorkYear());
    basicinfor1.setReference(this.basicinfor.getReference());
    basicinfor1.setReferPhone(this.basicinfor.getReferPhone());
    if ("0".equals(this.flag1)) {
      basicinfor1.setCount3(Integer.valueOf(1));
    }
    boolean flag = this.basicinforService.updateBasicinfor(basicinfor1);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    this.mark = "4";
    return this.result;
  }
  public String updateBasicinforList4() throws Exception {
    Basicinfor basicinfor1 = this.basicinforService.findBasicinforByBasicInforId(this.basicInforId.intValue());
    basicinfor1.setMonthAverEarn(this.basicinfor.getMonthAverEarn());
    basicinfor1.setIncomeDescribe(this.basicinfor.getIncomeDescribe());
    basicinfor1.setMonthAverPay(this.basicinfor.getMonthAverPay());
    basicinfor1.setPayDescribe(this.basicinfor.getPayDescribe());
    basicinfor1.setHouseCondition(this.basicinfor.getHouseCondition());
    basicinfor1.setHouseValue(this.basicinfor.getHouseValue());
    basicinfor1.setIfByCar(this.basicinfor.getIfByCar());
    basicinfor1.setCarValue(this.basicinfor.getCarValue());
    basicinfor1.setJoinCompanyName(this.basicinfor.getJoinCompanyName());
    basicinfor1.setJoinCompanyResource(this.basicinfor.getJoinCompanyResource());
    basicinfor1.setOtherResourceDescribe(this.basicinfor.getOtherResourceDescribe());
    if ("0".equals(this.flag1)) {
      basicinfor1.setCount4(Integer.valueOf(1));
    }
    boolean flag = this.basicinforService.updateBasicinfor(basicinfor1);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    this.mark = "5";
    return this.result;
  }
  public String updateBasicinforList5() throws Exception {
    Basicinfor basicinfor1 = this.basicinforService.findBasicinforByBasicInforId(this.basicInforId.intValue());
    basicinfor1.setHouseAddress(this.basicinfor.getHouseAddress());
    basicinfor1.setBuildingArea(this.basicinfor.getBuildingArea());
    basicinfor1.setBuildingYear(this.basicinfor.getBuildingYear());
    basicinfor1.setContributionStatus(this.basicinfor.getContributionStatus());
    basicinfor1.setOwnerOne(this.basicinfor.getOwnerOne());
    basicinfor1.setOwnerOneShare(this.basicinfor.getOwnerOneShare());
    basicinfor1.setOwnerTwo(this.basicinfor.getOwnerTwo());
    basicinfor1.setOwnerTwoShare(this.basicinfor.getOwnerTwoShare());
    basicinfor1.setLoanYear(this.basicinfor.getLoanYear());
    basicinfor1.setMonthCont(this.basicinfor.getMonthCont());
    basicinfor1.setOweLoanBala(this.basicinfor.getOweLoanBala());
    basicinfor1.setMortgageContact(this.basicinfor.getMortgageContact());
    if ("0".equals(this.flag1)) {
      basicinfor1.setCount5(Integer.valueOf(1));
    }
    boolean flag = this.basicinforService.updateBasicinfor(basicinfor1);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    this.mark = "6";
    return this.result;
  }
  public String updateBasicinforList6() throws Exception {
    Basicinfor basicinfor1 = this.basicinforService.findBasicinforByBasicInforId(this.basicInforId.intValue());
    basicinfor1.setCosignerOne(this.basicinfor.getCosignerOne());
    basicinfor1.setCosignerOneRelation(this.basicinfor.getCosignerOneRelation());
    basicinfor1.setCosignerOnePhone(this.basicinfor.getCompanyPhone());
    basicinfor1.setCosignerTwo(this.basicinfor.getCosignerTwo());
    basicinfor1.setCosignerTwoRelation(this.basicinfor.getCosignerTwoRelation());
    basicinfor1.setCosignerOnePhone(this.basicinfor.getCosignerOnePhone());
    basicinfor1.setCosignerTwoPhone(this.basicinfor.getCosignerTwoPhone());
    basicinfor1.setCount6(Integer.valueOf(1));
    boolean flag = this.basicinforService.updateBasicinfor(basicinfor1);
    if (flag)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    this.mark = "1";
    return this.result;
  }
  public String findBasicinforByBasicInforId(int basicInforId) throws Exception {
    Basicinfor basicinfor = this.basicinforService.findBasicinforByBasicInforId(basicInforId);

    if (basicinfor != null)
    {
      this.result = "success";
    }
    else
    {
      this.result = "error";
    }
    return this.result;
  }

	public String findBasicinfors() throws Exception {
		List<Basicinfor> basicinfors = this.basicinforService.findBasicinfors();
    for (Basicinfor basicinfor : basicinfors) {
      if (basicinfor != null)
      {
        this.result = "success";
      }
      else
      {
        this.result = "error";
      }
    }

    return this.result; }
	
//根据手机号查找用户
	public String findBasicinforByphone() throws Exception {
		basicinfor = this.basicinforService.findBasicinforByPhone(this.phoneNum);
		if (basicinfor==null)
			this.result = "{\"result\":\"0\"}";
		else {
			this.result = "{\"result\":\"1\"}";
		}
		return "success";
	}

	public String findBasicinforByCard() throws Exception {
		Basicinfor bf = this.basicinforService.findBasicinforByIdNum(this.idNum);
		if (bf==null)
			this.result = "{\"result\":\"0\"}";
		else {
			this.result = "{\"result\":\"1\"}";
		}
		return "success";
	}
  public Integer getUserId() {
    return this.userId;
  }
  public void setUserId(Integer userId) {
    this.userId = userId;
  }
  public String getCurrentLiveCity1() {
    return this.currentLiveCity1;
  }
  public void setCurrentLiveCity1(String currentLiveCity1) {
    this.currentLiveCity1 = currentLiveCity1;
  }
  public String getCurrentLiveCity2() {
    return this.currentLiveCity2;
  }
  public void setCurrentLiveCity2(String currentLiveCity2) {
    this.currentLiveCity2 = currentLiveCity2;
  }
  public String getCurrentLiveCity3() {
    return this.currentLiveCity3;
  }
  public void setCurrentLiveCity3(String currentLiveCity3) {
    this.currentLiveCity3 = currentLiveCity3;
  }
  public String getResult() {
    return this.result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public Integer getBasicInforId() {
    return this.basicInforId;
  }
  public void setBasicInforId(Integer basicInforId) {
    this.basicInforId = basicInforId;
  }
  public String getRealName() {
    return this.realName;
  }
  public void setRealName(String realName) {
    this.realName = realName;
  }
  public String getIdNum() {
    return this.idNum;
  }
  public void setIdNum(String idNum) {
    this.idNum = idNum;
  }
  public String getPhoneNum() {
    return this.phoneNum;
  }
  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }
  public Integer getAge() {
    return this.age;
  }
  public void setAge(Integer age) {
    this.age = age;
  }
  public String getBirthPlace() {
    return this.birthPlace;
  }
  public void setBirthPlace(String birthPlace) {
    this.birthPlace = birthPlace;
  }
  public String getCurrentLiveCity() {
    return this.currentLiveCity;
  }
  public void setCurrentLiveCity(String currentLiveCity) {
    this.currentLiveCity = currentLiveCity;
  }
  public String getSex() {
    return this.sex;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public String getMarryStatus() {
    return this.marryStatus;
  }
  public void setMarryStatus(String marryStatus) {
    this.marryStatus = marryStatus;
  }
  public String getHighestEdu() {
    return this.highestEdu;
  }
  public void setHighestEdu(String highestEdu) {
    this.highestEdu = highestEdu;
  }
  public String getMonthIncome() {
    return this.monthIncome;
  }
  public void setMonthIncome(String monthIncome) {
    this.monthIncome = monthIncome;
  }
  public String getJob() {
    return this.job;
  }
  public void setJob(String job) {
    this.job = job;
  }
  public String getPersonalProfile() {
    return this.personalProfile;
  }
  public void setPersonalProfile(String personalProfile) {
    this.personalProfile = personalProfile;
  }
  public String getCurrentAddress() {
    return this.currentAddress;
  }
  public void setCurrentAddress(String currentAddress) {
    this.currentAddress = currentAddress;
  }
  public String getHomePhone() {
    return this.homePhone;
  }
  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }
  public String getLinkmanOne() {
    return this.linkmanOne;
  }
  public void setLinkmanOne(String linkmanOne) {
    this.linkmanOne = linkmanOne;
  }
  public String getRelationOne() {
    return this.relationOne;
  }
  public void setRelationOne(String relationOne) {
    this.relationOne = relationOne;
  }
  public String getPhoneOne() {
    return this.phoneOne;
  }
  public void setPhoneOne(String phoneOne) {
    this.phoneOne = phoneOne;
  }
  public String getOtherOne() {
    return this.otherOne;
  }
  public void setOtherOne(String otherOne) {
    this.otherOne = otherOne;
  }
  public String getLinkmanTwo() {
    return this.linkmanTwo;
  }
  public void setLinkmanTwo(String linkmanTwo) {
    this.linkmanTwo = linkmanTwo;
  }
  public String getRelationTwo() {
    return this.relationTwo;
  }
  public void setRelationTwo(String relationTwo) {
    this.relationTwo = relationTwo;
  }
  public String getPhoneTwo() {
    return this.phoneTwo;
  }
  public void setPhoneTwo(String phoneTwo) {
    this.phoneTwo = phoneTwo;
  }
  public String getOtherTwo() {
    return this.otherTwo;
  }
  public void setOtherTwo(String otherTwo) {
    this.otherTwo = otherTwo;
  }
  public String getLinkmanThree() {
    return this.linkmanThree;
  }
  public void setLinkmanThree(String linkmanThree) {
    this.linkmanThree = linkmanThree;
  }
  public String getRelationThree() {
    return this.relationThree;
  }
  public void setRelationThree(String relationThree) {
    this.relationThree = relationThree;
  }
  public String getPhoneThree() {
    return this.phoneThree;
  }
  public void setPhoneThree(String phoneThree) {
    this.phoneThree = phoneThree;
  }
  public String getOtherThree() {
    return this.otherThree;
  }
  public void setOtherThree(String otherThree) {
    this.otherThree = otherThree;
  }
  public String getCompanyName() {
    return this.companyName;
  }
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }
  public String getCompanyPhone() {
    return this.companyPhone;
  }
  public void setCompanyPhone(String companyPhone) {
    this.companyPhone = companyPhone;
  }
  public String getCompanyAddress() {
    return this.companyAddress;
  }
  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }
  public String getWorkYear() {
    return this.workYear;
  }
  public void setWorkYear(String workYear) {
    this.workYear = workYear;
  }
  public String getReference() {
    return this.reference;
  }
  public void setReference(String reference) {
    this.reference = reference;
  }
  public String getReferPhone() {
    return this.referPhone;
  }
  public void setReferPhone(String referPhone) {
    this.referPhone = referPhone;
  }
  public String getMonthAverEarn() {
    return this.monthAverEarn;
  }
  public void setMonthAverEarn(String monthAverEarn) {
    this.monthAverEarn = monthAverEarn;
  }
  public String getIncomeDescribe() {
    return this.incomeDescribe;
  }
  public void setIncomeDescribe(String incomeDescribe) {
    this.incomeDescribe = incomeDescribe;
  }
  public String getMonthAverPay() {
    return this.monthAverPay;
  }
  public void setMonthAverPay(String monthAverPay) {
    this.monthAverPay = monthAverPay;
  }
  public String getPayDescribe() {
    return this.payDescribe;
  }
  public void setPayDescribe(String payDescribe) {
    this.payDescribe = payDescribe;
  }
  public String getHouseCondition() {
    return this.houseCondition;
  }
  public void setHouseCondition(String houseCondition) {
    this.houseCondition = houseCondition;
  }
  public String getHouseValue() {
    return this.houseValue;
  }
  public void setHouseValue(String houseValue) {
    this.houseValue = houseValue;
  }
  public String getIfByCar() {
    return this.ifByCar;
  }
  public void setIfByCar(String ifByCar) {
    this.ifByCar = ifByCar;
  }
  public String getCarValue() {
    return this.carValue;
  }
  public void setCarValue(String carValue) {
    this.carValue = carValue;
  }
  public String getJoinCompanyName() {
    return this.joinCompanyName;
  }
  public void setJoinCompanyName(String joinCompanyName) {
    this.joinCompanyName = joinCompanyName;
  }
  public String getJoinCompanyResource() {
    return this.joinCompanyResource;
  }
  public void setJoinCompanyResource(String joinCompanyResource) {
    this.joinCompanyResource = joinCompanyResource;
  }
  public String getOtherResourceDescribe() {
    return this.otherResourceDescribe;
  }
  public void setOtherResourceDescribe(String otherResourceDescribe) {
    this.otherResourceDescribe = otherResourceDescribe;
  }
  public String getHouseAddress() {
    return this.houseAddress;
  }
  public void setHouseAddress(String houseAddress) {
    this.houseAddress = houseAddress;
  }
  public String getBuildingArea() {
    return this.buildingArea;
  }
  public void setBuildingArea(String buildingArea) {
    this.buildingArea = buildingArea;
  }
  public String getBuildingYear() {
    return this.buildingYear;
  }
  public void setBuildingYear(String buildingYear) {
    this.buildingYear = buildingYear;
  }
  public String getContributionStatus() {
    return this.contributionStatus;
  }
  public void setContributionStatus(String contributionStatus) {
    this.contributionStatus = contributionStatus;
  }
  public String getOwnerOne() {
    return this.ownerOne;
  }
  public void setOwnerOne(String ownerOne) {
    this.ownerOne = ownerOne;
  }
  public String getOwnerOneShare() {
    return this.ownerOneShare;
  }
  public void setOwnerOneShare(String ownerOneShare) {
    this.ownerOneShare = ownerOneShare;
  }
  public String getOwnerTwo() {
    return this.ownerTwo;
  }
  public void setOwnerTwo(String ownerTwo) {
    this.ownerTwo = ownerTwo;
  }
  public String getOwnerTwoShare() {
    return this.ownerTwoShare;
  }
  public void setOwnerTwoShare(String ownerTwoShare) {
    this.ownerTwoShare = ownerTwoShare;
  }
  public String getLoanYear() {
    return this.loanYear;
  }
  public void setLoanYear(String loanYear) {
    this.loanYear = loanYear;
  }
  public Double getMonthCont() {
    return this.monthCont;
  }
  public void setMonthCont(Double monthCont) {
    this.monthCont = monthCont;
  }
  public Double getOweLoanBala() {
    return this.oweLoanBala;
  }
  public void setOweLoanBala(Double oweLoanBala) {
    this.oweLoanBala = oweLoanBala;
  }
  public String getMortgageContact() {
    return this.mortgageContact;
  }
  public void setMortgageContact(String mortgageContact) {
    this.mortgageContact = mortgageContact;
  }
  public String getCosignerOne() {
    return this.cosignerOne;
  }
  public void setCosignerOne(String cosignerOne) {
    this.cosignerOne = cosignerOne;
  }
  public String getCosignerOneRelation() {
    return this.cosignerOneRelation;
  }
  public void setCosignerOneRelation(String cosignerOneRelation) {
    this.cosignerOneRelation = cosignerOneRelation;
  }
  public String getCosignerOnePhone() {
    return this.cosignerOnePhone;
  }
  public void setCosignerOnePhone(String cosignerOnePhone) {
    this.cosignerOnePhone = cosignerOnePhone;
  }
  public String getCosignerTwo() {
    return this.cosignerTwo;
  }
  public void setCosignerTwo(String cosignerTwo) {
    this.cosignerTwo = cosignerTwo;
  }
  public String getCosignerTwoRelation() {
    return this.cosignerTwoRelation;
  }
  public void setCosignerTwoRelation(String cosignerTwoRelation) {
    this.cosignerTwoRelation = cosignerTwoRelation;
  }
  public String getCosignerTwoPhone() {
    return this.cosignerTwoPhone;
  }
  public void setCosignerTwoPhone(String cosignerTwoPhone) {
    this.cosignerTwoPhone = cosignerTwoPhone;
  }
  public String getCosignerOneCard() {
    return this.cosignerOneCard;
  }
  public void setCosignerOneCard(String cosignerOneCard) {
    this.cosignerOneCard = cosignerOneCard;
  }
  public String getCosignerTwoCard() {
    return this.cosignerTwoCard;
  }
  public void setCosignerTwoCard(String cosignerTwoCard) {
    this.cosignerTwoCard = cosignerTwoCard;
  }
  public UservipService getUservipService() {
    return this.uservipService;
  }
  public void setUservipService(UservipService uservipService) {
    this.uservipService = uservipService;
  }
  public String getMark() {
    return this.mark;
  }
  public void setMark(String mark) {
    this.mark = mark;
  }
  public String getFlag1() {
    return this.flag1;
  }
  public void setFlag1(String flag1) {
    this.flag1 = flag1;
  }
}