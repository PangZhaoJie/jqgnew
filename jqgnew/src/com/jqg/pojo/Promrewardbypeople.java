 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Promrewardbypeople
   implements Serializable
 {
   private Integer promRewardByPeopleId;
   private Double reward;
   private Integer peopleNum;
   private Timestamp startTime;
   private Timestamp endTime;
 
   public Promrewardbypeople()
   {
   }
 
   public Promrewardbypeople(Double reward, Integer peopleNum, Timestamp startTime, Timestamp endTime)
   {
     this.reward = reward;
     this.peopleNum = peopleNum;
     this.startTime = startTime;
     this.endTime = endTime;
   }
 
   public Integer getPromRewardByPeopleId()
   {
     return this.promRewardByPeopleId;
   }
 
   public void setPromRewardByPeopleId(Integer promRewardByPeopleId) {
     this.promRewardByPeopleId = promRewardByPeopleId;
   }
 
   public Double getReward() {
     return this.reward;
   }
 
   public void setReward(Double reward) {
     this.reward = reward;
   }
 
   public Integer getPeopleNum() {
     return this.peopleNum;
   }
 
   public void setPeopleNum(Integer peopleNum) {
     this.peopleNum = peopleNum;
   }
 
   public Timestamp getStartTime() {
     return this.startTime;
   }
 
   public void setStartTime(Timestamp startTime) {
     this.startTime = startTime;
   }
 
   public Timestamp getEndTime() {
     return this.endTime;
   }
 
   public void setEndTime(Timestamp endTime) {
     this.endTime = endTime;
   }
 }

