package com.jqg.service.impl;

import java.util.List;

import com.jqg.dao.TransferDao;
import com.jqg.dao.impl.TransferDaoImpl;
import com.jqg.pojo.Transfer;
import com.jqg.service.TransferService;

public class TransferServiceImpl implements TransferService {
	TransferDao transferDao = new TransferDaoImpl();

	public Transfer findTransferByTransferId(Integer transferId)throws Exception {
		return this.transferDao.findTransferByTransferId(transferId);
	}

	public boolean updateTransfer(Transfer transfer) throws Exception {
		return this.transferDao.updateTransfer(transfer);
	}

	public boolean addTransfer(Transfer transfer) throws Exception {
		return this.transferDao.addTransfer(transfer);
	}
	
	public  List<Transfer> findTransferList()throws Exception{
		
		return this.transferDao.findTransferList();
	}
	public Transfer findTransferByTenderId(Integer tenderId) throws Exception {
		return this.transferDao.findTransferByTenderId(tenderId);
	}

	public Transfer findTransferByTenderId1(Integer tenderId1) throws Exception {
		return this.transferDao.findTransferByTenderId1(tenderId1);
	}


	public Transfer findTransferByTenderId3(Integer tenderId) throws Exception {
		return this.transferDao.findTransferByTenderId3(tenderId);
	}
	
	public  List<Transfer> findTransferListBySql(String sql)throws Exception{
		
		return this.transferDao.findTransferListBySql(sql);
	}
	public  List<Transfer> findTransferListByHql(String sql,int page,int pageSize)throws Exception{
		
		return this.transferDao.findTransferListByHql(sql, page, pageSize);
	}
}
