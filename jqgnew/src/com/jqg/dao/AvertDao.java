package com.jqg.dao;

import com.jqg.pojo.Avert;

import java.util.List;

public abstract interface AvertDao
{
  public abstract boolean addAvert(Avert paramAvert)
    throws Exception;

  public abstract boolean updateAvert(Avert paramAvert)
    throws Exception;

  public abstract boolean deleteAvert(Avert paramAvert)
    throws Exception;

  public abstract List<Avert> findAverts()
    throws Exception;

  public abstract Avert findAvertByavertId(int paramInt)
    throws Exception;
}

