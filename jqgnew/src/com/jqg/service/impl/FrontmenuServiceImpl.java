 package com.jqg.service.impl;
 
 import com.jqg.dao.FrontmenuDao;
import com.jqg.dao.impl.FrontmenuDaoImpl;
import com.jqg.pojo.Frontmenu;
import com.jqg.service.FrontmenuService;

import java.util.List;
 
 public class FrontmenuServiceImpl
   implements FrontmenuService
 {
   FrontmenuDao frontmenuDao = new FrontmenuDaoImpl();
 
   public boolean addFrontmenu(Frontmenu frontmenu) throws Exception
   {
     return this.frontmenuDao.addFrontmenu(frontmenu);
   }
 
   public boolean updateFrontmenu(Frontmenu frontmenu)
     throws Exception
   {
     return this.frontmenuDao.updateFrontmenu(frontmenu);
   }
 
   public boolean deleteFrontmenu(Frontmenu frontmenu)
     throws Exception
   {
     return this.frontmenuDao.deleteFrontmenu(frontmenu);
   }
 
   public List<Frontmenu> findFrontmenus()
     throws Exception
   {
     return this.frontmenuDao.findFrontmenus();
   }
 
   public Frontmenu findFrontmenuById(Integer frontmenuId)
     throws Exception
   {
     return this.frontmenuDao.findFrontmenuById(frontmenuId);
   }
 
   public List<Frontmenu> findFrontmenuByIdANdId(int frontMenuId1, int frontMenuId2)
     throws Exception
   {
     return this.frontmenuDao.findFrontmenuByIdANdId(frontMenuId1, frontMenuId2);
   }
 
   public List<Frontmenu> findFrontmenusBy(int fatherId)
     throws Exception
   {
     return this.frontmenuDao.findFrontmenusBy(fatherId);
   }
 
   public List<Frontmenu> findFrontmenusByDisplay(int fatherId)
     throws Exception
   {
     return this.frontmenuDao.findFrontmenusByDisplay(fatherId);
   }
 
   public Frontmenu findFrontmenuByCode(String frontMenuCode)
     throws Exception
   {
     return this.frontmenuDao.findFrontmenuByCode(frontMenuCode);
   }
 
   public List<Frontmenu> findFrontmenutonew(int frontMenuId1, int frontMenuId2)
     throws Exception
   {
     return this.frontmenuDao.findFrontmenutonew(frontMenuId1, frontMenuId2);
   }

public List<Frontmenu> findFrontmenuByisColumn() throws Exception {
	// TODO Auto-generated method stub
	return this.frontmenuDao.findFrontmenuByisColumn();
}
 }

