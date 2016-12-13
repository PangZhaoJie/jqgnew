 package com.jqg.dao.impl;
 
 import com.jqg.dao.UserLogDao;
import com.jqg.pojo.UserLog;
import com.jqg.session.factory.HibernateSessionFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 public class UserLogDaoImpl
   implements UserLogDao
 {
   public boolean addUserLog(UserLog userLog)
     throws Exception
   {
     Session session = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       session.save(userLog);
       flag = true;
     } catch (Exception e) {
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean updateUserLog(UserLog userLog)
     throws Exception
   {
     Session session = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       session.merge(userLog);
       flag = true;
     } catch (Exception e) {
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public boolean deleteUserLog(UserLog userLog)
     throws Exception
   {
     Session session = null;
     boolean flag = false;
     try {
       session = HibernateSessionFactory.getSession();
       session.delete(userLog);
       flag = true;
     } catch (Exception e) {
		throw e;
	}finally{
		 
	}
     return flag;
   }
 
   public List<UserLog> findUserLogs()
     throws Exception
   {
     Session session = null;
     List userLogs = new ArrayList();
     try {
       session = HibernateSessionFactory.getSession();
       Query query = session.createQuery("from UserLog");
       userLogs = query.list();
     } catch (Exception e) {
		throw e;
	}finally{
		 
	}
     return userLogs;
   }
 
   public UserLog findUserLogByUserLogId(int userLogId)
     throws Exception
   {
     Session session = null;
     UserLog userLog = null;
     try {
       session = HibernateSessionFactory.getSession();
       Query query = session.createQuery("from UserLog r where r.userLogId=?");
       query.setInteger(0, userLogId);
       userLog = (UserLog)query.uniqueResult();
     } catch (Exception e) {
		throw e;
	}finally{
		 
	}
     return userLog;
   }

public List<UserLog> findUserLogByUserId(int userId) throws Exception {
	  Session session = null;
	     List userLogs = new ArrayList();
	     try {
	       session = HibernateSessionFactory.getSession();
	       Query query = session.createQuery("from UserLog u where u.uservip.userId=?");
	       query.setInteger(0, userId);
	       userLogs = query.list();
	     } catch (Exception e) {
			throw e;
		}finally{
			 
		}
	     return userLogs;
}
 
 }

