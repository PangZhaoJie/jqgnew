 package com.jqg.service.impl;
 
 import com.jqg.dao.TenderwordsDao;
import com.jqg.dao.impl.TenderwordsDaoImpl;
import com.jqg.pojo.Tenderwords;
import com.jqg.service.TenderwordsService;

import java.util.List;
 
 public class TenderwordsServiceImpl
   implements TenderwordsService
 {
   TenderwordsDao tenderwordsDao = new TenderwordsDaoImpl();
 
   public boolean addTenderwords(Tenderwords tenderwords) throws Exception
   {
     return this.tenderwordsDao.addTenderwords(tenderwords);
   }
 
   public boolean updateTenderwords(Tenderwords tenderwords)
     throws Exception
   {
     return this.tenderwordsDao.updateTenderwords(tenderwords);
   }
 
   public boolean deleteTenderwords(Tenderwords tenderwords)
     throws Exception
   {
     return this.tenderwordsDao.deleteTenderwords(tenderwords);
   }
 
   public List<Tenderwords> findTenderwordss()
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordss();
   }
 
   public Tenderwords findTenderwordsById(Integer tenderWordsId)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsById(tenderWordsId);
   }
 
   public List<Tenderwords> findTenderwordsByState(int lssuingId)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsByState(lssuingId);
   }
 
   public List<Tenderwords> findTenderwordsByStatePage(int lssuingId, int currentPage, int pageSize)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsByStatePage(lssuingId, currentPage, pageSize);
   }
 
   public List<Tenderwords> findTenderwordsByUserId(int userId)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsByUserId(userId);
   }
 
   public List<Tenderwords> findTenderwordsByUserIdPage(String sql)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsByUserIdPage(sql);
   }
 
   public List<Tenderwords> findTenderwordsByLssuingId(int lssuingId)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsByLssuingId(lssuingId);
   }
 
   public List<Tenderwords> findTenderwordsByPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     return this.tenderwordsDao.findTenderwordsByPage(userId, currentPage, pageSize);
   }
 }

