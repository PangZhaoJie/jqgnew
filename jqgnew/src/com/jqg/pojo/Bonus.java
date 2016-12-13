 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Bonus
   implements Serializable
 {
   private Integer bonusId;
   private Uservip uservip;
   private Integer rquestBonus;
   private String bonusExamine;
   private Timestamp bonusChangeTime;
   private Integer num;
   private String status;
 
   public Bonus()
   {
   }
 
   public Bonus(Integer bonusId)
   {
     this.bonusId = bonusId;
   }
 
   public Bonus(Integer bonusId, Uservip uservip, Integer rquestBonus, String bonusExamine, Timestamp bonusChangeTime, Integer num, String status)
   {
     this.bonusId = bonusId;
     this.uservip = uservip;
     this.rquestBonus = rquestBonus;
     this.bonusExamine = bonusExamine;
     this.bonusChangeTime = bonusChangeTime;
     this.num = num;
     this.status = status;
   }
 
   public Integer getBonusId()
   {
     return this.bonusId;
   }
 
   public void setBonusId(Integer bonusId) {
     this.bonusId = bonusId;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Integer getRquestBonus() {
     return this.rquestBonus;
   }
 
   public void setRquestBonus(Integer rquestBonus) {
     this.rquestBonus = rquestBonus;
   }
 
   public String getBonusExamine() {
     return this.bonusExamine;
   }
 
   public void setBonusExamine(String bonusExamine) {
     this.bonusExamine = bonusExamine;
   }
 
   public Timestamp getBonusChangeTime() {
     return this.bonusChangeTime;
   }
 
   public void setBonusChangeTime(Timestamp bonusChangeTime) {
     this.bonusChangeTime = bonusChangeTime;
   }
 
   public Integer getNum() {
     return this.num;
   }
 
   public void setNum(Integer num) {
     this.num = num;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 }

