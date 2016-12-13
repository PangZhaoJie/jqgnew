 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Oerationlog
   implements Serializable
 {
   private Integer oerationLogId;
   private Manager manager;
   private String oerationCategory;
   private Timestamp oerationChangeTime;
   private String orationIp;
   private String oerationRemaks;
 
   public Oerationlog()
   {
   }
 
   public Oerationlog(Integer oerationLogId)
   {
     this.oerationLogId = oerationLogId;
   }
 
   public Oerationlog(Integer oerationLogId, Manager manager, String oerationCategory, Timestamp oerationChangeTime, String orationIp, String oerationRemaks)
   {
     this.oerationLogId = oerationLogId;
     this.manager = manager;
     this.oerationCategory = oerationCategory;
     this.oerationChangeTime = oerationChangeTime;
     this.orationIp = orationIp;
     this.oerationRemaks = oerationRemaks;
   }
 
   public Integer getOerationLogId()
   {
     return this.oerationLogId;
   }
 
   public void setOerationLogId(Integer oerationLogId) {
     this.oerationLogId = oerationLogId;
   }
 
   public Manager getManager() {
     return this.manager;
   }
 
   public void setManager(Manager manager) {
     this.manager = manager;
   }
 
   public String getOerationCategory() {
     return this.oerationCategory;
   }
 
   public void setOerationCategory(String oerationCategory) {
     this.oerationCategory = oerationCategory;
   }
 
   public Timestamp getOerationChangeTime() {
     return this.oerationChangeTime;
   }
 
   public void setOerationChangeTime(Timestamp oerationChangeTime) {
     this.oerationChangeTime = oerationChangeTime;
   }
 
   public String getOrationIp() {
     return this.orationIp;
   }
 
   public void setOrationIp(String orationIp) {
     this.orationIp = orationIp;
   }
 
   public String getOerationRemaks() {
     return this.oerationRemaks;
   }
 
   public void setOerationRemaks(String oerationRemaks) {
     this.oerationRemaks = oerationRemaks;
   }
 }

