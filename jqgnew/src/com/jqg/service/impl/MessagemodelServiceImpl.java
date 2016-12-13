 package com.jqg.service.impl;
 
 import com.jqg.dao.MessagemodelDao;
import com.jqg.dao.impl.MessagemodelDaoImpl;
import com.jqg.pojo.Messagemodel;
import com.jqg.service.MessagemodelService;

import java.util.List;
 
 public class MessagemodelServiceImpl
   implements MessagemodelService
 {
   MessagemodelDao messagemodelDao = new MessagemodelDaoImpl();
 
   public boolean createMessagemodel(Messagemodel messagemodel) throws Exception
   {
     return this.messagemodelDao.addMessagemodel(messagemodel);
   }
 
   public boolean updateMessagemodel(Messagemodel messagemodel)
     throws Exception
   {
     return this.messagemodelDao.updateMessagemodel(messagemodel);
   }
 
   public boolean deleteMessagemodel(Messagemodel messagemodel)
     throws Exception
   {
     return this.messagemodelDao.deleteMessagemodel(messagemodel);
   }
 
   public List<Messagemodel> findMessagemodels()
     throws Exception
   {
     return this.messagemodelDao.findMessagemodels();
   }
 
   public Messagemodel findMessagemodelByintegralPid(int messModelId)
     throws Exception
   {
     return this.messagemodelDao.findMessagemodelByintegralPid(messModelId);
   }
 }

