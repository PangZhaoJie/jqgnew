 package com.jqg.dao.impl;
 
 import com.jqg.dao.TenderwordsDao;
import com.jqg.pojo.Tenderwords;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class TenderwordsDaoImpl
   implements TenderwordsDao
 {
   public boolean addTenderwords(Tenderwords tenderwords)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(tenderwords);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deleteTenderwords(Tenderwords tenderwords)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(tenderwords);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public Tenderwords findTenderwordsById(Integer tenderWordsId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Tenderwords tenderwords = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tenderwords f where f.tenderWordsId=?");
       query.setInteger(0, tenderWordsId.intValue());
       tenderwords = (Tenderwords)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwords;
   }
 
   public List<Tenderwords> findTenderwordss()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwordss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tenderwords");
       tenderwordss = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwordss;
   }
 
   public boolean updateTenderwords(Tenderwords tenderwords)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(tenderwords);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public List<Tenderwords> findTenderwordsByState(int lssuingId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwordss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tenderwords t where  t.lssuing.lssuingId=?");
       query.setInteger(0, lssuingId);
       tenderwordss = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwordss;
   }
 
   public List<Tenderwords> findTenderwordsByStatePage(int lssuingId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwordss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = "SELECT * from Tenderwords t where t.lssuingId=?  ORDER BY t.tenderWordsId DESC";
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Tenderwords.class);
       sqlQuery.setInteger(0, lssuingId);
       sqlQuery.setFirstResult(currentPage);
       sqlQuery.setMaxResults(pageSize);
       tenderwordss = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwordss;
   }
 
   public List<Tenderwords> findTenderwordsByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwords = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tenderwords t where t.uservip.userId=?");
       query.setInteger(0, userId);
       tenderwords = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwords;
   }
 
   public List<Tenderwords> findTenderwordsByUserIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwords = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Tenderwords.class);
 
       tenderwords = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwords;
   }
 
   public List<Tenderwords> findTenderwordsByLssuingId(int lssuingId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwords = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tenderwords t where t.state=1 and t.lssuing.lssuingId=?");
       query.setInteger(0, lssuingId);
       tenderwords = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwords;
   }
 
   public List<Tenderwords> findTenderwordsByPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenderwordss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = "SELECT * from Tenderwords t where t.state=1 and t.userId=?  ORDER BY t.tenderWordsId DESC";
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Tenderwords.class);
       sqlQuery.setInteger(0, userId);
       sqlQuery.setFirstResult(currentPage);
       sqlQuery.setMaxResults(pageSize);
       tenderwordss = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenderwordss;
   }
 }

