package com.jqg.service;

import com.jqg.pojo.Automaticbid;

import java.util.List;

public abstract interface AutomaticbidService
{
  public abstract boolean createAutomaticbid(Automaticbid paramAutomaticbid)
    throws Exception;

  public abstract boolean updateAutomaticbid(Automaticbid paramAutomaticbid)
    throws Exception;

  public abstract boolean deleteAutomaticbid(Automaticbid paramAutomaticbid)
    throws Exception;

  public abstract List<Automaticbid> findAutomaticbids()
    throws Exception;

  public abstract List<Automaticbid> findAutomaticbidBySql(String paramString)
    throws Exception;

  public abstract Automaticbid findAutomaticbidByautomaticbidId(int paramInt)
    throws Exception;

  public abstract int queryCountAutomaticbid()
    throws Exception;
}
