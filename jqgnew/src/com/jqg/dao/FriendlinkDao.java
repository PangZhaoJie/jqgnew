package com.jqg.dao;

import com.jqg.pojo.Friendlink;

import java.util.List;

public abstract interface FriendlinkDao
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

  public abstract List<Friendlink> findFriendlinksByIndex(String flag)
    throws Exception;

  public abstract List<Friendlink> findFriendlinkspage(int paramInt1, int paramInt2)
    throws Exception;
}

