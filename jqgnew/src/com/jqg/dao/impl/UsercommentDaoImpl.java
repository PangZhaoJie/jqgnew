 package com.jqg.dao.impl;
 
 import com.jqg.dao.UsercommentDao;
import com.jqg.pojo.Usercomment;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class UsercommentDaoImpl
   implements UsercommentDao
 {
   public boolean addUsercomment(Usercomment usercomment)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(usercomment);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deleteUsercomment(Usercomment usercomment)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(usercomment);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public Usercomment findUsercommentById(Integer commentId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Usercomment usercomment = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Usercomment u where u.commentId=?");
       query.setInteger(0, commentId.intValue());
       usercomment = (Usercomment)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return usercomment;
   }
 
   public List<Usercomment> findUsercomments()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List usercomments = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Usercomment");
       usercomments = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return usercomments;
   }
 
   public boolean updateUsercomment(Usercomment usercomment)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(usercomment);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public List<Usercomment> findUsercommentByUserId(Integer askId, Integer firstResult, Integer maxResult)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List usercomments = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Usercomment u where u.investstrategy.askId=? order by u.commentDate");
       query.setInteger(0, askId.intValue());
       if ((firstResult.intValue() != 0) || (maxResult.intValue() != 0))
       {
         query.setFirstResult(firstResult.intValue());
         query.setMaxResults(maxResult.intValue());
       }
       usercomments = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return usercomments;
   }
 
   public List<Usercomment> findUsercommentByPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List usercomments = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Usercomment.class);
       usercomments = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return usercomments;
   }
 }

