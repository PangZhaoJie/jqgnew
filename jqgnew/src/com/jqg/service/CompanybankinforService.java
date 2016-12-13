package com.jqg.service;

import com.jqg.pojo.Companybankinfor;

import java.util.List;

public abstract interface CompanybankinforService
{
  public abstract boolean createCompanybankinfor(Companybankinfor paramCompanybankinfor)
    throws Exception;

  public abstract boolean updateCompanybankinfor(Companybankinfor paramCompanybankinfor)
    throws Exception;

  public abstract boolean deleteCompanybankinfor(Companybankinfor paramCompanybankinfor)
    throws Exception;

  public abstract List<Companybankinfor> findCompanybankinfors()
    throws Exception;

  public abstract Companybankinfor findCompanybankinforById(int paramInt)
    throws Exception;
}
