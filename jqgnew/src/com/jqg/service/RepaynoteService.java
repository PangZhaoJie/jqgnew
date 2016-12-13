package com.jqg.service;

import com.jqg.pojo.Repaynote;

import java.util.List;

public abstract interface RepaynoteService
{
  public abstract boolean addRepaynote(Repaynote paramRepaynote)
    throws Exception;

  public abstract boolean updateRepaynote(Repaynote paramRepaynote)
    throws Exception;

  public abstract boolean deleteRepaynote(Repaynote paramRepaynote)
    throws Exception;

  public abstract List<Repaynote> findRepaynotes()
    throws Exception;

  public abstract Repaynote findRepaynoteByRepaynoteId(int paramInt)
    throws Exception;

  public abstract List<Repaynote> findRepaynoteBylssuing(int paramInt)
    throws Exception;

  public abstract List<Repaynote> findRepaynoteByUserId(int paramInt)
    throws Exception;

  public abstract List<Repaynote> findRepaynoteByUserIdAndTime(int paramInt)
    throws Exception;

  public abstract List<Repaynote> findRepayBySql(String paramString)
    throws Exception;
  
  public abstract List<Repaynote> findComRepay(String starttime, String endtime)
			throws Exception;
}
