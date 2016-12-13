package com.jqg.dao;

import com.jqg.pojo.Question;

import java.util.List;

public abstract interface QuestionDao
{
  public abstract boolean addQuestion(Question paramQuestion)
    throws Exception;

  public abstract boolean updateQuestion(Question paramQuestion)
    throws Exception;

  public abstract boolean deleteQuestion(Question paramQuestion)
    throws Exception;

  public abstract List<Question> findQuestions()
    throws Exception;

  public abstract Question findQuestionByQuestionId(int paramInt)
    throws Exception;
}
