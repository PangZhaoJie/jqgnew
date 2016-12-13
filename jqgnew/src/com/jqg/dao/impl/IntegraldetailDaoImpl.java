 package com.jqg.dao.impl;
 
 import com.jqg.dao.IntegraldetailDao;
import com.jqg.pojo.Integraldetail;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class IntegraldetailDaoImpl
   implements IntegraldetailDao
 {
   public boolean addIntegraldetail(Integraldetail integraldetail)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(integraldetail);
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
 
   public boolean updateIntegraldetail(Integraldetail integraldetail)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(integraldetail);
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
 
   public boolean deleteIntegraldetail(Integraldetail integraldetail)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(integraldetail);
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
 
   public List<Integraldetail> findIntegraldetails()
     throws Exception
   {
     return null;
   }
 
   public Integraldetail findIntegraldetailByintegralId(int integralId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Integraldetail integraldetail = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integraldetail a where a.integralId=?");
       query.setInteger(0, integralId);
       integraldetail = (Integraldetail)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integraldetail;
   }
 
   public List<Integraldetail> findIntegraldetailsByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List integraldetails = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Integraldetail t where t.uservip.userId=? order by integralTime");
       query.setInteger(0, userId);
       integraldetails = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integraldetails;
   }
 
   public List<Integraldetail> findIntegraldetailsByUserIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List integraldetails = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Integraldetail.class);
 
       integraldetails = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return integraldetails;
   }
 }

