package com.jqg.service;

import com.jqg.pojo.Promrewardbypeople;

import java.util.List;

public abstract interface PromrewardbypeopleService
{
  public abstract boolean createPromrewardbypeople(Promrewardbypeople paramPromrewardbypeople)
    throws Exception;

  public abstract boolean updatePromrewardbypeople(Promrewardbypeople paramPromrewardbypeople)
    throws Exception;

  public abstract boolean deletePromrewardbypeople(Promrewardbypeople paramPromrewardbypeople)
    throws Exception;

  public abstract List<Promrewardbypeople> findPromrewardbypeoples()
    throws Exception;

  public abstract Promrewardbypeople findPromrewardbypeopleById(int paramInt)
    throws Exception;

  public abstract List<Promrewardbypeople> findPromrewardbypeoplesByUserId(int paramInt)
    throws Exception;

  public abstract List<Promrewardbypeople> findPromrewardbypeoplesByUserIdPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract List<Promrewardbypeople> findPromrewardbySql(String paramString)
    throws Exception;
}
