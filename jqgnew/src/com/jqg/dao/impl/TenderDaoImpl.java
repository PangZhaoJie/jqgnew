 package com.jqg.dao.impl;
 
 import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jqg.dao.TenderDao;
import com.jqg.pojo.Rank;
import com.jqg.pojo.Repaynote;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Uservip;
import com.jqg.session.factory.HibernateSessionFactory;
 
 public class TenderDaoImpl
   implements TenderDao
 {
   public boolean addTender(Tender tender)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(tender);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deleteTender(Tender tender)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(tender);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public Tender findTenderById(Integer tenderWordsId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Tender tender = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tender t where t.tenderId=?");
       query.setInteger(0, tenderWordsId.intValue());
       tender = (Tender)query.uniqueResult();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tender;
   }
 
   public List<Tender> findTenders()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenders = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tender");
       tenders = query.list();
       //transaction.commit();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenders;
   }
 
   public boolean updateTender(Tender tender)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(tender);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public List<Tender> findTendersBylssuingId(int lssuingId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenders = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tender t where t.lssuing.lssuingId=?");
       query.setInteger(0, lssuingId);
       tenders = query.list();
     } catch (Exception e) {
		throw e;
	}finally{
		 
	}
     return tenders;
   }
 
   public List<Tender> findTendersBylssuingIdPage(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenders = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       String procName = sql;
       SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(Tender.class);
 
       tenders = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenders;
   }
 
   public List<Tender> findTendersByuserID(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenders = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tender t where t.uservip.userId = ? and t.lssuing.state=1 or  t.lssuing.state=3  ");
       query.setInteger(0, userId);
       tenders = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenders;
   }
 
   public List<Tender> findTendersByuserIdCategory(int userId, int state)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenders = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tender t where t.uservip.userId = ? and t.lssuing.state=? ");
       query.setInteger(0, userId);
       query.setInteger(1, state);
       tenders = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenders;
   }
 
   public List<Tender> findTendersByuserIdCategoryPage(int userId, int state, int currentPage, int pageSize)
     throws Exception
   {
	     Session session = null;
	     //Transaction transaction = null;
	     List tenders = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Tender t where t.uservip.userId = ? and t.lssuing.state=? ");
	       query.setInteger(0, userId);
	       query.setInteger(1, state);
	       if(currentPage!=0&&pageSize!=0){
	    	   query.setFirstResult(currentPage);
	           query.setMaxResults(pageSize);
	       }
	       
	       tenders = query.list();
	     } catch (Exception e) {
	       //transaction.rollback();
			throw e;
		}finally{
			 
		}
	     return tenders;
   }
 
   public List<Tender> findTendersByuser(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List tenders = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Tender t where t.uservip.userId = ?");
       query.setInteger(0, userId);
       tenders = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return tenders;
   }

public List<Rank> findTendersByDate(Timestamp startDate, Timestamp endDate)
		throws Exception {
	Tender t=new Tender();
	
	Session session = null;
	List<Rank> rList=new ArrayList<Rank>();
    List<Object[]> list = new ArrayList();
    try {
      session = HibernateSessionFactory.getSession();
      
//      String procName = "select t.userId userId, sum(t.money) money  from tender t,tender d where t.tenderId=d.tenderId and t.tenderTime   BETWEEN  ?  AND  ?   GROUP BY t.userId  order by t.money DESC LIMIT 0,5";
      String procName ="select a.userId,a.money from (select t.userId userId, sum(t.money) money  from tender t,tender d where t.tenderId=d.tenderId and t.tenderTime  BETWEEN  ?  AND  ? GROUP BY t.userId  )a ORDER BY a.money DESC ";
      SQLQuery sqlQuery = session.createSQLQuery(procName).addScalar("userId").addScalar("money");
      sqlQuery.setTimestamp(0, startDate);
      sqlQuery.setTimestamp(1, endDate);
      sqlQuery.setFirstResult(0);
      sqlQuery.setMaxResults(5);
      list=sqlQuery.list();
      for(Object[] obj:list){
    	  Rank r=new Rank();
    	  for(int i=0;i<obj.length;i++){
    		  if(i==0){
    			  r.setId(obj[i].toString());
    		  }else{
    			  r.setMoney(obj[i].toString());
    		  }
    	  }
    	  rList.add(r);
      }
      
    } catch (Exception e) {
		throw e;
	}finally{
		 
	}
    
    return rList;
}

	public List<Tender> finTendersBystate(int userId, int state) throws Exception {
	     Session session = null;
	     //Transaction transaction = null;
	     List tenders = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Tender t where t.uservip.userId = ? and t.state=? ");
	       query.setInteger(0, userId);
	       query.setInteger(1, state);
	       tenders = query.list();
	     } catch (Exception e) {
	       //transaction.rollback();
			throw e;
		}finally{
			 
		}
	     return tenders;
	   
	}

	public List<Tender> finTendersBystatePage(int userId, int state, int page,
			int pagesize) throws Exception {
	     Session session = null;
	     //Transaction transaction = null;
	     List tenders = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       
	       //transaction = session.beginTransaction();
	       Query query = session.createQuery("from Tender t where t.uservip.userId = ? and t.state=? ");
	       query.setInteger(0, userId);
	       query.setInteger(1, state);
	       query.setFirstResult(page);
	       query.setMaxResults(pagesize);
	       tenders = query.list();
	     } catch (Exception e) {
	       //transaction.rollback();
			throw e;
		}finally{
			 
		}
	     return tenders;
	   
	}

	public List<Tender> findTenderBySql(String sql) throws Exception {
	     Session session = null;
	     //Transaction transaction = null;
	     List repaynotes = new ArrayList();
	     try
	     {
	       session = HibernateSessionFactory.getSession(); 
	       //transaction = session.beginTransaction();
	       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Tender.class);
	       repaynotes = sqlQuery.list();
	     }
	     catch (Exception e) {
	       //transaction.rollback();
			throw e;
		}finally{
			 
		}
	     return repaynotes;
	   
	}
	
	public List<Tender> findTendersByID(int userId) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List list = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
//			String procName = "select * from uservip a where a.retereeUserId=? ORDER BY a.userId DESC";
			String procName="SELECT t.* FROM tender t ,uservip u WHERE t.userId=u.userId and  FIND_IN_SET(u.userId, getChildList(?)) and u.userId!=? ORDER BY t.tenderId ;";
			SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(
					Tender.class);
			sqlQuery.setInteger(0, userId);
			sqlQuery.setInteger(1, userId);
			list = sqlQuery.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return list;
	}
 	
 	public List<Tender> findTendersByFunPage(int userId, int currentPage,
			int pageSize) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List<Tender> list = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
//			String procName = "select * from uservip a where a.retereeUserId=? ORDER BY a.userId DESC";
			String procName="SELECT t.* FROM tender t ,uservip u WHERE t.userId=u.userId and  FIND_IN_SET(u.userId, getChildList(?)) and u.userId!=? ORDER BY t.tenderId";
			SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(
					Tender.class);
			sqlQuery.setInteger(0, userId);
			sqlQuery.setInteger(1, userId);
			sqlQuery.setFirstResult(currentPage);
		    sqlQuery.setMaxResults(pageSize);
			list = sqlQuery.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return list;
		
	}

	public List<Tender> findTenderByHql(String sql, Integer page,
			Integer pageSize) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(sql);
		if(page!=-1 && pageSize!=-1){
			query.setFirstResult(page);
			query.setMaxResults(pageSize);
		}
		List<Tender> tenders=query.list();
		return tenders;
	}

 }

