package com.jqg.service;

import com.jqg.pojo.Validtime;

import java.util.List;

public abstract interface ValidtimeService
{
  public abstract boolean addValidtime(Validtime paramValidtime)
    throws Exception;

  public abstract boolean updateValidtime(Validtime paramValidtime)
    throws Exception;

  public abstract boolean deleteValidtime(Validtime paramValidtime)
    throws Exception;

  public abstract List<Validtime> findValidtimes()
    throws Exception;

  public abstract Validtime findValidtimeById(Integer paramInteger)
    throws Exception;
}
