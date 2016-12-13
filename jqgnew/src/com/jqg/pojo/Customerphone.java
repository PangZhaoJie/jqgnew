 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Customerphone
   implements Serializable
 {
   private Integer customerphoneId;
   private String customerphoneName;
   private String customerphoneNumber;
   private Integer display;
   private Timestamp createDate;
 
   public Customerphone()
   {
   }
 
   public Customerphone(Integer customerphoneId)
   {
     this.customerphoneId = customerphoneId;
   }
 
   public Customerphone(Integer customerphoneId, String customerphoneName, String customerphoneNumber, Integer display ,Timestamp createDate)
   {
     this.customerphoneId = customerphoneId;
     this.customerphoneName = customerphoneName;
     this.customerphoneNumber = customerphoneNumber;
     this.display = display;
     this.createDate=createDate;
   }
 
   public Integer getCustomerphoneId()
   {
     return this.customerphoneId;
   }
 
   public void setCustomerphoneId(Integer customerphoneId) {
     this.customerphoneId = customerphoneId;
   }
 
   public String getCustomerphoneName() {
     return this.customerphoneName;
   }
 
   public void setCustomerphoneName(String customerphoneName) {
     this.customerphoneName = customerphoneName;
   }
 

 
   public String getCustomerphoneNumber() {
	return customerphoneNumber;
}

public void setCustomerphoneNumber(String customerphoneNumber) {
	this.customerphoneNumber = customerphoneNumber;
}

public Integer getDisplay() {
     return this.display;
   }
 
   public void setDisplay(Integer display) {
     this.display = display;
   }

public Timestamp getCreateDate() {
	return createDate;
}

public void setCreateDate(Timestamp createDate) {
	this.createDate = createDate;
}
   
   
 }

