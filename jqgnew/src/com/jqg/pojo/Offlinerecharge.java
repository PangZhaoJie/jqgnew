 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Offlinerecharge
   implements Serializable
 {
   private Integer offlineRechargeId;
   private Companybankinfor companybankinfor;
   private Uservip uservip;
   private Double rechargeAmount;
   private String billSerialNum;
   private String recharge;
   private String printProof;
   private String offlineCreditNote;
   private String vipName;
   private Timestamp rechargeTime;
   private Integer rechargeStatus;
   private String checkOrderNum;
   private Double offReward;
 
   public Offlinerecharge()
   {
   }
 
   public Offlinerecharge(Integer offlineRechargeId)
   {
     this.offlineRechargeId = offlineRechargeId;
   }
 
   public Offlinerecharge(Integer offlineRechargeId, Companybankinfor companybankinfor, Uservip uservip, Double rechargeAmount, String billSerialNum, String recharge, String printProof, String offlineCreditNote, String vipName, Timestamp rechargeTime, Integer rechargeStatus, String checkOrderNum, Double offReward)
   {
     this.offlineRechargeId = offlineRechargeId;
     this.companybankinfor = companybankinfor;
     this.uservip = uservip;
     this.rechargeAmount = rechargeAmount;
     this.billSerialNum = billSerialNum;
     this.recharge = recharge;
     this.printProof = printProof;
     this.offlineCreditNote = offlineCreditNote;
     this.vipName = vipName;
     this.rechargeTime = rechargeTime;
     this.rechargeStatus = rechargeStatus;
     this.checkOrderNum = checkOrderNum;
     this.offReward = offReward;
   }
 
   public Integer getOfflineRechargeId()
   {
     return this.offlineRechargeId;
   }
 
   public void setOfflineRechargeId(Integer offlineRechargeId) {
     this.offlineRechargeId = offlineRechargeId;
   }
 
   public Companybankinfor getCompanybankinfor() {
     return this.companybankinfor;
   }
 
   public void setCompanybankinfor(Companybankinfor companybankinfor) {
     this.companybankinfor = companybankinfor;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Double getRechargeAmount() {
     return this.rechargeAmount;
   }
 
   public void setRechargeAmount(Double rechargeAmount) {
     this.rechargeAmount = rechargeAmount;
   }
 
   public String getBillSerialNum() {
     return this.billSerialNum;
   }
 
   public void setBillSerialNum(String billSerialNum) {
     this.billSerialNum = billSerialNum;
   }
 
   public String getRecharge() {
     return this.recharge;
   }
 
   public void setRecharge(String recharge) {
     this.recharge = recharge;
   }
 
   public String getPrintProof() {
     return this.printProof;
   }
 
   public void setPrintProof(String printProof) {
     this.printProof = printProof;
   }
 
   public String getOfflineCreditNote() {
     return this.offlineCreditNote;
   }
 
   public void setOfflineCreditNote(String offlineCreditNote) {
     this.offlineCreditNote = offlineCreditNote;
   }
 
   public String getVipName() {
     return this.vipName;
   }
 
   public void setVipName(String vipName) {
     this.vipName = vipName;
   }
 
   public Timestamp getRechargeTime() {
     return this.rechargeTime;
   }
 
   public void setRechargeTime(Timestamp rechargeTime) {
     this.rechargeTime = rechargeTime;
   }
 
   public Integer getRechargeStatus() {
     return this.rechargeStatus;
   }
 
   public void setRechargeStatus(Integer rechargeStatus) {
     this.rechargeStatus = rechargeStatus;
   }
 
   public String getCheckOrderNum() {
     return this.checkOrderNum;
   }
 
   public void setCheckOrderNum(String checkOrderNum) {
     this.checkOrderNum = checkOrderNum;
   }
 
   public Double getOffReward() {
     return this.offReward;
   }
 
   public void setOffReward(Double offReward) {
     this.offReward = offReward;
   }
 }

