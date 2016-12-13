 package com.jqg.service.impl;
 
 import com.jqg.dao.PeriodtimeDao;
import com.jqg.dao.impl.PeriodtimeDaoImpl;
import com.jqg.pojo.Periodtime;
import com.jqg.service.PeriodtimeService;

import java.util.List;
 
 public class PeriodtimeServiceImpl
   implements PeriodtimeService
 {
   PeriodtimeDao periodtimeDao = new PeriodtimeDaoImpl();
 
   public boolean addPeriodtime(Periodtime periodtime) throws Exception
   {
     return this.periodtimeDao.addPeriodtime(periodtime);
   }
 
   public boolean updatePeriodtime(Periodtime periodtime)
     throws Exception
   {
     return this.periodtimeDao.updatePeriodtime(periodtime);
   }
 
   public boolean deletePeriodtime(Periodtime periodtime)
     throws Exception
   {
     return this.periodtimeDao.deletePeriodtime(periodtime);
   }
 
   public List<Periodtime> findPeriodtimes()
     throws Exception
   {
     return this.periodtimeDao.findPeriodtimes();
   }
 
   public Periodtime findPeriodtimeByPeriodtimeId(int periodtimeId)
     throws Exception
   {
     return this.periodtimeDao.findPeriodtimeByPeriodtimeId(periodtimeId);
   }
 }

