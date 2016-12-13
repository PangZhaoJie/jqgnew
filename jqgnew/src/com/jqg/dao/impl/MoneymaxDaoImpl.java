 package com.jqg.dao.impl;
 
 import com.jqg.dao.MoneymaxDao;
import com.jqg.pojo.Moneymax;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class MoneymaxDaoImpl
   implements MoneymaxDao
 {
   public boolean addMoneymax(Moneymax moneymax)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(moneymax);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteMoneymax(Moneymax moneymax)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(moneymax);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Moneymax findMoneymaxById(Integer moneyMaxId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Moneymax moneymax = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneymax m where m.moneyMaxId=?");
       query.setInteger(0, moneyMaxId.intValue());
       moneymax = (Moneymax)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneymax;
   }
 
   public List<Moneymax> findMoneymaxs()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List moneymaxs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Moneymax");
       moneymaxs = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return moneymaxs;
   }
 
   public boolean updateMoneymax(Moneymax moneymax)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(moneymax);
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

