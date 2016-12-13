 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.sql.Timestamp;
 
 public class Usercomment
   implements Serializable
 {
   private Integer commentId;
   private Investstrategy investstrategy;
   private Uservip uservip;
   private String content;
   private Timestamp commentDate;
 
   public Usercomment()
   {
   }
 
   public Usercomment(Integer commentId, Investstrategy investstrategy, Uservip uservip, String content, Timestamp commentDate)
   {
     this.commentId = commentId;
     this.investstrategy = investstrategy;
     this.uservip = uservip;
     this.content = content;
     this.commentDate = commentDate;
   }
 
   public Integer getCommentId()
   {
     return this.commentId;
   }
 
   public void setCommentId(Integer commentId) {
     this.commentId = commentId;
   }
 
   public Investstrategy getInveststrategy() {
     return this.investstrategy;
   }
 
   public void setInveststrategy(Investstrategy investstrategy) {
     this.investstrategy = investstrategy;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public Timestamp getCommentDate() {
     return this.commentDate;
   }
 
   public void setCommentDate(Timestamp commentDate) {
     this.commentDate = commentDate;
   }
 }

