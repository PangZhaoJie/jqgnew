package com.jqg.service;

import com.jqg.pojo.Matercertifi;

import java.util.List;

public abstract interface MatercertifiService
{
  public abstract boolean createMatercertifi(Matercertifi paramMatercertifi)
    throws Exception;

  public abstract boolean updateMatercertifi(Matercertifi paramMatercertifi)
    throws Exception;

  public abstract boolean deleteMatercertifi(Matercertifi paramMatercertifi)
    throws Exception;

  public abstract List<Matercertifi> findMatercertifis()
    throws Exception;

  public abstract Matercertifi findMatercertifiByMaterCertifiId(int paramInt)
    throws Exception;

  public abstract Matercertifi findMatercertifiByUserId(int paramInt)
    throws Exception;
}
