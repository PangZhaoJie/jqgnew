package com.jqg.service;

import com.jqg.pojo.Moneymin;

import java.util.List;

public abstract interface MoneyminService
{
  public abstract boolean addMoneymin(Moneymin paramMoneymin)
    throws Exception;

  public abstract boolean updateMoneymin(Moneymin paramMoneymin)
    throws Exception;

  public abstract boolean deleteMoneymin(Moneymin paramMoneymin)
    throws Exception;

  public abstract List<Moneymin> findMoneymins()
    throws Exception;

  public abstract Moneymin findMoneyminById(Integer paramInteger)
    throws Exception;
}