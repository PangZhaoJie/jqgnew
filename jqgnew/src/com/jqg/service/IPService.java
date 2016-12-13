package com.jqg.service;

import javax.servlet.http.HttpServletRequest;

public abstract interface IPService
{
	public abstract String getIpAddr(HttpServletRequest paramHttpServletRequest)
		    throws Exception;
}

