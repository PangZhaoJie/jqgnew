package com.jqg.dao;

import com.jqg.pojo.Periodday;

import java.util.List;

public abstract interface PerioddayDao
{
  public abstract boolean addPeriodday(Periodday paramPeriodday)
    throws Exception;

  public abstract boolean updatePeriodday(Periodday paramPeriodday)
    throws Exception;

  public abstract boolean deletePeriodday(Periodday paramPeriodday)
    throws Exception;

  public abstract List<Periodday> findPerioddays()
    throws Exception;

  public abstract Periodday findPerioddayByPerioddayId(int paramInt)
    throws Exception;
}
