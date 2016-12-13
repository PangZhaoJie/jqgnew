 package com.jqg.dao.impl;
 
 import com.jqg.dao.RequestquotaDao;
import com.jqg.pojo.Requestquota;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class RequestquotaDaoImpl
   implements RequestquotaDao
 {
   public boolean addRequestquota(Requestquota requestquota)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(requestquota);
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
 
   public boolean updateRequestquota(Requestquota requestquota)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(requestquota);
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
 
   public boolean deleteRequestquota(Requestquota requestquota)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(requestquota);
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
 
   public List<Requestquota> findRequestquotas()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List requestquotas = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Requestquota");
       requestquotas = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return requestquotas;
   }
 
   public Requestquota findRequestquotaByrequestQuotaId(int requestQuotaId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Requestquota requestquota = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Requestquota r where r.requestQuotaId=?");
       query.setInteger(0, requestQuotaId);
       requestquota = (Requestquota)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return requestquota;
   }
 
   public List<Requestquota> findRequestquotasByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List requestquotas = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Requestquota t where t.uservip.userId=?");
       query.setInteger(0, userId);
       requestquotas = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return requestquotas;
   }
 
   public List<Requestquota> findRequestquotasByUserIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List requestquotas = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Requestquota.class);
 
       requestquotas = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return requestquotas;
   }
 }

