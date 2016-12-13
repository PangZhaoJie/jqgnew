 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Companyinformation
   implements Serializable
 {
   private Integer companyId;
   private String companyOfficialSeal;
   private String companyName;
   private String companyAddress;
   private String companyPhone;
 
   public Companyinformation()
   {
   }
 
   public Companyinformation(Integer companyId)
   {
     this.companyId = companyId;
   }
 
   public Companyinformation(Integer companyId, String companyOfficialSeal, String companyName, String companyAddress, String companyPhone)
   {
     this.companyId = companyId;
     this.companyOfficialSeal = companyOfficialSeal;
     this.companyName = companyName;
     this.companyAddress = companyAddress;
     this.companyPhone = companyPhone;
   }
 
   public Integer getCompanyId()
   {
     return this.companyId;
   }
 
   public void setCompanyId(Integer companyId) {
     this.companyId = companyId;
   }
 
   public String getCompanyOfficialSeal() {
     return this.companyOfficialSeal;
   }
 
   public void setCompanyOfficialSeal(String companyOfficialSeal) {
     this.companyOfficialSeal = companyOfficialSeal;
   }
 
   public String getCompanyName() {
     return this.companyName;
   }
 
   public void setCompanyName(String companyName) {
     this.companyName = companyName;
   }
 
   public String getCompanyAddress() {
     return this.companyAddress;
   }
 
   public void setCompanyAddress(String companyAddress) {
     this.companyAddress = companyAddress;
   }
 
   public String getCompanyPhone() {
     return this.companyPhone;
   }
 
   public void setCompanyPhone(String companyPhone) {
     this.companyPhone = companyPhone;
   }
 }

