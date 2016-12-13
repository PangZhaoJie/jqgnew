 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Matercertifi
   implements Serializable
 {
   private Integer materCertifiId;
   private Manager manager;
   private Dataclassification dataclassification;
   private Uservip uservip;
   private String fileName;
   private String type;
   private String size;
   private Integer status;
   private String fileUrl;
   private Timestamp upLoadTime;
   private Timestamp examineTime;
   private String examineContent;
   private String bonusPoints;
   private String materialsName;
 
   public Matercertifi()
   {
   }
 
   public Matercertifi(Integer materCertifiId)
   {
     this.materCertifiId = materCertifiId;
   }
 
   public Matercertifi(Integer materCertifiId, Manager manager, Dataclassification dataclassification, Uservip uservip, String fileName, String type, String size, Integer status, String fileUrl, Timestamp upLoadTime, Timestamp examineTime, String examineContent, String bonusPoints, String materialsName)
   {
     this.materCertifiId = materCertifiId;
     this.manager = manager;
     this.dataclassification = dataclassification;
     this.uservip = uservip;
     this.fileName = fileName;
     this.type = type;
     this.size = size;
     this.status = status;
     this.fileUrl = fileUrl;
     this.upLoadTime = upLoadTime;
     this.examineTime = examineTime;
     this.examineContent = examineContent;
     this.bonusPoints = bonusPoints;
     this.materialsName = materialsName;
   }
 
   public Integer getMaterCertifiId()
   {
     return this.materCertifiId;
   }
 
   public void setMaterCertifiId(Integer materCertifiId) {
     this.materCertifiId = materCertifiId;
   }
 
   public Manager getManager() {
     return this.manager;
   }
 
   public void setManager(Manager manager) {
     this.manager = manager;
   }
 
   public Dataclassification getDataclassification() {
     return this.dataclassification;
   }
 
   public void setDataclassification(Dataclassification dataclassification) {
     this.dataclassification = dataclassification;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getFileName() {
     return this.fileName;
   }
 
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
 
   public String getType() {
     return this.type;
   }
 
   public void setType(String type) {
     this.type = type;
   }
 
   public String getSize() {
     return this.size;
   }
 
   public void setSize(String size) {
     this.size = size;
   }
 
   public Integer getStatus() {
     return this.status;
   }
 
   public void setStatus(Integer status) {
     this.status = status;
   }
 
   public String getFileUrl() {
     return this.fileUrl;
   }
 
   public void setFileUrl(String fileUrl) {
     this.fileUrl = fileUrl;
   }
 
   public Timestamp getUpLoadTime() {
     return this.upLoadTime;
   }
 
   public void setUpLoadTime(Timestamp upLoadTime) {
     this.upLoadTime = upLoadTime;
   }
 
   public Timestamp getExamineTime() {
     return this.examineTime;
   }
 
   public void setExamineTime(Timestamp examineTime) {
     this.examineTime = examineTime;
   }
 
   public String getExamineContent() {
     return this.examineContent;
   }
 
   public void setExamineContent(String examineContent) {
     this.examineContent = examineContent;
   }
 
   public String getBonusPoints() {
     return this.bonusPoints;
   }
 
   public void setBonusPoints(String bonusPoints) {
     this.bonusPoints = bonusPoints;
   }
 
   public String getMaterialsName() {
     return this.materialsName;
   }
 
   public void setMaterialsName(String materialsName) {
     this.materialsName = materialsName;
   }
 }

