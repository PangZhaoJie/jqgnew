package com.jqg.service;

import com.jqg.pojo.Investmentlevel;

import java.util.List;

public abstract interface InvestmentlevelService
{
  public abstract boolean createInvestmentlevel(Investmentlevel paramInvestmentlevel)
    throws Exception;

  public abstract boolean updateInvestmentlevel(Investmentlevel paramInvestmentlevel)
    throws Exception;

  public abstract boolean deleteInvestmentlevel(Investmentlevel paramInvestmentlevel)
    throws Exception;

  public abstract List<Investmentlevel> findInvestmentlevels()
    throws Exception;

  public abstract Investmentlevel findInvestmentlevelByinvestmentLevelId(int paramInt)
    throws Exception;
}
