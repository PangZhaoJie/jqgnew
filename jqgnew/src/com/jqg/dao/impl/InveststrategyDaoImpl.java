 package com.jqg.dao.impl;
 
 import com.jqg.dao.InveststrategyDao;
import com.jqg.pojo.Investstrategy;
import com.jqg.session.factory.HibernateSessionFactory;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class InveststrategyDaoImpl
   implements InveststrategyDao
 {
   public boolean addInveststrategy(Investstrategy investstrategy)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(investstrategy);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteInveststrategy(Investstrategy investstrategy)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(investstrategy);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Investstrategy findInveststrategyById(Integer askId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Investstrategy investstrategy = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Investstrategy i where i.askId=?");
       query.setInteger(0, askId.intValue());
       investstrategy = (Investstrategy)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investstrategy;
   }
 
   public List<Investstrategy> findInveststrategys()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List investstrategys = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Investstrategy");
       investstrategys = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investstrategys;
   }
 
   public boolean updateInveststrategy(Investstrategy investstrategy)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(investstrategy);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public List<Investstrategy> findInveststrategysByIdex()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List investstrategys = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Investstrategy i ORDER BY i.askId DESC ");
       query.setFirstResult(0);
       query.setMaxResults(6);
       investstrategys = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investstrategys;
   }
 
   public List<Investstrategy> findInveststrategyByType()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List investstrategys = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createSQLQuery("select a.askId,a.askTitle,u.userName,a.askTypeId,a.commentCount,a.clickCount, a.askDate ,count(*) from (select * from investstrategy order by askDate desc) a ,uservip u where a.userId=u.userId group by a.askTypeId ");
 
       List list = query.list();
 
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       for (int i = 0; i < list.size(); i++) {
         Investstrategy ins = new Investstrategy();
         Object[] a = (Object[])list.get(i);
         ins.setAskId(Integer.valueOf(Integer.parseInt(String.valueOf(a[0]))));
         ins.setAskTitle((String)a[1]);
         ins.setAskUserName((String)a[2]);
         ins.setTypeFlag(Integer.parseInt(String.valueOf(a[3])));
         if (a[4] != null) {
           ins.setCommentCount(Integer.valueOf(Integer.parseInt(String.valueOf(a[4]))));
         }
         if (a[5] != null) {
           ins.setClickCount(Integer.valueOf(Integer.parseInt(String.valueOf(a[5]))));
         }
         ins.setAskDate((Timestamp)a[6]);
         ins.setNoteCount(Integer.parseInt(String.valueOf(a[7])));
         investstrategys.add(ins);
       }
 
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investstrategys;
   }
 
   public List<Investstrategy> findInveststrategyByDate(int flag, String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List investstrategys = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery(sql);
       if (flag == -1) {
         query.setFirstResult(0);
         query.setMaxResults(7);
       }
       investstrategys = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investstrategys;
   }
 
   public List<Investstrategy> findInveststrategyByPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List investstrategys = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Investstrategy.class);
 
       investstrategys = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return investstrategys;
   }
 }

