 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Tenderwords
   implements Serializable
 {
   private Integer tenderWordsId;
   private Lssuing lssuing;
   private Uservip uservip;
   private String message;
   private int state;
   private Timestamp time;
 
   public Tenderwords()
   {
   }
 
   public Tenderwords(Integer tenderWordsId)
   {
     this.tenderWordsId = tenderWordsId;
   }
 
   public Tenderwords(Integer tenderWordsId, Lssuing lssuing, Uservip uservip, String message, int state, Timestamp time)
   {
     this.tenderWordsId = tenderWordsId;
     this.lssuing = lssuing;
     this.uservip = uservip;
     this.message = message;
     this.state = state;
     this.time = time;
   }
 
   public Timestamp getTime()
   {
     return this.time;
   }
 
   public void setTime(Timestamp time) {
     this.time = time;
   }
   public Integer getTenderWordsId() {
     return this.tenderWordsId;
   }
 
   public void setTenderWordsId(Integer tenderWordsId)
   {
     this.tenderWordsId = tenderWordsId;
   }
 
   public Lssuing getLssuing() {
     return this.lssuing;
   }
 
   public void setLssuing(Lssuing lssuing) {
     this.lssuing = lssuing;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getMessage() {
     return this.message;
   }
 
   public void setMessage(String message) {
     this.message = message;
   }
 
   public int getState() {
     return this.state;
   }
 
   public void setState(int state) {
     this.state = state;
   }
 }

