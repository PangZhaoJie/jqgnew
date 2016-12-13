 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Requestquota
   implements Serializable
 {
   private Integer requestQuotaId;
   private Uservip uservip;
   private Integer requestQuota;
   private Integer examine;
   private Integer throughQuota;
   private String opinion;
   private Timestamp submitTime;
   private String applyType;
   private String applyExplain;
 
   public Requestquota()
   {
   }
 
   public Requestquota(Integer requestQuotaId)
   {
     this.requestQuotaId = requestQuotaId;
   }
 
   public Requestquota(Integer requestQuotaId, Uservip uservip, Integer requestQuota, Integer examine, Integer throughQuota, String opinion, Timestamp submitTime, String applyType, String applyExplain)
   {
     this.requestQuotaId = requestQuotaId;
     this.uservip = uservip;
     this.requestQuota = requestQuota;
     this.examine = examine;
     this.throughQuota = throughQuota;
     this.opinion = opinion;
     this.submitTime = submitTime;
     this.applyType = applyType;
     this.applyExplain = applyExplain;
   }
 
   public Integer getRequestQuotaId()
   {
     return this.requestQuotaId;
   }
 
   public void setRequestQuotaId(Integer requestQuotaId) {
     this.requestQuotaId = requestQuotaId;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Integer getRequestQuota() {
     return this.requestQuota;
   }
 
   public void setRequestQuota(Integer requestQuota) {
     this.requestQuota = requestQuota;
   }
 
   public Integer getExamine() {
     return this.examine;
   }
 
   public void setExamine(Integer examine) {
     this.examine = examine;
   }
 
   public Integer getThroughQuota() {
     return this.throughQuota;
   }
 
   public void setThroughQuota(Integer throughQuota) {
     this.throughQuota = throughQuota;
   }
 
   public String getOpinion() {
     return this.opinion;
   }
 
   public void setOpinion(String opinion) {
     this.opinion = opinion;
   }
 
   public Timestamp getSubmitTime() {
     return this.submitTime;
   }
 
   public void setSubmitTime(Timestamp submitTime) {
     this.submitTime = submitTime;
   }
 
   public String getApplyType() {
     return this.applyType;
   }
 
   public void setApplyType(String applyType) {
     this.applyType = applyType;
   }
 
   public String getApplyExplain() {
     return this.applyExplain;
   }
 
   public void setApplyExplain(String applyExplain) {
     this.applyExplain = applyExplain;
   }
 }

