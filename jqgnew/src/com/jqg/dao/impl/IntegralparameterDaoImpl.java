 package com.jqg.dao.impl;
 
 import com.jqg.dao.IntegralparameterDao;
import com.jqg.pojo.Integralparameter;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class IntegralparameterDaoImpl
   implements IntegralparameterDao
 {
   public boolean addIntegralparameter(Integralparameter integralparameter)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(integralparameter);
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
 
   public boolean updateIntegralparameter(Integralparameter integralparameter)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(integralparameter);
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
 
   public boolean deleteIntegralparameter(Integralparameter integralparameter)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(integralparameter);
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
 
   public List<Integralparameter> findIntegralparameters()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List integralparameters = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integralparameter");
       integralparameters = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integralparameters;
   }
 
   public Integralparameter findIntegralparameterByintegralPid(int integralPid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Integralparameter integralparameter = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integralparameter i where i.integralPid=?");
       query.setInteger(0, integralPid);
       integralparameter = (Integralparameter)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integralparameter;
   }
 
   public Integralparameter findIntegralparameterByIntegralPname(String integralPname)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Integralparameter integralparameter = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integralparameter i where i.integralPname=?");
       query.setParameter(0, integralPname);
       integralparameter = (Integralparameter)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integralparameter;
   }
 }

