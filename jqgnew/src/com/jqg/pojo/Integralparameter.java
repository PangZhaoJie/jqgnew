 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Integralparameter
   implements Serializable
 {
   private Integer integralPid;
   private String integralPname;
   private String integralPnumber;
   private String integralPdescription;
 
   public Integralparameter()
   {
   }
 
   public Integralparameter(Integer integralPid)
   {
     this.integralPid = integralPid;
   }
 
   public Integralparameter(Integer integralPid, String integralPname, String integralPnumber, String integralPdescription)
   {
     this.integralPid = integralPid;
     this.integralPname = integralPname;
     this.integralPnumber = integralPnumber;
     this.integralPdescription = integralPdescription;
   }
 
   public Integer getIntegralPid()
   {
     return this.integralPid;
   }
 
   public void setIntegralPid(Integer integralPid) {
     this.integralPid = integralPid;
   }
 
   public String getIntegralPname() {
     return this.integralPname;
   }
 
   public void setIntegralPname(String integralPname) {
     this.integralPname = integralPname;
   }
 
   public String getIntegralPnumber() {
     return this.integralPnumber;
   }
 
   public void setIntegralPnumber(String integralPnumber) {
     this.integralPnumber = integralPnumber;
   }
 
   public String getIntegralPdescription() {
     return this.integralPdescription;
   }
 
   public void setIntegralPdescription(String integralPdescription) {
     this.integralPdescription = integralPdescription;
   }
 }

