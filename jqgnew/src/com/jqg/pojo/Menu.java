 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Menu
   implements Serializable
 {
   private Integer menuId;
   private String menuName;
   private Set powers = new HashSet(0);
 
   public Menu()
   {
   }
 
   public Menu(Integer menuId, String menuName)
   {
     this.menuId = menuId;
     this.menuName = menuName;
   }
 
   public Menu(Integer menuId, String menuName, Set powers)
   {
     this.menuId = menuId;
     this.menuName = menuName;
     this.powers = powers;
   }
 
   public Integer getMenuId()
   {
     return this.menuId;
   }
 
   public void setMenuId(Integer menuId) {
     this.menuId = menuId;
   }
 
   public String getMenuName() {
     return this.menuName;
   }
 
   public void setMenuName(String menuName) {
     this.menuName = menuName;
   }
 
   public Set getPowers() {
     return this.powers;
   }
 
   public void setPowers(Set powers) {
     this.powers = powers;
   }
 }

