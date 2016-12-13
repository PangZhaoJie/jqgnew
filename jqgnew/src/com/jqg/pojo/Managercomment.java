 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Managercomment
   implements Serializable
 {
   private Integer magcommentId;
   private Manager manager;
   private Investstrategy investstrategy;
   private String magcontent;
   private Timestamp magcommentDate;
 
   public Managercomment()
   {
   }
 
   public Managercomment(Integer magcommentId, Manager manager, Investstrategy investstrategy, String magcontent, Timestamp magcommentDate)
   {
     this.magcommentId = magcommentId;
     this.manager = manager;
     this.investstrategy = investstrategy;
     this.magcontent = magcontent;
     this.magcommentDate = magcommentDate;
   }
 
   public Integer getMagcommentId()
   {
     return this.magcommentId;
   }
 
   public void setMagcommentId(Integer magcommentId) {
     this.magcommentId = magcommentId;
   }
 
   public Manager getManager() {
     return this.manager;
   }
 
   public void setManager(Manager manager) {
     this.manager = manager;
   }
 
   public Investstrategy getInveststrategy() {
     return this.investstrategy;
   }
 
   public void setInveststrategy(Investstrategy investstrategy) {
     this.investstrategy = investstrategy;
   }
 
   public String getMagcontent() {
     return this.magcontent;
   }
 
   public void setMagcontent(String magcontent) {
     this.magcontent = magcontent;
   }
 
   public Timestamp getMagcommentDate() {
     return this.magcommentDate;
   }
 
   public void setMagcommentDate(Timestamp magcommentDate) {
     this.magcommentDate = magcommentDate;
   }
 }

