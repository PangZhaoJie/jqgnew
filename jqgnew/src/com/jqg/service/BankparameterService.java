package com.jqg.service;

import com.jqg.pojo.Bankparameter;

import java.util.List;

public abstract interface BankparameterService
{
  public abstract boolean createBankparameter(Bankparameter paramBankparameter)
    throws Exception;

  public abstract boolean updateBankparameter(Bankparameter paramBankparameter)
    throws Exception;

  public abstract boolean deleteBankparameter(Bankparameter paramBankparameter)
    throws Exception;

  public abstract List<Bankparameter> findBankparameters()
    throws Exception;

  public abstract Bankparameter findBankparameterBybankPid(int paramInt)
    throws Exception;
}
