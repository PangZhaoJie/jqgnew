package com.jqg.dao.impl;

import com.jqg.dao.OfflinerechargeDao;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OfflinerechargeDaoImpl implements OfflinerechargeDao {
	public boolean addOfflinerecharge(Offlinerecharge offlinerecharge)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.save(offlinerecharge);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return flag;
	}

	public boolean updateOfflinerecharge(Offlinerecharge offlinerecharge)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.merge(offlinerecharge);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return flag;
	}

	public boolean deleteOfflinerecharge(Offlinerecharge offlinerecharge)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.delete(offlinerecharge);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return flag;
	}

	public List<Offlinerecharge> findOfflinerecharges() throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List offlinerecharges = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Offlinerecharge where rechargeStatus=1");
			offlinerecharges = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return offlinerecharges;
	}

	public Offlinerecharge findOfflinerechargeById(int offlineRechargeId)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Offlinerecharge offlinerecharge = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Offlinerecharge o where o.offlineRechargeId=?");
			query.setInteger(0, offlineRechargeId);
			offlinerecharge = (Offlinerecharge) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return offlinerecharge;
	}

	public List<Offlinerecharge> findOfflinerechargeBySql(String sql)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List offlinerecharges = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql).addEntity(
					Offlinerecharge.class);
			offlinerecharges = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return offlinerecharges;
	}

	public List findListbysqsl(String sql) throws Exception {
		// TODO Auto-generated method stub

		Session session = null;
		//Transaction transaction = null;
		List offlinerecharges = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql);
			offlinerecharges = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
		   throw e;
		}finally{
			 
		}
		return offlinerecharges;

	}
}
