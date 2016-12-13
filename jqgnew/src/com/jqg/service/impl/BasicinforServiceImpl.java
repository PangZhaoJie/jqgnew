 package com.jqg.service.impl;
 
 import com.jqg.dao.BasicinforDao;
import com.jqg.dao.impl.BasicinforDaoIpml;
import com.jqg.pojo.Basicinfor;
import com.jqg.service.BasicinforService;

import java.util.List;
 
 public class BasicinforServiceImpl
   implements BasicinforService
 {
   BasicinforDao basicinforDao = new BasicinforDaoIpml();
 
   public Integer createBasicinfor(Basicinfor basicinfor) throws Exception
   {
     return this.basicinforDao.addBasicinfor(basicinfor);
   }
 
   public boolean updateBasicinfor(Basicinfor basicinfor)
     throws Exception
   {
     return this.basicinforDao.updateBasicinfor(basicinfor);
   }
 
   public boolean deleteBasicinfor(Basicinfor basicinfor)
     throws Exception
   {
     return this.basicinforDao.deleteBasicinfor(basicinfor);
   }
 
   public List<Basicinfor> findBasicinfors()
     throws Exception
   {
     return this.basicinforDao.findBasicinfors();
   }
 
   public Basicinfor findBasicinforByBasicInforId(int basicInforId)
     throws Exception
   {
     return this.basicinforDao.findBasicinforByBasicInforId(basicInforId);
   }
 
   public Basicinfor findBasicinforByUserId(int userId)
     throws Exception
   {
     return this.basicinforDao.findBasicinforByUserId(userId);
   }

public Basicinfor findBasicinforByPhone(String phone) throws Exception {
	return this.basicinforDao.findBasicinforByPhone(phone);
}

public Basicinfor findBasicinforByIdNum(String idNum) throws Exception {
	return this.basicinforDao.findBasicinforByIdNum(idNum);
}
 }

