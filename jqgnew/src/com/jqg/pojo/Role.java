 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Role
   implements Serializable
 {
   private Integer roleId;
   private String roleName;
   private Set powers = new HashSet(0);
   private Set managers = new HashSet(0);
 
   public Role()
   {
   }
 
   public Role(Integer roleId)
   {
     this.roleId = roleId;
   }
 
   public Role(Integer roleId, String roleName, Set powers, Set managers)
   {
     this.roleId = roleId;
     this.roleName = roleName;
     this.powers = powers;
     this.managers = managers;
   }
 
   public Integer getRoleId()
   {
     return this.roleId;
   }
 
   public void setRoleId(Integer roleId) {
     this.roleId = roleId;
   }
 
   public String getRoleName() {
     return this.roleName;
   }
 
   public void setRoleName(String roleName) {
     this.roleName = roleName;
   }
 
   public Set getPowers() {
     return this.powers;
   }
 
   public void setPowers(Set powers) {
     this.powers = powers;
   }
 
   public Set getManagers() {
     return this.managers;
   }
 
   public void setManagers(Set managers) {
     this.managers = managers;
   }
 }

