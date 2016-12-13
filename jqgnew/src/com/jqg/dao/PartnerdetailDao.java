package com.jqg.dao;

import com.jqg.pojo.Partnerdetail;

import java.util.List;

public abstract interface PartnerdetailDao
{
  public abstract boolean addPartnerdetail(Partnerdetail paramPartnerdetail)
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
