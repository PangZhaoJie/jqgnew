package com.jqg.dao;

import com.jqg.pojo.Area;

import java.util.List;

public abstract interface AreaDao {
	public abstract boolean addArea(Area area) throws Exception;

	public abstract boolean updateArea(Area area) throws Exception;

	public abstract boolean deleteArea(Area area) throws Exception;

	public abstract List<Area> findAreas() throws Exception;

	public abstract Area findAreaByAreaId(int areaid) throws Exception;

	public abstract List<Area> findAreasByParentid(int parentId)
			throws Exception;

}
