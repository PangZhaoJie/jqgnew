package com.jqg.dao;

import java.util.List;

import com.jqg.pojo.Transfer;

public abstract interface TransferDao {
	 public abstract Transfer findTransferByTransferId(Integer transferId) throws Exception;

	public abstract boolean updateTransfer(Transfer transfer) throws Exception;

	public abstract boolean addTransfer(Transfer transfer)throws Exception;
	
	public abstract List<Transfer> findTransferList()throws Exception;

	public abstract Transfer findTransferByTenderId(Integer tenderId)throws Exception;

	public abstract Transfer findTransferByTenderId1(Integer tenderId1)throws Exception;

	public abstract Transfer findTransferByTenderId3(Integer tenderId)throws Exception;
	public abstract List<Transfer> findTransferListBySql(String sql)throws Exception;
	
	public abstract List<Transfer> findTransferListByHql(String sql,int page,int pageSize)throws Exception;
}
