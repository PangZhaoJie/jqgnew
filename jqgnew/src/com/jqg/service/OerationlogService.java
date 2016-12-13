package com.jqg.service;

import com.jqg.pojo.Oerationlog;

import java.util.List;

public abstract interface OerationlogService
{
  public abstract boolean createOerationlog(Oerationlog paramOerationlog)
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
