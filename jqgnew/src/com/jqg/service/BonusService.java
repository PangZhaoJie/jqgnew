package com.jqg.service;

import com.jqg.pojo.Bonus;

import java.util.List;

public abstract interface BonusService
{
  public abstract boolean createBonus(Bonus paramBonus)
    throws Exception;

  public abstract boolean updateBonus(Bonus paramBonus)
    throws Exception;

  public abstract boolean deleteBonus(Bonus paramBonus)
    throws Exception;

  public abstract List<Bonus> findBonuss()
    throws Exception;

  public abstract Bonus findBonusBybonusId(int paramInt)
    throws Exception;

  public abstract List<Bonus> findBonussByUserId(int paramInt)
    throws Exception;

  public abstract List<Bonus> findBonussByUserIdPage(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;

  public abstract List<Bonus> findBonusBySql(String paramString)
    throws Exception;
}
