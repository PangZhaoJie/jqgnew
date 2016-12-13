 package com.jqg.service.impl;
 
 import com.jqg.dao.MoneycountDao;
import com.jqg.dao.impl.MoneycountDaoIpml;
import com.jqg.pojo.Moneycount;
import com.jqg.service.MoneycountService;

import java.util.List;
 
 public class MoneycountServiceImpl
   implements MoneycountService
 {
   MoneycountDao moneycountDao = new MoneycountDaoIpml();
 
   public boolean createMoneycount(Moneycount moneycount) throws Exception
   {
     return this.moneycountDao.addMoneycount(moneycount);
   }
 
   public boolean updateMoneycount(Moneycount moneycount)
     throws Exception
   {
     return this.moneycountDao.updateMoneycount(moneycount);
   }
 
   public boolean deleteMoneycount(Moneycount moneycount)
     throws Exception
   {
     return this.moneycountDao.deleteMoneycount(moneycount);
   }
 
   public List<Moneycount> findMoneycounts()
     throws Exception
   {
     return this.moneycountDao.findMoneycounts();
   }
 
   public Moneycount findMoneycountById(int moneyCountId)
     throws Exception
   {
     return this.moneycountDao.findMoneycountById(moneyCountId);
   }
 
   public Moneycount findMoneycountByuserId(int userId)
     throws Exception
   {
     return this.moneycountDao.findMoneycountByuserId(userId);
   }
 
   public List<Moneycount> findMoneycountBySql(String sql)
     throws Exception
   {
     return this.moneycountDao.findMoneycountBySql(sql);
   }
 }

