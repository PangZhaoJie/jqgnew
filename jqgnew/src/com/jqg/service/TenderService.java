package com.jqg.service;

import java.sql.Timestamp;
import java.util.List;

import com.jqg.pojo.Rank;
import com.jqg.pojo.Tender;

public abstract interface TenderService
{
  public abstract boolean addTender(Tender paramTender)
    throws Exception;

  public abstract boolean updateTender(Tender paramTender)
    throws Exception;

  public abstract boolean deleteTender(Tender paramTender)
    throws Exception;

  public abstract List<Tender> findTenders()
    throws Exception;

  public abstract Tender findTenderById(Integer paramInteger)
    throws Exception;

  public abstract List<Tender> findTendersBylssuingId(int paramInt)
    throws Exception;

  public abstract List<Tender> findTendersBylssuingIdPage(String paramString)
    throws Exception;

  public abstract List<Tender> findTendersByuserID(int paramInt)
    throws Exception;

  public abstract List<Tender> findTendersByuserIdCategory(int paramInt1, int paramInt2)
    throws Exception;

  public abstract List<Tender> findTendersByuserIdCategoryPage(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    throws Exception;

  public abstract List<Tender> findTendersByuser(int paramInt)
    throws Exception;
  
  public abstract List<Rank> findTendersByDate(Timestamp startDate,Timestamp endDate)
		    throws Exception;
  
  /**
   * 查询回收中和已回收投资的信息
   * @param userId
   * @param state
   * @return
   */
  public abstract List<Tender> finTendersBystate(int userId,int state)throws Exception;
  public abstract List<Tender> finTendersBystatePage(int userId,int state,int page,int pagesize)throws Exception;
 
  public abstract List<Tender> findTenderBySql(String sql)
		    throws Exception;
  //通过函数查找所有数据
  public abstract List<Tender> findTendersByID(int id)
		    throws Exception;
  
  //根据函数查找信息并分页
 public abstract List<Tender> findTendersByFunPage(int paramInt1,
			int paramInt2, int paramInt3) throws Exception;
/**
 * 债权转让列表查询
 * */

public abstract List<Tender> findTenderByHql(String sql, Integer page, Integer pageSize) throws Exception;
	}

