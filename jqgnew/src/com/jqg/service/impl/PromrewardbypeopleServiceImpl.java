 package com.jqg.service.impl;
 
 import com.jqg.dao.PromrewardbypeopleDao;
import com.jqg.dao.impl.PromrewardbypeopleDaoIpml;
import com.jqg.pojo.Promrewardbypeople;
import com.jqg.service.PromrewardbypeopleService;

import java.util.List;
 
 public class PromrewardbypeopleServiceImpl
   implements PromrewardbypeopleService
 {
   PromrewardbypeopleDao promrewardbypeopleDao = new PromrewardbypeopleDaoIpml();
 
   public boolean createPromrewardbypeople(Promrewardbypeople promrewardbypeople) throws Exception
   {
     return this.promrewardbypeopleDao.addPromrewardbypeople(promrewardbypeople);
   }
 
   public boolean updatePromrewardbypeople(Promrewardbypeople promrewardbypeople)
     throws Exception
   {
     return this.promrewardbypeopleDao.updatePromrewardbypeople(promrewardbypeople);
   }
 
   public boolean deletePromrewardbypeople(Promrewardbypeople promrewardbypeople)
     throws Exception
   {
     return this.promrewardbypeopleDao.deletePromrewardbypeople(promrewardbypeople);
   }
 
   public List<Promrewardbypeople> findPromrewardbypeoples()
     throws Exception
   {
     return this.promrewardbypeopleDao.findPromrewardbypeoples();
   }
 
   public Promrewardbypeople findPromrewardbypeopleById(int promRewardByPeopleId)
     throws Exception
   {
     return this.promrewardbypeopleDao.findPromrewardbypeopleById(promRewardByPeopleId);
   }
 
   public List<Promrewardbypeople> findPromrewardbypeoplesByUserId(int userId)
     throws Exception
   {
     return this.promrewardbypeopleDao.findPromrewardbypeoplesByUserId(userId);
   }
 
   public List<Promrewardbypeople> findPromrewardbypeoplesByUserIdPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     return this.promrewardbypeopleDao.findPromrewardbypeoplesByUserIdPage(userId, currentPage, pageSize);
   }
 
   public List<Promrewardbypeople> findPromrewardbySql(String sql)
     throws Exception
   {
     return this.promrewardbypeopleDao.findPromrewardbySql(sql);
   }
 }

