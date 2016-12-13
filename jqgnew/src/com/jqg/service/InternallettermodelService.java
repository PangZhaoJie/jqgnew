package com.jqg.service;

import com.jqg.pojo.Internallettermodel;

import java.util.List;

public abstract interface InternallettermodelService
{
  public abstract boolean createInternallettermodel(Internallettermodel paramInternallettermodel)
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
