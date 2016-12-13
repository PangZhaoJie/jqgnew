package com.jqg.service;

import java.util.List;

import com.jqg.pojo.Transfer;

public abstract interface TransferService {
	/**
	 * 根据id查询
	 * @param transferId
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTransferId(Integer transferId) throws Exception;
	/**
	 * 修改
	 * @param transfer
	 * @return
	 * @throws Exception
	 */
	public abstract boolean updateTransfer(Transfer transfer) throws Exception;
	/**
	 * 添加
	 * @param transfer
	 * @return
	 * @throws Exception
	 */
	public abstract boolean addTransfer(Transfer transfer)throws Exception;
	
	/**
	 * 查询所有的转让记录
	 * @return
	 * @throws Exception
	 */
	public abstract List<Transfer> findTransferList()throws Exception;
	/**
	 *根据转让人投资记录id查找转让记录
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTenderId(Integer tenderId) throws Exception;
	/**
	 *根据转让人投资记录id查找转让（审核成功的）记录
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTenderId1(Integer tenderId1) throws Exception;
	/**
	 *根据转让人投资记录id查找转让（撤消的债权）记录
	 * @return
	 * @throws Exception
	 */
	public abstract Transfer findTransferByTenderId3(Integer tenderId)throws Exception;
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public abstract List<Transfer> findTransferListBySql(String sql)throws Exception;
	/**
	 * 根据Hql查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public abstract List<Transfer> findTransferListByHql(String sql,int page,int pageSize)throws Exception;
}
