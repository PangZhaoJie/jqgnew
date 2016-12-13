 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Basicinfor
   implements Serializable
 {
   private Integer basicInforId;
   private Uservip uservip;
   private String realName;
   private String idNum;
   private String phoneNum;
   private Integer age;
   private String birthPlace;
   private String currentLiveCity;
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
   private Integer count1;
   private Integer count2;
   private Integer count3;
   private Integer count4;
   private Integer count5;
   private Integer count6;
 
   public Basicinfor()
   {
   }
 
   public Basicinfor(Integer basicInforId)
   {
     this.basicInforId = basicInforId;
   }
 
   public Basicinfor(Integer basicInforId, Uservip uservip, String realName, String idNum, String phoneNum, Integer age, String birthPlace, String currentLiveCity, String sex, String marryStatus, String highestEdu, String monthIncome, String job, String personalProfile, String currentAddress, String homePhone, String linkmanOne, String relationOne, String phoneOne, String otherOne, String linkmanTwo, String relationTwo, String phoneTwo, String otherTwo, String linkmanThree, String relationThree, String phoneThree, String otherThree, String companyName, String companyPhone, String companyAddress, String workYear, String reference, String referPhone, String monthAverEarn, String incomeDescribe, String monthAverPay, String payDescribe, String houseCondition, String houseValue, String ifByCar, String carValue, String joinCompanyName, String joinCompanyResource, String otherResourceDescribe, String houseAddress, String buildingArea, String buildingYear, String contributionStatus, String ownerOne, String ownerOneShare, String ownerTwo, String ownerTwoShare, String loanYear, Double monthCont, Double oweLoanBala, String mortgageContact, String cosignerOne, String cosignerOneRelation, String cosignerOnePhone, String cosignerTwo, String cosignerTwoRelation, String cosignerTwoPhone, String cosignerOneCard, String cosignerTwoCard, Integer count1, Integer count2, Integer count3, Integer count4, Integer count5, Integer count6)
   {
     this.basicInforId = basicInforId;
     this.uservip = uservip;
     this.realName = realName;
     this.idNum = idNum;
     this.phoneNum = phoneNum;
     this.age = age;
     this.birthPlace = birthPlace;
     this.currentLiveCity = currentLiveCity;
     this.sex = sex;
     this.marryStatus = marryStatus;
     this.highestEdu = highestEdu;
     this.monthIncome = monthIncome;
     this.job = job;
     this.personalProfile = personalProfile;
     this.currentAddress = currentAddress;
     this.homePhone = homePhone;
     this.linkmanOne = linkmanOne;
     this.relationOne = relationOne;
     this.phoneOne = phoneOne;
     this.otherOne = otherOne;
     this.linkmanTwo = linkmanTwo;
     this.relationTwo = relationTwo;
     this.phoneTwo = phoneTwo;
     this.otherTwo = otherTwo;
     this.linkmanThree = linkmanThree;
     this.relationThree = relationThree;
     this.phoneThree = phoneThree;
     this.otherThree = otherThree;
     this.companyName = companyName;
     this.companyPhone = companyPhone;
     this.companyAddress = companyAddress;
     this.workYear = workYear;
     this.reference = reference;
     this.referPhone = referPhone;
     this.monthAverEarn = monthAverEarn;
     this.incomeDescribe = incomeDescribe;
     this.monthAverPay = monthAverPay;
     this.payDescribe = payDescribe;
     this.houseCondition = houseCondition;
     this.houseValue = houseValue;
     this.ifByCar = ifByCar;
     this.carValue = carValue;
     this.joinCompanyName = joinCompanyName;
     this.joinCompanyResource = joinCompanyResource;
     this.otherResourceDescribe = otherResourceDescribe;
     this.houseAddress = houseAddress;
     this.buildingArea = buildingArea;
     this.buildingYear = buildingYear;
     this.contributionStatus = contributionStatus;
     this.ownerOne = ownerOne;
     this.ownerOneShare = ownerOneShare;
     this.ownerTwo = ownerTwo;
     this.ownerTwoShare = ownerTwoShare;
     this.loanYear = loanYear;
     this.monthCont = monthCont;
     this.oweLoanBala = oweLoanBala;
     this.mortgageContact = mortgageContact;
     this.cosignerOne = cosignerOne;
     this.cosignerOneRelation = cosignerOneRelation;
     this.cosignerOnePhone = cosignerOnePhone;
     this.cosignerTwo = cosignerTwo;
     this.cosignerTwoRelation = cosignerTwoRelation;
     this.cosignerTwoPhone = cosignerTwoPhone;
     this.cosignerOneCard = cosignerOneCard;
     this.cosignerTwoCard = cosignerTwoCard;
     this.count1 = count1;
     this.count2 = count2;
     this.count3 = count3;
     this.count4 = count4;
     this.count5 = count5;
     this.count6 = count6;
   }
 
   public Integer getBasicInforId()
   {
     return this.basicInforId;
   }
 
   public void setBasicInforId(Integer basicInforId) {
     this.basicInforId = basicInforId;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
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
 
   public Integer getCount1() {
     return this.count1;
   }
 
   public void setCount1(Integer count1) {
     this.count1 = count1;
   }
 
   public Integer getCount2() {
     return this.count2;
   }
 
   public void setCount2(Integer count2) {
     this.count2 = count2;
   }
 
   public Integer getCount3() {
     return this.count3;
   }
 
   public void setCount3(Integer count3) {
     this.count3 = count3;
   }
 
   public Integer getCount4() {
     return this.count4;
   }
 
   public void setCount4(Integer count4) {
     this.count4 = count4;
   }
 
   public Integer getCount5() {
     return this.count5;
   }
 
   public void setCount5(Integer count5) {
     this.count5 = count5;
   }
 
   public Integer getCount6() {
     return this.count6;
   }
 
   public void setCount6(Integer count6) {
     this.count6 = count6;
   }
 }

