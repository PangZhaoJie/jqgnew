package com.jqg.service;

import com.jqg.pojo.Role;

import java.util.List;

public abstract interface RoleService {
	public abstract boolean addRole(Role paramRole) throws Exception;

	public abstract Integer createRole(Role paramRole) throws Exception;

	public abstract boolean updateRole(Role paramRole) throws Exception;

	public abstract boolean deleteRole(Role paramRole) throws Exception;

	public abstract List<Role> findRoles() throws Exception;

	public abstract Role findRoleByRoleId(Integer paramInteger)
			throws Exception;

	public abstract String findRoleByName(String paramString,
			Integer paramInteger) throws Exception;

	public abstract List<Role> findRolesBysql(String paramString)
			throws Exception;
}
