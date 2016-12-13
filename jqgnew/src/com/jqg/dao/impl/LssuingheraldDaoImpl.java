 package com.jqg.dao.impl;
 
 import com.jqg.dao.LssuingheraldDao;
import com.jqg.pojo.Lssuingherald;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class LssuingheraldDaoImpl
   implements LssuingheraldDao
 {
   public boolean addLssuingherald(Lssuingherald lssuingherald)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(lssuingherald);
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
 
   public boolean updateLssuingherald(Lssuingherald lssuingherald)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(lssuingherald);
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
 
   public boolean deleteLssuingherald(Lssuingherald lssuingherald)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(lssuingherald);
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
 
   public List<Lssuingherald> findLssuingheralds()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuingheralds = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuingherald");
       lssuingheralds = query.list();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuingheralds;
   }
 
   public Lssuingherald findLssuingheraldByLssuingheraldId(int lssuingHeraldId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Lssuingherald lssuingherald = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuingherald l where l.lssuingHeraldId=?");
       query.setInteger(0, lssuingHeraldId);
       lssuingherald = (Lssuingherald)query.uniqueResult();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuingherald;
   }
 
   public Lssuingherald findLssuingheraldByindex()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Lssuingherald lssuingherald = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       String procName = "select * from lssuingherald l ORDER BY l.heraldTime DESC";
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Lssuingherald.class);
       sqlQuery.setFirstResult(0);
       sqlQuery.setMaxResults(1);
       lssuingherald = (Lssuingherald)sqlQuery.uniqueResult();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuingherald;
   }
 }

