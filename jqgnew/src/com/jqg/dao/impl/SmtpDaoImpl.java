 package com.jqg.dao.impl;
 
 import com.jqg.dao.SmtpDao;
import com.jqg.pojo.Smtp;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class SmtpDaoImpl
   implements SmtpDao
 {
   public boolean addSmtp(Smtp smtp)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(smtp);
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
 
   public boolean updateSmtp(Smtp smtp)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(smtp);
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
 
   public boolean deleteSmtp(Smtp smtp)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(smtp);
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
 
   public List<Smtp> findSmtps()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List smtps = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Smtp");
       smtps = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return smtps;
   }
 
   public Smtp findSmtpBysmtpid(int smtpid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Smtp smtp = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Smtp s where s.smtpid=?");
       query.setInteger(0, smtpid);
       smtp = (Smtp)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return smtp;
   }
 }

