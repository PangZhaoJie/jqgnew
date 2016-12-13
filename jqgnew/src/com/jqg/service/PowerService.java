package com.jqg.service;

import com.jqg.pojo.Power;

import java.util.List;
/**
 * 
 * 
 * 类名：PowerService
 * 功能：权限表
 * 详细：
 * 作者：曹中德(caozhongde)
 * 版本：1.0
 * 日期：2015-1-23 下午1:57:27
 *
 */
public abstract interface PowerService
{

	  public abstract boolean addPower(Power paramPower)
	    throws Exception;

	  public abstract boolean updatePower(Power paramPower)
	    throws Exception;

	  public abstract boolean deletePower(Power paramPower)
	    throws Exception;

	  public abstract List<Power> findPowers()
	    throws Exception;

	  public abstract Power findPowerByPowerId(Integer paramInteger)
	    throws Exception;

	  public abstract List<Power> findPowersByMeuAndRole(Integer paramInteger1, Integer paramInteger2)
	    throws Exception;

	  public abstract List<Power> findPowersBySql(String paramString)
	    throws Exception;
}
