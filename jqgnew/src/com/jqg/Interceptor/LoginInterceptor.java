package com.jqg.Interceptor;

import com.jqg.pojo.Manager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor
{
  public String intercept(ActionInvocation invocation)
    throws Exception
  {
    Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");

    if (manager != null)
    {
      return invocation.invoke();
    }

    return "input";
  }
}