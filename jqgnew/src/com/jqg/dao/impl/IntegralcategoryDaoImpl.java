 package com.jqg.dao.impl;
 
 import com.jqg.dao.IntegralcategoryDao;
import com.jqg.pojo.Integralcategory;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class IntegralcategoryDaoImpl
   implements IntegralcategoryDao
 {
   public boolean addIntegralcategory(Integralcategory integralcategory)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(integralcategory);
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
 
   public boolean updateIntegralcategory(Integralcategory integralcategory)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(integralcategory);
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
 
   public boolean deleteIntegralcategory(Integralcategory integralcategory)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(integralcategory);
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
 
   public List<Integralcategory> findIntegralcategorys()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List integralcategories = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integralcategory");
       integralcategories = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integralcategories;
   }
 
   public Integralcategory findIntegralcategoryByintCategoryId(int intCategoryId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Integralcategory integralcategory = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integralcategory i where i.intCategoryId=?");
       query.setInteger(0, intCategoryId);
       integralcategory = (Integralcategory)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integralcategory;
   }
 }

