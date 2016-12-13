 package com.jqg.service.impl;
 
 import com.jqg.dao.MoneymaxDao;
import com.jqg.dao.impl.MoneymaxDaoImpl;
import com.jqg.pojo.Moneymax;
import com.jqg.service.MoneymaxService;

import java.util.List;
 
 public class MoneymaxServiceImpl
   implements MoneymaxService
 {
   MoneymaxDao moneymaxDao = new MoneymaxDaoImpl();
 
   public boolean addMoneymax(Moneymax moneymax) throws Exception
   {
     return this.moneymaxDao.addMoneymax(moneymax);
   }
 
   public boolean updateMoneymax(Moneymax moneymax)
     throws Exception
   {
     return this.moneymaxDao.updateMoneymax(moneymax);
   }
 
   public boolean deleteMoneymax(Moneymax moneymax)
     throws Exception
   {
     return this.moneymaxDao.deleteMoneymax(moneymax);
   }
 
   public List<Moneymax> findMoneymaxs()
     throws Exception
   {
     return this.moneymaxDao.findMoneymaxs();
   }
 
   public Moneymax findMoneymaxById(Integer moneyMaxId)
     throws Exception
   {
     return this.moneymaxDao.findMoneymaxById(moneyMaxId);
   }
 }

