 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Uservipnote
   implements Serializable
 {
   private Integer userVipNoteId;
   private Uservip uservip;
   private Timestamp loginDate;
   private String ip;
 
   public Uservipnote()
   {
   }
 
   public Uservipnote(Integer userVipNoteId)
   {
     this.userVipNoteId = userVipNoteId;
   }
 
   public Uservipnote(Integer userVipNoteId, Uservip uservip, Timestamp loginDate, String ip)
   {
     this.userVipNoteId = userVipNoteId;
     this.uservip = uservip;
     this.loginDate = loginDate;
     this.ip = ip;
   }
 
   public Integer getUserVipNoteId()
   {
     return this.userVipNoteId;
   }
 
   public void setUserVipNoteId(Integer userVipNoteId) {
     this.userVipNoteId = userVipNoteId;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Timestamp getLoginDate() {
     return this.loginDate;
   }
 
   public void setLoginDate(Timestamp loginDate) {
     this.loginDate = loginDate;
   }
 
   public String getIp() {
     return this.ip;
   }
 
   public void setIp(String ip) {
     this.ip = ip;
   }
 }

