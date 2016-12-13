package com.jqg.dao;

import com.jqg.pojo.Findmoney;

import java.util.List;

public abstract interface FindmoneyDao
{
  public abstract boolean addFindmoney(Findmoney paramFindmoney)
    throws Exception;

  public abstract boolean updateFindmoney(Findmoney paramFindmoney)
    throws Exception;

  public abstract boolean deleteFindmoney(Findmoney paramFindmoney)
    throws Exception;

  public abstract List<Findmoney> findFindmoneys()
    throws Exception;

  public abstract Findmoney findFindmoneyByfindMoneyId(int paramInt)
    throws Exception;
}

