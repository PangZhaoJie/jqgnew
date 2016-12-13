 package com.jqg.service.impl;
 
 import com.jqg.dao.BonusDao;
import com.jqg.dao.impl.BonusDaoImpl;
import com.jqg.pojo.Bonus;
import com.jqg.service.BonusService;

import java.util.List;
 
 public class BonusServiceImpl
   implements BonusService
 {
   BonusDao bonusDao = new BonusDaoImpl();
 
   public boolean createBonus(Bonus bonus) throws Exception
   {
     return this.bonusDao.addBonus(bonus);
   }
 
   public boolean updateBonus(Bonus bonus)
     throws Exception
   {
     return this.bonusDao.updateBonus(bonus);
   }
 
   public boolean deleteBonus(Bonus bonus)
     throws Exception
   {
     return this.bonusDao.deleteBonus(bonus);
   }
 
   public List<Bonus> findBonuss()
     throws Exception
   {
     return this.bonusDao.findBonuss();
   }
 
   public Bonus findBonusBybonusId(int bonusId)
     throws Exception
   {
     return this.bonusDao.findBonusBybonusId(bonusId);
   }
 
   public List<Bonus> findBonussByUserId(int userId)
     throws Exception
   {
     return this.bonusDao.findBonussByUserId(userId);
   }
 
   public List<Bonus> findBonussByUserIdPage(int userId, int currentPage, int pageSize)
     throws Exception
   {
     return this.bonusDao.findBonussByUserIdPage(userId, currentPage, pageSize);
   }
 
   public List<Bonus> findBonusBySql(String sql)
     throws Exception
   {
     return this.bonusDao.findBonusBySql(sql);
   }
 }

