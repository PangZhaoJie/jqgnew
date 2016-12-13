package com.jqg.dao;

import com.jqg.pojo.Asktype;

import java.util.List;

public abstract interface AsktypeDao
{
  public abstract boolean addAsktype(Asktype paramAsktype)
    throws Exception;

  public abstract boolean updateAsktype(Asktype paramAsktype)
    throws Exception;

  public abstract boolean deleteAsktype(Asktype paramAsktype)
    throws Exception;

  public abstract List<Asktype> findAsktypes()
    throws Exception;

  public abstract Asktype findAsktypeById(Integer paramInteger)
    throws Exception;
}

