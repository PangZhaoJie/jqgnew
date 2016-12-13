 package com.jqg.dao.impl;
 
 import com.jqg.dao.BasicinforDao;
import com.jqg.pojo.Basicinfor;
import com.jqg.pojo.Uservip;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class BasicinforDaoIpml
   implements BasicinforDao
 {
   public Integer addBasicinfor(Basicinfor basicinfor)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Integer flag = null;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(basicinfor);
       //transaction.commit();
       flag = basicinfor.getBasicInforId();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean updateBasicinfor(Basicinfor basicinfor) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.merge(basicinfor);
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
 
   public boolean deleteBasicinfor(Basicinfor basicinfor) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       session.delete(basicinfor);
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
 
   public List<Basicinfor> findBasicinfors() throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List amountinfos = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Basicinfor");
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
 
   public Basicinfor findBasicinforByBasicInforId(int basicInforId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Basicinfor basicinfor = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Basicinfor p where p.basicInforId=?");
       query.setInteger(0, basicInforId);
       basicinfor = (Basicinfor)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return basicinfor;
   }
 
   public Basicinfor findBasicinforByUserId(int userId) throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Basicinfor basicinfor = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Basicinfor p where p.uservip.userId=?");
       query.setInteger(0, userId);
       basicinfor = (Basicinfor)query.uniqueResult();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return basicinfor;
   }

public Basicinfor findBasicinforByPhone(String phone) throws Exception {
	Session session = null;
    //Transaction transaction = null;
    Basicinfor basicinfor = new  Basicinfor();
    try
    {
      session = HibernateSessionFactory.getSession(); 
      //transaction = session.beginTransaction();
      Query query = session.createQuery("from Basicinfor p where p.phoneNum=?");
      query.setString(0, phone);
      basicinfor = (Basicinfor)query.uniqueResult();
     
    }
    catch (Exception e) {
//      //transaction.rollback();
//      throw e;
      System.err.println(e);
    }finally{
   	 
    }
    return basicinfor;
}

public Basicinfor findBasicinforByIdNum(String idNum) throws Exception {
	Session session = null;
    //Transaction transaction = null;
    Basicinfor basicinfor = new  Basicinfor();
    try
    {
      session = HibernateSessionFactory.getSession(); 
      //transaction = session.beginTransaction();
      Query query = session.createQuery("from Basicinfor p where p.idNum=?");
      query.setString(0, idNum);
      basicinfor = (Basicinfor)query.uniqueResult();
     
    }
    catch (Exception e) {
      //transaction.rollback();
      throw e;
    }finally{
   	 
    }
    return basicinfor;
}

 }

