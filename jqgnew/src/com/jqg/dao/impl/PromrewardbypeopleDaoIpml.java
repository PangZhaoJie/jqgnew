 package com.jqg.dao.impl;
 
 import com.jqg.dao.PromrewardbypeopleDao;
import com.jqg.pojo.Promrewardbypeople;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PromrewardbypeopleDaoIpml
   implements PromrewardbypeopleDao
 {
   public boolean addPromrewardbypeople(Promrewardbypeople promrewardbypeople)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(promrewardbypeople);
       //transaction.commit();
       flag = true;
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean updatePromrewardbypeople(Promrewardbypeople promrewardbypeople) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(promrewardbypeople);
       //transaction.commit();
       flag = true;
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deletePromrewardbypeople(Promrewardbypeople promrewardbypeople) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(promrewardbypeople);
       //transaction.commit();
       flag = true;
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public List<Promrewardbypeople> findPromrewardbypeoples() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Promrewardbypeople");
       amountinfos = query.list();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return amountinfos;
   }
 
   public Promrewardbypeople findPromrewardbypeopleById(int promRewardByPeopleId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Promrewardbypeople promrewardbypeople = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Promrewardbypeople p where p.promRewardByPeopleId=?");
       query.setInteger(0, promRewardByPeopleId);
       promrewardbypeople = (Promrewardbypeople)query.uniqueResult();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return promrewardbypeople;
   }
 
   public List<Promrewardbypeople> findPromrewardbypeoplesByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List promrewardbypeoples = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Promrewardbypeople t where t.uservip.userId=?");
       query.setInteger(0, userId);
       promrewardbypeoples = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return promrewardbypeoples;
   }
 
   public List<Promrewardbypeople> findPromrewardbypeoplesByUserIdPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List promrewardbypeoples = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = "select * from promrewardbypeople a where a.userId=? ORDER BY a.promrewardbypeopleId DESC";
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Promrewardbypeople.class);
       sqlQuery.setInteger(0, userId);
       sqlQuery.setFirstResult(currentPage);
       sqlQuery.setMaxResults(pageSize);
       promrewardbypeoples = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return promrewardbypeoples;
   }
 
   public List<Promrewardbypeople> findPromrewardbySql(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List promrewardbypeoples = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Promrewardbypeople.class);
       promrewardbypeoples = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return promrewardbypeoples;
   }
 }

