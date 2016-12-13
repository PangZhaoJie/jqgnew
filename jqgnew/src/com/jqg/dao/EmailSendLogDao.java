package com.jqg.dao;

import com.jqg.pojo.EmailSendLog;

import java.util.List;

public abstract interface EmailSendLogDao
{
  public abstract boolean addEmailSendLog(EmailSendLog paramEmailSendLog)
    throws Exception;

  public abstract boolean updateEmailSendLog(EmailSendLog paramEmailSendLog)
    throws Exception;

  public abstract boolean deleteEmailSendLog(EmailSendLog paramEmailSendLog)
    throws Exception;

  public abstract List<EmailSendLog> findEmailSendLogs()
    throws Exception;

  public abstract EmailSendLog findEmailSendLogByEmailSendLogId(int paramInt)
    throws Exception;

  public abstract List<EmailSendLog> findEmailSendLogsByUserId(int paramInt)
    throws Exception;

  public abstract List<EmailSendLog> findEmailSendLogsByUserIdPage(String paramString)
    throws Exception;
}

