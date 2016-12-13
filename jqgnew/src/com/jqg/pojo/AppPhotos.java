package com.jqg.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class AppPhotos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3307486296702320903L;
	private Integer appId;//id��
	private String userName;//������
	private String title;//ͼƬ����
	private String pathImages;//ͼƬ·��
	private Integer type;//����
	private String url;//ͼƬ���ӵ�ַ
	private String content;//����
	private Timestamp publishTime;//����ʱ��
	public AppPhotos(){
		
	}
	public AppPhotos(Integer appId){
		this.appId = appId;
	}
	public AppPhotos(Integer appId,String userName,String title,String pathImages, Integer type,String url,String content,Timestamp publishTime){
		this.appId = appId;
		this.title = title;
		this.pathImages = pathImages;
		this.type = type;
		this.url = url;
		this.content = content;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPathImages() {
		return pathImages;
	}
	public void setPathImages(String pathImages) {
		this.pathImages = pathImages;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}
	
}
