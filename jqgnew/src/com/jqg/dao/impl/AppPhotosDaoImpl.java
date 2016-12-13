package com.jqg.dao.impl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import com.jqg.dao.AppPhotosDao;
import com.jqg.pojo.AppPhotos;
import com.jqg.session.factory.HibernateSessionFactory;
public class AppPhotosDaoImpl implements AppPhotosDao {
/**
 * 查找所有app图片
 * */
	public List<AppPhotos> findPhotos() throws Exception {
		Session session = null;
		List<AppPhotos> appPhoto = new ArrayList<AppPhotos>();
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from AppPhotos");
			appPhoto = query.list();
		} catch (Exception e) {
			throw e;
		}finally{
			
		}
		return appPhoto;
	}
/**
 * 
 * 查找所有app图片分页
 * */
	public List<AppPhotos> findPhotosByPage(int currentPage, int pageSize) {
		Session session = null;
		List<AppPhotos> appPhoto = new ArrayList<AppPhotos>();
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from AppPhotos a ORDER BY a.appId DESC");
			query.setFirstResult(currentPage);
			query.setMaxResults(pageSize);
			appPhoto = query.list();
		} catch (HibernateException e) {
			throw e;
		}finally{
			
		}
		return appPhoto;
	}
/**
 * 添加app图片
 * */
	public boolean addAppPhotos(AppPhotos appPhoto) throws Exception {
		Session session = null;
		boolean boo =false;
		try {
			session = HibernateSessionFactory.getSession();
			session.save(appPhoto);
			session.setFlushMode(FlushMode.COMMIT);
			boo = true;
		} catch (Exception e) {
			throw e;
		}finally{
			
		}
		return boo;
	}
	/**
	 * 根据id查找
	 * */
public AppPhotos findPhotoByAppId(Integer appId) throws Exception {
	Session session = null;
	AppPhotos appPhoto = null;
	try {
		session=HibernateSessionFactory.getSession();
		Query query = session.createQuery("from AppPhotos a where a.appId=?");
		query.setInteger(0, appId);
		appPhoto = (AppPhotos)query.uniqueResult();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw e;
	}finally{
		
	}
	return appPhoto;
}
/**
 * 更新app图片
 * */
	public boolean updatePhoto(AppPhotos appPhoto) throws Exception {
		Session session = null;
		boolean boo =false;
		try {
			session=HibernateSessionFactory.getSession();
			session.merge(appPhoto);
			session.setFlushMode(FlushMode.COMMIT);
			boo = true;
		} catch (Exception e) {
			throw e;
		}finally{
			
		}
		return boo;
	}
	/**
	 * 删除app图片
	 * */
	public boolean deletePhotoByApp(AppPhotos appPhoto) throws Exception {
		Session session = null;
		boolean boo  = false;
		try {
			session = HibernateSessionFactory.getSession();
			session.delete(appPhoto);
			session.setFlushMode(FlushMode.COMMIT);
			boo = true;
		} catch (Exception e) {
			throw e;
		}finally{
			
		}
		return boo;
	}
	public List<AppPhotos> findPhotosByType(int int1, int int2, int int3,int int4) throws Exception {
		Session session =null;
		List<AppPhotos> appPhoto = new ArrayList<AppPhotos>();
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from AppPhotos a where a.type in (?,?,?,?)");
			query.setInteger(0, int1);
			query.setInteger(1, int2);
			query.setInteger(2, int3);
			query.setInteger(3, int4);
			appPhoto = query.list();
		} catch (Exception e) {
			throw e; 
		}finally{
			
		}
		return appPhoto;
	}

}
