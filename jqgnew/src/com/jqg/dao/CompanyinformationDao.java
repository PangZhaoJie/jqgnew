package com.jqg.dao;

import com.jqg.pojo.Companyinformation;

import java.util.List;

public abstract interface CompanyinformationDao
{
  public abstract boolean addCompanyinformation(Companyinformation paramCompanyinformation)
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

