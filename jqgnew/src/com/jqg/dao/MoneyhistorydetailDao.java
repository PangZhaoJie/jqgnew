package com.jqg.dao;

import com.jqg.pojo.Moneyhistorydetail;

import java.util.List;

public abstract interface MoneyhistorydetailDao
{
  public abstract boolean addMoneyhistorydetail(Moneyhistorydetail paramMoneyhistorydetail)
    throws Exception;

  public abstract boolean updateMoneyhistorydetail(Moneyhistorydetail paramMoneyhistorydetail)
    throws Exception;

  public abstract boolean deleteMoneyhistorydetail(Moneyhistorydetail paramMoneyhistorydetail)
    throws Exception;

  public abstract List<Moneyhistorydetail> findMoneyhistorydetails()
    throws Exception;

  public abstract Moneyhistorydetail findMoneyhistorydetailById(int paramInt)
    throws Exception;

  public abstract Moneyhistorydetail findMoneyhistorydetailByUserId(int paramInt)
    throws Exception;

  public abstract List<Moneyhistorydetail> findMoneyhistorydetailBySql(String paramString)
    throws Exception;
}
