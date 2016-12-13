 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Companybankinfor
   implements Serializable
 {
   private Integer companyBankInforId;
   private Bankparameter bankparameter;
   private String accountName;
   private String accountNum;
   private String accountAddress;
   private Set offlinerecharges = new HashSet(0);
 
   public Companybankinfor()
   {
   }
 
   public Companybankinfor(Integer companyBankInforId)
   {
     this.companyBankInforId = companyBankInforId;
   }
 
   public Companybankinfor(Integer companyBankInforId, Bankparameter bankparameter, String accountName, String accountNum, String accountAddress, Set offlinerecharges)
   {
     this.companyBankInforId = companyBankInforId;
     this.bankparameter = bankparameter;
     this.accountName = accountName;
     this.accountNum = accountNum;
     this.accountAddress = accountAddress;
     this.offlinerecharges = offlinerecharges;
   }
 
   public Integer getCompanyBankInforId()
   {
     return this.companyBankInforId;
   }
 
   public void setCompanyBankInforId(Integer companyBankInforId) {
     this.companyBankInforId = companyBankInforId;
   }
 
   public Bankparameter getBankparameter() {
     return this.bankparameter;
   }
 
   public void setBankparameter(Bankparameter bankparameter) {
     this.bankparameter = bankparameter;
   }
 
   public String getAccountName() {
     return this.accountName;
   }
 
   public void setAccountName(String accountName) {
     this.accountName = accountName;
   }
 
   public String getAccountNum() {
     return this.accountNum;
   }
 
   public void setAccountNum(String accountNum) {
     this.accountNum = accountNum;
   }
 
   public String getAccountAddress() {
     return this.accountAddress;
   }
 
   public void setAccountAddress(String accountAddress) {
     this.accountAddress = accountAddress;
   }
 
   public Set getOfflinerecharges() {
     return this.offlinerecharges;
   }
 
   public void setOfflinerecharges(Set offlinerecharges) {
     this.offlinerecharges = offlinerecharges;
   }
 }

