package com.jqg.dao;

import com.jqg.pojo.Record;
import com.jqg.pojo.Tender;
import com.jqg.pojo.UserLog;

import java.util.List;

public abstract interface UserLogDao
{
	public abstract boolean addUserLog(UserLog userLog) throws Exception;

	public abstract boolean updateUserLog(UserLog paramUserLog)
			throws Exception;

	public abstract boolean deleteUserLog(UserLog paramUserLog)
			throws Exception;

	/**
	 * ��ѯ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract List<UserLog> findUserLogs() throws Exception;

	/**
	 * ����id��ѯ
	 * 
	 * @param paramInt
	 * @return
	 * @throws Exception
	 */
	public abstract UserLog findUserLogByUserLogId(int id) throws Exception;

	/**
	 * �����û�id��ѯ
	 * 
	 * @param paramString
	 * @return
	 * @throws Exception
	 */
	public abstract List<UserLog> findUserLogByUserId(int userId)
			throws Exception;
}
