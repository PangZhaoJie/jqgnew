package com.jqg.dao;

import com.jqg.pojo.Lssingphoto;

import java.util.List;

public abstract interface LssingphotoDao
{
  public abstract boolean addLssingphoto(Lssingphoto paramLssingphoto)
    throws Exception;

  public abstract boolean updateLssingphoto(Lssingphoto paramLssingphoto)
    throws Exception;

  public abstract boolean deleteLssingphoto(Lssingphoto paramLssingphoto)
    throws Exception;

  public abstract List<Lssingphoto> findLssingphotos()
    throws Exception;

  public abstract List<Lssingphoto> findLssingphotosBylssuing(int paramInt)
    throws Exception;

  public abstract List<Lssingphoto> findLssingphotosBylssuingPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract Lssingphoto findLssingphotoById(Integer paramInteger)
    throws Exception;
}
