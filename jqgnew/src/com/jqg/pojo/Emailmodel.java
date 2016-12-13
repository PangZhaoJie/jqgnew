 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Emailmodel
   implements Serializable
 {
   private Integer emailModelId;
   private String emailModelName;
   private String emailModelContent;
   private int isOpen;
 
   public Emailmodel()
   {
   }
 
   public Emailmodel(Integer emailModelId)
   {
     this.emailModelId = emailModelId;
   }
 
   public Emailmodel(Integer emailModelId, String emailModelName, String emailModelContent, int isOpen)
   {
     this.emailModelId = emailModelId;
     this.emailModelName = emailModelName;
     this.emailModelContent = emailModelContent;
     this.isOpen = isOpen;
   }
 
   public Integer getEmailModelId()
   {
     return this.emailModelId;
   }
 
   public void setEmailModelId(Integer emailModelId) {
     this.emailModelId = emailModelId;
   }
 
   public String getEmailModelName() {
     return this.emailModelName;
   }
 
   public void setEmailModelName(String emailModelName) {
     this.emailModelName = emailModelName;
   }
 
   public String getEmailModelContent() {
     return this.emailModelContent;
   }
 
   public void setEmailModelContent(String emailModelContent) {
     this.emailModelContent = emailModelContent;
   }
 
   public int getIsOpen() {
     return this.isOpen;
   }
 
   public void setIsOpen(int isOpen) {
     this.isOpen = isOpen;
   }
 }

