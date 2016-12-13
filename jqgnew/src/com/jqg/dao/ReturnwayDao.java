package com.jqg.dao;

import com.jqg.pojo.Returnway;

import java.util.List;

public abstract interface ReturnwayDao
{
  public abstract boolean addReturnway(Returnway paramReturnway)
    throws Exception;

  public abstract boolean updateReturnway(Returnway paramReturnway)
    throws Exception;

  public abstract boolean deleteReturnway(Returnway paramReturnway)
    throws Exception;

  public abstract List<Returnway> findReturnways()
    throws Exception;

  public abstract Returnway findReturnwayById(Integer paramInteger)
    throws Exception;
}
