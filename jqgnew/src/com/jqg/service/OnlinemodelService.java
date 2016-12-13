package com.jqg.service;

import com.jqg.pojo.Onlinemodel;

import java.util.List;

public abstract interface OnlinemodelService
{
  public abstract boolean createOnlinemodel(Onlinemodel paramOnlinemodel)
    throws Exception;

  public abstract boolean updateOnlinemodel(Onlinemodel paramOnlinemodel)
    throws Exception;

  public abstract boolean deleteOnlinemodel(Onlinemodel paramOnlinemodel)
    throws Exception;

  public abstract List<Onlinemodel> findOnlinemodels()
    throws Exception;

  public abstract List<Onlinemodel> findOnlinemodelByAble()
    throws Exception;

  public abstract Onlinemodel findOnlinemodelByonlineId(int paramInt)
    throws Exception;
}
