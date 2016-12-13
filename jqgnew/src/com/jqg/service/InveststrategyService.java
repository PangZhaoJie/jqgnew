package com.jqg.service;

import com.jqg.pojo.Investstrategy;

import java.util.List;
/**
 * Ͷ�ʲ���service
 * @author Administrator
 *
 */
public abstract interface InveststrategyService
{
  public abstract boolean addInveststrategy(Investstrategy paramInveststrategy)
    throws Exception;

  public abstract boolean updateInveststrategy(Investstrategy paramInveststrategy)
    throws Exception;

  public abstract boolean deleteInveststrategy(Investstrategy paramInveststrategy)
    throws Exception;

  public abstract List<Investstrategy> findInveststrategys()
    throws Exception;

  public abstract Investstrategy findInveststrategyById(Integer paramInteger)
    throws Exception;

  public abstract List<Investstrategy> findInveststrategysByIdex()
    throws Exception;

  public abstract List<Investstrategy> findInveststrategyByType()
    throws Exception;

  public abstract List<Investstrategy> findInveststrategyByDate(int paramInt, String paramString)
    throws Exception;
/**
 * ������վ��Ͷ�ʲ���
 * @param paramString
 * @return
 * @throws Exception
 */
  public abstract List<Investstrategy> findInveststrategyByPage(String paramString)
    throws Exception;
}
