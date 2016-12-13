 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Bankparameter
   implements Serializable
 {
   private Integer bankPid;
   private String bankPname;
   private String bankPnumber;
   private Set personalbankinfors = new HashSet(0);
   private Set companybankinfors = new HashSet(0);
 
   public Bankparameter()
   {
   }
 
   public Bankparameter(Integer bankPid)
   {
     this.bankPid = bankPid;
   }
 
   public Bankparameter(Integer bankPid, String bankPname, String bankPnumber, Set personalbankinfors, Set companybankinfors)
   {
     this.bankPid = bankPid;
     this.bankPname = bankPname;
     this.bankPnumber = bankPnumber;
     this.personalbankinfors = personalbankinfors;
     this.companybankinfors = companybankinfors;
   }
 
   public Integer getBankPid()
   {
     return this.bankPid;
   }
 
   public void setBankPid(Integer bankPid) {
     this.bankPid = bankPid;
   }
 
   public String getBankPname() {
     return this.bankPname;
   }
 
   public void setBankPname(String bankPname) {
     this.bankPname = bankPname;
   }
 
   public String getBankPnumber() {
     return this.bankPnumber;
   }
 
   public void setBankPnumber(String bankPnumber) {
     this.bankPnumber = bankPnumber;
   }
 
   public Set getPersonalbankinfors() {
     return this.personalbankinfors;
   }
 
   public void setPersonalbankinfors(Set personalbankinfors) {
     this.personalbankinfors = personalbankinfors;
   }
 
   public Set getCompanybankinfors() {
     return this.companybankinfors;
   }
 
   public void setCompanybankinfors(Set companybankinfors) {
     this.companybankinfors = companybankinfors;
   }
 }

