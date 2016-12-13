package com.jqg.service;

import com.jqg.pojo.Lssuing;
import com.jqg.pojo.LssuingComp;

import java.util.List;
/**
 * ���� service
 * @author Administrator
 *
 */
public abstract interface LssuingService
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
  public abstract List<Lssuing> findLssuingsIndex(String sql,int cunt)
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
   * �޸Ľ�����
   */
  public boolean updateLssuingComp(LssuingComp lssuingComp) throws Exception;
  /**
   * ���ݽ�id���ҽ���Ϣ
   */
  public abstract LssuingComp findLssuingCompById(Integer compId)
		    throws Exception;
}
