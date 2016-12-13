 package com.jqg.service.impl;
 
 import com.jqg.dao.PartnerdetailDao;
import com.jqg.dao.impl.PartnerdetailDaoIpml;
import com.jqg.pojo.Partnerdetail;
import com.jqg.service.PartnerdetailService;

import java.util.List;
 
 public class PartnerdetailServiceImpl
   implements PartnerdetailService
 {
   PartnerdetailDao partnerdetailDao = new PartnerdetailDaoIpml();
 
   public boolean createPartnerdetail(Partnerdetail partnerdetail) throws Exception
   {
     return this.partnerdetailDao.addPartnerdetail(partnerdetail);
   }
 
   public boolean updatePartnerdetail(Partnerdetail partnerdetail)
     throws Exception
   {
     return this.partnerdetailDao.updatePartnerdetail(partnerdetail);
   }
 
   public boolean deletePartnerdetail(Partnerdetail partnerdetail)
     throws Exception
   {
     return this.partnerdetailDao.deletePartnerdetail(partnerdetail);
   }
 
   public Partnerdetail findPartnerdetailByDetailId(int detailId)
     throws Exception
   {
     return this.partnerdetailDao.findPartnerdetailByDetailId(detailId);
   }
 
   public List<Partnerdetail> findPartnerdetails()
     throws Exception
   {
     return this.partnerdetailDao.findPartnerdetails();
   }
 }

