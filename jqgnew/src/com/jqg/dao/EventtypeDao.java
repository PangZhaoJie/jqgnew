package com.jqg.dao;

import com.jqg.pojo.Eventtype;

import java.util.List;

public abstract interface EventtypeDao
{
  public abstract boolean addEventtype(Eventtype paramEventtype)
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

