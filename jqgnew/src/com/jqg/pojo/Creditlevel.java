 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Creditlevel
   implements Serializable
 {
   private Integer creditLevelId;
   private String creditLevelName;
   private String creditLevelStart;
   private String creditLevelEnd;
   private String creditLevelPicture;
   private Set uservips = new HashSet(0);
 
   public Creditlevel()
   {
   }
 
   public Creditlevel(Integer creditLevelId)
   {
     this.creditLevelId = creditLevelId;
   }
 
   public Creditlevel(Integer creditLevelId, String creditLevelName, String creditLevelStart, String creditLevelEnd, String creditLevelPicture, Set uservips)
   {
     this.creditLevelId = creditLevelId;
     this.creditLevelName = creditLevelName;
     this.creditLevelStart = creditLevelStart;
     this.creditLevelEnd = creditLevelEnd;
     this.creditLevelPicture = creditLevelPicture;
     this.uservips = uservips;
   }
 
   public Integer getCreditLevelId()
   {
     return this.creditLevelId;
   }
 
   public void setCreditLevelId(Integer creditLevelId) {
     this.creditLevelId = creditLevelId;
   }
 
   public String getCreditLevelName() {
     return this.creditLevelName;
   }
 
   public void setCreditLevelName(String creditLevelName) {
     this.creditLevelName = creditLevelName;
   }
 
   public String getCreditLevelStart() {
     return this.creditLevelStart;
   }
 
   public void setCreditLevelStart(String creditLevelStart) {
     this.creditLevelStart = creditLevelStart;
   }
 
   public String getCreditLevelEnd() {
     return this.creditLevelEnd;
   }
 
   public void setCreditLevelEnd(String creditLevelEnd) {
     this.creditLevelEnd = creditLevelEnd;
   }
 
   public String getCreditLevelPicture() {
     return this.creditLevelPicture;
   }
 
   public void setCreditLevelPicture(String creditLevelPicture) {
     this.creditLevelPicture = creditLevelPicture;
   }
 
   public Set getUservips() {
     return this.uservips;
   }
 
   public void setUservips(Set uservips) {
     this.uservips = uservips;
   }
 }

