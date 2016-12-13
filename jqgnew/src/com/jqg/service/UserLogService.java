package com.jqg.service;

import java.util.List;

import com.jqg.pojo.UserLog;

public abstract interface UserLogService
{
	public abstract boolean addUserLog(UserLog userLog) throws Exception;

	public abstract boolean updateUserLog(UserLog paramUserLog)
			throws Exception;

	public abstract boolean deleteUserLog(UserLog paramUserLog)
			throws Exception;

	/**
	 * 查询所有
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<UserLog> findUserLogs() throws Exception;

	/**
	 * 根据id查询
	 * 
	 * @param paramInt
	 * @return
	 * @throws Exception
	 */
	public abstract UserLog findUserLogByUserLogId(int id) throws Exception;

	/**
	 * 根据用户id查询
	 * 
	 * @param paramString
	 * @return
	 * @throws Exception
	 */
	public abstract List<UserLog> findUserLogByUserId(int userId)
			throws Exception;

}
