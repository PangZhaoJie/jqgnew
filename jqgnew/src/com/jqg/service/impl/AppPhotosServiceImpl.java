package com.jqg.service.impl;
import java.util.List;

import com.jqg.dao.AppPhotosDao;
import com.jqg.dao.impl.AppPhotosDaoImpl;
import com.jqg.pojo.AppPhotos;
import com.jqg.service.AppPhotosService;
public class AppPhotosServiceImpl implements AppPhotosService {
	AppPhotosDao appPhotosDao = new AppPhotosDaoImpl();
	public List<AppPhotos> findPhotos() throws Exception {
		return appPhotosDao.findPhotos();
	}
	public List<AppPhotos> findPhotosByPage(int currentPage, int pageSize) {
		return appPhotosDao.findPhotosByPage(currentPage, pageSize);
	}
	public boolean addAppPhotos(AppPhotos appPhoto) throws Exception {
		return appPhotosDao.addAppPhotos(appPhoto);
	}
	public AppPhotos findPhotoByAppId(Integer appId) throws Exception {
		return appPhotosDao.findPhotoByAppId(appId);
	}
	public boolean updatePhoto(AppPhotos appPhoto) throws Exception {
		return appPhotosDao.updatePhoto(appPhoto);
	}
	public boolean deletePhotoByApp(AppPhotos appPhoto) throws Exception {
		return appPhotosDao.deletePhotoByApp(appPhoto);
	}
	public List<AppPhotos> findPhotosByType(int int1, int int2, int int3,int int4) throws Exception {
		return appPhotosDao.findPhotosByType(int1,int2,int3,int4);
	}
}
