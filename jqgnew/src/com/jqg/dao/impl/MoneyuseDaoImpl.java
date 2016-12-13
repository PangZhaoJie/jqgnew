 package com.jqg.dao.impl;
 
 import com.jqg.dao.MoneyuseDao;
import com.jqg.pojo.Moneyuse;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class MoneyuseDaoImpl
   implements MoneyuseDao
 {
   public boolean addMoneyuse(Moneyuse moneyuse)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(moneyuse);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteMoneyuse(Moneyuse moneyuse)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(moneyuse);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Moneyuse findMoneyuseById(Integer moneyUseId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Moneyuse moneyuse = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneyuse m where m.moneyUseId=?");
       query.setInteger(0, moneyUseId.intValue());
       moneyuse = (Moneyuse)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneyuse;
   }
 
   public List<Moneyuse> findMoneyuses()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List moneyuses = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneyuse");
       moneyuses = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneyuses;
   }
 
   public boolean updateMoneyuse(Moneyuse moneyuse)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(moneyuse);
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

