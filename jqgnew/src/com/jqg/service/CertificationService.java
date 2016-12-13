package com.jqg.service;

import com.jqg.pojo.Certification;

import java.util.List;

public abstract interface CertificationService
{
  public abstract boolean createCertification(Certification paramCertification)
    throws Exception;

  public abstract boolean updateCertification(Certification paramCertification)
    throws Exception;

  public abstract boolean deleteCertification(Certification paramCertification)
    throws Exception;

  public abstract List<Certification> findCertifications()
    throws Exception;

  public abstract Certification findCertificationByCertificationId(int paramInt)
    throws Exception;

  public abstract Certification findCertificationByUserId(int paramInt)
    throws Exception;

  public abstract List<Certification> findCertificationsBysql(String paramString)
    throws Exception;
}