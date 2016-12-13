 package com.jqg.service.impl;
 
 import com.jqg.dao.TranslateDao;
import com.jqg.dao.impl.TranslateDaoImpl;
import com.jqg.pojo.Translate;
import com.jqg.service.TranslateService;

import java.util.List;
 
 public class TranslateServiceImpl
   implements TranslateService
 {
   private TranslateDao translateDao = new TranslateDaoImpl();
 
   public boolean addTranslate(Translate translate) throws Exception
   {
     return this.translateDao.addTranslate(translate);
   }
 
   public boolean updateTranslate(Translate translate)
     throws Exception
   {
     return this.translateDao.updateTranslate(translate);
   }
 
   public boolean deleteTranslate(Translate translate)
     throws Exception
   {
     return this.translateDao.deleteTranslate(translate);
   }
 
   public List<Translate> findTranslates()
     throws Exception
   {
     return this.translateDao.findTranslates();
   }
 
   public Translate findTranslateById(Integer translateId)
     throws Exception
   {
     return this.translateDao.findTranslateById(translateId);
   }
 
   public List<Translate> findTranslatesByuser(int userId)
     throws Exception
   {
     return this.translateDao.findTranslatesByuser(userId);
   }
 
   public List<Translate> findTranslatesByuserPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     return this.translateDao.findTranslatesByuserPage(userId, currentPage, pageSize);
   }
 
   public List<Translate> findTranslateBySql(String sql)
     throws Exception
   {
     return this.translateDao.findTranslateBySql(sql);
   }
 }

