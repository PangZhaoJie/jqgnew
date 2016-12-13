 package com.jqg.dao.impl;
 
 import com.jqg.dao.InboxDao;
import com.jqg.pojo.Inbox;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class InboxDaoIpml
   implements InboxDao
 {
   public boolean addInbox(Inbox inbox)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(inbox);
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
 
   public boolean updateInbox(Inbox inbox) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(inbox);
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
 
   public boolean deleteInbox(Inbox inbox) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(inbox);
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
 
   public List<Inbox> findInboxs() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Inbox");
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
 
   public Inbox findInboxByInboxId(int inboxId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Inbox inbox = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Inbox p where p.inboxId=?");
       query.setInteger(0, inboxId);
       inbox = (Inbox)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return inbox;
   }
 
   public List<Inbox> findInboxsByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List inboxs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Inbox t where t.uservip.userId=?");
       query.setInteger(0, userId);
       inboxs = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return inboxs;
   }
 
   public List<Inbox> findInboxsByUserIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List inboxs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Inbox.class);
 
       inboxs = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return inboxs;
   }
 }

