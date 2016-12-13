 package com.jqg.service.impl;
 
 import com.jqg.dao.RequestquotaDao;
import com.jqg.dao.impl.RequestquotaDaoImpl;
import com.jqg.pojo.Requestquota;
import com.jqg.service.RequestquotaService;

import java.util.List;
 
 public class RequestquotaServiceImpl
   implements RequestquotaService
 {
   RequestquotaDao requestquotaDao = new RequestquotaDaoImpl();
 
   public boolean createRequestquota(Requestquota requestquota) throws Exception
   {
     return this.requestquotaDao.addRequestquota(requestquota);
   }
 
   public boolean updateRequestquota(Requestquota requestquota)
     throws Exception
   {
     return this.requestquotaDao.updateRequestquota(requestquota);
   }
 
   public boolean deleteRequestquota(Requestquota requestquota)
     throws Exception
   {
     return this.requestquotaDao.deleteRequestquota(requestquota);
   }
 
   public List<Requestquota> findRequestquotas()
     throws Exception
   {
     return this.requestquotaDao.findRequestquotas();
   }
 
   public Requestquota findRequestquotaByrequestQuotaId(int requestQuotaId)
     throws Exception
   {
     return this.requestquotaDao.findRequestquotaByrequestQuotaId(requestQuotaId);
   }
 
   public List<Requestquota> findRequestquotasByUserId(int userId)
     throws Exception
   {
     return this.requestquotaDao.findRequestquotasByUserId(userId);
   }
 
   public List<Requestquota> findRequestquotasByUserIdPage(String sql)
     throws Exception
   {
     return this.requestquotaDao.findRequestquotasByUserIdPage(sql);
   }
 }

