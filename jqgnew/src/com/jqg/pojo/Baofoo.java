 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Baofoo
   implements Serializable
 {
   private Integer askTypeId;
   private Integer fatherId;
   private String askTypeName;
   private Set investstrategies = new HashSet(0);
 
   public Baofoo()
   {
   }
 
   public Baofoo(Integer askTypeId)
   {
     this.askTypeId = askTypeId;
   }
 
   public Baofoo(Integer askTypeId, Integer fatherId, String askTypeName, Set investstrategies)
   {
     this.askTypeId = askTypeId;
     this.fatherId = fatherId;
     this.askTypeName = askTypeName;
     this.investstrategies = investstrategies;
   }
 
   public Integer getAskTypeId()
   {
     return this.askTypeId;
   }
 
   public void setAskTypeId(Integer askTypeId) {
     this.askTypeId = askTypeId;
   }
 
   public Integer getFatherId() {
     return this.fatherId;
   }
 
   public void setFatherId(Integer fatherId) {
     this.fatherId = fatherId;
   }
 
   public String getAskTypeName() {
     return this.askTypeName;
   }
 
   public void setAskTypeName(String askTypeName) {
     this.askTypeName = askTypeName;
   }
 
   public Set getInveststrategies() {
     return this.investstrategies;
   }
 
   public void setInveststrategies(Set investstrategies) {
     this.investstrategies = investstrategies;
   }
 }

