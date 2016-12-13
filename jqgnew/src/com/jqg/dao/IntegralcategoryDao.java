package com.jqg.dao;

import com.jqg.pojo.Integralcategory;

import java.util.List;

public abstract interface IntegralcategoryDao
{
  public abstract boolean addIntegralcategory(Integralcategory paramIntegralcategory)
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
