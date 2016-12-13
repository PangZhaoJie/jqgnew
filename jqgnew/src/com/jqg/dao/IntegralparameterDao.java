package com.jqg.dao;

import com.jqg.pojo.Integralparameter;

import java.util.List;

public abstract interface IntegralparameterDao
{
  public abstract boolean addIntegralparameter(Integralparameter paramIntegralparameter)
    throws Exception;

  public abstract boolean updateIntegralparameter(Integralparameter paramIntegralparameter)
    throws Exception;

  public abstract boolean deleteIntegralparameter(Integralparameter paramIntegralparameter)
    throws Exception;

  public abstract List<Integralparameter> findIntegralparameters()
    throws Exception;

  public abstract Integralparameter findIntegralparameterByintegralPid(int paramInt)
    throws Exception;

  public abstract Integralparameter findIntegralparameterByIntegralPname(String paramString)
    throws Exception;
}
