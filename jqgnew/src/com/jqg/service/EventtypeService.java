package com.jqg.service;

import com.jqg.pojo.Eventtype;

import java.util.List;

public abstract interface EventtypeService
{
  public abstract boolean createEventtype(Eventtype paramEventtype)
    throws Exception;

  public abstract boolean updateEventtype(Eventtype paramEventtype)
    throws Exception;

  public abstract boolean deleteEventtype(Eventtype paramEventtype)
    throws Exception;

  public abstract List<Eventtype> findEventtypes()
    throws Exception;

  public abstract Eventtype findEventtypeById(int paramInt)
    throws Exception;
}
