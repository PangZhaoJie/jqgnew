 package com.jqg.service.impl;
 
 import com.jqg.dao.RecordDao;
import com.jqg.dao.impl.RecordDaoImpl;
import com.jqg.pojo.Record;
import com.jqg.service.RecordService;

import java.util.List;
 
 public class RecordServiceImpl
   implements RecordService
 {
   RecordDao recordDao = new RecordDaoImpl();
 
   public boolean addRecord(Record record) throws Exception
   {
     return this.recordDao.addRecord(record);
   }
 
   public boolean updateRecord(Record record)
     throws Exception
   {
     return this.recordDao.updateRecord(record);
   }
 
   public boolean deleteRecord(Record record)
     throws Exception
   {
     return this.recordDao.deleteRecord(record);
   }
 
   public List<Record> findRecords()
     throws Exception
   {
     return this.recordDao.findRecords();
   }
 
   public Record findRecordByRecordId(int recordId)
     throws Exception
   {
     return this.recordDao.findRecordByRecordId(recordId);
   }
 
   public List<Record> findRecordsBguserIdAntenderId(int userId, int tenderId)
     throws Exception
   {
     return this.recordDao.findRecordsBguserIdAntenderId(userId, tenderId);
   }
 
   public List<Record> findRecordsBguserIdAntenderIdSize(int userId, int tenderId)
     throws Exception
   {
     return this.recordDao.findRecordsBguserIdAntenderIdSize(userId, tenderId);
   }
 
   public List<Record> findRecordsBguserIdAntenderIdlimit1(int userId, int tenderId)
     throws Exception
   {
     return this.recordDao.findRecordsBguserIdAntenderIdlimit1(userId, tenderId);
   }
 
   public List<Record> findRecordsBguserIdAntenderIdpage(int userId, int tenderId, int currentPage, int totalPage)
     throws Exception
   {
     return this.recordDao.findRecordsBguserIdAntenderIdpage(userId, tenderId, currentPage, totalPage);
   }
 
   public List<Record> findRecordByUserIdAndTime(int userId)
     throws Exception
   {
     return this.recordDao.findRecordByUserIdAndTime(userId);
   }
 
   public List<Record> findRecordByRecordId(String sql)
     throws Exception
   {
     return this.recordDao.findRecordByRecordId(sql);
   }

public List<Record> findRecordBySql(String sql) throws Exception {
	// TODO Auto-generated method stub
	return this.recordDao.findRecordBySql(sql);
}

public  List<Record> findRecordByTenderId(Integer tenderId) throws Exception {
	return this.recordDao.findRecordByTenderId(tenderId);
}
 }

