 package com.jqg.service.impl;
 
 import com.jqg.dao.UsercommentDao;
import com.jqg.dao.impl.UsercommentDaoImpl;
import com.jqg.pojo.Usercomment;
import com.jqg.service.UsercommentService;

import java.util.List;
 
 public class UsercommentServiceImpl
   implements UsercommentService
 {
   UsercommentDao usercommentDao = new UsercommentDaoImpl();
 
   public boolean addUsercomment(Usercomment usercomment) throws Exception
   {
     return this.usercommentDao.addUsercomment(usercomment);
   }
 
   public boolean updateUsercomment(Usercomment usercomment)
     throws Exception
   {
     return this.usercommentDao.updateUsercomment(usercomment);
   }
 
   public boolean deleteUsercomment(Usercomment usercomment)
     throws Exception
   {
     return this.usercommentDao.deleteUsercomment(usercomment);
   }
 
   public List<Usercomment> findUsercomments()
     throws Exception
   {
     return this.usercommentDao.findUsercomments();
   }
 
   public Usercomment findUsercommentById(Integer commentId)
     throws Exception
   {
     return this.usercommentDao.findUsercommentById(commentId);
   }
 
   public List<Usercomment> findUsercommentByUserId(Integer askId, Integer firstResult, Integer maxResult)
     throws Exception
   {
     return this.usercommentDao.findUsercommentByUserId(askId, firstResult, maxResult);
   }
 
   public List<Usercomment> findUsercommentByPage(String sql)
     throws Exception
   {
     return this.usercommentDao.findUsercommentByPage(sql);
   }
 }

