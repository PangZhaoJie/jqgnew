 package com.jqg.service.impl;
 
 import com.jqg.dao.OerationlogDao;
import com.jqg.dao.impl.OerationlogDaoImpl;
import com.jqg.pojo.Oerationlog;
import com.jqg.service.OerationlogService;

import java.util.List;
 
 public class OerationlogServiceImpl
   implements OerationlogService
 {
   OerationlogDao oerationlogDao = new OerationlogDaoImpl();
 
   public boolean createOerationlog(Oerationlog oerationlog) throws Exception
   {
     return this.oerationlogDao.addOerationlog(oerationlog);
   }
 
   public boolean updateOerationlog(Oerationlog oerationlog)
     throws Exception
   {
     return this.oerationlogDao.updateOerationlog(oerationlog);
   }
 
   public boolean deleteOerationlog(Oerationlog oerationlog)
     throws Exception
   {
     return this.oerationlogDao.deleteOerationlog(oerationlog);
   }
 
   public List<Oerationlog> findOerationlogs()
     throws Exception
   {
     return this.oerationlogDao.findOerationlogs();
   }
 
   public Oerationlog findOerationlogByoerationLogId(int oerationLogId)
     throws Exception
   {
     return this.oerationlogDao.findOerationlogByoerationLogId(oerationLogId);
   }
 
   public List<Oerationlog> findOerationlogsPage(int currentPage, int pageSize)
     throws Exception
   {
     return this.oerationlogDao.findOerationlogsPage(currentPage, pageSize);
   }
 
   public List<Oerationlog> findOerationlogsBysql(String sql)
     throws Exception
   {
     return this.oerationlogDao.findOerationlogsBysql(sql);
   }

public List<Oerationlog> findByManager(int managerId) throws Exception {
	return this.oerationlogDao.findByManager(managerId);
}
 }

