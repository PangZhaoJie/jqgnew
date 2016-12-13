 package com.jqg.service.impl;
 
 import com.jqg.dao.InvestmentlevelDao;
import com.jqg.dao.impl.InvestmentlevelDaoImpl;
import com.jqg.pojo.Investmentlevel;
import com.jqg.service.InvestmentlevelService;

import java.util.List;
 
 public class InvestmentlevelServiceImpl
   implements InvestmentlevelService
 {
   InvestmentlevelDao investmentlevelDao = new InvestmentlevelDaoImpl();
 
   public boolean createInvestmentlevel(Investmentlevel investmentlevel)
     throws Exception
   {
     return this.investmentlevelDao.addInvestmentlevel(investmentlevel);
   }
 
   public boolean updateInvestmentlevel(Investmentlevel investmentlevel)
     throws Exception
   {
     return this.investmentlevelDao.updateInvestmentlevel(investmentlevel);
   }
 
   public boolean deleteInvestmentlevel(Investmentlevel investmentlevel)
     throws Exception
   {
     return this.investmentlevelDao.deleteInvestmentlevel(investmentlevel);
   }
 
   public List<Investmentlevel> findInvestmentlevels()
     throws Exception
   {
     return this.investmentlevelDao.findInvestmentlevels();
   }
 
   public Investmentlevel findInvestmentlevelByinvestmentLevelId(int investmentLevelId)
     throws Exception
   {
     return this.investmentlevelDao.findInvestmentlevelByinvestmentLevelId(investmentLevelId);
   }
 }

