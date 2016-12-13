package com.jqg.service.impl;

import com.jqg.dao.AreaDao;
import com.jqg.dao.impl.AreaDaoImpl;
import com.jqg.pojo.Area;
import com.jqg.service.AreaService;

import java.util.List;

public class AreaServiceImpl implements AreaService {
	AreaDao areaDao = new AreaDaoImpl();

	public boolean createArea(Area area) throws Exception {
		return this.areaDao.addArea(area);
	}

	public boolean updateArea(Area area) throws Exception {
		return this.areaDao.updateArea(area);
	}

	public boolean deleteArea(Area area) throws Exception {
		return this.areaDao.deleteArea(area);
	}

	public List<Area> findAreas() throws Exception {
		return this.areaDao.findAreas();
	}

	public Area findAreaByAreaId(int areaId) throws Exception {
		return this.areaDao.findAreaByAreaId(areaId);
	}

	public List<Area> findAreasByParentid(int parentId) throws Exception {
		// TODO Auto-generated method stub
		return this.areaDao.findAreasByParentid(parentId);
	}
}
