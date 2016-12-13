 package com.jqg.service.impl;
 
 import com.jqg.dao.AsktypeDao;
import com.jqg.dao.impl.AsktypeDaoImpl;
import com.jqg.pojo.Asktype;
import com.jqg.service.AsktypeService;

import java.util.List;
 
 public class AsktypeServiceImpl
   implements AsktypeService
 {
   AsktypeDao asktypeDao = new AsktypeDaoImpl();
 
   public boolean addAsktype(Asktype asktype)
     throws Exception
   {
     return this.asktypeDao.addAsktype(asktype);
   }
 
   public boolean updateAsktype(Asktype Asktype)
     throws Exception
   {
     return this.asktypeDao.updateAsktype(Asktype);
   }
 
   public boolean deleteAsktype(Asktype Asktype)
     throws Exception
   {
     return this.asktypeDao.deleteAsktype(Asktype);
   }
 
   public List<Asktype> findAsktypes()
     throws Exception
   {
     return this.asktypeDao.findAsktypes();
   }
 
   public Asktype findAsktypeById(Integer askTypeId) throws Exception
   {
     return this.asktypeDao.findAsktypeById(askTypeId);
   }
 }

