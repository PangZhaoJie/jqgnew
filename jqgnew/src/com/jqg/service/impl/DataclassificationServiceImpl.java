 package com.jqg.service.impl;
 
 import com.jqg.dao.DataclassificationDao;
import com.jqg.dao.impl.DataclassificationDaoIpml;
import com.jqg.pojo.Dataclassification;
import com.jqg.service.DataclassificationService;

import java.util.List;
 
 public class DataclassificationServiceImpl
   implements DataclassificationService
 {
   DataclassificationDao dataclassificationDao = new DataclassificationDaoIpml();
 
   public boolean createDataclassification(Dataclassification dataclassification) throws Exception
   {
     return this.dataclassificationDao.addDataclassification(dataclassification);
   }
 
   public boolean updateDataclassification(Dataclassification dataclassification)
     throws Exception
   {
     return this.dataclassificationDao.updateDataclassification(dataclassification);
   }
 
   public boolean deleteDataclassification(Dataclassification dataclassification)
     throws Exception
   {
     return this.dataclassificationDao.deleteDataclassification(dataclassification);
   }
 
   public List<Dataclassification> findDataclassifications()
     throws Exception
   {
     return this.dataclassificationDao.findDataclassifications();
   }
 
   public Dataclassification findDataclassificationByDataClassId(int dataClassId)
     throws Exception
   {
     return this.dataclassificationDao.findDataclassificationByDataClassId(dataClassId);
   }
 }

