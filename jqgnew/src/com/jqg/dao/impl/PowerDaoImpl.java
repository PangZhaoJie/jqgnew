 package com.jqg.dao.impl;
 
 import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jqg.dao.PowerDao;
import com.jqg.pojo.Power;
import com.jqg.session.factory.HibernateSessionFactory;
 
 public class PowerDaoImpl
   implements PowerDao
 {
	   public boolean addPower(Power power)
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     boolean flag = false;
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       session.save(power);
			       //transaction.commit();
			       flag = true;
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return flag;
			   }
			 
			   public boolean deletePower(Power power)
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     boolean flag = false;
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       session.delete(power);
			       //transaction.commit();
			       flag = true;
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return flag;
			   }
			 
			   public Power findPowerByPowerId(Integer powerId)
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     Power power = null;
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       Query query = session.createQuery("from Power p where p.powerId=?");
			       query.setInteger(0, powerId.intValue());
			       power = (Power)query.uniqueResult();
			       //transaction.commit();
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return power;
			   }
			 
			   public List<Power> findPowers()
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     List powers = new ArrayList();
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       Query query = session.createQuery("from Power");
			       powers = query.list();
			       //transaction.commit();
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return powers;
			   }
			 
			   public boolean updatePower(Power power)
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     boolean flag = false;
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       session.merge(power);
			       //transaction.commit();
			       flag = true;
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return flag;
			   }
			 
			   public List<Power> findPowersByMeuAndRole(Integer menuId, Integer roleId)
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     List powers = new ArrayList();
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       String procName = "select * from power where menuId=" + menuId + " and roleId=" + roleId;
			       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Power.class);
			       powers = sqlQuery.list();
			       //transaction.commit();
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return powers;
			   }
			 
			   public List<Power> findPowersBySql(String sql)
			     throws Exception
			   {
			     Session session = null;
			     //Transaction transaction = null;
			     List powers = new ArrayList();
			     try {
			       session = HibernateSessionFactory.getSession();
			       
			       //transaction = session.beginTransaction();
			       String procName = sql;
			       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Power.class);
			       powers = sqlQuery.list();
			       //transaction.commit();
			     } catch (Exception e) {
			       //transaction.rollback();
			       throw e;
			     }
			     return powers;
			   }
 }

