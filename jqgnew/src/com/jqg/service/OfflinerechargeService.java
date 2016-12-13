package com.jqg.service;

import com.jqg.pojo.Offlinerecharge;

import java.util.List;

public abstract interface OfflinerechargeService
{
  public abstract boolean createOfflinerecharge(Offlinerecharge paramOfflinerecharge)
    throws Exception;

  public abstract boolean updateOfflinerecharge(Offlinerecharge paramOfflinerecharge)
    throws Exception;

  public abstract boolean deleteOfflinerecharge(Offlinerecharge paramOfflinerecharge)
    throws Exception;

  public abstract List<Offlinerecharge> findOfflinerecharges()
    throws Exception;

  public abstract Offlinerecharge findOfflinerechargeById(int paramInt)
    throws Exception;

  public abstract List<Offlinerecharge> findOfflinerechargeBySql(String paramString)
    throws Exception;
  
  public abstract List findListbysqsl(String sql) throws Exception;
}
