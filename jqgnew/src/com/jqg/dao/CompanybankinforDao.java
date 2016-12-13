package com.jqg.dao;

import com.jqg.pojo.Companybankinfor;

import java.util.List;

public abstract interface CompanybankinforDao
{
  public abstract boolean addCompanybankinfor(Companybankinfor paramCompanybankinfor)
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

