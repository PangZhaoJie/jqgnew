 package com.jqg.dao.impl;
 
 import com.jqg.dao.UservipnoteDao;
import com.jqg.pojo.Uservipnote;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class UservipnoteDaoImpl
   implements UservipnoteDao
 {
   public boolean addUservipnote(Uservipnote Uservipnote)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(Uservipnote);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deleteUservipnote(Uservipnote uservipnote)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(uservipnote);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public Uservipnote findUservipByUserVipNoteId(int userVipNoteId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Uservipnote uservipnote = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Uservipnote u where u.userVipNoteId=?");
       query.setInteger(0, userVipNoteId);
       uservipnote = (Uservipnote)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return uservipnote;
   }
 
   public List<Uservipnote> findUservipnotes()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List uservipnotes = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Uservipnote");
       uservipnotes = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return uservipnotes;
   }
 
   public boolean updateUservipnote(Uservipnote uservipnote)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(uservipnote);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 }

