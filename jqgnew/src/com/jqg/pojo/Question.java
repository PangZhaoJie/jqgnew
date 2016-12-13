 package com.jqg.pojo;
 
 import java.io.Serializable;
 import java.util.HashSet;
 import java.util.Set;
 
 public class Question
   implements Serializable
 {
   private Integer questionId;
   private String content;
   private Set questionsetsForQuestionTwoId = new HashSet(0);
   private Set questionsetsForQuestionOneId = new HashSet(0);
 
   public Question()
   {
   }
 
   public Question(Integer questionId)
   {
     this.questionId = questionId;
   }
 
   public Question(Integer questionId, String content, Set questionsetsForQuestionTwoId, Set questionsetsForQuestionOneId)
   {
     this.questionId = questionId;
     this.content = content;
     this.questionsetsForQuestionTwoId = questionsetsForQuestionTwoId;
     this.questionsetsForQuestionOneId = questionsetsForQuestionOneId;
   }
 
   public Integer getQuestionId()
   {
     return this.questionId;
   }
 
   public void setQuestionId(Integer questionId) {
     this.questionId = questionId;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public Set getQuestionsetsForQuestionTwoId() {
     return this.questionsetsForQuestionTwoId;
   }
 
   public void setQuestionsetsForQuestionTwoId(Set questionsetsForQuestionTwoId) {
     this.questionsetsForQuestionTwoId = questionsetsForQuestionTwoId;
   }
 
   public Set getQuestionsetsForQuestionOneId() {
     return this.questionsetsForQuestionOneId;
   }
 
   public void setQuestionsetsForQuestionOneId(Set questionsetsForQuestionOneId) {
     this.questionsetsForQuestionOneId = questionsetsForQuestionOneId;
   }
 }

