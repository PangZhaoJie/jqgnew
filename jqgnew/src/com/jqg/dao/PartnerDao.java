package com.jqg.dao;

import com.jqg.pojo.Partner;

import java.util.List;

public abstract interface PartnerDao
{
  public abstract boolean addPartner(Partner paramPartner)
    throws Exception;

  public abstract boolean updatePartner(Partner paramPartner)
    throws Exception;

  public abstract boolean deletePartner(Partner paramPartner)
    throws Exception;

  public abstract List<Partner> findPartners()
    throws Exception;

  public abstract Partner findPartnerByPartnerId(int paramInt)
    throws Exception;
}
