 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Validtime
   implements Serializable
 {
   private Integer validTimeId;
   private String validTimeName;
   private Set lssuings = new HashSet(0);
 
   public Validtime()
   {
   }
 
   public Validtime(Integer validTimeId, String validTimeName)
   {
     this.validTimeId = validTimeId;
     this.validTimeName = validTimeName;
   }
 
   public Validtime(Integer validTimeId, String validTimeName, Set lssuings)
   {
     this.validTimeId = validTimeId;
     this.validTimeName = validTimeName;
     this.lssuings = lssuings;
   }
 
   public Integer getValidTimeId()
   {
     return this.validTimeId;
   }
 
   public void setValidTimeId(Integer validTimeId) {
     this.validTimeId = validTimeId;
   }
 
   public String getValidTimeName() {
     return this.validTimeName;
   }
 
   public void setValidTimeName(String validTimeName) {
     this.validTimeName = validTimeName;
   }
 
   public Set getLssuings() {
     return this.lssuings;
   }
 
   public void setLssuings(Set lssuings) {
     this.lssuings = lssuings;
   }
 }

