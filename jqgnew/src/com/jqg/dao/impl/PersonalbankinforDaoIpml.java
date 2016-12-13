 package com.jqg.dao.impl;
 
 import com.jqg.dao.PersonalbankinforDao;
import com.jqg.pojo.Personalbankinfor;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PersonalbankinforDaoIpml
   implements PersonalbankinforDao
 {
   public boolean addPersonalbankinfor(Personalbankinfor personalbankinfor)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(personalbankinfor);
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
 
   public boolean updatePersonalbankinfor(Personalbankinfor personalbankinfor) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(personalbankinfor);
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
 
   public boolean deletePersonalbankinfor(Personalbankinfor personalbankinfor) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(personalbankinfor);
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
 
   public List<Personalbankinfor> findPersonalbankinfors() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Personalbankinfor");
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
 
   public Personalbankinfor findPersonalbankinforById(int personalBankInforId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Personalbankinfor personalbankinfor = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Personalbankinfor p where p.personalBankInforId=?");
       query.setInteger(0, personalBankInforId);
       personalbankinfor = (Personalbankinfor)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return personalbankinfor;
   }
 
   public List<Personalbankinfor> findPersonalbankinforsByuserPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Personalbankinfor p where p.uservip.userId=?");
       query.setInteger(0, userId);
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
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
 
   public List<Personalbankinfor> findPersonalbankinforsByuser(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Personalbankinfor p where p.uservip.userId=?");
       query.setInteger(0, userId);
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
 }

