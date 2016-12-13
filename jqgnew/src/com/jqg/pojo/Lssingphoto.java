 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Lssingphoto
   implements Serializable
 {
   private Integer photoId;
   private Lssuing lssuing;
   private String photo;
 
   public Lssingphoto()
   {
   }
 
   public Lssingphoto(Integer photoId)
   {
     this.photoId = photoId;
   }
 
   public Lssingphoto(Integer photoId, Lssuing lssuing, String photo)
   {
     this.photoId = photoId;
     this.lssuing = lssuing;
     this.photo = photo;
   }
 
   public Integer getPhotoId()
   {
     return this.photoId;
   }
 
   public void setPhotoId(Integer photoId) {
     this.photoId = photoId;
   }
 
   public Lssuing getLssuing() {
     return this.lssuing;
   }
 
   public void setLssuing(Lssuing lssuing) {
     this.lssuing = lssuing;
   }
 
   public String getPhoto() {
     return this.photo;
   }
 
   public void setPhoto(String photo) {
     this.photo = photo;
   }
 }

