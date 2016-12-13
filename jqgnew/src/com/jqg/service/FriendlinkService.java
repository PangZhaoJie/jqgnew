package com.jqg.service;

import com.jqg.pojo.Friendlink;

import java.util.List;
/**
 * 友情连接service
 * @author Administrator
 *
 */
public abstract interface FriendlinkService
{
  public abstract boolean addFriendlink(Friendlink paramFriendlink)
    throws Exception;

  public abstract boolean updateFriendlink(Friendlink paramFriendlink)
    throws Exception;

  public abstract boolean deleteFriendlink(Friendlink paramFriendlink)
    throws Exception;

  public abstract List<Friendlink> findFriendlinks()
    throws Exception;

  public abstract Friendlink findFriendlinkById(Integer paramInteger)
    throws Exception;
/**
 * 根据索引查找连接并返回列表
 * @return
 * @throws Exception
 */
  public abstract List<Friendlink> findFriendlinksByIndex(String flag)
    throws Exception;

  public abstract List<Friendlink> findFriendlinkspage(int paramInt1, int paramInt2)
    throws Exception;
}
