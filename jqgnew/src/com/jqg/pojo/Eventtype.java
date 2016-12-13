 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Eventtype
   implements Serializable
 {
   private Integer eventTypeId;
   private String eventName;
   private Set moneyhistorydetails = new HashSet(0);
 
   public Eventtype()
   {
   }
 
   public Eventtype(Integer eventTypeId)
   {
     this.eventTypeId = eventTypeId;
   }
 
   public Eventtype(Integer eventTypeId, String eventName, Set moneyhistorydetails)
   {
     this.eventTypeId = eventTypeId;
     this.eventName = eventName;
     this.moneyhistorydetails = moneyhistorydetails;
   }
 
   public Integer getEventTypeId()
   {
     return this.eventTypeId;
   }
 
   public void setEventTypeId(Integer eventTypeId) {
     this.eventTypeId = eventTypeId;
   }
 
   public String getEventName() {
     return this.eventName;
   }
 
   public void setEventName(String eventName) {
     this.eventName = eventName;
   }
 
   public Set getMoneyhistorydetails() {
     return this.moneyhistorydetails;
   }
 
   public void setMoneyhistorydetails(Set moneyhistorydetails) {
     this.moneyhistorydetails = moneyhistorydetails;
   }
 }

