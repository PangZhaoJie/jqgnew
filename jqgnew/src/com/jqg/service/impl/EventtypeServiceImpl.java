 package com.jqg.service.impl;
 
 import com.jqg.dao.EventtypeDao;
import com.jqg.dao.impl.EventtypeDaoIpml;
import com.jqg.pojo.Eventtype;
import com.jqg.service.EventtypeService;

import java.util.List;
 
 public class EventtypeServiceImpl
   implements EventtypeService
 {
   EventtypeDao eventtypeDao = new EventtypeDaoIpml();
 
   public boolean createEventtype(Eventtype eventtype) throws Exception
   {
     return this.eventtypeDao.addEventtype(eventtype);
   }
 
   public boolean updateEventtype(Eventtype eventtype)
     throws Exception
   {
     return this.eventtypeDao.updateEventtype(eventtype);
   }
 
   public boolean deleteEventtype(Eventtype eventtype)
     throws Exception
   {
     return this.eventtypeDao.deleteEventtype(eventtype);
   }
 
   public List<Eventtype> findEventtypes()
     throws Exception
   {
     return this.eventtypeDao.findEventtypes();
   }
 
   public Eventtype findEventtypeById(int eventTypeId)
     throws Exception
   {
     return this.eventtypeDao.findEventtypeById(eventTypeId);
   }
 }

