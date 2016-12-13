package com.jqg.service;

import com.jqg.pojo.Partner;

import java.util.List;

public abstract interface PartnerService
{
  public abstract boolean createPartner(Partner paramPartner)
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
