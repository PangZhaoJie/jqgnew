 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Integraldetail
   implements Serializable
 {
   private Integer integralId;
   private Integralcategory integralcategory;
   private Uservip uservip;
   private Double integralQuota;
   private Timestamp integralTime;
   private String integralReason;
   private Double remainIntegral;
 
   public Integraldetail()
   {
   }
 
   public Integraldetail(Integer integralId)
   {
     this.integralId = integralId;
   }
 
   public Integraldetail(Integer integralId, Integralcategory integralcategory, Uservip uservip, Double integralQuota, Timestamp integralTime, String integralReason, Double remainIntegral)
   {
     this.integralId = integralId;
     this.integralcategory = integralcategory;
     this.uservip = uservip;
     this.integralQuota = integralQuota;
     this.integralTime = integralTime;
     this.integralReason = integralReason;
     this.remainIntegral = remainIntegral;
   }
 
   public Integer getIntegralId()
   {
     return this.integralId;
   }
 
   public void setIntegralId(Integer integralId) {
     this.integralId = integralId;
   }
 
   public Integralcategory getIntegralcategory() {
     return this.integralcategory;
   }
 
   public void setIntegralcategory(Integralcategory integralcategory) {
     this.integralcategory = integralcategory;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Timestamp getIntegralTime() {
     return this.integralTime;
   }
 
   public void setIntegralTime(Timestamp integralTime) {
     this.integralTime = integralTime;
   }
 
   public String getIntegralReason() {
     return this.integralReason;
   }
 
   public void setIntegralReason(String integralReason) {
     this.integralReason = integralReason;
   }

public Double getIntegralQuota() {
	return integralQuota;
}

public void setIntegralQuota(Double integralQuota) {
	this.integralQuota = integralQuota;
}

public Double getRemainIntegral() {
	return remainIntegral;
}

public void setRemainIntegral(Double remainIntegral) {
	this.remainIntegral = remainIntegral;
}
   
 }

