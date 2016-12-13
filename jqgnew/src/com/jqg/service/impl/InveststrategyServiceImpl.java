 package com.jqg.service.impl;
 
 import com.jqg.dao.InveststrategyDao;
import com.jqg.dao.impl.InveststrategyDaoImpl;
import com.jqg.pojo.Investstrategy;
import com.jqg.service.InveststrategyService;

import java.util.List;
 
 public class InveststrategyServiceImpl
   implements InveststrategyService
 {
   InveststrategyDao investstrategyDao = new InveststrategyDaoImpl();
 
   public boolean addInveststrategy(Investstrategy investstrategy) throws Exception
   {
     return this.investstrategyDao.addInveststrategy(investstrategy);
   }
 
   public boolean updateInveststrategy(Investstrategy investstrategy)
     throws Exception
   {
     return this.investstrategyDao.updateInveststrategy(investstrategy);
   }
 
   public boolean deleteInveststrategy(Investstrategy investstrategy)
     throws Exception
   {
     return this.investstrategyDao.deleteInveststrategy(investstrategy);
   }
 
   public List<Investstrategy> findInveststrategys()
     throws Exception
   {
     return this.investstrategyDao.findInveststrategys();
   }
 
   public Investstrategy findInveststrategyById(Integer askId)
     throws Exception
   {
     return this.investstrategyDao.findInveststrategyById(askId);
   }
 
   public List<Investstrategy> findInveststrategysByIdex()
     throws Exception
   {
     return this.investstrategyDao.findInveststrategysByIdex();
   }
 
   public List<Investstrategy> findInveststrategyByType()
     throws Exception
   {
     return this.investstrategyDao.findInveststrategyByType();
   }
 
   public List<Investstrategy> findInveststrategyByDate(int flag, String sql) throws Exception
   {
     return this.investstrategyDao.findInveststrategyByDate(flag, sql);
   }
 
   public List<Investstrategy> findInveststrategyByPage(String sql)
     throws Exception
   {
     return this.investstrategyDao.findInveststrategyByPage(sql);
   }
 }

