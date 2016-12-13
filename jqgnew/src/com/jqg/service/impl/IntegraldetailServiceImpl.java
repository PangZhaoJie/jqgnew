 package com.jqg.service.impl;
 
 import com.jqg.dao.IntegraldetailDao;
import com.jqg.dao.impl.IntegraldetailDaoImpl;
import com.jqg.pojo.Integraldetail;
import com.jqg.service.IntegraldetailService;

import java.util.List;
 
 public class IntegraldetailServiceImpl
   implements IntegraldetailService
 {
   IntegraldetailDao integraldetailDao = new IntegraldetailDaoImpl();
 
   public boolean createIntegraldetail(Integraldetail integraldetail)
     throws Exception
   {
     return this.integraldetailDao.addIntegraldetail(integraldetail);
   }
 
   public boolean updateIntegraldetail(Integraldetail integraldetail)
     throws Exception
   {
     return this.integraldetailDao.updateIntegraldetail(integraldetail);
   }
 
   public boolean deleteIntegraldetail(Integraldetail integraldetail)
     throws Exception
   {
     return this.integraldetailDao.deleteIntegraldetail(integraldetail);
   }
 
   public List<Integraldetail> findIntegraldetails()
     throws Exception
   {
     return this.integraldetailDao.findIntegraldetails();
   }
 
   public Integraldetail findIntegraldetailByintegralId(int integralId)
     throws Exception
   {
     return this.integraldetailDao.findIntegraldetailByintegralId(integralId);
   }
 
   public List<Integraldetail> findIntegraldetailsByUserId(int userId)
     throws Exception
   {
     return this.integraldetailDao.findIntegraldetailsByUserId(userId);
   }
 
   public List<Integraldetail> findIntegraldetailsByUserIdPage(String sql)
     throws Exception
   {
     return this.integraldetailDao.findIntegraldetailsByUserIdPage(sql);
   }
 }

