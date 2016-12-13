 package com.jqg.dao.impl;
 
 import com.jqg.dao.PlatcountDao;
import com.jqg.pojo.Platcount;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PlatcountDaoImpl
   implements PlatcountDao
 {
   public boolean addPlatcount(Platcount platcount)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(platcount);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean updatePlatcount(Platcount platcount)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(platcount);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deletePlatcount(Platcount platcount)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(platcount);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public List<Platcount> findPlatcounts()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Platcount");
       amountinfos = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return amountinfos;
   }
 
   public Platcount findPlatcountById(int countid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Platcount platcount = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Platcount p where p.countid=?");
       query.setInteger(0, countid);
       platcount = (Platcount)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return platcount;
   }
 
  
 
   public List<Platcount> findPlatcountBySql(String sql) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List translates = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       SQLQuery query = session.createSQLQuery(sql).addEntity(Platcount.class);
       translates = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return translates;
   }
 }

