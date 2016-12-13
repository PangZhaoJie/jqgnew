 package com.jqg.service.impl;
 
 import com.jqg.dao.UservipnoteDao;
import com.jqg.dao.impl.UservipnoteDaoImpl;
import com.jqg.pojo.Uservipnote;
import com.jqg.service.UservipnoteService;

import java.util.List;
 
 public class UservipnoteServiceImpl
   implements UservipnoteService
 {
   UservipnoteDao uservipnoteDao = new UservipnoteDaoImpl();
 
   public boolean updateUservipnote(Uservipnote uservipnote)
     throws Exception
   {
     return this.uservipnoteDao.updateUservipnote(uservipnote);
   }
 
   public boolean deleteUservipnote(Uservipnote uservipnote)
     throws Exception
   {
     return this.uservipnoteDao.deleteUservipnote(uservipnote);
   }
 
   public List<Uservipnote> findUservipnotes()
     throws Exception
   {
     return this.uservipnoteDao.findUservipnotes();
   }
 
   public Uservipnote findUservipByUserVipNoteId(int userVipNoteId)
     throws Exception
   {
     return this.uservipnoteDao.findUservipByUserVipNoteId(userVipNoteId);
   }
 
   public boolean addUservipnote(Uservipnote uservipnote)
     throws Exception
   {
     return this.uservipnoteDao.addUservipnote(uservipnote);
   }
 }

