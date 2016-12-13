 package com.jqg.service.impl;
 
 import java.util.List;

import com.jqg.dao.SystemconfDao;
import com.jqg.dao.impl.SystemconfDaoImpl;
import com.jqg.pojo.Systemconf;
import com.jqg.service.SystemconfService;
 
 public class SystemconfServiceImpl
   implements SystemconfService
 {
   SystemconfDao systemconfDao = new SystemconfDaoImpl();
 
   public boolean createSystemconf(Systemconf systemconf)
     throws Exception
   {
     return this.systemconfDao.addSystemconf(systemconf);
   }
 
   public boolean updateSystemconf(Systemconf systemconf)
     throws Exception
   {
     return this.systemconfDao.updateSystemconf(systemconf);
   }
 
   public boolean deleteSystemconf(Systemconf systemconf)
     throws Exception
   {
     return this.systemconfDao.deleteSystemconf(systemconf);
   }
 
   public List<Systemconf> findSystemconfs()
     throws Exception
   {
     return this.systemconfDao.findSystemconfs();
   }
 
   public Systemconf findSystemconfBySysId(int sysId)
     throws Exception
   {
     return this.systemconfDao.findSystemconfBySysId(sysId);
   }
 
   public Systemconf findSystemconfByParname(String sysname)
     throws Exception
   {
     return this.systemconfDao.findSystemconfByParname(sysname);
   }
 }

