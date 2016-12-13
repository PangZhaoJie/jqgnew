 package com.jqg.dao.impl;
 
 import com.jqg.dao.InternallettermodelDao;
import com.jqg.pojo.Internallettermodel;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class InternallettermodelDaoImpl
   implements InternallettermodelDao
 {
   public boolean addInternallettermodel(Internallettermodel internallettermodel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(internallettermodel);
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
 
   public boolean updateInternallettermodel(Internallettermodel internallettermodel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(internallettermodel);
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
 
   public boolean deleteInternallettermodel(Internallettermodel internallettermodel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(internallettermodel);
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
 
   public List<Internallettermodel> findInternallettermodels()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List internallettermodels = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Internallettermodel");
       internallettermodels = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return internallettermodels;
   }
 
   public Internallettermodel findInternallettermodelByinterModelId(int interModelId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Internallettermodel internallettermodel = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Internallettermodel i where i.interModelId=?");
       query.setInteger(0, interModelId);
       internallettermodel = (Internallettermodel)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return internallettermodel;
   }
 }

