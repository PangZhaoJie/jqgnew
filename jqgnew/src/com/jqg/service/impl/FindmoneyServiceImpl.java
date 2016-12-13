 package com.jqg.service.impl;
 
 import com.jqg.dao.FindmoneyDao;
import com.jqg.dao.impl.FindmoneyDaoImpl;
import com.jqg.pojo.Findmoney;
import com.jqg.service.FindmoneyService;

import java.util.List;
 
 public class FindmoneyServiceImpl
   implements FindmoneyService
 {
   FindmoneyDao findmoneyDao = new FindmoneyDaoImpl();
 
   public boolean createFindmoney(Findmoney findmoney) throws Exception
   {
     return this.findmoneyDao.addFindmoney(findmoney);
   }
 
   public boolean updateFindmoney(Findmoney findmoney)
     throws Exception
   {
     return this.findmoneyDao.updateFindmoney(findmoney);
   }
 
   public boolean deleteFindmoney(Findmoney findmoney)
     throws Exception
   {
     return this.findmoneyDao.deleteFindmoney(findmoney);
   }
 
   public List<Findmoney> findFindmoneys()
     throws Exception
   {
     return this.findmoneyDao.findFindmoneys();
   }
 
   public Findmoney findFindmoneyByfindMoneyId(int findMoneyId)
     throws Exception
   {
     return this.findmoneyDao.findFindmoneyByfindMoneyId(findMoneyId);
   }
 }

