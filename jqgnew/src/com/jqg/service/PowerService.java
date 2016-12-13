package com.jqg.service;

import com.jqg.pojo.Power;

import java.util.List;
/**
 * 
 * 
 * ������PowerService
 * ���ܣ�Ȩ�ޱ�
 * ��ϸ��
 * ���ߣ����е�(caozhongde)
 * �汾��1.0
 * ���ڣ�2015-1-23 ����1:57:27
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
