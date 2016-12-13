 package com.jqg.dao.impl;
 
 import com.jqg.dao.FriendlinkDao;
import com.jqg.pojo.Friendlink;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class FriendlinkDaoImpl
   implements FriendlinkDao
 {
   public boolean addFriendlink(Friendlink friendlink)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(friendlink);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteFriendlink(Friendlink friendlink)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(friendlink);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Friendlink findFriendlinkById(Integer friendLinkId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Friendlink friendlink = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Friendlink f where f.friendLinkId=?");
       query.setInteger(0, friendLinkId.intValue());
       friendlink = (Friendlink)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return friendlink;
   }
 
   public List<Friendlink> findFriendlinks()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List friendlinks = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Friendlink");
       friendlinks = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return friendlinks;
   }
 
   public boolean updateFriendlink(Friendlink friendlink)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(friendlink);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
   /**
    * 根据索引查找连接并返回列表
    * @return
    * @throws Exception
    */
   public List<Friendlink> findFriendlinksByIndex(String flag)
     throws Exception
   {
	     Session session = null;
	     String where = "";
	     if(flag.equals("pic")){
	    	 where = " and f.photo<>'1'";
	     }else if(flag.equals("con")){
	    	 where = " and f.photo='1'";
	     }
	     //Transaction transaction = null;
	     List friendlinks = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Friendlink f where f.isDisplay = 1 "+where+" ORDER BY f.place DESC");
	       friendlinks = query.list();
	       //transaction.commit();
	     } catch (Exception e) {
	       //transaction.rollback();
	       throw e;
	     }finally{
	    	 
	     }
	     return friendlinks;
   }
 
   public List<Friendlink> findFriendlinkspage(int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List friendlinks = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Friendlink");
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
       friendlinks = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return friendlinks;
   }
 }

