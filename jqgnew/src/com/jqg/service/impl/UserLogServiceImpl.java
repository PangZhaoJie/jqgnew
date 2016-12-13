 package com.jqg.service.impl;
 
 import java.util.List;

import com.jqg.dao.UserLogDao;
import com.jqg.dao.impl.UserLogDaoImpl;
import com.jqg.pojo.UserLog;
import com.jqg.service.UserLogService;
 
 public class UserLogServiceImpl
   implements UserLogService
 {
   UserLogDao recordDao = new UserLogDaoImpl();
 
   public boolean addUserLog(UserLog record) throws Exception
   {
     return this.recordDao.addUserLog(record);
   }
 
   public boolean updateUserLog(UserLog record)
     throws Exception
   {
     return this.recordDao.updateUserLog(record);
   }
 
   public boolean deleteUserLog(UserLog record)
     throws Exception
   {
     return this.recordDao.deleteUserLog(record);
   }
 
   public List<UserLog> findUserLogs()
     throws Exception
   {
     return this.recordDao.findUserLogs();
   }
 
   public UserLog findUserLogByUserLogId(int id)
     throws Exception
   {
     return this.recordDao.findUserLogByUserLogId(id);
   }
 
   public List<UserLog> findUserLogByUserId(int userId)
     throws Exception
   {
     return this.recordDao.findUserLogByUserId(userId);
   }
 
   
 }

