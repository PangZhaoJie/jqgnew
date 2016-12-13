package com.jqg.dao.impl;

import com.jqg.dao.ManagerDao;
import com.jqg.pojo.Manager;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManagerDaoImpl implements ManagerDao {
	public boolean addManager(Manager manager) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.save(manager);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public boolean deleteManager(Manager manager) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.delete(manager);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public Manager findManagerByManagerId(Integer managerId) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Manager manager = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Manager m where m.managerId=?");
			query.setInteger(0, managerId.intValue());
			manager = (Manager) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return manager;
	}

	public List<Manager> findManagers() throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List managers = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Manager");
			managers = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return managers;
	}
	/**
	 * 查询所有的客服信息
	 */
	public List<Manager> findManagerByIsCustomer()throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List<Manager> managers =new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Manager where isCustomer=1");
			managers = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return managers;
	}

	public boolean updateManager(Manager Manager) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.merge(Manager);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public Manager findManagerByLogin(String managerName, String password,
			String passwordWord) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Manager manager = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Manager m where m.managerName=? and m.password=? and m.passwordWord=?");
			query.setParameter(0, managerName);
			query.setParameter(1, password);
			query.setParameter(2, passwordWord);
			manager = (Manager) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return manager;
	}

	public Manager findManagerByname(String name) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Manager manager = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Manager m where m.managerName=?");
			query.setString(0, name);
			manager = (Manager) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return manager;
	}

	public List<Manager> findManagerspage(int currentPage, int pageSize)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List managers = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Manager");
			query.setFirstResult(currentPage);
			query.setMaxResults(pageSize);
			managers = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return managers;
	}

	public List<Manager> findManagerBySql(String sql) throws Exception {
		Session session = null;
//		//Transaction transaction = null;
		List managers = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
//			//transaction = session.beginTransaction();
			SQLQuery query = session.createSQLQuery(sql).addEntity(
					Manager.class);
			managers = query.list();
//			//transaction.commit();
		} catch (Exception e) {
//			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return managers;
	}

	public List<Manager> findManagers1(Integer isCustomer) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List managers = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Manager where isBan=1 and isCustomer="
							+ isCustomer);
			managers = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		}
		return managers;
	}

	public List<Manager> findManagerspage1(int currentPage, int pageSize,
			Integer isCustomer) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List managers = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Manager where isBan=1 and isCustomer="
							+ isCustomer);
			query.setFirstResult(currentPage);
			query.setMaxResults(pageSize);
			managers = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		}
		return managers;
	}

}
