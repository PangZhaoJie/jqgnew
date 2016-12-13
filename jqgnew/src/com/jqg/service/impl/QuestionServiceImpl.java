 package com.jqg.service.impl;
 
 import com.jqg.dao.QuestionDao;
import com.jqg.dao.impl.QuestionDaoIpml;
import com.jqg.pojo.Question;
import com.jqg.service.QuestionService;

import java.util.List;
 
 public class QuestionServiceImpl
   implements QuestionService
 {
   QuestionDao questionDao = new QuestionDaoIpml();
 
   public boolean createQuestion(Question question) throws Exception
   {
     return this.questionDao.addQuestion(question);
   }
 
   public boolean updateQuestion(Question question)
     throws Exception
   {
     return this.questionDao.updateQuestion(question);
   }
 
   public boolean deleteQuestion(Question question)
     throws Exception
   {
     return this.questionDao.deleteQuestion(question);
   }
 
   public List<Question> findQuestions()
     throws Exception
   {
     return this.questionDao.findQuestions();
   }
 
   public Question findQuestionByQuestionId(int questionId)
     throws Exception
   {
     return this.questionDao.findQuestionByQuestionId(questionId);
   }
 }

