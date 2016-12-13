 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Integralcategory
   implements Serializable
 {
   private Integer intCategoryId;
   private String intCategoryName;
   private Set integraldetails = new HashSet(0);
 
   public Integralcategory()
   {
   }
 
   public Integralcategory(Integer intCategoryId)
   {
     this.intCategoryId = intCategoryId;
   }
 
   public Integralcategory(Integer intCategoryId, String intCategoryName, Set integraldetails)
   {
     this.intCategoryId = intCategoryId;
     this.intCategoryName = intCategoryName;
     this.integraldetails = integraldetails;
   }
 
   public Integer getIntCategoryId()
   {
     return this.intCategoryId;
   }
 
   public void setIntCategoryId(Integer intCategoryId) {
     this.intCategoryId = intCategoryId;
   }
 
   public String getIntCategoryName() {
     return this.intCategoryName;
   }
 
   public void setIntCategoryName(String intCategoryName) {
     this.intCategoryName = intCategoryName;
   }
 
   public Set getIntegraldetails() {
     return this.integraldetails;
   }
 
   public void setIntegraldetails(Set integraldetails) {
     this.integraldetails = integraldetails;
   }
 }

