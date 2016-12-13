 package com.jqg.dao.impl;
 
 import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jqg.dao.RoleDao;
import com.jqg.pojo.Role;
import com.jqg.session.factory.HibernateSessionFactory;
 
 public class RoleDaoImpl
   implements RoleDao
 {

	   public boolean addRole(Role role)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     boolean flag = false;
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       session.save(role);
	       //transaction.commit();
	       flag = true;
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return flag;
	   }
	 
	   public boolean deleteRole(Role role)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     boolean flag = false;
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       session.delete(role);
	       //transaction.commit();
	       flag = true;
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return flag;
	   }
	 
	   public Role findRoleByRoleId(Integer roleId)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     Role role = null;
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Role r where r.roleId=?");
	       query.setInteger(0, roleId.intValue());
	       role = (Role)query.uniqueResult();
	       //transaction.commit();
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return role;
	   }
	 
	   public List<Role> findRoles()
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     List roles = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Role");
	       roles = query.list();
	       //transaction.commit();
	     } catch (Exception e) {
	       throw e;
	     }
	     return roles;
	   }
	 
	   public boolean updateRole(Role role)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     boolean flag = false;
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       session.merge(role);
	       //transaction.commit();
	       flag = true;
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return flag;
	   }
	 
	   public Integer createRole(Role role)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     Integer flag = null;
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       session.save(role);
	       //transaction.commit();
	       flag = role.getRoleId();
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return flag;
	   }
	 
	   public String findRoleByName(String roleName, Integer roleId)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     Role role = null;
	     String flag = "0";
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Role r where r.roleName=? and r.roleId!=?");
	       query.setParameter(0, roleName);
	       query.setInteger(1, roleId.intValue());
	       role = (Role)query.uniqueResult();
	       if (role != null) {
	         flag = "1";
	       }
	       //transaction.commit();
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return flag;
	   }
	 
	   public List<Role> findRolesBysql(String sql)
	     throws Exception
	   {
	     Session session = null;
	     //Transaction transaction = null;
	     List roles = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       String procName = sql;
	       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Role.class);
	       roles = sqlQuery.list();
	       //transaction.commit();
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }
	     return roles;
	   }
 }

