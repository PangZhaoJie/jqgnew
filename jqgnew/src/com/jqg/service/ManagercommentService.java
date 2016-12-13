package com.jqg.service;

import com.jqg.pojo.Managercomment;

import java.util.List;

public abstract interface ManagercommentService
{
  public abstract boolean addManagercomment(Managercomment paramManagercomment)
    throws Exception;

  public abstract boolean updateManagercomment(Managercomment paramManagercomment)
    throws Exception;

  public abstract boolean deleteManagercomment(Managercomment paramManagercomment)
    throws Exception;

  public abstract List<Managercomment> findManagercomments()
    throws Exception;

  public abstract Managercomment findManagercommentById(Integer paramInteger)
    throws Exception;
}
