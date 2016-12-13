package com.ckfinder;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class ckeditor extends StrutsPrepareAndExecuteFilter
{
  public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
    throws IOException, ServletException
  {
	 
    HttpServletRequest request = (HttpServletRequest)arg0;
    String uri = request.getRequestURI();
   
    if (uri.contains("/back/ueditor/jsp/")) {             
        System.out.println("使用自定义过滤器");             
        chain.doFilter(arg0, arg1);         
    }else{             
        System.out.println("使用默认过滤器");             
        if ((uri.endsWith("/ckfinder/core/connector/java/connector.java")) || (uri.endsWith("servlet"))){
            chain.doFilter(arg0, arg1);
          }else{
            super.doFilter(arg0, arg1, chain);
          }
    } 
  }
}