 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Latestnews
   implements Serializable
 {
   private Integer newsId;
   private Frontmenu frontmenu;
   private String title;
   private String publish;
   private String importWord;
   private String synopsis;
   private Integer sequence;
   private String photo;
   private Integer isRemotePhoto;
   private String content;
   private Integer isShow;
   private Timestamp publishTime;
   private String url;
 
   public Latestnews()
   {
   }
 
   public Latestnews(Integer newsId, Frontmenu frontmenu, String title)
   {
     this.newsId = newsId;
     this.frontmenu = frontmenu;
     this.title = title;
   }
 
   public Latestnews(Integer newsId, Frontmenu frontmenu, String title, String publish, String importWord, String synopsis, Integer sequence, String photo, Integer isRemotePhoto, String content, Integer isShow, Timestamp publishTime,String url)
   {
     this.newsId = newsId;
     this.frontmenu = frontmenu;
     this.title = title;
     this.publish = publish;
     this.importWord = importWord;
     this.synopsis = synopsis;
     this.sequence = sequence;
     this.photo = photo;
     this.isRemotePhoto = isRemotePhoto;
     this.content = content;
     this.isShow = isShow;
     this.publishTime = publishTime;
     this.url=url;
   }
 
   public Integer getNewsId()
   {
     return this.newsId;
   }
 
   public void setNewsId(Integer newsId) {
     this.newsId = newsId;
   }
 
   public Frontmenu getFrontmenu() {
     return this.frontmenu;
   }
 
   public void setFrontmenu(Frontmenu frontmenu) {
     this.frontmenu = frontmenu;
   }
 
   public String getTitle() {
     return this.title;
   }
 
   public void setTitle(String title) {
     this.title = title;
   }
 
   public String getPublish() {
     return this.publish;
   }
 
   public void setPublish(String publish) {
     this.publish = publish;
   }
 
   public String getImportWord() {
     return this.importWord;
   }
 
   public void setImportWord(String importWord) {
     this.importWord = importWord;
   }
 
   public String getSynopsis() {
     return this.synopsis;
   }
 
   public void setSynopsis(String synopsis) {
     this.synopsis = synopsis;
   }
 
   public Integer getSequence() {
     return this.sequence;
   }
 
   public void setSequence(Integer sequence) {
     this.sequence = sequence;
   }
 
   public String getPhoto() {
     return this.photo;
   }
 
   public void setPhoto(String photo) {
     this.photo = photo;
   }
 
   public Integer getIsRemotePhoto() {
     return this.isRemotePhoto;
   }
 
   public void setIsRemotePhoto(Integer isRemotePhoto) {
     this.isRemotePhoto = isRemotePhoto;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public Integer getIsShow() {
     return this.isShow;
   }
 
   public void setIsShow(Integer isShow) {
     this.isShow = isShow;
   }
 
   public Timestamp getPublishTime() {
     return this.publishTime;
   }
 
   public void setPublishTime(Timestamp publishTime) {
     this.publishTime = publishTime;
   }

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
   
 }

