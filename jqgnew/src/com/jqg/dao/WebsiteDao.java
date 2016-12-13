package com.jqg.dao;

import com.jqg.pojo.Website;

import java.util.List;

public abstract interface WebsiteDao
{
  public abstract boolean addWebsite(Website paramWebsite)
    throws Exception;

  public abstract boolean updateWebsite(Website paramWebsite)
    throws Exception;

  public abstract boolean deleteWebsite(Website paramWebsite)
    throws Exception;

  public abstract List<Website> findWebsites()
    throws Exception;

  public abstract Website findWebsiteBywebsiteId(int paramInt)
    throws Exception;
}
