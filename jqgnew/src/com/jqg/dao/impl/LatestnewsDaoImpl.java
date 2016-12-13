 package com.jqg.dao.impl;
 
 import com.jqg.dao.LatestnewsDao;
import com.jqg.pojo.Latestnews;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class LatestnewsDaoImpl
   implements LatestnewsDao
 {
   public boolean addLatestnews(Latestnews latestnews)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(latestnews);
       session.setFlushMode(FlushMode.COMMIT);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteLatestnews(Latestnews latestnews)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(latestnews);
       session.setFlushMode(FlushMode.COMMIT);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Latestnews findLatestnewsByLatestnewsId(Integer newsId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Latestnews latestnews = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l where l.newsId=?");
       query.setInteger(0, newsId.intValue());
       latestnews = (Latestnews)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnews;
   }
 
   public List<Latestnews> findLatestnewss(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List latestnewss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
//       Query query = session.createQuery(sql);
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Latestnews.class);
       latestnewss = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnewss;
   }
 
   public boolean updateLatestnews(Latestnews Latestnews)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(Latestnews);
       session.setFlushMode(FlushMode.COMMIT);
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
    * 根据图片查询最新新闻
    * @return
    * @throws Exception
    */
   public List<Latestnews> findLatestnewssBypicture()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List latestnewss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
//       String procName = "{call findLatestnewssBypicture()}";
       String sql = "from Latestnews l where l.photo !='0' and l.frontmenu.frontMenuId=46 ORDER BY l.newsId DESC";
       Query query = session.createQuery(sql);
 
       latestnewss = query.list();
       //transaction.commit();
     }
     catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
//    	 session.close();
     }
     return latestnewss;
   }
 /**
  * 根据索引查询最新消息
  */
   public List<Latestnews> findLatestnewssByfrontMenuIdindex(int frontMenuId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List latestnewss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l where l.frontmenu.frontMenuId = ? ORDER BY l.newsId DESC ");
       query.setInteger(0, frontMenuId);
       query.setFirstResult(0);
       query.setMaxResults(5);
       latestnewss = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnewss;
   }
 
   public List<Latestnews> findLatestnewssByfrontMenuId(int frontMenuId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List latestnewss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l where l.frontmenu.frontMenuId = ? ORDER BY l.newsId DESC ");
       query.setInteger(0, frontMenuId);
       latestnewss = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnewss;
   }
 
   public List<Latestnews> findLatestnewssByfrontMenuIdPage(int frontMenuId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List latestnewss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l where l.frontmenu.frontMenuId = ? ORDER BY l.newsId DESC ");
       query.setInteger(0, frontMenuId);
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
 
       latestnewss = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnewss;
   }
 
   public Latestnews findLatestnewsByonPage(Integer newsId, int frontmenuId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Latestnews latestnews = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l where l.newsId > ? and l.frontmenu.frontMenuId = ?  ");
       query.setInteger(0, newsId.intValue());
       query.setInteger(1, frontmenuId);
       query.setFirstResult(0);
       query.setMaxResults(1);
       latestnews = (Latestnews)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnews;
   }
 
   public Latestnews findLatestnewsBydownPage(Integer newsId, int frontmenuId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Latestnews latestnews = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l where l.newsId < ? and l.frontmenu.frontMenuId = ? ORDER BY l.newsId DESC ");
       query.setInteger(0, newsId.intValue());
       query.setInteger(1, frontmenuId);
       query.setFirstResult(0);
       query.setMaxResults(1);
       latestnews = (Latestnews)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnews;
   }
 
   public List<Latestnews> findLatestnewsspage(int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List latestnewss = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Latestnews l ORDER BY l.newsId DESC");
       query.setFirstResult(currentPage);
       query.setMaxResults(pageSize);
       latestnewss = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return latestnewss;
   }
 }

