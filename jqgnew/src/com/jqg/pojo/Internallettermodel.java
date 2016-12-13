 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Internallettermodel
   implements Serializable
 {
   private Integer interModelId;
   private String interModelName;
   private String interModelContent;
   private int isOpen;
 
   public Internallettermodel()
   {
   }
 
   public Internallettermodel(Integer interModelId)
   {
     this.interModelId = interModelId;
   }
 
   public Internallettermodel(Integer interModelId, String interModelName, String interModelContent, int isOpen)
   {
     this.interModelId = interModelId;
     this.interModelContent = interModelContent;
     this.interModelName = interModelName;
     this.isOpen = isOpen;
   }
 
   public Integer getInterModelId()
   {
     return this.interModelId;
   }
 
   public void setInterModelId(Integer interModelId) {
     this.interModelId = interModelId;
   }
 
   public String getInterModelName() {
     return this.interModelName;
   }
 
   public void setInterModelName(String interModelName) {
     this.interModelName = interModelName;
   }
 
   public String getInterModelContent() {
     return this.interModelContent;
   }
 
   public void setInterModelContent(String interModelContent) {
     this.interModelContent = interModelContent;
   }
 
   public int getIsOpen() {
     return this.isOpen;
   }
 
   public void setIsOpen(int isOpen) {
     this.isOpen = isOpen;
   }
 }

