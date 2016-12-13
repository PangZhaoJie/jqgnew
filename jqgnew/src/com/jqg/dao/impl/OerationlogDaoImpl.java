 package com.jqg.dao.impl;
 
 import com.jqg.dao.OerationlogDao;
import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class OerationlogDaoImpl
   implements OerationlogDao
 {
   public boolean addOerationlog(Oerationlog oerationlog)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(oerationlog);
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
 
   public boolean updateOerationlog(Oerationlog oerationlog)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(oerationlog);
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
 
   public boolean deleteOerationlog(Oerationlog oerationlog)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(oerationlog);
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
 
   public List<Oerationlog> findOerationlogs()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List oerationlogs = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Oerationlog");
       oerationlogs = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return oerationlogs;
   }
   
   public Oerationlog findOerationlogByoerationLogId(int oerationLogId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Oerationlog oerationlog = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Oerationlog o where o.oerationLogId=?");
       query.setInteger(0, oerationLogId);
       oerationlog = (Oerationlog)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return oerationlog;
   }
 
   public List<Oerationlog> findOerationlogsPage(int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List oerationlogs = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Oerationlog o ORDER BY o.oerationLogId DESC");
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
       oerationlogs = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return oerationlogs;
   }
 
   public List<Oerationlog> findOerationlogsBysql(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List oerationlogs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Oerationlog.class);
 
       oerationlogs = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return oerationlogs;
   }

public List<Oerationlog> findByManager(int  managerId) throws Exception {
	Session session = null;
	List<Oerationlog> oerationlog = null;
    try
    {
      session = HibernateSessionFactory.getSession(); 
      Query query = session.createQuery("from Oerationlog o where o.manager.managerId=?");
      query.setInteger(0, managerId);
      oerationlog = query.list();
    }
    catch (Exception e) {
      throw e;
    }
    return oerationlog;
}
 }

