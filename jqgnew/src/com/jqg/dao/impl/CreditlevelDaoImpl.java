 package com.jqg.dao.impl;
 
 import com.jqg.dao.CreditlevelDao;
import com.jqg.pojo.Creditlevel;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class CreditlevelDaoImpl
   implements CreditlevelDao
 {
   public boolean addCreditlevel(Creditlevel creditlevel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(creditlevel);
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
 
   public boolean updateCreditlevel(Creditlevel creditlevel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(creditlevel);
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
 
   public boolean deleteCreditlevel(Creditlevel creditlevel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(creditlevel);
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
 
   public List<Creditlevel> findCreditlevels()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List creditlevels = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Creditlevel");
       creditlevels = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return creditlevels;
   }
 
   public Creditlevel findCreditlevelBycreditLevelId(int creditLevelId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Creditlevel creditlevel = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Creditlevel c where c.creditLevelId=?");
       query.setInteger(0, creditLevelId);
       creditlevel = (Creditlevel)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return creditlevel;
   }
 }

