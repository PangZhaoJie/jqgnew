 package com.jqg.service.impl;
 
 import com.jqg.dao.CreditlevelDao;
import com.jqg.dao.impl.CreditlevelDaoImpl;
import com.jqg.pojo.Creditlevel;
import com.jqg.service.CreditlevelService;

import java.util.List;
 
 public class CreditlevelServiceImpl
   implements CreditlevelService
 {
   CreditlevelDao creditlevelDao = new CreditlevelDaoImpl();
 
   public boolean createCreditlevel(Creditlevel creditlevel) throws Exception
   {
     return this.creditlevelDao.addCreditlevel(creditlevel);
   }
 
   public boolean updateCreditlevel(Creditlevel creditlevel)
     throws Exception
   {
     return this.creditlevelDao.updateCreditlevel(creditlevel);
   }
 
   public boolean deleteCreditlevel(Creditlevel creditlevel)
     throws Exception
   {
     return this.creditlevelDao.deleteCreditlevel(creditlevel);
   }
 
   public List<Creditlevel> findCreditlevels()
     throws Exception
   {
     return this.creditlevelDao.findCreditlevels();
   }
 
   public Creditlevel findCreditlevelBycreditLevelId(int creditLevelId)
     throws Exception
   {
     return this.creditlevelDao.findCreditlevelBycreditLevelId(creditLevelId);
   }
 }

