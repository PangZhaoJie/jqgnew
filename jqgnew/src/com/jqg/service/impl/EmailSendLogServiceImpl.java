 package com.jqg.service.impl;
 
 import com.jqg.dao.EmailSendLogDao;
import com.jqg.dao.impl.EmailSendLogDaoIpml;
import com.jqg.pojo.EmailSendLog;
import com.jqg.service.EmailSendLogService;

import java.util.List;
 
 public class EmailSendLogServiceImpl
   implements EmailSendLogService
 {
   EmailSendLogDao emailSendLog = new EmailSendLogDaoIpml();
 
   public boolean createEmailSendLog(EmailSendLog smsSendLog) throws Exception
   {
     return this.emailSendLog.addEmailSendLog(smsSendLog);
   }
 
   public boolean updateEmailSendLog(EmailSendLog smsSendLog)
     throws Exception
   {
     return this.emailSendLog.updateEmailSendLog(smsSendLog);
   }
 
   public boolean deleteEmailSendLog(EmailSendLog smsSendLog)
     throws Exception
   {
     return this.emailSendLog.deleteEmailSendLog(smsSendLog);
   }
 
   public List<EmailSendLog> findEmailSendLogs()
     throws Exception
   {
     return this.emailSendLog.findEmailSendLogs();
   }
 
   public EmailSendLog findEmailSendLogByEmailSendLogId(int emailSendLog)
     throws Exception
   {
     return this.emailSendLog.findEmailSendLogByEmailSendLogId(emailSendLog);
   }
 
   public List<EmailSendLog> findEmailSendLogsByUserId(int userId)
     throws Exception
   {
     return this.emailSendLog.findEmailSendLogsByUserId(userId);
   }
 
   public List<EmailSendLog> findEmailSendLogsByUserIdPage(String sql)
     throws Exception
   {
     return this.emailSendLog.findEmailSendLogsByUserIdPage(sql);
   }
 }

