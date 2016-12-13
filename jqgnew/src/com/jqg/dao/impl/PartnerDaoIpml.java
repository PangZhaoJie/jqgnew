 package com.jqg.dao.impl;
 
 import com.jqg.dao.PartnerDao;
import com.jqg.pojo.Partner;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class PartnerDaoIpml
   implements PartnerDao
 {
   public boolean addPartner(Partner partner)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(partner);
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
 
   public boolean updatePartner(Partner partner) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(partner);
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
 
   public boolean deletePartner(Partner partner) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(partner);
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
 
   public List<Partner> findPartners() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Partner");
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
 
   public Partner findPartnerByPartnerId(int partnerId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Partner partner = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Partner p where p.partnerId=?");
       query.setInteger(0, partnerId);
       partner = (Partner)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return partner;
   }
 }

