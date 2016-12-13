 package com.jqg.service.impl;
 
 import com.jqg.dao.AutomaticbidDao;
import com.jqg.dao.impl.AutomaticbidDaoImpl;
import com.jqg.pojo.Automaticbid;
import com.jqg.service.AutomaticbidService;

import java.util.List;
 
 public class AutomaticbidServiceImpl
   implements AutomaticbidService
 {
   AutomaticbidDao automaticbidDao = new AutomaticbidDaoImpl();
 
   public boolean createAutomaticbid(Automaticbid automaticbid)
     throws Exception
   {
     return this.automaticbidDao.addAutomaticbid(automaticbid);
   }
 
   public boolean updateAutomaticbid(Automaticbid automaticbid)
     throws Exception
   {
     return this.automaticbidDao.updateAutomaticbid(automaticbid);
   }
 
   public boolean deleteAutomaticbid(Automaticbid automaticbid)
     throws Exception
   {
     return this.automaticbidDao.deleteAutomaticbid(automaticbid);
   }
 
   public List<Automaticbid> findAutomaticbids()
     throws Exception
   {
     return this.automaticbidDao.findAutomaticbids();
   }
 
   public Automaticbid findAutomaticbidByautomaticbidId(int automaticbidId)
     throws Exception
   {
     return this.automaticbidDao.findAutomaticbidByautomaticbidId(automaticbidId);
   }
 
   public int queryCountAutomaticbid() throws Exception
   {
     return this.automaticbidDao.queryCountAutomaticbid();
   }
 
   public List<Automaticbid> findAutomaticbidBySql(String sql)
     throws Exception
   {
     return this.automaticbidDao.findAutomaticbidBySql(sql);
   }
 }

