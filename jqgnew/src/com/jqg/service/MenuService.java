package com.jqg.service;

import com.jqg.pojo.Menu;

import java.util.List;

public abstract interface MenuService
{
  public abstract boolean addMenu(Menu paramMenu)
    throws Exception;

  public abstract boolean updateMenu(Menu paramMenu)
    throws Exception;

  public abstract boolean deleteMenu(Menu paramMenu)
    throws Exception;

  public abstract List<Menu> findMenus()
    throws Exception;

  public abstract Menu findMenuByMenuId(Integer paramInteger)
    throws Exception;
}