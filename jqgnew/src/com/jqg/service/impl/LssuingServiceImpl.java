 package com.jqg.service.impl;
 
 import com.jqg.dao.LssuingDao;
import com.jqg.dao.impl.LssuingDaoImpl;
import com.jqg.pojo.Lssuing;
import com.jqg.pojo.LssuingComp;
import com.jqg.service.LssuingService;

import java.util.List;
 
 public class LssuingServiceImpl
   implements LssuingService
 {
   LssuingDao lssuingDao = new LssuingDaoImpl();
 
   public boolean addLssuing(Lssuing lssuing) throws Exception
   {
     return this.lssuingDao.addLssuing(lssuing);
   }
 
   public boolean updateLssuing(Lssuing lssuing)
     throws Exception
   {
     return this.lssuingDao.updateLssuing(lssuing);
   }
 
   public boolean deleteLssuing(Lssuing lssuing)
     throws Exception
   {
     return this.lssuingDao.deleteLssuing(lssuing);
   }
 
   public List<Lssuing> findLssuings()
     throws Exception
   {
     return this.lssuingDao.findLssuings();
   }
 
   public Lssuing findLssuingById(Integer lssuingId)
     throws Exception
   {
     return this.lssuingDao.findLssuingById(lssuingId);
   }
 
   public List<Lssuing> findLssuingsIndex()
     throws Exception
   {
     return this.lssuingDao.findLssuingsIndex();
   }
 
   public List<Lssuing> findLssuingsBySearch(String sql)
     throws Exception
   {
     return this.lssuingDao.findLssuingsBySearch(sql);
   }
   public List<Lssuing> findLssuingsIndex(String sql,int cunt)
     throws Exception
   {
     return this.lssuingDao.findLssuingsIndex(sql,cunt);
   }
   public List<Lssuing> findLssuingsByUserId(int userId)
     throws Exception
   {
     return this.lssuingDao.findLssuingsByUserId(userId);
   }
 
   public List<Lssuing> findLssuingsByStatePage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     return this.lssuingDao.findLssuingsByStatePage(userId, currentPage, pageSize);
   }
 
   public List<Lssuing> findLssuingsByUserIdAndState(int userId)
     throws Exception
   {
     return this.lssuingDao.findLssuingsByUserIdAndState(userId);
   }
 
   public List<Lssuing> findLssuingsByUserIdAndByState(int userId, int state)
     throws Exception
   {
     return this.lssuingDao.findLssuingsByUserIdAndByState(userId, state);
   }
   /**
    * 修改借款方资料
    */
   public boolean updateLssuingComp(LssuingComp lssuingComp)
		     throws Exception
		   {
		     return this.lssuingDao.updateLssuingComp(lssuingComp);
		   }
   /**
    * 根据借款方id查找借款方信息
    */

public LssuingComp findLssuingCompById(Integer compId) throws Exception {
	 return this.lssuingDao.findLssuingCompById(compId);
}
 }

