 package com.jqg.service.impl;
 
 import com.jqg.dao.SmtpDao;
import com.jqg.dao.impl.SmtpDaoImpl;
import com.jqg.pojo.Smtp;
import com.jqg.service.SmtpService;

import java.util.List;
 
 public class SmtpServiceImpl
   implements SmtpService
 {
   SmtpDao smtpDao = new SmtpDaoImpl();
 
   public boolean createSmtp(Smtp smtp) throws Exception
   {
     return this.smtpDao.addSmtp(smtp);
   }
 
   public boolean updateSmtp(Smtp smtp)
     throws Exception
   {
     return this.smtpDao.updateSmtp(smtp);
   }
 
   public boolean deleteSmtp(Smtp smtp)
     throws Exception
   {
     return this.smtpDao.deleteSmtp(smtp);
   }
 
   public List<Smtp> findSmtps()
     throws Exception
   {
     return this.smtpDao.findSmtps();
   }
 
   public Smtp findSmtpBysmtpid(int smtpid)
     throws Exception
   {
     return this.smtpDao.findSmtpBysmtpid(smtpid);
   }
 }

