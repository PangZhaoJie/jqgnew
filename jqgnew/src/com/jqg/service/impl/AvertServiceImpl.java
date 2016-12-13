 package com.jqg.service.impl;
 
 import com.jqg.dao.AvertDao;
import com.jqg.dao.impl.AvertDaoImpl;
import com.jqg.pojo.Avert;
import com.jqg.service.AvertService;

import java.util.List;
 
 public class AvertServiceImpl
   implements AvertService
 {
   AvertDao avertDao = new AvertDaoImpl();
 
   public boolean createAvert(Avert avert) throws Exception
   {
     return this.avertDao.addAvert(avert);
   }
 
   public boolean updateAvert(Avert avert)
     throws Exception
   {
     return this.avertDao.updateAvert(avert);
   }
 
   public boolean deleteAvert(Avert avert)
     throws Exception
   {
     return this.avertDao.deleteAvert(avert);
   }
 
   public List<Avert> findAverts()
     throws Exception
   {
     return this.avertDao.findAverts();
   }
 
   public Avert findAvertByavertId(int avertId)
     throws Exception
   {
     return this.avertDao.findAvertByavertId(avertId);
   }
 }

