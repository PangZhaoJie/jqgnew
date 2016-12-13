 package com.jqg.dao.impl;
 
 import com.jqg.dao.PeriodtimeDao;
import com.jqg.pojo.Periodtime;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PeriodtimeDaoImpl
   implements PeriodtimeDao
 {
   public boolean addPeriodtime(Periodtime periodtime)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(periodtime);
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
 
   public boolean updatePeriodtime(Periodtime periodtime)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(periodtime);
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
 
   public boolean deletePeriodtime(Periodtime periodtime)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(periodtime);
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
 
   public List<Periodtime> findPeriodtimes()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List periodtimes = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Periodtime");
       periodtimes = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return periodtimes;
   }
 
   public Periodtime findPeriodtimeByPeriodtimeId(int periodtimeId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Periodtime periodtime = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Periodtime p where p.periodTimeId=?");
       query.setInteger(0, periodtimeId);
       periodtime = (Periodtime)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return periodtime;
   }
 }

