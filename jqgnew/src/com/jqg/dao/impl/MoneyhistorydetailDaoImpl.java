 package com.jqg.dao.impl;
 
 import com.jqg.dao.MoneyhistorydetailDao;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class MoneyhistorydetailDaoImpl
   implements MoneyhistorydetailDao
 {
   public boolean addMoneyhistorydetail(Moneyhistorydetail moneyhistorydetail)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(moneyhistorydetail);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean updateMoneyhistorydetail(Moneyhistorydetail moneyhistorydetail)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(moneyhistorydetail);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteMoneyhistorydetail(Moneyhistorydetail moneyhistorydetail)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(moneyhistorydetail);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public List<Moneyhistorydetail> findMoneyhistorydetails()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneyhistorydetail");
       amountinfos = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return amountinfos;
   }
 
   public Moneyhistorydetail findMoneyhistorydetailById(int moneyHistoryDetailId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Moneyhistorydetail moneyhistorydetail = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneyhistorydetail p where p.moneyHistoryDetailId=?");
       query.setInteger(0, moneyHistoryDetailId);
       moneyhistorydetail = (Moneyhistorydetail)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneyhistorydetail;
   }
 
   public Moneyhistorydetail findMoneyhistorydetailByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Moneyhistorydetail moneyhistorydetail = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       SQLQuery query = session.createSQLQuery("select * from Moneyhistorydetail p where p.userId=" + userId).addEntity(Moneyhistorydetail.class);
       moneyhistorydetail = (Moneyhistorydetail)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneyhistorydetail;
   }
 
   public List<Moneyhistorydetail> findMoneyhistorydetailBySql(String sql) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List translates = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       SQLQuery query = session.createSQLQuery(sql).addEntity(Moneyhistorydetail.class);
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

