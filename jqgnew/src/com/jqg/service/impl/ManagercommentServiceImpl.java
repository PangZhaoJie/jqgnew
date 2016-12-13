 package com.jqg.service.impl;
 
 import com.jqg.dao.ManagercommentDao;
import com.jqg.dao.impl.ManagercommentDaoImpl;
import com.jqg.pojo.Managercomment;
import com.jqg.service.ManagercommentService;

import java.util.List;
 
 public class ManagercommentServiceImpl
   implements ManagercommentService
 {
   ManagercommentDao managercommentDao = new ManagercommentDaoImpl();
 
   public boolean addManagercomment(Managercomment managercomment) throws Exception
   {
     return this.managercommentDao.addManagercomment(managercomment);
   }
 
   public boolean updateManagercomment(Managercomment managercomment)
     throws Exception
   {
     return this.managercommentDao.updateManagercomment(managercomment);
   }
 
   public boolean deleteManagercomment(Managercomment managercomment)
     throws Exception
   {
     return this.managercommentDao.deleteManagercomment(managercomment);
   }
 
   public List<Managercomment> findManagercomments()
     throws Exception
   {
     return this.managercommentDao.findManagercomments();
   }
 
   public Managercomment findManagercommentById(Integer magcommentId)
     throws Exception
   {
     return this.managercommentDao.findManagercommentById(magcommentId);
   }
 }

