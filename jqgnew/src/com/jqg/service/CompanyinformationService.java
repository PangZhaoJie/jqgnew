package com.jqg.service;

import com.jqg.pojo.Companyinformation;

import java.util.List;

public abstract interface CompanyinformationService
{
  public abstract boolean createCompanyinformation(Companyinformation paramCompanyinformation)
    throws Exception;

  public abstract boolean updateCompanyinformation(Companyinformation paramCompanyinformation)
    throws Exception;

  public abstract boolean deleteCompanyinformation(Companyinformation paramCompanyinformation)
    throws Exception;

  public abstract List<Companyinformation> findCompanyinformations()
    throws Exception;

  public abstract Companyinformation findCompanyinformationBycompanyId(int paramInt)
    throws Exception;
}
