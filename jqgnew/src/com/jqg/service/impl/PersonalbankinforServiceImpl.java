 package com.jqg.service.impl;
 
 import com.jqg.dao.PersonalbankinforDao;
import com.jqg.dao.impl.PersonalbankinforDaoIpml;
import com.jqg.pojo.Personalbankinfor;
import com.jqg.service.PersonalbankinforService;

import java.util.List;
 
 public class PersonalbankinforServiceImpl
   implements PersonalbankinforService
 {
   PersonalbankinforDao personalbankinforDao = new PersonalbankinforDaoIpml();
 
   public boolean createPersonalbankinfor(Personalbankinfor personalbankinfor) throws Exception
   {
     return this.personalbankinforDao.addPersonalbankinfor(personalbankinfor);
   }
 
   public boolean updatePersonalbankinfor(Personalbankinfor personalbankinfor)
     throws Exception
   {
     return this.personalbankinforDao.updatePersonalbankinfor(personalbankinfor);
   }
 
   public boolean deletePersonalbankinfor(Personalbankinfor personalbankinfor)
     throws Exception
   {
     return this.personalbankinforDao.deletePersonalbankinfor(personalbankinfor);
   }
 
   public List<Personalbankinfor> findPersonalbankinfors()
     throws Exception
   {
     return this.personalbankinforDao.findPersonalbankinfors();
   }
 
   public Personalbankinfor findPersonalbankinforById(int personalBankInforId)
     throws Exception
   {
     return this.personalbankinforDao.findPersonalbankinforById(personalBankInforId);
   }
 
   public List<Personalbankinfor> findPersonalbankinforsByuser(int userId)
     throws Exception
   {
     return this.personalbankinforDao.findPersonalbankinforsByuser(userId);
   }
 
   public List<Personalbankinfor> findPersonalbankinforsByuserPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     return this.personalbankinforDao.findPersonalbankinforsByuserPage(userId, currentPage, pageSize);
   }
 }

