package com.jqg.service.impl;

import com.jqg.dao.ManagerDao;
import com.jqg.dao.impl.ManagerDaoImpl;
import com.jqg.pojo.Manager;
import com.jqg.service.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
	ManagerDao managerDao = new ManagerDaoImpl();

	public boolean addManager(Manager manager) throws Exception {
		return this.managerDao.addManager(manager);
	}

	public boolean updateManager(Manager manager) throws Exception {
		return this.managerDao.updateManager(manager);
	}

	public boolean deleteManager(Manager manager) throws Exception {
		return this.managerDao.deleteManager(manager);
	}

	public List<Manager> findManagers() throws Exception {
		return this.managerDao.findManagers();
	}

	public Manager findManagerByManagerId(Integer managerId) throws Exception {
		return this.managerDao.findManagerByManagerId(managerId);
	}

	public Manager findManagerByLogin(String managerName, String password,
			String passwordWord) throws Exception {
		return this.managerDao.findManagerByLogin(managerName, password,
				passwordWord);
	}

	public Manager findManagerByname(String name) throws Exception {
		return this.managerDao.findManagerByname(name);
	}

	public List<Manager> findManagerBySql(String sql) throws Exception {
		return this.managerDao.findManagerBySql(sql);
	}

	public List<Manager> findManagerspage(int currentPage, int pageSize)
			throws Exception {
		return this.managerDao.findManagerspage(currentPage, pageSize);
	}

	public List<Manager> findManagers1(Integer isCustomer) throws Exception {
		return this.managerDao.findManagers1(isCustomer);
	}

	public List<Manager> findManagerspage1(int currentPage, int pageSize,
			Integer isCustomer) throws Exception {
		return this.managerDao.findManagerspage1(currentPage, pageSize,
				isCustomer);
	}
	//查询客服信息
	public List<Manager> findManagerByIsCustomer()throws Exception {
		return this.managerDao.findManagerByIsCustomer();
	}
}
