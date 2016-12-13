 package com.jqg.service.impl;
 
 import com.jqg.dao.InternallettermodelDao;
import com.jqg.dao.impl.InternallettermodelDaoImpl;
import com.jqg.pojo.Internallettermodel;
import com.jqg.service.InternallettermodelService;

import java.util.List;
 
 public class InternallettermodelServiceImpl
   implements InternallettermodelService
 {
   InternallettermodelDao internallettermodelDao = new InternallettermodelDaoImpl();
 
   public boolean createInternallettermodel(Internallettermodel internallettermodel)
     throws Exception
   {
     return this.internallettermodelDao.addInternallettermodel(internallettermodel);
   }
 
   public boolean updateInternallettermodel(Internallettermodel internallettermodel)
     throws Exception
   {
     return this.internallettermodelDao.updateInternallettermodel(internallettermodel);
   }
 
   public boolean deleteInternallettermodel(Internallettermodel internallettermodel)
     throws Exception
   {
     return this.internallettermodelDao.deleteInternallettermodel(internallettermodel);
   }
 
   public List<Internallettermodel> findInternallettermodels()
     throws Exception
   {
     return this.internallettermodelDao.findInternallettermodels();
   }
 
   public Internallettermodel findInternallettermodelByinterModelId(int interModelId)
     throws Exception
   {
     return this.internallettermodelDao.findInternallettermodelByinterModelId(interModelId);
   }
 }

