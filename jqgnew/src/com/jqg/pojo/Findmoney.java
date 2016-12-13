 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Findmoney
   implements Serializable
 {
   private Integer findMoneyId;
   private String findMoneyName;
   private String findMoneyNumber;
 
   public Findmoney()
   {
   }
 
   public Findmoney(Integer findMoneyId)
   {
     this.findMoneyId = findMoneyId;
   }
 
   public Findmoney(Integer findMoneyId, String findMoneyName, String findMoneyNumber)
   {
     this.findMoneyId = findMoneyId;
     this.findMoneyName = findMoneyName;
     this.findMoneyNumber = findMoneyNumber;
   }
 
   public Integer getFindMoneyId()
   {
     return this.findMoneyId;
   }
 
   public void setFindMoneyId(Integer findMoneyId) {
     this.findMoneyId = findMoneyId;
   }
 
   public String getFindMoneyName() {
     return this.findMoneyName;
   }
 
   public void setFindMoneyName(String findMoneyName) {
     this.findMoneyName = findMoneyName;
   }
 
   public String getFindMoneyNumber() {
     return this.findMoneyNumber;
   }
 
   public void setFindMoneyNumber(String findMoneyNumber) {
     this.findMoneyNumber = findMoneyNumber;
   }
 }

