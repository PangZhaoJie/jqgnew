 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Manager
   implements Serializable
 {
   private Integer managerId;
   private Role role;
   private String managerName;
   private String password;
   private String realName;
   private String qq;
   private String telephone;
   private Integer roleId;
   private Integer isBan;
   private Integer isCustomer;
   private String passwordWord;
   private Integer display;
   private String pathMages;
   private Set certifications = new HashSet(0);
   private Set oerationlogs = new HashSet(0);
   private Set managercomments = new HashSet(0);
   private Set uservips = new HashSet(0);
   private Set matercertifis = new HashSet(0);
   
   private Integer count;
 
   public Manager()
   {
   }
 
   public Manager(Integer managerId)
   {
     this.managerId = managerId;
   }
 
   public Manager(Integer managerId, Role role, String managerName, String password, String realName, String qq, String telephone, Integer roleId, Integer isBan, Integer isCustomer, String passwordWord, Set certifications, Set oerationlogs, Set managercomments, Set uservips, Set matercertifis, Integer display,String pathMages,Integer count)
   {
     this.managerId = managerId;
     this.role = role;
     this.managerName = managerName;
     this.password = password;
     this.realName = realName;
     this.qq = qq;
     this.telephone = telephone;
     this.roleId = roleId;
     this.isBan = isBan;
     this.isCustomer = isCustomer;
     this.passwordWord = passwordWord;
     this.certifications = certifications;
     this.oerationlogs = oerationlogs;
     this.managercomments = managercomments;
     this.uservips = uservips;
     this.matercertifis = matercertifis;
     this.display = display;
     this.count = count;
   }
 
   public Integer getManagerId()
   {
     return this.managerId;
   }
 
   public void setManagerId(Integer managerId) {
     this.managerId = managerId;
   }
 
   public Role getRole() {
     return this.role;
   }
 
   public void setRole(Role role) {
     this.role = role;
   }
 
   public String getManagerName() {
     return this.managerName;
   }
 
   public void setManagerName(String managerName) {
     this.managerName = managerName;
   }
 
   public String getPassword() {
     return this.password;
   }
 
   public void setPassword(String password) {
     this.password = password;
   }
 
   public String getRealName() {
     return this.realName;
   }
 
   public void setRealName(String realName) {
     this.realName = realName;
   }
 
   public String getQq() {
     return this.qq;
   }
 
   public void setQq(String qq) {
     this.qq = qq;
   }
 
   public String getTelephone() {
     return this.telephone;
   }
 
   public void setTelephone(String telephone) {
     this.telephone = telephone;
   }
 
   public Integer getRoleId() {
     return this.roleId;
   }
 
   public void setRoleId(Integer roleId) {
     this.roleId = roleId;
   }
 
   public Integer getIsBan() {
     return this.isBan;
   }
 
   public void setIsBan(Integer isBan) {
     this.isBan = isBan;
   }
 
   public Integer getIsCustomer() {
     return this.isCustomer;
   }
 
   public void setIsCustomer(Integer isCustomer) {
     this.isCustomer = isCustomer;
   }
 
   public String getPasswordWord() {
     return this.passwordWord;
   }
 
   public void setPasswordWord(String passwordWord) {
     this.passwordWord = passwordWord;
   }
 
   public Set getCertifications() {
     return this.certifications;
   }
 
   public void setCertifications(Set certifications) {
     this.certifications = certifications;
   }
 
   public Set getOerationlogs() {
     return this.oerationlogs;
   }
 
   public void setOerationlogs(Set oerationlogs) {
     this.oerationlogs = oerationlogs;
   }
 
   public Set getManagercomments() {
     return this.managercomments;
   }
 
   public void setManagercomments(Set managercomments) {
     this.managercomments = managercomments;
   }
 
   public Set getUservips() {
     return this.uservips;
   }
 
   public void setUservips(Set uservips) {
     this.uservips = uservips;
   }
 
   public Set getMatercertifis() {
     return this.matercertifis;
   }
 
   public void setMatercertifis(Set matercertifis) {
     this.matercertifis = matercertifis;
   }
 
   public Integer getDisplay() {
     return this.display;
   }
 
   public void setDisplay(Integer display) {
     this.display = display;
   }

	public String getPathMages() {
		return pathMages;
	}
	
	public void setPathMages(String pathMages) {
		this.pathMages = pathMages;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
   
 }

