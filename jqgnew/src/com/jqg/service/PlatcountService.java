package com.jqg.service;

import com.jqg.pojo.Platcount;

import java.util.List;

public abstract interface PlatcountService
{
  public abstract boolean createPlatcount(Platcount paramPlatcount)
    throws Exception;

  public abstract boolean updatePlatcount(Platcount paramPlatcount)
    throws Exception;

  public abstract boolean deletePlatcount(Platcount paramPlatcount)
    throws Exception;

  public abstract List<Platcount> findPlatcounts()
    throws Exception;

  public abstract Platcount findPlatcountById(int paramInt)
    throws Exception;

  public abstract List<Platcount> findPlatcountBySql(String paramString)
    throws Exception;
}
