 package com.jqg.service.impl;
 
 import com.jqg.dao.MoneyuseDao;
import com.jqg.dao.impl.MoneyuseDaoImpl;
import com.jqg.pojo.Moneyuse;
import com.jqg.service.MoneyuseService;

import java.util.List;
 
 public class MoneyuseServiceImpl
   implements MoneyuseService
 {
   MoneyuseDao moneyuseDao = new MoneyuseDaoImpl();
 
   public boolean addMoneyuse(Moneyuse moneyuse) throws Exception
   {
     return this.moneyuseDao.addMoneyuse(moneyuse);
   }
 
   public boolean updateMoneyuse(Moneyuse moneyuse)
     throws Exception
   {
     return this.moneyuseDao.updateMoneyuse(moneyuse);
   }
 
   public boolean deleteMoneyuse(Moneyuse moneyuse)
     throws Exception
   {
     return this.moneyuseDao.deleteMoneyuse(moneyuse);
   }
 
   public List<Moneyuse> findMoneyuses()
     throws Exception
   {
     return this.moneyuseDao.findMoneyuses();
   }
 
   public Moneyuse findMoneyuseById(Integer moneyUseId)
     throws Exception
   {
     return this.moneyuseDao.findMoneyuseById(moneyUseId);
   }
 }

