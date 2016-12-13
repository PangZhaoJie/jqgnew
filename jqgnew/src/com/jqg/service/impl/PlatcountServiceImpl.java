 package com.jqg.service.impl;
 
 import com.jqg.dao.PlatcountDao;
import com.jqg.dao.impl.PlatcountDaoImpl;
import com.jqg.pojo.Platcount;
import com.jqg.service.PlatcountService;

import java.util.List;
 
 public class PlatcountServiceImpl
   implements PlatcountService
 {
   PlatcountDao platcountDao = new PlatcountDaoImpl();
 
   public boolean createPlatcount(Platcount platcount)
     throws Exception
   {
     return this.platcountDao.addPlatcount(platcount);
   }
 
   public boolean updatePlatcount(Platcount platcount)
     throws Exception
   {
     return this.platcountDao.updatePlatcount(platcount);
   }
 
   public boolean deletePlatcount(Platcount platcount)
     throws Exception
   {
     return this.platcountDao.deletePlatcount(platcount);
   }
 
   public List<Platcount> findPlatcounts()
     throws Exception
   {
     return this.platcountDao.findPlatcounts();
   }
 
   public Platcount findPlatcountById(int countid)
     throws Exception
   {
     return this.platcountDao.findPlatcountById(countid);
   }
 
   public List<Platcount> findPlatcountBySql(String sql)
     throws Exception
   {
     return this.platcountDao.findPlatcountBySql(sql);
   }
 }

