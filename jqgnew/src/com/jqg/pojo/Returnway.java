 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Returnway
   implements Serializable
 {
   private Integer returnWayId;
   private String returnWayName;
   private Set lssuingheralds = new HashSet(0);
   private Set lssuings = new HashSet(0);
   private Set automaticbids = new HashSet(0);
 
   public Returnway()
   {
   }
 
   public Returnway(Integer returnWayId, String returnWayName)
   {
     this.returnWayId = returnWayId;
     this.returnWayName = returnWayName;
   }
 
   public Returnway(Integer returnWayId, String returnWayName, Set lssuingheralds, Set lssuings, Set automaticbids)
   {
     this.automaticbids = automaticbids;
     this.returnWayId = returnWayId;
     this.returnWayName = returnWayName;
     this.lssuingheralds = lssuingheralds;
     this.lssuings = lssuings;
   }
 
   public Integer getReturnWayId()
   {
     return this.returnWayId;
   }
 
   public Set getAutomaticbids() {
     return this.automaticbids;
   }
 
   public void setAutomaticbids(Set automaticbids) {
     this.automaticbids = automaticbids;
   }
 
   public void setReturnWayId(Integer returnWayId) {
     this.returnWayId = returnWayId;
   }
 
   public String getReturnWayName() {
     return this.returnWayName;
   }
 
   public void setReturnWayName(String returnWayName) {
     this.returnWayName = returnWayName;
   }
 
   public Set getLssuingheralds() {
     return this.lssuingheralds;
   }
 
   public void setLssuingheralds(Set lssuingheralds) {
     this.lssuingheralds = lssuingheralds;
   }
 
   public Set getLssuings() {
     return this.lssuings;
   }
 
   public void setLssuings(Set lssuings) {
     this.lssuings = lssuings;
   }
 }

