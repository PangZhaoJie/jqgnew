 package com.jqg.dao.impl;
 
 import com.jqg.dao.BonusDao;
import com.jqg.pojo.Bonus;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class BonusDaoImpl
   implements BonusDao
 {
   public boolean addBonus(Bonus bonus)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(bonus);
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
 
   public boolean updateBonus(Bonus bonus)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(bonus);
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
 
   public boolean deleteBonus(Bonus bonus)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try
     {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(bonus);
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
 
   public List<Bonus> findBonuss()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List bonuss = new ArrayList();
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Bonus");
       bonuss = query.list();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return bonuss;
   }
 
   public Bonus findBonusBybonusId(int bonusId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Bonus bonus = null;
     try
     {
       session = HibernateSessionFactory.getSession(); 
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Bonus b where b.bonusId=?");
       query.setInteger(0, bonusId);
       bonus = (Bonus)query.uniqueResult();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return bonus;
   }
 
   public List<Bonus> findBonussByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List bonuss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Bonus t where t.uservip.userId=?");
       query.setInteger(0, userId);
       bonuss = query.list();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return bonuss;
   } 
 
   public List<Bonus> findBonussByUserIdPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List bonuss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = "select * from bonus a where a.userId=? ORDER BY a.bonusId DESC ";
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Bonus.class);
       sqlQuery.setInteger(0, userId);
       sqlQuery.setFirstResult(currentPage);
       sqlQuery.setMaxResults(pageSize);
       bonuss = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return bonuss;
   }
 
   public List<Bonus> findBonusBySql(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List bonuss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Bonus.class);
       bonuss = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return bonuss;
   }
 }

