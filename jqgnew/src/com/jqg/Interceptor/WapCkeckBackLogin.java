package com.jqg.Interceptor;

import com.jqg.pojo.Uservip;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class WapCkeckBackLogin extends AbstractInterceptor
{
  public String intercept(ActionInvocation actionInvocation)
    throws Exception
  {
	  HttpServletRequest  request = ServletActionContext.getRequest();
	  String sessionId = request.getSession().getId();
	  Uservip uservip = (Uservip) ActionContext.getContext().getSession()
				.get("uservip" + sessionId);  
    if (uservip != null)
    {
      return actionInvocation.invoke();
    }

    return "login";
  }
}