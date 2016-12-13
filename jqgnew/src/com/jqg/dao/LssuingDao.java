package com.jqg.dao;

import com.jqg.pojo.Lssuing;
import com.jqg.pojo.LssuingComp;

import java.util.List;

public abstract interface LssuingDao
{
  public abstract boolean addLssuing(Lssuing paramLssuing)
    throws Exception;

  public abstract boolean updateLssuing(Lssuing paramLssuing)
    throws Exception;

  public abstract boolean deleteLssuing(Lssuing paramLssuing)
    throws Exception;

  public abstract List<Lssuing> findLssuings()
    throws Exception;

  public abstract Lssuing findLssuingById(Integer paramInteger)
    throws Exception;

  public abstract List<Lssuing> findLssuingsIndex()
    throws Exception;

  public abstract List<Lssuing> findLssuingsBySearch(String paramString)
    throws Exception;
  public abstract List<Lssuing> findLssuingsIndex(String paramString,int cunt)
		    throws Exception;
  public abstract List<Lssuing> findLssuingsByUserId(int paramInt)
    throws Exception;

  public abstract List<Lssuing> findLssuingsByStatePage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract List<Lssuing> findLssuingsByUserIdAndState(int paramInt)
    throws Exception;

  public abstract List<Lssuing> findLssuingsByUserIdAndByState(int paramInt1, int paramInt2)
    throws Exception;
  /**
   * 根据借款方id查找借款方信息
   */
  public abstract LssuingComp findLssuingCompById(Integer compId)
		    throws Exception;
  /**
   * 修改借款方资料
   */
  public boolean updateLssuingComp(LssuingComp lssuingComp) throws Exception;
}
