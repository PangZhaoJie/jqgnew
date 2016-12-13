 package com.jqg.service.impl;
 
 import com.jqg.dao.PartnerDao;
import com.jqg.dao.impl.PartnerDaoIpml;
import com.jqg.pojo.Partner;
import com.jqg.service.PartnerService;

import java.util.List;
 
 public class PartnerServiceImpl
   implements PartnerService
 {
   PartnerDao partnerDao = new PartnerDaoIpml();
 
   public boolean createPartner(Partner partner) throws Exception
   {
     return this.partnerDao.addPartner(partner);
   }
 
   public boolean updatePartner(Partner partner)
     throws Exception
   {
     return this.partnerDao.updatePartner(partner);
   }
 
   public boolean deletePartner(Partner partner)
     throws Exception
   {
     return this.partnerDao.deletePartner(partner);
   }
 
   public List<Partner> findPartners()
     throws Exception
   {
     return this.partnerDao.findPartners();
   }
 
   public Partner findPartnerByPartnerId(int partnerId)
     throws Exception
   {
     return this.partnerDao.findPartnerByPartnerId(partnerId);
   }
 }

