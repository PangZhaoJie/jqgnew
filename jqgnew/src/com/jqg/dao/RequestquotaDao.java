package com.jqg.dao;

import com.jqg.pojo.Requestquota;

import java.util.List;

public abstract interface RequestquotaDao
{
  public abstract boolean addRequestquota(Requestquota paramRequestquota)
    throws Exception;

  public abstract boolean updateRequestquota(Requestquota paramRequestquota)
    throws Exception;

  public abstract boolean deleteRequestquota(Requestquota paramRequestquota)
    throws Exception;

  public abstract List<Requestquota> findRequestquotas()
    throws Exception;

  public abstract Requestquota findRequestquotaByrequestQuotaId(int paramInt)
    throws Exception;

  public abstract List<Requestquota> findRequestquotasByUserId(int paramInt)
    throws Exception;

  public abstract List<Requestquota> findRequestquotasByUserIdPage(String paramString)
    throws Exception;
}
