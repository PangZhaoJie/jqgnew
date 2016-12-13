 package com.jqg.service.impl;
 
 import com.jqg.dao.OfflinerechargeDao;
import com.jqg.dao.impl.OfflinerechargeDaoImpl;
import com.jqg.pojo.Offlinerecharge;
import com.jqg.service.OfflinerechargeService;

import java.util.List;
 
 public class OfflinerechargeServiceImpl
   implements OfflinerechargeService
 {
   OfflinerechargeDao offlinerechargeDao = new OfflinerechargeDaoImpl();
 
   public boolean createOfflinerecharge(Offlinerecharge offlinerecharge)
     throws Exception
   {
     return this.offlinerechargeDao.addOfflinerecharge(offlinerecharge);
   }
 
   public boolean updateOfflinerecharge(Offlinerecharge offlinerecharge)
     throws Exception
   {
     return this.offlinerechargeDao.updateOfflinerecharge(offlinerecharge);
   }
 
   public boolean deleteOfflinerecharge(Offlinerecharge offlinerecharge)
     throws Exception
   {
     return this.offlinerechargeDao.deleteOfflinerecharge(offlinerecharge);
   }
 
   public List<Offlinerecharge> findOfflinerecharges()
     throws Exception
   {
     return this.offlinerechargeDao.findOfflinerecharges();
   }
 
   public Offlinerecharge findOfflinerechargeById(int offlineRechargeId)
     throws Exception
   {
     return this.offlinerechargeDao.findOfflinerechargeById(offlineRechargeId);
   }
 
   public List<Offlinerecharge> findOfflinerechargeBySql(String sql)
     throws Exception
   {
     return this.offlinerechargeDao.findOfflinerechargeBySql(sql);
   }

   
	public List findListbysqsl(String sql)  throws Exception{
	// TODO Auto-generated method stub
	return this.offlinerechargeDao.findListbysqsl(sql);
}
 }

