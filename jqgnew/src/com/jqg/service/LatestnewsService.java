package com.jqg.service;

import com.jqg.pojo.Latestnews;

import java.util.List;
/**
 * 最新新闻service
 * @author Administrator
 *
 */
public abstract interface LatestnewsService
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
/**
 * 根据图片查询最新新闻
 * @return
 * @throws Exception
 */
  public abstract List<Latestnews> findLatestnewssBypicture()
    throws Exception;
/**
 * 根据索引查询最新消息
 * @param paramInt
 * @return
 * @throws Exception
 */
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

  public abstract List<Latestnews> findLatestnewsspage(int paramInt1, int paramInt2)
    throws Exception;
}
