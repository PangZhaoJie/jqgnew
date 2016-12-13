 package com.jqg.service.impl;
 
 import com.jqg.dao.LssingphotoDao;
import com.jqg.dao.impl.LssingphotoDaoImpl;
import com.jqg.pojo.Lssingphoto;
import com.jqg.service.LssingphotoService;

import java.util.List;
 
 public class LssingphotoServiceImpl
   implements LssingphotoService
 {
   LssingphotoDao lssingphotoDao = new LssingphotoDaoImpl();
 
   public boolean addLssingphoto(Lssingphoto lssingphoto) throws Exception
   {
     return this.lssingphotoDao.addLssingphoto(lssingphoto);
   }
 
   public boolean updateLssingphoto(Lssingphoto lssingphoto)
     throws Exception
   {
     return this.lssingphotoDao.updateLssingphoto(lssingphoto);
   }
 
   public boolean deleteLssingphoto(Lssingphoto lssingphoto)
     throws Exception
   {
     return this.lssingphotoDao.deleteLssingphoto(lssingphoto);
   }
 
   public List<Lssingphoto> findLssingphotos()
     throws Exception
   {
     return this.lssingphotoDao.findLssingphotos();
   }
 
   public Lssingphoto findLssingphotoById(Integer photoId)
     throws Exception
   {
     return this.lssingphotoDao.findLssingphotoById(photoId);
   }
 
   public List<Lssingphoto> findLssingphotosBylssuing(int lssuingId)
     throws Exception
   {
     return this.lssingphotoDao.findLssingphotosBylssuing(lssuingId);
   }
 
   public List<Lssingphoto> findLssingphotosBylssuingPage(int lssuingId, int currentPage, int pageSize)
     throws Exception
   {
     return this.lssingphotoDao.findLssingphotosBylssuingPage(lssuingId, currentPage, pageSize);
   }
 }

