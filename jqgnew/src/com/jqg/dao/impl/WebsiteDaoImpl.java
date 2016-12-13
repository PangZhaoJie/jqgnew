 package com.jqg.dao.impl;
 
 import com.jqg.dao.WebsiteDao;
import com.jqg.pojo.Website;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class WebsiteDaoImpl
   implements WebsiteDao
 {
   public boolean addWebsite(Website website)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(website);
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
 
   public boolean updateWebsite(Website website)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(website);
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
 
   public boolean deleteWebsite(Website website)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(website);
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
 
   public List<Website> findWebsites()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List websites = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Website");
       websites = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return websites;
   }
   /**
    * ¸ù¾ÝÍøÕ¾ID²éÑ¯ÍøÕ¾
    * @param paramInt
    * @return
    * @throws Exception
    */
   public Website findWebsiteBywebsiteId(int websiteId)
     throws Exception
   {
     Session session = null;
//     //Transaction transaction = null;
     Website website = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
//       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Website w where w.websiteId=?");
       query.setInteger(0, websiteId);
       website = (Website)query.uniqueResult();
//       //transaction.commit();
     }
     catch (Exception e) {
       System.out.println(e.getMessage());	 
//       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return website;
   }
 }

