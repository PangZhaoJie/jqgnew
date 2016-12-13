 package com.jqg.service.impl;
 
 import com.jqg.dao.PerioddayDao;
import com.jqg.dao.impl.PerioddayDaoImpl;
import com.jqg.pojo.Periodday;
import com.jqg.service.PerioddayService;

import java.util.List;
 
 public class PeriodDayServiceImpl
   implements PerioddayService
 {
   PerioddayDao perioddayDao = new PerioddayDaoImpl();
 
   public boolean addPeriodday(Periodday periodday) throws Exception
   {
     return this.perioddayDao.addPeriodday(periodday);
   }
 
   public boolean updatePeriodday(Periodday periodday)
     throws Exception
   {
     return this.perioddayDao.updatePeriodday(periodday);
   }
 
   public boolean deletePeriodday(Periodday periodday)
     throws Exception
   {
     return this.perioddayDao.deletePeriodday(periodday);
   }
 
   public List<Periodday> findPerioddays()
     throws Exception
   {
     return this.perioddayDao.findPerioddays();
   }
 
   public Periodday findPerioddayByPerioddayId(int perioddayId)
     throws Exception
   {
     return this.perioddayDao.findPerioddayByPerioddayId(perioddayId);
   }
 }

