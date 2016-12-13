package com.jqg.dao;

import com.jqg.pojo.Frontmenu;

import java.util.List;

public abstract interface FrontmenuDao
{
  public abstract boolean addFrontmenu(Frontmenu paramFrontmenu)
    throws Exception;

  public abstract boolean updateFrontmenu(Frontmenu paramFrontmenu)
    throws Exception;

  public abstract boolean deleteFrontmenu(Frontmenu paramFrontmenu)
    throws Exception;

  public abstract List<Frontmenu> findFrontmenus()
    throws Exception;

  public abstract Frontmenu findFrontmenuById(Integer paramInteger)
    throws Exception;

  public abstract List<Frontmenu> findFrontmenuByIdANdId(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Frontmenu> findFrontmenutonew(int paramInt1, int paramInt2)
    throws Exception;
  
  
  public abstract List<Frontmenu> findFrontmenuByisColumn() throws Exception;

  public abstract List<Frontmenu> findFrontmenusBy(int paramInt)
    throws Exception;

  public abstract List<Frontmenu> findFrontmenusByDisplay(int paramInt)
    throws Exception;

  public abstract Frontmenu findFrontmenuByCode(String paramString)
    throws Exception;
}
