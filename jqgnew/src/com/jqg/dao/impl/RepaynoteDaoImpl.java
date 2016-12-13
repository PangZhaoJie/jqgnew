package com.jqg.dao.impl;

import com.jqg.dao.RepaynoteDao;
import com.jqg.pojo.Repaynote;
import com.jqg.session.factory.HibernateSessionFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RepaynoteDaoImpl implements RepaynoteDao {
	public boolean addRepaynote(Repaynote repaynote) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();

			// transaction = session.beginTransaction();
			session.save(repaynote);
			// transaction.commit();
			flag = true;
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return flag;
	}

	public boolean updateRepaynote(Repaynote repaynote) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();

			// transaction = session.beginTransaction();
			session.merge(repaynote);
			// transaction.commit();
			flag = true;
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return flag;
	}

	public boolean deleteRepaynote(Repaynote repaynote) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();

			// transaction = session.beginTransaction();
			session.delete(repaynote);
			// transaction.commit();
			flag = true;
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return flag;
	}

	public List<Repaynote> findRepaynotes() throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Repaynote> repaynotes = new ArrayList<Repaynote>();
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session.createQuery("from Repaynote");
			repaynotes = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynotes;
	}

	public Repaynote findRepaynoteByRepaynoteId(int repaynoteId)
			throws Exception {
		Session session = null;
		// Transaction transaction = null;
		Repaynote repaynote = null;
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Repaynote  r where r.repayNoteId=?");
			query.setInteger(0, repaynoteId);
			repaynote = (Repaynote) query.uniqueResult();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynote;
	}

	public List<Repaynote> findRepaynoteBylssuing(int lssuingId)
			throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Repaynote> repaynotes = new ArrayList<Repaynote>();
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Repaynote r where r.lssuing.lssuingId=?");
			query.setInteger(0, lssuingId);
			repaynotes = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynotes;
	}

	public List<Repaynote> findRepaynoteByUserId(int userId) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Repaynote> repaynotes = new ArrayList<Repaynote>();
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Repaynote r where r.uservip.userId=? and r.repayState=2");
			query.setInteger(0, userId);
			repaynotes = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynotes;
	}

	public List<Repaynote> findRepaynoteByUserIdAndTime(int userId)
			throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Repaynote> repaynotes = new ArrayList<Repaynote>();
		String strs = new SimpleDateFormat("yyyy-MM").format(Calendar
				.getInstance().getTime());
		strs = strs + "%";
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Repaynote r where r.uservip.userId=? and r.repayState=0 and r.repayDate like '"
							+ strs + "'");
			query.setInteger(0, userId);
			repaynotes = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynotes;
	}

	public List<Repaynote> findRepayBySql(String sql) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Repaynote> repaynotes = new ArrayList<Repaynote>();
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(
					Repaynote.class);
			repaynotes = sqlQuery.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynotes;
	}

	public List<Repaynote> findComRepay(String starttime, String endtime)
			throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Repaynote> repaynotes = new ArrayList<Repaynote>();
		String strs = new SimpleDateFormat("yyyy-MM").format(Calendar
				.getInstance().getTime());
		strs = strs + "%";
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Repaynote r where r.lssuing.lssuingType=6 and r.repayState=0 and r.repayDate >= '"
							+ starttime + "' and and r.repayDate<= '"
							+ endtime + "'");
			repaynotes = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return repaynotes;
	}
}
