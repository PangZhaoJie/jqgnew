package com.jqg.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.jqg.dao.TransferDao;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Transfer;
import com.jqg.session.factory.HibernateSessionFactory;

public class TransferDaoImpl implements TransferDao {

	public Transfer findTransferByTransferId(Integer transferId)
			throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Query query = session
				.createQuery("from Transfer t where t.transferId = ?");
		query.setInteger(0, transferId);
		Transfer transfer = (Transfer) query.uniqueResult();
		return transfer;
	}

	public boolean updateTransfer(Transfer transfer) throws Exception {
		Session session = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			session.merge(transfer);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	public boolean addTransfer(Transfer transfer) throws Exception {
		Session session = null;
		boolean flag = false;
		try {
			session = HibernateSessionFactory.getSession();
			session.save(transfer);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 查询所有的转让记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Transfer> findTransferList() throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Transfer");
		List<Transfer> transfer = query.list();
		return transfer;
	}

	public Transfer findTransferByTenderId(Integer tenderId) throws Exception {
		Session session =HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Transfer t where t.tender1=? and t.isTransfer<3");
		query.setInteger(0, tenderId);
		Transfer transfer =(Transfer) query.uniqueResult();
		return transfer;
	}

	public Transfer findTransferByTenderId1(Integer tenderId1) throws Exception {
		Session session =HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Transfer t where t.tender1=? and isTransfer in (1,2,4)");
		query.setInteger(0, tenderId1);
		Transfer transfer =(Transfer) query.uniqueResult();
		return transfer;
	}

	public Transfer findTransferByTenderId3(Integer tenderId) throws Exception {
		Session session =HibernateSessionFactory.getSession();
		Query query = session.createQuery("from Transfer t where t.tender1=? and t.isTransfer=3");
		query.setInteger(0, tenderId);
		Transfer transfer =(Transfer) query.uniqueResult();
		return transfer;
	}
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Transfer> findTransferListBySql(String sql) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Transfer.class);
		List<Transfer> list = sqlQuery.list();
		return list;
	}
	/**
	 * 根据Hql查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Transfer> findTransferListByHql(String sql,int page,int pageSize) throws Exception {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery(sql);
		if(page!=-1||pageSize!=-1){
			
			query.setFirstResult(page);
			query.setMaxResults(pageSize);
		}
		
		List<Transfer> transfer = query.list();
		return transfer;
	}
}
