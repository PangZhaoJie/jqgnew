 package com.jqg.service.impl;
 
 import com.jqg.dao.MatercertifiDao;
import com.jqg.dao.impl.MatercertifiDaoIpml;
import com.jqg.pojo.Matercertifi;
import com.jqg.service.MatercertifiService;

import java.util.List;
 
 public class MatercertifiServiceImpl
   implements MatercertifiService
 {
   MatercertifiDao matercertifiDao = new MatercertifiDaoIpml();
 
   public boolean createMatercertifi(Matercertifi matercertifi) throws Exception
   {
     return this.matercertifiDao.addMatercertifi(matercertifi);
   }
 
   public boolean updateMatercertifi(Matercertifi matercertifi)
     throws Exception
   {
     return this.matercertifiDao.updateMatercertifi(matercertifi);
   }
 
   public boolean deleteMatercertifi(Matercertifi matercertifi)
     throws Exception
   {
     return this.matercertifiDao.deleteMatercertifi(matercertifi);
   }
 
   public List<Matercertifi> findMatercertifis()
     throws Exception
   {
     return this.matercertifiDao.findMatercertifis();
   }
 
   public Matercertifi findMatercertifiByMaterCertifiId(int matercertifiId)
     throws Exception
   {
     return this.matercertifiDao.findMatercertifiByMaterCertifiId(matercertifiId);
   }
 
   public Matercertifi findMatercertifiByUserId(int userId)
     throws Exception
   {
     return this.matercertifiDao.findMatercertifiByUserId(userId);
   }
 }

