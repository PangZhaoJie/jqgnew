 package com.jqg.dao.impl;
 
 import com.jqg.dao.InvestmentlevelDao;
import com.jqg.pojo.Investmentlevel;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class InvestmentlevelDaoImpl
   implements InvestmentlevelDao
 {
   public boolean addInvestmentlevel(Investmentlevel investmentlevel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(investmentlevel);
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
 
   public boolean updateInvestmentlevel(Investmentlevel investmentlevel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(investmentlevel);
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
 
   public boolean deleteInvestmentlevel(Investmentlevel investmentlevel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(investmentlevel);
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
 
   public List<Investmentlevel> findInvestmentlevels()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List investmentlevels = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Investmentlevel");
       investmentlevels = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investmentlevels;
   }
 
   public Investmentlevel findInvestmentlevelByinvestmentLevelId(int investmentLevelId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Investmentlevel investmentlevel = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Investmentlevel i where i.investmentLevelId=?");
       query.setInteger(0, investmentLevelId);
       investmentlevel = (Investmentlevel)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investmentlevel;
   }
 }

