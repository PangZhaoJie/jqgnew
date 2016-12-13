 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Frontmenu
   implements Serializable
 {
   private Integer frontMenuId;
   private String name;
   private String frontMenuCode;
   private Integer fatherId;
   private Integer nature;
   private Integer isDisplay;
   private int isColumn;
   private String url;
   private Set latestnewses = new HashSet(0);
 
   public Frontmenu()
   {
   }
 
   public Frontmenu(Integer frontMenuId, String name, String frontMenuCode, Integer nature, Integer isDisplay)
   {
     this.frontMenuId = frontMenuId;
     this.name = name;
     this.frontMenuCode = frontMenuCode;
     this.nature = nature;
     this.isDisplay = isDisplay;
   }
 
   public Frontmenu(Integer frontMenuId, String name, String frontMenuCode, Integer fatherId, Integer nature, Integer isDisplay, String url, Set latestnewses)
   {
     this.frontMenuId = frontMenuId;
     this.name = name;
     this.frontMenuCode = frontMenuCode;
     this.fatherId = fatherId;
     this.nature = nature;
     this.isDisplay = isDisplay;
     this.url = url;
     this.latestnewses = latestnewses;
   }
 
   public Integer getFrontMenuId()
   {
     return this.frontMenuId;
   }
 
   public void setFrontMenuId(Integer frontMenuId) {
     this.frontMenuId = frontMenuId;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getFrontMenuCode() {
     return this.frontMenuCode;
   }
 
   public void setFrontMenuCode(String frontMenuCode) {
     this.frontMenuCode = frontMenuCode;
   }
   
   
 
   public int getIsColumn() {
	return isColumn;
   }
	
   public void setIsColumn(int isColumn) {
	this.isColumn = isColumn;
   }

   public Integer getFatherId() {
     return this.fatherId;
   }
 
   public void setFatherId(Integer fatherId) {
     this.fatherId = fatherId;
   }
 
   public Integer getNature() {
     return this.nature;
   }
 
   public void setNature(Integer nature) {
     this.nature = nature;
   }
 
   public Integer getIsDisplay() {
     return this.isDisplay;
   }
 
   public void setIsDisplay(Integer isDisplay) {
     this.isDisplay = isDisplay;
   }
 
   public String getUrl() {
     return this.url;
   }
 
   public void setUrl(String url) {
     this.url = url;
   }
 
   public Set getLatestnewses() {
     return this.latestnewses;
   }
 
   public void setLatestnewses(Set latestnewses) {
     this.latestnewses = latestnewses;
   }
 }

