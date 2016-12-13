 package com.jqg.service.impl;
 
 import com.jqg.dao.LssuingheraldDao;
import com.jqg.dao.impl.LssuingheraldDaoImpl;
import com.jqg.pojo.Lssuingherald;
import com.jqg.service.LssuingheraldService;

import java.util.List;
 
 public class LssuingheraldServiceImpl
   implements LssuingheraldService
 {
   LssuingheraldDao lssuingheraldDao = new LssuingheraldDaoImpl();
 
   public boolean createLssuingherald(Lssuingherald lssuingherald)
     throws Exception
   {
     return this.lssuingheraldDao.addLssuingherald(lssuingherald);
   }
 
   public boolean updateLssuingherald(Lssuingherald lssuingherald)
     throws Exception
   {
     return this.lssuingheraldDao.updateLssuingherald(lssuingherald);
   }
 
   public boolean deleteLssuingherald(Lssuingherald lssuingherald)
     throws Exception
   {
     return this.lssuingheraldDao.deleteLssuingherald(lssuingherald);
   }
 
   public List<Lssuingherald> findLssuingheralds()
     throws Exception
   {
     return this.lssuingheraldDao.findLssuingheralds();
   }
 
   public Lssuingherald findLssuingheraldByLssuingheraldId(int lssuingHeraldId)
     throws Exception
   {
     return this.lssuingheraldDao.findLssuingheraldByLssuingheraldId(lssuingHeraldId);
   }
 
   public Lssuingherald findLssuingheraldByindex()
     throws Exception
   {
     return this.lssuingheraldDao.findLssuingheraldByindex();
   }
 }

