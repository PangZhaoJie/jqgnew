package com.jqg.dao;

import com.jqg.pojo.Uservip;

import java.util.List;

public abstract interface UservipDao {
	public abstract Integer addUservip(Uservip paramUservip) throws Exception;

	public abstract boolean updateUservip(Uservip paramUservip)
			throws Exception;

	public abstract boolean deleteUservip(Uservip paramUservip)
			throws Exception;

	public abstract List<Uservip> findUservips() throws Exception;

	public abstract List<Uservip> findUservipsBysql(String paramString)
			throws Exception;

	public abstract List<Uservip> findUservipsPage(int paramInt1, int paramInt2)
			throws Exception;

	public abstract Uservip findUservipByUserid(int paramInt) throws Exception;

	public abstract Uservip findUservipByPasswordAndUserName(
			String paramString1, String paramString2) throws Exception;

	public abstract Uservip findUservipByPayPwdAndUserName(String paramString1,
			String paramString2) throws Exception;
	public abstract Uservip findUservipByPayPwdAndUserID(String paypwd, int id)
		    throws Exception;
	public abstract Uservip findUservipByMailAndPassword(String paramString1,
			String paramString2) throws Exception;

	public abstract String findUservipByUserName(String paramString)
			throws Exception;

	public abstract String findUservipByMail(String paramString)
			throws Exception;

	public abstract List<Uservip> findUservipsByUserId(int paramInt)
			throws Exception;

	public abstract List<Uservip> findUservipsByUserIdPage(int paramInt1,
			int paramInt2, int paramInt3) throws Exception;

	public abstract Uservip findUservipByEmail(String paramString)
			throws Exception;
	public abstract Uservip findUservipByIdAndUserNameAndEmail(int id, String username) throws Exception;
	
	public abstract List<Uservip> findUservips(int paramInt)
			throws Exception;
	public abstract List<Uservip> findUservipsByFunPage(int paramInt1,
			int paramInt2, int paramInt3) throws Exception;
	
	//查询所有是vip的用户
	public abstract List<Uservip> findVIP() throws Exception;
	
	
//  定时任务使用	
	public abstract List<Uservip> findVIPJob()throws Exception;
	public abstract boolean updateUservipJob(Uservip paramUservip) throws Exception;
//	结束 

	public abstract int findUservipBytoatl()throws Exception;
	
	public abstract int findCount(String sql) throws Exception;
}
