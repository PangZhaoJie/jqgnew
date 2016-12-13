package com.jqg.dao;

import javax.servlet.http.HttpServletRequest;

public abstract interface IPDao
{
  public abstract String getIpAddr(HttpServletRequest paramHttpServletRequest)
    throws Exception;
}
