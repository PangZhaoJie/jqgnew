 package com.jqg.service.impl;
 
 import com.jqg.dao.InboxDao;
import com.jqg.dao.impl.InboxDaoIpml;
import com.jqg.pojo.Inbox;
import com.jqg.service.InboxService;

import java.util.List;
 
 public class InboxServiceImpl
   implements InboxService
 {
   InboxDao inboxDao = new InboxDaoIpml();
 
   public boolean createInbox(Inbox inbox) throws Exception
   {
     return this.inboxDao.addInbox(inbox);
   }
 
   public boolean updateInbox(Inbox inbox)
     throws Exception
   {
     return this.inboxDao.updateInbox(inbox);
   }
 
   public boolean deleteInbox(Inbox inbox)
     throws Exception
   {
     return this.inboxDao.deleteInbox(inbox);
   }
 
   public List<Inbox> findInboxs()
     throws Exception
   {
     return this.inboxDao.findInboxs();
   }
 
   public Inbox findInboxByInboxId(int inboxId)
     throws Exception
   {
     return this.inboxDao.findInboxByInboxId(inboxId);
   }
 
   public List<Inbox> findInboxsByUserId(int userId)
     throws Exception
   {
     return this.inboxDao.findInboxsByUserId(userId);
   }
 
   public List<Inbox> findInboxsByUserIdPage(String sql)
     throws Exception
   {
     return this.inboxDao.findInboxsByUserIdPage(sql);
   }
 }

