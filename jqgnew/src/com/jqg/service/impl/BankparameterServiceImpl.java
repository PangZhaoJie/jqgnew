 package com.jqg.service.impl;
 
 import com.jqg.dao.BankparameterDao;
import com.jqg.dao.impl.BankparameterDaoImpl;
import com.jqg.pojo.Bankparameter;
import com.jqg.service.BankparameterService;

import java.util.List;
 
 public class BankparameterServiceImpl
   implements BankparameterService
 {
   BankparameterDao bankparameterDao = new BankparameterDaoImpl();
 
   public boolean createBankparameter(Bankparameter bankparameter)
     throws Exception
   {
     return this.bankparameterDao.addBankparameter(bankparameter);
   }
 
   public boolean updateBankparameter(Bankparameter bankparameter)
     throws Exception
   {
     return this.bankparameterDao.updateBankparameter(bankparameter);
   }
 
   public boolean deleteBankparameter(Bankparameter bankparameter)
     throws Exception
   {
     return this.bankparameterDao.deleteBankparameter(bankparameter);
   }
 
   public List<Bankparameter> findBankparameters()
     throws Exception
   {
     return this.bankparameterDao.findBankparameters();
   }
 
   public Bankparameter findBankparameterBybankPid(int bankPid)
     throws Exception
   {
     return this.bankparameterDao.findBankparameterBybankPid(bankPid);
   }
 }

