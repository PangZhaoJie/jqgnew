 package com.jqg.dao.impl;
 
 import com.jqg.dao.AsktypeDao;
import com.jqg.pojo.Asktype;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class AsktypeDaoImpl
   implements AsktypeDao
 {
   public boolean addAsktype(Asktype asktype)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(asktype);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteAsktype(Asktype asktype)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(asktype);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Asktype findAsktypeById(Integer AsktypeId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Asktype asktype = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Asktype f where f.askTypeId=?");
       query.setInteger(0, AsktypeId.intValue());
       asktype = (Asktype)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return asktype;
   }
 
   public List<Asktype> findAsktypes()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List asktypes = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Asktype");
       asktypes = query.list();
       //transaction.commit();
     } catch (Exception e) {
      //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return asktypes;
   }
 
   public boolean updateAsktype(Asktype asktype)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(asktype);
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

