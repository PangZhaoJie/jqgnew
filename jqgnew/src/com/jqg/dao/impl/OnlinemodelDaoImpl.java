 package com.jqg.dao.impl;
 
 import com.jqg.dao.OnlinemodelDao;
import com.jqg.pojo.Onlinemodel;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class OnlinemodelDaoImpl
   implements OnlinemodelDao
 {
   public boolean addOnlinemodel(Onlinemodel onlinemodel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(onlinemodel);
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
 
   public boolean updateOnlinemodel(Onlinemodel onlinemodel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(onlinemodel);
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
 
   public boolean deleteOnlinemodel(Onlinemodel onlinemodel)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(onlinemodel);
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
 
   public List<Onlinemodel> findOnlinemodels()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List onlinemodels = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Onlinemodel");
       onlinemodels = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return onlinemodels;
   }
 
   public List<Onlinemodel> findOnlinemodelByAble()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List onlinemodels = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Onlinemodel o where o.enable=1");
       onlinemodels = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return onlinemodels;
   }
 
   public Onlinemodel findOnlinemodelByonlineId(int onlineId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Onlinemodel onlinemodel = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Onlinemodel o where o.onlineId=?");
       query.setInteger(0, onlineId);
       onlinemodel = (Onlinemodel)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return onlinemodel;
   }
 }

