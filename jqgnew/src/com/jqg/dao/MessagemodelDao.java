package com.jqg.dao;

import com.jqg.pojo.Messagemodel;

import java.util.List;

public abstract interface MessagemodelDao
{
  public abstract boolean addMessagemodel(Messagemodel paramMessagemodel)
    throws Exception;

  public abstract boolean updateMessagemodel(Messagemodel paramMessagemodel)
    throws Exception;

  public abstract boolean deleteMessagemodel(Messagemodel paramMessagemodel)
    throws Exception;

  public abstract List<Messagemodel> findMessagemodels()
    throws Exception;

  public abstract Messagemodel findMessagemodelByintegralPid(int paramInt)
    throws Exception;
}
