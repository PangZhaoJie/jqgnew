 package com.jqg.service.impl;
 
 import com.jqg.dao.FriendlinkDao;
import com.jqg.dao.impl.FriendlinkDaoImpl;
import com.jqg.pojo.Friendlink;
import com.jqg.service.FriendlinkService;

import java.util.List;
 
 public class FriendlinkServiceImpl
   implements FriendlinkService
 {
   FriendlinkDao friendlinkDao = new FriendlinkDaoImpl();
 
   public boolean addFriendlink(Friendlink friendlink) throws Exception
   {
     return this.friendlinkDao.addFriendlink(friendlink);
   }
 
   public boolean updateFriendlink(Friendlink friendlink)
     throws Exception
   {
     return this.friendlinkDao.updateFriendlink(friendlink);
   }
 
   public boolean deleteFriendlink(Friendlink friendlink)
     throws Exception
   {
     return this.friendlinkDao.deleteFriendlink(friendlink);
   }
 
   public List<Friendlink> findFriendlinks()
     throws Exception
   {
     return this.friendlinkDao.findFriendlinks();
   }
 
   public Friendlink findFriendlinkById(Integer friendLinkId)
     throws Exception
   {
     return this.friendlinkDao.findFriendlinkById(friendLinkId);
   }
 
   public List<Friendlink> findFriendlinksByIndex(String flag)
     throws Exception
   {
     return this.friendlinkDao.findFriendlinksByIndex(flag);
   }
 
   public List<Friendlink> findFriendlinkspage(int currentPage, int pageSize)
     throws Exception
   {
     return this.friendlinkDao.findFriendlinkspage(currentPage, pageSize);
   }
 }

