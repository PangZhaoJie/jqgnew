package com.jqg.service;

import com.jqg.pojo.Moneymax;

import java.util.List;

public abstract interface MoneymaxService
{
  public abstract boolean addMoneymax(Moneymax paramMoneymax)
    throws Exception;

  public abstract boolean updateMoneymax(Moneymax paramMoneymax)
    throws Exception;

  public abstract boolean deleteMoneymax(Moneymax paramMoneymax)
    throws Exception;

  public abstract List<Moneymax> findMoneymaxs()
    throws Exception;

  public abstract Moneymax findMoneymaxById(Integer paramInteger)
    throws Exception;
}
