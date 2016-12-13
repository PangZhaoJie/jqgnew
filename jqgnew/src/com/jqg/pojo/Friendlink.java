 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Friendlink
   implements Serializable
 {
   private Integer friendLinkId;
   private String link;
   private Integer place;
   private String name;
   private String photo;
   private Integer isDisplay;
 
   public Friendlink()
   {
   }
 
   public Friendlink(Integer friendLinkId, String link, Integer place, Integer isDisplay)
   {
     this.friendLinkId = friendLinkId;
     this.link = link;
     this.place = place;
     this.isDisplay = isDisplay;
   }
 
   public Friendlink(Integer friendLinkId, String link, Integer place, String name, String photo, Integer isDisplay)
   {
     this.friendLinkId = friendLinkId;
     this.link = link;
     this.place = place;
     setName(name);
     this.photo = photo;
     this.isDisplay = isDisplay;
   }
 
   public Integer getFriendLinkId()
   {
     return this.friendLinkId;
   }
 
   public void setFriendLinkId(Integer friendLinkId) {
     this.friendLinkId = friendLinkId;
   }
 
   public String getLink() {
     return this.link;
   }
 
   public void setLink(String link) {
     this.link = link;
   }
 
   public Integer getPlace() {
     return this.place;
   }
 
   public void setPlace(Integer place) {
     this.place = place;
   }
 
   public String getPhoto()
   {
     return this.photo;
   }
 
   public void setPhoto(String photo) {
     this.photo = photo;
   }
 
   public Integer getIsDisplay() {
     return this.isDisplay;
   }
 
   public void setIsDisplay(Integer isDisplay) {
     this.isDisplay = isDisplay;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 }

