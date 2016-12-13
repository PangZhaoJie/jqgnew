 package com.jqg.service.impl;
 
 import com.jqg.dao.IntegralcategoryDao;
import com.jqg.dao.impl.IntegralcategoryDaoImpl;
import com.jqg.pojo.Integralcategory;
import com.jqg.service.IntegralcategoryService;

import java.util.List;
 
 public class IntegralcategoryServiceImpl
   implements IntegralcategoryService
 {
   IntegralcategoryDao integralcategoryDao = new IntegralcategoryDaoImpl();
 
   public boolean createIntegralcategory(Integralcategory integralcategory)
     throws Exception
   {
     return this.integralcategoryDao.addIntegralcategory(integralcategory);
   }
 
   public boolean updateIntegralcategory(Integralcategory integralcategory)
     throws Exception
   {
     return this.integralcategoryDao.updateIntegralcategory(integralcategory);
   }
 
   public boolean deleteIntegralcategory(Integralcategory integralcategory)
     throws Exception
   {
     return this.integralcategoryDao.deleteIntegralcategory(integralcategory);
   }
 
   public List<Integralcategory> findIntegralcategorys()
     throws Exception
   {
     return this.integralcategoryDao.findIntegralcategorys();
   }
 
   public Integralcategory findIntegralcategoryByintCategoryId(int intCategoryId)
     throws Exception
   {
     return this.integralcategoryDao.findIntegralcategoryByintCategoryId(intCategoryId);
   }
 }

