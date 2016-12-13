package com.jqg.dao;

import java.util.List;

import com.jqg.pojo.Manager;
import com.jqg.pojo.Oerationlog;

public abstract interface OerationlogDao
{
  public abstract boolean addOerationlog(Oerationlog paramOerationlog)
    throws Exception;

  public abstract boolean updateOerationlog(Oerationlog paramOerationlog)
    throws Exception;

  public abstract boolean deleteOerationlog(Oerationlog paramOerationlog)
    throws Exception;

  public abstract List<Oerationlog> findOerationlogs()
    throws Exception;

  public abstract List<Oerationlog> findOerationlogsPage(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Oerationlog> findOerationlogsBysql(String paramString)
    throws Exception;

  public abstract Oerationlog findOerationlogByoerationLogId(int paramInt)
    throws Exception;
  /**
   * ¸ù¾ÝmanagerId²éÑ¯
   * @param managerId
   * @return
   * @throws Exception
   */
  public abstract List<Oerationlog> findByManager(int managerId)throws Exception ;
}
