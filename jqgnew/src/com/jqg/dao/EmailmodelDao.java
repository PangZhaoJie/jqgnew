package com.jqg.dao;

import com.jqg.pojo.Emailmodel;

import java.util.List;

public abstract interface EmailmodelDao
{
  public abstract boolean addEmailmodel(Emailmodel paramEmailmodel)
    throws Exception;

  public abstract boolean updateEmailmodel(Emailmodel paramEmailmodel)
    throws Exception;

  public abstract boolean deleteEmailmodel(Emailmodel paramEmailmodel)
    throws Exception;

  public abstract List<Emailmodel> findEmailmodels()
    throws Exception;

  public abstract Emailmodel findEmailmodelByemailModelId(int paramInt)
    throws Exception;
}

