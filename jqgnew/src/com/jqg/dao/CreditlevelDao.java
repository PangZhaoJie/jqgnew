package com.jqg.dao;

import com.jqg.pojo.Creditlevel;

import java.util.List;

public abstract interface CreditlevelDao
{
  public abstract boolean addCreditlevel(Creditlevel paramCreditlevel)
    throws Exception;

  public abstract boolean updateCreditlevel(Creditlevel paramCreditlevel)
    throws Exception;

  public abstract boolean deleteCreditlevel(Creditlevel paramCreditlevel)
    throws Exception;

  public abstract List<Creditlevel> findCreditlevels()
    throws Exception;

  public abstract Creditlevel findCreditlevelBycreditLevelId(int paramInt)
    throws Exception;
}

