package com.jqg.service;

import com.jqg.pojo.Customerphone;

import java.util.List;

public abstract interface CustomerphoneService
{
  public abstract boolean createCustomerphone(Customerphone paramCustomerphone)
    throws Exception;

  public abstract boolean updateCustomerphone(Customerphone paramCustomerphone)
    throws Exception;

  public abstract boolean deleteCustomerphone(Customerphone paramCustomerphone)
    throws Exception;

  public abstract List<Customerphone> findCustomerphones()
    throws Exception;

  public abstract Customerphone findCustomerphoneBycustomerphoneId(int paramInt)
    throws Exception;
  
  public abstract Customerphone findLastCustomerphone()
		    throws Exception;
}
