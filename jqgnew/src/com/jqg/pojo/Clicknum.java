 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Clicknum
   implements Serializable
 {
   private Integer clickNumId;
   private Investstrategy investstrategy;
   private Uservip uservip;
   private String clickNumIp;
   private Timestamp clickNumDate;
 
   public Clicknum()
   {
   }
 
   public Clicknum(Integer clickNumId)
   {
     this.clickNumId = clickNumId;
   }
 
   public Clicknum(Integer clickNumId, Investstrategy investstrategy, Uservip uservip, String clickNumIp, Timestamp clickNumDate)
   {
     this.clickNumId = clickNumId;
     this.investstrategy = investstrategy;
     this.uservip = uservip;
     this.clickNumIp = clickNumIp;
     this.clickNumDate = clickNumDate;
   }
 
   public Integer getClickNumId()
   {
     return this.clickNumId;
   }
 
   public void setClickNumId(Integer clickNumId) {
     this.clickNumId = clickNumId;
   }
 
   public Investstrategy getInveststrategy() {
     return this.investstrategy;
   }
 
   public void setInveststrategy(Investstrategy investstrategy) {
     this.investstrategy = investstrategy;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getClickNumIp() {
     return this.clickNumIp;
   }
 
   public void setClickNumIp(String clickNumIp) {
     this.clickNumIp = clickNumIp;
   }
 
   public Timestamp getClickNumDate() {
     return this.clickNumDate;
   }
 
   public void setClickNumDate(Timestamp clickNumDate) {
     this.clickNumDate = clickNumDate;
   }
 }

