 package com.jqg.service.impl;
 
 import com.jqg.dao.WebsiteDao;
import com.jqg.dao.impl.WebsiteDaoImpl;
import com.jqg.pojo.Website;
import com.jqg.service.WebsiteService;

import java.util.List;
 
 public class WebsiteServiceImpl
   implements WebsiteService
 {
   WebsiteDao websiteDao = new WebsiteDaoImpl();
 
   public boolean createWebsite(Website website) throws Exception
   {
     return this.websiteDao.addWebsite(website);
   }
 
   public boolean updateWebsite(Website website)
     throws Exception
   {
     return this.websiteDao.updateWebsite(website);
   }
 
   public boolean deleteWebsite(Website website)
     throws Exception
   {
     return this.websiteDao.deleteWebsite(website);
   }
 
   public List<Website> findWebsites()
     throws Exception
   {
     return this.websiteDao.findWebsites();
   }
 
   public Website findWebsiteBywebsiteId(int websiteId)
     throws Exception
   {
     return this.websiteDao.findWebsiteBywebsiteId(websiteId);
   }
 }

