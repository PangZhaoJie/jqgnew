 package com.jqg.service.impl;
 
 import com.jqg.dao.CompanybankinforDao;
import com.jqg.dao.impl.CompanybankinforDaoIpml;
import com.jqg.pojo.Companybankinfor;
import com.jqg.service.CompanybankinforService;

import java.util.List;
 
 public class CompanybankinforServiceImpl
   implements CompanybankinforService
 {
   CompanybankinforDao companybankinforDao = new CompanybankinforDaoIpml();
 
   public boolean createCompanybankinfor(Companybankinfor companybankinfor) throws Exception
   {
     return this.companybankinforDao.addCompanybankinfor(companybankinfor);
   }
 
   public boolean updateCompanybankinfor(Companybankinfor companybankinfor)
     throws Exception
   {
     return this.companybankinforDao.updateCompanybankinfor(companybankinfor);
   }
 
   public boolean deleteCompanybankinfor(Companybankinfor companybankinfor)
     throws Exception
   {
     return this.companybankinforDao.deleteCompanybankinfor(companybankinfor);
   }
 
   public List<Companybankinfor> findCompanybankinfors()
     throws Exception
   {
     return this.companybankinforDao.findCompanybankinfors();
   }
 
   public Companybankinfor findCompanybankinforById(int companyBankInforId)
     throws Exception
   {
     return this.companybankinforDao.findCompanybankinforById(companyBankInforId);
   }
 }

