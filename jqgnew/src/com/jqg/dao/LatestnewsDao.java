package com.jqg.dao;

import com.jqg.pojo.Latestnews;

import java.util.List;

public abstract interface LatestnewsDao
{
  public abstract boolean addLatestnews(Latestnews paramLatestnews)
    throws Exception;

  public abstract boolean updateLatestnews(Latestnews paramLatestnews)
    throws Exception;

  public abstract boolean deleteLatestnews(Latestnews paramLatestnews)
    throws Exception;

  public abstract List<Latestnews> findLatestnewss(String sql)
    throws Exception;

  public abstract Latestnews findLatestnewsByLatestnewsId(Integer paramInteger)
    throws Exception;

  public abstract List<Latestnews> findLatestnewsspage(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Latestnews> findLatestnewssBypicture()
    throws Exception;

  public abstract List<Latestnews> findLatestnewssByfrontMenuIdindex(int paramInt)
    throws Exception;

  public abstract List<Latestnews> findLatestnewssByfrontMenuId(int paramInt)
    throws Exception;

  public abstract List<Latestnews> findLatestnewssByfrontMenuIdPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract Latestnews findLatestnewsByonPage(Integer paramInteger, int paramInt)
    throws Exception;

  public abstract Latestnews findLatestnewsBydownPage(Integer paramInteger, int paramInt)
    throws Exception;
}
