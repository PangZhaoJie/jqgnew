 package com.jqg.dao.impl;
 
 import com.jqg.dao.AutomaticbidDao;
import com.jqg.pojo.Automaticbid;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class AutomaticbidDaoImpl
   implements AutomaticbidDao
 {
   public boolean addAutomaticbid(Automaticbid automaticbid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(automaticbid);
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
 
   public boolean updateAutomaticbid(Automaticbid automaticbid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(automaticbid);
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
 
   public boolean deleteAutomaticbid(Automaticbid automaticbid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(automaticbid);
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
 
   public List<Automaticbid> findAutomaticbids()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List automaticbids = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Automaticbid");
       automaticbids = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return automaticbids;
   }
 
   public Automaticbid findAutomaticbidByautomaticbidId(int automaticbidId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Automaticbid automaticbid = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Automaticbid a where a.automaticBidId=?");
       query.setInteger(0, automaticbidId);
       automaticbid = (Automaticbid)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return automaticbid;
   }
 
   public int queryCountAutomaticbid()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     int count = 0;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Automaticbid a where a.enable=1");
       if (query.list() != null) {
         count = query.list().size();
       }
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return count;
   }
 
   public List<Automaticbid> findAutomaticbidBySql(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List automaticbids = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Automaticbid.class);
       automaticbids = sqlQuery.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return automaticbids;
   }
 }

