 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Partner
   implements Serializable
 {
   private Integer partnerId;
   private String menuName;
   private Set partnerdetails = new HashSet(0);
 
   public Partner()
   {
   }
 
   public Partner(Integer partnerId)
   {
     this.partnerId = partnerId;
   }
 
   public Partner(Integer partnerId, String menuName, Set partnerdetails)
   {
     this.partnerId = partnerId;
     this.menuName = menuName;
     this.partnerdetails = partnerdetails;
   }
 
   public Integer getPartnerId()
   {
     return this.partnerId;
   }
 
   public void setPartnerId(Integer partnerId) {
     this.partnerId = partnerId;
   }
 
   public String getMenuName() {
     return this.menuName;
   }
 
   public void setMenuName(String menuName) {
     this.menuName = menuName;
   }
 
   public Set getPartnerdetails() {
     return this.partnerdetails;
   }
 
   public void setPartnerdetails(Set partnerdetails) {
     this.partnerdetails = partnerdetails;
   }
 }

