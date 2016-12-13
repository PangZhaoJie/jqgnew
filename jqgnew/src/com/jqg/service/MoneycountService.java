package com.jqg.service;

import com.jqg.pojo.Moneycount;

import java.util.List;

public abstract interface MoneycountService
{
  public abstract boolean createMoneycount(Moneycount paramMoneycount)
    throws Exception;

  public abstract boolean updateMoneycount(Moneycount paramMoneycount)
    throws Exception;

  public abstract boolean deleteMoneycount(Moneycount paramMoneycount)
    throws Exception;

  public abstract List<Moneycount> findMoneycounts()
    throws Exception;

  public abstract Moneycount findMoneycountById(int paramInt)
    throws Exception;

  public abstract Moneycount findMoneycountByuserId(int paramInt)
    throws Exception;

  public abstract List<Moneycount> findMoneycountBySql(String paramString)
    throws Exception;
}
