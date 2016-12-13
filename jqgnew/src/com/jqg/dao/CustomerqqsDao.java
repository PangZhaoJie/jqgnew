package com.jqg.dao;

import com.jqg.pojo.Customerqqs;

import java.util.List;

public abstract interface CustomerqqsDao
{
  public abstract boolean addCustomerqqs(Customerqqs paramCustomerqqs)
    throws Exception;

  public abstract boolean updateCustomerqqs(Customerqqs paramCustomerqqs)
    throws Exception;

  public abstract boolean deleteCustomerqqs(Customerqqs paramCustomerqqs)
    throws Exception;

  public abstract List<Customerqqs> findCustomerqqss()
    throws Exception;

  public abstract Customerqqs findCustomerqqsBycustomerQqsid(int paramInt)
    throws Exception;
  
  public abstract List<Customerqqs> findCustomerqqbydisplay(int display) throws Exception;
}

