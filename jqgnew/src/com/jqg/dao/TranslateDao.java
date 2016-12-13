package com.jqg.dao;

import com.jqg.pojo.Translate;

import java.util.List;

public abstract interface TranslateDao
{
  public abstract boolean addTranslate(Translate paramTranslate)
    throws Exception;

  public abstract boolean updateTranslate(Translate paramTranslate)
    throws Exception;

  public abstract boolean deleteTranslate(Translate paramTranslate)
    throws Exception;

  public abstract List<Translate> findTranslates()
    throws Exception;

  public abstract List<Translate> findTranslatesByuser(int paramInt)
    throws Exception;

  public abstract List<Translate> findTranslatesByuserPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract Translate findTranslateById(Integer paramInteger)
    throws Exception;

  public abstract List<Translate> findTranslateBySql(String paramString)
    throws Exception;
}
