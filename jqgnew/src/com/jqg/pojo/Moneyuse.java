 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Moneyuse
   implements Serializable
 {
   private Integer moneyUseId;
   private String moneyUseName;
   private Set lssuings = new HashSet(0);
 
   public Moneyuse()
   {
   }
 
   public Moneyuse(Integer moneyUseId, String moneyUseName)
   {
     this.moneyUseId = moneyUseId;
     this.moneyUseName = moneyUseName;
   }
 
   public Moneyuse(Integer moneyUseId, String moneyUseName, Set lssuings)
   {
     this.moneyUseId = moneyUseId;
     this.moneyUseName = moneyUseName;
     this.lssuings = lssuings;
   }
 
   public Integer getMoneyUseId()
   {
     return this.moneyUseId;
   }
 
   public void setMoneyUseId(Integer moneyUseId) {
     this.moneyUseId = moneyUseId;
   }
 
   public String getMoneyUseName() {
     return this.moneyUseName;
   }
 
   public void setMoneyUseName(String moneyUseName) {
     this.moneyUseName = moneyUseName;
   }
 
   public Set getLssuings() {
     return this.lssuings;
   }
 
   public void setLssuings(Set lssuings) {
     this.lssuings = lssuings;
   }
 }

