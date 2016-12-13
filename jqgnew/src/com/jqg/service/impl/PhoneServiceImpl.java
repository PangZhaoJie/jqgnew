 package com.jqg.service.impl;
 
 import com.jqg.dao.PhoneDao;
import com.jqg.dao.impl.PhoneDaoIpml;
import com.jqg.pojo.Phone;
import com.jqg.service.PhoneService;

import java.util.List;
 
 public class PhoneServiceImpl
   implements PhoneService
 {
   PhoneDao phoneDao = new PhoneDaoIpml();
 
   public boolean createPhone(Phone phone) throws Exception
   {
     return this.phoneDao.addPhone(phone);
   }
 
   public boolean updatePhone(Phone phone)
     throws Exception
   {
     return this.phoneDao.updatePhone(phone);
   }
 
   public boolean deletePhone(Phone phone)
     throws Exception
   {
     return this.phoneDao.deletePhone(phone);
   }
 
   public List<Phone> findPhones()
     throws Exception
   {
     return this.phoneDao.findPhones();
   }
 
   public Phone findPhoneByphoneId(int phoneId)
     throws Exception
   {
     return this.phoneDao.findPhoneByphoneId(phoneId);
   }
 
   public Phone findPhoneByOpen()
     throws Exception
   {
     return this.phoneDao.findPhoneByOpen();
   }
 }

