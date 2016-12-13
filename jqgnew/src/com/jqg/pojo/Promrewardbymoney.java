 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Promrewardbymoney
   implements Serializable
 {
   private Integer promRewardByMoneyId;
   private Double money;
   private Double reward;
   private Timestamp endTime;
   private Timestamp startTime;
 
   public Promrewardbymoney()
   {
   }
 
   public Promrewardbymoney(Integer promRewardByMoneyId)
   {
     this.promRewardByMoneyId = promRewardByMoneyId;
   }
 
   public Promrewardbymoney(Integer promRewardByMoneyId, Double money, Double reward, Timestamp endTime, Timestamp startTime)
   {
     this.promRewardByMoneyId = promRewardByMoneyId;
     this.money = money;
     this.reward = reward;
     this.endTime = endTime;
     this.startTime = startTime;
   }
 
   public Integer getPromRewardByMoneyId()
   {
     return this.promRewardByMoneyId;
   }
 
   public void setPromRewardByMoneyId(Integer promRewardByMoneyId) {
     this.promRewardByMoneyId = promRewardByMoneyId;
   }
 
   public Double getMoney() {
     return this.money;
   }
 
   public void setMoney(Double money) {
     this.money = money;
   }
 
   public Double getReward() {
     return this.reward;
   }
 
   public void setReward(Double reward) {
     this.reward = reward;
   }
 
   public Timestamp getEndTime() {
     return this.endTime;
   }
 
   public void setEndTime(Timestamp endTime) {
     this.endTime = endTime;
   }
 
   public Timestamp getStartTime() {
     return this.startTime;
   }
 
   public void setStartTime(Timestamp startTime) {
     this.startTime = startTime;
   }
 }

