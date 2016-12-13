package com.jqg.dao;

import com.jqg.pojo.Questionset;

import java.util.List;

public abstract interface QuestionsetDao
{
  public abstract boolean addQuestionset(Questionset paramQuestionset)
    throws Exception;

  public abstract boolean updateQuestionset(Questionset paramQuestionset)
    throws Exception;

  public abstract boolean deleteQuestionset(Questionset paramQuestionset)
    throws Exception;

  public abstract List<Questionset> findQuestionsets()
    throws Exception;

  public abstract Questionset findQuestionsetByQuestionsetId(int paramInt)
    throws Exception;

  public abstract Questionset findQuestionsetByUserId(int paramInt)
    throws Exception;
}
