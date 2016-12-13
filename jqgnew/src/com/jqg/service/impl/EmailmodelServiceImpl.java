 package com.jqg.service.impl;
 
 import com.jqg.dao.EmailmodelDao;
import com.jqg.dao.impl.EmailmodelDaoImpl;
import com.jqg.pojo.Emailmodel;
import com.jqg.service.EmailmodelService;

import java.util.List;
 
 public class EmailmodelServiceImpl
   implements EmailmodelService
 {
   EmailmodelDao emailmodelDao = new EmailmodelDaoImpl();
 
   public boolean createEmailmodel(Emailmodel emailmodel) throws Exception
   {
     return this.emailmodelDao.addEmailmodel(emailmodel);
   }
 
   public boolean updateEmailmodel(Emailmodel emailmodel)
     throws Exception
   {
     return this.emailmodelDao.updateEmailmodel(emailmodel);
   }
 
   public boolean deleteEmailmodel(Emailmodel emailmodel)
     throws Exception
   {
     return this.emailmodelDao.deleteEmailmodel(emailmodel);
   }
 
   public List<Emailmodel> findEmailmodels()
     throws Exception
   {
     return this.emailmodelDao.findEmailmodels();
   }
 
   public Emailmodel findEmailmodelByemailModelId(int emailModelId)
     throws Exception
   {
     return this.emailmodelDao.findEmailmodelByemailModelId(emailModelId);
   }
 }

