package com.jqg.service;

import com.jqg.pojo.Promrewardbymoney;

import java.util.List;

public abstract interface PromrewardbymoneyService
{
  public abstract boolean createPromrewardbymoney(Promrewardbymoney paramPromrewardbymoney)
    throws Exception;

  public abstract boolean updatePromrewardbymoney(Promrewardbymoney paramPromrewardbymoney)
    throws Exception;

  public abstract boolean deletePromrewardbymoney(Promrewardbymoney paramPromrewardbymoney)
    throws Exception;

  public abstract List<Promrewardbymoney> findPromrewardbymoneys()
    throws Exception;

  public abstract Promrewardbymoney findPromrewardbymoneyById(int paramInt)
    throws Exception;
}
