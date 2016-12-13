 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Dataclassification
   implements Serializable
 {
   private Integer dataClassId;
   private String dataClassName;
   private Set matercertifis = new HashSet(0);
 
   public Dataclassification()
   {
   }
 
   public Dataclassification(Integer dataClassId)
   {
     this.dataClassId = dataClassId;
   }
 
   public Dataclassification(Integer dataClassId, String dataClassName, Set matercertifis)
   {
     this.dataClassId = dataClassId;
     this.dataClassName = dataClassName;
     this.matercertifis = matercertifis;
   }
 
   public Integer getDataClassId()
   {
     return this.dataClassId;
   }
 
   public void setDataClassId(Integer dataClassId) {
     this.dataClassId = dataClassId;
   }
 
   public String getDataClassName() {
     return this.dataClassName;
   }
 
   public void setDataClassName(String dataClassName) {
     this.dataClassName = dataClassName;
   }
 
   public Set getMatercertifis() {
     return this.matercertifis;
   }
 
   public void setMatercertifis(Set matercertifis) {
     this.matercertifis = matercertifis;
   }
 }

