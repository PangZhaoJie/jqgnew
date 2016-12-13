package com.jqg.dao;

import com.jqg.pojo.Systemconf;

import java.util.List;

public abstract interface SystemconfDao
{
  public abstract boolean addSystemconf(Systemconf systemconf)
    throws Exception;

  public abstract boolean updateSystemconf(Systemconf systemconf)
    throws Exception;

  public abstract boolean deleteSystemconf(Systemconf systemconf)
    throws Exception;

  public abstract List<Systemconf> findSystemconfs()
    throws Exception;

  public abstract Systemconf findSystemconfBySysId(int paramInt)
    throws Exception;

  public abstract Systemconf findSystemconfByParname(String paramString)
    throws Exception;
}
