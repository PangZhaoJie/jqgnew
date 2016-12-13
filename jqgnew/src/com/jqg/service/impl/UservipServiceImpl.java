package com.jqg.service.impl;

import com.jqg.dao.UservipDao;
import com.jqg.dao.impl.UservipDaoImpl;
import com.jqg.pojo.Uservip;
import com.jqg.service.UservipService;

import java.util.List;

public class UservipServiceImpl implements UservipService {
	UservipDao uservipDao = new UservipDaoImpl();

	public Integer createUservip(Uservip uservip) throws Exception {
		return this.uservipDao.addUservip(uservip);
	}

	public boolean deleteUservip(Uservip uservip) throws Exception {
		return this.uservipDao.deleteUservip(uservip);
	}

	public Uservip findUservipByUserid(int userid) throws Exception {
		return this.uservipDao.findUservipByUserid(userid);
	}

	public Uservip findUservipByMailAndPassword(String mail, String password)
			throws Exception {
		return this.uservipDao.findUservipByMailAndPassword(mail, password);
	}

	public Uservip findUservipByPasswordAndUserName(String password,
			String userName) throws Exception {
		return this.uservipDao.findUservipByPasswordAndUserName(password,
				userName);
	}

	public List<Uservip> findUservips() throws Exception {
		return this.uservipDao.findUservips();
	}

	public boolean updateUservip(Uservip uservip) throws Exception {
		return this.uservipDao.updateUservip(uservip);
	}

	public String findUservipByUserName(String userName) throws Exception {
		return this.uservipDao.findUservipByUserName(userName);
	}

	public String findUservipByMail(String mail) throws Exception {
		return this.uservipDao.findUservipByMail(mail);
	}

	public Uservip findUservipByPayPwdAndUserName(String payPwd, String userName)
			throws Exception {
		return this.uservipDao.findUservipByPayPwdAndUserName(payPwd, userName);
	}

	public Uservip findUservipByPayPwdAndUserID(String payPwd, int id)
			throws Exception {
		return this.uservipDao.findUservipByPayPwdAndUserID(payPwd, id);
	}

	public List<Uservip> findUservipsByUserId(int userId) throws Exception {
		return this.uservipDao.findUservipsByUserId(userId);
	}

	public List<Uservip> findUservipsByUserIdPage(int userId, int currentPage,
			int pageSize) throws Exception {
		return this.uservipDao.findUservipsByUserIdPage(userId, currentPage,
				pageSize);
	}

	public List<Uservip> findUservipsPage(int currentPage, int pageSize)
			throws Exception {
		return this.uservipDao.findUservipsPage(currentPage, pageSize);
	}

	public List<Uservip> findUservipsBysql(String sql) throws Exception {
		return this.uservipDao.findUservipsBysql(sql);
	}

	public Uservip findUservipByEmail(String paramString) throws Exception {

		return this.uservipDao.findUservipByEmail(paramString);
	}

	public Uservip findUservipByIdAndUserNameAndEmail(int id, String username)
			throws Exception {

		return this.uservipDao.findUservipByIdAndUserNameAndEmail(id, username);
	}

	public List<Uservip> findUservips(int userId) throws Exception {
		return this.uservipDao.findUservips(userId);
	}

	public List<Uservip> findUservipsByFunPage(int userId, int currentPage,
			int pageSize) throws Exception {
		return this.uservipDao.findUservipsByFunPage(userId, currentPage,
				pageSize);
	}
	
	public  List<Uservip> findVIP() throws Exception{
		
		return this.uservipDao.findVIP();
	}
	public  List<Uservip> findVIPJob()throws Exception{
		return this.uservipDao.findVIPJob();
	}
	public  boolean updateUservipJob(Uservip paramUservip)throws Exception {
		return this.uservipDao.updateUservipJob(paramUservip);
	}

	public int findUservipBytoatl() throws Exception {
		return this.uservipDao.findUservipBytoatl();
	}
	
	public int findCount(String sql) throws Exception{
		return this.uservipDao.findCount(sql);
	}

}
