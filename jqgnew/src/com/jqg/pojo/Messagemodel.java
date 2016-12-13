 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Messagemodel
   implements Serializable
 {
   private Integer messModelId;
   private String messModelName;
   private String messModelContent;
   private int isOpen;
 
   public Messagemodel()
   {
   }
 
   public Messagemodel(Integer messModelId)
   {
     this.messModelId = messModelId;
   }
 
   public Messagemodel(Integer messModelId, String messModelName, String messModelContent, int isOpen)
   {
     this.messModelId = messModelId;
     this.messModelContent = messModelContent;
     this.messModelName = messModelName;
     this.isOpen = isOpen;
   }
 
   public Integer getMessModelId()
   {
     return this.messModelId;
   }
 
   public void setMessModelId(Integer messModelId) {
     this.messModelId = messModelId;
   }
 
   public String getMessModelName() {
     return this.messModelName;
   }
 
   public void setMessModelName(String messModelName) {
     this.messModelName = messModelName;
   }
 
   public String getMessModelContent() {
     return this.messModelContent;
   }
 
   public void setMessModelContent(String messModelContent) {
     this.messModelContent = messModelContent;
   }
 
   public int getIsOpen() {
     return this.isOpen;
   }
 
   public void setIsOpen(int isOpen) {
     this.isOpen = isOpen;
   }
 }

