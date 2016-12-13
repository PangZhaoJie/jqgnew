 package com.jqg.service.impl;
 
 import com.jqg.dao.IPDao;
import com.jqg.dao.impl.IPDaoImpl;
import com.jqg.service.IPService;

import javax.servlet.http.HttpServletRequest;
 
 public class IPServiceImpl
   implements IPService
 {
	 IPDao ipDao = new IPDaoImpl();
	 
	   public String getIpAddr(HttpServletRequest request) throws Exception
	   {
	     return this.ipDao.getIpAddr(request);
	   }
 }

