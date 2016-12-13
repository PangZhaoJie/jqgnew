package com.jqg.dao;

import com.jqg.pojo.Internallettermodel;

import java.util.List;

public abstract interface InternallettermodelDao
{
  public abstract boolean addInternallettermodel(Internallettermodel paramInternallettermodel)
    throws Exception;

  public abstract boolean updateInternallettermodel(Internallettermodel paramInternallettermodel)
    throws Exception;

  public abstract boolean deleteInternallettermodel(Internallettermodel paramInternallettermodel)
    throws Exception;

  public abstract List<Internallettermodel> findInternallettermodels()
    throws Exception;

  public abstract Internallettermodel findInternallettermodelByinterModelId(int paramInt)
    throws Exception;
}
