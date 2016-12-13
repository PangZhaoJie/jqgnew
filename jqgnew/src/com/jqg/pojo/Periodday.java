 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Periodday
   implements Serializable
 {
   private Integer periodDayId;
   private String periodDayName;
   private Set lssuings = new HashSet(0);
 
   public Periodday()
   {
   }
 
   public Periodday(Integer periodDayId)
   {
     this.periodDayId = periodDayId;
   }
 
   public Periodday(Integer periodDayId, String periodDayName, Set lssuings)
   {
     this.periodDayId = periodDayId;
     this.periodDayName = periodDayName;
     this.lssuings = lssuings;
   }
 
   public Integer getPeriodDayId()
   {
     return this.periodDayId;
   }
 
   public void setPeriodDayId(Integer periodDayId) {
     this.periodDayId = periodDayId;
   }
 
   public String getPeriodDayName() {
     return this.periodDayName;
   }
 
   public void setPeriodDayName(String periodDayName) {
     this.periodDayName = periodDayName;
   }
 
   public Set getLssuings() {
     return this.lssuings;
   }
 
   public void setLssuings(Set lssuings) {
     this.lssuings = lssuings;
   }
 }

