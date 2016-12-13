 package com.jqg.dao.impl;
 
 import com.jqg.dao.LssingphotoDao;
import com.jqg.pojo.Lssingphoto;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class LssingphotoDaoImpl
   implements LssingphotoDao
 {
   public boolean addLssingphoto(Lssingphoto lssingphoto)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(lssingphoto);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteLssingphoto(Lssingphoto lssingphoto)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(lssingphoto);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Lssingphoto findLssingphotoById(Integer photoId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Lssingphoto lssingphoto = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssingphoto l where l.photoId=?");
       query.setInteger(0, photoId.intValue());
       lssingphoto = (Lssingphoto)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssingphoto;
   }
 
   public List<Lssingphoto> findLssingphotos()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssingphotos = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssingphoto");
       lssingphotos = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssingphotos;
   }
 
   public boolean updateLssingphoto(Lssingphoto lssingphoto)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(lssingphoto);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public List<Lssingphoto> findLssingphotosBylssuing(int lssuingId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssingphotos = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssingphoto l where l.lssuing.lssuingId=?");
       query.setInteger(0, lssuingId);
       lssingphotos = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssingphotos;
   }
 
   public List<Lssingphoto> findLssingphotosBylssuingPage(int lssuingId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssingphotos = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssingphoto l where l.lssuing.lssuingId=?");
       query.setInteger(0, lssuingId);
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
       lssingphotos = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssingphotos;
   }
 }

