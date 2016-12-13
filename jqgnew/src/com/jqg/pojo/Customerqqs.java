 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Customerqqs
   implements Serializable
 {
   private Integer customerQqsid;
   private String customerQqsname;
   private String customerQqsnumber;
   private Integer display;
 
   public Customerqqs()
   {
   }
 
   public Customerqqs(Integer customerQqsid)
   {
     this.customerQqsid = customerQqsid;
   }
 
   public Customerqqs(Integer customerQqsid, String customerQqsname, String customerQqsnumber, Integer display)
   {
     this.customerQqsid = customerQqsid;
     this.customerQqsname = customerQqsname;
     this.customerQqsnumber = customerQqsnumber;
     this.display = display;
   }
 
   public Integer getCustomerQqsid()
   {
     return this.customerQqsid;
   }
 
   public void setCustomerQqsid(Integer customerQqsid) {
     this.customerQqsid = customerQqsid;
   }
 
   public String getCustomerQqsname() {
     return this.customerQqsname;
   }
 
   public void setCustomerQqsname(String customerQqsname) {
     this.customerQqsname = customerQqsname;
   }
 
   public String getCustomerQqsnumber() {
     return this.customerQqsnumber;
   }
 
   public void setCustomerQqsnumber(String customerQqsnumber) {
     this.customerQqsnumber = customerQqsnumber;
   }
 
   public Integer getDisplay() {
     return this.display;
   }
 
   public void setDisplay(Integer display) {
     this.display = display;
   }
 }

