package com.jqg.service;

import com.jqg.pojo.Integraldetail;

import java.util.List;

public abstract interface IntegraldetailService
{
  public abstract boolean createIntegraldetail(Integraldetail paramIntegraldetail)
    throws Exception;

  public abstract boolean updateIntegraldetail(Integraldetail paramIntegraldetail)
    throws Exception;

  public abstract boolean deleteIntegraldetail(Integraldetail paramIntegraldetail)
    throws Exception;

  public abstract List<Integraldetail> findIntegraldetails()
    throws Exception;

  public abstract Integraldetail findIntegraldetailByintegralId(int paramInt)
    throws Exception;

  public abstract List<Integraldetail> findIntegraldetailsByUserId(int paramInt)
    throws Exception;

  public abstract List<Integraldetail> findIntegraldetailsByUserIdPage(String paramString)
    throws Exception;
}
