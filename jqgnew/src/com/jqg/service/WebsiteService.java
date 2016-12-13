package com.jqg.service;

import com.jqg.pojo.Website;

import java.util.List;
/**
 * ÍøÕ¾service
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
 * ¸ù¾ÝÍøÕ¾ID²éÑ¯ÍøÕ¾
 * @param paramInt
 * @return
 * @throws Exception
 */
  public abstract Website findWebsiteBywebsiteId(int paramInt)
    throws Exception;
}
