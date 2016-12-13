<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>防sql注入系统</title>  
  </head>  
    
  <body>  
    这个是防sql注入系统，自动过滤您的请求，请更换请求字符串。  
    <%=session.getAttribute("sqlInjectError")%>  
    <p><a href="<%=path%>">点此返回</a></p>  
  </body>  
</html>  