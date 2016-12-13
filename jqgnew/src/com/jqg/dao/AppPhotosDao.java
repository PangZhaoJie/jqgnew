package com.jqg.dao;

import java.util.List;

import com.jqg.pojo.AppPhotos;

public abstract interface AppPhotosDao {
	
	public abstract List<AppPhotos> findPhotos() throws Exception;

	public abstract List<AppPhotos> findPhotosByPage(int currentPage,int pageSize);

	public abstract boolean addAppPhotos(AppPhotos appPhoto)throws Exception;

	public abstract AppPhotos findPhotoByAppId(Integer appId)throws Exception;

	public abstract boolean updatePhoto(AppPhotos appPhoto)throws Exception;

	public abstract boolean deletePhotoByApp(AppPhotos appPhoto)throws Exception;

	public abstract List<AppPhotos> findPhotosByType(int int1, int int2,int int3, int int4)throws Exception;
}
