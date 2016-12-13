package com.jqg.service;

import com.jqg.pojo.Smtp;

import java.util.List;

public abstract interface SmtpService
{
  public abstract boolean createSmtp(Smtp paramSmtp)
    throws Exception;

  public abstract boolean updateSmtp(Smtp paramSmtp)
    throws Exception;

  public abstract boolean deleteSmtp(Smtp paramSmtp)
    throws Exception;

  public abstract List<Smtp> findSmtps()
    throws Exception;

  public abstract Smtp findSmtpBysmtpid(int paramInt)
    throws Exception;
}