 package com.jqg.dao.impl;
 
 import com.jqg.dao.CompanyinformationDao;
import com.jqg.pojo.Companyinformation;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class CompanyinformationDaoImpl
   implements CompanyinformationDao
 {
   public boolean addCompanyinformation(Companyinformation companyinformation)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(companyinformation);
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
 
   public boolean updateCompanyinformation(Companyinformation companyinformation)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(companyinformation);
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
 
   public boolean deleteCompanyinformation(Companyinformation companyinformation)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(companyinformation);
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
 
   public List<Companyinformation> findCompanyinformations()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List companyinformations = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Companyinformation");
       companyinformations = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return companyinformations;
   }
 
   public Companyinformation findCompanyinformationBycompanyId(int companyId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Companyinformation companyinformation = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Companyinformation c where c.companyId=?");
       query.setInteger(0, companyId);
       companyinformation = (Companyinformation)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return companyinformation;
   }
 }

