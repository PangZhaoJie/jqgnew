 package com.jqg.service.impl;
 
 import com.jqg.dao.QuestionsetDao;
import com.jqg.dao.impl.QuestionsetDaoIpml;
import com.jqg.pojo.Questionset;
import com.jqg.service.QuestionsetService;

import java.util.List;
 
 public class QuestionsetServiceImpl
   implements QuestionsetService
 {
   QuestionsetDao questionsetDao = new QuestionsetDaoIpml();
 
   public boolean createQuestionset(Questionset questionset) throws Exception
   {
     return this.questionsetDao.addQuestionset(questionset);
   }
 
   public boolean updateQuestionset(Questionset questionset)
     throws Exception
   {
     return this.questionsetDao.updateQuestionset(questionset);
   }
 
   public boolean deleteQuestionset(Questionset questionset)
     throws Exception
   {
     return this.questionsetDao.deleteQuestionset(questionset);
   }
 
   public List<Questionset> findQuestionsets()
     throws Exception
   {
     return this.questionsetDao.findQuestionsets();
   }
 
   public Questionset findQuestionsetByQuestionsetId(int questionsetId)
     throws Exception
   {
     return this.questionsetDao.findQuestionsetByQuestionsetId(questionsetId);
   }
 
   public Questionset findQuestionsetByUserId(int userId)
     throws Exception
   {
     return this.questionsetDao.findQuestionsetByUserId(userId);
   }
 }

