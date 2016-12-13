 package com.jqg.service.impl;
 
 import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.jqg.dao.TenderDao;
import com.jqg.dao.impl.TenderDaoImpl;
import com.jqg.pojo.Rank;
import com.jqg.pojo.Tender;
import com.jqg.pojo.Uservip;
import com.jqg.service.TenderService;
import com.jqg.session.factory.HibernateSessionFactory;
 
 public class TenderServiceImpl
   implements TenderService
 {
   TenderDao tenderDao = new TenderDaoImpl();
 
   public boolean addTender(Tender tender) throws Exception
   {
     return this.tenderDao.addTender(tender);
   }
 
   public boolean updateTender(Tender tender)
     throws Exception
   {
     return this.tenderDao.updateTender(tender);
   }
 
   public boolean deleteTender(Tender tender)
     throws Exception
   {
     return this.tenderDao.deleteTender(tender);
   }
 
   public List<Tender> findTenders()
     throws Exception
   {
     return this.tenderDao.findTenders();
   }
 
   public Tender findTenderById(Integer tenderWordsId)
     throws Exception
   {
     return this.tenderDao.findTenderById(tenderWordsId);
   }
 
   public List<Tender> findTendersBylssuingId(int lssuingId)
     throws Exception
   {
     return this.tenderDao.findTendersBylssuingId(lssuingId);
   }
 
   public List<Tender> findTendersBylssuingIdPage(String sql)
     throws Exception
   {
     return this.tenderDao.findTendersBylssuingIdPage(sql);
   }
 
   public List<Tender> findTendersByuserID(int userId)
     throws Exception
   {
     return this.tenderDao.findTendersByuserID(userId);
   }
 
   public List<Tender> findTendersByuserIdCategory(int userId, int state)
     throws Exception
   {
     return this.tenderDao.findTendersByuserIdCategory(userId, state);
   }
 
   public List<Tender> findTendersByuserIdCategoryPage(int userId, int state, int currentPage, int pageSize)
     throws Exception
   {
     return this.tenderDao.findTendersByuserIdCategoryPage(userId, state, currentPage, pageSize);
   }
 
   public List<Tender> findTendersByuser(int userId)
     throws Exception
   {
     return this.tenderDao.findTendersByuser(userId);
   }

	public List<Rank> findTendersByDate(Timestamp startDate, Timestamp endDate)
			throws Exception {
		
		return this.tenderDao.findTendersByDate(startDate, endDate);
	}

	public List<Tender> finTendersBystate(int userId, int state) throws Exception {
		// TODO Auto-generated method stub
		return tenderDao.finTendersBystate(userId, state);
	}

	public List<Tender> finTendersBystatePage(int userId, int state, int page,
			int pagesize) throws Exception {
		// TODO Auto-generated method stub
		return tenderDao.finTendersBystatePage(userId, state,page,pagesize);
	}

	public List<Tender> findTenderBySql(String sql) throws Exception {
		// TODO Auto-generated method stub
		return tenderDao.findTenderBySql(sql);
	}
	
	  //通过函数查找所有数据
	  public  List<Tender> findTendersByID(int id)
			    throws Exception{
		  return tenderDao.findTendersByID(id);
	  }
	  
	  //根据函数查找信息并分页
	 public  List<Tender> findTendersByFunPage(int paramInt1,
				int paramInt2, int paramInt3) throws Exception{
	
		 return tenderDao.findTendersByFunPage(paramInt1,paramInt2,paramInt3);
	 }

	public List<Tender> findTenderByHql(String sql, Integer page,
			Integer pageSize) throws Exception {
		return tenderDao.findTenderByHql(sql,page,pageSize);
	}
 }

