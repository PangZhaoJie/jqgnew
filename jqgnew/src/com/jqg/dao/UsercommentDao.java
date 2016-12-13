package com.jqg.dao;

import com.jqg.pojo.Usercomment;

import java.util.List;

public abstract interface UsercommentDao
{
  public abstract boolean addUsercomment(Usercomment paramUsercomment)
    throws Exception;

  public abstract boolean updateUsercomment(Usercomment paramUsercomment)
    throws Exception;

  public abstract boolean deleteUsercomment(Usercomment paramUsercomment)
    throws Exception;

  public abstract List<Usercomment> findUsercomments()
    throws Exception;

  public abstract Usercomment findUsercommentById(Integer paramInteger)
    throws Exception;

  public abstract List<Usercomment> findUsercommentByUserId(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
    throws Exception;

  public abstract List<Usercomment> findUsercommentByPage(String paramString)
    throws Exception;
}
