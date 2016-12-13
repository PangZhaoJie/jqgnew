package com.jqg.dao;

import com.jqg.pojo.Lssuingherald;

import java.util.List;

public abstract interface LssuingheraldDao
{
  public abstract boolean addLssuingherald(Lssuingherald paramLssuingherald)
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
