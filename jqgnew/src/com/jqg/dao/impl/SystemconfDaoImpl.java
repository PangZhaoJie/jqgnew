 package com.jqg.dao.impl;
 
 import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jqg.dao.SystemconfDao;
import com.jqg.pojo.Systemconf;
import com.jqg.session.factory.HibernateSessionFactory;
 
 public class SystemconfDaoImpl
   implements SystemconfDao
 {
   public boolean addSystemconf(Systemconf systemconf)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(systemconf);
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
 
   public boolean updateSystemconf(Systemconf systemconf)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(systemconf);
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
 
   public boolean deleteSystemconf(Systemconf systemconf)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(systemconf);
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
 
   public List<Systemconf> findSystemconfs()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List systemconfs = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Systemconf where datatype!=0  ORDER BY sysid");
       systemconfs = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return systemconfs;
   }
 
   public Systemconf findSystemconfBySysId(int sysId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Systemconf systemconf = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Systemconf s where s.sysId=?");
       query.setInteger(0, sysId);
       systemconf = (Systemconf)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return systemconf;
   }
 
   public Systemconf findSystemconfByParname(String parname)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Systemconf systemconf = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Systemconf s where s.parname=?");
       query.setParameter(0, parname);
       systemconf = (Systemconf)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return systemconf;
   }


 }

