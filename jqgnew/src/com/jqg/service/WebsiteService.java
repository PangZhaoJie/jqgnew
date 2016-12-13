package com.jqg.service;

import com.jqg.pojo.Website;

import java.util.List;
/**
 * ��վservice
 * @author Administrator
 *
 */
public abstract interface WebsiteService
{
  public abstract boolean createWebsite(Website paramWebsite)
    throws Exception;

  public abstract boolean updateWebsite(Website paramWebsite)
    throws Exception;

  public abstract boolean deleteWebsite(Website paramWebsite)
    throws Exception;

  public abstract List<Website> findWebsites()
    throws Exception;
/**
 * ������վID��ѯ��վ
 * @param paramInt
 * @return
 * @throws Exception
 */
  public abstract Website findWebsiteBywebsiteId(int paramInt)
    throws Exception;
}
