package com.jqg.dao.impl;

import com.jqg.dao.UservipDao;
import com.jqg.pojo.Uservip;
import com.jqg.session.factory.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UservipDaoImpl implements UservipDao {
	public Integer addUservip(Uservip uservip) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Integer flag = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.save(uservip);
			//transaction.commit();
			flag = uservip.getUserId();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public boolean deleteUservip(Uservip uservip) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.delete(uservip);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public Uservip findUservipByUserid(int userid) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.userId=?");
			query.setInteger(0, userid);
			uservip = (Uservip) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}

	public Uservip findUservipByPasswordAndUserName(String password,
			String userName) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.password=? and u.userName=?");
			query.setParameter(0, password);
			query.setParameter(1, userName);
			uservip = (Uservip) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}

	public Uservip findUservipByPayPwdAndUserName(String payPwd, String userName)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.payPwd=? and u.userName=?");
			query.setParameter(0, payPwd);
			query.setParameter(1, userName);
			uservip = (Uservip) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}

	public Uservip findUservipByPayPwdAndUserID(String payPwd, int id)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.payPwd=? and u.userId=?");
			query.setParameter(0, payPwd);
			query.setParameter(1, id);
			uservip = (Uservip) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}
	
	public Uservip findUservipByMailAndPassword(String mail, String password)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.mail=? and u.password=?");
			query.setParameter(0, mail);
			query.setParameter(1, password);
			uservip = (Uservip) query.uniqueResult();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}

	public List<Uservip> findUservips() throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Uservip");
			uservips = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

	public boolean updateUservip(Uservip uservip) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			session.merge(uservip);
			//transaction.commit();
			flag = true;
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public String findUservipByUserName(String userName) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		String flag = "0";
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.userName=?");
			query.setParameter(0, userName);
			uservip = (Uservip) query.uniqueResult();
			if (uservip != null) {
				flag = "1";
			}
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public String findUservipByMail(String mail) throws Exception {
		String flag = "0";
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Uservip u where u.enable=1 and u.mail=?");
			query.setParameter(0, mail);
			uservip = (Uservip) query.uniqueResult();
			if (uservip != null) {
				flag = "1";
			}
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public List<Uservip> findUservipsByUserId(int userId) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip t where t.uservip.userId=?");
			query.setInteger(0, userId);
			uservips = query.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

	public List<Uservip> findUservipsByUserIdPage(int userId, int currentPage,
			int pageSize) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			String procName = "select * from uservip a where a.retereeUserId=? ORDER BY a.userId DESC";
			SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(
					Uservip.class);
			sqlQuery.setInteger(0, userId);
			sqlQuery.setFirstResult(currentPage);
		    sqlQuery.setMaxResults(pageSize);
			uservips = sqlQuery.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

	public List<Uservip> findUservipsPage(int currentPage, int pageSize)
			throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u ORDER BY u.userId desc");
			query.setFirstResult(currentPage);
			query.setMaxResults(pageSize);
			uservips = query.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

	public List<Uservip> findUservipsBysql(String sql) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			String procName = sql;
			SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(
					Uservip.class);
			uservips = sqlQuery.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

	public Uservip findUservipByEmail(String mail) throws Exception {

		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Uservip u where u.mail=?");
			query.setParameter(0, mail);
			uservip = (Uservip) query.uniqueResult();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}

	public Uservip findUservipByIdAndUserNameAndEmail(int id, String username) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		Uservip uservip = null;
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session
					.createQuery("from Uservip u where u.userId=? and u.userName=?");
			query.setInteger(0, id);
			query.setString(1, username);
			uservip = (Uservip) query.uniqueResult();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservip;
	}
	
	public List<Uservip> findUservips(int userId) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
//			String procName = "select * from uservip a where a.retereeUserId=? ORDER BY a.userId DESC";
			String procName="SELECT * FROM uservip WHERE FIND_IN_SET(userId, getChildList(?)) and userId!=?";
			SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(
					Uservip.class);
			sqlQuery.setInteger(0, userId);
			sqlQuery.setInteger(1, userId);
			uservips = sqlQuery.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

	public List<Uservip> findUservipsByFunPage(int userId, int currentPage,
			int pageSize) throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
//			String procName = "select * from uservip a where a.retereeUserId=? ORDER BY a.userId DESC";
			String procName="SELECT * FROM uservip WHERE FIND_IN_SET(userId, getChildList(?)) and userId!=? order by registerDate";
			SQLQuery sqlQuery = session.createSQLQuery(procName).addEntity(
					Uservip.class);
			sqlQuery.setInteger(0, userId);
			sqlQuery.setInteger(1, userId);
			sqlQuery.setFirstResult(currentPage);
		    sqlQuery.setMaxResults(pageSize);
			uservips = sqlQuery.list();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}
	
	public List<Uservip> findVIP() throws Exception {
		Session session = null;
		//Transaction transaction = null;
		List<Uservip> uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			//transaction = session.beginTransaction();
			Query query = session.createQuery("from Uservip where isVIP=1 ");
			uservips = query.list();
			//transaction.commit();
		} catch (Exception e) {
			//transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}

//  定时任务使用	
	
	public List<Uservip> findVIPJob() throws Exception {
		Session session = null;
		Transaction transaction = null;
		List<Uservip> uservips = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			
			transaction = session.beginTransaction();
			Query query = session.createQuery("from Uservip where isVIP=1 ");
			uservips = query.list();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			
		}
		return uservips;
	}
	
	public boolean updateUservipJob(Uservip uservip) throws Exception {
		Session session = null;
		Transaction transaction = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			
			transaction = session.beginTransaction();
			session.merge(uservip);
			transaction.commit();
			flag = true;
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			
		}
		return flag;
	}

	public int findUservipBytoatl() throws Exception {
		Session session=null;
		List<Uservip> uservip=new ArrayList<Uservip>();
		try {
			session=HibernateSessionFactory.getSession();
			SQLQuery sqlQuery=session.createSQLQuery("select count(DISTINCT t.userId) as counttender from uservip as t");
			uservip=sqlQuery.list();
		} catch (Exception e) {
			throw e;
		}finally{
			
		}
		if(uservip!=null && uservip.size()>0){
			Object ob=uservip.get(0);
			int total=Integer.valueOf(ob.toString());
			return total;
		}
		return 0;
	}
	
	
//	定时任务结束
	
	
	public int findCount(String sql) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		List list = new ArrayList();
		try {
			session = HibernateSessionFactory.getSession();
			SQLQuery sqlQuery = session.createSQLQuery(sql);
			list = sqlQuery.list();
		} catch (Exception e) {
			throw e;
		} finally {

		}
		if (list != null && list.size() > 0) {
			Object obj = list.get(0);
			int totalnum = Integer.valueOf(obj.toString());
			return totalnum;
		} else {
			return 0;
		}
	}
}
