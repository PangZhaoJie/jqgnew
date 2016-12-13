package com.jqg.dao;

import com.jqg.pojo.Tenderwords;

import java.util.List;

public abstract interface TenderwordsDao
{
  public abstract boolean addTenderwords(Tenderwords paramTenderwords)
    throws Exception;

  public abstract boolean updateTenderwords(Tenderwords paramTenderwords)
    throws Exception;

  public abstract boolean deleteTenderwords(Tenderwords paramTenderwords)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordss()
    throws Exception;

  public abstract Tenderwords findTenderwordsById(Integer paramInteger)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordsByState(int paramInt)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordsByStatePage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordsByUserId(int paramInt)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordsByUserIdPage(String paramString)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordsByLssuingId(int paramInt)
    throws Exception;

  public abstract List<Tenderwords> findTenderwordsByPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;
}
