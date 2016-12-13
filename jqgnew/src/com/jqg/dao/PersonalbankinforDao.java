package com.jqg.dao;

import com.jqg.pojo.Personalbankinfor;

import java.util.List;

public abstract interface PersonalbankinforDao
{
  public abstract boolean addPersonalbankinfor(Personalbankinfor paramPersonalbankinfor)
    throws Exception;

  public abstract boolean updatePersonalbankinfor(Personalbankinfor paramPersonalbankinfor)
    throws Exception;

  public abstract boolean deletePersonalbankinfor(Personalbankinfor paramPersonalbankinfor)
    throws Exception;

  public abstract List<Personalbankinfor> findPersonalbankinfors()
    throws Exception;

  public abstract List<Personalbankinfor> findPersonalbankinforsByuser(int paramInt)
    throws Exception;

  public abstract List<Personalbankinfor> findPersonalbankinforsByuserPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract Personalbankinfor findPersonalbankinforById(int paramInt)
    throws Exception;
}
