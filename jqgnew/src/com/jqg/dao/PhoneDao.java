package com.jqg.dao;

import com.jqg.pojo.Phone;

import java.util.List;

public abstract interface PhoneDao
{
  public abstract boolean addPhone(Phone paramPhone)
    throws Exception;

  public abstract boolean updatePhone(Phone paramPhone)
    throws Exception;

  public abstract boolean deletePhone(Phone paramPhone)
    throws Exception;

  public abstract List<Phone> findPhones()
    throws Exception;

  public abstract Phone findPhoneByphoneId(int paramInt)
    throws Exception;

  public abstract Phone findPhoneByOpen()
    throws Exception;
}
