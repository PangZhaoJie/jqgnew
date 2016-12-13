package com.jqg.dao;

import com.jqg.pojo.Dataclassification;

import java.util.List;

public abstract interface DataclassificationDao
{
  public abstract boolean addDataclassification(Dataclassification paramDataclassification)
    throws Exception;

  public abstract boolean updateDataclassification(Dataclassification paramDataclassification)
    throws Exception;

  public abstract boolean deleteDataclassification(Dataclassification paramDataclassification)
    throws Exception;

  public abstract List<Dataclassification> findDataclassifications()
    throws Exception;

  public abstract Dataclassification findDataclassificationByDataClassId(int paramInt)
    throws Exception;
}

