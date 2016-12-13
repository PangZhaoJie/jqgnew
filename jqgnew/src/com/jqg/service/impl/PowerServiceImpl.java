 package com.jqg.service.impl;
 
 import com.jqg.dao.PowerDao;
import com.jqg.dao.impl.PowerDaoImpl;
import com.jqg.pojo.Power;
import com.jqg.service.PowerService;

import java.util.List;
 
 public class PowerServiceImpl
   implements PowerService
 {
	   PowerDao powerDao = new PowerDaoImpl();
	   
	   public boolean addPower(Power power) throws Exception
	   {
	     return this.powerDao.addPower(power);
	   }
	 
	   public boolean updatePower(Power power)
	     throws Exception
	   {
	     return this.powerDao.updatePower(power);
	   }
	 
	   public boolean deletePower(Power power)
	     throws Exception
	   {
	     return this.powerDao.deletePower(power);
	   }
	 
	   public List<Power> findPowers()
	     throws Exception
	   {
	     return this.powerDao.findPowers();
	   }
	 
	   public Power findPowerByPowerId(Integer powerId)
	     throws Exception
	   {
	     return this.powerDao.findPowerByPowerId(powerId);
	   }
	 
	   public List<Power> findPowersByMeuAndRole(Integer menuId, Integer roleId)
	     throws Exception
	   {
	     return this.powerDao.findPowersByMeuAndRole(menuId, roleId);
	   }
	 
	   public List<Power> findPowersBySql(String sql)
	     throws Exception
	   {
	     return this.powerDao.findPowersBySql(sql);
	   }
 }

