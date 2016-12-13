package com.jqg.service;

import com.jqg.pojo.Moneyuse;

import java.util.List;

public abstract interface MoneyuseService
{
  public abstract boolean addMoneyuse(Moneyuse paramMoneyuse)
    throws Exception;

  public abstract boolean updateMoneyuse(Moneyuse paramMoneyuse)
    throws Exception;

  public abstract boolean deleteMoneyuse(Moneyuse paramMoneyuse)
    throws Exception;

  public abstract List<Moneyuse> findMoneyuses()
    throws Exception;

  public abstract Moneyuse findMoneyuseById(Integer paramInteger)
    throws Exception;
}
