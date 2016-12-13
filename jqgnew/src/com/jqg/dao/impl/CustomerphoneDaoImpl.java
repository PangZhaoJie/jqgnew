 package com.jqg.dao.impl;
 
 import com.jqg.dao.CustomerphoneDao;
import com.jqg.pojo.Customerphone;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class CustomerphoneDaoImpl
   implements CustomerphoneDao
 {
   public boolean addCustomerphone(Customerphone customerphone)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(customerphone);
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
 
   public boolean updateCustomerphone(Customerphone customerphone)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(customerphone);
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
 
   public boolean deleteCustomerphone(Customerphone customerphone)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(customerphone);
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
 
   public List<Customerphone> findCustomerphones()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List customerphones = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Customerphone");
       customerphones = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return customerphones;
   }
 
   public Customerphone findCustomerphoneBycustomerphoneId(int customerphoneId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Customerphone customerphone = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Customerphone c where c.customerphoneId=?");
       query.setInteger(0, customerphoneId);
       customerphone = (Customerphone)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return customerphone;
   }

public Customerphone findLastCustomerphone() throws Exception {
	Session session = null;
    //Transaction transaction = null;
    Customerphone cp = new Customerphone();
    try
    {
      session = HibernateSessionFactory.getSession(); 
      //transaction = session.beginTransaction();
      Query query = session.createQuery("from Customerphone order by createDate DESC");
      List<Customerphone> customerphones = query.list();
     
      if(customerphones.size()>0){
    	 cp= customerphones.get(0);
      }
      //transaction.commit();
    }
    catch (Exception e) {
      throw e;
    }finally{
   	 
    }
    return cp;
}


 }

