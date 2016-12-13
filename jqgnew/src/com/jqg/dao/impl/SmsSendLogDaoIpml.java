 package com.jqg.dao.impl;
 
 import com.jqg.dao.SmsSendLogDao;
import com.jqg.pojo.SmsSendLog;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class SmsSendLogDaoIpml
   implements SmsSendLogDao
 {
   public boolean addSmsSendLog(SmsSendLog smsSendLog)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(smsSendLog);
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
 
   public boolean updateSmsSendLog(SmsSendLog smsSendLog) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(smsSendLog);
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
 
   public boolean deleteSmsSendLog(SmsSendLog smsSendLog) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(smsSendLog);
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
 
   public List<SmsSendLog> findSmsSendLogs() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from SmsSendLog");
       amountinfos = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return amountinfos;
   }
 
   public SmsSendLog findSmsSendLogBySmsSendLogId(int smsSendLogId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     SmsSendLog smsSendLog = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from SmsSendLog p where p.smsSendLogId=?");
       query.setInteger(0, smsSendLogId);
       smsSendLog = (SmsSendLog)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return smsSendLog;
   }
 
   public List<SmsSendLog> findSmsSendLogsByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List smsSendLogs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from SmsSendLog t where t.uservip.userId=?");
       query.setInteger(0, userId);
       smsSendLogs = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return smsSendLogs;
   }
 
   public List<SmsSendLog> findSmsSendLogsByUserIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List smsSendLogs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(SmsSendLog.class);
 
       smsSendLogs = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return smsSendLogs;
   }
 }

