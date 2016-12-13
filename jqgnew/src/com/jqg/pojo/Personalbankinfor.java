 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Personalbankinfor
   implements Serializable
 {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer personalBankInforId;
   private Bankparameter bankparameter;
   private Uservip uservip;
   private String accountName;
   private String accountNum;
   private String accountAddress;
   private String accountNums;//临时存放用户的银行卡号  页面使用
   private Area area;
 
   public Personalbankinfor()
   {
   }
 
   public Personalbankinfor(Integer personalBankInforId)
   {
     this.personalBankInforId = personalBankInforId;
   }
 
   public Personalbankinfor(Integer personalBankInforId, Bankparameter bankparameter, Uservip uservip, String accountName, String accountNum, String accountAddress)
   {
     this.personalBankInforId = personalBankInforId;
     this.bankparameter = bankparameter;
     this.uservip = uservip;
     this.accountName = accountName;
     this.accountNum = accountNum;
     this.accountAddress = accountAddress;
   }
 
   public Integer getPersonalBankInforId()
   {
     return this.personalBankInforId;
   }
 
   public void setPersonalBankInforId(Integer personalBankInforId) {
     this.personalBankInforId = personalBankInforId;
   }
 
   public Bankparameter getBankparameter() {
     return this.bankparameter;
   }
 
   public void setBankparameter(Bankparameter bankparameter) {
     this.bankparameter = bankparameter;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getAccountName() {
     return this.accountName;
   }
 
   public void setAccountName(String accountName) {
     this.accountName = accountName;
   }
 
   public String getAccountNum() {
     return this.accountNum;
   }
 
   public void setAccountNum(String accountNum) {
     this.accountNum = accountNum;
   }
 
   public String getAccountAddress() {
     return this.accountAddress;
   }
 
   public void setAccountAddress(String accountAddress) {
     this.accountAddress = accountAddress;
   }

	public String getAccountNums() {
		return accountNums;
	}
	
	public void setAccountNums(String accountNums) {
		this.accountNums = accountNums;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	
   
 }

