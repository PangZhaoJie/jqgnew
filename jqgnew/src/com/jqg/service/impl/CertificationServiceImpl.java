 package com.jqg.service.impl;
 
 import com.jqg.dao.CertificationDao;
import com.jqg.dao.impl.CertificationDaoIpml;
import com.jqg.pojo.Certification;
import com.jqg.service.CertificationService;

import java.util.List;
 
 public class CertificationServiceImpl
   implements CertificationService
 {
   CertificationDao certificationDao = new CertificationDaoIpml();
 
   public boolean createCertification(Certification certification) throws Exception
   {
     return this.certificationDao.addCertification(certification);
   }
 
   public boolean updateCertification(Certification certification)
     throws Exception
   {
     return this.certificationDao.updateCertification(certification);
   }
 
   public boolean deleteCertification(Certification certification)
     throws Exception
   {
     return this.certificationDao.deleteCertification(certification);
   }
 
   public List<Certification> findCertifications()
     throws Exception
   {
     return this.certificationDao.findCertifications();
   }
 
   public Certification findCertificationByCertificationId(int certificationId)
     throws Exception
   {
     return this.certificationDao.findCertificationByCertificationId(certificationId);
   }
 
   public Certification findCertificationByUserId(int userId)
     throws Exception
   {
     return this.certificationDao.findCertificationByUserId(userId);
   }
 
   public List<Certification> findCertificationsBysql(String sql)
     throws Exception
   {
     return this.certificationDao.findCertificationsBysql(sql);
   }
 }

