 package com.jqg.dao.impl;
 
 import com.jqg.dao.MoneyminDao;
import com.jqg.pojo.Moneymin;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class MoneyminDaoImpl
   implements MoneyminDao
 {
   public boolean addMoneymin(Moneymin moneymin)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(moneymin);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteMoneymin(Moneymin moneymin)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(moneymin);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Moneymin findMoneyminById(Integer moneyMinId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Moneymin moneymin = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneymin m where m.moneyMinId=?");
       query.setInteger(0, moneyMinId.intValue());
       moneymin = (Moneymin)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneymin;
   }
 
   public List<Moneymin> findMoneymins()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List moneymins = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneymin");
       moneymins = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneymins;
   }
 
   public boolean updateMoneymin(Moneymin moneymin)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(moneymin);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 }

