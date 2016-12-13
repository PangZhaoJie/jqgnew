 package com.jqg.service.impl;
 
 import com.jqg.dao.SmsSendLogDao;
import com.jqg.dao.impl.SmsSendLogDaoIpml;
import com.jqg.pojo.SmsSendLog;
import com.jqg.service.SmsSendLogService;

import java.util.List;
 
 public class SmsSendLogServiceImpl
   implements SmsSendLogService
 {
   SmsSendLogDao smsSendLog = new SmsSendLogDaoIpml();
 
   public boolean createSmsSendLog(SmsSendLog smsSendLog) throws Exception
   {
     return this.smsSendLog.addSmsSendLog(smsSendLog);
   }
 
   public boolean updateSmsSendLog(SmsSendLog smsSendLog)
     throws Exception
   {
     return this.smsSendLog.updateSmsSendLog(smsSendLog);
   }
 
   public boolean deleteSmsSendLog(SmsSendLog smsSendLog)
     throws Exception
   {
     return this.smsSendLog.deleteSmsSendLog(smsSendLog);
   }
 
   public List<SmsSendLog> findSmsSendLogs()
     throws Exception
   {
     return this.smsSendLog.findSmsSendLogs();
   }
 
   public SmsSendLog findSmsSendLogBySmsSendLogId(int smsSendLog)
     throws Exception
   {
     return this.smsSendLog.findSmsSendLogBySmsSendLogId(smsSendLog);
   }
 
   public List<SmsSendLog> findSmsSendLogsByUserId(int userId)
     throws Exception
   {
     return this.smsSendLog.findSmsSendLogsByUserId(userId);
   }
 
   public List<SmsSendLog> findSmsSendLogsByUserIdPage(String sql)
     throws Exception
   {
     return this.smsSendLog.findSmsSendLogsByUserIdPage(sql);
   }

 }

