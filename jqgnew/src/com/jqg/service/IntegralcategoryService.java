package com.jqg.service;

import com.jqg.pojo.Integralcategory;

import java.util.List;

public abstract interface IntegralcategoryService
{
  public abstract boolean createIntegralcategory(Integralcategory paramIntegralcategory)
    throws Exception;

  public abstract boolean updateIntegralcategory(Integralcategory paramIntegralcategory)
    throws Exception;

  public abstract boolean deleteIntegralcategory(Integralcategory paramIntegralcategory)
    throws Exception;

  public abstract List<Integralcategory> findIntegralcategorys()
    throws Exception;

  public abstract Integralcategory findIntegralcategoryByintCategoryId(int paramInt)
    throws Exception;
}
