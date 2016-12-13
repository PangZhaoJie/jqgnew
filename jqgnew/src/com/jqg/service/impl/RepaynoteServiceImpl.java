 package com.jqg.service.impl;
 
 import com.jqg.dao.RepaynoteDao;
import com.jqg.dao.impl.RepaynoteDaoImpl;
import com.jqg.pojo.Repaynote;
import com.jqg.service.RepaynoteService;

import java.util.List;
 
 public class RepaynoteServiceImpl
   implements RepaynoteService
 {
   RepaynoteDao repaynoteDao = new RepaynoteDaoImpl();
 
   public boolean addRepaynote(Repaynote repaynote) throws Exception
   {
     return this.repaynoteDao.addRepaynote(repaynote);
   }
 
   public boolean updateRepaynote(Repaynote repaynote)
     throws Exception
   {
     return this.repaynoteDao.updateRepaynote(repaynote);
   }
 
   public boolean deleteRepaynote(Repaynote repaynote)
     throws Exception
   {
     return this.repaynoteDao.deleteRepaynote(repaynote);
   }
 
   public List<Repaynote> findRepaynotes()
     throws Exception
   {
     return this.repaynoteDao.findRepaynotes();
   }
 
   public Repaynote findRepaynoteByRepaynoteId(int repaynoteId)
     throws Exception
   {
     return this.repaynoteDao.findRepaynoteByRepaynoteId(repaynoteId);
   }
 
   public List<Repaynote> findRepaynoteBylssuing(int lssuingId)
     throws Exception
   {
     return this.repaynoteDao.findRepaynoteBylssuing(lssuingId);
   }
 
   public List<Repaynote> findRepaynoteByUserId(int userId)
     throws Exception
   {
     return this.repaynoteDao.findRepaynoteByUserId(userId);
   }
 
   public List<Repaynote> findRepaynoteByUserIdAndTime(int userId)
     throws Exception
   {
     return this.repaynoteDao.findRepaynoteByUserIdAndTime(userId);
   }
 
   public List<Repaynote> findRepayBySql(String sql)
     throws Exception
   {
     return this.repaynoteDao.findRepayBySql(sql);
   }

public List<Repaynote> findComRepay(String starttime, String endtime)
		throws Exception {
	// TODO Auto-generated method stub
	return this.repaynoteDao.findComRepay(starttime, endtime);
}
 }

