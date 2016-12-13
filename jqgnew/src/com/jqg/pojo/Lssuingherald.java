 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Lssuingherald
   implements Serializable
 {
   private Integer lssuingHeraldId;
   private Periodtime periodtime;
   private Returnway returnway;
   private Integer isShow;
   private Double awaed;
   private String money;
   private Double rate;
   private Timestamp heraldTime;
   private String lssuingName;
   private String time;
 
   public Lssuingherald()
   {
   }
 
   public Lssuingherald(Integer lssuingHeraldId)
   {
     this.lssuingHeraldId = lssuingHeraldId;
   }
 
   public Lssuingherald(Integer lssuingHeraldId, Periodtime periodtime, Returnway returnway, Integer isShow, Double awaed, String money, Double rate, Timestamp heraldTime, String lssuingName,String time)
   {
     this.lssuingHeraldId = lssuingHeraldId;
     this.periodtime = periodtime;
     this.returnway = returnway;
     this.isShow = isShow;
     this.awaed = awaed;
     this.money = money;
     this.rate = rate;
     this.heraldTime = heraldTime;
     this.lssuingName = lssuingName;
     this.time=time;
   }
 
   public Integer getLssuingHeraldId()
   {
     return this.lssuingHeraldId;
   }
 
   public void setLssuingHeraldId(Integer lssuingHeraldId) {
     this.lssuingHeraldId = lssuingHeraldId;
   }
 
   public Periodtime getPeriodtime() {
     return this.periodtime;
   }
 
   public void setPeriodtime(Periodtime periodtime) {
     this.periodtime = periodtime;
   }
 
   public Returnway getReturnway() {
     return this.returnway;
   }
 
   public void setReturnway(Returnway returnway) {
     this.returnway = returnway;
   }
 
   public Integer getIsShow() {
     return this.isShow;
   }
 
   public void setIsShow(Integer isShow) {
     this.isShow = isShow;
   }
 
   public Double getAwaed() {
     return this.awaed;
   }
 
   public void setAwaed(Double awaed) {
     this.awaed = awaed;
   }
 
   public String getMoney() {
     return this.money;
   }
 
   public void setMoney(String money) {
     this.money = money;
   }
 
   public Double getRate() {
     return this.rate;
   }
 
   public void setRate(Double rate) {
     this.rate = rate;
   }
 
   public Timestamp getHeraldTime() {
     return this.heraldTime;
   }
 
   public void setHeraldTime(Timestamp heraldTime) {
     this.heraldTime = heraldTime;
   }
 
   public String getLssuingName() {
     return this.lssuingName;
   }
 
   public void setLssuingName(String lssuingName) {
     this.lssuingName = lssuingName;
   }

	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
   
 }

