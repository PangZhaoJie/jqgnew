 package com.jqg.dao.impl;
 
 import com.jqg.dao.LssuingDao;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.LssuingComp;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class LssuingDaoImpl
   implements LssuingDao
 {
   public boolean addLssuing(Lssuing lssuing)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(lssuing);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public boolean deleteLssuing(Lssuing lssuing)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(lssuing);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public Lssuing findLssuingById(Integer lssuingId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Lssuing lssuing = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuing l where l.lssuingId=?  ");
       query.setInteger(0, lssuingId.intValue());
       lssuing = (Lssuing)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuing;
   }
 
   public List<Lssuing> findLssuings()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuing l where l.state<5  and l.lssuingType != 6 ");
       lssuings = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuings;
   }
 
   public boolean updateLssuing(Lssuing lssuing)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(lssuing);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
   }
 
   public List<Lssuing> findLssuingsIndex()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
//       String procName = "{call findLssuingsIndex()}";
       String sql="from Lssuing where  state<5 and lssuingType != 6 order by borrowTime desc";
       Query sqlQuery = session.createQuery(sql);
       sqlQuery.setFirstResult(0);
       sqlQuery.setMaxResults(5);
       lssuings = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
//    	 session.close();
     }
     return lssuings;
   }
 
   public List<Lssuing> findLssuingsBySearch(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Lssuing.class);
 
       lssuings = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuings;
   }
   public List<Lssuing> findLssuingsIndex(String sql,int cunt)
		     throws Exception
		   {
		     Session session = null;
		     //Transaction transaction = null;
		     List lssuings = new ArrayList();
		     try {
		       session = HibernateSessionFactory.getSession();
		       
		       //transaction = session.beginTransaction();
		       String procName = sql;
		       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Lssuing.class);
		       sqlQuery.setFirstResult(0);
		       sqlQuery.setMaxResults(cunt);
		       lssuings = sqlQuery.list();
		       //transaction.commit();
		     } catch (Exception e) {
		       //transaction.rollback();
		       throw e;
		     }finally{
		    	 
		     }
		     return lssuings;
		   }
		 
   public List<Lssuing> findLssuingsByUserId(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuing t where t.uservip.userId=? and t.state < 5 and t.lssuingType != 6 ");
       query.setInteger(0, userId);
       lssuings = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuings;
   }
 
   public List<Lssuing> findLssuingsByStatePage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = "select distinct a.* from  (select * from lssuing ls where ls.userId=? and ls.lssuingType != 6 ) a ,tenderWords t where a.userId=t.userId and t.state=1 and t.lssuingId=a.lssuingId and a.lssuingType != 6  ORDER BY a.lssuingId DESC ";
 
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Lssuing.class);
       sqlQuery.setInteger(0, userId);
       
       sqlQuery.setFirstResult(currentPage);
       sqlQuery.setMaxResults(pageSize);
       lssuings = sqlQuery.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuings;
   }
 
   public List<Lssuing> findLssuingsByUserIdAndState(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuing t where t.uservip.userId=? and t.state=2 and t.lssuingType != 6");
       query.setInteger(0, userId);
       lssuings = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuings;
   }
 
   public List<Lssuing> findLssuingsByUserIdAndByState(int userId, int state)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List lssuings = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Lssuing t where t.uservip.userId=? and t.state=? and t.lssuingType != 6");
       query.setInteger(0, userId);
       query.setInteger(1, state);
       lssuings = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuings;
   }
   /**
    * 修改借款方资料信息
    */
  
public boolean updateLssuingComp(LssuingComp lssuingComp) throws Exception {
	 Session session = null;
     //Transaction transaction = null; 
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(lssuingComp);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return flag;
 }

public LssuingComp findLssuingCompById(Integer compId) throws Exception {
	 Session session = null;
     //Transaction transaction = null;
	 LssuingComp lssuingComp = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from LssuingComp lc where lc.compId=?  ");
       query.setInteger(0, compId.intValue());
       lssuingComp = (LssuingComp)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
       throw e;
     }finally{
    	 
     }
     return lssuingComp;
}
 }
