 package com.jqg.service.impl;
 
 import com.jqg.dao.ReturnwayDao;
import com.jqg.dao.impl.ReturnwayDaoImpl;
import com.jqg.pojo.Returnway;
import com.jqg.service.ReturnwayService;

import java.util.List;
 
 public class ReturnwayServiceImpl
   implements ReturnwayService
 {
   ReturnwayDao returnwayDao = new ReturnwayDaoImpl();
 
   public boolean addReturnway(Returnway returnway) throws Exception
   {
     return this.returnwayDao.addReturnway(returnway);
   }
 
   public boolean updateReturnway(Returnway returnway)
     throws Exception
   {
     return this.returnwayDao.updateReturnway(returnway);
   }
 
   public boolean deleteReturnway(Returnway returnway)
     throws Exception
   {
     return this.returnwayDao.deleteReturnway(returnway);
   }
 
   public List<Returnway> findReturnways()
     throws Exception
   {
     return this.returnwayDao.findReturnways();
   }
 
   public Returnway findReturnwayById(Integer returnWayId)
     throws Exception
   {
     return this.returnwayDao.findReturnwayById(returnWayId);
   }
 }

