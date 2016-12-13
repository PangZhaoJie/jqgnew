package com.jqg.service;

import com.jqg.pojo.Manager;

import java.util.List;

public abstract interface ManagerService
{
  public abstract boolean addManager(Manager paramManager)
    throws Exception;

  public abstract boolean updateManager(Manager paramManager)
    throws Exception;

  public abstract boolean deleteManager(Manager paramManager)
    throws Exception;

  public abstract List<Manager> findManagers()
    throws Exception;

  public abstract List<Manager> findManagerspage(int paramInt1, int paramInt2)
    throws Exception;

  public abstract Manager findManagerByManagerId(Integer paramInteger)
    throws Exception;
  public abstract Manager findManagerByLogin(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract Manager findManagerByname(String paramString)
    throws Exception;

  public abstract List<Manager> findManagerBySql(String paramString)
    throws Exception;
  public abstract List<Manager> findManagers1(Integer paramInteger)
		    throws Exception;
  public abstract List<Manager> findManagerspage1(int paramInt1, int paramInt2, Integer paramInteger)
		    throws Exception;
  //查询客服信息
  public  abstract List<Manager> findManagerByIsCustomer() throws Exception;
}
