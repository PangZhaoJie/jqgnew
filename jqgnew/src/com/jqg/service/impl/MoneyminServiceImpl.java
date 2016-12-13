 package com.jqg.service.impl;
 
 import com.jqg.dao.MoneyminDao;
import com.jqg.dao.impl.MoneyminDaoImpl;
import com.jqg.pojo.Moneymin;
import com.jqg.service.MoneyminService;

import java.util.List;
 
 public class MoneyminServiceImpl
   implements MoneyminService
 {
   MoneyminDao moneyminDao = new MoneyminDaoImpl();
 
   public boolean addMoneymin(Moneymin moneymin) throws Exception
   {
     return this.moneyminDao.addMoneymin(moneymin);
   }
 
   public boolean updateMoneymin(Moneymin moneymin)
     throws Exception
   {
     return this.moneyminDao.updateMoneymin(moneymin);
   }
 
   public boolean deleteMoneymin(Moneymin moneymin)
     throws Exception
   {
     return this.moneyminDao.deleteMoneymin(moneymin);
   }
 
   public List<Moneymin> findMoneymins()
     throws Exception
   {
     return this.moneyminDao.findMoneymins();
   }
 
   public Moneymin findMoneyminById(Integer moneyMinId)
     throws Exception
   {
     return this.moneyminDao.findMoneyminById(moneyMinId);
   }
 }

