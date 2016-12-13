 package com.jqg.service.impl;
 
 import com.jqg.dao.CustomerqqsDao;
import com.jqg.dao.impl.CustomerqqsDaoImpl;
import com.jqg.pojo.Customerqqs;
import com.jqg.service.CustomerqqsService;

import java.util.List;
 
 public class CustomerqqsServiceImpl
   implements CustomerqqsService
 {
   CustomerqqsDao customerqqsDao = new CustomerqqsDaoImpl();
 
   public boolean createCustomerqqs(Customerqqs customerqqs) throws Exception
   {
     return this.customerqqsDao.addCustomerqqs(customerqqs);
   }
 
   public boolean updateCustomerqqs(Customerqqs customerqqs)
     throws Exception
   {
     return this.customerqqsDao.updateCustomerqqs(customerqqs);
   }
 
   public boolean deleteCustomerqqs(Customerqqs customerqqs)
     throws Exception
   {
     return this.customerqqsDao.deleteCustomerqqs(customerqqs);
   }
 
   public List<Customerqqs> findCustomerqqss()
     throws Exception
   {
     return this.customerqqsDao.findCustomerqqss();
   }
 
   public Customerqqs findCustomerqqsBycustomerQqsid(int customerQqsid)
     throws Exception
   {
     return this.customerqqsDao.findCustomerqqsBycustomerQqsid(customerQqsid);
   }

	
	public List<Customerqqs> findCustomerqqbydisplay(int display) throws Exception {
		// TODO Auto-generated method stub
		
		return this.customerqqsDao.findCustomerqqbydisplay(display);
	}
 }

