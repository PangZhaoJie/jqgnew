 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Moneymin
   implements Serializable
 {
   private Integer moneyMinId;
   private Integer moneyMinName;
   private Set lssuings = new HashSet(0);
 
   public Moneymin()
   {
   }
 
   public Moneymin(Integer moneyMinId, Integer moneyMinName)
   {
     this.moneyMinId = moneyMinId;
     this.moneyMinName = moneyMinName;
   }
 
   public Moneymin(Integer moneyMinId, Integer moneyMinName, Set lssuings)
   {
     this.moneyMinId = moneyMinId;
     this.moneyMinName = moneyMinName;
     this.lssuings = lssuings;
   }
 
   public Integer getMoneyMinId()
   {
     return this.moneyMinId;
   }
 
   public void setMoneyMinId(Integer moneyMinId) {
     this.moneyMinId = moneyMinId;
   }
 
   public Integer getMoneyMinName() {
     return this.moneyMinName;
   }
 
   public void setMoneyMinName(Integer moneyMinName) {
     this.moneyMinName = moneyMinName;
   }
 
   public Set getLssuings() {
     return this.lssuings;
   }
 
   public void setLssuings(Set lssuings) {
     this.lssuings = lssuings;
   }
 }

