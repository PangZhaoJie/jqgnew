 package com.jqg.dao.impl;
 
 import com.jqg.dao.TranslateDao;
import com.jqg.pojo.Translate;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class TranslateDaoImpl
   implements TranslateDao
 {
   public boolean addTranslate(Translate translate)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(translate);
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
 
   public boolean updateTranslate(Translate translate)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(translate);
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
 
   public boolean deleteTranslate(Translate translate)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(translate);
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
 
   public List<Translate> findTranslates()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List translates = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Translate");
       translates = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return translates;
   }
 
   public Translate findTranslateById(Integer translateId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Translate translate = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Translate t where t.translateId=?");
       query.setInteger(0, translateId.intValue());
       translate = (Translate)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return translate;
   }
 
   public List<Translate> findTranslatesByuser(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List translates = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Translate t where t.uservip.userId=?");
       query.setInteger(0, userId);
       translates = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return translates;
   }
 
   public List<Translate> findTranslatesByuserPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List translates = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Translate t where t.uservip.userId=?");
       query.setInteger(0, userId);
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
       translates = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return translates;
   }
 
   public List<Translate> findTranslateBySql(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List translates = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       SQLQuery query = session.createSQLQuery(sql).addEntity(Translate.class);
       translates = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return translates;
   }
 }

