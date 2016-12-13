 package com.jqg.service.impl;
 
 import com.jqg.dao.OnlinemodelDao;
import com.jqg.dao.impl.OnlinemodelDaoImpl;
import com.jqg.pojo.Onlinemodel;
import com.jqg.service.OnlinemodelService;

import java.util.List;
 
 public class OnlinemodelServiceImpl
   implements OnlinemodelService
 {
   OnlinemodelDao onlinemodelDao = new OnlinemodelDaoImpl();
 
   public boolean createOnlinemodel(Onlinemodel onlinemodel) throws Exception
   {
     return this.onlinemodelDao.addOnlinemodel(onlinemodel);
   }
 
   public boolean updateOnlinemodel(Onlinemodel onlinemodel)
     throws Exception
   {
     return this.onlinemodelDao.updateOnlinemodel(onlinemodel);
   }
 
   public boolean deleteOnlinemodel(Onlinemodel onlinemodel)
     throws Exception
   {
     return this.onlinemodelDao.deleteOnlinemodel(onlinemodel);
   }
 
   public List<Onlinemodel> findOnlinemodels()
     throws Exception
   {
     return this.onlinemodelDao.findOnlinemodels();
   }
 
   public List<Onlinemodel> findOnlinemodelByAble()
     throws Exception
   {
     return this.onlinemodelDao.findOnlinemodelByAble();
   }
 
   public Onlinemodel findOnlinemodelByonlineId(int onlineId)
     throws Exception
   {
     return this.onlinemodelDao.findOnlinemodelByonlineId(onlineId);
   }
 }

