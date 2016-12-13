package com.jqg.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jqg.dao.AreaDao;
import com.jqg.pojo.Area;
import com.jqg.session.factory.HibernateSessionFactory;

public class AreaDaoImpl implements AreaDao {
	public boolean addArea(Area area) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();

			// transaction = session.beginTransaction();
			session.save(area);
			// transaction.commit();
			flag = true;
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return flag;
	}

	public boolean updateArea(Area area) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();

			// transaction = session.beginTransaction();
			session.merge(area);
			// transaction.commit();
			flag = true;
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return flag;
	}

	public boolean deleteArea(Area area) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();

			// transaction = session.beginTransaction();
			session.delete(area);
			// transaction.commit();
			flag = true;
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return flag;
	}

	public List<Area> findAreas() throws Exception {
		Session session = null;
		// Transaction transaction = null;
		List<Area> areas = new ArrayList<Area>();
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session.createQuery("from Area");
			areas = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return areas;
	}

	public Area findAreaByAreaId(int areaid) throws Exception {
		Session session = null;
		// Transaction transaction = null;
		Area area = null;
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session.createQuery("from Area b where b.id=?");
			query.setInteger(0, areaid);
			area = (Area) query.uniqueResult();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return area;
	}

	public List<Area> findAreasByParentid(int parentId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		// Transaction transaction = null;
		List<Area> areas = new ArrayList<Area>();
		try {
			session = HibernateSessionFactory.getSession();
			// transaction = session.beginTransaction();
			Query query = session.createQuery("from Area b  where b.parentid=?");
			query.setInteger(0, parentId);
			areas = query.list();
			// transaction.commit();
		} catch (Exception e) {
			// transaction.rollback();
			throw e;
		} finally {

		}
		return areas;
	}

}
