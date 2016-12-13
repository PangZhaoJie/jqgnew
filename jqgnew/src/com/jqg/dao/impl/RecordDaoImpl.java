 package com.jqg.dao.impl;
 
 import com.jqg.dao.RecordDao;
import com.jqg.pojo.Record;
import com.jqg.session.factory.HibernateSessionFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class RecordDaoImpl
   implements RecordDao
 {
   public boolean addRecord(Record record)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.save(record);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean updateRecord(Record record)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.merge(record);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deleteRecord(Record record)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       session.delete(record);
       //transaction.commit();
       flag = true;
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public List<Record> findRecords()
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Record");
       records = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }
 
   public Record findRecordByRecordId(int recordId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     Record record = null;
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Record r where r.recordId=?");
       query.setInteger(0, recordId);
       record = (Record)query.uniqueResult();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return record;
   }
 
   public List<Record> findRecordByUserIdAndTime(int userId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     String strs = new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime());
     strs = strs + "%";
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Record r where r.uservip.userId=? and r.recordState=0 and r.recordDate like '" + strs + "'");
       query.setInteger(0, userId);
       records = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }
 
   public List<Record> findRecordsBguserIdAntenderId(int userId, int tenderId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
//       Query query = session.createQuery("from Record r where r.uservip.userId =? and r.tender.tenderId=? and r.recordState=1");
       Query query = session.createQuery("from Record r where r.uservip.userId =? and r.tender.tenderId=? ");
       query.setInteger(0, userId);
       query.setInteger(1, tenderId);
       records = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }
 
   public List<Record> findRecordsBguserIdAntenderIdSize(int userId, int tenderId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Record r where r.uservip.userId =? and r.tender.tenderId=? ");
       query.setInteger(0, userId);
       query.setInteger(1, tenderId);
       records = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }
 
   public List<Record> findRecordsBguserIdAntenderIdpage(int userId, int tenderId, int currentPage, int totalPage)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Record r where r.uservip.userId =? and r.tender.tenderId=? ");
       query.setInteger(0, userId);
       query.setInteger(1, tenderId);
       query.setFirstResult(currentPage);
       query.setMaxResults(totalPage);
       records = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }
 
   public List<Record> findRecordsBguserIdAntenderIdlimit1(int userId, int tenderId)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       Query query = session.createQuery("from Record r where r.uservip.userId =? and r.tender.tenderId=? and r.recordState=1");
       query.setInteger(0, userId);
       query.setInteger(1, tenderId);
       query.setFirstResult(0);
       query.setMaxResults(1);
       records = query.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }
 
   public List<Record> findRecordByRecordId(String sql)
     throws Exception
   {
     Session session = null;
     //Transaction transaction = null;
     List records = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       
       //transaction = session.beginTransaction();
       SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Record.class);
       records = sqlQuery.list();
     } catch (Exception e) {
       //transaction.rollback();
		throw e;
	}finally{
		 
	}
     return records;
   }

public List<Record> findRecordBySql(String sql) throws Exception {

    Session session = null;
    //Transaction transaction = null;
    List records = new ArrayList();
    try {
      session = HibernateSessionFactory.getSession();
      
      //transaction = session.beginTransaction();
      SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Record.class);
      records = sqlQuery.list();
    } catch (Exception e) {
      //transaction.rollback();
		throw e;
	}finally{
		 
	}
    return records;
  
}

	public  List<Record> findRecordByTenderId(Integer tenderId) throws Exception {
		Session session = null;
		List records = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(" from Record r where r.tender.tenderId = ?");
			query.setInteger(0, tenderId);
			records = query.list();
		} catch (Exception e) {
			throw e ;
		}finally{
			
		}
		return records;
	}
 }

