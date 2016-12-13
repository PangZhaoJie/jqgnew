package com.jqg.Interceptor;

import com.jqg.pojo.Manager;
import com.jqg.pojo.Role;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.io.PrintStream;
import java.util.Map;

public class ManagerInterceptor extends AbstractInterceptor
{
  public String intercept(ActionInvocation invocation)
    throws Exception
  {
    Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");

    if ((manager != null) && (manager.getRole().getRoleId().intValue() == 1))
    {
      return invocation.invoke();
    }
    System.out.println("s");
    return "login";
  }
}