 package com.jqg.service.impl;
 
 import com.jqg.dao.RoleDao;
import com.jqg.dao.impl.RoleDaoImpl;
import com.jqg.pojo.Role;
import com.jqg.service.RoleService;

import java.util.List;
 
 public class RoleServiceImpl
   implements RoleService
 {

	   RoleDao roleDao = new RoleDaoImpl();
	 
	   public boolean addRole(Role role)
	     throws Exception
	   {
	     return this.roleDao.addRole(role);
	   }
	 
	   public boolean updateRole(Role role)
	     throws Exception
	   {
	     return this.roleDao.updateRole(role);
	   }
	 
	   public boolean deleteRole(Role role)
	     throws Exception
	   {
	     return this.roleDao.deleteRole(role);
	   }
	 
	   public List<Role> findRoles()
	     throws Exception
	   {
	     return this.roleDao.findRoles();
	   }
	 
	   public Role findRoleByRoleId(Integer roleId)
	     throws Exception
	   {
	     return this.roleDao.findRoleByRoleId(roleId);
	   }
	 
	   public Integer createRole(Role role)
	     throws Exception
	   {
	     return this.roleDao.createRole(role);
	   }
	 
	   public String findRoleByName(String roleName, Integer roleId)
	     throws Exception
	   {
	     return this.roleDao.findRoleByName(roleName, roleId);
	   }
	 
	   public List<Role> findRolesBysql(String sql)
	     throws Exception
	   {
	     return this.roleDao.findRolesBysql(sql);
	   }
 }

