 package com.jqg.pojo;
 
 import java.io.Serializable;
 
 public class Questionset
   implements Serializable
 {
   private Integer questionSetId;
   private Question questionByQuestionOneId;
   private Uservip uservip;
   private Question questionByQuestionTwoId;
   private String answerOne;
   private String answerTwo;
 
   public Questionset()
   {
   }
 
   public Questionset(Integer questionSetId)
   {
     this.questionSetId = questionSetId;
   }
 
   public Questionset(Integer questionSetId, Question questionByQuestionOneId, Uservip uservip, Question questionByQuestionTwoId, String answerOne, String answerTwo)
   {
     this.questionSetId = questionSetId;
     this.questionByQuestionOneId = questionByQuestionOneId;
     this.uservip = uservip;
     this.questionByQuestionTwoId = questionByQuestionTwoId;
     this.answerOne = answerOne;
     this.answerTwo = answerTwo;
   }
 
   public Integer getQuestionSetId()
   {
     return this.questionSetId;
   }
 
   public void setQuestionSetId(Integer questionSetId) {
     this.questionSetId = questionSetId;
   }
 
   public Question getQuestionByQuestionOneId() {
     return this.questionByQuestionOneId;
   }
 
   public void setQuestionByQuestionOneId(Question questionByQuestionOneId) {
     this.questionByQuestionOneId = questionByQuestionOneId;
   }
 
   public Uservip getUservip() {
     return this.uservip;
   }
 
   public void setUservip(Uservip uservip) {
     this.uservip = uservip;
   }
 
   public Question getQuestionByQuestionTwoId() {
     return this.questionByQuestionTwoId;
   }
 
   public void setQuestionByQuestionTwoId(Question questionByQuestionTwoId) {
     this.questionByQuestionTwoId = questionByQuestionTwoId;
   }
 
   public String getAnswerOne() {
     return this.answerOne;
   }
 
   public void setAnswerOne(String answerOne) {
     this.answerOne = answerOne;
   }
 
   public String getAnswerTwo() {
     return this.answerTwo;
   }
 
   public void setAnswerTwo(String answerTwo) {
     this.answerTwo = answerTwo;
   }
 }

