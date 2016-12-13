package com.jqg.service;

import com.jqg.pojo.Partnerdetail;

import java.util.List;

public abstract interface PartnerdetailService
{
  public abstract boolean createPartnerdetail(Partnerdetail paramPartnerdetail)
    throws Exception;

  public abstract boolean updatePartnerdetail(Partnerdetail paramPartnerdetail)
    throws Exception;

  public abstract boolean deletePartnerdetail(Partnerdetail paramPartnerdetail)
    throws Exception;

  public abstract List<Partnerdetail> findPartnerdetails()
    throws Exception;

  public abstract Partnerdetail findPartnerdetailByDetailId(int paramInt)
    throws Exception;
}
