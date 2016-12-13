 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Investstrategy
   implements Serializable
 {
   private Integer askId;
   private Asktype asktype;
   private Uservip uservip;
   private Integer commentCount;
   private Integer clickCount;
   private String askTitle;
   private String askContent;
   private Timestamp askDate;
   private Set clicknums = new HashSet(0);
   private Set usercomments = new HashSet(0);
   private Set managercomments = new HashSet(0);
   private int noteCount;
   private String askUserName;
   private int typeFlag;
 
   public Investstrategy()
   {
   }
 
   public Investstrategy(Integer askId, Asktype asktype, Uservip uservip, String askTitle, String askContent, Timestamp askDate, int noteCount, String askUserName, int typeFlag)
   {
     this.askId = askId;
     this.asktype = asktype;
     this.uservip = uservip;
     this.askTitle = askTitle;
     this.askContent = askContent;
     this.askDate = askDate;
     this.noteCount = noteCount;
     this.askUserName = askUserName;
     this.typeFlag = typeFlag;
   }
 
   public Investstrategy(Integer askId, Asktype asktype, Uservip uservip, Integer commentCount, Integer clickCount, String askTitle, String askContent, Timestamp askDate, Set clicknums, Set usercomments, Set managercomments, int noteCount, String askUserName, int typeFlag)
   {
     this.askId = askId;
     this.asktype = asktype;
     this.uservip = uservip;
     this.commentCount = commentCount;
     this.clickCount = clickCount;
     this.askTitle = askTitle;
     this.askContent = askContent;
     this.askDate = askDate;
     this.clicknums = clicknums;
     this.usercomments = usercomments;
     this.managercomments = managercomments;
     this.noteCount = noteCount;
     this.askUserName = askUserName;
     this.typeFlag = typeFlag;
   }
 
   public int getTypeFlag()
   {
     return this.typeFlag;
   }
 
   public void setTypeFlag(int typeFlag) {
     this.typeFlag = typeFlag;
   }
 
   public int getNoteCount() {
     return this.noteCount;
   }
 
   public String getAskUserName() {
     return this.askUserName;
   }
 
   public void setAskUserName(String askUserName) {
     this.askUserName = askUserName;
   }
 
   public void setNoteCount(int noteCount) {
     this.noteCount = noteCount;
   }
 
   public Integer getAskId() {
     return this.askId;
   }
 
   public void setAskId(Integer askId) {
     this.askId = askId;
   }
 
   public Asktype getAsktype() {
     return this.asktype;
   }
 
   public void setAsktype(Asktype asktype) {
     this.asktype = asktype;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Integer getCommentCount() {
     return this.commentCount;
   }
 
   public void setCommentCount(Integer commentCount) {
     this.commentCount = commentCount;
   }
 
   public Integer getClickCount() {
     return this.clickCount;
   }
 
   public void setClickCount(Integer clickCount) {
     this.clickCount = clickCount;
   }
 
   public String getAskTitle() {
     return this.askTitle;
   }
 
   public void setAskTitle(String askTitle) {
     this.askTitle = askTitle;
   }
 
   public String getAskContent() {
     return this.askContent;
   }
 
   public void setAskContent(String askContent) {
     this.askContent = askContent;
   }
 
   public Timestamp getAskDate() {
     return this.askDate;
   }
 
   public void setAskDate(Timestamp askDate) {
     this.askDate = askDate;
   }
 
   public Set getClicknums() {
     return this.clicknums;
   }
 
   public void setClicknums(Set clicknums) {
     this.clicknums = clicknums;
   }
 
   public Set getUsercomments() {
     return this.usercomments;
   }
 
   public void setUsercomments(Set usercomments) {
     this.usercomments = usercomments;
   }
 
   public Set getManagercomments() {
     return this.managercomments;
   }
 
   public void setManagercomments(Set managercomments) {
     this.managercomments = managercomments;
   }
 }

