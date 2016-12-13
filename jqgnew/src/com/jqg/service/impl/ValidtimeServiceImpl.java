 package com.jqg.service.impl;
 
 import com.jqg.dao.ValidtimeDao;
import com.jqg.dao.impl.ValidtimeDaoImpl;
import com.jqg.pojo.Validtime;
import com.jqg.service.ValidtimeService;

import java.util.List;
 
 public class ValidtimeServiceImpl
   implements ValidtimeService
 {
   ValidtimeDao validtimeDao = new ValidtimeDaoImpl();
 
   public boolean addValidtime(Validtime validtime) throws Exception
   {
     return this.validtimeDao.addValidtime(validtime);
   }
 
   public boolean updateValidtime(Validtime validtime)
     throws Exception
   {
     return this.validtimeDao.updateValidtime(validtime);
   }
 
   public boolean deleteValidtime(Validtime validtime)
     throws Exception
   {
     return this.validtimeDao.deleteValidtime(validtime);
   }
 
   public List<Validtime> findValidtimes()
     throws Exception
   {
     return this.validtimeDao.findValidtimes();
   }
 
   public Validtime findValidtimeById(Integer validTimeId)
     throws Exception
   {
     return this.validtimeDao.findValidtimeById(validTimeId);
   }
 }

