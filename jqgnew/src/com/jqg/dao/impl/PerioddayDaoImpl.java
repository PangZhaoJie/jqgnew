 package com.jqg.dao.impl;
 
 import com.jqg.dao.PerioddayDao;
import com.jqg.pojo.Periodday;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PerioddayDaoImpl
   implements PerioddayDao
 {
   public boolean addPeriodday(Periodday periodday)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(periodday);
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
 
   public boolean updatePeriodday(Periodday periodday)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(periodday);
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
 
   public boolean deletePeriodday(Periodday periodday)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(periodday);
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
 
   public List<Periodday> findPerioddays()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List perioddays = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Periodday");
       perioddays = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return perioddays;
   }
 
   public Periodday findPerioddayByPerioddayId(int perioddayId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Periodday periodday = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Periodday p where p.periodDayId=?");
       query.setInteger(0, perioddayId);
       periodday = (Periodday)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return periodday;
   }
 }

