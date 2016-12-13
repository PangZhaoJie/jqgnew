 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Partnerdetail
   implements Serializable
 {
   private Integer detailId;
   private Partner partner;
   private String companyName;
   private String detail;
   private String photo;
 
   public Partnerdetail()
   {
   }
 
   public Partnerdetail(Integer detailId)
   {
     this.detailId = detailId;
   }
 
   public Partnerdetail(Integer detailId, Partner partner, String companyName, String detail, String photo)
   {
     this.detailId = detailId;
     this.partner = partner;
     this.companyName = companyName;
     this.detail = detail;
     this.photo = photo;
   }
 
   public Integer getDetailId()
   {
     return this.detailId;
   }
 
   public void setDetailId(Integer detailId) {
     this.detailId = detailId;
   }
 
   public Partner getPartner() {
     return this.partner;
   }
 
   public void setPartner(Partner partner) {
     this.partner = partner;
   }
 
   public String getCompanyName() {
     return this.companyName;
   }
 
   public void setCompanyName(String companyName) {
     this.companyName = companyName;
   }
 
   public String getDetail() {
     return this.detail;
   }
 
   public void setDetail(String detail) {
     this.detail = detail;
   }
 
   public String getPhoto() {
     return this.photo;
   }
 
   public void setPhoto(String photo) {
     this.photo = photo;
   }
 }

