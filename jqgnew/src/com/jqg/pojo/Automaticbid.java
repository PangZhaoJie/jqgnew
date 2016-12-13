 package com.jqg.pojo;

 import java.io.Serializable;
 import java.sql.Timestamp;

 public class Automaticbid
   implements Serializable
 {
   private Integer automaticBidId;
   private Integer falseId;
   private Periodtime periodtime;
   private Uservip uservip;
   private Returnway returnway;
   private Integer isType;
   private Integer orderNum;
   private Integer enable;
   private String automaticBidNumber;
   private Integer lssuingType;
   private Timestamp endTime;
   private Double startMoneyRate;
   private Double endMoneyRate;

   public Automaticbid()
   {
   }

   public Automaticbid(Integer automaticBidId)
   {
     this.automaticBidId = automaticBidId;
   }

   public Automaticbid(Integer automaticBidId, Periodtime periodtime, Uservip uservip, Returnway returnway, Integer isType, Integer enable, String automaticBidNumber, Integer lssuingType, Timestamp endTime, Double startMoneyRate, Double endMoneyRate, Integer orderNum, Integer falseId)
   {
     this.automaticBidId = automaticBidId;
     this.periodtime = periodtime;
     this.uservip = uservip;
     this.returnway = returnway;
     this.isType = isType;
     this.enable = enable;
     this.automaticBidNumber = automaticBidNumber;
     this.lssuingType = lssuingType;
     this.endTime = endTime;
     this.startMoneyRate = startMoneyRate;
     this.endMoneyRate = endMoneyRate;
     this.orderNum = orderNum;
     this.falseId = falseId;
   }

   public Integer getAutomaticBidId()
   {
     return this.automaticBidId;
   }

   public Integer getFalseId() {
     return this.falseId;
   }

   public void setFalseId(Integer falseId) {
     this.falseId = falseId;
   }

   public Integer getOrderNum() {
     return this.orderNum;
   }

   public void setOrderNum(Integer orderNum) {
     this.orderNum = orderNum;
   }

   public void setAutomaticBidId(Integer automaticBidId) {
     this.automaticBidId = automaticBidId;
   }

   public Periodtime getPeriodtime() {
     return this.periodtime;
   }

   public void setPeriodtime(Periodtime periodtime) {
     this.periodtime = periodtime;
   }

   public Uservip getUservip() {
     return this.uservip;
   }

   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }

   public Returnway getReturnway() {
     return this.returnway;
   }

   public void setReturnway(Returnway returnway) {
     this.returnway = returnway;
   }

   public Integer getIsType() {
     return this.isType;
   }

   public void setIsType(Integer isType) {
     this.isType = isType;
   }

   public Integer getEnable() {
     return this.enable;
   }

   public void setEnable(Integer enable) {
     this.enable = enable;
   }

   public String getAutomaticBidNumber() {
     return this.automaticBidNumber;
   }

   public void setAutomaticBidNumber(String automaticBidNumber) {
     this.automaticBidNumber = automaticBidNumber;
   }

   public Integer getLssuingType() {
     return this.lssuingType;
   }

   public void setLssuingType(Integer lssuingType) {
     this.lssuingType = lssuingType;
   }

   public Timestamp getEndTime() {
     return this.endTime;
   }

   public void setEndTime(Timestamp endTime) {
     this.endTime = endTime;
   }

   public Double getStartMoneyRate() {
     return this.startMoneyRate;
   }

   public void setStartMoneyRate(Double startMoneyRate) {
     this.startMoneyRate = startMoneyRate;
   }

   public Double getEndMoneyRate() {
     return this.endMoneyRate;
   }

   public void setEndMoneyRate(Double endMoneyRate) {
     this.endMoneyRate = endMoneyRate;
   }
 }

