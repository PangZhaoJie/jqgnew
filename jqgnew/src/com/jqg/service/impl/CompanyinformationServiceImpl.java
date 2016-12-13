 package com.jqg.service.impl;
 
 import com.jqg.dao.CompanyinformationDao;
import com.jqg.dao.impl.CompanyinformationDaoImpl;
import com.jqg.pojo.Companyinformation;
import com.jqg.service.CompanyinformationService;

import java.util.List;
 
 public class CompanyinformationServiceImpl
   implements CompanyinformationService
 {
   CompanyinformationDao companyinformationDao = new CompanyinformationDaoImpl();
 
   public boolean createCompanyinformation(Companyinformation companyinformation)
     throws Exception
   {
     return this.companyinformationDao.addCompanyinformation(companyinformation);
   }
 
   public boolean updateCompanyinformation(Companyinformation companyinformation)
     throws Exception
   {
     return this.companyinformationDao.updateCompanyinformation(companyinformation);
   }
 
   public boolean deleteCompanyinformation(Companyinformation companyinformation)
     throws Exception
   {
     return this.companyinformationDao.deleteCompanyinformation(companyinformation);
   }
 
   public List<Companyinformation> findCompanyinformations()
     throws Exception
   {
     return findCompanyinformations();
   }
 
   public Companyinformation findCompanyinformationBycompanyId(int companyId)
     throws Exception
   {
     return findCompanyinformationBycompanyId(companyId);
   }
 }

