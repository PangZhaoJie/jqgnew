 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Certification
   implements Serializable
 {
   private Integer certificationId;
   private Manager manager;
   private Uservip uservip;
   private String realName;
   private String idNum;
   private String frontImage;
   private String negativeImage;
   private Timestamp upLoadTime;
   private Integer examineStatus;
 
   public Certification()
   {
   }
 
   public Certification(Integer certificationId)
   {
     this.certificationId = certificationId;
   }
 
   public Certification(Integer certificationId, Manager manager, Uservip uservip, String realName, String idNum, String frontImage, String negativeImage, Timestamp upLoadTime, Integer examineStatus)
   {
     this.certificationId = certificationId;
     this.manager = manager;
     this.uservip = uservip;
     this.realName = realName;
     this.idNum = idNum;
     this.frontImage = frontImage;
     this.negativeImage = negativeImage;
     this.upLoadTime = upLoadTime;
     this.examineStatus = examineStatus;
   }
 
   public Integer getCertificationId()
   {
     return this.certificationId;
   }
 
   public void setCertificationId(Integer certificationId) {
     this.certificationId = certificationId;
   }
 
   public Manager getManager() {
     return this.manager;
   }
 
   public void setManager(Manager manager) {
     this.manager = manager;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getRealName() {
     return this.realName;
   }
 
   public void setRealName(String realName) {
     this.realName = realName;
   }
 
   public String getIdNum() {
     return this.idNum;
   }
 
   public void setIdNum(String idNum) {
     this.idNum = idNum;
   }
 
   public String getFrontImage() {
     return this.frontImage;
   }
 
   public void setFrontImage(String frontImage) {
     this.frontImage = frontImage;
   }
 
   public String getNegativeImage() {
     return this.negativeImage;
   }
 
   public void setNegativeImage(String negativeImage) {
     this.negativeImage = negativeImage;
   }
 
   public Timestamp getUpLoadTime() {
     return this.upLoadTime;
   }
 
   public void setUpLoadTime(Timestamp upLoadTime) {
     this.upLoadTime = upLoadTime;
   }
 
   public Integer getExamineStatus() {
     return this.examineStatus;
   }
 
   public void setExamineStatus(Integer examineStatus) {
     this.examineStatus = examineStatus;
   }
 }

