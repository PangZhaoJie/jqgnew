package com.jqg.dao;

import com.jqg.pojo.Inbox;

import java.util.List;

public abstract interface InboxDao
{
  public abstract boolean addInbox(Inbox paramInbox)
    throws Exception;

  public abstract boolean updateInbox(Inbox paramInbox)
    throws Exception;

  public abstract boolean deleteInbox(Inbox paramInbox)
    throws Exception;

  public abstract List<Inbox> findInboxs()
    throws Exception;

  public abstract Inbox findInboxByInboxId(int paramInt)
    throws Exception;

  public abstract List<Inbox> findInboxsByUserId(int paramInt)
    throws Exception;

  public abstract List<Inbox> findInboxsByUserIdPage(String paramString)
    throws Exception;
}
