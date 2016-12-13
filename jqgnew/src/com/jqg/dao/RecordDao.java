package com.jqg.dao;

import com.jqg.pojo.Record;
import com.jqg.pojo.Tender;

import java.util.List;

public abstract interface RecordDao
{
  public abstract boolean addRecord(Record paramRecord)
    throws Exception;

  public abstract boolean updateRecord(Record paramRecord)
    throws Exception;

  public abstract boolean deleteRecord(Record paramRecord)
    throws Exception;

  public abstract List<Record> findRecords()
    throws Exception;

  public abstract Record findRecordByRecordId(int paramInt)
    throws Exception;

  public abstract List<Record> findRecordByRecordId(String paramString)
    throws Exception;

  public abstract List<Record> findRecordsBguserIdAntenderId(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Record> findRecordsBguserIdAntenderIdSize(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Record> findRecordsBguserIdAntenderIdpage(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws Exception;

  public abstract List<Record> findRecordsBguserIdAntenderIdlimit1(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Record> findRecordByUserIdAndTime(int paramInt)
    throws Exception;
  
  public abstract List<Record> findRecordBySql(String sql)
		    throws Exception;

public abstract  List<Record> findRecordByTenderId(Integer tenderId)throws Exception;
}
