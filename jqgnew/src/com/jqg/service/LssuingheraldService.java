package com.jqg.service;

import com.jqg.pojo.Lssuingherald;

import java.util.List;
/**
 * Ԥ��service
 * @author Administrator
 *
 */
public abstract interface LssuingheraldService
{
  public abstract boolean createLssuingherald(Lssuingherald paramLssuingherald)
    throws Exception;

  public abstract boolean updateLssuingherald(Lssuingherald paramLssuingherald)
    throws Exception;

  public abstract boolean deleteLssuingherald(Lssuingherald paramLssuingherald)
    throws Exception;

  public abstract List<Lssuingherald> findLssuingheralds()
    throws Exception;

  public abstract Lssuingherald findLssuingheraldByLssuingheraldId(int paramInt)
    throws Exception;
/**
 * ����������ѯ������Ԥ����Ϣ
 * @return
 * @throws Exception
 */
  public abstract Lssuingherald findLssuingheraldByindex()
    throws Exception;
}
