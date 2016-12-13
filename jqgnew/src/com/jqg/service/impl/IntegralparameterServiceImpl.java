 package com.jqg.service.impl;
 
 import com.jqg.dao.IntegralparameterDao;
import com.jqg.dao.impl.IntegralparameterDaoImpl;
import com.jqg.pojo.Integralparameter;
import com.jqg.service.IntegralparameterService;

import java.util.List;
 
 public class IntegralparameterServiceImpl
   implements IntegralparameterService
 {
   IntegralparameterDao integralparameterDao = new IntegralparameterDaoImpl();
 
   public boolean createIntegralparameter(Integralparameter integralparameter)
     throws Exception
   {
     return this.integralparameterDao.addIntegralparameter(integralparameter);
   }
 
   public boolean updateIntegralparameter(Integralparameter integralparameter)
     throws Exception
   {
     return this.integralparameterDao.updateIntegralparameter(integralparameter);
   }
 
   public boolean deleteIntegralparameter(Integralparameter integralparameter)
     throws Exception
   {
     return this.integralparameterDao.deleteIntegralparameter(integralparameter);
   }
 
   public List<Integralparameter> findIntegralparameters()
     throws Exception
   {
     return this.integralparameterDao.findIntegralparameters();
   }
 
   public Integralparameter findIntegralparameterByintegralPid(int integralPid)
     throws Exception
   {
     return this.integralparameterDao.findIntegralparameterByintegralPid(integralPid);
   }
 
   public Integralparameter findIntegralparameterByIntegralPname(String integralPname)
     throws Exception
   {
     return this.integralparameterDao.findIntegralparameterByIntegralPname(integralPname);
   }
 }

