 package com.jqg.service.impl;
 
 import com.jqg.dao.LatestnewsDao;
import com.jqg.dao.impl.LatestnewsDaoImpl;
import com.jqg.pojo.Latestnews;
import com.jqg.service.LatestnewsService;

import java.util.List;
 
 public class LatestnewsServiceImpl
   implements LatestnewsService
 {
   LatestnewsDao latestnewsDao = new LatestnewsDaoImpl();
 
   public boolean addLatestnews(Latestnews latestnews) throws Exception
   {
     return this.latestnewsDao.addLatestnews(latestnews);
   }
 
   public boolean updateLatestnews(Latestnews latestnews)
     throws Exception
   {
     return this.latestnewsDao.updateLatestnews(latestnews);
   }
 
   public boolean deleteLatestnews(Latestnews latestnews)
     throws Exception
   {
     return this.latestnewsDao.deleteLatestnews(latestnews);
   }
 
   public List<Latestnews> findLatestnewss(String sql)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewss(sql);
   }
 
   public Latestnews findLatestnewsByLatestnewsId(Integer newsId)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewsByLatestnewsId(newsId);
   }
 
   public List<Latestnews> findLatestnewssBypicture()
     throws Exception
   {
     return this.latestnewsDao.findLatestnewssBypicture();
   }
 
   public List<Latestnews> findLatestnewssByfrontMenuId(int frontMenuId)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewssByfrontMenuId(frontMenuId);
   }
 
   public List<Latestnews> findLatestnewssByfrontMenuIdindex(int frontMenuId)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewssByfrontMenuIdindex(frontMenuId);
   }
 
   public List<Latestnews> findLatestnewssByfrontMenuIdPage(int frontMenuId, int currentPage, int pageSize)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewssByfrontMenuIdPage(frontMenuId, currentPage, pageSize);
   }
 
   public Latestnews findLatestnewsByonPage(Integer newsId, int frontmenuId)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewsByonPage(newsId, frontmenuId);
   }
 
   public Latestnews findLatestnewsBydownPage(Integer newsId, int frontmenuId)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewsBydownPage(newsId, frontmenuId);
   }
 
   public List<Latestnews> findLatestnewsspage(int currentPage, int pageSize)
     throws Exception
   {
     return this.latestnewsDao.findLatestnewsspage(currentPage, pageSize);
   }
 }

