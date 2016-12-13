<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.jqg.pojo.Website"%>
<%@page import="com.jqg.service.impl.WebsiteServiceImpl"%>
<%@page import="com.jqg.service.WebsiteService"%>
<%
WebsiteService websiteService = new WebsiteServiceImpl();
Website website = websiteService.findWebsiteBywebsiteId(1);
 %>
<div class="bottom"><%=website.getWebfoot()  %></div>