 package com.jqg.dao.impl;
 
 import com.jqg.dao.CustomerqqsDao;
import com.jqg.pojo.Customerqqs;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class CustomerqqsDaoImpl
   implements CustomerqqsDao
 {
   public boolean addCustomerqqs(Customerqqs customerqqs)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(customerqqs);
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
 
   public boolean updateCustomerqqs(Customerqqs customerqqs)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(customerqqs);
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
 
   public boolean deleteCustomerqqs(Customerqqs customerqqs)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(customerqqs);
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
 
   public List<Customerqqs> findCustomerqqss()
     throws Exception
   {
     Session session = null;
    
     List customerqqs = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       Query query = session.createQuery("from Customerqqs");
       customerqqs = query.list();
     }
     catch (Exception e) {
       throw e;
     }finally{
    	 
     }
     return customerqqs;
   }
 
   public Customerqqs findCustomerqqsBycustomerQqsid(int customerQqsid)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Customerqqs customerqqs = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Customerqqs c where c.customerQqsid=?");
       query.setInteger(0, customerQqsid);
       customerqqs = (Customerqqs)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return customerqqs;
   }
   
   public List<Customerqqs> findCustomerqqbydisplay(int display) throws Exception{
	   Session session = null;
//	     //Transaction transaction = null;
	     List customerqqs = new ArrayList();
	     try
	     {
	       session = HibernateSessionFactory.getSession(); 
//	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Customerqqs where display="+display);
	       customerqqs = query.list();
//	       //transaction.commit();
	     }
	     catch (Exception e) {
//	       //transaction.rollback();
	       throw e;
	     }finally{
	    	 
	     }
	     return customerqqs;
   }
 }

