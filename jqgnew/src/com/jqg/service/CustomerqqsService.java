package com.jqg.service;

import com.jqg.pojo.Customerqqs;

import java.util.List;
/**
 * qq¿Í»§service
 * @author Administrator
 *
 */
public abstract interface CustomerqqsService
{
  public abstract boolean createCustomerqqs(Customerqqs paramCustomerqqs)
    throws Exception;

  public abstract boolean updateCustomerqqs(Customerqqs paramCustomerqqs)
    throws Exception;

  public abstract boolean deleteCustomerqqs(Customerqqs paramCustomerqqs)
    throws Exception;

  public abstract List<Customerqqs> findCustomerqqss()
    throws Exception;

  public abstract Customerqqs findCustomerqqsBycustomerQqsid(int paramInt)
    throws Exception;
  
  public abstract List<Customerqqs> findCustomerqqbydisplay(int display)throws Exception;
  
}
