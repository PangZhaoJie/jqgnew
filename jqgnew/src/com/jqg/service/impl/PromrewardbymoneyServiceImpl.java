 package com.jqg.service.impl;
 
 import com.jqg.dao.PromrewardbymoneyDao;
import com.jqg.dao.impl.PromrewardbymoneyDaoIpml;
import com.jqg.pojo.Promrewardbymoney;
import com.jqg.service.PromrewardbymoneyService;

import java.util.List;
 
 public class PromrewardbymoneyServiceImpl
   implements PromrewardbymoneyService
 {
   PromrewardbymoneyDao promrewardbymoneyDao = new PromrewardbymoneyDaoIpml();
 
   public boolean createPromrewardbymoney(Promrewardbymoney promrewardbymoney) throws Exception
   {
     return this.promrewardbymoneyDao.addPromrewardbymoney(promrewardbymoney);
   }
 
   public boolean updatePromrewardbymoney(Promrewardbymoney promrewardbymoney)
     throws Exception
   {
     return this.promrewardbymoneyDao.updatePromrewardbymoney(promrewardbymoney);
   }
 
   public boolean deletePromrewardbymoney(Promrewardbymoney promrewardbymoney)
     throws Exception
   {
     return this.promrewardbymoneyDao.deletePromrewardbymoney(promrewardbymoney);
   }
 
   public List<Promrewardbymoney> findPromrewardbymoneys()
     throws Exception
   {
     return this.promrewardbymoneyDao.findPromrewardbymoneys();
   }
 
   public Promrewardbymoney findPromrewardbymoneyById(int promRewardByMoneyId)
     throws Exception
   {
     return this.promrewardbymoneyDao.findPromrewardbymoneyById(promRewardByMoneyId);
   }
 }

