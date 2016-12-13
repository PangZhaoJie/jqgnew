 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Inbox
   implements Serializable
 {
   private Integer inboxId;
   private Uservip uservip;
   private String sendName;
   private String content;
   private Integer status;
   private Timestamp receiveTime;
   private String title;
 
   public Inbox()
   {
   }
 
   public Inbox(Integer inboxId)
   {
     this.inboxId = inboxId;
   }
 
   public Inbox(Integer inboxId, Uservip uservip, String sendName, String content, Integer status, Timestamp receiveTime, String title)
   {
     this.inboxId = inboxId;
     this.uservip = uservip;
     this.sendName = sendName;
     this.content = content;
     this.status = status;
     this.receiveTime = receiveTime;
     this.title = title;
   }
 
   public Integer getInboxId()
   {
     return this.inboxId;
   }
 
   public void setInboxId(Integer inboxId) {
     this.inboxId = inboxId;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getSendName() {
     return this.sendName;
   }
 
   public void setSendName(String sendName) {
     this.sendName = sendName;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public Integer getStatus() {
     return this.status;
   }
 
   public void setStatus(Integer status) {
     this.status = status;
   }
 
   public Timestamp getReceiveTime() {
     return this.receiveTime;
   }
 
   public void setReceiveTime(Timestamp receiveTime) {
     this.receiveTime = receiveTime;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 }

