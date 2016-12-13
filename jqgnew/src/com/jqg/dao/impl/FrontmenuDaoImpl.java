 package com.jqg.dao.impl;
 
 import com.jqg.dao.FrontmenuDao;
import com.jqg.pojo.Frontmenu;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class FrontmenuDaoImpl
   implements FrontmenuDao
 {
   public boolean addFrontmenu(Frontmenu frontmenu)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(frontmenu);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteFrontmenu(Frontmenu frontmenu)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(frontmenu);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Frontmenu findFrontmenuById(Integer frontmenuId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Frontmenu frontmenu = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Frontmenu f where f.frontMenuId=?");
       query.setInteger(0, frontmenuId.intValue());
       frontmenu = (Frontmenu)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return frontmenu;
   }
 
   public List<Frontmenu> findFrontmenus()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List frontmenus = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Frontmenu");
       frontmenus = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return frontmenus;
   }
 
   public boolean updateFrontmenu(Frontmenu frontmenu)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(frontmenu);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public List<Frontmenu> findFrontmenuByIdANdId(int frontMenuId1, int frontMenuId2)
     throws Exception
   {
     Session session = null;
//     //Transaction transaction = null;
     List frontmenus = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
//       //transaction = session.beginTransaction();
       String sql = "from Frontmenu where isDisplay=1 and fatherId=0 and frontMenuId >"
				+ frontMenuId1 + " and frontMenuId <" + frontMenuId2;
//       String procName = "{call findFrondMenuByIdAndId(?,?)}";
       Query query = session.createQuery(sql);
//       query.setInteger(0, frontMenuId1);
//       query.setInteger(1, frontMenuId2);
       frontmenus = query.list();
//       //transaction.commit();
     } catch (Exception e) {
//       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return frontmenus;
   }
 
   public List<Frontmenu> findFrontmenusBy(int fatherId)
     throws Exception
   {
     Session session = null;
//     //Transaction transaction = null;
     List frontmenus = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
//       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Frontmenu f where f.fatherId=? and f.isDisplay=1");
       query.setInteger(0, fatherId);
       frontmenus = query.list();
//       //transaction.commit();
     } catch (Exception e) {
//       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return frontmenus;
   }
 
   public List<Frontmenu> findFrontmenusByDisplay(int fatherId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List frontmenus = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Frontmenu f where f.fatherId=? and f.isDisplay=1");
       query.setInteger(0, fatherId);
       frontmenus = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return frontmenus;
   }
 
   public Frontmenu findFrontmenuByCode(String frontMenuCode)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Frontmenu frontmenu = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Frontmenu f where f.frontMenuCode=?");
       query.setString(0, frontMenuCode);
       frontmenu = (Frontmenu)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return frontmenu;
   }
 
   public List<Frontmenu> findFrontmenutonew(int frontMenuId1, int frontMenuId2)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List frontmenus = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String sql = "from Frontmenu where fatherId !=0 and frontMenuId >"
				+ frontMenuId1 + " and frontMenuId <" + frontMenuId2
				+ " and isDisplay=1";
       Query sqlQuery = session.createQuery(sql);
       sqlQuery.setInteger(0, frontMenuId1);
       sqlQuery.setInteger(1, frontMenuId2);
       frontmenus = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	
     }
     return frontmenus;
   }

public List<Frontmenu> findFrontmenuByisColumn() throws Exception {
	// TODO Auto-generated method stub
	Session session = null;
    //Transaction transaction = null;
    List frontmenus = new ArrayList();
    try {
      session = HibernateSessionFactory.getSession();
      
      //transaction = session.beginTransaction();
      Query query = session.createQuery("from Frontmenu f where isColumn=1");
      frontmenus = query.list();
      //transaction.commit();
    } catch (Exception e) {
      //transaction.rollback();
      throw e;
    }finally{
   	 
    }
    return frontmenus;
}
 }

