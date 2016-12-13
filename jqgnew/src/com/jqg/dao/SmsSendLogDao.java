package com.jqg.dao;

import com.jqg.pojo.SmsSendLog;

import java.util.List;

public abstract interface SmsSendLogDao
{
  public abstract boolean addSmsSendLog(SmsSendLog paramSmsSendLog)
    throws Exception;

  public abstract boolean updateSmsSendLog(SmsSendLog paramSmsSendLog)
    throws Exception;

  public abstract boolean deleteSmsSendLog(SmsSendLog paramSmsSendLog)
    throws Exception;

  public abstract List<SmsSendLog> findSmsSendLogs()
    throws Exception;

  public abstract SmsSendLog findSmsSendLogBySmsSendLogId(int paramInt)
    throws Exception;

  public abstract List<SmsSendLog> findSmsSendLogsByUserId(int paramInt)
    throws Exception;

  public abstract List<SmsSendLog> findSmsSendLogsByUserIdPage(String paramString)
    throws Exception;
  
}
