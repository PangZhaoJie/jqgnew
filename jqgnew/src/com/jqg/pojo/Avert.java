 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Avert
   implements Serializable
 {
   private Integer avertId;
   private Integer avertName;
   private String avertContent;
   private Timestamp avertTime;
   private String avertAdress;
 
   public Avert()
   {
   }
 
   public Avert(Integer avertId)
   {
     this.avertId = avertId;
   }
 
   public Avert(Integer avertId, Integer avertName, String avertContent, Timestamp avertTime, String avertAdress)
   {
     this.avertId = avertId;
     this.avertName = avertName;
     this.avertContent = avertContent;
     this.avertTime = avertTime;
     this.avertAdress = avertAdress;
   }
 
   public Integer getAvertId()
   {
     return this.avertId;
   }
 
   public void setAvertId(Integer avertId) {
     this.avertId = avertId;
   }
 
   public Integer getAvertName() {
     return this.avertName;
   }
 
   public void setAvertName(Integer avertName) {
     this.avertName = avertName;
   }
 
   public String getAvertContent() {
     return this.avertContent;
   }
 
   public void setAvertContent(String avertContent) {
     this.avertContent = avertContent;
   }
 
   public Timestamp getAvertTime() {
     return this.avertTime;
   }
 
   public void setAvertTime(Timestamp avertTime) {
     this.avertTime = avertTime;
   }
 
   public String getAvertAdress() {
     return this.avertAdress;
   }
 
   public void setAvertAdress(String avertAdress) {
     this.avertAdress = avertAdress;
   }
 }

