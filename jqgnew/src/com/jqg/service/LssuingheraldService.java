package com.jqg.service;

import com.jqg.pojo.Lssuingherald;

import java.util.List;
/**
 * 预警service
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
 * 根据索引查询发布的预警信息
 * @return
 * @throws Exception
 */
  public abstract Lssuingherald findLssuingheraldByindex()
    throws Exception;
}
