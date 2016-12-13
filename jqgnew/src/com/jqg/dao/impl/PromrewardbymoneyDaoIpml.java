 package com.jqg.dao.impl;
 
 import com.jqg.dao.PromrewardbymoneyDao;
import com.jqg.pojo.Promrewardbymoney;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PromrewardbymoneyDaoIpml
   implements PromrewardbymoneyDao
 {
   public boolean addPromrewardbymoney(Promrewardbymoney promrewardbymoney)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(promrewardbymoney);
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
 
   public boolean updatePromrewardbymoney(Promrewardbymoney promrewardbymoney) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(promrewardbymoney);
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
 
   public boolean deletePromrewardbymoney(Promrewardbymoney promrewardbymoney) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(promrewardbymoney);
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
 
   public List<Promrewardbymoney> findPromrewardbymoneys() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Promrewardbymoney");
       amountinfos = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return amountinfos;
   }
 
   public Promrewardbymoney findPromrewardbymoneyById(int promRewardByMoneyId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Promrewardbymoney promrewardbymoney = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Promrewardbymoney p where p.promRewardByMoneyId=?");
       query.setInteger(0, promRewardByMoneyId);
       promrewardbymoney = (Promrewardbymoney)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return promrewardbymoney;
   }
 }

