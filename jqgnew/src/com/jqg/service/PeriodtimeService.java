package com.jqg.service;

import com.jqg.pojo.Periodtime;

import java.util.List;

public abstract interface PeriodtimeService
{
  public abstract boolean addPeriodtime(Periodtime paramPeriodtime)
    throws Exception;

  public abstract boolean updatePeriodtime(Periodtime paramPeriodtime)
    throws Exception;

  public abstract boolean deletePeriodtime(Periodtime paramPeriodtime)
    throws Exception;

  public abstract List<Periodtime> findPeriodtimes()
    throws Exception;

  public abstract Periodtime findPeriodtimeByPeriodtimeId(int paramInt)
    throws Exception;
}
