 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Moneymax
   implements Serializable
 {
   private Integer moneyMaxId;
   private Integer moneyMaxName;
   private Set lssuings = new HashSet(0);
 
   public Moneymax()
   {
   }
 
   public Moneymax(Integer moneyMaxId, Integer moneyMaxName)
   {
     this.moneyMaxId = moneyMaxId;
     this.moneyMaxName = moneyMaxName;
   }
 
   public Moneymax(Integer moneyMaxId, Integer moneyMaxName, Set lssuings)
   {
     this.moneyMaxId = moneyMaxId;
     this.moneyMaxName = moneyMaxName;
     this.lssuings = lssuings;
   }
 
   public Integer getMoneyMaxId()
   {
     return this.moneyMaxId;
   }
 
   public void setMoneyMaxId(Integer moneyMaxId) {
     this.moneyMaxId = moneyMaxId;
   }
 
   public Integer getMoneyMaxName() {
     return this.moneyMaxName;
   }
 
   public void setMoneyMaxName(Integer moneyMaxName) {
     this.moneyMaxName = moneyMaxName;
   }
 
   public Set getLssuings() {
     return this.lssuings;
   }
 
   public void setLssuings(Set lssuings) {
     this.lssuings = lssuings;
   }
 }

