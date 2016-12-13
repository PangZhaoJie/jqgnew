package com.jqg.service;

import com.jqg.pojo.Basicinfor;

import java.util.List;

public abstract interface BasicinforService
{
  public abstract Integer createBasicinfor(Basicinfor paramBasicinfor)
    throws Exception;

  public abstract boolean updateBasicinfor(Basicinfor paramBasicinfor)
    throws Exception;

  public abstract boolean deleteBasicinfor(Basicinfor paramBasicinfor)
    throws Exception;

  public abstract List<Basicinfor> findBasicinfors()
    throws Exception;

  public abstract Basicinfor findBasicinforByBasicInforId(int paramInt)
    throws Exception;

  public abstract Basicinfor findBasicinforByUserId(int paramInt)
    throws Exception;
  public abstract Basicinfor findBasicinforByPhone(String phone)
		    throws Exception;
  public abstract Basicinfor findBasicinforByIdNum(String idNum) throws Exception;
}
