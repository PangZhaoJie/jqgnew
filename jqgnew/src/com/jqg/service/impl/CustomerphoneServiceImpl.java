 package com.jqg.service.impl;
 
 import com.jqg.dao.CustomerphoneDao;
import com.jqg.dao.impl.CustomerphoneDaoImpl;
import com.jqg.pojo.Customerphone;
import com.jqg.service.CustomerphoneService;

import java.util.List;
 
 public class CustomerphoneServiceImpl
   implements CustomerphoneService
 {
   CustomerphoneDao customerphoneDao = new CustomerphoneDaoImpl();
 
   public boolean createCustomerphone(Customerphone customerphone)
     throws Exception
   {
     return this.customerphoneDao.addCustomerphone(customerphone);
   }
 
   public boolean updateCustomerphone(Customerphone customerphone)
     throws Exception
   {
     return this.customerphoneDao.updateCustomerphone(customerphone);
   }
 
   public boolean deleteCustomerphone(Customerphone customerphone)
     throws Exception
   {
     return this.customerphoneDao.deleteCustomerphone(customerphone);
   }
 
   public List<Customerphone> findCustomerphones()
     throws Exception
   {
     return this.customerphoneDao.findCustomerphones();
   }
 
   public Customerphone findCustomerphoneBycustomerphoneId(int customerphoneId)
     throws Exception
   {
     return this.customerphoneDao.findCustomerphoneBycustomerphoneId(customerphoneId);
   }

public Customerphone findLastCustomerphone() throws Exception {
	
	 return this.customerphoneDao.findLastCustomerphone();
}
 }

