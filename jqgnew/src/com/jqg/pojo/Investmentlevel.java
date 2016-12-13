 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Investmentlevel
   implements Serializable
 {
   private Integer investmentLevelId;
   private String investmentLevelName;
   private String investmentLevelStart;
   private String investmentLevelEnd;
   private String investmentLevelPicture;
   private Set uservips = new HashSet(0);
 
   public Investmentlevel()
   {
   }
 
   public Investmentlevel(Integer investmentLevelId)
   {
     this.investmentLevelId = investmentLevelId;
   }
 
   public Investmentlevel(Integer investmentLevelId, String investmentLevelName, String investmentLevelStart, String investmentLevelEnd, String investmentLevelPicture, Set uservips)
   {
     this.investmentLevelId = investmentLevelId;
     this.investmentLevelName = investmentLevelName;
     this.investmentLevelStart = investmentLevelStart;
     this.investmentLevelEnd = investmentLevelEnd;
     this.investmentLevelPicture = investmentLevelPicture;
     this.uservips = uservips;
   }
 
   public Integer getInvestmentLevelId()
   {
     return this.investmentLevelId;
   }
 
   public void setInvestmentLevelId(Integer investmentLevelId) {
     this.investmentLevelId = investmentLevelId;
   }
 
   public String getInvestmentLevelName() {
     return this.investmentLevelName;
   }
 
   public void setInvestmentLevelName(String investmentLevelName) {
     this.investmentLevelName = investmentLevelName;
   }
 
   public String getInvestmentLevelStart() {
     return this.investmentLevelStart;
   }
 
   public void setInvestmentLevelStart(String investmentLevelStart) {
     this.investmentLevelStart = investmentLevelStart;
   }
 
   public String getInvestmentLevelEnd() {
     return this.investmentLevelEnd;
   }
 
   public void setInvestmentLevelEnd(String investmentLevelEnd) {
     this.investmentLevelEnd = investmentLevelEnd;
   }
 
   public String getInvestmentLevelPicture() {
     return this.investmentLevelPicture;
   }
 
   public void setInvestmentLevelPicture(String investmentLevelPicture) {
     this.investmentLevelPicture = investmentLevelPicture;
   }
 
   public Set getUservips() {
     return this.uservips;
   }
 
   public void setUservips(Set uservips) {
     this.uservips = uservips;
   }
 }

