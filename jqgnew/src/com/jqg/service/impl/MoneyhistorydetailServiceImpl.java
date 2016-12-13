 package com.jqg.service.impl;
 
 import com.jqg.dao.MoneyhistorydetailDao;
import com.jqg.dao.impl.MoneyhistorydetailDaoImpl;
import com.jqg.pojo.Moneyhistorydetail;
import com.jqg.service.MoneyhistorydetailService;

import java.util.List;
 
 public class MoneyhistorydetailServiceImpl
   implements MoneyhistorydetailService
 {
   MoneyhistorydetailDao moneyhistorydetailDao = new MoneyhistorydetailDaoImpl();
 
   public boolean createMoneyhistorydetail(Moneyhistorydetail moneyhistorydetail)
     throws Exception
   {
     return this.moneyhistorydetailDao.addMoneyhistorydetail(moneyhistorydetail);
   }
 
   public boolean updateMoneyhistorydetail(Moneyhistorydetail moneyhistorydetail)
     throws Exception
   {
     return this.moneyhistorydetailDao.updateMoneyhistorydetail(moneyhistorydetail);
   }
 
   public boolean deleteMoneyhistorydetail(Moneyhistorydetail moneyhistorydetail)
     throws Exception
   {
     return this.moneyhistorydetailDao.deleteMoneyhistorydetail(moneyhistorydetail);
   }
 
   public List<Moneyhistorydetail> findMoneyhistorydetails()
     throws Exception
   {
     return this.moneyhistorydetailDao.findMoneyhistorydetails();
   }
 
   public Moneyhistorydetail findMoneyhistorydetailById(int moneyHistoryDetailId)
     throws Exception
   {
     return this.moneyhistorydetailDao.findMoneyhistorydetailById(moneyHistoryDetailId);
   }
 
   public Moneyhistorydetail findMoneyhistorydetailByUserId(int userId)
     throws Exception
   {
     return this.moneyhistorydetailDao.findMoneyhistorydetailByUserId(userId);
   }
 
   public List<Moneyhistorydetail> findMoneyhistorydetailBySql(String sql)
     throws Exception
   {
     return this.moneyhistorydetailDao.findMoneyhistorydetailBySql(sql);
   }
 }

