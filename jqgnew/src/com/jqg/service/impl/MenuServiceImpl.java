 package com.jqg.service.impl;
 
 import com.jqg.dao.MenuDao;
import com.jqg.dao.impl.MenuDaoImpl;
import com.jqg.pojo.Menu;
import com.jqg.service.MenuService;

import java.util.List;
 
 public class MenuServiceImpl
   implements MenuService
 {
   MenuDao menuDao = new MenuDaoImpl();
 
   public boolean addMenu(Menu menu) throws Exception
   {
     return this.menuDao.addMenu(menu);
   }
 
   public boolean updateMenu(Menu menu)
     throws Exception
   {
     return this.menuDao.updateMenu(menu);
   }
 
   public boolean deleteMenu(Menu menu)
     throws Exception
   {
     return this.menuDao.deleteMenu(menu);
   }
 
   public List<Menu> findMenus()
     throws Exception
   {
     return this.menuDao.findMenus();
   }
 
   public Menu findMenuByMenuId(Integer menuId)
     throws Exception
   {
     return this.menuDao.findMenuByMenuId(menuId);
   }
 }

