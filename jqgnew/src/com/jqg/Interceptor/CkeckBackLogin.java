package com.jqg.Interceptor;

import com.jqg.pojo.Uservip;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class CkeckBackLogin extends AbstractInterceptor
{
  public String intercept(ActionInvocation actionInvocation)
    throws Exception
  {
    Map session = actionInvocation.getInvocationContext().getSession();
    if ((Uservip)session.get("uservip") != null)
    {
      return actionInvocation.invoke();
    }

    return "login";
  }
}