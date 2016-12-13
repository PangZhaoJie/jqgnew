 package com.jqg.dao.impl;
 
 import com.jqg.dao.EmailSendLogDao;
import com.jqg.pojo.EmailSendLog;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class EmailSendLogDaoIpml
   implements EmailSendLogDao
 {
   public boolean addEmailSendLog(EmailSendLog emailSendLog)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(emailSendLog);
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
 
   public boolean updateEmailSendLog(EmailSendLog emailSendLog) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(emailSendLog);
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
 
   public boolean deleteEmailSendLog(EmailSendLog emailSendLog) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(emailSendLog);
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
 
   public List<EmailSendLog> findEmailSendLogs() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from EmailSendLog");
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
 
   public EmailSendLog findEmailSendLogByEmailSendLogId(int emailSendLogId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     EmailSendLog emailSendLog = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from EmailSendLog p where p.emailSendLogId=?");
       query.setInteger(0, emailSendLogId);
       emailSendLog = (EmailSendLog)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return emailSendLog;
   }
 
   public List<EmailSendLog> findEmailSendLogsByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List emailSendLogs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from EmailSendLog t where t.uservip.userId=?");
       query.setInteger(0, userId);
       emailSendLogs = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return emailSendLogs;
   }
 
   public List<EmailSendLog> findEmailSendLogsByUserIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List emailSendLogs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(EmailSendLog.class);
 
       emailSendLogs = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return emailSendLogs;
   }
 }

