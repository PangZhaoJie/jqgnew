package com.jqg.service;

import java.util.List;

import com.jqg.pojo.Transfer;

public abstract interface TransferService {
	/**
	 * ����id��ѯ
	 * @param transferId
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTransferId(Integer transferId) throws Exception;
	/**
	 * �޸�
	 * @param transfer
	 * @return
	 * @throws Exception
	 */
	public abstract boolean updateTransfer(Transfer transfer) throws Exception;
	/**
	 * ���
	 * @param transfer
	 * @return
	 * @throws Exception
	 */
	public abstract boolean addTransfer(Transfer transfer)throws Exception;
	
	/**
	 * ��ѯ���е�ת�ü�¼
	 * @return
	 * @throws Exception
	 */
	public abstract List<Transfer> findTransferList()throws Exception;
	/**
	 *����ת����Ͷ�ʼ�¼id����ת�ü�¼
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTenderId(Integer tenderId) throws Exception;
	/**
	 *����ת����Ͷ�ʼ�¼id����ת�ã���˳ɹ��ģ���¼
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTenderId1(Integer tenderId1) throws Exception;
	/**
	 *����ת����Ͷ�ʼ�¼id����ת�ã�������ծȨ����¼
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTenderId3(Integer tenderId)throws Exception;
	/**
	 * ����sql��ѯ
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public abstract List<Transfer> findTransferListBySql(String sql)throws Exception;
	/**
	 * ����Hql��ѯ
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public abstract List<Transfer> findTransferListByHql(String sql,int page,int pageSize)throws Exception;
}
